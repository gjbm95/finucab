package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.fragment.AgregarPresupuesto_fragment;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by Oswaldo on 06/05/2017.
 */

public class Presupuesto_Controller {

    public static Presupuesto presupuesto;

    public static EditText nombrePresupuesto;
    public static EditText montoPresupuesto;
    public static EditText recurrenciaPresupuesto;
    public static CompoundButton recurrenciaButton, unicoButton;
    public static Spinner categoriaPresupuesto;
    public static TextView recurrenciaTextView;
    public static TextView totalTextView, gananciaTextView, gastoTextView;
    public static Float ganancias,gastos,total;
    public static ArrayList<Presupuesto> listaGanancias = new ArrayList<>();
    public static ArrayList<Presupuesto> listaGastos = new ArrayList<>();





    public static void asignarValores( ){

        nombrePresupuesto.setText(presupuesto.get_nombre());
        montoPresupuesto.setText(presupuesto.get_monto().toString());
        if(presupuesto.get_tipo().equals("Unico")){

            unicoButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.INVISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.INVISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
        }else if(presupuesto.get_tipo().equals("Recurrente")){
            recurrenciaButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.VISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.VISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
            recurrenciaPresupuesto.setText( presupuesto.get_duracion().toString());
        }

    }

    public static void volverInvisibleRecurrencia(){
        recurrenciaTextView.setVisibility(recurrenciaTextView.INVISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
        recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.INVISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
    }

    public static int validacionPresupuestoVacio() {

        //TEXTVIEW

        if (nombrePresupuesto.getText().toString().isEmpty()) {
            nombrePresupuesto.setError("Debe colocar un Nombre de Presupuesto");
        }
        if (montoPresupuesto.getText().toString().isEmpty()) {
            montoPresupuesto.setError("Debe colocar un Monto");
        }

        //SPINNER
        if (categoriaPresupuesto.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) categoriaPresupuesto.getSelectedView();
            errorText.setError("Debe colocar una categoria");
        }

        if (recurrenciaButton.isChecked()) {
            if (recurrenciaPresupuesto.getText().toString().isEmpty()) {
                recurrenciaPresupuesto.setError("Debe colocar un numero de meses");
            }
        }

        return 0;
    }

    public static void cualquiercosa (Activity actividad, TextView cartel){
        Parametros.setUrl("http://192.168.0.112:8080/FinUcabWebService/webresources/Modulo1/pruebaDB" );
        new Recepcion(actividad).execute(Parametros.getUrl());

    }

    //Este es el que funciona
    public static void visualizarPresupuestos( Activity actividad ){
        listaGanancias = new ArrayList<>();
        listaGastos = new ArrayList<>();
        ganancias = 0.0F;
        gastos = 0.0F;
        total = 0.0F;
        Parametros.setUrl("http://192.168.1.4:8080/FinUcabWebService/webresources/Modulo3" +
                "/ListaPresupuesto" );
        System.out.println("VA A INICIAR EL HILO");
        new Recepcion(actividad).execute(Parametros.getUrl());
        System.out.println("SALIO DLE HILO");
        System.out.println(Parametros.respuesta);
        JSONObject jObject = null;

        try {
            System.out.println("Primera linea del try");
            JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
            int count = mJsonArray.length();
            for(int i=0 ; i< count; i++){   // iterate through jsonArray
                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Presupuesto pre = new Presupuesto();
                pre.set_duracion(Integer.parseInt((String)jObject.get("Duracion")));
                pre.set_clasificacion((String)jObject.get("Clasificacion"));
                pre.set_monto(Float.parseFloat((String)jObject.get("Monto")));
                pre.set_categoria((String)jObject.get("Categoria"));
                pre.set_nombre((String)jObject.get("Nombre"));
                String tipo = (String) jObject.get("Tipo");
                System.out.println("Tipo en el JSON: "+(String) jObject.get("Tipo"));
                System.out.println("Tipo en la variable: "+tipo);
                if((jObject.get("Tipo")).equals("t")){
                    listaGanancias.add(pre);
                    ganancias = ganancias + pre.get_monto();
                }else{
                    listaGastos.add(pre);
                    gastos = gastos +pre.get_monto();
                }
            }
            total = ganancias - gastos;
            System.out.println("TOTAL: "+total.toString()+" GANANCIAS: "+ganancias.toString()+" GASTOS: "+gastos.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static void asignarTotales(){
        gananciaTextView.setText(ganancias.toString());
        gastoTextView.setText(gastos.toString());
        totalTextView.setText(total.toString());
    }


}
