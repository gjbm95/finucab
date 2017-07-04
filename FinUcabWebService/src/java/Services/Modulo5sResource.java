/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Pago;
import Dominio.SimpleResponse;
import Exceptions.DataReaderException;
import Exceptions.FabricaExcepcion;
import Exceptions.FinUCABException;
import Exceptions.SingletonLog;
import Logica.Comando;
import Logica.FabricaComando;
import Logica.Modulo5.AgregarPagoException;
import Logica.Modulo5.ConsultarPagoException;
import Logica.Modulo5.ListarPagosException;
import Logica.Modulo5.ModificarPagoException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaAgregar(Entidad enti) throws DataReaderException{
          
        validadorEntidad(enti);
        return String.valueOf(((SimpleResponse) enti).getId());
        
    }
    
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaConsultar(Entidad enti) throws DataReaderException{
         
        validadorEntidad(enti);
        return stringVerPago(enti);
        
    }
     
     
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaLista(Entidad enti) throws DataReaderException{
         
        validadorEntidad(enti);
        return stringListaPago(enti);
        
    }
    
    
    
    /**
     * Metodo para obtener la respuesta que se le envia al cliente
     * @param enti
     * @return 
     */
    private String obtenerRespuestaModificar(Entidad enti) throws DataReaderException{
          
        validadorEntidad(enti);
        return String.valueOf(((SimpleResponse) enti).getId());
        
    }
    
    
    
    /**
     * Metodo para validar un string
     * @param valor
     * @return boolean
     */
    private boolean validadorString(String valor) throws DataReaderException{
        
        if (valor == null) {
            throw FabricaExcepcion.instanciarDataReaderException(3);
        }else if(valor.equals("")) {
            throw FabricaExcepcion.instanciarDataReaderException(4);
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
            throw FabricaExcepcion.instanciarDataReaderException(6);
        }
        return true;
    }
    
   
    
    /**
     * Metodo para validar que una entidad no sea nula ni vacia
     * @param valor
     * @return boolean
     */
    private boolean validadorEntidad(Entidad valor) throws DataReaderException{
        
        if (valor == null)
            throw FabricaExcepcion.instanciarDataReaderException(5);
     
        return true;
    }
    
    
    

    /**
     * Metodo encargado de la construccion de los JSON para agregar un pago
     * @param datosPagos
     * @return Entidad
     */
    private Entidad entidadAgregarPago (@QueryParam("datosPago") String datosPagos) throws DataReaderException    {

        Entidad ex = null;
                 
            try{       
                
                boolean validador  =validadorString(datosPagos); 
                if( validador ){
         
                    String decodifico = URLDecoder.decode(datosPagos,"UTF-8");
                    JsonReader reader = Json.createReader(new StringReader(decodifico));
                    JsonObject pagoJSON = reader.readObject();           
                    reader.close();
                    ex = FabricaEntidad.obtenerPago( pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion"), pagoJSON.getString("pg_nombre_categoria")) ;

                }
            }catch (UnsupportedEncodingException ex1) {
                throw FabricaExcepcion.instanciarDataReaderException(999);
            }
        return ex;
    }
    
    
    
    
    /**
     * Metodo encargado de la construccion de los JSON para ver un pago
     * @param Objeto
     * @return String
     */
    private String stringVerPago(Entidad Objeto) throws DataReaderException{
       
        String respuesta ="";
        boolean validador  =validadorEntidad(Objeto);
     
                if( validador ){
              
                    JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();

                    Pago pago = (Pago) Objeto;                  
                     pagoBuilder.add("pg_id",pago.getId());
                     pagoBuilder.add("pg_monto",pago.getTotal());
                     pagoBuilder.add("pg_tipoTransaccion",pago.getTipo());
                     pagoBuilder.add("pg_categoria",pago.getCategoria());
                     pagoBuilder.add("pg_descripcion",pago.getDescripcion());
                     JsonObject pagoJsonObject = pagoBuilder.build(); 
                    respuesta = pagoJsonObject.toString();

                }
          
            return respuesta;
    } 
    

    
    
    /**
     * Metodo encargado de la construccion de los JSON para listar los pagos
     * @param objeto
     * @return String
     */
    private String stringListaPago (Entidad objeto) throws DataReaderException {

       
    String respuesta = "";
        
        boolean validador  =validadorEntidad(objeto);
        if( validador ){
            

                ArrayList<Entidad> lista =  ((ListaEntidad) objeto).getLista();                

                JsonObjectBuilder pagoBuilder = Json.createObjectBuilder();
                JsonArrayBuilder list = Json.createArrayBuilder();
                for (Entidad enti : lista) {
                    Pago pago = (Pago) enti;
                    

                    pagoBuilder.add("pg_id",pago.getId());
                    pagoBuilder.add("pg_monto",pago.getTotal());
                    pagoBuilder.add("pg_tipoTransaccion",pago.getTipo());
                    pagoBuilder.add("pg_nombre_categoria",pago.getNombreCategoria());
                    pagoBuilder.add("pg_categoria",pago.getCategoria());
                    pagoBuilder.add("pg_descripcion",pago.getDescripcion());
                    JsonObject pagoJsonObject = pagoBuilder.build();  
                    list.add( pagoJsonObject.toString());
                    
                }

                
                JsonArray listJsonObject = list.build();
                respuesta = listJsonObject.toString();

        }
        
        return respuesta;
    }
    
    
    
    /**
     * Metodo encargado de la construccion de los JSON para modificar un pago
     * @param datosPagos
     * @return Entidad
     */
    private Entidad entidadModificarPago(@QueryParam("datosPago") String datosPagos) throws DataReaderException{

        
        Entidad ex = null;
        
        
        try {  
            boolean validador  =validadorString(datosPagos);
                
            if( validador ){
            
            
                String decodifico = URLDecoder.decode(datosPagos,"UTF-8");
                JsonReader reader = Json.createReader(new StringReader(decodifico));
                JsonObject pagoJSON = reader.readObject();
                reader.close(); 
                
                ex = FabricaEntidad.obtenerPago(pagoJSON.getInt("pg_id"),pagoJSON.getInt("pg_categoria"), pagoJSON.getString("pg_descripcion"), pagoJSON.getInt("pg_monto"), pagoJSON.getString("pg_tipoTransaccion"), pagoJSON.getString("pg_nombre_categoria")) ; 
                
            }
      
        } catch (UnsupportedEncodingException ex1) {
            throw FabricaExcepcion.instanciarDataReaderException(999);
        }

        return ex;
        
    }
    
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/pruebaDB")
    public String getPruebaDataBase() {
        return null;
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
    public String registrarPago(@QueryParam("datosPago") String datosPagos)  {
          
        SingletonLog.getInstance().debug("Registrar Pago ");
            
         String respuesta = "";
         
        try {
            
            Entidad e = entidadAgregarPago(datosPagos);
            Comando c = FabricaComando.instanciarComandoAgregarPago(e);
            c.ejecutar();
            Entidad objectResponse = c.getResponse();
           respuesta = obtenerRespuestaAgregar(objectResponse);
            SingletonLog.getInstance().info("Pago Registrado");
           
        }  catch (AgregarPagoException | DataReaderException ex) {
            respuesta = ex.getOwnMessage();
            SingletonLog.getInstance().error("Error registrando pago");
            ex.printStackTrace();
            
        }  catch (FinUCABException ex) {
            respuesta = Registro.RegistroError.error_default;
            SingletonLog.getInstance().error("Error registrando pago");
            ex.printStackTrace();
            
        
        }  catch (Exception ex) {
            respuesta = Registro.RegistroError.error_default;
            SingletonLog.getInstance().error("Error registrando pago");
            ex.printStackTrace();
            
        }
        
        
        return respuesta;
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
        
        SingletonLog.getInstance().debug("Obteniendo Pago con id: " + idPago);

        String respuesta ="";
        try { 
                         
            if( validadorInteger(idPago)){
                        
                Comando c = FabricaComando.instanciarComandoConsultarPago(idPago);
                c.ejecutar();
                Entidad objectResponse = c.getResponse();
                respuesta =obtenerRespuestaConsultar(objectResponse);
                        
            }
        }catch (ConsultarPagoException | DataReaderException ex) {
            respuesta = ex.getOwnMessage();
        }  catch (FinUCABException ex) {
            respuesta = Registro.RegistroError.error_default;
        
        }  catch (Exception ex) {
            respuesta = Registro.RegistroError.error_default;
        }
         SingletonLog.getInstance().info("Retornando pago"+idPago);   
         return respuesta;
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
    public String visualizarPago(@QueryParam("datosPago") int idPago) {
        
        SingletonLog.getInstance().debug("Listando Pagos de usuario con id:" + idPago);

        String respuesta ="";
        try{
            if( validadorInteger(idPago) ){
                Comando c = FabricaComando.instanciarComandoListarPagos(idPago);
                c.ejecutar();
                Entidad objectResponse = c.getResponse();
                respuesta =obtenerRespuestaLista(objectResponse);
            }
        }
        catch (ListarPagosException | DataReaderException ex) {
            respuesta = ex.getOwnMessage();
        }  catch (FinUCABException ex) {
            respuesta = Registro.RegistroError.error_default;
        
        }  catch (Exception ex) {
            respuesta = Registro.RegistroError.error_default;
        }
        SingletonLog.getInstance().info("Retornando Pagos de usuario con id:" + idPago);   
        return respuesta;
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
        
            SingletonLog.getInstance().debug("Modificando pago");
        String respuesta = "";       

        try {
                Entidad ex = entidadModificarPago(datosPagos);
                Comando c = FabricaComando.instanciarComandoModificarPago(ex);
                c.ejecutar();
                Entidad objectResponse = c.getResponse();
                respuesta = obtenerRespuestaModificar(objectResponse);
                
        }catch (ModificarPagoException | DataReaderException ex) {
            respuesta = ex.getOwnMessage();
        }  catch (FinUCABException ex) {
            respuesta = Registro.RegistroError.error_default;
        
        }  catch (Exception ex) {
            respuesta = Registro.RegistroError.error_default;
        }
        
       SingletonLog.getInstance().info("Respuesta modificado: " + respuesta); 
       return respuesta;
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