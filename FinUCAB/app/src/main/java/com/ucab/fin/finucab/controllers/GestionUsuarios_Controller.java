package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Usuario;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenaInvalida_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenasDiferentes_Exception;
import com.ucab.fin.finucab.exceptions.CorreoInvalido_Exception;
import com.ucab.fin.finucab.exceptions.Longitud_Exception;
import com.ucab.fin.finucab.exceptions.UsuarioInvalido_Exception;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * Created by Junior on 06/05/2017.
 */

public class GestionUsuarios_Controller {
    //Recursos:
    public static EditText nombre;
    public static EditText apellido;
    public static EditText correo;
    public static EditText usuario;
    public static EditText contrasena1;
    public static EditText contrasena2;
    public static EditText pregunta;
    public static EditText respuesta;
    public static int pasoRegistro;





    public GestionUsuarios_Controller (){


    }
    // Valida que no esten vacios los campos y coincida con la respuesta establecida por el usuario con la establecida por el usuario en recuperacion de contraseña
    public static int validacionRespuesta() {

        try {
        verificoVacio(respuesta);
            //FALTA VERIFICAR QUE COINCIDA CON LA ESTABLECIDA
        } catch (CampoVacio_Exception e) {
            e.getCampo().setError(e.getMessage());
        return 1;
        }
        return 0;
    }

    // Valida que no esten vacios los campos y coincidan las contraseñas en recuperacion de contraseña
    public static int validacionContrasenas() {

        try {
            verificoVacio(contrasena1);
            verificoVacio(contrasena2);
            verificoIgualdad(contrasena1,contrasena2);
            //FALTA VERIFICAR QUE COINCIDA CON LA ESTABLECIDA
        } catch (CampoVacio_Exception e) {
            e.getCampo().setError(e.getMessage());
            return 1;
        }catch (ContrasenasDiferentes_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }
        return 0;
    }

