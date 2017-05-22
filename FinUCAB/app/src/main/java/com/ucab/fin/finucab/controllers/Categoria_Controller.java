package com.ucab.fin.finucab.controllers;

import android.util.Log;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;

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


        Log.v("Id",id+"/"+esHabilitar);
    }



}


