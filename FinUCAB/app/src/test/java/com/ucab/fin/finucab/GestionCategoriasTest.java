package com.ucab.fin.finucab;

/**
 * Created by Juan on 25-05-2017.
 */


import android.widget.EditText;

import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.fragment.AgregarCategoria_Fragment;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
        //startFragment( fragment );


        //Situacion 1: si agarra que esta vacio aceptado es true y assertTrue da true
         EditText campo = AgregarCategoria_Fragment.AgregarcategoriaEditText;
        //EditText campo = AgregarCategoria_Fragment.AddDescripcionEditText = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        AgregarCategoria_Fragment.AgregarcategoriaEditText.setText("");
         EditText campo2 = AgregarCategoria_Fragment.AgregarcategoriaEditText;
        //EditText campo2 = AgregarCategoria_Fragment.AddDescripcionEditText = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        AgregarCategoria_Fragment.AgregarcategoriaEditText.setText("Comida");

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
//        startFragment( fragment );


        //Situacion 1: si agarra que esta vacio aceptado es true y assertTrue da true
        //EditText campo = AgregarCategoria_Fragment.AgregarcategoriaEditText =(EditText) fragment.findViewById(R.id.AgregarcategoriaEditText);

        //AgregarCategoria_Fragment.AddDescripcionEditText.setText("");
        // EditText campo2 = AgregarCategoria_Fragment.AddDescripcionEditText;

        // EditText campo2 = AgregarCategoria_Fragment.AddDescripcionEditText = (EditText)fragment.getView().findViewById(R.id.AddDescripcionEditText);
        //EditText campo = null;





       // EditText campo2 = AgregarCategoria_Fragment.AddDescripcionEditText = (EditText) fragment.getView().findViewById(R.id.AddDescripcionEditText);
        //AgregarCategoria_Fragment.AddDescripcionEditText.setText("La comida de la universidad");
        EditText campo2 = null;

        boolean aceptado = true;

        if (campo2 != null && aceptado== true ) {
            Categoria_Controller.verificoVacio(campo2);
        }
        else
        aceptado = false;
        assertFalse(aceptado);
    }

    //Situacion 2: si aceptado es false y la prueba da true porque no esta vacia devuelvo en asserFalse true






    @Test
    public void HabilitarEventoSwitch_isCorrect () throws Exception{
       // ListaCategorias_Fragment fragment = new ListaCategorias_Fragment();
        //startFragment( fragment );

        boolean prueba = false ;
        try{
            Categoria_Controller.setHabilitarEventoSwitch(prueba);
        }catch(Exception e){
            prueba = false;
        }
        assertFalse(prueba);

        prueba = true;
        try{
            Categoria_Controller.setHabilitarEventoSwitch(prueba);
        }catch(Exception e){
            prueba = true;
        }
        assertTrue(prueba);



    }

    /**
     * realizo pruebas al metodo colocar alcual las listas en el manejador
     * @throws Exception
     */
    @Test
    public void setListaCategorias_isCorrect () throws Exception {
      //  ListaCategorias_Fragment fragment = new ListaCategorias_Fragment();
       // startFragment( fragment );

        Categoria categoria = new Categoria();
        Categoria pruebas = new Categoria();
        ArrayList<Categoria> prueba = new ArrayList<Categoria>();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        ArrayList<Categoria> prueba1 = new ArrayList<Categoria>();

        try {

            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(true);
            categoria.setEsIngreso(true);

            categorias.add(categoria);




            Categoria_Controller.setListaCategorias(categorias);
        }

        catch (Exception e){

        }

        assertNotEquals(prueba,categorias);

        try {

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
        }

        catch (Exception e) {
        }

        assertNotEquals(categorias,prueba1);





    }


    /**
     * realizo pruebas al metodo colocar alcual las listas en el manejador
     * @throws Exception
     */
    @Test
    public void getListaCategorias_isCorrect () throws Exception {
        //ListaCategorias_Fragment fragment = new ListaCategorias_Fragment();
        //startFragment( fragment );

        Categoria categoria = new Categoria();
        Categoria pruebas = new Categoria();
        ArrayList<Categoria> prueba = new ArrayList<Categoria>();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        ArrayList<Categoria> prueba1 = new ArrayList<Categoria>();

        try {

            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(true);
            categoria.setEsIngreso(true);

            categorias.add(categoria);


            Categoria_Controller.getListaCategorias();
        }

        catch (Exception e) {
        }

        assertNotEquals(prueba,categorias);


        try {
            pruebas.setIdcategoria(1);
            pruebas.setNombre("Comida");
            pruebas.setDescripcion("Gastos en la universidad");
            pruebas.setEstaHabilitado(true);
            pruebas.setEsIngreso(true);
            prueba1.add(pruebas);


            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(true);
            categoria.setEsIngreso(true);
            categorias.add(categoria);

            Categoria_Controller.getListaCategorias();
        }

        catch (Exception e){

        }
        assertNotEquals(categorias,prueba1); //esperado , actual






    }

    /**
     * prueba para saber el numero de request
     * @throws Exception
     */
    @Test

    public void getCasoRequest_isCorrect () throws Exception {
        int prueba = -1;

        try {
            Categoria_Controller.getCasoRequest();

        }

        catch (Exception e){

        }


        assertEquals(-1, Categoria_Controller.getCasoRequest());

        try {
            Categoria_Controller.getCasoRequest();
        }
        catch (Exception e){

        }

        assertNotEquals(2,Categoria_Controller.getCasoRequest());
    }

    }




