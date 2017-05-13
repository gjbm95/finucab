package com.ucab.fin.finucab.fragment;import android.content.Context;import android.os.Bundle;import android.support.design.widget.FloatingActionButton;import android.support.v4.app.Fragment;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.view.ContextMenu;import android.view.GestureDetector;import android.view.LayoutInflater;import android.view.MenuInflater;import android.view.MenuItem;import android.view.MotionEvent;import android.view.View;import android.view.ViewGroup;import android.widget.Toast;import com.ucab.fin.finucab.R;import com.ucab.fin.finucab.activity.MainActivity;import com.ucab.fin.finucab.controllers.Presupuesto_Controller;import com.ucab.fin.finucab.domain.Presupuesto;import java.util.ArrayList;/** * A simple {@link Fragment} subclass. */public class GananciasFragment extends Fragment {    FloatingActionButton fab;    MainActivity parentActivity;    public GananciasFragment() {        // Required empty public constructor    }    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {        // Inflate the layout for this fragment        View rootView = inflater.inflate(R.layout.ganancias_fragment, container, false);        parentActivity = (MainActivity) getActivity();        fab = (FloatingActionButton) rootView.findViewById(R.id.addFloatingBtnGanancia);        fab.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                parentActivity.changeFragment(new AgregarPresupuesto_fragment(), false);            }        });        final RecyclerView recycleList = (RecyclerView) rootView.findViewById(R.id.gananciaReList);        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);        recycleList.setLayoutManager(myLayoutManager);        recycleList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),                recycleList, new GananciasFragment.ClickListener() {            @Override            public void onClick(View view, final int position) {                //Values are passing to activity & to fragment as well                //Toast.makeText(getActivity(), "Single Click on position :"+position,                //        Toast.LENGTH_SHORT).show();            }            @Override            public void onLongClick(View view, int position) {                registerForContextMenu(recycleList);            }        }));        PresupuestoAdapter pAdapter =new PresupuestoAdapter(populatedList());        recycleList.setAdapter(pAdapter);        return rootView;    }    @Override    public void onCreateContextMenu(ContextMenu menu, View v,                                    ContextMenu.ContextMenuInfo menuInfo)    {        super.onCreateContextMenu(menu, v, menuInfo);        MenuInflater inflater = getActivity().getMenuInflater();        inflater.inflate(R.menu.presupuesto_menu, menu);    }    //COLOCAR LASOPCIONES MODIFICAR Y ELIMINAR    @Override    public boolean onContextItemSelected(MenuItem item) {        switch (item.getItemId()) {            case R.id.modifyOption:                Presupuesto p = new Presupuesto();                p.set_nombre("hola");                p.set_duracion(20);                // p.set_categoria("categoria 1");                p.set_monto((float) 150000.0);                // p.set_clasificacion("sdfgsdf"); //clasificacion ganancia perdida                // p.set_tipo("sdfsdfsd"); // unico o recurrente                Presupuesto_Controller.presupuesto = p;                parentActivity.changeFragment(new ModificarPresupuestoFragment(), false);                return true;            case R.id.deleteOpcion:                Toast.makeText(getActivity(), "Opcion Eliminar seleccionada",Toast.LENGTH_LONG).show();                return true;            default:                return super.onContextItemSelected(item);        }    }//BORRAR CUANDO SE IMPLEMENTE LA CLASE PRESUPUESTO    private ArrayList<Presupuesto> populatedList() {        ArrayList<Presupuesto> listOfPersona = new ArrayList<Presupuesto>();        for(int i=0;i<20;i++)        {            float monto = 150000;            Presupuesto pi = new Presupuesto();            pi.set_nombre("Ganancia "+i);            pi.set_monto(monto);            pi.set_categoria("Nombre Categoria "+ i);            pi.set_duracion(i);            listOfPersona.add(pi);        }        return listOfPersona;    }    public static interface ClickListener{        public void onClick(View view,int position);        public void onLongClick(View view,int position);    }    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{        private GananciasFragment.ClickListener clicklistener;        private GestureDetector gestureDetector;        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final GananciasFragment.ClickListener clicklistener){            this.clicklistener=clicklistener;            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){                @Override                public boolean onSingleTapUp(MotionEvent e) {                    return true;                }                @Override                public void onLongPress(MotionEvent e) {                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());                    if(child!=null && clicklistener!=null){                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));                    }                }            });        }        @Override        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {            View child=rv.findChildViewUnder(e.getX(),e.getY());            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){                clicklistener.onClick(child,rv.getChildAdapterPosition(child));            }            return false;        }        @Override        public void onTouchEvent(RecyclerView rv, MotionEvent e) {        }        @Override        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {        }    }}