package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
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
    private int positionLongPress = -1;


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
        registerForContextMenu(recycle);

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


        recycle.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recycle, new PlanificacionFragment.ClickListener() {

            @Override
            public void onClick(View view, final int position) {

                /*Intent intent = new Intent(parentActivity, AddCategoryActivity.class);
                intent.putExtra("CATEGORIA_DATA", Categoria_Controller.manejador.getCategorias().get(position));
                startActivity(intent);*/

            }

            @Override
            public void onLongClick(View view, int position) {
                positionLongPress = position;
                //registerForContextMenu(recycle);
            }
        }));

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

        PlanificacionAdapter planificacionAdapter = new PlanificacionAdapter(Planificacion_Controller
                .planificacion_pago
                .getListaPlanificacion());
        recycle.setAdapter(planificacionAdapter);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        Planificacion pa;

        switch (item.getItemId()) {

            case R.id.eliminarItem:

                pa = Planificacion_Controller.planificacion_pago.getListaPlanificacion().get(positionLongPress);
                Toast.makeText(getActivity(), "Eliminando planificacion ...", Toast.LENGTH_LONG).show();
                Planificacion_Controller.eliminarPlanificacion(pa.getId());
                positionLongPress = -1;

                return true;

            case R.id.modificarItem:

                pa = Planificacion_Controller.planificacion_pago.getListaPlanificacion().get
                        (positionLongPress);
                positionLongPress = -1;
                modificarPlanificacion(pa.getId());

                return true;

            default:
                return super.onContextItemSelected(item);

        }

    }

    private void modificarPlanificacion(int id) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("modificar",true);
        bundle.putInt("planificacionId", id);
        AgregarPlanificacionFragment fragInfo = new AgregarPlanificacionFragment();
        fragInfo.setArguments(bundle);
        parentActivity.changeFragment(fragInfo,false);
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

                case 4:

                    String respuesta1 = (String) response;
                    Planificacion_Controller.managementRequest = -1;
                    showDialogResponse(respuesta1);
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

    private void showDialogResponse(String respuesta) {

        AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
        builder.setMessage(respuesta).setTitle("AVISO").
                setPositiveButton("Aceptar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        parentActivity.changeFragment(new PlanificacionFragment(), false);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static interface ClickListener {

        public void onClick(View view, int position);

        public void onLongClick(View view, int position);

    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private PlanificacionFragment.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final PlanificacionFragment.ClickListener
                clicklistener) {

            this.clicklistener = clicklistener;

            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {

                    return true;

                }

                @Override
                public void onLongPress(MotionEvent e) {

                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());

                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));

                    }

                }

            });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());

            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {

                clicklistener.onClick(child, rv.getChildAdapterPosition(child));

            }

            return false;

        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

}




