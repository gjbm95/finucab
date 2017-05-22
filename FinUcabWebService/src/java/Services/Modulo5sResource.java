/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.Conexion;
import java.io.StringReader;
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
@Path("/Modulo5")
public class Modulo5sResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo5sResource
     */
    public Modulo5sResource() {
    }

    /**
     * Retrieves representation of an instance of Services.Modulo5sResource
     * @return an instance of javax.json.Json
     */
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba")
    public String getPruebaJson() {
        //TODO return proper representation object
        JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
        usuarioBuilder.add("Nombre","Jose");
        usuarioBuilder.add("Apellido","Rodriguez");
        usuarioBuilder.add("Usuario","jose123");
        JsonObject usuarioJsonObject = usuarioBuilder.build();
       return usuarioJsonObject.toString();
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
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarPago")
    public String registrarPago(@QueryParam("datosPago") String datosPagos) {

        String decodifico = URLDecoder.decode(datosPagos);

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject pagoJSON = reader.readObject();
            reader.close();
            String query = "INSERT INTO pago ( pg_monto , pg_tipoTransaccion , categoriaca_id , pg_descripcion ) "
                    + "VALUES ( '" + Float.valueOf(pagoJSON.getString("pg_monto")) + "' , '" + pagoJSON.getString("pg_tipoTransaccion") + "' , "
                    + "'" + Integer.parseInt(pagoJSON.getString("categoriaca_id")) + "' , '" + pagoJSON.getString("pg_descripcion") + "' );";

            if (st.executeUpdate(query) > 0) {
                st.close();
                return "Registro exitoso";
            } else {
                st.close();
                return "No se pudo registrar";
            }

        } catch (Exception e) {

            return e.getMessage();

        }
    }
    
    /**
     * POST method for creating an instance of Modulo5Resource
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
    public Modulo5Resource getModulo5Resource(@PathParam("id") String id) {
        return Modulo5Resource.getInstance(id);
    }
}