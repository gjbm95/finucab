package com.ucab.fin.finucab;

import android.app.Activity;
import android.util.Log;

import com.ucab.fin.finucab.controllers.Planificacion_Controller;
import com.ucab.fin.finucab.domain.Planificacion;
import com.ucab.fin.finucab.fragment.AgregarPlanificacionFragment;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 *Modulo 6 - Modulo de Planificacion
 *Desarrolladores:
 *@author Eduardo Lorenzo / Christian Leon / William Lopez
 *Descripción de la clase:
 * Esta clase se encarga de realizar pruebas unitarias a la Controladora de Planificacion
 *
 **/
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GestionPlanificacionTest {
    /**
     * realizo prueba para revisar que esten llenos todos los campos requeridos
     *
     * @throws Exception
     */
    @Test
    public void CamposLlenosAgregar_isCorrect() throws Exception {
        AgregarPlanificacionFragment fragment = new AgregarPlanificacionFragment();
        startFragment( fragment );

        // Caso 1: Validar con campos requeridos vacios
        Planificacion_Controller.obtenerPlanificacion().setNombre("Nombre test");
        assertEquals(Planificacion_Controller.validarCamposRequeridos(), false);

        // Caso 2: Validar con campos requeridos llenos (pago unico)
        Planificacion_Controller.resetearCampos();
        Planificacion_Controller.obtenerPlanificacion().setDescripcion("Descripcion test");
        Planificacion_Controller.obtenerPlanificacion().setMonto(19.3);
        Planificacion_Controller.obtenerPlanificacion().setIdCategoria(1);
        Planificacion_Controller.obtenerPlanificacion().setRecurrente(false);
        Planificacion_Controller.obtenerPlanificacion().setFechaInicio(new Date(100));
        assertEquals(Planificacion_Controller.validarCamposRequeridos(), true);

        // Caso 3: Validar con campos requeridos llenos (pago unico)
        Planificacion_Controller.resetearCampos();
        Planificacion_Controller.obtenerPlanificacion().setDescripcion("Descripcion test");
        Planificacion_Controller.obtenerPlanificacion().setMonto(19.3);
        Planificacion_Controller.obtenerPlanificacion().setIdCategoria(1);
        Planificacion_Controller.obtenerPlanificacion().setRecurrente(true);
        Planificacion_Controller.obtenerPlanificacion().setFechaInicio(new Date(100));
        Planificacion_Controller.obtenerPlanificacion().setFechaFin(new Date(200));
        Planificacion_Controller.obtenerPlanificacion().setRecurrencia("Recurrencia");
        assertEquals(Planificacion_Controller.validarCamposRequeridos(), true);
    }

    /**
     * realizo prueba para revisar que las fechas cumplan con la condicion que
     * fecha inicio sea menor que fecha fin
     * @throws Exception
     */
    @Test
    public void FechasCorrectas_isCorrect() throws Exception {
        AgregarPlanificacionFragment fragment = new AgregarPlanificacionFragment();
        startFragment( fragment );

        // Caso 1: Es de tipo UNICO y no hay fecha fin
        Planificacion_Controller.resetearCampos();
        Planificacion_Controller.obtenerPlanificacion().setFechaInicio(new Date(100));
        assertEquals(Planificacion_Controller.validarFechas(), true);

        // Caso 2: Es de tipo RECURRENTE y fecha fin no ha sido setteada
        Planificacion_Controller.resetearCampos();
        Planificacion_Controller.obtenerPlanificacion().setRecurrente(true);
        Planificacion_Controller.obtenerPlanificacion().setFechaInicio(new Date(100));
        assertEquals(Planificacion_Controller.validarFechas(), false);

        // Caso 3: Es de tipo RECURRENTE y fecha fin si ha sido setteada pero es igual a la fecha inical
        Planificacion_Controller.resetearCampos();
        Planificacion_Controller.obtenerPlanificacion().setRecurrente(true);
        Planificacion_Controller.obtenerPlanificacion().setFechaInicio(new Date(100));
        Planificacion_Controller.obtenerPlanificacion().setFechaFin(new Date(100));
        assertEquals(Planificacion_Controller.validarFechas(), false);

        // Caso 4: Es de tipo RECURRENTE y fecha fin si ha sido setteada pero es menor a la fecha inical
        Planificacion_Controller.resetearCampos();
        Planificacion_Controller.obtenerPlanificacion().setRecurrente(true);
        Planificacion_Controller.obtenerPlanificacion().setFechaInicio(new Date(100));
        Planificacion_Controller.obtenerPlanificacion().setFechaFin(new Date(50));
        assertEquals(Planificacion_Controller.validarFechas(), false);

        // Caso 5: Es de tipo RECURRENTE y fecha fin si ha sido setteada pero es mayor a la fecha inical
        Planificacion_Controller.resetearCampos();
        Planificacion_Controller.obtenerPlanificacion().setRecurrente(true);
        Planificacion_Controller.obtenerPlanificacion().setFechaInicio(new Date(100));
        Planificacion_Controller.obtenerPlanificacion().setFechaFin(new Date(200));
        assertEquals(Planificacion_Controller.validarFechas(), true);
    }

    /**
     * Prueba si resetear variables funciona correctamente
     */
    @Test
    public void ResetearVariables_isCorrect() {
        Planificacion_Controller.obtenerPlanificacion().setNombre("Test");
        Planificacion_Controller.resetearCampos();

        assertEquals(Planificacion_Controller.obtenerPlanificacion().getNombre().length() > 0, false);
    }

    /**
     * Prueba para el agregar planificacion
     *
     * @throws Exception
     */
    @Test
    public void ServicioAgregarPlanificacion_isCorrect() throws JSONException, Exception {
        JSONObject json = new JSONObject();

        try {
            json.put("pa_nombre", "Nombre");
            json.put("pa_descripcion", "Descripcion");
            json.put("pa_monto", "190");
            json.put("pa_fechainicio", "1993-03-19");
            json.put("pa_fechafin", "1993-08-10");
            json.put("pa_recurrente", "false");
            json.put("pa_recurrencia", "");
            json.put("usuariou_id", "1");
            json.put("categoriaca_id", "1");
            json.put("pa_activo", "false");

            Parametros.reset();
            Parametros.setMetodo("Modulo6/registrarPlanificacion/1?datosPlanificacion=" + URLEncoder.encode(json.toString()));
            new Recepcion(new Activity(), null).execute("GET");
        } catch (JSONException e) {
            Log.d("ERROR", "Error creando el JSON.\n\r");
            e.printStackTrace();
        }
        catch (Exception e) {
            Log.d("ERROR", "Ha ocurrido un error inesperado.");
        }
    }

    /**
     * Prueba para el modificar planificacion
     *
     * @throws Exception
     */
    @Test
    public void ServicioModificarPlanificacion_isCorrect() throws JSONException, Exception {
        JSONObject json = new JSONObject();

        try {
            json.put("pa_nombre", "Nombre");
            json.put("pa_descripcion", "Descripcion");
            json.put("pa_monto", "190");
            json.put("pa_fechainicio", "1993-03-19");
            json.put("pa_fechafin", "1993-08-10");
            json.put("pa_recurrente", "false");
            json.put("pa_recurrencia", "");
            json.put("usuariou_id", "1");
            json.put("categoriaca_id", "1");
            json.put("pa_activo", "true");

            Parametros.reset();
            Parametros.setMetodo("Modulo6/registrarPlanificacion/1?datosPlanificacion=" + URLEncoder.encode(json.toString()));
            new Recepcion(new Activity(), null).execute("GET");

            json.put("pa_activo", "false");

            Parametros.reset();
            Parametros.setMetodo("Modulo6/modificarPlanificacion?datosPlanificacion=" + URLEncoder.encode(json.toString()));
            new Recepcion(new Activity(), null).execute("GET");
        } catch (JSONException e) {
            Log.d("ERROR", "Error creando el JSON.\n\r");
            e.printStackTrace();
        }
        catch (Exception e) {
            Log.d("ERROR", "Ha ocurrido un error inesperado.");
        }
    }
}
