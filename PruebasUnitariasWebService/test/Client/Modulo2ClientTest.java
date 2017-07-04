/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.URLEncoder;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alejandro Negrin
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
     * Test of agregarTDC method, of class Modulo2Client.
     */
    @Test
    public void testAgregarTDC() {
        System.out.println("agregarTDC");
        String datosTDC = "{ \"tc_id\" : \"-1\" , \"tc_tipo\" : \"PRUEBA TIPO\""
                + ", \"tc_fechavencimiento\" : \"10/10/2010\", \"tc_numero\" : \"PR123456\", "
                + "\"tc_saldo\" : \"500\" , \"usuariou_id\" : \"1\" }";
        datosTDC = URLEncoder.encode(datosTDC);
        
        Modulo2Client instance = new Modulo2Client();
        
        String result = instance.agregarTDC(datosTDC);
        
        int id = Integer.parseInt(result);
        assertTrue(id > 0);
    }

//    /**
//     * Test of actualizarTDC method, of class Modulo2Client.
//     */
//    @Test
//    public void testActualizarTDC() {
//        System.out.println("actualizarTDC");
//        String datosTDC = "{ \"u_usuario\" : \"mapg\" , \"u_nombre\" : \"Mariangel\""
//                + ", \"u_apellido\" : \"Perez\", \"u_correo\" : \"mariangel@hotmail.com\", "
//                + "\"u_pregunta\" : \"Nombre de mi mama\" , \"u_respuesta\" : \"/marisol\", "
//                + "\"u_password\" : \"123456\" }";
//        datosTDC = URLEncoder.encode(datosTDC);
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.actualizarTDC(datosTDC);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of agregarCuentaBancaria method, of class Modulo2Client.
//     */
//    @Test
//    public void testAgregarCuentaBancaria() {
//        System.out.println("agregarCuentaBancaria");
//        String datosCuenta = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.agregarCuentaBancaria(datosCuenta);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminarCuentaBancaria method, of class Modulo2Client.
//     */
//    @Test
//    public void testEliminarCuentaBancaria() {
//        System.out.println("eliminarCuentaBancaria");
//        String idCuenta = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.eliminarCuentaBancaria(idCuenta);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of eliminarTDC method, of class Modulo2Client.
//     */
//    @Test
//    public void testEliminarTDC() {
//        System.out.println("eliminarTDC");
//        String idtdc = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.eliminarTDC(idtdc);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultarTDC method, of class Modulo2Client.
//     */
//    @Test
//    public void testConsultarTDC() {
//        System.out.println("consultarTDC");
//        String idUsuario = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.consultarTDC(idUsuario);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultarCuentas method, of class Modulo2Client.
//     */
//    @Test
//    public void testConsultarCuentas() {
//        System.out.println("consultarCuentas");
//        String idUsuario = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.consultarCuentas(idUsuario);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actualizarDatosUsuario method, of class Modulo2Client.
//     */
//    @Test
//    public void testActualizarDatosUsuario() {
//        System.out.println("actualizarDatosUsuario");
//        String datosUsuario = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.actualizarDatosUsuario(datosUsuario);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actualizarCuentaBancaria method, of class Modulo2Client.
//     */
//    @Test
//    public void testActualizarCuentaBancaria() {
//        System.out.println("actualizarCuentaBancaria");
//        String datosCuenta = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.actualizarCuentaBancaria(datosCuenta);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of consultarEstadisticas method, of class Modulo2Client.
//     */
//    @Test
//    public void testConsultarEstadisticas() {
//        System.out.println("consultarEstadisticas");
//        String idUsuario = "";
//        Modulo2Client instance = new Modulo2Client();
//        String expResult = "";
//        String result = instance.consultarEstadisticas(idUsuario);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of close method, of class Modulo2Client.
//     */
//    @Test
//    public void testClose() {
//        System.out.println("close");
//        Modulo2Client instance = new Modulo2Client();
//        instance.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}
