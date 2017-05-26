package com.ucab.fin.finucab.exceptions;

import android.widget.EditText;

/**
 * Created by OSVALDO on 24/05/2017.
 */

public class NombrePresupuesto_Exception extends Exception {

    EditText campo;
    public NombrePresupuesto_Exception(String msg) {super(msg);}

    public void setCampo(EditText campo){
        this.campo = campo;
    }

    public EditText getCampo(){
        return campo;
    }


}
