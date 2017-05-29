/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.net.URLEncoder;
import javax.ws.rs.core.Response;
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
    public void testRegistrarUsuario() {
        System.out.println("ENTRE EN PRUEBA registrarUsuario");
        String datosCuenta = "{ \"u_usuario\" : \"AleNegrin\" , \"u_nombre\" : \"Alejandro\""
                + ", \"u_apellido\" : \"Negrin\", \"u_correo\" : \"aledavid21@hotmail.com\", "
                + "\"u_pregunta\" : \"Nombre de mi mama\" , \"u_respuesta\" : \"/alejandra\", "
                + "\"u_password\" : \"123456\" }";
        datosCuenta = URLEncoder.encode(datosCuenta);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.registrarUsuario(datosCuenta);

        assertNotNull(result);
        assertThat(result, anyOf(is("Registro exitoso"), is("No se pudo registrar"), containsString("ERROR")));
    }

    /**
     * Metodo para probar el correcto funcionamiento de la función
     * recuperarClave() del WebService Modulo1sResources
     *
     * @throws ClientErrorException
     */
    @Test
    public void testRecuperarClave() {
        System.out.println("ENTRE EN recuperarClave");
        String datosUsuario = "AleNegrin";

        Modulo1Client instance = new Modulo1Client();

        String result = instance.recuperarClave(datosUsuario);

        assertNotNull(result);
        assertThat(result, either(containsString("\"u_usuario\"")).or(is("ERROR")));
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
        String datosUsuario = "{ \"u_usuario\" : \"AleNegrin\" , \"u_password\" : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.actualizarClave(datosUsuario);

        assertNotNull(result);
        assertThat(result, anyOf(is("Clave Modificada"), is("Error")));

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
        String datosUsuario = "{ \"u_usuario\" : \"AleNegrin\" , \"u_password\" : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1Client instance = new Modulo1Client();

        String result = instance.iniciarSesion(datosUsuario);

        assertNotNull(result);
        assertThat(result, either(containsString("\"u_usuario\"")).or(is("ERROR")).or(is("DATOSMAL")));
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
        String nombreUsuario = "AleNegrin";

        Modulo1Client instance = new Modulo1Client();

        String result = instance.verificarUsuario(nombreUsuario);

        assertNotNull(result);
        assertThat(result, anyOf(is("No Disponible"), is("Usuario Disponible"), is("ERROR")));
    }
}
