package Services;

import BaseDatosDAO.Conexion;
import Dominio.Categoria;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.SimpleResponse;
import Exceptions.DataReaderException;
import Exceptions.FabricaExcepcion;
import Exceptions.FinUCABException;
import Logica.Comando;
import Logica.FabricaComando;
import static Services.Modulo3sResource.log;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import org.apache.logging.log4j.LogManager;

/**
 * REST Web Service
 *
 * @author AlejandroNegrin
 */
@Path("/Modulo4")
public class Modulo4sResource {
    
    final static org.apache.logging.log4j.Logger log = LogManager.getLogger();
    
    
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
        String respuesta = "";
        log.debug("Registrando Categoria ");
        try {

            Entidad e = registroCategoria(datosCategoria);
            Comando c = FabricaComando.instanciarComandoAgregarCategoria(e);
            c.ejecutar();
            Entidad objectResponse = c.getResponse();
            respuesta = obtenerRespuestaAgregar(objectResponse);

            log.info("Categoria registrado con id: " + respuesta);
            
        } catch (Exception ex) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, ex);
            log.error("Error registrando Categoria");
            ex.printStackTrace();
            
        }
         return respuesta;
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
    public String eliminarCategoria(@QueryParam("datosCategoria") int datosCategoria) {
        
        log.debug("Eliminando categoria con id:_" + datosCategoria);
        String respuesta="";
        try {
            Comando c = FabricaComando.instanciarComandoEliminarCategoria(datosCategoria);
            c.ejecutar();
            Entidad objectResponse = c.getResponse();
            respuesta = obtenerRespuestaEliminar(objectResponse);
            
        } catch (FinUCABException ex) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, ex);
            log.error("Error eliminando categoria con id: " + datosCategoria);
            ex.printStackTrace();
        }
        log.info("Categoria eliminada: " + datosCategoria + "respuesta "+respuesta);
        return respuesta;
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
    public String VisualizarCategoria(@QueryParam("datosCategoria") int usuario) {
        
        log.debug("Obteniendo Categorias del usuario con id: " + usuario);
            String respuesta ="";
            
        try{
            if( validadorInteger(usuario) ){
            Comando c = FabricaComando.instanciarComandoVisualizarCategoria(usuario);
            c.ejecutar();
            Entidad objectResponse = c.getResponse();
            respuesta = obtenerRespuestaLista(objectResponse);
            }
            else{
                
            System.out.println("Parametro de entrada nulo o vacio");  
            
            }
            
           }
        catch(Exception e) {
            respuesta = "Error Vacio :"+e.getMessage();
            log.error("Error obteniendo Categorias" + e.getMessage());
            e.printStackTrace();
        }
        
        log.info("Retornando Categorias del usuario: " + usuario);
        return respuesta;
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
        String respuesta="";
        log.debug("Modificando categoria");
       System.out.println(datosCategoria);
        try {
           
            Entidad e = modCategoria(datosCategoria);
            Comando c = FabricaComando.instanciarComandoModificarCategoria(e);
            c.ejecutar();
            Entidad objectResponse = c.getResponse();
            //respuesta = obtenerRespuestaModificar(objectResponse);
           } catch (Exception e) {

            System.out.println(e.getMessage());
            respuesta = "0";
            log.error("Error modificando categoria "+datosCategoria);
            e.printStackTrace();

        }
         log.info("Respuesta: " + respuesta);
        return respuesta;
        
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
    public String buscarCategoria(@QueryParam("datosCategoria") int datosCategoria){
        
        log.debug("Obteniendo Categoria con id: " + datosCategoria);
        String respuesta ="";
        try{
            if( validadorInteger(datosCategoria)){

            Comando c = FabricaComando.instanciarComandoConsultarCategoria(datosCategoria);
            c.ejecutar();
            Entidad objectResponse = c.getResponse(); 
            respuesta = obtenerRespuestaConsultar(objectResponse);
            }
            else{
            
            }
        }
        catch(DataReaderException e){
            respuesta = "Error :"+e.getOwnMessage();
            log.error("Error obteniendo categoria" + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e) {
            respuesta = "Error :"+e.getMessage();
            log.error("Error obteniendo categoria" + e.getMessage());
            e.printStackTrace();
        }
    return respuesta;
    }
    
    
    private String listaCategoria (Entidad objeto){
        
    String respuesta = "";
        
        boolean validador  =validadorEntidad(objeto);
                
        if( validador ){
            try{
                ArrayList<Entidad> lista =  ((ListaEntidad) objeto).getLista();
                JsonObjectBuilder categoriaBuilder = Json.createObjectBuilder();
                JsonArrayBuilder list = Json.createArrayBuilder();
                
                for (Entidad enti : lista) {
                    Categoria categoria = (Categoria) enti;
                    categoriaBuilder.add("ca_id",categoria.getId());
                    categoriaBuilder.add("ca_nombre",categoria.getNombre());
                    categoriaBuilder.add("ca_descripcion",categoria.getDescripcion());
                    categoriaBuilder.add("ca_eshabilitado",categoria.isEstaHabilitado());
                    categoriaBuilder.add("ca_esingreso",categoria.isIngreso());
                    categoriaBuilder.add("usuariou_id",categoria.getIdUsario());
                    JsonObject categoriaJsonObject = categoriaBuilder.build();  
                    list.add( categoriaJsonObject.toString());
                    
                }
                
                JsonArray listJsonObject = list.build();
                respuesta = listJsonObject.toString();
            }
            catch(Exception e){
                System.out.println(e);

            }
        }
        
        else {
            System.out.println("Error");   
        }
        
        return respuesta;
    }
    
    private Entidad registroCategoria (@QueryParam("datosCategoria") String datosCategorias) {
        
        Entidad ex = null;
        try {
            boolean validador  = validadorString(datosCategorias);
            if( validador ){
                String decodifico = URLDecoder.decode(datosCategorias,"UTF-8");
                JsonReader reader = Json.createReader(new StringReader(decodifico));
                JsonObject categoriaJSON = reader.readObject();           
                reader.close();
                ex = FabricaEntidad.obtenerCategoria(categoriaJSON.getInt("c_usuario"), categoriaJSON.getString("c_nombre"), categoriaJSON.getString("c_descripcion"), categoriaJSON.getBoolean("c_ingreso"), categoriaJSON.getBoolean("c_estado")) ;
                }
                
     
        }catch (DataReaderException e) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, e);
        }
         catch (FinUCABException e) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return ex;
    }
    
    private Entidad modCategoria (@QueryParam("datosCategoria") String datosCategorias){
        
        Entidad ex = null;
        try {
        boolean validador  = validadorString(datosCategorias);
                
        if( validador ){
        
        
            String decodifico = URLDecoder.decode(datosCategorias,"UTF-8");
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject categoriaJSON = reader.readObject();           
            reader.close();
            ex = FabricaEntidad.obtenerCategoria(categoriaJSON.getInt("c_id"),categoriaJSON.getInt("c_usuario"), categoriaJSON.getString("c_nombre"), categoriaJSON.getString("c_descripcion"), categoriaJSON.getBoolean("c_ingreso"), categoriaJSON.getBoolean("c_estado")) ;
            
        }
        else {
            
           System.out.println("Parametro de entrada nulo o vacio");
           
            }
        } catch (DataReaderException e) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (FinUCABException e) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, e);
        }
         catch (UnsupportedEncodingException e) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (Exception e) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, e);
        }
        return ex;
        
    }
    
    private String verCategoria(Entidad Objeto)throws DataReaderException{
        
         String respuesta ="";
         boolean validador  = validadorEntidad(Objeto);
          try{
                if( validador ){
                JsonObjectBuilder categoriaBuilder = Json.createObjectBuilder();
                
                 Categoria categoria = (Categoria) Objeto;                  
                categoriaBuilder.add("ca_id",categoria.getId());
                categoriaBuilder.add("ca_nombre",categoria.getNombre());
                categoriaBuilder.add("ca_descripcion",categoria.getDescripcion());
                categoriaBuilder.add("ca_eshabilitado",categoria.isEstaHabilitado());
                categoriaBuilder.add("ca_esingreso",categoria.isIngreso());
                categoriaBuilder.add("usuariou_id",categoria.getIdUsario());
                 JsonObject categoriaJsonObject = categoriaBuilder.build(); 
                respuesta = categoriaJsonObject.toString();
                }
           } catch (Exception ex) {
            Logger.getLogger(Modulo4sResource.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            return respuesta;
    } 
    
    
        /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaAgregar(Entidad enti){
          
        if(validadorEntidad(enti)) 
        
        return String.valueOf(((SimpleResponse) enti).getId());
        else {
            return "Error Entidad nula o Vacia";
        }
    }
            /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaEliminar(Entidad enti){
          
        if(validadorEntidad(enti)) 
        
        return String.valueOf(((SimpleResponse) enti).getId());
        else {
            return "Error Entidad nula o Vacia";
        }
    }
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaConsultar(Entidad enti) throws DataReaderException{
         
        if(validadorEntidad(enti)) 
        
        return verCategoria(enti);
        else {
            return "Error Entidad nula o Vacia";
        }
    }
     
     
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaLista(Entidad enti) throws DataReaderException{
         
        if(validadorEntidad(enti)){ 
        
        return listaCategoria(enti);
        }
        else {
            return "Error Entidad nula o Vacia";
        }
    }
    
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaModificar(Entidad enti){
          
        if(validadorEntidad(enti)) 
        
        return String.valueOf(((SimpleResponse) enti).getId());
        else {
            return "Error Entidad nula o Vacia";
        }
    }
    
    
    
    /**
     * Metodo para validar un string
     * @param valor
     * @return boolean
     */
    private boolean validadorString(String valor) throws DataReaderException{
        
        if (valor == null) {
            throw FabricaExcepcion.instanciarDataReaderException(1);
        }else if(valor.equals("")) {
            throw FabricaExcepcion.instanciarDataReaderException(1);
        }else{
            return true;
        }

    }
    
    
    
    /**
     * Metodo para validar que un integer no sea cero , ni nulo
     * @param valor
     * @return boolean
     */
    private boolean validadorInteger(int valor) throws DataReaderException{
        
        if (valor == 0) {
            throw FabricaExcepcion.instanciarDataReaderException(3);
        }
        return true;
    }
    
   
    
    /**
     * Metodo para validar que una entidad no sea nula ni vacia
     * @param valor
     * @return boolean
     */
    private boolean validadorEntidad(Entidad valor){
        
        return (valor!= null) && (!valor.equals(""));
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