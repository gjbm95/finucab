package com.ucab.fin.finucab.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.Pago_Controller;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;
import com.ucab.fin.finucab.domain.CategoriaSpinner;
import com.ucab.fin.finucab.domain.Pago;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;


public class ModificarPago_Fragment extends Fragment implements ResponseWebServiceInterface {

    MainActivity parentActivity;
    Button agregarButton;
    EditText descripcionEditText, montoEditText;
    Spinner categoriaSpinner,tipoSpinner;
    int Resp;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_modificar_pago, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Modificar Pago");

        descripcionEditText = (EditText) rootView.findViewById(R.id.descripccionEditText);
        montoEditText = (EditText) rootView.findViewById(R.id.montoEditText);
        categoriaSpinner= (Spinner) rootView.findViewById(R.id.categoriaSpinner) ;
        tipoSpinner= (Spinner) rootView.findViewById(R.id.tipoTransaccionSpinner) ;

        agregarButton = (Button) rootView.findViewById(R.id.acceptButton);


        Pago_Controller.categoriaPago = categoriaSpinner;
        Pago_Controller.montoPago = montoEditText;
        Pago_Controller.tipoTransaccion = tipoSpinner;
        Pago_Controller.descripcionPago = descripcionEditText;
        Pago_Controller.asignarValores();
        agregarButton = (Button) rootView.findViewById(R.id.acceptButtonMPago);
        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resp = Pago_Controller.validacionPagoVacio();
                if (Resp == 1) {
                    Pago pago = new Pago();
                    CategoriaSpinner sp = (CategoriaSpinner) categoriaSpinner.getSelectedItem();
                    String nombreCategoria = sp.toString();
                    int idCategoria = sp.getId();
                    pago.setIdCategoria(idCategoria);
                    pago.setCategoria(nombreCategoria);
                    pago.setDescripcion(Pago_Controller.descripcionPago.getText().toString());
                    pago.setTotal(Float.valueOf(Pago_Controller.montoPago.getText().toString()));

                    String tipo = "ingreso";
                    if (Pago_Controller.tipoTransaccion.getSelectedItemPosition() == 1){
                        tipo = "egreso";
                    }
                    pago.setTipo(tipo);

                    Pago_Controller.modificarPago(pago);
                    //parentActivity.changeFragment(new PaymentFragment(), false);
                }
            }
        });


        Pago_Controller.initManejador(parentActivity,this);
            Pago_Controller.listaCategoriasPa();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        Pago_Controller.initManejador(parentActivity,this);
    }

    /**
     * Response WebService
     * se llena la lista con las consultas provenientes del WebService con la BD
     * @param response Respuesta del WebService
     */
    @Override
    public void obtuvoCorrectamente(Object response){

        //Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();
        try {
            if(Pago_Controller.getCasoRequest() == 2 ){
                Log.e("Categoria",Parametros.getRespuesta());
                    JSONObject jsonObject = new JSONObject(Parametros.getRespuesta());
                    parentActivity.onBackPressed();

            }else if(Pago_Controller.getCasoRequest() == 5 ) {

                    JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());
                    LinkedList category = new LinkedList();

                    for (int i = 0; i < mJsonArray.length(); i++) {
                        String strJson = mJsonArray.getString(i);
                        JSONObject jsonObject = new JSONObject(strJson);

                        category.add(new CategoriaSpinner(jsonObject.getInt("ca_id"), jsonObject.getString("ca_nombre")));

                    }
                    ArrayAdapter spinner_adapter = new ArrayAdapter(parentActivity, android.R.layout
                            .simple_spinner_item, category);
                    spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categoriaSpinner.setAdapter(spinner_adapter);

            }

        } catch (JSONException e) {
            Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();
        }
            Pago_Controller.resetCasoRequest();


    }
    /**
     * Response WebService
     * se llena la lista con las consultas provenientes del WebService con la BD
     * @param response Error del WebService
     */
    @Override
    public void noObtuvoCorrectamente(Object response){

    }

}

