package com.ucab.fin.finucab.controllers;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Cuenta_Bancaria;
import com.ucab.fin.finucab.domain.Manejador_Banco;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Tarjeta_Credito;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenasDiferentes_Exception;
import com.ucab.fin.finucab.exceptions.ErrorSpinner_Exception;
import com.ucab.fin.finucab.exceptions.Longitud_Exception;
import com.ucab.fin.finucab.fragment.AgregarBancoFragment;
import com.ucab.fin.finucab.fragment.ModificarBancoFragment;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import java.util.ArrayList;

/**
 * Created by Junior on 14/06/2017.
 */

public class Banco_Controller {

    private static Manejador_Banco manejador;
    public static Cuenta_Bancaria banco;
    public static int  casoRequest = -1;
    public static EditText nombrebanco;  // EditText que contiene el nombre del banco
    public static EditText numerocuenta;// EditText que contiene el numero de cuenta
    public static EditText saldoinicial; // EditText que contiene el saldo inicial de la cuenta
    public static Spinner tipocuenta; // Spinner que contiene el tipo de cuenta seleccionado


    public static void initManejador(Activity actividad, ResponseWebServiceInterface interfaz){

        if ( manejador == null ||  manejador.getIntefaz() != interfaz ) {

            manejador = new Manejador_Banco(actividad, interfaz);

        }

    }


    /**
     * Metodo encargado de llamar a modificar  la Tarjeta seleccionada
     * @param banco  a modificar
     */
    public static void modificarBanco(Cuenta_Bancaria banco){

        casoRequest = 2;
        manejador.modificarBanco(banco);

    }

    /**
     * Metodo encargado de llamar a eliminar Cuenta bancaria
     * @param posicion posicion seleccionada de la lista
     */
    public static void borrarBanco(int posicion){

        casoRequest = 3;
        int id = manejador.getUltimosBancosObtenidos().get(posicion).getIdCuenta();
        manejador.borrarBanco(id);

    }


    /**
     * Colocar actual lista de categoria en el manejador
     * @param bancos
     */
    public static void setListaBancos(ArrayList<Cuenta_Bancaria> bancos){

        manejador.setUltimosBancosObtenidos(bancos);
    }

    /**
     * Colocar actual lista de categoria en el manejador
     * @return Lista de categoria cargada
     */
    public static ArrayList<Cuenta_Bancaria> getListaBancos(){

        return manejador.getUltimosBancosObtenidos();
    }

    /**
     * Resetea el caso del request al WebService
     */
    public static void resetCasoRequest(){
        casoRequest = -1;
    }

    /**
     * Obtener caso del request que se esta realizando
     * @return
     */
    public static int getCasoRequest(){
        return casoRequest;
    }


    /**
     *  Metodo encargado de llamar a agregar categoria
     * @param banco Cuenta bancaria a registrar
     */
    public static void registrarBanco(Cuenta_Bancaria banco){

        casoRequest = 1;
        manejador.agregarBanco(banco);

    }





    /**
     * Metodo encargado de llamar a obtener las bancos
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     */
    public static void obtenerTodosBancos(boolean showStatus){

        casoRequest = 0;
        manejador.obtenerTodosBancos(showStatus);

    }
    /**
     *  Metodo encargado de validar los datos suministrados en el registro de cuentas bancarias.
     *
     * @return retorna 0 si no hay ningun error y retorna 1 si lo hay
     */
    public static int validacionBancos(Fragment fragment)
    {
        try{
            verificoVacio(nombrebanco);
            verificoLongitud(nombrebanco,255,"string");
            verificoVacio(numerocuenta);
            verificoLongitud(numerocuenta,21,"int");
            verificoVacio(saldoinicial);
            verificoLongitud(saldoinicial,10,"int");
            verificoTipoCuenta(tipocuenta);
        } catch (CampoVacio_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (Longitud_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (ErrorSpinner_Exception e) {

            if (fragment instanceof AgregarBancoFragment){
                AgregarBancoFragment frag = (AgregarBancoFragment)fragment;
                Toast.makeText(fragment.getActivity(),"Debe seleccionar un tipo de cuenta",
                        Toast.LENGTH_SHORT).show();

            }
            if (fragment instanceof ModificarBancoFragment){
                ModificarBancoFragment frag = (ModificarBancoFragment)fragment;
                Toast.makeText(fragment.getActivity(),"Debe seleccionar un tipo de cuenta",
                        Toast.LENGTH_SHORT).show();

            }

            return 1;
        }
        return 0;
    }

    /**
     * Metodo encargado de validar el tipo de cuenta bancaria seleccionado en el spinner
     *
     */
    public static void verificoTipoCuenta(Spinner tipocuenta) throws ErrorSpinner_Exception {
         String text = tipocuenta.getSelectedItem().toString();
         if (text.equals("Seleccione")) {
             ErrorSpinner_Exception debetipo = new ErrorSpinner_Exception("Debe seleccionar un " +
                     "tipo de cuenta");
             debetipo.setSeleccion(tipocuenta);
             throw debetipo;
         }
     }


    /**
     * Realiza Validacion para verficar que los campos cumplan con el rango correcto
     *
     * @Param campo representa el EditText que contiene el texto a verificar
     * @Param longitud representa el limite maximo que debe tener la cadena de caracteres a validad
     * @Param tipo representa el tipo de dato del texto (String o Int)
     **/
    public static void verificoLongitud(EditText campo, int longitud, String tipo)throws Longitud_Exception {
        if (campo.getText().toString().length() >= longitud) {
            if (tipo.equals("string")) {
                Longitud_Exception campolargo = new Longitud_Exception("El texto es demasiado largo");
                campolargo.setCampo(campo);
                throw campolargo;
            }
            if (tipo.equals("int")) {
                Longitud_Exception campolargo = new Longitud_Exception("El numero es muy alto");
                campolargo.setCampo(campo);
                throw campolargo;
            }
        }
    }


    /**
     * Función de tipo entero que devuelve el codigo hash del la constrasena suministrada
     *
     * @param cleartext el texto sin cifrar a encriptar
     * @return el texto cifrado en int
     */
    public static int encriptarDatos(String cleartext)  {

        return cleartext.hashCode();
    }

    /**Este metodo le da un formato unico a los Nombre y Apellidos.
     * Colocando su primera letra en Mayusculas y el resto en minusculas.
     * @Param texto // Cadena de texto a ser formateada
     * @return devuelve una cadena de caracteres formateada de esta forma "Formato"
     **/
    public static CharSequence formatearCadena (String texto){
        if (texto.length()!=0) {
            String convertido = texto.toLowerCase();
            return Character.toUpperCase(convertido.charAt(0)) + convertido.substring(1);
        }
        return "";
    }

    /**Realiza la validacion para verificar que el campo este vacio:
     *
     * @param campo  Es el campo de texto EditText que quiero validad
     * @throws CampoVacio_Exception Señala que el campo esta vacio
     */
    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {
        if (campo.getText().toString().isEmpty())
        {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }

    }





}
