package com.ucab.fin.finucab.domain;

import android.app.Activity;
import android.util.Log;

import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramon on 24-May-17.
 */

public class Manejador_Categoria {

    private Activity actividad;
    private ArrayList<Categoria> categorias;

    public ArrayList<Categoria> getCategorias() {

        if (categorias.size() == 0 ){
            categorias = obtenerTodasCategorias();
        }

        return categorias;
    }

    public Manejador_Categoria(Activity actividad){

        this.actividad = actividad;
    }

    public boolean agregarCategoria( Categoria categoria) {

        JSONObject nueva_categoria = new JSONObject();
        try {
            nueva_categoria.put("c_nombre",categoria.getNombre());
            nueva_categoria.put("c_descripcion",categoria.getDescripcion());
            nueva_categoria.put("c_estado",categoria.isEstaHabilitado());
            nueva_categoria.put("c_ingreso",categoria.isIngreso());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo4/registrarCategoria?datosCategoria="+ URLEncoder.encode(nueva_categoria.toString()));
        new Recepcion(actividad).execute("GET");
        String respuesta = Parametros.getRespuesta();

        Log.v("Response-Manejador",respuesta);

        return true;
    }

    public boolean modificarCategoria( Categoria categoria) {
        return false;
    }

    public Categoria obtenerCategoria( int id) {

        ArrayList<Categoria> la = getCategorias();

        for(int i=0 ; i< la.size(); i++) {

            if( la.get(i).getIdcategoria() == id ){
                return  la.get(i);
            }

        }

        return  null;
    }

    public boolean borrarCategoria( int id) {

        Parametros.reset();
        Parametros.setMetodo("Modulo4/eliminarCategoria?datosCategoria="+ String.valueOf(id));
        new Recepcion(actividad).execute("GET");
        //Log.v("Response-Manejador",Parametros.respuesta);
        return false;
    }

    public ArrayList<Categoria> obtenerTodasCategorias() {

        ArrayList listaCategoria = new ArrayList<Categoria>();

        Parametros.setMetodo("Modulo4/visualizarCategoria" );
        new Recepcion(actividad).execute("GET");
        System.out.println(Parametros.respuesta);
        JSONObject jObject = null;
        try {
            JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
            int count = mJsonArray.length();

            Log.v("Response-Manejador",count+"");
            for(int i=0 ; i< count; i++){   // iterate through jsonArray
                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Categoria cat = new Categoria((int)jObject.get("Id"),
                                            (String)jObject.get("Nombre"),
                                            (String)jObject.get("Descripcion"),
                                            (Boolean) jObject.get("esHabilitado"),
                                            (Boolean) jObject.get("esIngreso"));
                listaCategoria.add(cat);

            }

            return populatedList();
            //return listaCategoria;

        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }

    }

    private ArrayList<Categoria> populatedList() {

        //Categoria_Controller.obtenerTodasCategorias(parentActivity);
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


}
