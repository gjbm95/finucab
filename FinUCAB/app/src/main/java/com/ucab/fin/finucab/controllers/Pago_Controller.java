package com.ucab.fin.finucab.controllers;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import com.ucab.fin.finucab.domain.Pago;
import android.app.Activity;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import java.net.URLEncoder;
import java.sql.SQLOutput;

/**
 * Created by jjsa_ on 18/5/2017.
 */

public class Pago_Controller {

    public static Pago pago;
    public static EditText descripcionPago;
    public static EditText montoPago;
    public static Spinner categoriaPago;
    public static Spinner tipoTransaccion;

    public static void asignarValores( ){

        descripcionPago.setText(pago.getDescripcion());
        montoPago.setText(Float.toString(pago.getTotal()));

    }
    public static int validacionPagoVacio() {
        int x = 1;
        //TEXTVIEW

        if (descripcionPago.getText().toString().isEmpty()) {
            descripcionPago.setError("Debe colocar una Descripcion");
            x=0;
        }
        if (montoPago.getText().toString().isEmpty()) {
            montoPago.setError("Debe colocar un Monto");
            x=0;
        }

        //SPINNER
        if (categoriaPago.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) categoriaPago.getSelectedView();
            errorText.setError("Debe colocar una Categoria");
            x=0;
        }

        //SPINNER
        if (tipoTransaccion.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) tipoTransaccion.getSelectedView();
            errorText.setError("Debe colocar un Tipo de Transaccion");
            x=0;
        }

        return x;
    }

    public static String registrarPago(Pago pago, Activity actividad){
        JSONObject nuevo_pago = new JSONObject();
        try {
            nuevo_pago.put("pg_monto",pago.getTotal());
            nuevo_pago.put("pg_tipoTransaccion",pago.getTipo());
            nuevo_pago.put("pg_categoria",pago.getCategoria());
            nuevo_pago.put("pg_descripcion",pago.getDescripcion());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setServer("http://192.168.3.111");
        Parametros.setPuerto("8080");
        Parametros.setMetodo("Modulo5/registrarPago?datosPago="+URLEncoder.encode(nuevo_pago.toString()));
        new Recepcion(actividad).execute("GET");
        return Parametros.getRespuesta();
    }

}
