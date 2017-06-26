package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucab.fin.finucab.domain.Cuenta_Bancaria;
import com.ucab.fin.finucab.domain.Manejador_Banco;
import com.ucab.fin.finucab.domain.Manejador_Tarjeta;
import com.ucab.fin.finucab.domain.Tarjeta_Credito;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ErrorSpinner_Exception;
import com.ucab.fin.finucab.exceptions.Longitud_Exception;
import com.ucab.fin.finucab.fragment.AgregarBancoFragment;
import com.ucab.fin.finucab.fragment.AgregarTarjetaCFragment;
import com.ucab.fin.finucab.fragment.ModificarBancoFragment;
import com.ucab.fin.finucab.fragment.ModificarTarjetaCFragment;
import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import java.util.ArrayList;

/**
 * Created by Junior on 21/06/2017.
 */

public class Tarjeta_Controller {

    private static Manejador_Tarjeta manejador;
    public static int  casoRequest = -1;
    public static EditText tipotarjeta;  // EditText que contiene el tipo de la tarjeta.
    public static EditText numerotarjeta;// EditText que contiene el numero de la tajeta.
    public static EditText fechaven; // EditText que contiene la fecha de vencimiento de la tarjeta.



    public static void initManejador(Activity actividad, ResponseWebServiceInterface interfaz){

        if ( manejador == null ||  manejador.getIntefaz() != interfaz ) {

            manejador = new Manejador_Tarjeta(actividad, interfaz);

        }

    }


    /**
     * Colocar actual lista de categoria en el manejador
     * @param tarjetas
     */
    public static void setListaTarjetas(ArrayList<Tarjeta_Credito> tarjetas){

        manejador.setUltimasTarjetasObtenidas(tarjetas);
    }

    /**
     * Colocar actual lista de categoria en el manejador
     * @return Lista de categoria cargada
     */
    public static ArrayList<Tarjeta_Credito> getListaTarjetas(){

        return manejador.getultimasTarjetasObtenidas();
    }
    /**
     *  Metodo encargado de llamar a agregar Tarjeta
     * @param tarjeta Tarjeta de Creditoa registrar
     */
    public static void registrarTarjeta(Tarjeta_Credito tarjeta){

        casoRequest = 1;
        manejador.agregarTarjeta(tarjeta);

    }



    /**
     *  Metodo encargado de validar los datos suministrados en el registro de Tarjetas de Credito.
     *
     * @return retorna 0 si no hay ningun error y retorna 1 si lo hay
     */
    public static int validacionTarjetas(Fragment fragment)
    {
        try{
            verificoVacio(tipotarjeta);
            verificoLongitud(tipotarjeta,255,"string");
            verificoVacio(numerotarjeta);
            verificoLongitud(numerotarjeta,21,"int");
            verificoVacio(fechaven);
        } catch (CampoVacio_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (Longitud_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }
        return 0;
    }

    /**
     * Metodo encargado de validar el tipo de cuenta bancaria seleccionado en el spinner
     *
     */
    public static void verificoCuentaAfiliada(Spinner tipocuenta) throws ErrorSpinner_Exception {
        String text = tipocuenta.getSelectedItem().toString();
        if (text.equals("Seleccione")) {
            ErrorSpinner_Exception debetipo = new ErrorSpinner_Exception("Debe seleccionar una " +
                    "cuenta");
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
     * Metodo encargado de llamar a obtener las categorias
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     */
    public static void obtenerTodasTarjetas(boolean showStatus){

        casoRequest = 1;
        manejador.obtenerTodasTarjetas(showStatus);

    }

}
