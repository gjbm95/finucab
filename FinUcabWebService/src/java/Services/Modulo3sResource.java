package Services;

import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Presupuesto;
import Exceptions.FinUCABException;
import Exceptions.Presupuesto.*;
import Logica.Comando;
import Logica.FabricaComando;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Modulo 3 - Modulo de Presupuestos Desarrolladores:*William Lopez
 * / Christian Leon / Eduardo Lorenzo 
 * Descripción de la clase: Clase encargada de Agregar,
 * modificar, eliminar y visualizar los presupuestos ademas de obtener la lista
 * de categorias
 */
@Path("/Modulo3")
public class Modulo3sResource {

    final static Logger log = LogManager.getLogger();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Modulo3sResource
     */
    public Modulo3sResource() {
    }

    
    /**
     * Se encarga de devolver los datos del presupuesto solicitado
     *
     * @param nombrePresupuesto
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ObtenerPresupuesto")
    public String getObtenerPresupuesto(@QueryParam("idPresupuesto") int idPresupuesto) {

        log.debug("Obteniendo presupuesto con id: " + idPresupuesto);

        String respuesta = "";

        try {
            Comando comando = FabricaComando.instanciarComandoObtenerPresupuesto(idPresupuesto);
            comando.ejecutar();
            Entidad objectResponse = comando.getResponse();
            respuesta = creaPresupuestoJson(objectResponse);

        } catch (ConsultarPresupuestoException e) {
            respuesta = e.getOwnMessage();
            log.error("Error obteniendo presupuesto "+idPresupuesto+ " " + e.getOwnMessage());
            e.printStackTrace();
        } catch (FinUCABException e){
            respuesta = "Error";
            log.error("Error obteniendo presupuesto "+idPresupuesto+ " " + e.getMessage());
        } catch (Exception e){
            log.error("Error general "+e.getMessage());
            e.printStackTrace();
        }
        log.info("Retornando presupuesto con id: " + idPresupuesto);
        return respuesta;
    }

    
    /**
     * Se encarga de devolver la lista de presupuesto por usuario
     *
     * @param Usuario
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ListaPresupuesto")
    public String getListaPresupuesto(@QueryParam("idUsuario") int idUsuario) {

        log.debug("Listando presupuestos de usuario con id: " + idUsuario);

        String respuesta = "0";
        try {
            Comando comando = FabricaComando.instanciarComandoListarPresupuestos(idUsuario);
            comando.ejecutar();
            Entidad objectResponse = comando.getResponse();
            respuesta = creaListaPresupuestos(objectResponse);

        } catch (ListarPresupuestoException e) {
            respuesta = e.getOwnMessage();
            log.error("Error listando los presupuestos del usuario con id: "+idUsuario+ " " + e.getOwnMessage());
            e.printStackTrace();
        } catch (FinUCABException e){
            respuesta = "Error";
            log.error("Error listando los presupuesto del usuario con id: "+idUsuario+ " " + e.getMessage());
        } catch (Exception e){
            respuesta = "Error";
            log.error("Error general "+e.getMessage());
            e.printStackTrace();
        }
        
        log.info("Retornando presupuestos de usuario: " + idUsuario);
        return respuesta;
    }

    
    /**
     * Se encarga de eliminar de la base de datos el presupuesto seleccionado
     *
     * @param nombrePresupuesto
     * @param Usuario
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/EliminarPresupuesto")
    public String getEliminarPresupuesto(@QueryParam("idPresupuesto") int idPresupuesto) {

        log.debug("Eliminando presupuesto con id: " + idPresupuesto);
        
        String respuesta ="0";
        try {
           Comando command = FabricaComando.instanciarComandoEliminarPresupuesto(idPresupuesto);
           command.ejecutar();
           Entidad objectResponse = command.getResponse();
           respuesta = String.valueOf(objectResponse.getId());
        } catch (EliminarPresupuestoException e) {
            respuesta = e.getOwnMessage();
            log.error("Error eliminando presupuesto "+idPresupuesto+ " " + e.getOwnMessage());
            e.printStackTrace();
        } catch (FinUCABException e){
            respuesta = "Error";
            log.error("Error eliminando presupuesto "+idPresupuesto+ " " + e.getMessage());
        } catch (Exception e){
            respuesta ="Error";
            log.error("Error general "+e.getMessage());
            e.printStackTrace();
        }
        
        log.info("Presupuesto eliminado: " + idPresupuesto + " codigo de respuesta "+respuesta);
        return respuesta;
    }

    /**
     * Se encarga de agregar a la base de datos el presupuesto
     *
     * @param nombreusuario
     * @param datosPresupuesto
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/registrarPresupuesto")
    public String registrarPresupuesto(@QueryParam("datosPresupuesto") String datosPresupuesto) {

        log.debug("Registrando presupuesto ");
        String respuesta = "0";
        try {
            Entidad e = creaPresupuesto(datosPresupuesto);
            Comando command = FabricaComando.instanciarComandoAgregarPresupuesto(e);
            command.ejecutar();
            Entidad objectResponse = command.getResponse();
            respuesta = String.valueOf(objectResponse.getId());
            log.info("Presupuesto registrado con id: " + respuesta);
        } catch (AgregarPresupuestoException e) {
            respuesta = e.getOwnMessage();
            log.error("Error agregando presupuesto "+datosPresupuesto+ " " + e.getOwnMessage());
            e.printStackTrace();
        } catch (FinUCABException e){
            respuesta = "Error";
            log.error("Error agregando presupuesto "+datosPresupuesto+ " " + e.getMessage());
        } catch (Exception e){
            respuesta = "Error";
            log.error("Error general "+e.getMessage());
            e.printStackTrace();
        }
        log.info("Presupuesto registrado: "+respuesta);
        return respuesta;
    }

    /**
     * Se encarga de modificar el presupuesto seleccionado
     *
     * @param datosPresupuesto
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ModificarPresupuesto")
    public String modificarPresupuesto(@QueryParam("datosPresupuesto") String datosPresupuesto) {

        log.debug("Modificando presupuesto");

        String respuesta = "0";
        try {
            Entidad e = modificaPresupuesto(datosPresupuesto);
            Comando command = FabricaComando.instanciarComandoModificarPresupuesto(e);
            command.ejecutar();
            Entidad resultado = command.getResponse();
            respuesta = String.valueOf(resultado.getId());
            
        } catch (ModificarPresupuestoException e) {
            respuesta = e.getOwnMessage();
            log.error("Error modificando presupuesto "+datosPresupuesto+ " " + e.getOwnMessage());
            e.printStackTrace();
        } catch (FinUCABException e){
            respuesta = "Error";
            log.error("Error modificando presupuesto "+datosPresupuesto+ " " + e.getMessage());
        } catch (Exception e){
            respuesta = "Error";
            log.error("Error general "+e.getMessage());
            e.printStackTrace();
        }
        
        log.info("Presupuesto modificado: " + respuesta);

        return respuesta;
    }

    /**
     * Se encarga de devolver la lista de presupuesto por usuario
     *
     * @param Usuario
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ListaPresupuestoExportar")
    public String getTodoslosPresupuestos(@QueryParam("idUsuario") int idUsuario) {
        
        log.debug("Exportando presupuestos de usuario con id: " + idUsuario);

        String respuesta = "0";
        try {
            Comando comando = FabricaComando.instanciarComandoExportarPresupuesto(idUsuario);
            comando.ejecutar();
            Entidad objectResponse = comando.getResponse();
            respuesta = creaListaPresupuestos(objectResponse);

        } catch (ExportarPresupuestoException e) {
            respuesta = e.getOwnMessage();
            log.error("Error exportando los presupuestos del usuario con id: "+idUsuario+ " " + e.getOwnMessage());
            e.printStackTrace();
        } catch (FinUCABException e){
            respuesta = "Error";
            log.error("Error exportando los presupuestos del usuario con id: " + idUsuario + " " + e.getMessage());
        } catch (Exception e) {
            respuesta = "Error";
            log.error("Error general " + e.getMessage());
            e.printStackTrace();
        }

        log.info("Retornando presupuestos de usuario: " + idUsuario);
        return respuesta;
    }

    /**
     * Se encarga de verificar la disponibilidad de un nombre para un
     * presupuesto
     *
     * @param nombrePresupuesto
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/VerificarNombre")
    public String VerificarNombre(@QueryParam("nombrePresupuesto") String nombrePresupuesto) {
        log.debug("Verificando nombre "+nombrePresupuesto);
        String respuesta = "0";
        try {
            nombrePresupuesto = nombrePresupuesto.replace('_', ' ');
            Comando command = FabricaComando.instanciarComandoVerificarNombre(nombrePresupuesto);
            command.ejecutar();
            Entidad objectResponse = command.getResponse();
            respuesta = String.valueOf(objectResponse.getId());

        } catch (VerificarNombreException e) {
            respuesta = e.getOwnMessage();
            log.error("Error verificando el nombre: "+nombrePresupuesto+ " " + e.getOwnMessage());
            e.printStackTrace();
        } catch (FinUCABException e){
            respuesta = "Error";
            log.error("Error verificando el nombre: "+nombrePresupuesto+ " " + e.getMessage());
        } catch (Exception e) {
            respuesta = "Error";
            log.error("Error general " + e.getMessage());
            e.printStackTrace();
        }
        
        log.info("Presupuesto verificado: " +nombrePresupuesto + " "+ respuesta);
        return respuesta;
    }

    /**
     * POST method for creating an instance of Modulo3Resource
     *
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

    private Entidad modificaPresupuesto(@QueryParam("datosPresupuesto") String datosPresupuesto) {

        Entidad e = null;
        try {
            String decodifico = URLDecoder.decode(datosPresupuesto, "UTF-8");
            System.out.println(decodifico);
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject presupuestoJSON = reader.readObject();
            reader.close();

            e = FabricaEntidad.obtenerPresupuesto(presupuestoJSON.getInt("pr_id"), presupuestoJSON.getString("pr_nombre"),
                    Double.valueOf(presupuestoJSON.getString("pr_monto")),
                    presupuestoJSON.getString("pr_clasificacion"), presupuestoJSON.getInt("pr_duracion"),
                    presupuestoJSON.getInt("pr_usuarioid"), presupuestoJSON.getString("categoriaca_id"), "true");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    private Entidad creaPresupuesto(@QueryParam("datosPresupuesto") String datosPresupuesto) {

        Entidad e = null;
        try {
            String decodifico = URLDecoder.decode(datosPresupuesto, "UTF-8");
            System.out.println(decodifico);
            JsonReader reader = Json.createReader(new StringReader(decodifico));
            JsonObject presupuestoJSON = reader.readObject();
            reader.close();

            e = FabricaEntidad.obtenerPresupuesto(presupuestoJSON.getString("pr_nombre"),
                    Double.valueOf(presupuestoJSON.getString("pr_monto")),
                    presupuestoJSON.getString("pr_clasificacion"), presupuestoJSON.getInt("pr_duracion"),
                    presupuestoJSON.getInt("pr_usuarioid"), presupuestoJSON.getString("categoriaca_id"), "true");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return e;
    }

    private String creaListaPresupuestos(Entidad entidad) {
        String respuesta = "";

        if (entidad != null) {
            ArrayList<Entidad> lista = ((ListaEntidad) entidad).getLista();
            JsonObjectBuilder presupuestoBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Entidad e : lista) {
                Presupuesto p = (Presupuesto) e;
                presupuestoBuilder.add("Id", p.getId());
                presupuestoBuilder.add("Nombre", p.getNombre());
                presupuestoBuilder.add("Categoria", p.getCategoria());
                presupuestoBuilder.add("Monto", p.getMonto());
                presupuestoBuilder.add("Duracion", p.getDuracion());
                presupuestoBuilder.add("Clasificacion", p.getClasificacion());
                presupuestoBuilder.add("Tipo", p.getTipo());
                JsonObject presupuestoJsonObject = presupuestoBuilder.build();
                arrayBuilder.add(presupuestoJsonObject);
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
            System.out.println(respuesta);
        } else {
            System.out.println("Ha ocurrido un error");
        }
        return respuesta;

    }

    private String creaPresupuestoJson(Entidad entidad) {

        String respuesta = "";

        if (entidad != null) {

            JsonObjectBuilder presupuestoBuilder = Json.createObjectBuilder();
            Presupuesto p = (Presupuesto) entidad;

            presupuestoBuilder.add("Id", p.getId());
            presupuestoBuilder.add("Nombre", p.getNombre());
            presupuestoBuilder.add("IdCategoria", p.getCategoria());
            presupuestoBuilder.add("Monto", p.getMonto());
            presupuestoBuilder.add("Duracion", p.getDuracion());
            presupuestoBuilder.add("Clasificacion", p.getClasificacion());
            presupuestoBuilder.add("Tipo", p.getTipo());

            JsonObject presupuestoJsonObject = presupuestoBuilder.build();
            respuesta = presupuestoJsonObject.toString();
        }

        return respuesta;
    }
}
