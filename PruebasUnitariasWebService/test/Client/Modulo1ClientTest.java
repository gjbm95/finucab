/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.StringReader;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Response;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.containsString;


/**
 *
 * @author AlejandroNegrin
 */
public class Modulo1ClientTest {
    
    public Modulo1ClientTest() {
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
     * Metodo para probar el correcto funcionanmiento de la función
     * registrarUsuario() del WebService Modulo1sResources
     *
     * @throws ClientErrorException
     */
    @Test
    public void testRegistrarUsuarioCorrecto() {
        System.out.println("ENTRE EN PRUEBA registrarUsuarioCorrecto");
        String datosCuenta = "{ \"u_usuario\" : \"mapg\" , \"u_nombre\" : \"Mariangel\""
                + ", \"u_apellido\" : \"Perez\", \"u_correo\" : \"mariangel@hotmail.com\", "
                + "\"u_pregunta\" : \"Nombre de mi mama\" , \"u_respuesta\" : \"/marisol\", "
                + "\"u_password\" : \"123456\" }";
        datosCuenta = URLEncoder.encode(datosCuenta);
        
        Modulo1Client instance = new Modulo1Client();

        String result = instance.registrarUsuario(datosCuenta);
        String resultado = instance.verificarUsuario("mapg");
        Assert.assertEquals("4",resultado);
        
    }
    
        @Test
    public void testRegistrarUsuarioIncorrecto() {
        System.out.println("ENTRE EN PRUEBA registrarUsuarioIncorrecto");
        String datosCuenta = "{ \"u_usuario\" : \"mapg\" , \"u_nombre\" : \"Mariangel2\""
                + ", \"u_apellido\" : \"Perez2\", \"u_correo\" : \"mariangel2@hotmail.com\", "
                + "\"u_pregunta\" : \"Nombre de mi mama2\" , \"u_respuesta\" : \"/marisol2\", "
                + "\"u_password\" : \"1234567\" }";
        datosCuenta = URLEncoder.encode(datosCuenta);
        Modulo1Client instance = new Modulo1Client();
        String expected = instance.registrarUsuario(datosCuenta);
        Assert.assertEquals("0",expected);
        
    }
    
    @Test
    public void testRegistrarUsuarioIncorrectoVacio() {
        System.out.println("ENTRE EN PRUEBA registrarUsuarioIncorrectoVacio");
        String datosCuenta = "";
        datosCuenta = URLEncoder.encode(datosCuenta);
        Modulo1Client instance = new Modulo1Client();
        String expected = instance.registrarUsuario(datosCuenta);
        Assert.assertEquals("0",expected);
        
    }


    /**
     * Metodo para probar el correcto funcionamiento de la función
     * recuperarClave() del WebService Modulo1sResources
     *
     * @throws ClientErrorException
     */
    @Test
    public void testRecuperarClaveCorrecto() {
        System.out.println("ENTRE EN recuperarClaveCorrecto");
        String datosUsuario = "mapg";

        Modulo1Client instance = new Modulo1Client();

        String result = instance.recuperarClave(datosUsuario);
  
            String [] resultSplitComa = result.split(",");
            String Decodifico = resultSplitComa[1];
            String [] resultSplit = Decodifico.split(":");
            String str = resultSplit[1];
            String resultado = str.replace('"',' ');
        Assert.assertEquals(" mapg ",resultado);
        
    }
    
    
    
    
         @Test
    public void testRecuperarClaveIncorrecto() {
        System.out.println("ENTRE EN recuperarClaveIncorrecto");
        String datosUsuario = "UsuarioX";

        Modulo1Client instance = new Modulo1Client();

        String result = instance.recuperarClave(datosUsuario);
  
           
        Assert.assertEquals("ERROR",result);
        
    }
    @Test
    public void testRecuperarClaveIncorrectoVacio() {
        System.out.println("ENTRE EN recuperarClaveIncorrectoVacio");
        String datosUsuario = "";
        Modulo1Client instance = new Modulo1Client();
        String result = instance.recuperarClave(datosUsuario);
        Assert.assertEquals("ERROR",result);
        
    }

    
    /**
     * Metodo para probar el correcto funcionamiento de la función
     * iniciarSesion() del WebService Modulo1sResources
     *
     * @throws ClientErrorException
     */
    @Test
    public void testIniciarSesion() {
        System.out.println("ENTRE EN iniciarSesion");
        String datosUsuario = "{ \"u_usuario\" : \"mapg\" , \"u_password\" : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.iniciarSesion(datosUsuario);
        String [] resultSplitComa = result.split(",");
            String usuario = resultSplitComa[1];
            String clave = resultSplitComa[7];
            String [] resultSplitusuario = usuario.split(":");
            String str = resultSplitusuario[1];
            String resultadoUsuario = str.replace('"',' ');
            String [] resultSplitclave = clave.split(":");
            String str2 = resultSplitclave[1];
            String resultadoClave = str2.replace('"',' ');
            String resultadoClaveTotal = resultadoClave.replace('}',' ');
            String Usuario = resultadoUsuario+","+resultadoClaveTotal;

        Assert.assertEquals(" mapg , 123456  ", Usuario);
        
    }
    
    @Test
    public void testIniciarSesionIncorrecto() {
        System.out.println("ENTRE EN iniciarSesion");
        String datosUsuario = "{ \"u_usuario\" : \"UsuarioX\" , \"u_password\" : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.iniciarSesion(datosUsuario);
        Assert.assertEquals("7", result);
        //assertThat(result, either(containsString("\"u_usuario\"")).or(is("ERROR")).or(is("DATOSMAL")));
        
    }
    
    @Test
    public void testIniciarSesionIncorrectoVacio() {
        System.out.println("ENTRE EN iniciarSesionVacio");
        String datosUsuario = "";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.iniciarSesion(datosUsuario);
        Assert.assertEquals("7", result);
        
    }
    /**
     * Metodo para probar el correcto funcionamiento de la función
     * actualizarClave() del WebService Modulo1sResources
     *
     * @throws ClientErrorException
     */
    @Test
    public void testActualizarClave() {
        System.out.println("ENTRE EN actualizarClave");
        String datosUsuario = "{ \"u_usuario\" : \"mapg\" , \"u_password\" : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.actualizarClave(datosUsuario);

        Assert.assertEquals("5", result);
 

    }
    
    @Test
    public void testActualizarClaveIncorrecto() {
        System.out.println("ENTRE EN actualizarClaveIncorrecto");
        String datosUsuario = "{ \"u_usuario\" : \"UsuarioX\" , \"u_password\" : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.actualizarClave(datosUsuario);

        Assert.assertEquals("6", result);
 

    }
    
    @Test
    public void testActualizarClaveIncorrectoVacio() {
        System.out.println("ENTRE EN actualizarClaveIncorrectoVacio");
        String datosUsuario = "";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.actualizarClave(datosUsuario);

        Assert.assertEquals("6", result);
 

    }
    


    /**
     * Metodo para probar el correcto funcionamiento de la función
     * verificarUsuario() del WebService Modulo1sResources
     *
     * @throws ClientErrorException
     */
    @Test
    public void testVerificarUsuario() {
        System.out.println("ENTRE EN verificarUsuario");
        String nombreUsuario = "mapg";

        Modulo1Client instance = new Modulo1Client();

        String result = instance.verificarUsuario(nombreUsuario);

        Assert.assertEquals("4",result);
      
    }
    
    @Test
    public void testVerificarUsuarioIncorrecto() {
        System.out.println("ENTRE EN verificarUsuarioIncorrecto");
        String nombreUsuario = "UsuarioX";

        Modulo1Client instance = new Modulo1Client();
        String result = instance.verificarUsuario(nombreUsuario);

        Assert.assertEquals("3",result);
       
    }
    
     @Test
    public void testVerificarUsuarioIncorrectoVacio() {
        System.out.println("ENTRE EN verificarUsuarioIncorrectoVacio");
        String nombreUsuario = "";
        Modulo1Client instance = new Modulo1Client();
        String result = instance.verificarUsuario(nombreUsuario);
        Assert.assertEquals("3",result);
       
    }
    
   
    
}

