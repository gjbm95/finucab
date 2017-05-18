package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu;
import android.view.MenuInflater;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Pago_Controller;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;
import com.ucab.fin.finucab.domain.Pago;
import com.ucab.fin.finucab.domain.Presupuesto;

import java.util.ArrayList;

public class PaymentFragment extends Fragment {


    FloatingActionButton fab;
    MainActivity parentActivity;

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

        PagoAdapter pAdapter =new PagoAdapter(populatedList());
        recycleList.setAdapter(pAdapter);
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

            case R.id.modifyGainOption:

                Pago p = new Pago();
                p.setCategoria("Universidad");
                p.setDescripcion("Semestre2");
                p.setTotal((float) 222.222);
                p.setTipo("Egreso");
                Pago_Controller.pago = p;
                parentActivity.changeFragment(new ModificarPago_Fragment(), false);

                return true;

            default:

                return super.onContextItemSelected(item);

        }

    }
    //BORRAR CUANDO SE IMPLEMENTE LA CLASE PAGO
    private ArrayList<Pago> populatedList() {

        ArrayList<Pago> listOfPersona = new ArrayList<Pago>();

        for(int i=0;i<5;i++)

        {

            Pago pi = new Pago();

            pi.setCategoria("Universidad");
            pi.setDescripcion("Semestre");
            pi.setTotal(10);
            pi.setFecha("A");
            pi.setImpuesto(0);
            pi.setSubtotal(0);
            pi.setTipo("ingreso");
            pi.setIdPago(0);

            listOfPersona.add(pi);

        }

        return listOfPersona;

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

}
