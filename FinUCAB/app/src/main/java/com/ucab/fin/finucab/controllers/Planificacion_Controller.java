package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.CategoriaSpinner;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Planificacion;
import com.ucab.fin.finucab.domain.Planificacion_Pago;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import java.util.LinkedList;

/**
 * Created by William on 27/5/2017.
 */

public class Planificacion_Controller {

    public static Planificacion_Pago planificacion_pago;
    public static Manejador_Categoria categoriaC;
    public static LinkedList<CategoriaSpinner> categorias;
    private static CategoriaSpinner categoriaSpinner;
    public static int managementRequest = -1;

    public static LinkedList<CategoriaSpinner> getCategorias() {
        return categorias;
    }

    public static void setCategorias(LinkedList<CategoriaSpinner> categorias) {
        Planificacion_Controller.categorias = categorias;
    }

    public static void init(Activity activity, ResponseWebServiceInterface interfaz) {
        planificacion_pago = new Planificacion_Pago(activity, interfaz);
        categoriaC = new Manejador_Categoria(activity, interfaz);
        categorias = new LinkedList();
    }

    public static void obtenerListaPlanificacion() {
        managementRequest = 0;
        planificacion_pago.listaPlanificacion();
    }

    public static void agregarPlanificacion(Planificacion planificacion) {
        managementRequest = 2;
        planificacion_pago.agregarPlanificacion(planificacion);
    }

    public static void listaCategoriasPa() {
        managementRequest = 1;
        categoriaC.obtenerTodasCategorias(true);
    }

    public static String nombreCategoria(int id) {
        String nombre = "";
        for (int i=0; i<categorias.size(); i++){
            categoriaSpinner = categorias.get(i);
            if (categoriaSpinner.getId() == id) {
                nombre = categoriaSpinner.toString();
            }
        }
        return nombre;
    }


}
