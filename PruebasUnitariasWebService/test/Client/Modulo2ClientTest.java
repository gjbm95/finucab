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
public class Modulo2ClientTest {
    
    public Modulo2ClientTest() {
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
     * Test of getPruebaJson method, of class Modulo2Client.
     */
    @Test
    public void testGetPruebaJson() {
        System.out.println("getPruebaJson");
        Modulo2Client instance = new Modulo2Client();
        String expResult = "";
        String result = instance.getPruebaJson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postJson method, of class Modulo2Client.
     */
    @Test
    public void testPostJson() {
        System.out.println("postJson");
        Object requestEntity = null;
        Modulo2Client instance = new Modulo2Client();
        Response expResult = null;
        Response result = instance.postJson(requestEntity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPruebaDataBase method, of class Modulo2Client.
     */
    @Test
    public void testGetPruebaDataBase() {
        System.out.println("getPruebaDataBase");
        Modulo2Client instance = new Modulo2Client();
        String expResult = "";
        String result = instance.getPruebaDataBase();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class Modulo2Client.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        Modulo2Client instance = new Modulo2Client();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
