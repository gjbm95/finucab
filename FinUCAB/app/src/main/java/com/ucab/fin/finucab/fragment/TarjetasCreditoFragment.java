package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.ucab.fin.finucab.controllers.Banco_Controller;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.Tarjeta_Controller;
import com.ucab.fin.finucab.domain.Cuenta_Bancaria;
import com.ucab.fin.finucab.domain.Tarjeta_Credito;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TarjetasCreditoFragment extends Fragment  implements ResponseWebServiceInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FloatingActionButton fab; //Boton flotante para agregar mas bancos
    MainActivity parentActivity;
    RecyclerView recycleList;
    private int positionLongPress = -1; //posicion del menu longpress



    public TarjetasCreditoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TarjetasCreditoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TarjetasCreditoFragment newInstance(String param1, String param2) {
        TarjetasCreditoFragment fragment = new TarjetasCreditoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View fragview = inflater.inflate(R.layout.tarjetas_credito_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Tarjetas de Credito");
        Tarjeta_Controller.initManejador(parentActivity,this);
        // Configuracion inicial del boton flotante
        fab = (FloatingActionButton) fragview.findViewById(R.id.addFloatingBtnTarjetas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new AgregarTarjetaCFragment(), false);
                parentActivity.closeDrawerLayout();
            }
        });
        //Configurando RecyclerView
        recycleList = (RecyclerView) fragview.findViewById(R.id.tarjetasReList);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleList.setLayoutManager(myLayoutManager);
        recycleList.addOnItemTouchListener(new TarjetasCreditoFragment.RecyclerTouchListener
                (getActivity(), recycleList, new TarjetasCreditoFragment.ClickListener() {

            @Override
            public void onClick(View view, final int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                positionLongPress = position;
                registerForContextMenu(recycleList);
            }


        }));

        Tarjeta_Controller.obtenerTodasTarjetas(true);


        return fragview;
    }


    public static interface ClickListener{

        public void onClick(View view,int position);
        public void onLongClick(View view,int position);

    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private TarjetasCreditoFragment.ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView,
                                     final TarjetasCreditoFragment.ClickListener clicklistener){

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



    private void llenadoPrueba(){
        ArrayList<Tarjeta_Credito>listaTarjetas = new ArrayList<Tarjeta_Credito>();
        listaTarjetas.add(new Tarjeta_Credito(1,"Master Card","20/06/2017",200000,"023489234233"));
        listaTarjetas.add(new Tarjeta_Credito(2,"American Express","20/06/2017",200000,"234972342323"));
        recycleList.setAdapter(new TarjetaAdapter(listaTarjetas));
    }

    /**
     *Creando menu de longpress llamada al menu
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.tarjeta_menu, menu);

    }

    /**
     * Opciones del longPress Editar y eliminar
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.deleteTarjetaOption:


                return true;

            case R.id.editarTarjetaOption:
                parentActivity.changeFragment(new ModificarTarjetaCFragment(), false);
                parentActivity.closeDrawerLayout();
                return true;

            default:
                return super.onContextItemSelected(item);

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


            if (Parametros.getRespuesta().equals("Error")||Parametros.getRespuesta().equals("ERROR") ) {

                Toast.makeText(parentActivity, "Ups, ha ocurrido un error", Toast.LENGTH_SHORT).show();

            }else {
                switch (Tarjeta_Controller.getCasoRequest()) {

                    case 0:
                        ArrayList listaTarjetas = new ArrayList<Tarjeta_Credito>();
                        JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());

                        for (int i = 0; i < mJsonArray.length(); i++) {   // iterate through jsonArray
                            String strJson = mJsonArray.getString(i);
                            JSONObject jObject = new JSONObject(strJson);

                            listaTarjetas.add(new Tarjeta_Credito((int) jObject.get("tc_id"),
                                    (String) jObject.get("tc_tipo"),
                                    (String) jObject.get("tc_fechavencimiento"),
                                    (float) jObject.get("tc_saldo"),
                                    (String) jObject.get("tc_numero")));

                        }

                        Tarjeta_Controller.setListaTarjetas(listaTarjetas);
                        recycleList.setAdapter(new BancoAdapter(listaTarjetas));
                        Tarjeta_Controller.resetCasoRequest();

                        break;

                    case 2:
                        Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();

                        break;
                    case 3:

                        Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();
                        //Banco_Controller.obtenerTodasCategorias(false);

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
