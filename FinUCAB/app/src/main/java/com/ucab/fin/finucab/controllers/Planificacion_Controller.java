package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Planificacion;
import com.ucab.fin.finucab.domain.Planificacion_Pago;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

/**
 * Created by William on 27/5/2017.
 */

public class Planificacion_Controller {

    public static Planificacion_Pago planificacion_pago;


    public static void init(Activity activity, ResponseWebServiceInterface interfaz){
        planificacion_pago = new Planificacion_Pago(activity, interfaz);

    }

    public static void obtenerListaPlanificacion(){
        planificacion_pago.listaPlanificacion();
    }

    public static void agregarPlanificacion(Planificacion planificacion){
        planificacion_pago.agregarPlanificacion(planificacion);
    }





}
