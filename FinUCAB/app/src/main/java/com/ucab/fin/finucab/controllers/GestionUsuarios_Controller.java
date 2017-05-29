package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Usuario;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenaInvalida_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenasDiferentes_Exception;
import com.ucab.fin.finucab.exceptions.CorreoInvalido_Exception;
import com.ucab.fin.finucab.exceptions.Longitud_Exception;
import com.ucab.fin.finucab.exceptions.RespuestaInvalida_Exception;
import com.ucab.fin.finucab.exceptions.UsuarioInvalido_Exception;
import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase se encarga de gestionar los datos entrantes y salientes de la aplicacion que va
 * relacionado con los usuarios del sistema.
 *
 **/

public class GestionUsuarios_Controller {
    //Recursos:
    public static EditText nombre;  // EditText que contiene el nombre del usuario
    public static EditText apellido;// EditText que contiene el apellido del usuario
    public static EditText correo; // EditText que contiene el correo del usuario
    public static EditText usuario; // EditText que contiene el nombre de usuario de la cuenta
    public static EditText contrasena1; // EditText que contiene la contrasena del usuario
    public static EditText contrasena2; // EditText que contiene la contrasena confirmada del usuario
    public static EditText pregunta; // EditText que contiene la pregunta secreta del usuario
    public static EditText respuesta; // EditText que contiene la respuesta secreta del usuario
    public static int pasoRegistro; // Variable entera que almacena la etapa actual del registro de usuario.
    public static int pasoRecuperacion; // Variable entera que almacena la etapa actual del recuperacion de usuario.
    public static boolean pasoRegistroCuenta; // Variable booleana que indica presencia de error en el paso 2 de registro.

    /**
     * Valida que no esten vacios los campos y coincida con la respuesta establecida por el usuario
     * con la establecida por el usuario en recuperacion de contraseña
     *
     */
    public static int validacionRespuesta() {

        try {
            verificoVacio(respuesta);
            verificoRespuesta(respuesta);
            //FALTA VERIFICAR QUE COINCIDA CON LA ESTABLECIDA
        } catch (CampoVacio_Exception e) {
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (RespuestaInvalida_Exception e) {
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
        } catch (CampoVacio_Exception e) {
            e.getCampo().setError(e.getMessage());
            return 1;
        }catch (ContrasenasDiferentes_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }
        return 0;
    }


    /**
     *  Se encarga de validar que no se encuentre vacio los campos nombre, apellido, correo
     *  tambien se valida si el formato del correo electronico es correcto.
     *  @return retorna 0 si no hay ningun error y retorna 1 si los hay.
     */
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
     * @return retorna 0 si no hay ningun error y retorna 1 si lo hay
     */
    public static int validacionEtapaCuenta()
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
        return 0;
    }


