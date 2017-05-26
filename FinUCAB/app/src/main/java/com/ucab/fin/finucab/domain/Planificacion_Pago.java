package com.ucab.fin.finucab.domain;

import android.app.Activity;
import android.util.Log;

import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by Junior on 03/05/2017.
 */

public class Planificacion_Pago {

    private Activity activity;
    private ArrayList<Planificacion> listaPlanificacion;
    private ResponseWebServiceInterface interfaz;
    private Usuario usuario;

    public ArrayList<Planificacion> getListaPlanificacion() {
        return listaPlanificacion;
    }
    public void setListaPlanificacion(ArrayList<Planificacion> listaPlanificacion) {
        this.listaPlanificacion = listaPlanificacion;
    }


    public Planificacion_Pago(Activity actividad, ResponseWebServiceInterface interfaz){
        this.activity = actividad;
        this.interfaz = interfaz;
    }

    public void agregarPlanificacion( Planificacion planificacion ){

        usuario = ControlDatos.getUsuario();
        JSONObject object = new JSONObject();

       //  usuariou_id, categoriaca_id, pa_activo

        try {
            object.put("pa_id", planificacion.getId());
            object.put("pa_nombre", planificacion.getNombre());
            object.put("pa_descripcion", planificacion.getDescripcion());
            object.put("pa_monto", planificacion.getMonto().toString());
            object.put("pa_fechainicio", planificacion.getFechaInicio().toString());
            object.put("pa_fechafin", planificacion.getFechaFin().toString());
            object.put("pa_recurrente", planificacion.getRecurrente().booleanValue());
            object.put("pa_recurrencia", planificacion.getRecurrencia());
            object.put("usuariou_id", usuario.getIdusuario());
            object.put("categoriaca_id", planificacion.getIdCategoria());
            object.put("pa_activo", planificacion.getActivo().booleanValue());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo6/registrarPlanificacion?datosPlanificacion=" + URLEncoder.encode(object.toString()));
        new Recepcion(activity).execute("GET");

    }

    public void listaPlanificacion(){

        int id = 1;
        Parametros.setMetodo("Modulo6/visualizarPlanificacion?datosPlanificacion="+ String.valueOf(id));
        new Recepcion(activity, interfaz).execute("GET");
    }


}
