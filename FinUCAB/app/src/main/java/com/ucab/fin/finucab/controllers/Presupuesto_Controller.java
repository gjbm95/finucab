package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.CategoriaSpinner;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.NombrePresupuesto_Exception;
import com.ucab.fin.finucab.fragment.AgregarPresupuesto_fragment;
import com.ucab.fin.finucab.fragment.CategoriaAdapter;
import com.ucab.fin.finucab.fragment.PresupuestoAdapter;
import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.security.AccessController.getContext;

/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase se encarga del manejo de los datos entrantes y salientes de la aplicacion que va
 *relacionado con los presupuestos
 **/
public class Presupuesto_Controller {

    public static Presupuesto presupuesto;
    public static EditText nombrePresupuesto;
    public static EditText montoPresupuesto;
    public static EditText recurrenciaPresupuesto;
    public static CompoundButton recurrenciaButton, unicoButton;
    public static Spinner categoriaPresupuesto;
    public static TextView recurrenciaTextView;
    public static TextView totalTextView, gananciaTextView, gastoTextView;
    public static Double ganancias, gastos, total;
    public static Integer posicionLista;
    public static RecyclerView recyclerList;
    public static ArrayList<Presupuesto> listaGanancias = new ArrayList<>();
    public static ArrayList<Presupuesto> listaGastos = new ArrayList<>();
    public static boolean tipoPresupuesto;
    public static ResponseWebServiceInterface interfaz;
    public static Button agregarButton;
    public static LinkedList<CategoriaSpinner> categorias;

    /**
     * Método que se encarga de obtener en el web service las categorías asociadas a un usuario
     * @param actividad
     */
    public static void obtenerSpinner(Activity actividad) {

        Manejador_Categoria manejador_categoria = new Manejador_Categoria(actividad,interfaz);
        manejador_categoria.obtenerTodasCategorias(true);

    }

