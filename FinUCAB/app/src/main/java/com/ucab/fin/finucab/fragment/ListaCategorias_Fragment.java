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

 * A simple {@link Fragment} subclass.

 */
public class ListaCategorias_Fragment extends Fragment implements ResponseWebServiceInterface {

    FloatingActionButton fab;
    MainActivity parentActivity;
    RecyclerView recycleList;

    private int positionLongPress = -1;
    private int casoRequest = -1;

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

        Categoria_Controller.initManejador(parentActivity,this);

        // Configuracion inicial del boton flotante
        fab = (FloatingActionButton) rootView.findViewById(R.id.addFloatingBtnCategoria);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(parentActivity, AddCategoryActivity.class));
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

                Intent intent = new Intent(parentActivity, AddCategoryActivity.class);
                intent.putExtra("CATEGORIA_DATA", Categoria_Controller.manejador.getCategorias().get(position));
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                Log.v("longpress",position+"");
                positionLongPress = position;
                registerForContextMenu(recycleList);
            }
        }));
            //celdas


        casoRequest = 0;
        Categoria_Controller.manejador.obtenerTodasCategorias();


        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();

        if (Parametros.getRespuesta() != null) {

            Log.v("Response-Fra",Parametros.getRespuesta());
            if (Parametros.getRespuesta().equals("Error")||Parametros.getRespuesta().equals("ERROR") ) {

                Toast.makeText(parentActivity, "Ups, ha ocurrido un error", Toast.LENGTH_SHORT).show();

            }

            Parametros.reset();
        }

        CategoriaAdapter cAdapter =new CategoriaAdapter(Categoria_Controller.manejador.getCategorias());
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

                casoRequest = 1;
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


    /*---      Response WebService       --*/

    @Override
    public void obtuvoCorrectamente(Object response){

        if ( casoRequest == 0 ) {
            JSONArray mJsonArray = null;
            JSONObject jObject = null;
            String strJson;

            ArrayList listaCategoria = new ArrayList<Categoria>();
            try {
                mJsonArray = new JSONArray(Parametros.getRespuesta());
                int count = mJsonArray.length();

                for(int i=0 ; i< count; i++){   // iterate through jsonArray
                    //jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                    strJson = mJsonArray.getString(i);
                    jObject = new JSONObject(strJson);

                    Categoria cat = new Categoria((int)jObject.get("Id"),
                            (String)jObject.get("Nombre"),
                            (String)jObject.get("Descripcion"),
                            (Boolean) jObject.get("esHabilitado"),
                            (Boolean) jObject.get("esIngreso"));

                    listaCategoria.add(cat);

                }

                Categoria_Controller.manejador.setCategorias(listaCategoria);
                CategoriaAdapter cAdapter =new CategoriaAdapter(listaCategoria);
                recycleList.setAdapter(cAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if ( casoRequest == 1 ) {

            Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void noObtuvoCorrectamente(Object response){

    }

}

