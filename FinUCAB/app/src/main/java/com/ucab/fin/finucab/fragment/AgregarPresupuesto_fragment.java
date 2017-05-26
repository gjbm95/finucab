package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.exceptions.NombrePresupuesto_Exception;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarPresupuesto_fragment extends Fragment implements CompoundButton.OnCheckedChangeListener,ResponseWebServiceInterface{
    TextView recurrentTextView;
    EditText monthsEditText, nameEditText,amountEditText;
    RadioButton onlyRadioButton, recurrentRadioButton;
    Spinner categorySpinner;
    MainActivity parentActivity;
    Button agregarButton;
    Integer caso = 0;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.agregar_presupuesto_fragment, container, false);

        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Agregar Presupuesto");

        Presupuesto_Controller.interfaz = (ResponseWebServiceInterface) this;

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
                if(Presupuesto_Controller.validacionVacio(parentActivity)==0){
                    caso=1;


                }
            }
        });

        onlyRadioButton.setOnCheckedChangeListener(this);
        recurrentRadioButton.setOnCheckedChangeListener(this);

        Presupuesto_Controller.asignarSpinner(parentActivity);

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        if (Parametros.getRespuesta() != null) {
            Log.v("Response-Fra",Parametros.getRespuesta());
            if (Parametros.getRespuesta().equals("Error")||Parametros.getRespuesta().equals("ERROR") ) {
                Toast.makeText(parentActivity, "Ups, ha ocurrido un error", Toast.LENGTH_SHORT).show();

            }

            //Parametros.reset();
        }

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


    @Override
    public void obtuvoCorrectamente(Object response) {
        if(caso==0){
            JSONObject jObject = null;
            System.out.println("Antes del try");
            if(Parametros.getRespuesta().equals("Error")){
                Presupuesto_Controller.mensajeError(parentActivity,"Error de conexion con servidor!");
            }else{
                try {
                    System.out.println("Despues del try");
                    JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
                    int count = mJsonArray.length();
                    String[] valores = new String[count];
                    for (int i = 0; i < count; i++) {   // iterate through jsonArray

                        jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                        String categoria = ((String) jObject.get("Nombre"));
                        System.out.println("La categoria es: " + categoria);
                        valores[i] = categoria;
                    }
                    ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                            android.R.layout.simple_spinner_dropdown_item, valores);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categorySpinner.setAdapter(adapter);
                    Parametros.reset();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else if (caso==1){
            try {
                Presupuesto_Controller.DevolverValidacion(nameEditText,true);
                caso = 2;
                Presupuesto_Controller.registrarPresupuesto(parentActivity);

            } catch (NombrePresupuesto_Exception e) {
                e.getCampo().setError(e.getMessage());;
            }
        }else if (caso == 2) {
            parentActivity.changeFragment(new AgregadoFragment(), false);
        }



    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }
}