    //Se encarga de validar que no se encuentre vacio los campos nombre, apellido, correo
    //Tambien se valida si el formato del correo electronico es correcto.
    public static int validacionEtapaDatos(){

        try{
            verificoVacio(nombre);
            verificoLongitud(nombre,50,"string");
            verificoVacio(apellido);
            verificoLongitud(apellido,50,"string");
            verificoVacio(correo);
            verificoLongitud(correo,100,"string");
            verificoCorreo(correo);
        }catch (CampoVacio_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (CorreoInvalido_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }catch (Longitud_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }

               //Estadarizamos mayusculas y minusculas:
               nombre.setText(GestionUsuarios_Controller.formatearCadena(nombre.getText().toString()));
               apellido.setText(GestionUsuarios_Controller.formatearCadena(apellido.getText().toString()));
               return 0;
    }


     /**
     *  Metodo encargado de validar los datos suministrados en la etapa de registro de datos de la cuenta.
     *
     *
     * @return
     */
     public static int validacionEtapaCuenta(Activity activity)
    {
          try{
              verificoVacio(usuario);
              verificoLongitud(usuario,50,"string");
              verificoVacio(contrasena1);
              verificoVacio(contrasena2);
              verificoIgualdad(contrasena1,contrasena2);
          } catch (CampoVacio_Exception e){
              e.getCampo().setError(e.getMessage());
              return 1;
          } catch (ContrasenasDiferentes_Exception e){
              e.getCampo().setError(e.getMessage());
              return 1;
          } catch (Longitud_Exception e){
              e.getCampo().setError(e.getMessage());
              return 1;
          }
        /*
            Falta validar si  ya existe el nombre de usuario en el sistema.
         */

        return 0;
    }


    /**Se encarga de validar que no se encuentre vacio los campos pregunta, respuesta
     * en la etapa de registro de datos de la seguridad de las cuentas.
     *
     *
    **/
     public static int validacionEtapaSeguridad(){
        try{
            verificoVacio(pregunta);
            verificoLongitud(pregunta,1000,"string");
            verificoVacio(respuesta);
            verificoLongitud(respuesta,1000,"string");
        }catch (CampoVacio_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }catch (Longitud_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }

        return 0;
    }

    /**
     * Validacion para verficar que los campos cumplan con el rango correcto
     *
     * @Param campo representa el EditText que contiene el texto a verificar
     * @Param longitud representa el limite maximo que debe tener la cadena de caracteres a validad
     * @Param tipo representa el tipo de dato del texto (String o Int)
     **/
    public static void verificoLongitud(EditText campo,int longitud, String tipo)throws Longitud_Exception {
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

    /**Realizo la validacion para verificar que el campo este vacio:
     *
     *
     * @param campo
     * @throws CampoVacio_Exception
     */
    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {
        if (campo.getText().toString().isEmpty())
        {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }

    }


    //Realizo la validacion para verificar que el usuario este correcto y si no esta repetido:
    public static void verificoUsuario(EditText campo) throws UsuarioInvalido_Exception {
        if (campo.getText().toString().isEmpty()) //HAY QUE CORREGIR LA VERIFICACION
        {
            UsuarioInvalido_Exception campoerroneo = new UsuarioInvalido_Exception("Usuario Inexistente");
            campoerroneo.setCampo(campo);
            throw campoerroneo;
        }
    }


    //Realizo la validacion para verificar que el usuario este correcto y si no esta repetido:
    public static void verificoUsuario(Activity actividad,EditText campo) throws UsuarioInvalido_Exception {
        if (campo.getText().toString().isEmpty()) //HAY QUE CORREGIR LA VERIFICACION
        {
            UsuarioInvalido_Exception campoerroneo = new UsuarioInvalido_Exception("Usuario Inexistente");
            campoerroneo.setCampo(campo);
            throw campoerroneo;
        }
        Parametros.setMetodo("verificarUsuario?nombreUsuario="+campo.getText().toString());
        new Recepcion(actividad).execute("GET");

    }

    //Realizo la validacion para verificar que la contraseña coincide a la establecida por ese usuario:
    public static void verificoContrasena(EditText campo) throws ContrasenaInvalida_Exception {
        if (campo.getText().toString().isEmpty()) //HAY QUE CORREGIR LA VERIFICACION
        {
            ContrasenaInvalida_Exception campoerroneo = new ContrasenaInvalida_Exception("Contraseña Invalida");
            campoerroneo.setCampo(campo);
            campo.setText("");
            throw campoerroneo;
        }

    }

    /**Realizo la validacion para verificar que las contraseñas son diferentes:
     *
     *
     * @param contesena1
     * @param contrasena2
     * @throws ContrasenasDiferentes_Exception
     */
    public static void verificoIgualdad(EditText contesena1, EditText contrasena2) throws ContrasenasDiferentes_Exception {
        if (!(contrasena1.getText().toString().equals(contrasena2.getText().toString())))
        {
            ContrasenasDiferentes_Exception diferentes = new ContrasenasDiferentes_Exception("Las contraseñas son diferentes");
            diferentes.setCampo(contrasena2);
            throw diferentes;
        }



    }

    /**Realizo la validacion para verificar si el correo electronico suministrado es valido.
     * @param campo
     * @throws CorreoInvalido_Exception
     */
    public static void verificoCorreo(EditText campo)throws CorreoInvalido_Exception{
        if (!campo.getText().toString().contains("@")) {
            CorreoInvalido_Exception correoinvalido  =  new CorreoInvalido_Exception("El correo es invalido");
            correoinvalido.setCampo(campo);
            throw correoinvalido;
        }else
        {
            String [] fragmento = campo.getText().toString().split("@");
            if (fragmento.length==2) {
                if (!fragmento[1].contains(".")) {
                    CorreoInvalido_Exception correoinvalido = new CorreoInvalido_Exception("El correo es invalido");
                    correoinvalido.setCampo(campo);
                    throw correoinvalido;
                }
            }else
            {
                CorreoInvalido_Exception correoinvalido = new CorreoInvalido_Exception("El correo es invalido");
                correoinvalido.setCampo(campo);
                throw correoinvalido;
            }
        }
    }


    /**Este metodo le da un formato unico a los Nombre y Apellidos.
     * Colocando su primera letra en Mayusculas y el resto en minusculas.
     * @Param texto // Cadena de texto a ser formateada
     *
    **/
     public static CharSequence formatearCadena (String texto){
        String convertido = texto.toLowerCase();
        return Character.toUpperCase(convertido.charAt(0)) + convertido.substring(1);
    }

    /**
     *  Metodo encargado de realizar de enviar los datos del usuario para su registro en el
     *  servidor del sistema.
     *
     * @param usuario  // Obejto de tipo usuario con los datos del nuevo usuario
     * @param actividad // Actidad actual donde se ejecuta la accion.
     * @return
     */
    public static String registrarUsuario(Usuario usuario, Activity actividad){
        JSONObject nuevo_usuario = new JSONObject();
        try {
            nuevo_usuario.put("u_usuario",usuario.getUsuario());
            nuevo_usuario.put("u_nombre",usuario.getNombre());
            nuevo_usuario.put("u_apellido",usuario.getApellido());
            nuevo_usuario.put("u_correo",usuario.getCorreo());
            nuevo_usuario.put("u_password",usuario.getContrasena());
            nuevo_usuario.put("u_pregunta",usuario.getPregunta());
            nuevo_usuario.put("u_respuesta",usuario.getRespuesta());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("registrarUsuario?datosUsuario="+URLEncoder.encode(nuevo_usuario.toString()));
        new Recepcion(actividad).execute("GET");
        return Parametros.getRespuesta();
    }

    /**
     * Función de tipo entero que devuelve el codigo hash del la constrasena suministrada
     *
     * @param cleartext el texto sin cifrar a encriptar
     * @return el texto cifrado en modo int
     */
    public static int encriptarDatos(String cleartext)  {

         return cleartext.hashCode();
    }






    /**Inicializo nuevamente las variables
     *
     */
    public static void resetarVariables (){
         nombre=null;
         apellido=null;
         correo=null;
         usuario=null;
         contrasena1=null;
         contrasena2=null;
         pregunta=null;
         respuesta=null;
    }


}
