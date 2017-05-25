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
 * Esta clase se encarga de gestionar la actividad de los botones que se encuentran en nuestras
 * diferentes vistas
 * . Y de inicializar
 * parametros de los botones para la aplicacion.
 */

public class Categoria_Controller {

    public static Categoria categoria; //Creacion de una variable categoria de tipo Categoria
    public static EditText escribirCategoria; //EditText que contiene la categoria
    public static EditText escribirDescripcion; //EditText que contiene la descripcion de la categoria

    public static Manejador_Categoria manejador;

    public static void initManejador(Activity actividad, ResponseWebServiceInterface interfaz){

        manejador = new Manejador_Categoria(actividad, interfaz);

    }

    /**Realizo la validacion para verificar que el campo este vacio:
     *
     *
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

    public static void HabilitarCategoria(int id, boolean esHabilitar){

        Categoria categoria = manejador.obtenerCategoria(id);
        categoria.setEstaHabilitado(esHabilitar);

        manejador.modificarCategoria(categoria);

    }

    public static void modificarCategoria(Categoria categoria){

        manejador.modificarCategoria(categoria);

    }

    //METODO PARA REGISTRAR UNA CATEGORIA
    public static void registrarCategoria(Categoria categoria){

        manejador.agregarCategoria(categoria);

    }

    public static void borrarCategoria(int id){

        manejador.borrarCategoria(id);

    }


    public static void obtenerTodasCategorias(){

        manejador.obtenerTodasCategorias();

    }


}




