package com.ucab.fin.finucab.webservice;

/**
 * Created by Junior on 17/05/2017.
 */

import org.json.JSONObject;

/**
 * Esta clase se encarga de mantener la informacion que devuelve el servicio web y que esta
 * pueda usarse desde cualquier clase del proyecto.
 *
 */
public class Parametros {

    public static String server ="";// Almacena la direccion IP o Dominio del servidor donde se aloja el WebService
    public static String puerto =""; // Almacena el puerto
    public static String url="";  // Almacena la URL del servicio web, con sus metodos y parametros.
    public static String respuesta=""; // Almacena la respuesta del servicio web para que se pueda consumir.
    public static JSONObject objetoJson; //Objeto a ser enviado o resibido;

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

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        Parametros.server = server;
    }

    public static String getPuerto() {
        return puerto;
    }

    public static void setPuerto(String puerto) {
        Parametros.puerto = puerto;
    }
    public static void setMetodo(String direccion){

        Parametros.url = server +":"+puerto+"/FinUcabWebService/webresources/"+direccion;
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
