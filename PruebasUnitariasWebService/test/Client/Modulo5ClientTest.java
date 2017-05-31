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
public class Modulo5ClientTest {
    
    public Modulo5ClientTest() {
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
     * Test of getPruebaJson method, of class Modulo5Client.
     */
    @Test
    public void testGetPruebaJson() {
        System.out.println("getPruebaJson");
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.getPruebaJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postJson method, of class Modulo5Client.
     */
    @Test
    public void testPostJson() {
        System.out.println("postJson");
        Object requestEntity = null;
        Modulo5Client instance = new Modulo5Client();
        Response expResult = null;
        Response result = instance.postJson(requestEntity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPago method, of class Modulo5Client.
     */
    @Test
    public void testModificarPago() {
        System.out.println("modificarPago");
        String datosPago = "";
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.modificarPago(datosPago);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPruebaDataBase method, of class Modulo5Client.
     */
    @Test
    public void testGetPruebaDataBase() {
        System.out.println("getPruebaDataBase");
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.getPruebaDataBase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarPago method, of class Modulo5Client.
     */
    @Test
    public void testRegistrarPago() {
        System.out.println("registrarPago");
        String datosPago = "";
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.registrarPago(datosPago);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarPago method, of class Modulo5Client.
     */
    @Test
    public void testConsultarPago() {
        System.out.println("consultarPago");
        String datosPago = "";
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.consultarPago(datosPago);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of visualizarPago method, of class Modulo5Client.
     */
    @Test
    public void testVisualizarPago() {
        System.out.println("visualizarPago");
        String datosPago = "";
        Modulo5Client instance = new Modulo5Client();
        String expResult = "";
        String result = instance.visualizarPago(datosPago);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class Modulo5Client.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        Modulo5Client instance = new Modulo5Client();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
