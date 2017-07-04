/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebasModulo1;

import BaseDatosDAO.Conexion;
import Dominio.Entidad;
import Registro.RegistroBaseDatos;
import Services.Modulo1sResource;
import java.io.StringReader;
import java.net.URLEncoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import junit.framework.Assert;
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
public class PruebasModulo1 {
    
    public PruebasModulo1() {
    }
    
 /**
     * Metodo para registrar un usuario cada vez que 
     * se vaya a testear un metodo
     */
    
    @Before
    public void DatosUsuario() {
       System.out.println("ENTRE EN PRUEBA registrarUsuarioCorrecto");
        String datosCuenta = "{ \"u_usuario\" : \"mapg\" , "
                + "\"u_nombre\" : \"Mariangel\" , \"u_apellido\" : \"Perez\", "
                + "\"u_correo\" : \"mariangel@hotmail.com\", "
                + "\"u_pregunta\" : \"Nombre de mi mama\" , "
                + "\"u_respuesta\" : \"/marisol\", "
                + "\"u_password\" : \"123456\" }";
        datosCuenta = URLEncoder.encode(datosCuenta);
        Modulo1sResource instance = new Modulo1sResource();
        instance.registrarUsuario(datosCuenta);
       
    }
    
    /**
     * Metodo para borrar un usuario despues de  
     * finalizada una prueba
     */
    @After
    public void DeleteUser() {
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            
            String query = "DELETE from Usuario WHERE u_usuario ='mapg';";
        
            st.executeQuery(query);
            st.close();
            
        } catch (SQLException ex) {
          
        }
            
    }
    
      
    /**
     * Metodo para probar el correcto funcionanmiento de la función
     * registrarUsuario() del WebService Modulo1sResources
     */
    @Test
    public void testRegistrarUsuarioCorrecto() {
        
        Modulo1sResource instance = new Modulo1sResource();
        String resultado = instance.verificarUsuario("mapg");
        Assert.assertEquals("4",resultado);
        
    }
    
     /**
     * Metodo para probar el funcionanmiento incorrecto de la función
     * registrarUsuario() del WebService Modulo1sResources
     */
        @Test
    public void testRegistrarUsuarioIncorrecto() {
        System.out.println("ENTRE EN PRUEBA registrarUsuarioIncorrecto");
        String datosCuenta = "{ \"u_usuario\" : \"mapg\" ,"
                + " \"u_nombre\" : \"Mariangel2\", \"u_apellido\" : \"Perez2\","
                + " \"u_correo\" : \"mariangel2@hotmail.com\", "
                + "\"u_pregunta\" : \"Nombre de mi mama2\" ,"
                + " \"u_respuesta\" : \"/marisol2\", "
                + "\"u_password\" : \"1234567\" }";
        datosCuenta = URLEncoder.encode(datosCuenta);
        Modulo1sResource instance = new Modulo1sResource();
        String expected = instance.registrarUsuario(datosCuenta);
        Assert.assertEquals("0",expected);
        
    }
    
     /**
     * Metodo para probar el funcionanmiento de la función
     * registrarUsuario() del WebService Modulo1sResources
     * cuando se ingresan valores vacios 
     */
    @Test
    public void testRegistrarUsuarioIncorrectoVacio() {
        System.out.println("ENTRE EN PRUEBA registrarUsuarioIncorrectoVacio");
        String datosCuenta = "";
        datosCuenta = URLEncoder.encode(datosCuenta);
        Modulo1sResource instance = new Modulo1sResource();
        String expected = instance.registrarUsuario(datosCuenta);
         Assert.assertEquals("0",expected);
        
    }


    /**
     * Metodo para probar el correcto funcionamiento de la función
     * recuperarClave() del WebService Modulo1sResources
     */
    @Test
    public void testRecuperarClaveCorrecto() {
        System.out.println("ENTRE EN recuperarClaveCorrecto");
        String datosUsuario = "mapg";

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.recuperarClave(datosUsuario);
        String [ ] data = result.split(":-:");
        JsonReader reader = Json.createReader(new StringReader(data[0]));
        JsonObject jsonObj = reader.readObject();
        reader.close();
         Assert.assertEquals("mapg",jsonObj.getString("u_usuario"));
        
    }
    
     /**
     * Metodo para probar el funcionamiento incorrecto de la función
     * recuperarClave() del WebService Modulo1sResources
     */
    
    
         @Test
    public void testRecuperarClaveIncorrecto() {
        System.out.println("ENTRE EN recuperarClaveIncorrecto");
        String datosUsuario = "UsuarioX";

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.recuperarClave(datosUsuario);
  
           
        Assert.assertEquals("ERROR",result);
        
    }
    
      /**
     * Metodo para probar el funcionamiento de la función
     * recuperarClave() del WebService Modulo1sResources
     * cuando se ingresan valores vacios
     */
    @Test
    public void testRecuperarClaveIncorrectoVacio() {
        System.out.println("ENTRE EN recuperarClaveIncorrectoVacio");
        String datosUsuario = "";
        Modulo1sResource instance = new Modulo1sResource();
        String result = instance.recuperarClave(datosUsuario);
        Assert.assertEquals("ERROR",result);
        
    }

    
    /**
     * Metodo para probar el correcto funcionamiento de la función
     * iniciarSesion() del WebService Modulo1sResources
     */
    @Test
    public void testIniciarSesion() {
        System.out.println("ENTRE EN iniciarSesion");
        String datosUsuario = "{ \"u_usuario\" : \"mapg\" , \"u_password\" "
                + ": \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.iniciarSesion(datosUsuario);
        String [ ] data = result.split(":-:");
        JsonReader reader = Json.createReader(new StringReader(data[0]));
        JsonObject jsonObj = reader.readObject();
        reader.close();

        Assert.assertEquals("mapg,123456",jsonObj.getString("u_usuario")+","
                +jsonObj.getString("u_password"));
        
    }
    
     /**
     * Metodo para probar el funcionamiento incorrecto de la función
     * iniciarSesion() del WebService Modulo1sResources
     */
    
    @Test
    public void testIniciarSesionIncorrecto() {
        System.out.println("ENTRE EN iniciarSesion");
        String datosUsuario = "{ \"u_usuario\" : \"UsuarioX\" , "
                + "\"u_password\" : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.iniciarSesion(datosUsuario);
        Assert.assertEquals("7", result);
       
        
    }
     /**
     * Metodo para probar el funcionamiento de la función
     * iniciarSesion() del WebService Modulo1sResources
     * cuando se ingresan valores vacios
     */
    @Test
    public void testIniciarSesionIncorrectoVacio() {
        System.out.println("ENTRE EN iniciarSesionVacio");
        String datosUsuario = "";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.iniciarSesion(datosUsuario);
        Assert.assertEquals("7", result);
        
    }
    /**
     * Metodo para probar el correcto funcionamiento de la función
     * actualizarClave() del WebService Modulo1sResources
     */
    @Test
    public void testActualizarClave() {
        System.out.println("ENTRE EN actualizarClave");
        String datosUsuario = "{ \"u_usuario\" : \"mapg\" , \"u_password\" "
                + ": \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.actualizarClave(datosUsuario);

        Assert.assertEquals("5", result);
 

    }
    
     /**
     * Metodo para probar el funcionamiento incorrecto de la función
     * actualizarClave() del WebService Modulo1sResources
     */
    @Test
    public void testActualizarClaveIncorrecto() {
        System.out.println("ENTRE EN actualizarClaveIncorrecto");
        String datosUsuario = "{ \"u_usuario\" : \"UsuarioX\" , \"u_password\""
                + " : \"123456\" }";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.actualizarClave(datosUsuario);

        Assert.assertEquals("6", result);
 

    }
    
     /**
     * Metodo para probar el funcionamiento de la función
     * actualizarClave() del WebService Modulo1sResources
     * cuando se ingresan valores vacios
     */
    
    @Test
    public void testActualizarClaveIncorrectoVacio() {
        System.out.println("ENTRE EN actualizarClaveIncorrectoVacio");
        String datosUsuario = "";
        datosUsuario = URLEncoder.encode(datosUsuario);

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.actualizarClave(datosUsuario);

        Assert.assertEquals("6", result);
 

    }
    


    /**
     * Metodo para probar el correcto funcionamiento de la función
     * verificarUsuario() del WebService Modulo1sResources
     */
    
    @Test
    public void testVerificarUsuario() {
        System.out.println("ENTRE EN verificarUsuario");
        String nombreUsuario = "mapg";

        Modulo1sResource instance = new Modulo1sResource();

        String result = instance.verificarUsuario(nombreUsuario);

        Assert.assertEquals("4",result);
      
    }
    
    /**
     * Metodo para probar el funcionamiento incorrecto de la función
     * verificarUsuario() del WebService Modulo1sResources
     */
    @Test
    public void testVerificarUsuarioIncorrecto() {
        System.out.println("ENTRE EN verificarUsuarioIncorrecto");
        String nombreUsuario = "UsuarioX";

        Modulo1sResource instance = new Modulo1sResource();
        String result = instance.verificarUsuario(nombreUsuario);

        Assert.assertEquals("3",result);
       
    }
    
    /**
     * Metodo para probar el funcionamiento de la función
     * verificarUsuario() del WebService Modulo1sResources
     * cuando se ingresan valores vacios
     */
    
     @Test
    public void testVerificarUsuarioIncorrectoVacio() {
        System.out.println("ENTRE EN verificarUsuarioIncorrectoVacio");
        String nombreUsuario = "";
        Modulo1sResource instance = new Modulo1sResource();
        String result = instance.verificarUsuario(nombreUsuario);
        Assert.assertEquals("3",result);
       
    }
    
    
    
}
