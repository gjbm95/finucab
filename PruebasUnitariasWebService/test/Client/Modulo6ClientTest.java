/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Junior
 */
public class Modulo6ClientTest {
    
    public Modulo6ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPruebaJson method, of class Modulo6Client.
     */
    @Test
    public void testGetPruebaJson() {
        System.out.println("getPruebaJson");
        Modulo6Client instance = new Modulo6Client();
        String expResult = "";
        String result = instance.getPruebaJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registarPlanificacion method, of class Modulo6Client.
     */
    @Test
    public void testRegistarPlanificacion() {
        System.out.println("registarPlanificacion");
        Object requestEntity = null;
        String id = "";
        Modulo6Client instance = new Modulo6Client();
        String expResult = "";
        String result = instance.registarPlanificacion(requestEntity, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarPlanificacion method, of class Modulo6Client.
     */
    @Test
    public void testRegistrarPlanificacion() {
        System.out.println("registrarPlanificacion");
        String datosPlanificacion = "";
        Modulo6Client instance = new Modulo6Client();
        String expResult = "";
        String result = instance.registrarPlanificacion(datosPlanificacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postJson method, of class Modulo6Client.
     */
    @Test
    public void testPostJson() {
        System.out.println("postJson");
        Object requestEntity = null;
        Modulo6Client instance = new Modulo6Client();
        Response expResult = null;
        Response result = instance.postJson(requestEntity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of getPruebaDataBase method, of class Modulo6Client.
     */
    @Test
    public void testGetPruebaDataBase() {
        System.out.println("getPruebaDataBase");
        Modulo6Client instance = new Modulo6Client();
        String expResult = "";
        String result = instance.getPruebaDataBase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPlanificacion method, of class Modulo6Client.
     */
    @Test
    public void testListarPlanificacion() {
        System.out.println("listarPlanificacion");
        String id = "";
        Modulo6Client instance = new Modulo6Client();
        String expResult = "";
        String result = instance.listarPlanificacion(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of VisualizarPlanificacion method, of class Modulo6Client.
     */
    @Test
    public void testVisualizarPlanificacion() {
        System.out.println("VisualizarPlanificacion");
        String datosPlanificacion = "";
        Modulo6Client instance = new Modulo6Client();
        String expResult = "";
        String result = instance.VisualizarPlanificacion(datosPlanificacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class Modulo6Client.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        Modulo6Client instance = new Modulo6Client();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
