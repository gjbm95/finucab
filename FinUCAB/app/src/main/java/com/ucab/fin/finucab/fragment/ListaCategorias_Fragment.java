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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.domain.Categoria;

import java.util.ArrayList;





/**

 * A simple {@link Fragment} subclass.

 */

public class ListaCategorias_Fragment extends Fragment {

    FloatingActionButton fab;



    MainActivity parentActivity;





    public ListaCategorias_Fragment() {

        // Required empty public constructor

    }





    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lista_categorias, container, false);
        parentActivity = (MainActivity) getActivity();
        final RecyclerView recycleList = (RecyclerView) rootView.findViewById(R.id.categoriaReList);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleList.setLayoutManager(myLayoutManager);
        recycleList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recycleList, new ListaCategorias_Fragment.ClickListener() {
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

        CategoriaAdapter cAdapter =new CategoriaAdapter(populatedList());
        recycleList.setAdapter(cAdapter);
        return rootView;

    }






/*
 @Override
    public void onCreateContextMenu(ContextMenu menu, View v,

                                    ContextMenu.ContextMenuInfo menuInfo)

    {

        super.onCreateContextMenu(menu, v, menuInfo);



        MenuInflater inflater = getActivity().getMenuInflater();

        inflater.inflate(R.menu.presupuesto_gasto_menu, menu);

    }
    */



    //COLOCAR LASOPCIONES MODIFICAR Y ELIMINAR



   /*
   @Override
   public boolean onContextItemSelected(MenuItem item) {



        switch (item.getItemId()) {

            case R.id.modifyOption:

                Presupuesto p = new Presupuesto();
                p.set_nombre("hola");
                p.set_duracion(20);
                // p.set_categoria("categoria 1");
                p.set_monto((float) 150000.0);
                // p.set_clasificacion("sdfgsdf"); //clasificacion ganancia perdida
                // p.set_tipo("sdfsdfsd"); // unico o recurrente
                Presupuesto_Controller.presupuesto = p;
                parentActivity.changeFragment(new ModificarPresupuestoFragment(), false);

                return true;

            case R.id.deleteOpcion:

                Toast.makeText(getActivity(), "Opcion Eliminar seleccionada",Toast.LENGTH_LONG).show();

                return true;

            default:

                return super.onContextItemSelected(item);

        }

    }
*/
//BORRAR CUANDO SE IMPLEMENTE LA CLASE PRESUPUESTO



    private ArrayList<Categoria> populatedList() {

        ArrayList<Categoria> listOfPersona = new ArrayList<Categoria>();

        for(int i=0;i<20;i++)

        {

            float monto = 150000;

            Categoria pi = new Categoria();

            pi.setNombre("Nombre Categoria");


            listOfPersona.add(pi);



        }

        return listOfPersona;

    }





    public static interface ClickListener{

        public void onClick(View view,int position);

        public void onLongClick(View view,int position);

    }



    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{



        private ListaCategorias_Fragment.ClickListener clicklistener;

        private GestureDetector gestureDetector;



        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ListaCategorias_Fragment.ClickListener clicklistener){



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

