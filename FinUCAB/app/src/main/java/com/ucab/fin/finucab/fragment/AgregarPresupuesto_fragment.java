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
    TextView textMeses;
    EditText meses;
    RadioButton unico, recurrente;
    Spinner spinner;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_agregar_presupuesto, container, false);

        textMeses = (TextView) rootView.findViewById(R.id.Textrecurrencia);
        meses = (EditText) rootView.findViewById(R.id.meses);
        unico = (RadioButton) rootView.findViewById(R.id.unico);
        unico.setOnCheckedChangeListener(this);
        recurrente = (RadioButton) rootView.findViewById(R.id.recurrente);
        recurrente.setOnCheckedChangeListener(this);

        textMeses.setVisibility(textMeses.INVISIBLE);   //SE COLOCA INVISIBLE EL TEXTVIEW
        meses.setVisibility(textMeses.INVISIBLE);  //SE COLOCA INVISIBLE EL EDITTEXT

        spinner = (Spinner)rootView.findViewById(R.id.categoria);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categoria_arrays, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.recurrente) {        //VERIFICO SI EL BOTON QUE ESTA PRESIONADO ES RRECURRENTE
                textMeses.setVisibility(textMeses.VISIBLE);     //SE COLOCA VISIBLE EL TEXTVIEW
                meses.setVisibility(meses.VISIBLE);             //SE COLOCA VISIBLE EL EDIT TEXT
            }
            if (buttonView.getId() == R.id.unico) {             //VERIFICO SI EL BOTON QUE ESTA PRESIONADO ES UNICO
                textMeses.setVisibility(textMeses.INVISIBLE);   //SE COLOCA INVISIBLE EL TEXTVIEW
                meses.setVisibility(meses.INVISIBLE);           //SE COLOCA INVISIBLE EL EDITTEXT
            }
        }

    }



}
