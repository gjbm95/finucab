package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.AddPaymentActivity;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.domain.Pago;

import java.util.ArrayList;
import java.util.List;

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
            @Override
            public void onClick(View v) {
                startActivity( new Intent(parentActivity, AddPaymentActivity.class));

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

//BORRAR CUANDO SE IMPLEMENTE LA CLASE PRESUPUESTO



    private ArrayList<Pago> populatedList() {

        ArrayList<Pago> listOfPersona = new ArrayList<Pago>();

        for(int i=0;i<5;i++)

        {

            Pago pi = new Pago();

            pi.setCategoria("Nombre Categoria");
            pi.setDescripcion("Descripcion");
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

        public RecyclerTouchListener(FragmentActivity activity, RecyclerView recycleList, ClickListener clickListener) {
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
