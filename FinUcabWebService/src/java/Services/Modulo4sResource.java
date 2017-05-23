
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
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarCategoria")
    public String registrarCategoria(@QueryParam("datosCategoria") String datosCategoria) {

        String decodifico = URLDecoder.decode(datosCategoria);

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();
            reader.close();
            String query = "INSERT INTO categoria ( ca_nombre , c_descripcion , ca_tipo , ca_habilitado , usuariou_id ) "
                    + "VALUES ( '" + categoriaJSON.getString("ca_nombre") + "' , '" + categoriaJSON.getString("c_descripcion") + "' , "
                    + "'" + categoriaJSON.getString("ca_tipo") + "' , '" + categoriaJSON.getString("ca_habilitado") + "' , '" + categoriaJSON.getString("usuariou_id") + "');";

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
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarCategoria")
    public String eliminarCategoria(@QueryParam("datosCategoria") String datosCategoria) {

        String decodifico = URLDecoder.decode(datosCategoria);

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();
            reader.close();
            String query = "DELETE FROM categoria WHERE ca_id ='" + categoriaJSON.getString("ca_id")  + "');";

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
    
     @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarCategoria")
    public String mostrarCategoria(@QueryParam("datosCategoria") String datosCategoria , String usuario) {
        //TODO return proper representation object
    
        String decodifico = URLDecoder.decode(datosCategoria);
        String respuesta = "";
        
        try {

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery ("SELECT ca_nombre, c_descripcion  FROM Usuario Categoria WHERE u_usuario = usuariou_id AND  u_usuario ='" + usuario + "';");
            while (rs.next()) {
                //Creo el objeto Json!             
                JsonObjectBuilder categoriaBuilder = Json.createObjectBuilder();
                categoriaBuilder.add("Nombre", rs.getString(0));
                categoriaBuilder.add("Descripcion", rs.getString(1));
                categoriaBuilder.add("Estado", rs.getString(2));
                JsonObject categoriaJsonObject = categoriaBuilder.build();
                respuesta = categoriaJsonObject.toString();

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


