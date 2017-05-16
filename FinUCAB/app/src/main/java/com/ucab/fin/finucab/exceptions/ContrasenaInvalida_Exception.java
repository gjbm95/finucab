package com.ucab.fin.finucab.exceptions;

import android.widget.EditText;

/**
 * Created by Junior on 09/05/2017.
 */

public class ContrasenaInvalida_Exception extends Exception {


    EditText campo;

    public ContrasenaInvalida_Exception(String msg) {
        super(msg);
    }

    public void setCampo(EditText campo){
        this.campo = campo;
    }

    public EditText getCampo(){
        return campo;
    }



}
