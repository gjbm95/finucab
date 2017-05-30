package com.ucab.fin.finucab.domain;

import android.app.Activity;
import android.util.Log;

import com.ucab.fin.finucab.controllers.Planificacion_Controller;
import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Junior on 03/05/2017.
 */

public class Planificacion_Pago {

    private Activity activity;
    private ArrayList<Planificacion> listaPlanificacion;
    private ResponseWebServiceInterface interfaz;
    private Usuario usuario;
    private String recurrencia;
    private Planificacion planificacion;

    public ArrayList<Planificacion> getListaPlanificacion() {
        return listaPlanificacion;
    }

    public void setListaPlanificacion(ArrayList<Planificacion> listaPlanificacion) {
        this.listaPlanificacion = listaPlanificacion;
    }

    public Planificacion getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(Planificacion planificacion) {
        this.planificacion = planificacion;
    }

    public Planificacion_Pago(Activity actividad, ResponseWebServiceInterface interfaz) {
        this.activity = actividad;
        this.interfaz = interfaz;
    }

    public void agregarPlanificacion(Planificacion planificacion) {

        DateFormat format = new SimpleDateFormat("d-M-yyyy");
        usuario = ControlDatos.getUsuario();
        JSONObject object = new JSONObject();
        String fechaIni = format.format(planificacion.getFechaInicio());
        String fechaFin = format.format(planificacion.getFechaFin());
        if (!planificacion.getRecurrencia().equals("")) {
            recurrencia = planificacion.getRecurrencia();
        } else {
            recurrencia = "";
        }

        try {
            object.put("pa_nombre", planificacion.getNombre());
            object.put("pa_descripcion", planificacion.getDescripcion());
            object.put("pa_monto", planificacion.getMonto().toString());
            object.put("pa_fechainicio", fechaIni);
            object.put("pa_fechafin", fechaFin);
            object.put("pa_recurrente", planificacion.getRecurrente().booleanValue());
            object.put("pa_recurrencia", recurrencia);
            object.put("usuariou_id", usuario.getIdusuario());
            object.put("categoriaca_id", planificacion.getIdCategoria());
            object.put("pa_activo", planificacion.getActivo().booleanValue());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo6/registrarPlanificacion?datosPlanificacion=" + URLEncoder.encode(object.toString()));
        new Recepcion(activity, interfaz).execute("GET");

    }

    public void listaPlanificacion() {

        usuario = ControlDatos.getUsuario();
        int id = usuario.getIdusuario();
        Parametros.reset();
        Parametros.setMetodo("Modulo6/visualizarPlanificacion?datosPlanificacion=" + String.valueOf(id));
        new Recepcion(activity, interfaz).execute("GET");
    }

    public void obtenerPlanificacion(int id){
        Parametros.reset();
        Parametros.setMetodo("Modulo6/buscarPlanificacion?datosPlanificacion="+ String.valueOf(id));
        new Recepcion(activity, interfaz).execute("GET");/*
        Planificacion pa;
        ArrayList<Planificacion> al = Planificacion_Controller.getListaGuardada();
        for (int i=0; i<al.size(); i++){
            pa = al.get(i);
            if (pa.getId() == id) {
                return pa;
            }
        }
        return null;*/
    }

    public void borrarPlanificacion(int id){
        Parametros.reset();
        Parametros.setMetodo("Modulo6/eliminarPlanificacion?datosPlanificacion=" + String.valueOf(id));
        new Recepcion(activity, interfaz).execute("GET");
    }

}
