package com.ucab.fin.finucab.domain;

import android.app.Activity;

import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase se encarga de manejar los metodos agregar, modificar, eliminar y visualizar
 * los cuales se comunicaran con el WebService y llenaran las vistas que se le mostraran al
 * usuario
 */

public class Manejador_Categoria {

    private Activity actividad;
    private ArrayList<Categoria> categorias; //creacion de un array de tipo categoria
    private ResponseWebServiceInterface intefaz; //creacion de una interfaz para funcionalidades de vistas

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Manejador_Categoria(Activity actividad, ResponseWebServiceInterface intefaz){

        this.actividad = actividad;
        this.intefaz = intefaz;
    }

    /**Creacion del metodo agregar Categoria
     * conexion con WebService por medio de Json
     *
     * @param categoria
     */
    public void agregarCategoria( Categoria categoria) {

        try {

            int idUsuario = 1;
            JSONObject nueva_categoria = new JSONObject();
            nueva_categoria.put("c_nombre",categoria.getNombre());
            nueva_categoria.put("c_descripcion",categoria.getDescripcion());
            nueva_categoria.put("c_estado",categoria.isEstaHabilitado());
            nueva_categoria.put("c_ingreso",categoria.isIngreso());
            nueva_categoria.put("c_usuario",idUsuario);

            Parametros.reset();
            Parametros.setMetodo("Modulo4/registrarCategoria?datosCategoria="+ URLEncoder.encode(nueva_categoria.toString()));
            new Recepcion(actividad,intefaz).execute("GET");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creacion del metodo modificar Categoria
     * conexion con WebService por medio de Json
     *
     * @param categoria
     */

    public void modificarCategoria( Categoria categoria) {
        try {

            JSONObject nueva_categoria = new JSONObject();
            nueva_categoria.put("c_id",categoria.getIdcategoria());
            nueva_categoria.put("c_nombre",categoria.getNombre());
            nueva_categoria.put("c_descripcion",categoria.getDescripcion());
            nueva_categoria.put("c_estado",categoria.isEstaHabilitado());
            nueva_categoria.put("c_ingreso",categoria.isIngreso());

            Parametros.reset();
            Parametros.setMetodo("Modulo4/modificarCategoria?datosCategoria="+ URLEncoder.encode(nueva_categoria.toString()));
            new Recepcion(actividad,intefaz).execute("GET");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**Creacion del metodo eliminar Categoria
     * conexion con WebService por medio de Json
     *
     * @param id
     */
    public void borrarCategoria( int id) {

        Parametros.reset();
        Parametros.setMetodo("Modulo4/eliminarCategoria?datosCategoria="+ String.valueOf(id));
        new Recepcion(actividad,intefaz).execute("GET");

    }

    /**Creacion del metodo Mostrar lista de Categoria
     * conexion con WebService por medio de Json
     *
     */
    public void obtenerTodasCategorias() {

        int idUsuario = 1;
        Parametros.reset();
        Parametros.setMetodo("Modulo4/visualizarCategoria?datosCategoria="+ String.valueOf(idUsuario) );
        new Recepcion(actividad,intefaz).execute("GET");

    }



    /**Creacion del metodo obtener Categoria
     la cual obtendra el id de una categoria dado un id, este metodo sera usado
     por el modulo de Pagos
     *
     * @param id
     * @return la.get(i)
     */
    public Categoria obtenerCategoria( int id) {

        ArrayList<Categoria> la = getCategorias();

        for(int i=0 ; i< la.size(); i++) {

            if( la.get(i).getIdcategoria() == id ){
                return  la.get(i);
            }

        }

        return  null;
    }



    //Creacion de un metodo que llenara una lista de categorias para probar los fragments
    public void defaultList() {

        //Categoria_Controller.obtenerTodasCategorias(parentActivity);
        ArrayList<Categoria> listTest = new ArrayList<Categoria>(); //creacion de un array de categorias

        listTest.add(new Categoria(0,"Comida","Almuerzos en la uni",true, false));
        listTest.add(new Categoria(1,"Tranporte","Camino a la uni",true, false));
        listTest.add(new Categoria(2,"Chupetas","Venta de cupetas",true, true));
        listTest.add(new Categoria(3,"Pintura","Putura para la casa",false, false));
        listTest.add(new Categoria(4,"Deporte","Deporte en la uni",false, false));
        listTest.add(new Categoria(5,"Materiales","materiales de la uni",false, false));
        listTest.add(new Categoria(6,"Musica","Pago de servicios en la uni",true, false));
        listTest.add(new Categoria(7,"Cable","Cable de la casa",false, false));

        categorias = listTest;

    }


}
