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
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.ExportarCategoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase se encargara de manejar lo relacionado con la lista de categorias
 * botones, funciones, llamadas
 */

public class ListaCategorias_Fragment extends Fragment implements ResponseWebServiceInterface {

    FloatingActionButton fab; //Boton flotante para agregar mas categorias
    MainActivity parentActivity;
    RecyclerView recycleList;

    private int positionLongPress = -1; //posicion del menu longpress
    private boolean isInOnCreate;
    /**
     *llamada al layout fragment_lista_categoria la cual muestra la posicion en la que
     *se mostraran las listas
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isInOnCreate = true;
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lista_categorias, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Categorias");

        Categoria_Controller.fragment = this;
        Categoria_Controller.initManejador(parentActivity,this);

        // Configuracion inicial del boton flotante
        fab = (FloatingActionButton) rootView.findViewById(R.id.addFloatingBtnCategoria);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parentActivity.changeFragment(new AgregarCategoria_Fragment(), false);
                parentActivity.closeDrawerLayout();
            }
        });

        //tipo RecyclerView
        recycleList = (RecyclerView) rootView.findViewById(R.id.categoriaReList);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        recycleList.setLayoutManager(myLayoutManager);
        recycleList.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recycleList, new ListaCategorias_Fragment.ClickListener() {
            /**
             * se llama al controlador de categoria
             * @param view
             * @param position
             */
            @Override
            public void onClick(View view, final int position) {

            }

            /**
             * Acciones para el longpress
             * @param view
             * @param position
             */
            @Override
            public void onLongClick(View view, int position) {

                positionLongPress = position;
                registerForContextMenu(recycleList);
            }
        }));

        Categoria_Controller.obtenerTodasCategorias(true);

        return rootView;

    }

    /**
     * Metodo para redirecciona al AgregarCategoria_Fragment para editarla
     * @param categoria Categoria a mostrar
     */
    public void redireccionarAgregarCategoria(Categoria categoria){
        AgregarCategoria_Fragment modificar = new AgregarCategoria_Fragment();
        modificar.categoria = categoria;
        parentActivity.changeFragment(modificar, false);
        parentActivity.closeDrawerLayout();

    }

    @Override
    public void onResume() {
        super.onResume();

        if (!isInOnCreate) {

            Categoria_Controller.initManejador(parentActivity,this);
            Categoria_Controller.obtenerTodasCategorias(false);
        }

        isInOnCreate = false;
    }

    /**
     *Creando menu de longpress llamada al menu
     * @param menu
     * @param v
     * @param menuInfo
    */
    @Override

    public void onCreateContextMenu(ContextMenu menu, View v,  ContextMenu.ContextMenuInfo menuInfo)
    {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.categoria_menu, menu);

    }


    /**
     * Opciones del longPress Exportar y eliminar
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.exportCategoryOpcion:

                /*si la opcion es Exportar se llama a ExportarCategoria
                para crear un archivo excel o cvs*/
                Toast.makeText(parentActivity, "Exportando...", Toast.LENGTH_LONG).show();
                ExportarCategoria_Controller task=new ExportarCategoria_Controller(Categoria_Controller.getListaCategorias());
                task.execute();
                Toast.makeText(parentActivity, "Exportado correctamente", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.deleteCategoryOption:
                    /*si la opcion es Eliminar se llama a borrarCategoria
                para eliminar la categoria seleccionada*/
                Toast.makeText(getActivity(), "Eliminando categoria ...",Toast.LENGTH_LONG).show();

                Categoria_Controller.borrarCategoria(positionLongPress);
                positionLongPress = -1;

                return true;

            default:
                return super.onContextItemSelected(item);

        }

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
                switch (Categoria_Controller.getCasoRequest()) {

                    case 0:
                        ArrayList listaCategoria = new ArrayList<Categoria>();
                        JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());

                        for (int i = 0; i < mJsonArray.length(); i++) {   // iterate through jsonArray
                            String strJson = mJsonArray.getString(i);
                            JSONObject jObject = new JSONObject(strJson);

                            listaCategoria.add(new Categoria((int) jObject.get("Id"),
                                    (String) jObject.get("Nombre"),
                                    (String) jObject.get("Descripcion"),
                                    (Boolean) jObject.get("esHabilitado"),
                                    (Boolean) jObject.get("esIngreso")));

                        }

                        Categoria_Controller.setHabilitarEventoSwitch(false);
                        Categoria_Controller.setListaCategorias(listaCategoria);
                        recycleList.setAdapter(new CategoriaAdapter(listaCategoria));
                        Categoria_Controller.resetCasoRequest();

                        break;

                    case 2:
                        Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();
                        Categoria_Controller.obtenerTodasCategorias(false);

                        break;
                    case 3:

                        Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();
                        Categoria_Controller.obtenerTodasCategorias(false);

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

