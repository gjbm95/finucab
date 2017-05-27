package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.ExportarPresupuesto_Controller;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase se encarga de la visualizacion de la suma total del dinero de ganancias y gastos
 **/

public class TotalFragment extends Fragment implements ResponseWebServiceInterface{
    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView gananciaTextView, gastoTextView, totalTextView;
    Button exportarButton;
    MainActivity parentActivity;

    public TotalFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.total_fragment, container, false);
        gananciaTextView = (TextView) rootView.findViewById(R.id.ingreso);
        gastoTextView = (TextView) rootView.findViewById(R.id.gasto);
        totalTextView = (TextView) rootView.findViewById(R.id.total);

        Presupuesto_Controller.gananciaTextView = gananciaTextView;
        Presupuesto_Controller.gastoTextView = gastoTextView;
        Presupuesto_Controller.totalTextView = totalTextView;

        Presupuesto_Controller.asignarTotales();

        ExportarPresupuesto_Controller.interfaz = (ResponseWebServiceInterface) this;
        parentActivity = (MainActivity) getActivity();
        exportarButton = (Button) rootView.findViewById(R.id.exportarButton);
        exportarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExportarPresupuesto_Controller.obtenerPresupuestos(parentActivity);
                Toast.makeText(parentActivity, "Exportando...", Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }

    /**
     * Se encarga de mostrar un mensaje de error si no hay conexión con el web service
     * Ademas se encarga de comenzar la tarea del exportar
     * @param response
     */
    @Override
    public void obtuvoCorrectamente(Object response) {
        if(Parametros.getRespuesta().equals("Error")){
            Presupuesto_Controller.mensajeError(parentActivity,"Error de conexion con servidor!");
        }else{
            ExportarPresupuesto_Controller.utilizarPresupuesto();
            ExportarPresupuesto_Controller task=new ExportarPresupuesto_Controller();
            task.execute();
            parentActivity.changeFragment(new AgregadoFragment(), false);
        }
    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }
}
