package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
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

    public static Manejador_Categoria manejador;

    public static void initManejador( Activity actividad ){

        manejador = new Manejador_Categoria(actividad);

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

        Categoria categoria = manejador.obtenerCategoria(id);
        categoria.setEstaHabilitado(esHabilitar);

        manejador.modificarCategoria(categoria);

    }

    public static void modificarCategoria(Categoria categoria){

        manejador.modificarCategoria(categoria);

    }

    //METODO PARA REGISTRAR UNA CATEGORIA
    public static void registrarCategoria(Categoria categoria){

        manejador.agregarCategoria(categoria);

    }

    public static void borrarCategoria(int id){

        manejador.borrarCategoria(id);

    }


    public static void obtenerTodasCategorias(){

        manejador.obtenerTodasCategorias();

    }


}