    /**Se encarga de validar que no se encuentre vacio los campos pregunta, respuesta
     * en la etapa de registro de datos de la seguridad de las cuentas.
     * @return retorna 0 si no hay ningun error y retorna 1 si lo hay
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
     * Realiza Validacion para verficar que los campos cumplan con el rango correcto
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
     * Realiza la validacion para verificar que el usuario y contraseña coincidan
     *
     * @param usuario campo de texto (EditText) donde se almacena el usuario.
     * @param clave campo de texto (EditText) donde se almacena la clave.
     *
     */
    public static void inicioSesion(EditText usuario,EditText clave,Activity actividad) {
        JSONObject nuevo_usuario = new JSONObject();
        try {
            nuevo_usuario.put("u_password",String.valueOf(clave.getText().toString().hashCode()));
            nuevo_usuario.put("u_usuario",usuario.getText());
            System.out.println(URLEncoder.encode(nuevo_usuario.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo1/iniciarSesion?datosUsuario="+URLEncoder.encode(nuevo_usuario.toString()));
        new Recepcion(actividad).execute("GET");
    }


    /**
     *   Realizo la validacion para verificar que el usuario  no este repetido en el sistema:
     *
     *   @param actividad  es la actividad actual donde se esta validando el usuario
     *   @param campo es el campo de texto (EditText) donde se va a validar la exitencia del usuario
     */
    public static void verificoUsuario(Activity actividad,EditText campo) throws UsuarioInvalido_Exception {
        if (campo.getText().toString().isEmpty()) //HAY QUE CORREGIR LA VERIFICACION
        {
            UsuarioInvalido_Exception campoerroneo = new UsuarioInvalido_Exception("Usuario Inexistente");
            campoerroneo.setCampo(campo);
            throw campoerroneo;
        }
        Parametros.setMetodo("Modulo1/verificarUsuario?nombreUsuario="+campo.getText().toString());
        new Recepcion(actividad).execute("GET");

    }

    /**
     *   Realizo la validacion para verificar que el usuario no este vacio
     *
     *   @param campo es el campo de texto (EditText) donde se va a validar la exitencia del usuario
     */
    public static void verificoUsuario(EditText campo) throws UsuarioInvalido_Exception {
        if (campo.getText().toString().isEmpty()) //HAY QUE CORREGIR LA VERIFICACION
        {
            UsuarioInvalido_Exception campoerroneo = new UsuarioInvalido_Exception("Usuario Inexistente");
            campoerroneo.setCampo(campo);
            throw campoerroneo;
        }
    }

    /**
     *  Realizo la validacion para verificar que la contraseña coincide a la establecida por ese usuario:
     *  @Param campo campo de texto donde se va a validar
     */
    public static void verificoContrasena(EditText campo) throws ContrasenaInvalida_Exception {
        if (!String.valueOf(campo.getText().toString().hashCode()).equals(ControlDatos.getUsuario().getContrasena()))
        {
            ContrasenaInvalida_Exception campoerroneo = new ContrasenaInvalida_Exception("Contraseña Incorrecta");
            campoerroneo.setCampo(campo);
            campo.setText("");
            throw campoerroneo;
        }

    }

    /**
     *  Realizo la validacion para verificar que la respuesta secreta coincide a la establecida por ese usuario:
     *  @Param campo campo de texto donde se va a validar
     */
    public static void verificoRespuesta(EditText campo) throws RespuestaInvalida_Exception {
        if(!String.valueOf(campo.getText().toString().hashCode()).equals(ControlDatos.getUsuario().getRespuesta()))
        {
            RespuestaInvalida_Exception campoerroneo = new RespuestaInvalida_Exception("Respuesta Incorrecta");
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
     * @return devuelve una cadena de caracteres formateada de esta forma "Formato"
     **/
    public static CharSequence formatearCadena (String texto){
        if (texto.length()!=0) {
            String convertido = texto.toLowerCase();
            return Character.toUpperCase(convertido.charAt(0)) + convertido.substring(1);
        }
        return "";
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
        Parametros.setMetodo("Modulo1/registrarUsuario?datosUsuario="+URLEncoder.encode(nuevo_usuario.toString()));
        new Recepcion(actividad).execute("GET");
        return Parametros.getRespuesta();
    }



    /**
     *  Metodo encargado de consultar si el usuario existe en el servidor del sistema.
     *
     * @param clave  // String con la clave nueva
     * @return
     */
    public static void actualizarContrasena(String clave, Activity actividad){
        JSONObject nuevo_usuario = new JSONObject();
        try {
            nuevo_usuario.put("u_password",String.valueOf(clave.hashCode()));
            nuevo_usuario.put("u_usuario",ControlDatos.getUsuario().getUsuario());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo1/actualizarClave?datosUsuario="+URLEncoder.encode(nuevo_usuario.toString()));
        new Recepcion(actividad).execute("GET");



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


    /**
     * Metodo para consultar datos del usuario del servidor
     * @param usuario Nombre de usuario
     * @param activity Actividad actual donde se ejecuta el metodo
     */
    public static void buscarUsuario(Activity activity, String usuario){
        Parametros.setMetodo("Modulo1/recuperarClave?datosUsuario=" + usuario);
        new Recepcion(activity).execute("GET");


    }

    /**
     * Metodo para obtener datos del usuario del servidor
     * @param datos informacion en JSON
     *
     */
    public static void descomponerUsuario(String datos){

        try {
            JSONObject usuario = new JSONObject(datos);
            ControlDatos.setUsuario(new Usuario(usuario.getInt("u_id"), usuario.getString("u_nombre"),
            usuario.getString("u_apellido"), usuario.getString("u_correo"), usuario.getString("u_usuario"),
            usuario.getString("u_password"),usuario.getString("u_pregunta"), usuario.getString("u_respuesta"), null, null));
        } catch (JSONException e) {
            e.printStackTrace();
        }


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
        pasoRecuperacion=0;

    }


}