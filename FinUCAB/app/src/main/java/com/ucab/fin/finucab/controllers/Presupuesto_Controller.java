package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.NombrePresupuesto_Exception;
import com.ucab.fin.finucab.fragment.AgregarPresupuesto_fragment;
import com.ucab.fin.finucab.fragment.CategoriaAdapter;
import com.ucab.fin.finucab.fragment.PresupuestoAdapter;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
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
    public static Float ganancias, gastos, total;
    public static Integer posicionLista;
    public static RecyclerView recyclerList;
    public static ArrayList<Presupuesto> listaGanancias = new ArrayList<>();
    public static ArrayList<Presupuesto> listaGastos = new ArrayList<>();
    public static boolean tipoPresupuesto;
    public static ResponseWebServiceInterface interfaz;

    public static  void initManejador(ResponseWebServiceInterface interfazFragment){

        interfaz = interfazFragment;


    }

    //METODOS PARA AGREGAR PRESUPUESTOS
    public static void asignarSpinner(Activity actividad) {

        System.out.println(Parametros.respuesta);
        Parametros.setMetodo("Modulo3/ObtenerSpinnerCategoria");
        new Recepcion(actividad).execute("GET");
        JSONObject jObject = null;
        System.out.println("Antes del try");
        try {
            System.out.println("Despues del try");
            JSONArray mJsonArray = new JSONArray(Parametros.respuesta);
            int count = mJsonArray.length();
            String[] valores = new String[count];
            for (int i = 0; i < count; i++) {   // iterate through jsonArray

                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                String categoria = ((String) jObject.get("Nombre"));
                System.out.println("La categoria es: " + categoria);
                valores[i] = categoria;
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(actividad,
                    android.R.layout.simple_spinner_dropdown_item, valores);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categoriaPresupuesto.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //Realizo la validacion para verificar que el presupuesto este correcto y si no esta repetido:

    public static boolean verificoNombre(Activity actividad, EditText campo) throws NombrePresupuesto_Exception{
        String nombre="";
        Parametros.setMetodo("Modulo3/verificarNombre?nombrePresupuesto="+campo.getText().toString());
        new Recepcion(actividad).execute("GET");
        nombre= Parametros.respuesta;
        if (nombre.equals("Repetido"))
        {
            NombrePresupuesto_Exception repetido = new NombrePresupuesto_Exception("Nombre del presupuesto repetido");
            repetido.setCampo(campo);
            throw repetido;
        }
        return true;
    }

    public static String registrarPresupuesto(Activity actividad) {
        JSONObject nuevo_presupuesto = new JSONObject();
        try {
            System.out.println("Entro en el registrar");
            nuevo_presupuesto.put("pr_nombre", nombrePresupuesto.getText());
            nuevo_presupuesto.put("pr_monto", montoPresupuesto.getText().toString());
            //nuevo_presupuesto.put("pr_usuarioid",ControlDatos.getUsuario().getIdusuario());
            if (unicoButton.isChecked()) {
                nuevo_presupuesto.put("pr_duracion", "0");
                nuevo_presupuesto.put("pr_clasificacion", unicoButton.getText());
            }
            if (recurrenciaButton.isChecked()) {
                nuevo_presupuesto.put("pr_duracion", recurrenciaPresupuesto.getText().toString());
                nuevo_presupuesto.put("pr_clasificacion", recurrenciaButton.getText());
            }

            //nuevo_presupuesto.put("usuariou_id",Presupuesto.);

            /*
            String categoria = categoriaPresupuesto.getSelectedItem().toString();
            String [] categoriaSplit = categoria.split("-");
            Integer categoriaid = Integer.parseInt(categoriaSplit[0]);
            nuevo_presupuesto.put("categoriaca_id",categoriaid.toString());
            */
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo3/registrarPresupuesto?datosPresupuesto=" + URLEncoder.encode(nuevo_presupuesto.toString()));
        new Recepcion(actividad).execute("GET");
        return Parametros.getRespuesta();
    }

    public static void vaciarCasillas() {
        nombrePresupuesto.setText("");
        montoPresupuesto.setText("");
        recurrenciaPresupuesto.setText("");
        unicoButton.isChecked();

    }

    public static int validacionVacio() {
        try {
            verificoVacio(nombrePresupuesto);
            verificoVacio(montoPresupuesto);
            if (recurrenciaButton.isChecked()) {
                verificoVacio(recurrenciaPresupuesto);
            }


        } catch (CampoVacio_Exception e) {
            e.getCampo().setError(e.getMessage());
            return 1;
        }


        /*
            Falta validar si  ya existe el nombre de usuario en el sistema.
         */

        return 0;
    }

    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {
        if (campo.getText().toString().isEmpty()) {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }
    }

    public static void volverInvisibleRecurrencia() {
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

    //METODOS PARA MODIFICAR EL PRESUPUESTO
    public static void obtenerPresupuesto(Activity actividad) {

        String nombrePresupuesto = "";
        System.out.println("POSICION: " + posicionLista);
        JSONObject json = null;
        if (tipoPresupuesto) {
            nombrePresupuesto = listaGanancias.get(posicionLista).get_nombre();
        } else {
            nombrePresupuesto = listaGastos.get(posicionLista).get_nombre();
        }
        nombrePresupuesto = nombrePresupuesto.replace(' ', '_');
        System.out.println(Parametros.respuesta);
        Parametros.setMetodo("Modulo3/ModificarPresupuesto?nombrePresupuesto=" + nombrePresupuesto);
        //Parametros.setMetodo("Modulo3/ModificarPresupuesto?nombrePresupuesto="+nombrePresupuesto+"&idUsuario="+ControlDatos.getUsuario().getIdusuario());

        new Recepcion(actividad).execute("GET");
        try {
            json = new JSONObject(Parametros.respuesta);
            presupuesto.set_categoria((String) json.get("IdCategoria"));
            presupuesto.set_nombre((String) json.get("Nombre"));
            presupuesto.set_monto(Float.parseFloat((String) json.get("Monto")));
            presupuesto.set_clasificacion((String) json.get("Clasificacion"));
            presupuesto.set_duracion(Integer.parseInt((String) json.get("Duracion")));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static void asignarValores() {

        nombrePresupuesto.setText(presupuesto.get_nombre());
        montoPresupuesto.setText(presupuesto.get_monto().toString());
        if (presupuesto.get_tipo().equals("Unico")) {

            unicoButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.INVISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.INVISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
        } else if (presupuesto.get_tipo().equals("Recurrente")) {
            recurrenciaButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.VISIBLE);       //SE COLOCA INVISIBLE EL TEXTVIEW
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.VISIBLE); //SE COLOCA INVISIBLE EL EDITTEXT
            recurrenciaPresupuesto.setText(presupuesto.get_duracion().toString());
        }

    }

    //METODOS PARA ELIMINAR EL PRESUPUESTO
    public static void eliminarPresupuestos(Activity actividad, boolean tipo) {
        Parametros.reset();
        String nombrePresupuesto = "";
        System.out.println("POSICION: " + posicionLista);
        if (tipo) {
            nombrePresupuesto = listaGanancias.get(posicionLista).get_nombre();
            ganancias = ganancias - listaGanancias.get(posicionLista).get_monto();
            total = ganancias - gastos;
            listaGanancias.remove(listaGanancias.get(posicionLista));
        } else {
            nombrePresupuesto = listaGastos.get(posicionLista).get_nombre();
            gastos = gastos - listaGastos.get(posicionLista).get_monto();
            total = ganancias - gastos;
            listaGastos.remove(listaGastos.get(posicionLista));
        }

        nombrePresupuesto = nombrePresupuesto.replace(' ', '_');
        asignarRecyclerView(recyclerList, tipo);
        asignarTotales();
        Parametros.setMetodo("Modulo3/EliminarPresupuesto?nombrePresupuesto=" + nombrePresupuesto);
        //Parametros.setMetodo("Modulo3/EliminarPresupuesto?nombrePresupuesto="+nombrePresupuesto+
        //                "&idUsuario="+ ControlDatos.getUsuario().getIdusuario());

        new Recepcion(actividad).execute("GET");

    }

    public static void cualquiercosa(Activity actividad, TextView cartel) {
        Parametros.setUrl("http://192.168.0.112:8080/FinUcabWebService/webresources/Modulo1/pruebaDB");
        new Recepcion(actividad).execute(Parametros.getUrl());
    }

    //METODOS PARA VISUALIZAR PRESUPUESTO
    public static void asignarRecyclerView(RecyclerView recycleList, Boolean tipo) {
        PresupuestoAdapter pAdapter;
        if (tipo) {
            pAdapter = new PresupuestoAdapter(listaGanancias);
        } else {
            pAdapter = new PresupuestoAdapter(listaGastos);
        }

        recycleList.setAdapter(pAdapter);
    }


    public static void visualizarPresupuestos(Activity actividad) {

        listaGanancias = new ArrayList<>();
        listaGastos = new ArrayList<>();
        ganancias = 0.0F;
        gastos = 0.0F;
        total = 0.0F;
        System.out.println("PRESUPUESTO_CONTROLLER: estoy en visualizar presupuesto");
        Parametros.setMetodo("Modulo3/ListaPresupuesto");
        System.out.println("El url es: "+Parametros.url);
        //Parametros.setMetodo("Modulo3/ListaPresupuesto?idUsuario="+ ControlDatos.getUsuario().getIdusuario());
        //new Recepcion(actividad).execute("GET");
        new Recepcion(actividad,interfaz).execute("GET");
        System.out.println(Parametros.respuesta);
        /*JSONObject jObject = null;
        try {
            JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());
            int count = mJsonArray.length();
            String strJson ="";
            for (int i = 0; i < count; i++) {   // iterate through jsonArray
                strJson = mJsonArray.getString(i);
                jObject = new JSONObject(strJson);
                //jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Presupuesto pre = new Presupuesto();
                pre.set_duracion(Integer.parseInt((String) jObject.get("Duracion")));
                pre.set_clasificacion((String) jObject.get("Clasificacion"));
                pre.set_monto(Float.parseFloat((String) jObject.get("Monto")));
                pre.set_categoria((String) jObject.get("Categoria"));
                pre.set_nombre((String) jObject.get("Nombre"));
                if ((jObject.get("Tipo")).equals("t")) {
                    listaGanancias.add(pre);
                    ganancias = ganancias + pre.get_monto();
                } else {
                    listaGastos.add(pre);
                    gastos = gastos + pre.get_monto();
                }
            }
            total = ganancias - gastos;

        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
    }


    public static void asignarTotales() {
        gananciaTextView.setText(ganancias.toString());
        gastoTextView.setText(gastos.toString());
        totalTextView.setText(total.toString());
    }


}
