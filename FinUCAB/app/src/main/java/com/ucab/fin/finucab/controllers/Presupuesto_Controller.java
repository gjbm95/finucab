package com.ucab.fin.finucab.controllers;

import android.widget.EditText;

import com.ucab.fin.finucab.domain.Presupuesto;

/**
 * Created by Oswaldo on 06/05/2017.
 */

public class Presupuesto_Controller {
    public static Presupuesto presupuesto;

    public static void asignarValores(EditText monthsEditText, EditText nameEditText, EditText amountEditText ){

        monthsEditText.setText( presupuesto.get_duracion().toString());
        nameEditText.setText(presupuesto.get_nombre());
        amountEditText.setText(presupuesto.get_monto().toString());

    }

}
