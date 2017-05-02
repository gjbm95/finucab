package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ucab.fin.finucab.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarPresupuesto_fragment extends Fragment implements CompoundButton.OnCheckedChangeListener{
    TextView recurrentTextView;
    EditText monthsEditText;
    RadioButton onlyRadioButton, recurrentRadioButton;
    Spinner categorySpinner;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_agregar_presupuesto, container, false);

        recurrentTextView = (TextView) rootView.findViewById(R.id.Textrecurrencia);
        monthsEditText = (EditText) rootView.findViewById(R.id.meses);
        onlyRadioButton = (RadioButton) rootView.findViewById(R.id.unico);
        onlyRadioButton.setOnCheckedChangeListener(this);
        recurrentRadioButton = (RadioButton) rootView.findViewById(R.id.recurrente);
        recurrentRadioButton.setOnCheckedChangeListener(this);

        recurrentTextView.setVisibility(recurrentTextView.INVISIBLE);   //SE COLOCA INVISIBLE EL TEXTVIEW
        monthsEditText.setVisibility(monthsEditText.INVISIBLE);         //SE COLOCA INVISIBLE EL EDITTEXT

        categorySpinner = (Spinner)rootView.findViewById(R.id.categoria);
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
