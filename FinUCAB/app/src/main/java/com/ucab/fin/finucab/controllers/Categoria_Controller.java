package com.ucab.fin.finucab.controllers;

import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;

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

}


