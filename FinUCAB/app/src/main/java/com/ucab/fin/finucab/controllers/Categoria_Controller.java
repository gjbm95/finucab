package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.fragment.CategoriaAdapter;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Juan on 10-05-2017.
 */

public class Categoria_Controller {

    public static Categoria categoria;
    public static EditText escribirCategoria;
    public static EditText escribirDescripcion;
    public static ArrayList<Categoria> listaCategoria = new ArrayList<>();


    public static void asignarValores( ){

        escribirCategoria.setText(categoria.getNombre());
        escribirDescripcion.setText(categoria.getDescripcion());
    }

    /**Realizo la validacion para verificar que el campo este vacio:
     *
     *
     * @param campo
     * @throws CampoVacio_Exception
     */
    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {
        if (campo.getText().toString().isEmpty())
        {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }

    }

    public static void HabilitarCategoria(int id, boolean esHabilitar){

        //Log.v("Id",id+"/"+esHabilitar);

    }

    //METODO PARA REGISTRAR UNA CATEGORIA
    public static String registrarCategoria(Categoria categoria, Activity actividad){
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
        return Parametros.getRespuesta();
    }

    //METODOS PARA VISUALIZAR CATEGORIA
    public static void visualizarCategoria(RecyclerView recycleList){
        CategoriaAdapter cAdapter;
        cAdapter = new CategoriaAdapter(listaCategoria);
        recycleList.setAdapter(cAdapter);
    }

    public static void visualizarCategoria( Activity actividad ){

        listaCategoria = new ArrayList<>();


        Parametros.setMetodo("Modulo4/ListaCategoria" );
        new Recepcion(actividad).execute("GET");
        System.out.println(Parametros.respuesta);
        JSONObject jObject = null;
        try {
            JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
            int count = mJsonArray.length();
            for(int i=0 ; i< count; i++){   // iterate through jsonArray
                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Categoria cat = new Categoria();
                cat.setNombre((String)jObject.get("Nombre"));
                cat.setDescripcion((String)jObject.get("Descripcion"));
                //cat.isEstaHabilitado(Boolean.parseBoolean((String) jObject.get("Estado")));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}




