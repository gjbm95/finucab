package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.ucab.fin.finucab.activity.AddCategoryActivity;
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
        parentActivity.getSupportActionBar().setTitle("Categorias");

        // Configuracion inicial del boton flotante
        fab = (FloatingActionButton) rootView.findViewById(R.id.addFloatingBtnCategoria);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(parentActivity, AddCategoryActivity.class));
            }
        });

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
            //celdas
        CategoriaAdapter cAdapter =new CategoriaAdapter(populatedList());
        recycleList.setAdapter(cAdapter);
        return rootView;

    }

//BORRAR CUANDO SE IMPLEMENTE LA CLASE PRESUPUESTO



    private ArrayList<Categoria> populatedList() {

        ArrayList<Categoria> listOfPersona = new ArrayList<Categoria>();

        for(int i=0;i<20;i++)

        {

            Categoria pi = new Categoria();

            pi.setNombre("Nombre Categoria");
            pi.setDescripcion("Descripcion");
            pi.setEstaHabilitado(true);
            pi.setIdcategoria(0);
            pi.isIngreso(false);
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

