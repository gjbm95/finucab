package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
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

    public static Categoria categoria; //Creacion de una variable categoria de tipo Categoria
    public static EditText escribirCategoria; //EditText que contiene la categoria
    public static EditText escribirDescripcion; //EditText que contiene la descripcion de la categoria

    private static Manejador_Categoria manejador;
    private static int  casoRequest = -1;
    private static boolean habilitarEventoSwitch = false ;

    /**
     *
     * @param actividad
     * @param interfaz
     */

    public static void initManejador(Activity actividad, ResponseWebServiceInterface interfaz){

        if ( manejador == null ||  manejador.getIntefaz() != interfaz ) {

            manejador = new Manejador_Categoria(actividad, interfaz);

        }

    }

    /**
     * Colocar actul lista de categoria en el manejador
     */
    public static void setHabilitarEventoSwitch(Boolean activado){

        habilitarEventoSwitch = activado;
    }


    /**
     * Colocar actul lista de categoria en el manejador
     */
    public static void setListaCategorias(ArrayList<Categoria> categorias){

        manejador.setCategorias(categorias);
    }

    /**
     * Colocar actul lista de categoria en el manejador
     * @return Lista de categoria cargada
     */
    public static ArrayList<Categoria> getListaCategorias(){

        return manejador.getCategorias();
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
    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {
        if (campo.getText().toString().isEmpty())
        {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }

    }




    /*------------------------------------- REQUEST ----------------------------------------*/

    /**
     *  Metodo encargado de habilitar  la categoria seleccionada
     * @param categoria
     * @param esHabilitar
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
     * @param categoria
     */
    public static void modificarCategoria(Categoria categoria){

        casoRequest = 2;
        manejador.modificarCategoria(categoria);

    }

    /**
     *  Metodo encargado de llamar a agregar categoria
     * @param categoria
     */
    public static void registrarCategoria(Categoria categoria){

        casoRequest = 1;
        manejador.agregarCategoria(categoria);

    }

    /**
     * Metodo encargado de llamar a agregar categoria
     * @param posicion
     */
    public static void borrarCategoria(int posicion){

        casoRequest = 3;
        int id = manejador.getCategorias().get(posicion).getIdcategoria();
        manejador.borrarCategoria(id);

    }

    /**
     * Metodo encargado de llamar a obtener las categorias
     */
    public static void obtenerTodasCategorias(){

        casoRequest = 0;
        manejador.obtenerTodasCategorias();

    }


}




