package com.ucab.fin.finucab.controllers;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Manejador_Pago;
import com.ucab.fin.finucab.domain.Pago;
import android.app.Activity;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 *Modulo 5 - Modulo de  Gestion de Pagos
 *Desarrolladores:
 *@author Maria Jose Perez / Luis Manuel Rojas / Jeffrey Soares
 *Descripción de la clase:
 * Esta clase se encarga de gestionar y llevar a cabo las llamadas a los distintos metodos
 * . Y de inicializar
 * parametros de los botones para la aplicacion.
 */

public class Pago_Controller {

    public static Pago pago;//Creacion de una variable pago de tipo Pago
    public static EditText descripcionPago;
    public static EditText montoPago;
    public static Spinner categoriaPago;
    public static Spinner tipoTransaccion;
    private ArrayList<Pago> pagos;
    private static Manejador_Pago manejador;
    private static int  casoRequest = -1;

    /**
     * Inicializar de ser necesario el manejador de data
     * @param actividad requerida para devolver la data (deprecated)
     * @param interfaz requerida para devolver la data
     */

    public static void initManejador(Activity actividad, ResponseWebServiceInterface interfaz){

        if ( manejador == null ||  manejador.getIntefaz() != interfaz ) {

            manejador = new Manejador_Pago(actividad, interfaz);

        }

    }

    /**
     * Colocar actul lista de categoria en el manejador
     */
    public static void setListaPagos(ArrayList<Pago> pagos){

        manejador.setPagos(pagos);
    }

    public static void asignarValores() {

        descripcionPago.setText(pago.getDescripcion());
        montoPago.setText(Float.toString(pago.getTotal()));

    }

    public static int validacionPagoVacio() {
        int x = 1;
        //TEXTVIEW

        if (descripcionPago.getText().toString().isEmpty()) {
            descripcionPago.setError("Debe colocar una Descripcion");
            x = 0;
        }
        if (montoPago.getText().toString().isEmpty()) {
            montoPago.setError("Debe colocar un Monto");
            x = 0;
        }

        //SPINNER
        if (categoriaPago.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) categoriaPago.getSelectedView();
            errorText.setError("Debe colocar una Categoria");
            x = 0;
        }

        //SPINNER
        if (tipoTransaccion.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) tipoTransaccion.getSelectedView();
            errorText.setError("Debe colocar un Tipo de Transaccion");
            x = 0;
        }

        return x;
    }

    /**
     *  Metodo encargado de llamar a agregar pago
     * @param pago Pago a registrar
     */
    public static void registrarPago(Pago pago){
        System.out.println(pago.getDescripcion());
        manejador.agregarPago(pago);

    }

    public static void obtenerTodosPagos(boolean showStatus){

        casoRequest = 0;
        manejador.obtenerTodosPagos(showStatus);

    }
    /**
     * Colocar actul lista de pagos en el manejador
     * @return Lista de pago cargada
     */
    public static ArrayList<Pago> getListaPagos(){

        return manejador.getPagos();
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
}