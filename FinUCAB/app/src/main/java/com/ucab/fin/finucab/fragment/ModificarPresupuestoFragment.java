package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.exceptions.NombrePresupuesto_Exception;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase se encarga del manejo de los datos entrantes para la funcion modificar presupuesto
 **/

public class ModificarPresupuestoFragment extends Fragment implements CompoundButton.OnCheckedChangeListener,ResponseWebServiceInterface {


    TextView recurrentTextView;
    EditText monthsEditText, nameEditText,amountEditText;
    RadioButton onlyRadioButton, recurrentRadioButton;
    Spinner categorySpinner;
    MainActivity parentActivity;
    Button agregarButton;
    Integer caso = 0;

    public ModificarPresupuestoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.agregar_presupuesto_fragment, container, false);

        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Modificar Presupuesto");

        recurrentTextView = (TextView) rootView.findViewById(R.id.recurrentTextView);
        monthsEditText = (EditText) rootView.findViewById(R.id.monthsEditText);
        nameEditText = (EditText) rootView.findViewById(R.id.budgetNameEditText);
        amountEditText = (EditText) rootView.findViewById(R.id.amountEditText);
        categorySpinner= (Spinner) rootView.findViewById(R.id.categorySpinner) ;
        onlyRadioButton= (RadioButton) rootView.findViewById(R.id.onlyRadioButton);
        recurrentRadioButton= (RadioButton) rootView.findViewById(R.id.recurrentRadioButton);


        Presupuesto_Controller.nombrePresupuesto = nameEditText;
        Presupuesto_Controller.montoPresupuesto = amountEditText;
        Presupuesto_Controller.recurrenciaPresupuesto = monthsEditText;
        Presupuesto_Controller.categoriaPresupuesto = categorySpinner;
        Presupuesto_Controller.recurrenciaButton = recurrentRadioButton;
        Presupuesto_Controller.unicoButton = onlyRadioButton;
        Presupuesto_Controller.recurrenciaTextView = recurrentTextView;


        Presupuesto_Controller.interfaz = (ResponseWebServiceInterface) this;
        Presupuesto_Controller.volverInvisibleRecurrencia();
        Presupuesto_Controller.obtenerPresupuesto(parentActivity);



        agregarButton = (Button) rootView.findViewById(R.id.acceptButton);
        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Presupuesto_Controller.validacionVacio(parentActivity)==0){
                    caso=2;
                }
            }
        });

        onlyRadioButton.setOnCheckedChangeListener(this);
        recurrentRadioButton.setOnCheckedChangeListener(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.categoryArray, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        return rootView;

    }


    /**
     * Se encarga de colocar visible o invisible los TextView y los EditText de la recurrencia
     * verificando si un presupuesto es unico o recurrente
     * @param buttonView
     * @param isChecked
     */

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.recurrentRadioButton) {
                recurrentTextView.setVisibility(recurrentTextView.VISIBLE);
                monthsEditText.setVisibility(monthsEditText.VISIBLE);
            }
            if (buttonView.getId() == R.id.onlyRadioButton) {
                recurrentTextView.setVisibility(recurrentTextView.INVISIBLE);
                monthsEditText.setVisibility(monthsEditText.INVISIBLE);
            }
        }

    }


    @Override
    public void onResume(){
        super.onResume();

    }

    /**
     * Se encarga de mostrar un mensaje de error si no hay conexión con el web service
     * Además se encarga de llenar el fragment de modificar,
     * Verificar si el nombre esta repetido y
     * Hacer la petición al web service para modificar
     * @param response
     */
    @Override
    public void obtuvoCorrectamente(Object response) {

            if(caso == 0){
                if(Parametros.getRespuesta().equals("Error")){
                    Presupuesto_Controller.mensajeError(parentActivity,"Error de conexion con servidor!");
                }else{
                    Presupuesto_Controller.presupuesto = new Presupuesto();
                    try {
                        JSONObject json = new JSONObject(Parametros.respuesta);
                        Presupuesto_Controller.presupuesto.set_categoria((String) json.get("IdCategoria"));
                        Presupuesto_Controller.presupuesto.set_nombre((String) json.get("Nombre"));
                        Presupuesto_Controller.presupuesto.set_monto(Float.parseFloat((String) json.get("Monto")));
                        Presupuesto_Controller.presupuesto.set_clasificacion((String) json.get("Clasificacion"));
                        Presupuesto_Controller.presupuesto.set_duracion(Integer.parseInt((String) json.get("Duracion")));
                        Presupuesto_Controller.presupuesto.set_tipo(((String) json.get("Tipo")));
                        caso =1;
                        Presupuesto_Controller.asignarValores();
                        Presupuesto_Controller.obtenerSpinner(parentActivity);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }else if(caso == 1){

                Presupuesto_Controller.asignarSpinner(parentActivity);

            }else if(caso == 2){
                try {
                    Presupuesto_Controller.DevolverValidacion(nameEditText,false);
                    caso = 3;
                    Presupuesto_Controller.modificarPresupuesto(parentActivity);
                    //
                } catch (NombrePresupuesto_Exception e) {
                    e.getCampo().setError(e.getMessage());;
                }
            }else if (caso == 3){
                if(Parametros.getRespuesta().equals("Error")){
                    Presupuesto_Controller.mensajeError(parentActivity,"Error de conexion con servidor!");
                }else{
                    parentActivity.changeFragment(new AgregadoFragment(), false);
                }
            }





    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }
}
