package com.ucab.fin.finucab.domain;

import android.app.Activity;
import android.util.Log;

import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 *Modulo 5 - Modulo de  Gestion de Pagos
 *Desarrolladores:
 *@author Maria Jose perez / Luis manuel Rojas / Jeffrey Soares
 *Descripción de la clase:
 * Esta clase se encarga de manejar los metodos agregar, modificar y visualizar
 * los cuales se comunicaran con el WebService y llenaran las vistas que se le mostraran al
 * usuario
 */

public class Manejador_Pago {

    private Activity actividad;
    private ArrayList<Pago> pagos; //creacion de un array de tipo pago
    private ResponseWebServiceInterface intefaz; //creacion de una interfaz para funcionalidades de vistas
    public ArrayList<Pago> getPagos() {
        return pagos;
    } // Obtener ultima lista recuperada
    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    } // Asignar ultima lista recuperada


    /*------------------------------------- CONSTRUCTORES ----------------------------------------*/

    public Manejador_Pago(Activity actividad, ResponseWebServiceInterface intefaz){

        this.actividad = actividad;
        this.intefaz = intefaz;
    }

    public Manejador_Pago(Activity actividad){

        this.actividad = actividad;
        this.intefaz = null;
    }

    /*------------------------------------- GETTER Y SETTER ----------------------------------------*/

    public Activity getActividad() {
        return actividad;
    }

    public ResponseWebServiceInterface getIntefaz() {
        return intefaz;
    }

    /*------------------------------------- REQUEST ----------------------------------------*/

    /**Creacion del metodo agregar Pago
     * conexion con WebService por medio de Json
     *
     * @param pago Pago a registrar
     */
    public void agregarPago( Pago pago) {

        try {
            JSONObject nuevo_pago = new JSONObject();
            nuevo_pago.put("pg_monto",pago.getTotal());
            nuevo_pago.put("pg_tipoTransaccion",pago.getTipo());
            nuevo_pago.put("pg_categoria",pago.getIdCategoria());
            nuevo_pago.put("pg_nombre_categoria",pago.getCategoria());
            nuevo_pago.put("pg_descripcion",pago.getDescripcion());
            Parametros.reset();
            Parametros.setMetodo("Modulo5/registrarPago?datosPago="+URLEncoder.encode(nuevo_pago.toString()));
            new Recepcion(actividad,intefaz).execute("GET");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    /**
     * Creacion del metodo modificar Pago
     * conexion con WebService por medio de Json
     *
     * @param pago Pago a modificar
     */

    public void modificarPago( Pago pago) {
        try {
            JSONObject nuevo_pago = new JSONObject();
            nuevo_pago.put("pg_id",pago.getIdPago());
            nuevo_pago.put("pg_monto",pago.getTotal());
            nuevo_pago.put("pg_tipoTransaccion",pago.getTipo());
            nuevo_pago.put("pg_categoria",pago.getIdCategoria());
            nuevo_pago.put("pg_nombre_categoria",pago.getCategoria());
            nuevo_pago.put("pg_descripcion",pago.getDescripcion());
            Parametros.reset();
            Parametros.setMetodo("Modulo5/modificarPago?datosPago="+URLEncoder.encode(nuevo_pago.toString()));
            new Recepcion(actividad,intefaz).execute("GET");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**Creacion del metodo Mostrar lista de Pago
     * conexion con WebService por medio de Json
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     *
     */
    public void obtenerTodosPagos(boolean showStatus) {

        int idUsuario = ControlDatos.getUsuario().getIdusuario();
        Parametros.reset();
        Parametros.setMetodo("Modulo5/visualizarPago?datosPago="+String.valueOf(idUsuario) );
        new Recepcion(actividad,intefaz,showStatus).execute("GET");
    }



    /**Creacion del metodo consultar Pago
     *la cual obtendra un pago dado un id
     * @param id Id dela pago a obtener
     * @return la.get(i)
     */
    public Pago obtenerPago( int id) {
        ArrayList<Pago> la = getPagos();
        for(int i=0 ; i< la.size(); i++) {
            if( la.get(i).getIdPago() == id ){
                return  la.get(i);
            }

        }

        return  null;
    }

}
