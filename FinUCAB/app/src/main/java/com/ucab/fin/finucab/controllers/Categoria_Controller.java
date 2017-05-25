package com.ucab.fin.finucab.controllers;

import android.app.Activity;
import android.widget.EditText;

import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

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

    public static Manejador_Categoria manejador;

    /**
     *
     * @param actividad
     * @param interfaz
     */

    public static void initManejador(Activity actividad, ResponseWebServiceInterface interfaz){

        manejador = new Manejador_Categoria(actividad, interfaz);

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

    /**
     *  Metodo encargado de habilitar  la categoria seleccionada
     * @param id
     * @param esHabilitar
     */

    public static void HabilitarCategoria(int id, boolean esHabilitar){

        Categoria categoria = manejador.obtenerCategoria(id);
        categoria.setEstaHabilitado(esHabilitar);

        manejador.modificarCategoria(categoria);

    }


    /**
     * Metodo encargado de llamar a modificar  la categoria seleccionada
     * @param categoria
     */

    public static void modificarCategoria(Categoria categoria){

        manejador.modificarCategoria(categoria);

    }

    /**
     *  Metodo encargado de llamar a agregar categoria
     * @param categoria
     */
    public static void registrarCategoria(Categoria categoria){

        manejador.agregarCategoria(categoria);

    }

    /**
     * Metodo encargado de llamar a agregar categoria
     * @param id
     */
    public static void borrarCategoria(int id){

        manejador.borrarCategoria(id);

    }

    /**
     * Metodo encargado de llamar a obtener las categorias
     */
    public static void obtenerTodasCategorias(){

        manejador.obtenerTodasCategorias();

    }


}




