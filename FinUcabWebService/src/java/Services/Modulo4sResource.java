package Services;

import DataBase.Conexion;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("/Modulo4")
public class Modulo4sResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo4sResource
     */
    public Modulo4sResource() {
    }

    /**
     * Retrieves representation of an instance of Services.Modulo4sResource
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
     * Funcion que registra una categoria creada por el usuario en la base de datos
     * 
     *
     * @param datosCategoria JSON.toString() con los atributos: c_uduario, c_nombre, c_descripcion
     * , c_ingreso, c_estado
     *
     * @return Si se inserta la categoria devuelve un String con el mensaje
     * "Registro Exitoso", De lo contrario devuelve el mensaje "No se pudo
     * registrar"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarCategoria")
    public String registrarCategoria(@QueryParam("datosCategoria") String datosCategoria) {
        System.out.println(datosCategoria);
        String decodifico = URLDecoder.decode(datosCategoria);

        try {
           
            Connection conn = Conexion.conectarADb();
           
            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();
           
            reader.close();
            String query = "INSERT INTO categoria (usuariou_id, ca_nombre , c_descripcion , ca_esingreso , ca_eshabilitado  ) "
                    + "VALUES ( " + categoriaJSON.getInt("c_usuario") + " , '" + categoriaJSON.getString("c_nombre") + "' , '" + categoriaJSON.getString("c_descripcion") 
                    + "' , " + "'" + categoriaJSON.getBoolean("c_ingreso") + "' , '" + categoriaJSON.getBoolean("c_estado")  + "');";
                       
            System.out.println(query);
           
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
     * Función que elimina una categoria y modifica las tablas donde se encontraba esa categoria
     * Siempre debe existir una categoria con id -1 para modificar cuando se elimine la categoria
     *
     * @param datosCategoria JSON.toString() con los atributos: c_id
     * @metodo eliminarCategoria2 para modificar todas las tablas donde aparecia la categoria.
     *
     * @return Si el registro fue borrado exitosamente devuelve un String
     *"Borado exitoso" de lo contrario devuelve "No se pudo borrar"
     * 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarCategoria")
    public String eliminarCategoria(@QueryParam("datosCategoria") String datosCategoria) {

        String decodifico = URLDecoder.decode(datosCategoria);
        EliminarCategoria2(decodifico, "presupuesto");
        EliminarCategoria2(decodifico,"pago");
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
           
            String query = "DELETE FROM categoria WHERE ca_id =" + decodifico  + ";";
            
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
     * Función que modifica todas las tablas donde aparecia la categoria a eliminar
     * 
     *
     * @param  id, tabla
     * 
     * 
     *
     * @return si se modifica las tablas donde aparecia la categoria a eliminar
     * devuelve un boolean true, en caso contrario devuelve false
     * 
     */
    public boolean EliminarCategoria2 (String id, String tabla){
        try{
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            String query = "UPDATE "+tabla+" SET "
                    + "categoriaca_id = " + -1 + 
                    " WHERE "
                    + "categoriaca_id = " + id + ";";
            if (st.executeUpdate(query) > 0) {
                st.close();
                return true;
            } else {
                st.close();
                return false;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false ;

        }
    }
    
      /**
     * Función que permite visualizar todas las categoria que posee un usuaria
     * 
     *
     * @param usuario JSON.toString() con los atributos: c_id
     * 
     * 
     *
     * @return devuelve una lista con todas las categoria que posee un usuario
     * en caso de no poseer categorias creadas devuelve null
     * 
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/visualizarCategoria")
    public String VisualizarCategoria(@QueryParam("datosCategoria") String usuario) {
              
        String decodifico = URLDecoder.decode(usuario);
       
        String respuesta ="";
        
        try{
                    
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Categoria WHERE ca_id <> -1  AND usuariou_id = '" + decodifico + "';");
            
            
             JsonObjectBuilder categoriaBuilder = Json.createObjectBuilder();
             JsonArrayBuilder list = Json.createArrayBuilder();
             int cont = 1;
            while (rs.next())
            {
                //Creo el objeto Json!             
                 
                 categoriaBuilder.add("Id",rs.getInt(1));
                 System.out.println(rs.getInt(1));
                 categoriaBuilder.add("Nombre",rs.getString(2));
                 System.out.println(rs.getString(2));
                 categoriaBuilder.add("Descripcion",rs.getString(3));
                 categoriaBuilder.add("esIngreso",rs.getBoolean(5));
                 categoriaBuilder.add("esHabilitado",rs.getBoolean(4));
                 JsonObject categoriaJsonObject = categoriaBuilder.build();  
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

     /**
     * Función que modifica en la base de datos atributos de categoria creados por el usuario
     * 
     *
     * @param datosCategoria JSON.toString() con los atributos: c_id, c_nombre, c_descripcion
     * ,c_ingreso, c_estado
     * 
     * 
     *
     * @return Si se modifica la categoria devuelve un String con el mensaje
     * "Modificacion exitosa", De lo contrario devuelve el mensaje "No se pudo
     * modificar"
     */
   @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/modificarCategoria")
    public String modificarCategoria(@QueryParam("datosCategoria") String datosCategoria) {
        String decodifico = URLDecoder.decode(datosCategoria);

        try {
           
            Connection conn = Conexion.conectarADb();
           
            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();
            reader.close();
            String query = "UPDATE categoria SET "
                    + "ca_nombre = '" + categoriaJSON.getString("c_nombre")
                    + "', c_descripcion = '" + categoriaJSON.getString("c_descripcion") 
                    + "', ca_esingreso = " + categoriaJSON.getBoolean("c_ingreso")
                    + ",ca_eshabilitado = " + categoriaJSON.getBoolean("c_estado") +
                    " WHERE "
                    + "ca_id = " + categoriaJSON.getInt("c_id") + ";";
            
               
                       
            //System.out.println(query);
           
            if (st.executeUpdate(query) > 0) {
                st.close();
                return "Modificacion exitosa";
            } else {
                st.close();
                return "No se pudo modificar";
                
            }

        } catch (Exception e) {

            return e.getMessage();

        }
    }
    
        /**
     * Función que busca y devuelve una categoria por su id
     *
     * @param datosCategoria JSON.toString() con los atributos: c_id
     * @metodo eliminarCategoria2 para modificar todas las tablas donde aparecia la categoria.
     *
     * @return Si el registro fue borrado exitosamente devuelve un String
     *con la categoria si se encontro de lo contrario devuelve null
     * 
     */
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/buscarCategoria")
    public String buscarCategoria(@QueryParam("datosCategoria") String datosCategoria){
        String decodifico = URLDecoder.decode(datosCategoria);
        try{
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Categoria WHERE ca_id = '" + decodifico + "';");
            JsonObjectBuilder categoriaBuilder = Json.createObjectBuilder();
            while (rs.next())
            {
            categoriaBuilder.add("Id",rs.getInt(1));
            System.out.println(rs.getInt(1));
            categoriaBuilder.add("Nombre",rs.getString(2));
            System.out.println(rs.getString(2));
            categoriaBuilder.add("Descripcion",rs.getString(3));
            categoriaBuilder.add("esIngreso",rs.getBoolean(5));
            categoriaBuilder.add("esHabilitado",rs.getBoolean(4));
            categoriaBuilder.add("usuariou_id",rs.getInt(6));
            JsonObject categoriaJsonObject = categoriaBuilder.build();  
            String  respuesta = categoriaJsonObject.toString();
            System.out.println(respuesta);
            return respuesta;
            }
        }
        catch(Exception e) {
            return e.getMessage();
        }
        return null;
    }

    /**
     * POST method for creating an instance of Modulo4Resource
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
    public Modulo4Resource getModulo4Resource(@PathParam("id") String id) {
        return Modulo4Resource.getInstance(id);
    }

}
