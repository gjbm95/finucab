package Services;

import BaseDatosDAO.Conexion;
import Dominio.*;
import Exceptions.FabricaExcepcion;
import Exceptions.FinUCABException;
import IndentityMap.IdentityMap;
import Logica.Comando;
import Logica.FabricaComando;
import Logica.Modulo2.AgregarFallidoException;
import Logica.Modulo2.ConversionFallidaException;
import Logica.Modulo2.EliminarFallidoException;
import Logica.Modulo2.MapaModulo2;
import Logica.Modulo2.ModificarFallidoException;
import java.io.StringReader;
import java.net.URLDecoder;
import java.sql.CallableStatement;
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
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
@Path("/Modulo2")
public class Modulo2sResource {

    @Context
    private UriInfo context;
    public static String resultado;

    /**
     * Creates a new instance of Modulo2sResource
     */
    public Modulo2sResource() {
    }

    /**
     * Función que atualiza los datos de un usuario.
     *
     * @return int 1 si se pudo actualizar, int 0 si no logro actualizar
     * @param String Json String con los atributos: u_id , u_uduario , u_nombre
     * ,u_apellido, u_correo , u_pregunta , u_respuesta , u_password  
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarDatosUsuario")
    public String actualizarDatosUsuario(@QueryParam("datosUsuario") String datosCuenta) {
        String decodifico = URLDecoder.decode(datosCuenta);
        String resultado = "1";
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de Actualizar datos de "
                    + "usuario recibida de: " + datosCuenta);
        try {
            JsonObject usuarioJSON = this.stringToJSON(decodifico);
            Usuario usuario = FabricaEntidad.obtenerUsuario();
            usuario.jsonToUsuario(usuarioJSON);
            Comando command = 
            FabricaComando.instanciarComandoActualizarDatosUsuario(usuario);
            command.ejecutar(); 
            Conexion.conectarADb().close();
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (ModificarFallidoException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (ConversionFallidaException ex) {
            Logger.getLogger(Modulo2sResource.class.getName())
                    .log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        } 
        
        return resultado;
    }

    /**
     * Función que agrega una nueva Cuenta Bancaria para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: ct_tipocuenta , ct_numcuenta
     * , ct_nombrebanco, ct_saldoactual , usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/agregarCuentaBancaria")
    public String agregarCuentaBancaria(@QueryParam("datosCuenta") String datosCuenta) {
        String decodifico = URLDecoder.decode(datosCuenta);
        String resultado = null;
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de agregar cuenta"
                    + " bancaria recibida de: " + datosCuenta);
        try {
            JsonObject cuentaJSON = this.stringToJSON(decodifico);
            Cuenta_Bancaria cuenta = jsonToCuenta (cuentaJSON);
            MapaModulo2 cache = MapaModulo2.obtenerInstancia();
            cache.setEntidad("CuentaNueva", cuenta);
            Comando command = FabricaComando.instanciarComandoAgregarCuenta(cuenta);
            command.ejecutar();
            cuenta = (Cuenta_Bancaria) command.getResponse();
            resultado = Integer.toString(cuenta.getId());
            Conexion.conectarADb().close();
            cache.eliminarEntidad("CuentaNueva");
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (AgregarFallidoException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NullPointerException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (ConversionFallidaException ex) {
            Logger.getLogger(Modulo2sResource.class.getName())
                    .log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }    
            
        return resultado.toString();
    }

    /**
     * Función que actualiza o modifica los datos de una cuenta bancaria
     *
     * @return int 1 si se pudo actualizar, int 0 si no logro actualizar
     * @param String Json String con los atributos: ct_id , ct_tipocuenta ,
     * ct_numcuenta ,ct_nombrebanco, ct_saldoactual
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarCuentaBancaria")
    public String actualizarCuentaBancaria(@QueryParam("datosCuenta") String datosCuenta) {
        String decodifico = URLDecoder.decode(datosCuenta);
        String resultado = "1";
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de Actualizar datos de "
                    + "Cuenta Bancaria recibida de: " + datosCuenta);
        try {
            JsonObject cuentaJSON = this.stringToJSON(decodifico);
            Cuenta_Bancaria cuenta = jsonToCuentaM (cuentaJSON);
            MapaModulo2 cache = MapaModulo2.obtenerInstancia();
            cache.setEntidad("CuentaModificada", cuenta);
            Comando command = FabricaComando.instanciarComandoActualizarCuenta(cuenta);
            command.ejecutar();
            Conexion.conectarADb().close();
            cache.eliminarEntidad("CuentaModificada");
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NullPointerException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (ModificarFallidoException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (ConversionFallidaException ex) {
            Logger.getLogger(Modulo2sResource.class.getName())
                    .log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        } 
        
        return resultado;
    }

    /**
     * Función que agrega una nueva Cuenta Bancaria para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: ct_tipocuenta , ct_numcuenta
     * , ct_nombrebanco, ct_saldoactual , usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarCuentaBancaria")
    public String eliminarCuentaBancaria(@QueryParam("idCuenta") String idCuenta) {
        String decodifico = URLDecoder.decode(idCuenta);
        int resultado = 1;
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de eliminar cuenta bancaria"
                    + " recibida de id: " +idCuenta);
        try {
            int id = Integer.parseInt(decodifico);
            Comando command = FabricaComando.instanciarComandoEliminarCuenta(id);
            command.ejecutar();
            resultado = command.getResponse().getId();
            Conexion.conectarADb().close();
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (EliminarFallidoException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        } 
        return Integer.toString(resultado);
    }

    /**
     * Función que agrega una nueva Tarjet de Crédito para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: tc_tipo ,
     * tc_fechavencimiento (en formato DD/MM/YYYY) , tc_numero, tc_saldo
     * ,usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/agregarTDC")
    public String agregarTDC(@QueryParam("datosTDC") String datosTDC) {
        String decodifico = URLDecoder.decode(datosTDC);
        int resultado = 0;
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de agregar tarjeta de "
                    + "credito recibida de: " + datosTDC);
        try {
            JsonObject tdcJSON = this.stringToJSON(decodifico);   
            Tarjeta_Credito tdc = jsonToTarjeta (tdcJSON);
            MapaModulo2 cache = MapaModulo2.obtenerInstancia();
            cache.setEntidad("TarjetaNueva", tdc);
            Comando command = FabricaComando.instanciarComandoAgregarTDC(tdc);
            command.ejecutar();
            tdc = (Tarjeta_Credito) command.getResponse();
            resultado = tdc.getId();
            Conexion.conectarADb().close();
            cache.eliminarEntidad("TarjetaNueva");
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (AgregarFallidoException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (NullPointerException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (ConversionFallidaException ex) {
            Logger.getLogger(Modulo2sResource.class.getName())
                    .log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        } 
        return Integer.toString(resultado);
    }

    /**
     * Función que actualiza o modifica los datos de una Tarjeta de crédito
     *
     * @return int 1 si se pudo actualizar, int 0 si no logro actualizar
     * @param String JSON String con los atributos: tc_id , tc_tipo ,
     * tc_fechavencimiento (en formato DD/MM/YYYY) , tc_numero, tc_saldo
     * ,usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/actualizarTDC")
    public String actualizarTDC(@QueryParam("datosTDC") String datosTDC) {
        String decodifico = datosTDC;
        String resultado = "1";
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de Actualizar datos de tarjeta de "
                    + "credito recibida de: "+datosTDC);
        try {
            JsonObject tdcJSON = this.stringToJSON(decodifico);
            Tarjeta_Credito tdc = jsonToTarjetaM (tdcJSON);
            MapaModulo2 cache = MapaModulo2.obtenerInstancia();
            cache.setEntidad("TarjetaModificada", tdc);
            Comando command = FabricaComando.instanciarComandoActualizarTDC(tdc);
            command.ejecutar();
            Conexion.conectarADb().close();
            cache.eliminarEntidad("TarjetaModificada");
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (ModificarFallidoException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NullPointerException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (ConversionFallidaException ex) {
            Logger.getLogger(Modulo2sResource.class.getName())
                    .log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        } 
        return resultado;
    }
    /**
     * Función que agrega una nueva Cuenta Bancaria para un Usuario
     *
     * @return int id de la nueva cuenta, 0 si no logro actualizar
     * @param String JSON String con los atributos: ct_tipocuenta , ct_numcuenta
     * , ct_nombrebanco, ct_saldoactual , usuariou_id
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/eliminarTDC")
    public String eliminarTDC(@QueryParam("idtdc") String idtdc) {
        int resultado = 0;
        String decodifico = idtdc;
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de eliminar datos de tarjeta de credito "
                    + "recibida de: " + idtdc);
        try {
            int id = Integer.parseInt(decodifico);
            Comando command = FabricaComando.instanciarComandoEliminarTDC(id);
            command.ejecutar();
            resultado = command.getResponse().getId();
            Conexion.conectarADb().close();
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (EliminarFallidoException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = 0;
        } 
        return Integer.toString(resultado);
    }
    /**
     * Función que busca todas las tarjetas de credito de un usuario
     *
     * @return JsonToString compuesto de JsonArrays de cada tarjeta
     * @param String id del usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarTDC")
    public String consultarTDC(@QueryParam("idUsuario") String idUsuario) {
        String decodifico = idUsuario;
        String resultado = "1";
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de consultar tarjetas de credito recibida de:"
                    + " " + idUsuario);
        try {
            int id = Integer.parseInt(decodifico);
            Comando command = FabricaComando.instanciarComandoConsultarTDC(id);
            command.ejecutar();
            SimpleResponse simple = (SimpleResponse) command.getResponse();
            resultado = simple.getDescripcion();
            Conexion.conectarADb().close();
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        } 
        System.out.println(resultado.toString());
        return resultado;
    }

    /**
     * Función que busca todas las tarjetas de credito de un usuario
     *
     * @return JsonToString compuesto de JsonArrays de cada tarjeta
     * @param String id del usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarCuentas")
    public String consultarCuentas(@QueryParam("idUsuario") String idUsuario) {
        String decodifico = URLDecoder.decode(idUsuario);
        String resultado = "1";
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de consultar cuentas bancarias recibida de:"
                    + " " + idUsuario);
        try{
        int id = Integer.parseInt(decodifico);
        Comando command = FabricaComando.instanciarComandoConsultarCuentas(id);
        command.ejecutar();
        SimpleResponse simple = (SimpleResponse) command.getResponse();
        resultado = simple.getDescripcion();
        Conexion.conectarADb().close();
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (FinUCABException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        } 
        return resultado;
    }

    /**
     * Función que busca todas las tarjetas de credito de un usuario
     *
     * @return JsonToString compuesto de JsonArrays de cada tarjeta
     * @param String id del usuario
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/consultarEstadisticas")
    public String consultarEstadisticas(@QueryParam("idUsuario") String idUsuario) {
        String decodifico = URLDecoder.decode(idUsuario);
        String resultado = "1";
        Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Solicitud de consultar estadisticas recibida de: " + idUsuario);
        try {
            int id = Integer.parseInt(decodifico);
            Comando command = FabricaComando.instanciarComandoConsultarEstadisticas(id);
            command.ejecutar();
            SimpleResponse simple = (SimpleResponse) command.getResponse();
            resultado = simple.getDescripcion();
            Conexion.conectarADb().close();
        }catch (SQLException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (NumberFormatException ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        }catch (Exception ex) {
            Logger.getLogger(Modulo2sResource.class.getName()).
                    log(Level.SEVERE, null, ex);
            resultado = "0";
        } 
        return resultado;
    }

    /**
     * Funcion que convierte un string con estructura JSON en JsonObject
     *
     * @param decodifico String con estructura json
     * @return JsonObject del string
     */
    public JsonObject stringToJSON(String decodifico) 
            throws ConversionFallidaException{
        if (decodifico.length()==0){
           ConversionFallidaException ex = 
                   FabricaExcepcion.instanciarConversionFallidaException
        (1,"No hay datos entrantes");       
                throw ex;
        } 
        JsonReader reader = Json.createReader(new StringReader(decodifico));
        JsonObject jsonObj = reader.readObject();
        reader.close();
        return jsonObj;
    }
    
    
    /**
     * Metodo encargado de convertir un Json en una entidad tarjeta de Credito.
     *  @param tdcJSON Objeto Json con los datos de la tarjeta 
     *  @return tdc Objeto de tipo Tarjeta_Credito
     */
    public Tarjeta_Credito jsonToTarjetaM (JsonObject tdcJSON)throws NullPointerException{
        Tarjeta_Credito tdc = new Tarjeta_Credito(tdcJSON.getString("tc_tipo"),
        tdcJSON.getString("tc_fechavencimiento"), tdcJSON.getString("tc_numero"),
        Float.parseFloat(tdcJSON.getString("tc_saldo")),
        Integer.parseInt(tdcJSON.getString("tc_id")),
        Integer.parseInt(tdcJSON.getString("usuariou_id")));
     return tdc;
    }
    
    
     /**
     * Metodo encargado de convertir un Json en una entidad Cuenta_Bancaria.
     *  @param cuentaJSON Objeto Json con los datos de la Cuenta Bancaria 
     *  @return cuenta Objeto de tipo Cuenta_Bancaria
     * */
    public Cuenta_Bancaria jsonToCuentaM (JsonObject cuentaJSON)throws NullPointerException{
        Cuenta_Bancaria cuenta = FabricaEntidad.obtenerCuentaBancaria
        (cuentaJSON.getString("ct_tipocuenta"),
        cuentaJSON.getString("ct_numcuenta"), cuentaJSON.getString("ct_nombrebanco"),
        Float.parseFloat(cuentaJSON.getString("ct_saldoactual")),
        Integer.parseInt(cuentaJSON.getString("ct_id")),
        Integer.parseInt(cuentaJSON.getString("usuariou_id")));
     return cuenta;
    }
    
