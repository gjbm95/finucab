package com.ucab.fin.finucab;

/**
 * Created by Juan on 25-05-2017.
 */


import android.widget.EditText;

import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.fragment.AgregarCategoria_Fragment;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase se encarga de realizar pruebas unitarias a la Controladora de Gestion de Categorias
 */
public class GestionCategoriasTest {

    /**
     * realizo prueba a campo agregar categoria vacio
     *
     * @throws Exception
     */
    @Test
    public void CampoCategoriaVacio() throws Exception {
        AgregarCategoria_Fragment fragment = new AgregarCategoria_Fragment();
        startFragment( fragment );

        //Situacion 1: si agarra que esta vacio aceptado es true y assertTrue da true
        Categoria_Controller.escribirCategoria = (EditText)fragment.getView().findViewById(R.id.AgregarcategoriaEditText);
        Categoria_Controller.escribirCategoria.setText("");
        boolean aceptado = false ;
        try{
            Categoria_Controller.verificoVacio(Categoria_Controller.escribirCategoria);
        }catch(CampoVacio_Exception e){
            aceptado = true;
        }
        assertTrue(aceptado);
        //Situacion 2: si aceptado es false y la prueba da true porque no esta vacia devuelvo en asserFalse true
        Categoria_Controller.escribirCategoria.setText("Descripcion");
        aceptado = false;
        try{
            Categoria_Controller.verificoVacio(Categoria_Controller.escribirCategoria);
        }
            catch(CampoVacio_Exception e){
            aceptado = true;
        }
        assertFalse(aceptado);
    }
    /**
     * realizo prueba a campo agregar categoria vacio
     *
     * @throws Exception
     */
    @Test
    public void CampoDescripcionVacio() throws Exception {
        AgregarCategoria_Fragment fragment = new AgregarCategoria_Fragment();
        startFragment( fragment );

        //Situacion 1: si agarra que esta vacio aceptado es true y assertTrue da true
        Categoria_Controller.escribirDescripcion = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        Categoria_Controller.escribirDescripcion.setText("");
        boolean aceptado = false ;
        try{
            Categoria_Controller.verificoVacio(Categoria_Controller.escribirDescripcion);
        }catch(CampoVacio_Exception e){
            aceptado = true;
        }
        assertTrue(aceptado);
        //Situacion 2: si aceptado es false y la prueba da true porque no esta vacia devuelvo en asserFalse true
        Categoria_Controller.escribirDescripcion.setText("Descripcion");
        aceptado = false;
        try{
            Categoria_Controller.verificoVacio(Categoria_Controller.escribirDescripcion);
        }
        catch(CampoVacio_Exception e){
            aceptado = true;
        }
        assertFalse(aceptado);
    }

    @Test
    public void AgregarCategoriaVaidacion() throws Exception{
        


    }



}