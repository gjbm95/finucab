package com.ucab.fin.finucab.exceptions;

import android.widget.Spinner;

/**
 * Created by Junior on 15/06/2017.
 */

public class ErrorSpinner_Exception extends Exception {

    Spinner seleccion;

    public ErrorSpinner_Exception(String msg) {
        super(msg);
    }

    public Spinner getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Spinner seleccion) {
        this.seleccion = seleccion;
    }
}
