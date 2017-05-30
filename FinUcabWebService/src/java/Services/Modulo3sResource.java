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

/**Modulo 3 - Modulo de Presupuestos
 * Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 * Descripción de la clase:
 * Clase encargada de Agregar, modificar, eliminar y visualizar los presupuestos
 * ademas de obtener la lista de categorias
*/

@Path("/Modulo3")
public class Modulo3sResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo3sResource
     */
    public Modulo3sResource() {
    }

    /**
     * Se encarga de devolver los datos del presupuesto solicitado
     * @param nombrePresupuesto
     * @return 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ObtenerPresupuesto")
    public String getObtenerPresupuesto(@QueryParam("nombrePresupuesto") 
            String nombrePresupuesto) {
        //TODO return proper representation object
        String respuesta ="";
        try{
            nombrePresupuesto = nombrePresupuesto.replace('_', ' ');

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT p.pr_nombre,c.ca_id,"
                    + "p.pr_monto,p.pr_duracion,p.pr_clasificacion,"
                    + "c.ca_esingreso FROM Presupuesto p,Categoria c "
                    + "where p.categoriaca_id=c.ca_id and "
                    + "pr_nombre='"+nombrePresupuesto+"';");
            while (rs.next())
            {
                //Creo el objeto Json!             
                 JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                 usuarioBuilder.add("Nombre",rs.getString(1));
                 usuarioBuilder.add("IdCategoria",rs.getString(2));
                 usuarioBuilder.add("Monto",rs.getString(3));
                 usuarioBuilder.add("Duracion",rs.getString(4));
                 usuarioBuilder.add("Clasificacion",rs.getString(5));
                 usuarioBuilder.add("Tipo",rs.getString(6));
                 
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
     * Se encarga de devolver la lista de categorias por usuario
     * @param Usuario
     * @return 
     */
  @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ObtenerSpinnerCategoria")
   public String ObtenerSpinnerCategoria(@QueryParam("usuarioid") String Usuario) {
        //TODO return proper representation object
        String respuesta ="";
        Integer idUsuario = 0;
        try{
            idUsuario = obtenerIdUsuario(Usuario);
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery("Select ca_id|| '- ' || ca_nombre " +
                    "from categoria " +
                    "where usuariou_id="+idUsuario+" and ca_eshabilitado = true "
                    + "and ca_id <> -1;");
            JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rs.next())
            {
                //Creo el objeto Json!             
                 
                 usuarioBuilder.add("Nombre",rs.getString(1));
                 JsonObject usuarioJsonObject = usuarioBuilder.build();  
                arrayBuilder.add(usuarioJsonObject);
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
            
            rs.close();
            st.close();

            return respuesta;
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
    
   
   /**
    * Se encarga de devolver la lista de presupuesto por usuario
    * @param Usuario
    * @return 
    */
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ListaPresupuesto")
   public String getListaPresupuesto(@QueryParam("idUsuario") String Usuario) {

        String respuesta ="";
        Integer idUsuario = 0;
        try{

            idUsuario = obtenerIdUsuario(Usuario);
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();

            
            ResultSet rs = st.executeQuery("SELECT p.pr_nombre,c.ca_nombre,"
                    + "p.pr_monto,p.pr_duracion,p.pr_clasificacion,c.ca_esIngreso "
                    + "FROM Presupuesto p,Categoria c "
                    + "where p.categoriaca_id=c.ca_id and "
                    + "p.usuariou_id="+idUsuario+";");
            
            JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rs.next()){
                //Creo el objeto Json!
                 usuarioBuilder.add("Nombre",rs.getString(1));
                 usuarioBuilder.add("Categoria",rs.getString(2));
                 usuarioBuilder.add("Monto",rs.getString(3));
                 usuarioBuilder.add("Duracion",rs.getString(4));
                 usuarioBuilder.add("Clasificacion",rs.getString(5));
                 usuarioBuilder.add("Tipo",rs.getString(6));
                 JsonObject usuarioJsonObject = usuarioBuilder.build();  
                arrayBuilder.add(usuarioJsonObject);  
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
            
            rs.close();
            st.close();

            return respuesta;
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
   
   /**
    * Se encarga de obtener el id del usuario a partir del nombre de usuario
    * @param nombre
    * @return 
    */
    public Integer obtenerIdUsuario(String nombre){
      Integer id = 0;
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();  
            ResultSet rs = st.executeQuery("select u_id from Usuario "
                    + "where u_usuario='"+nombre+"';");
            while(rs.next()){
                id = Integer.parseInt(rs.getString(1));
                return id;
            }
        } catch (Exception e) {
            //return e.getMessage();
        }
        return null;
    }
   
    
   /**
    * Se encarga de eliminar de la base de datos el presupuesto seleccionado
    * @param nombrePresupuesto
    * @param Usuario
    * @return 
    */
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/EliminarPresupuesto")
    public String getEliminarPresupuesto(@QueryParam("nombrePresupuesto") 
            String nombrePresupuesto,@QueryParam("idUsuario") String Usuario){
        Integer idUsuario=0;
      try {
            idUsuario= obtenerIdUsuario(Usuario);
            nombrePresupuesto = nombrePresupuesto.replace('_', ' ');
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();            
            String query = ("delete from Presupuesto where pr_nombre='"
                    +nombrePresupuesto+"' and usuariou_id='"
                    +idUsuario+"';");
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
    
    
    /**
     * Se encarga de agregar a la base de datos el presupuesto
     * @param nombreusuario
     * @param datosPresupuesto
     * @return 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarPresupuesto")
    public String registrarPresupuesto(@QueryParam("usuarioid")
            String nombreusuario, @QueryParam("datosPresupuesto")
            String datosPresupuesto) {
      
      String decodifico = URLDecoder.decode(datosPresupuesto);
      Integer idUsuario = 0;
      try {
            idUsuario = obtenerIdUsuario(nombreusuario);
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();           
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject presupuestoJSON = reader.readObject();
            reader.close();            
            String query = "insert into presupuesto(pr_nombre,pr_monto,"
                    + "pr_clasificacion,pr_duracion,usuariou_id,categoriaca_id)"
                    + "values ('"+presupuestoJSON.getString("pr_nombre") 
                    + "',"+presupuestoJSON.getString("pr_monto")
                    +",'"+presupuestoJSON.getString("pr_clasificacion")
                    +"',"+presupuestoJSON.getString("pr_duracion")
                    +","+idUsuario+","+
                    presupuestoJSON.getString("categoriaca_id")+");";
            
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
     * Se encarga de modificar el presupuesto seleccionado 
     * @param nombrePresupuesto
     * @param nombreusuario
     * @param datosPresupuesto
     * @return 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ModificarPresupuesto")
    public String modificarPresupuesto(@QueryParam("nombrePresupuesto")
            String nombrePresupuesto,@QueryParam("usuarioid")
            String nombreusuario, @QueryParam("datosPresupuesto")
            String datosPresupuesto) {
      
      String decodifico = URLDecoder.decode(datosPresupuesto);
      Integer idUsuario = 0;
      try {
          idUsuario = obtenerIdUsuario(nombreusuario);
          nombrePresupuesto = nombrePresupuesto.replace('_', ' ');
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();           
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject presupuestoJSON = reader.readObject();
            reader.close();            
          
            String query = "UPDATE presupuesto SET pr_nombre='"+
                    presupuestoJSON.getString("pr_nombre")+"',"
                    + "pr_monto="+presupuestoJSON.getString("pr_monto")+","
                    + "pr_clasificacion='"+
                    presupuestoJSON.getString("pr_clasificacion")+"',"
                    + "pr_duracion="+presupuestoJSON.getString("pr_duracion")+
                    ", categoriaca_id="+
                    presupuestoJSON.getString("categoriaca_id")+ 
                    "WHERE usuariou_id='"+idUsuario+
                    "'and pr_nombre ='"+nombrePresupuesto+"';";

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
    * Se encarga de devolver la lista de presupuesto por usuario
    * @param Usuario
    * @return 
    */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ListaPresupuestoExportar")
   public String getTodoslosPresupuestos(@QueryParam("idUsuario") 
           String Usuario) {
        //TODO return proper representation object
        String respuesta ="";
        Integer idUsuario=0;
        try{
            idUsuario = obtenerIdUsuario(Usuario);
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT p.pr_nombre,c.ca_nombre,"
                    + "p.pr_monto, p.pr_duracion,p.pr_clasificacion,"
                    + "c.ca_esingreso FROM Presupuesto p, Categoria c "
                    + "where p.categoriaca_id=c.ca_id and p.usuariou_id="+
                    idUsuario+" ORDER BY p.pr_clasificacion DESC;");
            
            JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rs.next())
            {
                //Creo el objeto Json!             
                 
                 usuarioBuilder.add("Nombre",rs.getString(1));
                 usuarioBuilder.add("Categoria",rs.getString(2));
                 usuarioBuilder.add("Monto",rs.getString(3));
                 usuarioBuilder.add("Duracion",rs.getString(4));
                 usuarioBuilder.add("Clasificacion",rs.getString(5));
                 usuarioBuilder.add("Tipo",rs.getString(6));
                 JsonObject usuarioJsonObject = usuarioBuilder.build();  
                arrayBuilder.add(usuarioJsonObject);
                 
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
            
            rs.close();
            st.close();

            return respuesta;
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
   
    /**
     * Se encarga de verificar la disponibilidad de un nombre para un 
     * presupuesto
     * @param nombrePresupuesto
     * @return 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/VerificarNombre")
    public String VerificarNombre(@QueryParam("nombrePresupuesto")
            String nombrePresupuesto) {
      try {
            nombrePresupuesto = nombrePresupuesto.replace('_', ' ');
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();    
            String query = "select pr_nombre from Presupuesto where "
                    + "pr_nombre='"+nombrePresupuesto+"';";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                return "Repetido";
            }            
               return "No Repetido";
            
        } catch (Exception e) {
            return e.getMessage();
        }
    }    
    
    
    
    /**
     * POST method for creating an instance of Modulo3Resource
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
    public Modulo3Resource getModulo3Resource(@PathParam("id") String id) {
        return Modulo3Resource.getInstance(id);
    }
}
