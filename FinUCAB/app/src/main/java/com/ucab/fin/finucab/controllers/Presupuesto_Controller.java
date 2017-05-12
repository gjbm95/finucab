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
    public static boolean isChecked;




    public static void asignarValores( ){

        recurrenciaPresupuesto.setText( presupuesto.get_duracion().toString());
        nombrePresupuesto.setText(presupuesto.get_nombre());
        montoPresupuesto.setText(presupuesto.get_monto().toString());

    }

    public static int validacionPresupuestoVacio() {

        if (nombrePresupuesto.getText().toString().isEmpty()) {
            nombrePresupuesto.setError("Debe colocar un Nombre de Presupuesto");

            return 1;
        }
        if (montoPresupuesto.getText().toString().isEmpty()) {
            montoPresupuesto.setError("Debe colocar un Monto");
            return 1;

        }
        if (recurrenciaButton.getId() == R.id.recurrentRadioButton) {                //VERIFICO SI EL BOTON QUE ESTA PRESIONADO ES RRECURRENTE
            if (recurrenciaPresupuesto.getText().toString().isEmpty()) {
                recurrenciaPresupuesto.setError("Debe colocar un Mes");
                return 1;
            }
        }
           /* if ((recurrenciaButton.getId() == R.id.recurrentRadioButton) && (unicoButton.getId() == R.id.onlyRadioButton)) {                //VERIFICO SI EL BOTON QUE ESTA PRESIONADO ES RRECURRENTE
                recurrenciaButton.setError("Debe seleccionar un tipo de presupuesto");
                unicoButton.setError("Debe seleccionar un tipo de presupuesto");
                return 1;
            }*/

        if (categoriaPresupuesto.getSelectedItemPosition()==0) {

           /* View selectedView = categoriaPresupuesto.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                selectedTextView.setError("Debe seleccionar una categoria");
            }*/
            nombrePresupuesto.setText("aquiles gay");

        }
        if (categoriaPresupuesto.getSelectedItemPosition()==-1) {

           /* View selectedView = categoriaPresupuesto.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                selectedTextView.setError("Debe seleccionar una categoria");
            }*/
            nombrePresupuesto.setText("aquiles maricon");

        }


        return 0;
    }



}
