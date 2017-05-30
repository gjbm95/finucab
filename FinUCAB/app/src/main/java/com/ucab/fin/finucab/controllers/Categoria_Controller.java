package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.fragment.ListaCategorias_Fragment;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import java.util.ArrayList;

/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase se encarga de gestionar y llevar a cabo las llamadas a los distintos metodos
 * . Y de inicializar
 * parametros de los botones para la aplicacion.
 */

public class Categoria_Controller {

    public static Object fragment; //Fragment que se esta controlando

    private static Manejador_Categoria manejador;
    public static int  casoRequest = -1;
    public static boolean habilitarEventoSwitch = false ;
    EditText prueba;

    /**
     * Inicializar de ser necesario el manejador de data
     * @param actividad requerida para devolver la data (deprecated)
     * @param interfaz requerida para devolver la data
     */

    public static void initManejador(Activity actividad, ResponseWebServiceInterface interfaz){

        if ( manejador == null ||  manejador.getIntefaz() != interfaz ) {

            manejador = new Manejador_Categoria(actividad, interfaz);

        }

    }

    /**
     * Colocar actual lista de categoria en el manejador
     */
    public static void setHabilitarEventoSwitch(Boolean activado){

        habilitarEventoSwitch = activado;
    }


    /**
      * Colocar actual lista de categoria en el manejador
     * @param categorias
     */
    public static void setListaCategorias(ArrayList<Categoria> categorias){

        manejador.setUltimasCategoriasObtenidas(categorias);
    }

    /**
     * Colocar actual lista de categoria en el manejador
     * @return Lista de categoria cargada
     */
    public static ArrayList<Categoria> getListaCategorias(){

        return manejador.getUltimasCategoriasObtenidas();
    }

    /**
     * Resetea el caso del request al WebService
     */
    public static void resetCasoRequest(){
        casoRequest = -1;
    }

    /**
     * Obtener caso del request que se esta realizando
     * @return
     */
    public static int getCasoRequest(){
        return casoRequest;
    }

    /**Realizo la validacion para verificar que el campo este vacio:
     * @param campo
     * @throws CampoVacio_Exception
     */
    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {  //ya hice la prueba
        if (campo.getText().toString().isEmpty() )
        {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }

    }

    public static void redireccionarAgregarCategoria(Categoria categoria){

        if (fragment != null ){
            ListaCategorias_Fragment listaCategorias_fragment = (ListaCategorias_Fragment) fragment;
            listaCategorias_fragment.redireccionarAgregarCategoria(categoria);
        }


    }




    /*------------------------------------- REQUEST ----------------------------------------*/

    /**
     *  Metodo encargado de habilitar o desabilitar la categoria seleccionada
     * @param categoria Categoria a habilitar o desabilitar
     * @param esHabilitar accion a realizar, habilitar o deshabilitar
     */
    public static void HabilitarCategoria(Categoria categoria, boolean esHabilitar){

        if (habilitarEventoSwitch) {
            casoRequest = 2;
            categoria.setEstaHabilitado(esHabilitar);
            manejador.modificarCategoria(categoria);
        }
    }


    /**
     * Metodo encargado de llamar a modificar  la categoria seleccionada
     * @param categoria Categoria a modificar
     */
    public static void modificarCategoria(Categoria categoria){

        casoRequest = 2;
        manejador.modificarCategoria(categoria);

    }

    /**
     *  Metodo encargado de llamar a agregar categoria
     * @param categoria Categoria a registrar
     */
    public static void registrarCategoria(Categoria categoria){

        casoRequest = 1;
        manejador.agregarCategoria(categoria);

    }

    /**
     * Metodo encargado de llamar a agregar categoria
     * @param posicion posicion seleccionada de la lista
     */
    public static void borrarCategoria(int posicion){

        casoRequest = 3;
        int id = manejador.getUltimasCategoriasObtenidas().get(posicion).getIdcategoria();
        manejador.borrarCategoria(id);

    }

    /**
     * Metodo encargado de llamar a obtener las categorias
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     */
    public static void obtenerTodasCategorias(boolean showStatus){

        casoRequest = 0;
        manejador.obtenerTodasCategorias(showStatus);

    }


}




