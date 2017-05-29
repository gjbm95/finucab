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
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
    
    /**
     * Función que registra un pago en la base de datos.
     *
     * @param datosPago JSON.toString() con los atributos: pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Si se inserta el pago devuelve un String con el mensaje
     * "Registro Exitoso", De lo contrario devuelve el mensaje "No se pudo
     * registrar"
     */
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
                    + "VALUES ( '" + pagoJSON.getInt("pg_monto") + "' , '" + pagoJSON.getString("pg_tipoTransaccion") + "' , '"
                    + pagoJSON.getInt("pg_categoria") 
                    + "' , '" + pagoJSON.getString("pg_descripcion") + "' );";

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
     * Función que consulta un pago seleccionado en la base de datos.
     *
     * @param consultarPago con los atributos: pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Si encuentra el pago devuelve los datos del pago.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarPago")
    public String consultarPago(@QueryParam("datosPago") int idPago) {

        System.out.println(idPago);
        String respuesta ="";

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT pg_id, pg_monto, pg_tipoTransaccion, categoriaca_id, pg_descripcion"
                         + " FROM pago WHERE pg_id = " + idPago);

             JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();
             //int cont = 1;
            while (rs.next())
            {
                //Creo el objeto Json!             
                 pagoBuilder.add("pg_id",rs.getInt(1));
                 System.out.println(rs.getInt(1));
                 pagoBuilder.add("pg_monto",rs.getFloat(2));
                 System.out.println(rs.getFloat(2));
                 pagoBuilder.add("pg_tipoTransaccion",rs.getString(3));
                 pagoBuilder.add("pg_categoria",rs.getInt(4));
                 System.out.println(rs.getString(4));
                 pagoBuilder.add("pg_descripcion",rs.getString(5));
                 System.out.println(rs.getString(5));
                 JsonObject pagoJsonObject = pagoBuilder.build();  
                 respuesta = pagoJsonObject.toString();
                 
            }
            rs.close();
            st.close();
            System.out.println(respuesta);
            return respuesta;
        

        } catch (Exception e) {

            return e.getMessage();

        }
    }
   
    /**
     * Función que visualiza los pagos.
     *
     * @param visualizarPago con los atributos: pg_id, pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Lista de pagos por Usuario
     */
     @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/visualizarPago")
    public String visualizarPago(@QueryParam("datosPago") int idUsuario) {
   
        String respuesta ="";
        
        try{
                    
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT pg_id, pg_monto, pg_tipoTransaccion, categoriaca_id, pg_descripcion "
                    + "FROM Pago, Categoria WHERE categoriaca_id = ca_id AND usuariou_id = "+ idUsuario);

             JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();
             JsonArrayBuilder list = Json.createArrayBuilder();
        
            while (rs.next())
            {
                //Creo el objeto Json!             
                 pagoBuilder.add("pg_id",rs.getInt(1));
                 System.out.println(rs.getInt(1));
                 pagoBuilder.add("pg_monto",rs.getFloat(2));
                 System.out.println(rs.getFloat(2));
                 pagoBuilder.add("pg_tipoTransaccion",rs.getString(3));
                 pagoBuilder.add("pg_categoria",rs.getInt(4));
                 System.out.println(rs.getString(4));
                 pagoBuilder.add("pg_descripcion",rs.getString(5));
                 System.out.println(rs.getString(5));
                 JsonObject pagoJsonObject = pagoBuilder.build();  
                 respuesta = pagoJsonObject.toString();
                 
                 list.add( respuesta);
                
            }
            rs.close();
            st.close();
            JsonArray listJsonObject = list.build();
            String resp = listJsonObject.toString();
            System.out.println(resp);
            return resp;
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
      
    /**
     * Función que modificar un pago.
     *
     * @param modificararPago con los atributos: pg_id, pg_monto, pg_tipoTransaccion,
     * pg_categoria , pg_descripcion
     *
     * @return Lista de pagos por Usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/modificarPago")
    public String modificarPago(@QueryParam("datosPago") String datosPagos) {
        System.out.println(datosPagos);
        String decodifico = URLDecoder.decode(datosPagos);

        try {
           
            Connection conn = Conexion.conectarADb();
           
            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject pagoJSON = reader.readObject();
            System.out.println(pagoJSON);
            reader.close();
            String query = "UPDATE pago SET "
                    + "pg_monto = '" + pagoJSON.getInt("pg_monto")
                    + "', pg_tipoTransaccion = '" + pagoJSON.getString("pg_tipoTransaccion") 
                    + "', categoriaca_id= " + pagoJSON.getInt("pg_categoria")
                    + ",pg_descripcion = '" + pagoJSON.getString("pg_descripcion") +
                    "' WHERE "
                    + "pg_id = " + pagoJSON.getInt("pg_id");
            
               System.out.println(query);
                       
            //System.out.println(query);
           
            if (st.executeUpdate(query) > 0) {
                st.close();
                System.out.println("modificacion exitosa");
                return "Modificacion exitosa";
            } else {
                st.close();
                System.out.println("no se pudo modificar");
                return "No se pudo modificar";
                
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