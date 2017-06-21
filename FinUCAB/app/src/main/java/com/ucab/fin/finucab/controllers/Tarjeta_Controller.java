package com.ucab.fin.finucab.controllers;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucab.fin.finucab.domain.Manejador_Banco;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ErrorSpinner_Exception;
import com.ucab.fin.finucab.exceptions.Longitud_Exception;
import com.ucab.fin.finucab.fragment.AgregarBancoFragment;
import com.ucab.fin.finucab.fragment.AgregarTarjetaCFragment;
import com.ucab.fin.finucab.fragment.ModificarBancoFragment;
import com.ucab.fin.finucab.fragment.ModificarTarjetaCFragment;

/**
 * Created by Junior on 21/06/2017.
 */

public class Tarjeta_Controller {

    private static Manejador_Banco manejador;
    public static int  casoRequest = -1;
    public static EditText tipotarjeta;  // EditText que contiene el tipo de la tarjeta.
    public static EditText numerotarjeta;// EditText que contiene el numero de la tajeta.
    public static EditText fechaven; // EditText que contiene la fecha de vencimiento de la tarjeta.
    public static Spinner cuentaafiliada; // Spinner que contiene la cuenta a la que esta afilida.

    /**
     *  Metodo encargado de validar los datos suministrados en el registro de cuentas bancarias.
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
            verificoCuentaAfiliada(cuentaafiliada);
        } catch (CampoVacio_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (Longitud_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (ErrorSpinner_Exception e) {

            if (fragment instanceof AgregarTarjetaCFragment){
                AgregarTarjetaCFragment frag = (AgregarTarjetaCFragment)fragment;
                Toast.makeText(fragment.getActivity(),"Debe seleccionar un tipo de cuenta",
                        Toast.LENGTH_SHORT).show();

            }
            if (fragment instanceof ModificarTarjetaCFragment){
                ModificarTarjetaCFragment frag = (ModificarTarjetaCFragment)fragment;
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




}
