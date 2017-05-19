package com.ucab.fin.finucab.controllers;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ucab.fin.finucab.domain.Pago;

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

}
