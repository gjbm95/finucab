package com.ucab.fin.finucab.fragment;

import android.content.Context;
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
import com.ucab.fin.finucab.domain.Presupuesto;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GastosFragment#} factory method to
 * create an instance of this fragment.
 */
public class GastosFragment extends Fragment {
    // TODO: Rename and change types of parameters
    FloatingActionButton fab;
    MainActivity parentActivity;

    public GastosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gastos_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        fab = (FloatingActionButton) rootView.findViewById(R.id.addFloatingBtnGasto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new AgregarPresupuesto_fragment(), false);

            }
        });
        final RecyclerView recycleList = (RecyclerView) rootView.findViewById(R.id.gastoReList);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleList.setLayoutManager(myLayoutManager);

        recycleList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recycleList, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

                registerForContextMenu(recycleList);
            }
        }));

        PresupuestoAdapter pAdapter =new PresupuestoAdapter(populatedList());
        recycleList.setAdapter(pAdapter);

        return rootView;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.presupuesto_menu, menu);
    }

    //COLOCAR LASOPCIONES MODIFICAR Y ELIMINAR
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.modifyOption:
                Toast.makeText(getActivity(), "Opcion Modificar seleccionada",Toast.LENGTH_LONG).show();
                return true;
            case R.id.deleteOpcion:
                Toast.makeText(getActivity(), "Opcion Eliminar seleccionada",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
//BORRAR CUANDO SE IMPLEMENTE LA CLASE PRESUPUESTO

    private ArrayList<Presupuesto> populatedList() {
        ArrayList<Presupuesto> listOfPersona = new ArrayList<Presupuesto>();
        for(int i=0;i<20;i++) {
            float monto = 150000;
            Presupuesto pi = new Presupuesto();
            pi.set_nombre("Gasto "+i);
            pi.set_monto(monto);
            pi.set_categoria("Nombre Categoria "+ i);
            pi.set_duracion(i);
            listOfPersona.add(pi);
        }
        return listOfPersona;
    }


    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

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