    /**
     * Metodo que se encarga de asignar las categorías asociadas a un usuario al spinner
     * @param actividad
     */
    public static void asignarSpinner(Activity actividad) {
        try {
            JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());
            LinkedList category = new LinkedList();


            for (int i = 0; i < mJsonArray.length(); i++) {
                String strJson = mJsonArray.getString(i);
                JSONObject jsonObject = new JSONObject(strJson);

                category.add(new CategoriaSpinner(jsonObject.getInt("ca_id"), jsonObject.getString("ca_nombre")));

            }
            ArrayAdapter spinner_adapter = new ArrayAdapter(actividad, android.R.layout
                    .simple_spinner_dropdown_item, category);
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            if(category.isEmpty()) {
                agregarButton.setEnabled(false);
                Toast.makeText(actividad, "Debe agregar Categorias primero", Toast.LENGTH_LONG).show();
            }
            categoriaPresupuesto.setAdapter(spinner_adapter);
            Parametros.reset();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que se encarga de verificar en la base de datos si el nombre de un presupuesto está repetido
     * @param actividad
     * @param campo
     * @return
     */

    public static boolean verificoNombre(Activity actividad, EditText campo){

        Parametros.setMetodo("Modulo3/VerificarNombre?nombrePresupuesto="+campo.getText().toString().replace(' ','_'));
        new Recepcion(actividad,interfaz).execute("GET");

        return true;
    }

    /**
     * Método que se encarga de validar si el nombre del presupuesto esta repetido, si es así, lanza una excepcion
     * @param campo
     * @param esAgregar
     * @return
     * @throws NombrePresupuesto_Exception
     */

    public static boolean DevolverValidacion(EditText campo,boolean esAgregar) throws NombrePresupuesto_Exception {
        String nombre="";
        nombre= Parametros.getRespuesta();
        if (esAgregar) {
            if (nombre.equals("1"))
            {
                NombrePresupuesto_Exception repetido = new NombrePresupuesto_Exception("Nombre del presupuesto repetido");
                repetido.setCampo(campo);
                throw repetido;
            }
        }
        if(!esAgregar){
            if((!presupuesto.get_nombre().equals(campo.getText().toString()))){
                if (nombre.equals("1"))
                {
                    NombrePresupuesto_Exception repetido = new NombrePresupuesto_Exception("Nombre del presupuesto repetido");
                    repetido.setCampo(campo);
                    throw repetido;
                }
            }
        }

        return true;
    }

    /**
     * Este método se encarga de recoger los datos de la aplicación y enviarlos al web service
     * para ser almacenados en la base de datos
     * @param actividad
     * @return
     */

    public static String registrarPresupuesto(Activity actividad) {
        JSONObject nuevo_presupuesto = new JSONObject();
        try {
            nuevo_presupuesto.put("pr_nombre", nombrePresupuesto.getText());
            nuevo_presupuesto.put("pr_monto", montoPresupuesto.getText().toString());
            if (unicoButton.isChecked()) {
                nuevo_presupuesto.put("pr_duracion", 0);
                nuevo_presupuesto.put("pr_clasificacion", "Unico");
            }
            if (recurrenciaButton.isChecked()) {
                nuevo_presupuesto.put("pr_duracion", Integer.parseInt(recurrenciaPresupuesto.getText().toString()));
                nuevo_presupuesto.put("pr_clasificacion", "Recurrente");
            }
            CategoriaSpinner categoria = (CategoriaSpinner) categoriaPresupuesto.getSelectedItem();
            //String [] categoriaSplit = categoria.split("-");
            String categoriaid = String.valueOf(categoria.getId());
            nuevo_presupuesto.put("categoriaca_id",categoriaid+"-"+categoria.toString());
            nuevo_presupuesto.put("pr_usuarioid",ControlDatos.getUsuario().getIdusuario());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.setMetodo("Modulo3/registrarPresupuesto?datosPresupuesto=" + URLEncoder.encode
                (nuevo_presupuesto.toString()));
        new Recepcion(actividad,interfaz).execute("GET");
        return Parametros.getRespuesta();
    }

    /**
     * Este metodo se encarga de hacer las llamadas para verificar por cada atributo si se encuentra vacio
     * @param actividad
     * @return
     */
    public static int validacionVacio(Activity actividad) {
        try {
            verificoVacio(nombrePresupuesto);
            verificoVacio(montoPresupuesto);
            if (recurrenciaButton.isChecked()) {
                verificoVacio(recurrenciaPresupuesto);
            }
            verificoNombre(actividad,nombrePresupuesto);


        } catch (CampoVacio_Exception e) {
            e.getCampo().setError(e.getMessage());
            return 1;
        }

        return 0;
    }

    /**
     * Este metodo se encarga de verificar si un atributo se encuentra vacio
     * @param campo
     * @throws CampoVacio_Exception
     */
    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {
        if (campo.getText().toString().isEmpty()) {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }
    }

    /**
     * Este metodo se encarga de volver el TextView y el EditText de la recurrencia invisibles
     */
    public static void volverInvisibleRecurrencia() {
        recurrenciaTextView.setVisibility(recurrenciaTextView.INVISIBLE);
        recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.INVISIBLE);
    }


    /**
     * Este método se encarga de conseguir desde el web service el presupuesto seleccionado
     * para obtener sus datos
     * @param actividad
     */
    public static void obtenerPresupuesto(Activity actividad) {

        int idPresupuesto = 0;
        System.out.println("POSICION: " + posicionLista);

        if (tipoPresupuesto) {
            idPresupuesto = listaGanancias.get(posicionLista).get_id();
        } else {
            idPresupuesto = listaGanancias.get(posicionLista).get_id();
        }
        System.out.println(Parametros.getRespuesta());
        Parametros.setMetodo("Modulo3/ObtenerPresupuesto?idPresupuesto=" + idPresupuesto);
        new Recepcion(actividad,interfaz).execute("GET");


    }

    /**
     * Este metodo se encarga de llenar el fragment de modificar con los datos del
     * presupuesto obtenido
     */
    public static void asignarValores() {

        nombrePresupuesto.setText(presupuesto.get_nombre());
        montoPresupuesto.setText(String.format("%.2f", presupuesto.get_monto()));
        if (presupuesto.get_clasificacion().equals("Unico")) {

            unicoButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.INVISIBLE);
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.INVISIBLE);
        } else if (presupuesto.get_clasificacion().equals("Recurrente")) {
            recurrenciaButton.setChecked(true);
            recurrenciaTextView.setVisibility(recurrenciaTextView.VISIBLE);
            recurrenciaPresupuesto.setVisibility(recurrenciaPresupuesto.VISIBLE);
            recurrenciaPresupuesto.setText(presupuesto.get_duracion().toString());
        }

    }

    /**
     * Se encarga de enviar los datos al web service para actualizarlos en la base de datos
     * @param actividad
     */
    public static void modificarPresupuesto(Activity actividad){
        JSONObject nuevo_presupuesto = new JSONObject();
        try {
            nuevo_presupuesto.put("pr_id", presupuesto.get_id());
            nuevo_presupuesto.put("pr_nombre", nombrePresupuesto.getText());
            nuevo_presupuesto.put("pr_monto", montoPresupuesto.getText().toString());
            if (unicoButton.isChecked()) {
                nuevo_presupuesto.put("pr_duracion", 0);
                nuevo_presupuesto.put("pr_clasificacion", "Unico");
            }
            if (recurrenciaButton.isChecked()) {
                nuevo_presupuesto.put("pr_duracion", Integer.parseInt(recurrenciaPresupuesto.getText().toString()));
                nuevo_presupuesto.put("pr_clasificacion", "Recurrente");
            }
            CategoriaSpinner categoria = (CategoriaSpinner) categoriaPresupuesto.getSelectedItem();
            /*String categoria = categoriaPresupuesto.getSelectedItem().toString();
            String [] categoriaSplit = categoria.split("-");*/
            String categoriaid = String.valueOf(categoria.getId());
            System.out.println("La categoria es: "+categoriaid);
            nuevo_presupuesto.put("categoriaca_id",categoriaid+"-"+categoria.toString());
            nuevo_presupuesto.put("pr_usuarioid",ControlDatos.getUsuario().getIdusuario());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.setMetodo("Modulo3/ModificarPresupuesto?datosPresupuesto=" + URLEncoder.encode
                (nuevo_presupuesto.toString()));
        new Recepcion(actividad,interfaz).execute("GET");


    }

    /**
     * Encargado de enviar los datos del presupuesto al web service para así eliminar el presupuesto
     * Actualiza la lista del total
     * @param actividad
     * @param tipo
     */
    public static void eliminarPresupuestos(Activity actividad, boolean tipo) {
        Parametros.reset();
        int idPresupuesto = 0;
        System.out.println("POSICION: " + posicionLista);
        if (tipo) {
            idPresupuesto = listaGanancias.get(posicionLista).get_id();
            ganancias = ganancias - listaGanancias.get(posicionLista).get_monto();
            total = ganancias - gastos;
            listaGanancias.remove(listaGanancias.get(posicionLista));
        } else {
            idPresupuesto = listaGastos.get(posicionLista).get_id();
            gastos = gastos - listaGastos.get(posicionLista).get_monto();
            total = ganancias - gastos;
            listaGastos.remove(listaGastos.get(posicionLista));
        }

        asignarRecyclerView(recyclerList, tipo);
        asignarTotales();
        Parametros.setMetodo("Modulo3/EliminarPresupuesto?idPresupuesto="+ idPresupuesto);
        new Recepcion(actividad,interfaz).execute("GET");
    }

    public static void cualquiercosa(Activity actividad, TextView cartel) {
        Parametros.setUrl("http://192.168.0.112:8080/FinUcabWebService/webresources/Modulo1/pruebaDB");
        new Recepcion(actividad).execute(Parametros.getUrl());
    }

    /**
     * Asigna a los recycler view las listas de los presupuestos dependiendo si es de ganancia o gastos
     * @param recycleList
     * @param tipo
     */
    public static void asignarRecyclerView(RecyclerView recycleList, boolean tipo) {
        PresupuestoAdapter pAdapter;
        if (tipo) {
            pAdapter = new PresupuestoAdapter(listaGanancias);
        } else {
            pAdapter = new PresupuestoAdapter(listaGastos);
        }

        recycleList.setAdapter(pAdapter);
    }

    /**
     * Hace la solicitud al web service la lista de presupuestos por usuario
     * @param actividad
     */
    public static void obtenerListaPresupuestos(Activity actividad) {
        listaGanancias = new ArrayList<>(); listaGastos = new ArrayList<>();
        ganancias = Double.valueOf(0); gastos = Double.valueOf(0); total = Double.valueOf(0);
        Parametros.setMetodo("Modulo3/ListaPresupuesto?idUsuario="+ControlDatos.getUsuario().getIdusuario());
        new Recepcion(actividad,interfaz).execute("GET");
    }

    /**
     * Se encarga de recibir la lista de presupuestos y las asigna a un ArrayList
     */
    public static void visualizarPresupuesto(){
        JSONArray mJsonArray = null;
        JSONObject jObject = null;

        ArrayList listaCategoria = new ArrayList<Categoria>();
        try {
            mJsonArray = new JSONArray(Parametros.getRespuesta());
            int count = mJsonArray.length();

            for (int i = 0; i < count; i++) {   // iterate through jsonArray
                jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                Presupuesto pre = new Presupuesto();
                pre.set_id(jObject.getInt("Id"));
                pre.set_duracion(jObject.getInt("Duracion"));
                pre.set_clasificacion((String) jObject.get("Clasificacion"));
                pre.set_monto(Double.parseDouble(jObject.getString("Monto")));
                pre.set_categoria((String) jObject.get("Categoria"));
                pre.set_nombre((String) jObject.get("Nombre"));
                if ((jObject.get("Tipo")).equals("true")) {
                    Presupuesto_Controller.listaGanancias.add(pre);
                    Presupuesto_Controller.ganancias = Presupuesto_Controller.ganancias + pre.get_monto();
                } else {
                    Presupuesto_Controller.listaGastos.add(pre);
                    Presupuesto_Controller.gastos = Presupuesto_Controller.gastos + pre.get_monto();
                }
            }
            Presupuesto_Controller.total = Presupuesto_Controller.ganancias - Presupuesto_Controller.gastos;


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Se encarga de asignar la suma de los presupuestos para el fragment de Total
     */
    public static void asignarTotales() {
        gananciaTextView.setText(String.format("%.2f",ganancias));
        gastoTextView.setText(String.format("%.2f",gastos));
        totalTextView.setText(String.format("%.2f",total));
    }

    /**
     * Meotodo que se encarga de mostrar un mensaje de error.
     * @param mensaje
     */
    public static void mensajeError(Activity actividad,String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(actividad);
        alertDialogBuilder.setMessage(mensaje);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
