package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarPresupuesto_fragment extends Fragment implements CompoundButton.OnCheckedChangeListener{
    TextView recurrentTextView;
    EditText monthsEditText, nameEditText,amountEditText;
    RadioButton onlyRadioButton, recurrentRadioButton;
    Spinner categorySpinner;
    MainActivity parentActivity;
    Button agregarButton;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.agregar_presupuesto_fragment, container, false);

        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Agregar Presupuesto");

        recurrentTextView = (TextView) rootView.findViewById(R.id.recurrentTextView);
        monthsEditText = (EditText) rootView.findViewById(R.id.monthsEditText);
        nameEditText = (EditText) rootView.findViewById(R.id.budgetNameEditText);
        amountEditText = (EditText) rootView.findViewById(R.id.amountEditText);
        categorySpinner= (Spinner) rootView.findViewById(R.id.categorySpinner) ;
        onlyRadioButton= (RadioButton) rootView.findViewById(R.id.onlyRadioButton);
        recurrentRadioButton= (RadioButton) rootView.findViewById(R.id.recurrentRadioButton);
        agregarButton = (Button) rootView.findViewById(R.id.acceptButton);

        Presupuesto_Controller.nombrePresupuesto = nameEditText;
        Presupuesto_Controller.montoPresupuesto = amountEditText;
        Presupuesto_Controller.recurrenciaPresupuesto=monthsEditText;
        Presupuesto_Controller.categoriaPresupuesto=categorySpinner;
        Presupuesto_Controller.recurrenciaButton=recurrentRadioButton;
        Presupuesto_Controller.unicoButton=onlyRadioButton;
        Presupuesto_Controller.recurrenciaTextView = recurrentTextView;

        Presupuesto_Controller.volverInvisibleRecurrencia();

        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presupuesto_Controller.validacionPresupuestoVacio();
            }
        });

        onlyRadioButton.setOnCheckedChangeListener(this);
        recurrentRadioButton.setOnCheckedChangeListener(this);

        //Se borrrara cuando se implemente correctamente
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categoryArray, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.recurrentRadioButton) {                //VERIFICO SI EL BOTON QUE ESTA PRESIONADO ES RRECURRENTE
                recurrentTextView.setVisibility(recurrentTextView.VISIBLE);       //SE COLOCA VISIBLE EL TEXTVIEW
                monthsEditText.setVisibility(monthsEditText.VISIBLE);             //SE COLOCA VISIBLE EL EDIT TEXT
            }
            if (buttonView.getId() == R.id.onlyRadioButton) {                     //VERIFICO SI EL BOTON QUE ESTA PRESIONADO ES UNICO
                recurrentTextView.setVisibility(recurrentTextView.INVISIBLE);     //SE COLOCA INVISIBLE EL TEXTVIEW
                monthsEditText.setVisibility(monthsEditText.INVISIBLE);           //SE COLOCA INVISIBLE EL EDITTEXT
            }
        }

    }



}
