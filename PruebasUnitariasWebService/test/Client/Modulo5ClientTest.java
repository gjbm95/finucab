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
 * @author Juan
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
    * Test Modificar Pago
    */
    @Test
    public void testModificarPago() {
       String datosPago ="{\"pg_monto\":1.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        datosPago = URLEncoder.encode(datosPago);
        
        
        
        Modulo5Client instance = new Modulo5Client();
        String result = instance.registrarPago(datosPago);
        String resulta = instance.consultarPago(result);
        String datosPagomodificar ="{\"pg_id\":"+result+",\"pg_monto\":9999.0,\"pg_tipoTransaccion\":\"egreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" Gaste en la universidad\"}";
        String datosPagoMod = URLEncoder.encode(datosPagomodificar);
        String modificar = instance.modificarPago(datosPagoMod);
        String EntiMod =  instance.consultarPago(result);
        String PagoArray[] = EntiMod.split(",");
        String PagoArray2[] = PagoArray[1].split(":");
        assertEquals("9999.0", PagoArray2[1]);
        // TODO review the generated test code and remove the default call to fail.
        
    }
  
    
   
    /**
     * Test Registrar Pago
     */
     @Test
    public void testRegistrarPago() {
       
        String datosPago ="{\"pg_monto\":2.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        datosPago = URLEncoder.encode(datosPago);
        
        Modulo5Client instance = new Modulo5Client();
        
        String result = instance.registrarPago(datosPago);
        
        String resulta = instance.consultarPago(result);
        String PagoArray[] = resulta.split(",");
        String Pago1Array[] = PagoArray[0].split(":");
        assertEquals(result, Pago1Array[1]);
        
    }


/**
 * Test Consultar Pago
 */
     @Test  
    public void testConsultarPago() {
       
        String datosPago ="{\"pg_monto\":4.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        
        datosPago = URLEncoder.encode(datosPago);
        
        Modulo5Client instance = new Modulo5Client();
        String result = instance.registrarPago(datosPago);
        String resulta = instance.consultarPago(result);
        String PagoArray[] = resulta.split(",");
        String Pago1Array[] = PagoArray[0].split(":");
        assertEquals(Pago1Array[1],result);
        
    
}
    
     /**
     * Test Visualizar Pago
     */
    @Test
    public void testVisualizarPago() {
        String aux = null;
        String aux2 = null;
        String datosPago ="{\"pg_monto\":123456789,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        
        datosPago = URLEncoder.encode(datosPago);
        Modulo5Client instance = new Modulo5Client();
        String result1 = instance.registrarPago(datosPago);
        String datosPago1 ="{\"pg_monto\":987654321,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
       
        datosPago1 = URLEncoder.encode(datosPago1);
        String result2 = instance.registrarPago(datosPago1);
        String resultVisualizar = instance.visualizarPago("1");
        String ArrayPago[] = resultVisualizar.split(",");
        for (int i = 0; i <ArrayPago.length; i++ ){
            String ArrayPagoAux1[] = ArrayPago[i].split(":");
            if (result1.equals(ArrayPagoAux1[1])){
                aux = ArrayPagoAux1[1];
            }
            if (result2.equals(ArrayPagoAux1[1])){
                 aux2 = ArrayPagoAux1[1];
            }
        }
        String actual = result1+result2;
        String compararConc = aux +aux2;
        assertEquals(actual, compararConc);
     }
    /**
     * Test Modificar pago fracaso
     */
     @Test
    public void testModificarPagoFracaso() {
       String datosPago ="{\"pg_monto\":1145471.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        datosPago = URLEncoder.encode(datosPago);
        
        
        
        Modulo5Client instance = new Modulo5Client();
        String result = instance.registrarPago(datosPago);
        String resulta = instance.consultarPago(result);
        String datosPagomodificar ="{\"pg_id\":"+result+",\"pg_monto\":9999.0,\"pg_tipoTransaccion\":\"egreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" Gaste en la universidad\"}";
        String datosPagoMod = URLEncoder.encode(datosPagomodificar);
        String modificar = instance.modificarPago(datosPagoMod);
        assertEquals(modificar, "Ha ocurrido un error");
               
    }
    /**
     * test registrar pago fracaso
     */
    @Test 
    public void testRegistrarPagoFracaso() {
       
        String datosPago ="{\"pg_monto\":a,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        datosPago = URLEncoder.encode(datosPago);
        
        Modulo5Client instance = new Modulo5Client();
        
        String result = instance.registrarPago(datosPago);
        assertEquals(result, "Ha ocurrido un error");
        
    }
    /**
     * test consultar pago fracaso
     */
    @Test 
    public void testConsultarPagoFracaso() {
       
        String datosPago ="{\"pg_monto\":445.0,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        
        datosPago = URLEncoder.encode(datosPago);
        
        Modulo5Client instance = new Modulo5Client();
        String result = instance.registrarPago(datosPago);
        String resulta = instance.consultarPago(result);
        assertNotEquals(resulta, datosPago);
        
    
}
    /**
     * test visualizar pago fracaso
     */
    @Test 
    public void testVisualizarPagoFracaso() {
        String datosPago ="{\"pg_monto\":78542,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
        
        datosPago = URLEncoder.encode(datosPago);
        Modulo5Client instance = new Modulo5Client();
        String result1 = instance.registrarPago(datosPago);
        String datosPago1 ="{\"pg_monto\":598563,\"pg_tipoTransaccion\":\"ingreso\",\"pg_categoria\":1, \"pg_nombre_categoria\":\"aca\",\"pg_descripcion\":\" TestPruebaAgregar\"}";
       
        datosPago1 = URLEncoder.encode(datosPago1);
        String result2 = instance.registrarPago(datosPago1);
        String resultVisualizar = instance.visualizarPago(result2);
        assertNotEquals(resultVisualizar, "datosPago"+"datosPago1");
     }
    
}
