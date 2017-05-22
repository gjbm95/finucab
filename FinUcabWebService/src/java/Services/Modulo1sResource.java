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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author AlejandroNegrin
 */
@Path("/Modulo1")
public class Modulo1sResource {

    @Context
    private UriInfo context;
    private boolean suiche;

    /**
     * Creates a new instance of Modulo1sResource
     */
    public Modulo1sResource() {
    }

    /**
     * Retrieves representation of an instance of Services.Modulo1sResource
     *
     * @return an instance of javax.json.Json
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba")
    public String getPruebaJson() {
        //TODO return proper representation object
        JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
        usuarioBuilder.add("Nombre", "Jose");
        usuarioBuilder.add("Apellido", "Rodriguez");
        usuarioBuilder.add("Usuario", "jose123");
        JsonObject usuarioJsonObject = usuarioBuilder.build();
        return usuarioJsonObject.toString();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("pruebaData")
    public String getData(@QueryParam("objeto") String objeto) {
        return "";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/pruebaDB")
    public String getPruebaDataBase() {
        //TODO return proper representation object
        String respuesta = "";
        try {

            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            //Se coloca el query
            ResultSet rs = st.executeQuery("SELECT * FROM Usuario;");
            while (rs.next()) {
                //Creo el objeto Json!             
                JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
                usuarioBuilder.add("Nombre", rs.getString(3));
                usuarioBuilder.add("Apellido", rs.getString(4));
                usuarioBuilder.add("Usuario", rs.getString(2));
                JsonObject usuarioJsonObject = usuarioBuilder.build();
                respuesta = usuarioJsonObject.toString();

            }
            rs.close();
            st.close();

            return respuesta;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Función que registra un usuario en la base de datos si esta disponible su
     * nombre de usuario.
     *
     * @param datosCuenta JSON.toString() con los atributos: u_usuario, u_nombre
     * , u_apellido , u_correo , u_pregunta , u_respuesta , u_pregunta ,
     * u_password
     *
     * @return Si se inserta el usuario devuelve un String con el mensaje
     * "Registro Exitoso", De lo contrario devuelve el mensaje "No se pudo
     * registrar"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarUsuario")
    public String registrarUsuario(@QueryParam("datosUsuario") String datosCuenta) {

        String decodifico = URLDecoder.decode(datosCuenta);

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            JsonObject usuarioJSON = this.stringToJSON(decodifico);
            String query = "INSERT INTO usuario ( u_usuario , u_nombre , u_apellido , u_correo , u_pregunta , u_respuesta , u_password ) "
                    + "VALUES ( '" + usuarioJSON.getString("u_usuario") + "' , '" + usuarioJSON.getString("u_nombre") + "' , "
                    + "'" + usuarioJSON.getString("u_apellido") + "' , '" + usuarioJSON.getString("u_correo") + "' , "
                    + "'" + usuarioJSON.getString("u_pregunta") + "' , '" + usuarioJSON.getString("u_respuesta") + "' , "
                    + "'" + usuarioJSON.getString("u_password") + "' );";

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
     * Funcion que verifica disponibilidad de nombre de usuario.
     *
     * @param usuario String del nombre de usuario a consultar.
     * @return Si el nombre ya existe devuelve "No Disponible", Si no existe
     * devuelve "Usuario Disponible"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/verificarUsuario")
    public String verificarUsuario(@QueryParam("nombreUsuario") String usuario) {
        int cantidadExistente = 0;

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            String query = "SELECT * from Usuario WHERE u_usuario ='" + usuario + "';";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                return "No Disponible";
            }

            return "Usuario Disponible";

        } catch (SQLException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    /**
     * Función que verifica existencia de un usuario en el sistema y de existir
     * verifica si el password recibido es igual al que está almacenado en la BD
     *
     * @param usuario JSON.toString() con los "atributos" u_usuario y u_password
     * @return De existir el usuario y la contraseña coincide retorna un JSON en
     * String con todos los datos del usuario. De lo contrario retorna el String
     * "ERROR"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/iniciarSesion")
    public String iniciarSesion(@QueryParam("datosUsuario") String usuario) {

//        String decodifico = "{ \"u_usuario\" : \"AleNegrin\" , \"u_password\" : \"123456\" }";
        String decodifico = URLDecoder.decode(usuario);
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();

            JsonObject usuarioJSON = this.stringToJSON(decodifico);

            String query = "SELECT * from Usuario WHERE u_usuario ='" + usuarioJSON.getString("u_usuario") + "';";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                JsonObject usuarioJsonObject = this.crearUsuarioJson(rs);

                if (usuarioJsonObject.getString("u_usuario").equals(usuarioJSON.getString("u_usuario"))) {
                    return usuarioJsonObject.toString();
                } else {
                    return "ERROR";
                }
            }

            return "ERROR";
        } catch (SQLException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }

    /**
     * Funcion que convierte un string con estructura JSON en JsonObject
     *
     * @param decodifico String con estructura json
     * @return JsonObject del string
     */
    private JsonObject stringToJSON(String decodifico) {
        JsonReader reader = Json.createReader(new StringReader(decodifico));
        JsonObject jsonObj = reader.readObject();
        reader.close();
        return jsonObj;
    }

