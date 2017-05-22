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
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.AddCategoryActivity;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.ExportarCategoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;

import java.util.ArrayList;

/**

 * A simple {@link Fragment} subclass.

 */
public class ListaCategorias_Fragment extends Fragment {

    FloatingActionButton fab;
    MainActivity parentActivity;
    RecyclerView recycleList;

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

        //configuracion inicion del boton exportar

        //configuracion inicial switch habilitar


            }
        });


        recycleList = (RecyclerView) rootView.findViewById(R.id.categoriaReList);
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

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

        CategoriaAdapter cAdapter =new CategoriaAdapter(populatedList());
        recycleList.setAdapter(cAdapter);
    }

    @Override
    //Creando menu de longpress llamada al menu
    public void onCreateContextMenu(ContextMenu menu, View v,  ContextMenu.ContextMenuInfo menuInfo)
    {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.categoria_menu, menu);

    }



    //COLOCAR LASOPCIONES EXPORTAR Y ELIMINAR
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.exportCategoryOpcion:

                Toast.makeText(parentActivity, "Exportando...", Toast.LENGTH_SHORT).show();
                ExportarCategoria_Controller task=new ExportarCategoria_Controller();
                task.execute();
                Toast.makeText(parentActivity, "Exportado correctamente", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.deleteCategoryOption:

                Toast.makeText(getActivity(), "Opcion Eliminar seleccionada",Toast.LENGTH_LONG).show();

                return true;

            default:
                return super.onContextItemSelected(item);

        }

    }

//BORRAR CUANDO SE IMPLEMENTE LA CLASE CATEGORIA
    private ArrayList<Categoria> populatedList() {

        ArrayList<Categoria> listTest = new ArrayList<Categoria>();

        listTest.add(new Categoria(0,"Comida","Almuerzos en la uni",true, false));
        listTest.add(new Categoria(1,"Tranporte","Camino a la uni",true, false));
        listTest.add(new Categoria(2,"Chupetas","Venta de cupetas",true, true));
        listTest.add(new Categoria(3,"Pintura","Putura par ala casa",false, false));
        listTest.add(new Categoria(4,"Deporte","Deporte en la uni",false, false));
        listTest.add(new Categoria(5,"Materiales","materiales de la uni",false, false));
        listTest.add(new Categoria(6,"Musica","Pago de servicios en la uni",true, false));
        listTest.add(new Categoria(7,"Cable","Cable dela casa",false, false));

        return listTest;

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

