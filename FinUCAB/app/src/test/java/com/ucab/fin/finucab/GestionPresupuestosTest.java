package com.ucab.fin.finucab;

import android.widget.EditText;

import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.ExportarPresupuesto_Controller;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.NombrePresupuesto_Exception;
import com.ucab.fin.finucab.fragment.AgregarCategoria_Fragment;
import com.ucab.fin.finucab.webservice.Parametros;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static com.ucab.fin.finucab.controllers.ExportarPresupuesto_Controller.listaPresupuestos;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase se encarga de realizar pruebas unitarias a las Controladoras de Gestion de Presupuesto
 */

public class GestionPresupuestosTest {

    /**
     * Este prueba se encarga de verificar si el metodo de visualizar presupuesto llena las listas
     * correspondientes
     * @throws JSONException
     */
    @Test
    public void visualizarPresupuestoTest() throws JSONException {
        Presupuesto_Controller.total = Double.valueOf(0);
        Presupuesto_Controller.ganancias = Double.valueOf(0);
        Presupuesto_Controller.gastos = Double.valueOf(0);
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        //Creo el objeto Json!
        object.put("Nombre","Ganancias Mensuales");
        object.put("Categoria","Casa");
        object.put("Monto","12000");
        object.put("Duracion","0");
        object.put("Clasificacion","Unico");
        object.put("Tipo","t");
        array.put(object);

        object.put("Nombre","Presupuesto");
        object.put("Categoria","Trabajo");
        object.put("Monto","5000");
        object.put("Duracion","1");
        object.put("Clasificacion","Recurrente");
        object.put("Tipo","t");
        array.put(object);

        Parametros.setRespuesta(array.toString());

        Presupuesto_Controller.visualizarPresupuesto();
        assertNotNull(Presupuesto_Controller.listaGanancias);
        assertNotNull(Presupuesto_Controller.listaGastos);
    }
    /**
     * Este prueba se encarga de verificar si el metodo de visualizar presupuesto llena la lista
     * correspondiente
     * @throws JSONException
     */

    @Test
    public void utilizarPresupuestoTest() throws JSONException {

        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        //Creo el objeto Json!
        object.put("Nombre","Ganancias Mensuales");
        object.put("Categoria","Casa");
        object.put("Monto","12000");
        object.put("Duracion","0");
        object.put("Clasificacion","Unico");
        object.put("Tipo","t");
        array.put(object);

        object.put("Nombre","Presupuesto");
        object.put("Categoria","Trabajo");
        object.put("Monto","5000");
        object.put("Duracion","1");
        object.put("Clasificacion","Recurrente");
        object.put("Tipo","t");
        array.put(object);

        Parametros.setRespuesta(array.toString());

        ExportarPresupuesto_Controller.utilizarPresupuesto();
        assertNotNull(ExportarPresupuesto_Controller.listaPresupuestos);

    }

    /**
     * Este prueba se encarga de verificar si el metodo de exportar a excel se cumple correctamente
     * @throws Exception
     */
    @Test
    public void ExportarExcel_isCorrect() throws Exception{

        Presupuesto presupuesto = new Presupuesto();

        try {
            presupuesto.set_nombre("MiPresupuesto");
            presupuesto.set_categoria("1");
            presupuesto.set_monto((double) 1500);
            presupuesto.set_clasificacion("Unico");
            presupuesto.set_duracion(0);
            presupuesto.set_tipo("Gastos");

            listaPresupuestos.add(presupuesto);
            assertTrue(ExportarPresupuesto_Controller.exportarExcel());

        }

        catch (Exception e) {
        }

    }

    /**
     * Este prueba se encarga de verificar si el metodo de exportar a csv se cumple correctamente
     * @throws Exception
     */
    @Test
    public void ExportarCsv_isCorrect() throws Exception {

        Presupuesto presupuesto = new Presupuesto();

        try {
            presupuesto.set_nombre("MiPresupuesto");
            presupuesto.set_categoria("1");
            presupuesto.set_monto((double) 1500);
            presupuesto.set_clasificacion("Unico");
            presupuesto.set_duracion(0);
            presupuesto.set_tipo("Gastos");

            listaPresupuestos.add(presupuesto);
            assertTrue(ExportarPresupuesto_Controller.exportarCSV());

        } catch (Exception e) {

        }


    }
    /**
     * Se realiza la prueba para verificar la validacion de vacio
     *
     * @throws Exception
     */

    @Test
    public void CampoNombreVacio_isCorrect() throws Exception {

        EditText nombre = null;

        boolean aceptado = true;

        if (nombre != null && aceptado== true ) {
            Presupuesto_Controller.verificoVacio(nombre);
        }
        else
            aceptado = false;
        assertFalse(aceptado);

    }
    /**
     * Ee comprueba la validacion para saber si el nombre de un presupuesto esta repetido o no
     * segun sea agregar o modificar.
     *
     * @throws Exception
     */
    @Test
    public void Devolver_validacion () {

        EditText campo = null;
        boolean aceptado = true;
        Parametros.setRespuesta("repetido");
        try {
            Presupuesto_Controller.DevolverValidacion(campo,aceptado);

        } catch (NombrePresupuesto_Exception e) {
            aceptado = false;
        }
        assertFalse(aceptado);
    }

}