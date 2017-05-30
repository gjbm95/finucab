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
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.Planificacion_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.CategoriaSpinner;
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
import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanificacionFragment extends Fragment implements ResponseWebServiceInterface {


    private FloatingActionButton fab;
    private MainActivity parentActivity;
    private RecyclerView recycle;


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
        Planificacion_Controller.init(parentActivity, this);

        recycle = (RecyclerView) rootView.findViewById(R.id.listaPlanificacion);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle.setLayoutManager(myLayoutManager);

        fab = (FloatingActionButton) rootView.findViewById(R.id.addPlanificacionFloatingBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Bundle bundle = new Bundle();
                bundle.putString("curda","anis");
                String val = bundle.getString("curda");*/

                parentActivity.changeFragment(new AgregarPlanificacionFragment(), true);
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

        Planificacion_Controller.listaCategoriasPa();
        //Planificacion_Controller.obtenerListaPlanificacion();

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        if (Parametros.getRespuesta() != null) {
            Log.v("Response-Fra", Parametros.getRespuesta());
            if (Parametros.getRespuesta().equals("Error") || Parametros.getRespuesta().equals("ERROR")) {
                Toast.makeText(parentActivity, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
            Parametros.reset();
        }

        PlanificacionAdapter planificacionAdapter = new PlanificacionAdapter(Planificacion_Controller.planificacion_pago
                .getListaPlanificacion());
        recycle.setAdapter(planificacionAdapter);
    }


    @Override
    public void obtuvoCorrectamente(Object response) {
        Log.i("Caso ", String.valueOf(Planificacion_Controller.managementRequest));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            JSONArray array = null;
            JSONObject object = null;
            String respuesta;


            switch (Planificacion_Controller.managementRequest) {

                case 0:

                    ArrayList listaPlanificacion = new ArrayList<Planificacion>();

                    array = new JSONArray(Parametros.getRespuesta());
                    int count = array.length();

                    for (int i = 0; i < count; i++) {
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

                    Planificacion_Controller.planificacion_pago.setListaPlanificacion(listaPlanificacion);
                    PlanificacionAdapter adapter = new PlanificacionAdapter(listaPlanificacion);
                    recycle.setAdapter(adapter);
                    Planificacion_Controller.managementRequest = -1;
                    break;

                case 1:

                    try {
                        JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());
                        LinkedList category = new LinkedList();


                        for (int i = 0; i < mJsonArray.length(); i++) {
                            String strJson = mJsonArray.getString(i);
                            JSONObject jsonObject = new JSONObject(strJson);

                            category.add(new CategoriaSpinner(jsonObject.getInt("Id"), jsonObject.getString("Nombre")));

                        }
                        Planificacion_Controller.setCategorias(category);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Planificacion_Controller.managementRequest = -1;
                    Planificacion_Controller.obtenerListaPlanificacion();
                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }
}




