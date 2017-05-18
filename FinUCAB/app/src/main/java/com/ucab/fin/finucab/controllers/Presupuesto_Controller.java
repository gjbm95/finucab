package com.ucab.fin.finucab.controllers;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.fragment.AgregarPresupuesto_fragment;

import static java.security.AccessController.getContext;

/**
 * Created by Oswaldo on 06/05/2017.
 */

public class Presupuesto_Controller {

    public static Presupuesto presupuesto;

    public static EditText nombrePresupuesto;
    public static EditText montoPresupuesto;
    public static EditText recurrenciaPresupuesto;
    public static CompoundButton recurrenciaButton, unicoButton;
    public static Spinner categoriaPresupuesto;
    public static TextView recurrenciaTextView;




    public static void asignarValores( ){

        nombrePresupuesto.setText(presupuesto.get_nombre());
        montoPresupuesto.setText(presupuesto.get_monto().toString());
        if(presupuesto.get_tipo().equals("Unico")){

            unicoButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.INVISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.INVISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
        }else if(presupuesto.get_tipo().equals("Recurrente")){
            recurrenciaButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.VISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.VISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
            recurrenciaPresupuesto.setText( presupuesto.get_duracion().toString());
        }

    }

    public static void volverInvisibleRecurrencia(){
        recurrenciaTextView.setVisibility(recurrenciaTextView.INVISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
        recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.INVISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
    }

    public static int validacionPresupuestoVacio() {

        //TEXTVIEW

        if (nombrePresupuesto.getText().toString().isEmpty()) {
            nombrePresupuesto.setError("Debe colocar un Nombre de Presupuesto");
        }
        if (montoPresupuesto.getText().toString().isEmpty()) {
            montoPresupuesto.setError("Debe colocar un Monto");
        }

        //SPINNER
        if (categoriaPresupuesto.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) categoriaPresupuesto.getSelectedView();
            errorText.setError("Debe colocar una categoria");
        }

        if (recurrenciaButton.isChecked()) {
            if (recurrenciaPresupuesto.getText().toString().isEmpty()) {
                recurrenciaPresupuesto.setError("Debe colocar un numero de meses");
            }
        }

        return 0;
    }



}
