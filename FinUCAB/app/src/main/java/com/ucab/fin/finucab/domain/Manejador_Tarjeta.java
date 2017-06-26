package com.ucab.fin.finucab.domain;

import android.app.Activity;

import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Junior on 25/06/2017.
 */

public class Manejador_Tarjeta {


    private Activity actividad;
    private ArrayList<Tarjeta_Credito> ultimasTarjetasObtenidas; //creacion de un array de tipo Banco
    private ResponseWebServiceInterface intefaz; //creacion de una interfaz para funcionalidades de vistas

        /*------------------------------------- CONSTRUCTORES ----------------------------------------*/

    public Manejador_Tarjeta(Activity actividad, ResponseWebServiceInterface intefaz){

        this.actividad = actividad;
        this.intefaz = intefaz;
    }

    public Manejador_Tarjeta(Activity actividad){

        this.actividad = actividad;
        this.intefaz = null;
    }




    public ResponseWebServiceInterface getIntefaz() {
        return intefaz;
    }

    public ArrayList<Tarjeta_Credito> getultimasTarjetasObtenidas() {
        return ultimasTarjetasObtenidas;
    } // Obtener ultima lista recuperada
    public void setUltimasTarjetasObtenidas(ArrayList<Tarjeta_Credito> tarjetas) {
        this.ultimasTarjetasObtenidas = tarjetas;
    } // Asignar ultima lista recuperada


    /**Creacion del metodo eliminar Tarjeta
     * conexion con WebService por medio de Json
     *
     * @param id Id de la Tarjeta a borrar
     */
    public void borrarTarjeta( int id) {

        Parametros.reset();
        Parametros.setMetodo("Modulo2/eliminarTarjeta?datosBanco="+ String.valueOf(id));
        new Recepcion(actividad,intefaz).execute("GET");

    }

   /*------------------------------------- REQUEST ----------------------------------------*/

    /**Creacion del metodo agregar Tarjeta
     * conexion con WebService por medio de Json
     *
     * @param tarjeta Tarjeta a registrar
     */
    public void agregarTarjeta(Tarjeta_Credito tarjeta) {

        try {

            int idUsuario = ControlDatos.getUsuario().getIdusuario();
            JSONObject nuevo_tarjeta = new JSONObject();
            nuevo_tarjeta.put("usuariou_id",Integer.toString(idUsuario));
            nuevo_tarjeta.put("tc_tipo",tarjeta.getTipotdc());
            nuevo_tarjeta.put("tc_fechavencimiento",tarjeta.getFechaven());
            nuevo_tarjeta.put("tc_saldo",Float.toString(tarjeta.getSaldo()));
            nuevo_tarjeta.put("tc_numero",tarjeta.getNumero());
            Parametros.reset();
            Parametros.setMetodo("Modulo2/agregarTDC?datosTDC="+
                    URLEncoder.encode(nuevo_tarjeta.toString()));
            new Recepcion(actividad,intefaz).execute("GET");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /**
     * Creacion del metodo modificar Tarjeta de Credito
     * conexion con WebService por medio de Json
     *
     * @param tarjeta Tarjeta de Credito a modificar
     */

    public void modificarBanco(Tarjeta_Credito tarjeta) {
        try {

            int idUsuario = ControlDatos.getUsuario().getIdusuario();
            JSONObject nuevo_tarjeta = new JSONObject();
            nuevo_tarjeta.put("usuariou_id",Integer.toString(idUsuario));
            nuevo_tarjeta.put("tc_tipo",tarjeta.getTipotdc());
            nuevo_tarjeta.put("tc_fechavencimiento",tarjeta.getFechaven());
            nuevo_tarjeta.put("tc_saldo",Float.toString(tarjeta.getSaldo()));
            nuevo_tarjeta.put("tc_numero",tarjeta.getNumero());
            Parametros.reset();
            Parametros.setMetodo("Modulo2/actualizarTDC?datosTDC="+
                    URLEncoder.encode(nuevo_tarjeta.toString()));
            new Recepcion(actividad,intefaz).execute("GET");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**Creacion del metodo Mostrar lista de Tarjetas
     * conexion con WebService por medio de Json
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     *
     */
    public void obtenerTodasTarjetas(boolean showStatus) {

        int idUsuario = ControlDatos.getUsuario().getIdusuario();
        Parametros.reset();
        Parametros.setMetodo("Modulo2/consultarTDC?idUsuario="+ String.valueOf(idUsuario) );
        new Recepcion(actividad,intefaz,showStatus).execute("GET");

    }

}
