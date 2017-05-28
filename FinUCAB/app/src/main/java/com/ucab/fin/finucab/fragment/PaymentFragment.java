package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import com.ucab.fin.finucab.controllers.Pago_Controller;
import com.ucab.fin.finucab.domain.Pago;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PaymentFragment extends Fragment implements ResponseWebServiceInterface {


    FloatingActionButton fab;
    MainActivity parentActivity;
    RecyclerView recycleList;
    private boolean isInOnCreate;

    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.payment_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Pagos");

        Pago_Controller.initManejador(parentActivity,this);

        // Configuracion inicial del boton flotante
        fab = (FloatingActionButton) rootView.findViewById(R.id.addFloatingBtnPago);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                parentActivity.changeFragment(new AgregarPago_Fragment(), false);
            }
        });


        final RecyclerView recycleList = (RecyclerView) rootView.findViewById(R.id.pagosReList);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleList.setLayoutManager(myLayoutManager);
        recycleList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recycleList, new PaymentFragment.ClickListener() {
            @Override

            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                //Toast.makeText(getActivity(), "Single Click on position :"+position,
                //        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                registerForContextMenu(recycleList);
            }
        }));
        Pago_Controller.obtenerTodosPagos(true);
        return rootView;


    }


    @Override

    public void onCreateContextMenu(ContextMenu menu, View v,

                                    ContextMenu.ContextMenuInfo menuInfo)

    {

        super.onCreateContextMenu(menu, v, menuInfo);



        MenuInflater inflater = getActivity().getMenuInflater();

        inflater.inflate(R.menu.pago_menu, menu);
    }
    @Override

    public boolean onContextItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.modifyPagoOption:

                Pago p = new Pago();
                p.setCategoria("Universidad");
                p.setDescripcion("Semestre2");
                p.setTotal((float) 222.222);
                p.setTipo("Egreso");
                Pago_Controller.pago = p;
                parentActivity.changeFragment(new ModificarPago_Fragment(), false);

                return true;

            case R.id.exportPagoOpcion:

                Toast.makeText(getActivity(), "Opcion Exportar seleccionada",Toast.LENGTH_LONG).show();

                return true;

            default:

                return super.onContextItemSelected(item);

        }
    }
    @Override
    public void onResume() {
        super.onResume();

        if (!isInOnCreate) {

            Pago_Controller.initManejador(parentActivity,this);
            Pago_Controller.obtenerTodosPagos(false);
        }

        isInOnCreate = false;
    }





    public static interface ClickListener{

        public void onClick(View view,int position);

        public void onLongClick(View view,int position);

    }



    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private PaymentFragment.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final PaymentFragment.ClickListener clicklistener){

            this.clicklistener=clicklistener;

            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

                @Override

                public boolean onSingleTapUp(MotionEvent e) {

                    return true;

                }



                @Override

                public void onLongPress(MotionEvent e) {

                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());

                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));

                    }

                }

            });

        }



        @Override

        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child=rv.findChildViewUnder(e.getX(),e.getY());

            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){

                clicklistener.onClick(child,rv.getChildAdapterPosition(child));

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
    /**
     * Response WebService
     * se llena la lista con las consultas provenientes del WebService con la BD
     * @param response
     */
    @Override
    public void obtuvoCorrectamente(Object response){
        try {

            Log.e("CASO",Pago_Controller.getCasoRequest()+"");

            if (Parametros.getRespuesta().equals("Error")||Parametros.getRespuesta().equals("ERROR") ) {

                Toast.makeText(parentActivity, "Ups, ha ocurrido un error", Toast.LENGTH_SHORT).show();

            }else {
                switch (Pago_Controller.getCasoRequest()) {

                    case 0:
                        ArrayList listaPago = new ArrayList<Pago>();
                        JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());
                        System.out.println(Parametros.getRespuesta());
                        for (int i = 0; i < mJsonArray.length(); i++) {   // iterate through jsonArray
                            String strJson = mJsonArray.getString(i);
                            JSONObject jObject = new JSONObject(strJson);

                            listaPago.add(new Pago(
                                    (int) jObject.get("pg_id"),
                                    (String) jObject.get("pg_categoria"),
                                    (String) jObject.get("pg_descripcion"),
                                    (float) jObject.get("pg_monto"),
                                    (String) jObject.get("pg_tipoTransaccion")));

                        }

                        Pago_Controller.setListaPagos(listaPago);
                        recycleList.setAdapter(new PagoAdapter(listaPago));
                        Pago_Controller.resetCasoRequest();

                        break;

                    case 2:
                        Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();
                        Pago_Controller.obtenerTodosPagos(false);

                        break;
                    case 3:

                        Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();
                        Pago_Controller.obtenerTodosPagos(false);

                        break;

                    default:
                        break;
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void noObtuvoCorrectamente(Object response){

    }

}
