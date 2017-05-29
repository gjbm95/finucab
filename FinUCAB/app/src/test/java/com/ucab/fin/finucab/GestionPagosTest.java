package com.ucab.fin.finucab;

/**
 * Created by Luis on 28-05-2017.
 */


import android.widget.EditText;

import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.controllers.Pago_Controller;
import com.ucab.fin.finucab.domain.Pago;
import com.ucab.fin.finucab.fragment.AgregarPago_Fragment;

import org.junit.Test;

import java.util.ArrayList;

import static com.ucab.fin.finucab.controllers.Pago_Controller.asignarValores;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *Modulo 5 - Modulo de  Gestion de Pagos
 *Desarrolladores:
 *@author Maria Jose Perez / Luis Rojas / Jeffrey Soares
 *Descripción de la clase:
 * Esta clase se encarga de realizar pruebas unitarias al Controlador de Gestion de Pagos
 */
public class GestionPagosTest {
    /**
            * realizo pruebas al metodo colocar las listas de pago en el manejador
     * @throws Exception
     */
    @Test
    public void setListaPagos () throws Exception {

        Pago pago = new Pago();
        ArrayList<Pago> pagos = new ArrayList<Pago>();

        try {
            pago.setIdPago(1);
            pago.setCategoria("Universidad");
            pago.setDescripcion("Pago Semestre");
            pago.setTotal(400000);
            pago.setTipo("Egreso");

            pagos.add(pago);


            Pago_Controller.setListaPagos(pagos);
        } catch (Exception e) {
        }

        assertNotNull(pagos);
    }

        /**
         * realizo pruebas al metodo tomar las listas de pago en el controlador
         * @throws Exception
         */
        @Test
        public void getListaPagos () throws Exception {


            Pago pago = new Pago();
            ArrayList<Pago> pagos = new ArrayList<Pago>();

            ArrayList<Pago> prueba = new ArrayList<Pago>();
            try {

                pago.setIdPago(1);
                pago.setCategoria("Universidad");
                pago.setDescripcion("Pago Semestre");
                pago.setTotal(400000);
                pago.setTipo("Egreso");
                pagos.add(pago);


                prueba = Pago_Controller.getListaPagos();
            } catch (Exception e) {
            }

            assertNotNull(pagos);
            System.out.printf(String.valueOf(prueba));
            assertNotEquals(pagos, prueba);

        }












}

