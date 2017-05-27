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
    private ArrayList<Categoria> ultimasCategoriasObtenidas; //creacion de un array de tipo categoria
    private ResponseWebServiceInterface intefaz; //creacion de una interfaz para funcionalidades de vistas

    /*------------------------------------- CONSTRUCTORES ----------------------------------------*/

    public Manejador_Categoria(Activity actividad, ResponseWebServiceInterface intefaz){

        this.actividad = actividad;
        this.intefaz = intefaz;
    }
    
    public Manejador_Categoria(Activity actividad){

        this.actividad = actividad;
        this.intefaz = null;
    }

    /*------------------------------------- GETTER Y SETTER ----------------------------------------*/

    public ResponseWebServiceInterface getIntefaz() {
        return intefaz;
    }

    public ArrayList<Categoria> getUltimasCategoriasObtenidas() {
        return ultimasCategoriasObtenidas;
    } // Obtener ultima lista recuperada
    public void setUltimasCategoriasObtenidas(ArrayList<Categoria> categorias) {
        this.ultimasCategoriasObtenidas = categorias;
    } // Asignar ultima lista recuperada



    /*------------------------------------- REQUEST ----------------------------------------*/

    /**Creacion del metodo agregar Categoria
     * conexion con WebService por medio de Json
     *
     * @param categoria Categoria a registrar
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
     * @param categoria Categoria a modificar
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
     * @param id Id de la categoria a borrar
     */
    public void borrarCategoria( int id) {

        Parametros.reset();
        Parametros.setMetodo("Modulo4/eliminarCategoria?datosCategoria="+ String.valueOf(id));
        new Recepcion(actividad,intefaz).execute("GET");

    }

    /**Creacion del metodo Mostrar lista de Categoria
     * conexion con WebService por medio de Json
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     *
     */
    public void obtenerTodasCategorias(boolean showStatus) {

        int idUsuario = 1;
        Parametros.reset();
        Parametros.setMetodo("Modulo4/visualizarCategoria?datosCategoria="+ String.valueOf(idUsuario) );
        new Recepcion(actividad,intefaz,showStatus).execute("GET");

    }

    /**
     * obtener informacion de la categoria con el id.
     *
     * @param id Id dela categoria a obtener
     * @return la.get(i)
     */
    public void obtenerCategoria( int id) {

        Parametros.reset();
        Parametros.setMetodo("Modulo4/buscarCategoria?datosCategoria="+ String.valueOf(id));
        new Recepcion(actividad,intefaz).execute("GET");

    }

    /**
     * Obtener informacion de la categoria con el id.
     * Este metodo funcionra solo si anteriormente ya se ha llamada el metodo obtenerTodasCategorias()
     *
     * @param id Id dela categoria a obtener
     * @return la.get(i)
     */
    public Categoria obtenerCategoriaEnUltimaBusqueda( int id) {

        for(int i=0 ; i< ultimasCategoriasObtenidas.size(); i++) {

            if( ultimasCategoriasObtenidas.get(i).getIdcategoria() == id ){
                return  ultimasCategoriasObtenidas.get(i);
            }

        }

        return  null;
    }

    /**
     * Creacion de un metodo que llenara una lista de categorias para probar los fragments
     */
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

        ultimasCategoriasObtenidas = listTest;

    }


}
