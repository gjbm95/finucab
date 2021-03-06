package com.ucab.fin.finucab.webservice;

/**
 * Created by Junior on 17/05/2017.
 */

import android.util.Log;

import com.ucab.fin.finucab.registros.Registro;

import org.json.JSONObject;
/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase se encarga de mantener la informacion que devuelve el servicio web y que esta
 * pueda usarse desde cualquier clase del proyecto.
 **/

public class Parametros {

    private static String url="";  // Almacena la URL del servicio web, con sus metodos y parametros.
    private static String respuesta=""; // Almacena la respuesta del servicio web para que se pueda consumir.
    private static JSONObject objetoJson; //Objeto a ser enviado o resibido;

    public Parametros () {

    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Parametros.url = url;
    }

    public static String getRespuesta() {
        return respuesta;
    }

    public static void setRespuesta(String respuesta) {
        Parametros.respuesta = respuesta;
    }

    public static JSONObject getObjetoJson() {
        return objetoJson;
    }

    public static void setObjetoJson(JSONObject objetoJson) {
        Parametros.objetoJson = objetoJson;
    }

    public static void setMetodo(String direccion){

        Parametros.url = Registro.server +":"+Registro.puerto+"/FinUcabWebService/webresources/"+direccion;

        Log.v("Request",Parametros.url);
    }
    /**
     * Este metodo se encarga de colocar valores nulos a los atributos de la clase
     * con el fin de que pueda utilizarse nuevamente por otro proceso del programa.
     */
    public static void reset (){
        respuesta = null;
        objetoJson = null;

    }

}
