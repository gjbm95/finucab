package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Planificacion;
import com.ucab.fin.finucab.domain.Planificacion_Pago;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

/**
 * Created by William on 27/5/2017.
 */

public class Planificacion_Controller {

    public static Planificacion_Pago planificacion_pago;
    public static Manejador_Categoria categoriaC;
    public static int managementRequest = 0;


    public static void init(Activity activity, ResponseWebServiceInterface interfaz){
        planificacion_pago = new Planificacion_Pago(activity, interfaz);
        categoriaC = new Manejador_Categoria(activity, interfaz);
    }

    public static void obtenerListaPlanificacion(){
        managementRequest =0;
        planificacion_pago.listaPlanificacion();
    }

    public static void agregarPlanificacion(Planificacion planificacion){
        managementRequest =2;
        planificacion_pago.agregarPlanificacion(planificacion);
    }

    public static void listaCategoriasPa(){
        managementRequest =1;
        categoriaC.obtenerTodasCategorias(true);
    }




}
