package com.ucab.fin.finucab.domain;

import android.app.Activity;
import android.util.Log;

import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramon on 24-May-17.
 */

public class Manejador_Categoria {

    private Activity actividad;
    private ArrayList<Categoria> categorias;

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Manejador_Categoria(Activity actividad){

        this.actividad = actividad;
    }

    public void agregarCategoria( Categoria categoria) {

        JSONObject nueva_categoria = new JSONObject();
        try {
            nueva_categoria.put("c_nombre",categoria.getNombre());
            nueva_categoria.put("c_descripcion",categoria.getDescripcion());
            nueva_categoria.put("c_estado",categoria.isEstaHabilitado());
            nueva_categoria.put("c_ingreso",categoria.isIngreso());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo4/registrarCategoria?datosCategoria="+ URLEncoder.encode(nueva_categoria.toString()));
        new Recepcion(actividad).execute("GET");

    }

    public void modificarCategoria( Categoria categoria) {
        JSONObject nueva_categoria = new JSONObject();
        try {
            nueva_categoria.put("c_id",categoria.getIdcategoria());
            nueva_categoria.put("c_nombre",categoria.getNombre());
            nueva_categoria.put("c_descripcion",categoria.getDescripcion());
            nueva_categoria.put("c_estado",categoria.isEstaHabilitado());
            nueva_categoria.put("c_ingreso",categoria.isIngreso());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Parametros.reset();
        Parametros.setMetodo("Modulo4/modificarCategoria?datosCategoria="+ URLEncoder.encode(nueva_categoria.toString()));
        new Recepcion(actividad).execute("GET");

    }

    public Categoria obtenerCategoria( int id) {

        ArrayList<Categoria> la = getCategorias();

        for(int i=0 ; i< la.size(); i++) {

            if( la.get(i).getIdcategoria() == id ){
                return  la.get(i);
            }

        }

        return  null;
    }

    public void borrarCategoria( int id) {

        Parametros.reset();
        Parametros.setMetodo("Modulo4/eliminarCategoria?datosCategoria="+ String.valueOf(id));
        new Recepcion(actividad).execute("GET");

    }

    public void obtenerTodasCategorias() {

        int idUsuario = 1;
        Parametros.setMetodo("Modulo4/visualizarCategoria?datosCategoria="+ String.valueOf(idUsuario) );
        new Recepcion(actividad).execute("GET");
        System.out.println(Parametros.respuesta);
    }

    public void defaultList() {

        //Categoria_Controller.obtenerTodasCategorias(parentActivity);
        ArrayList<Categoria> listTest = new ArrayList<Categoria>();

        listTest.add(new Categoria(0,"Comida","Almuerzos en la uni",true, false));
        listTest.add(new Categoria(1,"Tranporte","Camino a la uni",true, false));
        listTest.add(new Categoria(2,"Chupetas","Venta de cupetas",true, true));
        listTest.add(new Categoria(3,"Pintura","Putura par ala casa",false, false));
        listTest.add(new Categoria(4,"Deporte","Deporte en la uni",false, false));
        listTest.add(new Categoria(5,"Materiales","materiales de la uni",false, false));
        listTest.add(new Categoria(6,"Musica","Pago de servicios en la uni",true, false));
        listTest.add(new Categoria(7,"Cable","Cable dela casa",false, false));

        categorias = listTest;

    }


}
