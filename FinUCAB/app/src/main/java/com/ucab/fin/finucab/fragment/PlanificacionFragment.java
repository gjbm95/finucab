package com.ucab.fin.finucab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.domain.Planificacion;
import com.ucab.fin.finucab.domain.Planificacion_Pago;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanificacionFragment extends Fragment implements ResponseWebServiceInterface{


    private FloatingActionButton fab;
    private MainActivity parentActivity;
    private RecyclerView recycle;
    private Planificacion_Pago planificacion_pago;


    public PlanificacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_planificacion, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Planificacion de pagos");

        recycle = (RecyclerView) rootView.findViewById(R.id.listaPlanificacion);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(myLayoutManager);

        fab = (FloatingActionButton) rootView.findViewById(R.id.addPlanificacionFloatingBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Bundle bundle = new Bundle();
                bundle.putString("curda","anis");
                String val = bundle.getString("curda");*/

                parentActivity.changeFragment(new PlanificacionFragment(), true);
            }

        });


       /* recycleList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recycleList, new ListaCategorias_Fragment.ClickListener() {
            @Override

            public void onClick(View view, final int position) {

                Intent intent = new Intent(parentActivity, AddCategoryActivity.class);
                intent.putExtra("CATEGORIA_DATA", Categoria_Controller.manejador.getCategorias().get(position));
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                Log.v("longpress",position+"");
                positionLongPress = position;
                registerForContextMenu(recycleList);
            }
        }));*/

        planificacion_pago = new Planificacion_Pago(parentActivity, this);
        planificacion_pago.listaPlanificacion();

        return rootView;
    }


    @Override
    public void onResume(){
        super.onResume();

        if (Parametros.getRespuesta() != null) {
            Log.v("Response-Fra", Parametros.getRespuesta());
            if (Parametros.getRespuesta().equals("Error") || Parametros.getRespuesta().equals("ERROR")){
                Toast.makeText(parentActivity, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
            Parametros.reset();
        }

        PlanificacionAdapter planificacionAdapter = new PlanificacionAdapter(planificacion_pago.getListaPlanificacion());
        recycle.setAdapter(planificacionAdapter);
    }

    private void irTareaJson() {

    }


    @Override
    public void obtuvoCorrectamente(Object response) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray array = null;
        JSONObject object = null;
        String respuesta;

        ArrayList listaPlanificacion = new ArrayList<Planificacion>();
        try{
            array = new JSONArray(Parametros.getRespuesta());
            int count = array.length();

            for (int i=0; i<count; i++){
                respuesta = array.getString(i);
                object = new JSONObject(respuesta);
                Planificacion pa = new Planificacion(object.getInt("Id"),
                        format.parse(object.getString("fechaInicio")),
                        format.parse(object.getString("fechaFin")),
                        object.getString("Nombre"),
                        object.getString("Descripcion"),
                        Double.parseDouble(object.getString("Monto")),
                        object.getInt("Categoria"),
                        object.getBoolean("Recurrente"),
                        object.getString("Recurrencia"),
                        object.getBoolean("Activo"));

                listaPlanificacion.add(pa);
            }

            planificacion_pago.setListaPlanificacion(listaPlanificacion);
            PlanificacionAdapter adapter = new PlanificacionAdapter(listaPlanificacion);
            recycle.setAdapter(adapter);

        } catch (JSONException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }
}




