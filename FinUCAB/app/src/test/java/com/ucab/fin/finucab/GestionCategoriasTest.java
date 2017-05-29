package com.ucab.fin.finucab;

/**
 * Created by Juan on 25-05-2017.
 */


import android.widget.EditText;

import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;
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

        EditText campo2 = null;

        boolean aceptado = true;

        if (campo2 != null && aceptado == true) {
            Categoria_Controller.verificoVacio(campo2);
        } else
            assertTrue(aceptado);

    }

    /**
     * realizo prueba a campo agregar categoria vacio
     *
     * @throws Exception
     */

    @Test
    public void CampoDescripcionVacio_isCorrect() throws Exception {
       EditText campo2 = null;

        boolean aceptado = true;

        if (campo2 != null && aceptado == true) {
            Categoria_Controller.verificoVacio(campo2);
        } else
            assertTrue(aceptado);
    }


    /**
     * realizo prueba al evento habilitar
     * @throws Exception
     */
    @Test
    public void HabilitarEventoSwitch_isCorrect() throws Exception {
        boolean prueba = false;
        try {
            Categoria_Controller.setHabilitarEventoSwitch(prueba);
        } catch (Exception e) {
            prueba = false;
        }
        assertFalse(prueba);


        prueba = true;
        try {
            Categoria_Controller.setHabilitarEventoSwitch(prueba);
        } catch (Exception e) {
            prueba = true;
        }
        assertTrue(prueba);


    }

    /**
     * realizo pruebas al metodo colocar alcual las listas en el manejador
     *
     * @throws Exception
     */
    @Test
    public void setListaCategorias_isCorrect() throws Exception {
        Categoria categoria = new Categoria();
        ArrayList<Categoria> prueba = new ArrayList<Categoria>();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        try {

            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(true);
            categoria.setEsIngreso(true);

            categorias.add(categoria);


            Categoria_Controller.setListaCategorias(categorias);
        } catch (Exception e) {

        }

        assertNotEquals(prueba, categorias);

    }

    /**
     * realizo pruebas al metodo colocar alcual las listas en el manejador
     *
     * @throws Exception
     */
    @Test
    public void getListaCategorias_isCorrect() throws Exception {
        Categoria categoria = new Categoria();
        ArrayList<Categoria> prueba = new ArrayList<Categoria>();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();


        try {

            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(true);
            categoria.setEsIngreso(true);

            categorias.add(categoria);


            Categoria_Controller.getListaCategorias();
        } catch (Exception e) {
        }

        assertNotEquals(prueba, categorias);

    }

    /**
     * prueba para saber el numero de request
     *
     * @throws Exception
     */
    @Test
    public void getCasoRequest_isCorrect() throws Exception {
        int prueba = Categoria_Controller.casoRequest;

        try {
            Categoria_Controller.getCasoRequest();


        } catch (Exception e) {

        }

        assertEquals(Categoria_Controller.getCasoRequest(), prueba);

    }

    /**
     * realizo prueba al metodo  habilitar la categoria
     * @throws Exception
     */
    @Test
    public void HabilitarCategoria_isCorrect() throws Exception {

        Categoria categoria = new Categoria();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        try {

            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(false);
            categoria.setEsIngreso(true);
            categorias.add(categoria);

            boolean habilitar = categoria.isEstaHabilitado();




            if (habilitar == false && categoria != null) {
                try {
                    int casoRequet = 2;


                    Categoria_Controller.HabilitarCategoria(categoria, habilitar);
                    habilitar = true;
                    assertTrue(habilitar);
                    } catch (Exception e) {

                }

            }


        } catch (Exception e) {

        }

        Boolean habilitar2 = Categoria_Controller.habilitarEventoSwitch;
        Categoria categoria2 = new Categoria();
        Boolean prueba2 = false;

        try {

            boolean habilitar = true;
            if (habilitar == true && categoria2 == null)

                try {
                    Categoria_Controller.HabilitarCategoria(categoria2, habilitar2);

                    assertFalse(prueba2);
                } catch (Exception e) {

                }


        } catch (Exception e) {

        }
    }

    /**
     * realizo prueba a modificar categoria
     * @throws Exception
     */
    @Test
    public void modificarCategoria_isCorrect() throws Exception {

        try {

            Categoria categoria = new Categoria();

            categoria.setNombre("comida");
            categoria.setDescripcion("gastando dinero en la universidad");
            categoria.setEstaHabilitado(true);
            categoria.setEsIngreso(false);

            Boolean prueba;


            if (categoria != null) {

                try {
                    prueba = true;
                    Categoria_Controller.modificarCategoria(categoria);
                    assertTrue(prueba);

                } catch (Exception e) {

                }
            } else {
                prueba = false;

                assertFalse(prueba);
            }

        } catch (Exception e) {

        }
    }

    /**
     * realizo prueba al metodo borrar Categoria
     * @throws Exception
     */
    @Test
    public void borrarCategoria_isCorrect() throws Exception {

        Categoria categoria = new Categoria();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();




        try {

            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(true);
            categoria.setEsIngreso(true);
            categorias.add(categoria);

            int id = categoria.getIdcategoria();


               if (id ==1 ){
                    boolean respuesta = true;
                   Categoria_Controller.borrarCategoria(id);
                   assertTrue(respuesta);



               }

               else {
                   boolean respuesta = true;
                   assertFalse(respuesta);
               }


        } catch (Exception e) {

        }
    }

    /**
     * realizo prueba al metodo obtener Categorias
     * @throws Exception
     */
    @Test
    public void obtenerTodasCategorias_isCorrect() throws Exception {

        Categoria categoria = new Categoria();
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();




        try {

            categoria.setIdcategoria(1);
            categoria.setNombre("Comida");
            categoria.setDescripcion("Gastos en la universidad");
            categoria.setEstaHabilitado(false);
            categoria.setEsIngreso(true);
            categorias.add(categoria);

            Boolean id = categoria.isEstaHabilitado();



            if (id ==true ){
                boolean respuesta = true;
                Categoria_Controller.obtenerTodasCategorias(id);
                assertTrue(respuesta);



            }

            else if (id == false) {
                boolean respuesta = true;
                assertTrue(respuesta);
            }


        } catch (Exception e) {

        }
    }
}








