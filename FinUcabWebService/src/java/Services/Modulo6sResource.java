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
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
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
import javax.ws.rs.PUT;
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
@Path("/Modulo6")
public class Modulo6sResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo6sResource
     */
    public Modulo6sResource() {
    }

    /**
     * Retrieves representation of an instance of Services.Modulo6sResource
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
     * POST method for creating an instance of Modulo6Resource
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
     * @param id
     * @return 
     */
    @Path("{id}")
    public Modulo6Resource getModulo6Resource(@PathParam("id") String id) {
        return Modulo6Resource.getInstance(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarPlanificacion/{id : \\d+}")
    public String registarPlanificacion (@QueryParam("datosPlanificacion") String datosPlanificacion,
            @PathParam("id") String usuario)
            throws SQLException, SQLTimeoutException
    {
        System.out.println(datosPlanificacion);
        String decodifico = URLDecoder.decode(datosPlanificacion);
        try {     
            Connection conn = Conexion.conectarADb();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject planificacionJSON = reader.readObject();
            Statement st = conn.createStatement();
            
            String query = "INSERT INTO planificacion ( Pa_nombre , Pa_descripcion , "
                    + "Pa_monto , Pa_fechaInicio , Pa_fechaFin , Pa_recurrente , "
                    + "Pa_recurrencia , CategoriaCa_id , Pa_activo , UsuarioU_id ) "
                    + "VALUES ( '" +planificacionJSON.getString("Pa_nombre")+ "' , '"
                    + "" +planificacionJSON.getString("Pa_descripcion")+ "' , "
                    + "'" +planificacionJSON.getJsonNumber("Pa_monto").doubleValue()+ ""
                    + "' , '" +planificacionJSON.getJsonObject("Pa_fechaInicio")+ "' , "
                    + "'"+planificacionJSON.getJsonObject("Pa_fechaFin")+"' , "
                    + "'"+planificacionJSON.getBoolean("Pa_recurrente")+"' , "
                    + "'"+planificacionJSON.getString("Pa_recurrencia")+"' , "
                    + "'"+planificacionJSON.getInt("categoriaId")+"' , "
                    + "'true' , '"+usuario+"');";
            System.out.println(query);
            
            
            if (st.executeUpdate(query) > 0) {
                st.close();
                System.out.println("creacion exitosa");
                return "registro exitoso";
            } else {
                st.close();
                System.out.println("no se pudo crear");
                return "registro no exitoso";
                
            }

        } catch (Exception e) {

            return e.getMessage();

        }
        
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/listarPlanificacion/{id : \\d+}")
    public String listarPlanificacion(@PathParam("id") String usuario)
            throws SQLException, SQLTimeoutException
    {
        String respuesta ="";
        
        try{
                    
            Connection conn = Conexion.conectarADb();
            
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM Planificacion "
                    + "WHERE Pa_activo <> false  "
                    + "AND UsuarioU_id = '" + usuario + "';");
            
            
            JsonObjectBuilder planificacionBuilder = Json.createObjectBuilder();
            
            JsonArrayBuilder list = Json.createArrayBuilder();
            
            while (rs.next())
            {
                 planificacionBuilder.add("Id",rs.getInt("Pa_id"));
                 planificacionBuilder.add("Nombre",rs.getString("Pa_nombre"));
                 planificacionBuilder.add("Descripcion",rs.getString("Pa_descripcion"));
                 planificacionBuilder.add("Monto",rs.getDouble("Pa_monto"));
                 planificacionBuilder.add("Fecha Inicio",rs.getDate("Pa_fechaInicio").toString());
                 planificacionBuilder.add("Fecha fin", rs.getDate("Pa_fechaFin").toString());
                 planificacionBuilder.add("Recurrente", rs.getBoolean("Pa_recurrente"));
                 planificacionBuilder.add("Recurrencia", rs.getString("Pa_recurrencia"));
                 
                 JsonObject categoriaJsonObject = planificacionBuilder.build();  
                 respuesta = categoriaJsonObject.toString();
                 
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
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/modificarPlanificacion")
    public boolean modificarPlanificacion (@QueryParam("datosPlanificacion") String datosPlanificacion)
            throws SQLException, SQLTimeoutException
    {
        System.out.println(datosPlanificacion);
        String decodifico = URLDecoder.decode(datosPlanificacion);
        
        try {     
            
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject planificacionJSON = reader.readObject();
            Connection conn = Conexion.conectarADb();
            
            Double monto = Double.parseDouble(planificacionJSON.getString("pa_monto"));
           
            Statement st = conn.createStatement();
            
            String query = "UPDATE Planificacion SET "
                    + "pa_nombre = '" + planificacionJSON.getString("pa_nombre")
                    + "', pa_descripcion = '" + planificacionJSON.getString("pa_descripcion")
                    + "', pa_monto = " + monto
                    + ", pa_fechainicio = " + " to_date('" + planificacionJSON.getString("pa_fechainicio") + "', 'DD-MM-YYYY') "
                    + ", pa_fechafin = " + " to_date('" + planificacionJSON.getString("pa_fechafin") + "', 'DD-MM-YYYY') "
                    + ", pa_recurrente = " + planificacionJSON.getBoolean("pa_recurrente")
                    + ", pa_recurrencia = '" + planificacionJSON.getString("pa_recurrencia")
                    + "', usuariou_id = " + planificacionJSON.getInt("usuariou_id")
                    + ", categoriaca_id = " + planificacionJSON.getInt("categoriaId")
                    + ", pa_activo = " + planificacionJSON.getBoolean("Pa_activo")
                    + " WHERE "
                    + "pa_id = " + planificacionJSON.getString("pa_id") + ";";
            System.out.println(query);
           
            if (st.executeUpdate(query) > 0) {
                st.close();
                System.out.println("modificacion exitosa");
                return true;
            } else {
                st.close();
                System.out.println("no se pudo modificar");
                return false;
                
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
        
    }
    
      
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/visualizarPlanificacion")
    public String VisualizarPlanificacion(@QueryParam("datosPlanificacion") String usuario) {
              
        String decodifico = URLDecoder.decode(usuario);
       
        String respuesta ="";
        
        try{
                    
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Planificacion WHERE pa_id <> -1  AND usuariou_id = '" + decodifico + "';");
            
            
             JsonObjectBuilder planificacionBuilder = Json.createObjectBuilder();
             JsonArrayBuilder list = Json.createArrayBuilder();
             int cont = 1;
            while (rs.next())
            {
                //Creo el objeto Json!             
                 
                 planificacionBuilder.add("Id",rs.getInt(1));
                 System.out.println(rs.getInt(1));
                 planificacionBuilder.add("Nombre",rs.getString(2));
                 System.out.println(rs.getString(2));
                 planificacionBuilder.add("Descripcion",rs.getString(3));
                 planificacionBuilder.add("Monto",rs.getDouble(4));
                 planificacionBuilder.add("fechaInicio",  rs.getString(5));
                 planificacionBuilder.add("fechaFin", rs.getString(6));
                 planificacionBuilder.add("Recurrente",rs.getBoolean(7));
                 planificacionBuilder.add("Recurrencia",rs.getString(8));
                 planificacionBuilder.add("Usuario",rs.getInt(9));
                 planificacionBuilder.add("Categoria",rs.getInt(10));
                 planificacionBuilder.add("Activo",rs.getBoolean(11));
                 JsonObject planificacionJsonObject = planificacionBuilder.build();  
                 respuesta = planificacionJsonObject.toString();
                 
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
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarPlanificacion")
    public String registrarPlanificacion(@QueryParam("datosPlanificacion") String datosPlanificacion) {
        System.out.println(datosPlanificacion);
        String decodifico = URLDecoder.decode(datosPlanificacion);

        try {

            Connection conn = Conexion.conectarADb();

            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();
            Double monto = Double.parseDouble(categoriaJSON.getString("pa_monto"));

            reader.close();
            String query = "INSERT INTO planificacion (pa_nombre, pa_descripcion, pa_monto, pa_fechainicio, pa_fechafin, "
                    + "pa_recurrente, pa_recurrencia, usuariou_id, categoriaca_id, pa_activo ) " + "VALUES ( '" + categoriaJSON.getString("pa_nombre") + "' , '" + categoriaJSON.getString("pa_descripcion") + "' , " + monto + ", "
                    + " to_date('" + categoriaJSON.getString("pa_fechainicio") + "', 'DD-MM-YYYY'), to_date('" + categoriaJSON.getString("pa_fechafin") + "', 'DD-MM-YYYY'),"
                    + categoriaJSON.getBoolean("pa_recurrente") + ", ' " + categoriaJSON.getString("pa_recurrencia") + " ', " + categoriaJSON.getInt("usuariou_id") + ", "
                    + categoriaJSON.getInt("categoriaca_id") + ", " + categoriaJSON.getBoolean("pa_activo") + " );";

            System.out.println(query);

            if (st.executeUpdate(query) > 0) {
                st.close();
                return "Se agrego la planificacion correctamente";
            } else {
                st.close();
                return "Ha ocurrido un problema";
            }

        } catch (Exception e) {

            return e.getMessage();

        }
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/buscarPlanificacion")
    public String buscarPlanificacion(@QueryParam("datosPlanificacion") String datosPlanificacion){
        String decodifico = URLDecoder.decode(datosPlanificacion);
        try{
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Planificacion WHERE pa_id = '" + decodifico + "';");
            JsonObjectBuilder planificacionBuilder = Json.createObjectBuilder();
            while (rs.next())
            {
        
            planificacionBuilder.add("Id",rs.getInt(1));
                 System.out.println(rs.getInt(1));
                 planificacionBuilder.add("Nombre",rs.getString(2));
                 System.out.println(rs.getString(2));
                 planificacionBuilder.add("Descripcion",rs.getString(3));
                 planificacionBuilder.add("Monto",rs.getDouble(4));
                 planificacionBuilder.add("fechaInicio",  rs.getString(5));
                 planificacionBuilder.add("fechaFin", rs.getString(6));
                 planificacionBuilder.add("Recurrente",rs.getBoolean(7));
                 planificacionBuilder.add("Recurrencia",rs.getString(8));
                 planificacionBuilder.add("Usuario",rs.getInt(9));
                 planificacionBuilder.add("Categoria",rs.getInt(10));
                 planificacionBuilder.add("Activo",rs.getBoolean(11));
                 JsonObject planificacionJsonObject = planificacionBuilder.build();  
                 String respuesta = planificacionJsonObject.toString();
                 
                 System.out.println(respuesta);
                 return respuesta;
            
            }
        }
        catch(Exception e) {
            return e.getMessage();
        }
        return null;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarPlanificacion")
    public String eliminarPlanificacion(@QueryParam("datosPlanificacion") String datosPlanificacion) {

        String decodifico = URLDecoder.decode(datosPlanificacion);
        
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
           
            String query = "DELETE FROM Planificacion WHERE pa_id =" + decodifico  + ";";
            
            if (st.executeUpdate(query) > 0) {
                st.close();
                return "Borrado exitoso";
            } else {
                st.close();
                return "No se pudo borrar";
            }

        } catch (Exception e) {

            return e.getMessage();

        }
    }
}
