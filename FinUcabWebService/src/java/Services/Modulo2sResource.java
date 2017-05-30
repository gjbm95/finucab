/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.Conexion;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author AlejandroNegrin
 */
@Path("/Modulo2")
public class Modulo2sResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo2sResource
     */
    public Modulo2sResource() {
    }

    /**
     * Retrieves representation of an instance of Services.Modulo2sResource
     * @return an instance of javax.json.Json
     */
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/modificarUsuario")
    public String modificarUsuario(@QueryParam("datosUsuario") String datosCuenta) {

        String decodifico = URLDecoder.decode(datosCuenta);

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject usuarioJSON = reader.readObject();
            reader.close();
            String query = "UPDATE FROM TABLE usuario SET u_nombre = '"
                    +usuarioJSON.getString("u_nombre")+"', u_apellido = '" 
                    +usuarioJSON.getString("u_apellido")+"', u_correo = '"
                    +usuarioJSON.getString("u_correo")+"', u_pregunta = '"
                    +usuarioJSON.getString("u_pregunta")+"', u_respuesta = '"
                    +usuarioJSON.getString("u_respuesta") +"', u_password = '"
                    +usuarioJSON.getString("u_password")+"' WHERE u_usuario = '"
                    +usuarioJSON.getString("u_usuario")+"';";

            if (st.executeUpdate(query) > 0) {
                st.close();
                return "Se modifico usuario"+ usuarioJSON.getString("u_usuario") ;
            } else {
                st.close();
                return "No se pudo modificar";
            }

        } catch (Exception e) {

            return e.getMessage();

        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarUsuario")
    public String consultarUsuario(@QueryParam("nombreUsuario") String usuario) {
        //TODO return proper representation object
        String respuesta ="";
        try{

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT u_nombre, u_apellido, u_correo, u_pregunta, u_respuesta, u_password FROM Usuario WHERE u_usuario = '"+usuario+"'");
            while (rs.next())
            {
                //Creo el objeto Json!             
                 JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                 usuarioBuilder.add("Nombre",rs.getString(1));
                 usuarioBuilder.add("Apellido",rs.getString(2));
                 usuarioBuilder.add("Correo",rs.getString(3));
                 usuarioBuilder.add("Pregunta",rs.getString(4));
                 usuarioBuilder.add("Respuesta", rs.getString(5));
                 usuarioBuilder.add("Contrasena",rs.getString(6));
                 
                 JsonObject usuarioJsonObject = usuarioBuilder.build();  
                 respuesta = usuarioJsonObject.toString();
            }
            rs.close();
            st.close();

            return respuesta+"-:-Perfil";
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/pruebaConsultar")
    public String pruebaConsultar() {
        //TODO return proper representation object
        String respuesta ="";
        try{

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT u_nombre, u_apellido, u_correo, u_pregunta, u_respuesta, u_password FROM Usuario WHERE u_usuario = 'q';");
            while (rs.next())
            {
                //Creo el objeto Json!             
                 JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                 usuarioBuilder.add("Nombre",rs.getString(1));
                 usuarioBuilder.add("Apellido",rs.getString(2));
                 usuarioBuilder.add("Correo",rs.getString(3));
                 usuarioBuilder.add("Pregunta",rs.getString(4));
                 usuarioBuilder.add("Respuesta", rs.getString(5));
                 usuarioBuilder.add("Contrasena",rs.getString(6));
                 
                 JsonObject usuarioJsonObject = usuarioBuilder.build();  
                 respuesta = usuarioJsonObject.toString();
            }
            rs.close();
            st.close();

            return respuesta;
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/pruebaDB")
    public String getPruebaDataBase() {
        //TODO return proper representation object
        String respuesta ="";
        try{

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Usuario;");
            while (rs.next())
            {
                //Creo el objeto Json!             
                 JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                 usuarioBuilder.add("Nombre",rs.getString(3));
                 usuarioBuilder.add("Apellido",rs.getString(4));
                 usuarioBuilder.add("Usuario",rs.getString(2));
                 JsonObject usuarioJsonObject = usuarioBuilder.build();  
                 respuesta = usuarioJsonObject.toString();
            }
            rs.close();
            st.close();

            return respuesta;
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    /**
     * POST method for creating an instance of Modulo2Resource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(Json content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public Modulo2Resource getModulo2Resource(@PathParam("id") String id) {
        return Modulo2Resource.getInstance(id);
    }
}
