package com.ucab.fin.finucab.webservice;

/**
 * Created by Junior on 17/05/2017.
 */

/**
 * Esta clase se encarga de mantener la informacion que devuelve el servicio web y que esta
 * pueda usarse desde cualquier clase del proyecto.
 *
 */
public class Parametros {

    public static String url="";  // Almacena la URL del servicio web, con sus metodos y parametros.
    public static String respuesta=""; // Almacena la respuesta del servicio web para que se pueda consumir.

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

    /**
     * Este metodo se encarga de colocar valores nulos a los atributos de la clase
     * con el fin de que pueda utilizarse nuevamente por otro proceso del programa.
     */
    public static void reset (){
        url =null;
        respuesta = null;

    }

}
