package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Created by Juan on 10-05-2017.
 */

public class Categoria_Controller {

    public static Categoria categoria;
    public static EditText escribirCategoria;
    public static EditText escribirDescripcion;


    public static void asignarValores( ){

        escribirCategoria.setText(categoria.getNombre());
        escribirDescripcion.setText(categoria.getDescripcion());
    }
    public static int validacionCategoriaVacio() {
        int x = 1;
        //TEXTVIEW

        if (escribirCategoria.getText().toString().isEmpty()) {
            escribirCategoria.setError("Debe colocar una Categoria");
            x=0;
        }
        if (escribirDescripcion.getText().toString().isEmpty()) {
            escribirDescripcion.setError("Debe colocar una Descripcion");
            x=0;
        }


        return x;
    }

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

}