    /**
     * Funcion que retorna un JsonObject con los datos del usuario recibido en
     * ResultSet
     *
     * @param rs ResultSet de la consulta de base de datos realizada
     * @return JSONobject del usuario con los atributos: u_id, u_usuario,
     * u_nombre, u_apellido, u_correo, u_pregunta y u_password
     */
    private JsonObject crearUsuarioJson(ResultSet rs) {
        try {
            JsonObjectBuilder usuarioBuilder = Json.createObjectBuilder();
            usuarioBuilder.add("u_id", rs.getString("u_id"));
            usuarioBuilder.add("u_usuario", rs.getString("u_usuario"));
            usuarioBuilder.add("u_nombre", rs.getString("u_nombre"));
            usuarioBuilder.add("u_apellido", rs.getString("u_apellido"));
            usuarioBuilder.add("u_correo", rs.getString("u_correo"));
            usuarioBuilder.add("u_pregunta", rs.getString("u_pregunta"));
            usuarioBuilder.add("u_respuesta", rs.getString("u_respuesta"));
            usuarioBuilder.add("u_password", rs.getString("u_password"));
            return usuarioBuilder.build();
        } catch (SQLException ex) {
            Logger.getLogger(Modulo1sResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Función que verifica existencia de un usuario en el sistema y de existir
     * verifica si el password recibo es igual al que está almacenado en la BD
     *
     * @param usuario JSON.toString() con los "atributos" u_usuario y u_password
     * @return De existir el usuario y la contraseña coincide retorna un JSON en
     * String con todos los datos del usuario. De lo contrario retorna el String
     * "ERROR"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/recuperarClave")
    public String recuperarClave(@QueryParam("datosUsuario") String usuario) {

//        String usuario = "AleNegrin";
        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            String query = "SELECT * from Usuario WHERE u_usuario ='" + usuario + "';";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                JsonObject usuarioJsonObject = this.crearUsuarioJson(rs);
                return usuarioJsonObject.toString();
            }
            return "ERROR";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    
    /**
     * Función que actualiza la clave de un usuario en la BD
     *
     * @param datosUsuario JSON.toString() que tenga los "atributos" 
     * u_usuario , u_password
     * @return De existir el usuario y la contraseña coincide retorna un JSON en
     * String con todos los datos del usuario. De lo contrario retorna el String
     * "ERROR"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarClave")
    public Boolean actualizarClave(@QueryParam("datosUsuario") String datosUsuario) {
        
//            String decodifico = "{ \"u_usuario\" : \"jojo\" , \"u_password\" : \"elwebomio\" }";
       String decodifico = URLDecoder.decode(datosUsuario);

        try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            JsonObject usuarioJSON = this.stringToJSON(decodifico);
            String query = "UPDATE usuario SET "
                    + "u_password = '" + usuarioJSON.getString("u_password") + 
                    "' WHERE "
                    + "u_usuario = '" + usuarioJSON.getString("u_usuario") + "';";

            if (st.executeUpdate(query) > 0) {
                st.close();
                return true;
            } else {
                st.close();
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }
    }

    /**
     * POST method for creating an instance of Modulo1Resource
     *
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(Json content
    ) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public Modulo1Resource getModulo1Resource(@PathParam("id") String id
    ) {
        return Modulo1Resource.getInstance(id);
    }
}