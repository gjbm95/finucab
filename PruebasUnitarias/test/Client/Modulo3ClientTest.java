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
public class Modulo3ClientTest {
    
    public Modulo3ClientTest() {
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
     * Test of getObtenerPresupuesto method, of class Modulo3Client.
     */
    @Test
    public void testGetObtenerPresupuesto() {
        System.out.println("getObtenerPresupuesto");
        String nombrePresupuesto = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.getObtenerPresupuesto(nombrePresupuesto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postJson method, of class Modulo3Client.
     */
    @Test
    public void testPostJson() {
        System.out.println("postJson");
        Object requestEntity = null;
        Modulo3Client instance = new Modulo3Client();
        Response expResult = null;
        Response result = instance.postJson(requestEntity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificarPresupuesto method, of class Modulo3Client.
     */
    @Test
    public void testModificarPresupuesto() {
        System.out.println("modificarPresupuesto");
        String nombrePresupuesto = "";
        String usuarioid = "";
        String datosPresupuesto = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.modificarPresupuesto(nombrePresupuesto, usuarioid, datosPresupuesto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of VerificarNombre method, of class Modulo3Client.
     */
    @Test
    public void testVerificarNombre() {
        System.out.println("VerificarNombre");
        String nombrePresupuesto = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.VerificarNombre(nombrePresupuesto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaPresupuesto method, of class Modulo3Client.
     */
    @Test
    public void testGetListaPresupuesto() {
        System.out.println("getListaPresupuesto");
        String idUsuario = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.getListaPresupuesto(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ObtenerSpinnerCategoria method, of class Modulo3Client.
     */
    @Test
    public void testObtenerSpinnerCategoria() {
        System.out.println("ObtenerSpinnerCategoria");
        String usuarioid = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.ObtenerSpinnerCategoria(usuarioid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTodoslosPresupuestos method, of class Modulo3Client.
     */
    @Test
    public void testGetTodoslosPresupuestos() {
        System.out.println("getTodoslosPresupuestos");
        String idUsuario = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.getTodoslosPresupuestos(idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarPresupuesto method, of class Modulo3Client.
     */
    @Test
    public void testRegistrarPresupuesto() {
        System.out.println("registrarPresupuesto");
        String usuarioid = "";
        String datosPresupuesto = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.registrarPresupuesto(usuarioid, datosPresupuesto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEliminarPresupuesto method, of class Modulo3Client.
     */
    @Test
    public void testGetEliminarPresupuesto() {
        System.out.println("getEliminarPresupuesto");
        String nombrePresupuesto = "";
        String idUsuario = "";
        Modulo3Client instance = new Modulo3Client();
        String expResult = "";
        String result = instance.getEliminarPresupuesto(nombrePresupuesto, idUsuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class Modulo3Client.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        Modulo3Client instance = new Modulo3Client();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
