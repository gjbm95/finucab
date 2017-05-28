package com.ucab.fin.finucab;

/**
 * Created by Juan on 25-05-2017.
 */


import android.widget.EditText;

import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.fragment.AgregarCategoria_Fragment;
import com.ucab.fin.finucab.fragment.ListaCategorias_Fragment;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
    public void CampoAgregarCategoria_isCorrect() throws Exception {
        AgregarCategoria_Fragment fragment = new AgregarCategoria_Fragment();
        startFragment( fragment );


        //Situacion 1: si agarra que esta vacio aceptado es true y assertTrue da true
        EditText campo = AgregarCategoria_Fragment.AddDescripcionEditText = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        AgregarCategoria_Fragment.AddDescripcionEditText.setText("");
        EditText campo2 = AgregarCategoria_Fragment.AddDescripcionEditText = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        AgregarCategoria_Fragment.AddDescripcionEditText.setText("Gastos de comida en la universidad");

        boolean aceptado = false ;
        try{
            Categoria_Controller.verificoVacio(campo);
        }catch(CampoVacio_Exception e){
            aceptado = true;
        }
        assertTrue(aceptado);
        //Situacion 2: si aceptado es false y la prueba da true porque no esta vacia devuelvo en asserFalse true

        aceptado = false;
        try{
            Categoria_Controller.verificoVacio(campo2);
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
    public void CampoDescripcionVacio_isCorrect() throws Exception {
        AgregarCategoria_Fragment fragment = new AgregarCategoria_Fragment();
        startFragment( fragment );


        //Situacion 1: si agarra que esta vacio aceptado es true y assertTrue da true
       EditText campo = AgregarCategoria_Fragment.AgregarcategoriaEditText = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        AgregarCategoria_Fragment.AgregarcategoriaEditText.setText("");
        EditText campo2 = AgregarCategoria_Fragment.AgregarcategoriaEditText = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        AgregarCategoria_Fragment.AgregarcategoriaEditText.setText("Categoria");
         ;
        boolean aceptado = false ;
        try{
            Categoria_Controller.verificoVacio(campo);
        }catch(CampoVacio_Exception e){
            aceptado = true;
        }
        assertTrue(aceptado);
        //Situacion 2: si aceptado es false y la prueba da true porque no esta vacia devuelvo en asserFalse true

        aceptado = false;
        try{
            Categoria_Controller.verificoVacio(campo2);
        }
        catch(CampoVacio_Exception e){
            aceptado = true;
        }
        assertFalse(aceptado);
    }


    @Test
    public void HabilitarEventoSwitch_isCorrect () throws Exception{
        ListaCategorias_Fragment fragment = new ListaCategorias_Fragment();
        startFragment( fragment );

        boolean prueba = false ;
        try{
            Categoria_Controller.setHabilitarEventoSwitch(prueba);
        }catch(Exception e){
            prueba = true;
        }
        assertTrue(prueba);

        prueba = false;
        try{
            Categoria_Controller.setHabilitarEventoSwitch(prueba);
        }catch(Exception e){
            prueba = true;
        }
        assertTrue(prueba);



    }

    @Test
    public void setListaCategorias_isCorrect () throws Exception {
        ListaCategorias_Fragment fragment = new ListaCategorias_Fragment();
        startFragment( fragment );

        Categoria categoria = new Categoria();
        Categoria pruebas = new Categoria();



        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        categoria.setIdcategoria(1);
        categoria.setNombre("Comida");
        categoria.setDescripcion("Gastos en la universidad");
        categoria.setEstaHabilitado(true);
        categoria.setEsIngreso(true);

        categorias.add(categoria);


        ArrayList<Categoria> prueba = new ArrayList<Categoria>();

        Categoria_Controller.setListaCategorias(categorias);

        assertNotEquals(prueba,categorias);

        ArrayList<Categoria> prueba1 = new ArrayList<Categoria>();

        pruebas.setIdcategoria(1);
        pruebas.setNombre("Comida");
        pruebas.setDescripcion("Gastos en la universidad");
        pruebas.setEstaHabilitado(true);
        pruebas.setEsIngreso(true);
        prueba1.add(pruebas);


        categorias.add(categoria);
        categoria.setNombre("Comida");
        categoria.setDescripcion("Gastos en la universidad");
        categoria.setEstaHabilitado(true);
        categoria.setEsIngreso(true);
        categorias.add(categoria);

        assertEquals(categorias,prueba1);





    }


    }