    /**
     * Metodo encargado de convertir un Json en una entidad tarjeta de Credito.
     *  @param tdcJSON Objeto Json con los datos de la tarjeta 
     *  @return tdc Objeto de tipo Tarjeta_Credito
     */
    public Tarjeta_Credito jsonToTarjeta (JsonObject tdcJSON)throws NullPointerException{
        Tarjeta_Credito tdc = new Tarjeta_Credito(tdcJSON.getString("tc_tipo"),
        tdcJSON.getString("tc_fechavencimiento"), tdcJSON.getString("tc_numero"),
        Float.parseFloat(tdcJSON.getString("tc_saldo")),
        0,Integer.parseInt(tdcJSON.getString("usuariou_id")));
     return tdc;
    }
    
    
     /**
     * Metodo encargado de convertir un Json en una entidad Cuenta_Bancaria.
     *  @param cuentaJSON Objeto Json con los datos de la Cuenta Bancaria 
     *  @return cuenta Objeto de tipo Cuenta_Bancaria
     * */
    public Cuenta_Bancaria jsonToCuenta (JsonObject cuentaJSON) throws NullPointerException{
        Cuenta_Bancaria cuenta = FabricaEntidad.obtenerCuentaBancaria
        (cuentaJSON.getString("ct_tipocuenta"),
        cuentaJSON.getString("ct_numcuenta"), cuentaJSON.getString("ct_nombrebanco"),
        Float.parseFloat(cuentaJSON.getString("ct_saldoactual")),
        0,Integer.parseInt(cuentaJSON.getString("usuariou_id")));
     return cuenta;
    }

}
