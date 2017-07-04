/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Presupuesto;
import Exceptions.FabricaExcepcion;
import Exceptions.FinUCABException;
import Exceptions.Presupuesto.AgregarPresupuestoException;
import Exceptions.Presupuesto.ConsultarPresupuestoException;
import Exceptions.Presupuesto.EliminarPresupuestoException;
import Exceptions.Presupuesto.ListarPresupuestoException;
import Exceptions.Presupuesto.ModificarPresupuestoException;
import Exceptions.Presupuesto.VerificarNombreException;
import IndentityMap.FabricaIdentityMap;
import Registro.RegistroBaseDatos;
import Registro.RegistroIdentityMap;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author Junior
 */
public class DAOPresupuesto extends DAO implements IDAOPresupuesto {

    final static org.apache.logging.log4j.Logger log = LogManager.getLogger();
    
    /**
     * Metodo encargado de agregar un presupuesto en base de datos
     * @param e
     * @return el estado del insert y el id del presupuesto
     * @throws AgregarPresupuestoException 
     */
    @Override
    public Entidad agregar(Entidad e) throws AgregarPresupuestoException {

        Presupuesto presupuesto = (Presupuesto) e;
        int respuesta = 0;
        int idPresupuesto =0;
        String[] idCategoria;
        try {
            idCategoria = presupuesto.getCategoria().split("-");
            Connection conn = Conexion.conectarADb();
            PreparedStatement pag = conn.prepareStatement(RegistroBaseDatos.AGREGAR_PRESUPUESTO);
            pag.setString(1, presupuesto.getNombre());
            pag.setDouble(2, presupuesto.getMonto());
            pag.setString(3, presupuesto.getClasificacion());
            pag.setInt(4, presupuesto.getDuracion());
            pag.setInt(5, presupuesto.getUsuario());
            pag.setInt(6, Integer.parseInt(idCategoria[0]));
            pag.executeQuery();
            ResultSet rs = pag.getResultSet();
            if (rs.next()) {
                idPresupuesto = rs.getInt("agregarpresupuesto");
                pag.close();
            } else {
                throw FabricaExcepcion.instanciarAgregarPresupuestoException(300);
            }
            Desconectar(conn);
            respuesta = 1;
            presupuesto.setId(idPresupuesto);
            presupuesto.setTipo("true");
            FabricaIdentityMap.obtenerIdentityMap().addEntidadEnLista(RegistroIdentityMap.LISTA_PRESUPUESTO, presupuesto);

        } catch (SQLException ex) {
            log.error("Error agregando presupuesto: " + ex.getMessage());
            respuesta = 2;
            throw FabricaExcepcion.instanciarAgregarPresupuestoException(998, ex.getMessage());
        } catch (NullPointerException ex){
            log.error("Error agregando presupuesto, hay algun dato nulo "+ ex.getMessage());
            respuesta = 2;
            throw FabricaExcepcion.instanciarAgregarPresupuestoException(5, ex.getMessage());
        }

        return FabricaEntidad.obtenerSimpleResponse(respuesta, idPresupuesto);
    }

    /**
     * Metodo encargado de modificar un presupuesto en base de datos
     * @param e
     * @return el presupuesto modificado
     * @throws ModificarPresupuestoException 
     */
    @Override
    public Entidad modificar(Entidad e) throws ModificarPresupuestoException {

        Presupuesto presupuesto = (Presupuesto) e;
        String[] idCategoria;
        int respuesta = 0;

        try {
            idCategoria = presupuesto.getCategoria().split("-");
            Connection conn = Conectar();
            PreparedStatement pag = conn.prepareStatement(RegistroBaseDatos.MODIFICAR_PRESUPUESTO);
            pag.setString(1, presupuesto.getNombre());
            pag.setDouble(2, presupuesto.getMonto());
            pag.setString(3, presupuesto.getClasificacion());
            pag.setInt(4, presupuesto.getDuracion());
            pag.setInt(5, presupuesto.getUsuario());
            pag.setInt(6, Integer.parseInt(idCategoria[0]));
            pag.setInt(7, presupuesto.getId());
            System.out.println(pag.toString());
            pag.executeQuery();
            ResultSet rs = pag.getResultSet();
            rs.next();
            respuesta = rs.getInt("modificarpresupuesto");
            pag.close();
            Desconectar(conn);
            presupuesto.setTipo("true");
            FabricaIdentityMap.obtenerIdentityMap().updateEntidadEnLista(RegistroIdentityMap.LISTA_PRESUPUESTO, presupuesto);

        } catch (SQLException ex) {
            log.error("Error modificando presupuesto: "+ex.getMessage());
            respuesta = 2;
            throw FabricaExcepcion.instanciarModificarPresupuestoException(998, ex.getMessage());
        } catch (NullPointerException ex) {
            log.error("Error modificando presupuesto, hay un valor nulo " + ex.getMessage());
            throw FabricaExcepcion.instanciarModificarPresupuestoException(5, ex.getMessage());
        }
        return FabricaEntidad.obtenerSimpleResponse(respuesta);
    }

    /**
     * Metodo encargado de consultar un presupuesto en base de datos por su id
     * @param id
     * @return la entidad presupuesto
     * @throws ConsultarPresupuestoException 
     */
    @Override
    public Entidad consultar(int id) throws ConsultarPresupuestoException {
        Entidad presupuesto = FabricaIdentityMap.obtenerIdentityMap().getEntidadEnLista(RegistroIdentityMap.LISTA_PRESUPUESTO, id);

        if (presupuesto == null) {
            try {
                Connection conn = Conectar();
                PreparedStatement ps = conn.prepareStatement(RegistroBaseDatos.OBTENER_PRESUPUESTO);
                ps.setInt(1, id);
                System.out.println(ps.toString());
                ps.executeQuery();
                
                ResultSet rs = ps.getResultSet(); 
                if (rs.next()){
                presupuesto = new Presupuesto(rs.getInt(1), rs.getString(2), rs.getDouble(4),
                        rs.getString(6), rs.getInt(5), String.valueOf(rs.getInt(3)), 
                        String.valueOf(rs.getBoolean(7)));
                } else {
                    throw FabricaExcepcion.instanciarConsultarPresupuestoException(101);
                }
                FabricaIdentityMap.obtenerIdentityMap().setEntidad(RegistroIdentityMap.LISTA_PRESUPUESTO, presupuesto);
            } catch (SQLException e){
                log.error("Error consultando presupuesto: "+e.getMessage());
                throw FabricaExcepcion.instanciarConsultarPresupuestoException(998, e.getMessage());
            } catch (NullPointerException e){
                log.error("Error consultando presupuestos, hay un valor nulo "+e.getMessage());
                throw FabricaExcepcion.instanciarConsultarPresupuestoException(5, e.getMessage());
            }
        }
        return presupuesto;
    }

    /**
     * Metodo encargado de listar todos los presupuestos de un usuario en base de datos
     * @param idUsuario
     * @return un objeto ListaEntidad con todos los presupuestos de un usuario
     * @throws ListarPresupuestoException 
     */
    @Override
    public ListaEntidad consultarTodos(int idUsuario) throws ListarPresupuestoException {

        ListaEntidad listaEntidad = FabricaIdentityMap.obtenerIdentityMap().getListaEntidad(RegistroIdentityMap.LISTA_PRESUPUESTO);
                                  

        if (listaEntidad.getLista().isEmpty()) {

            try {
                ArrayList<Entidad> listaPresupuestos = new ArrayList<>();
                Connection conn = Conectar();
                PreparedStatement ps = conn.prepareStatement(RegistroBaseDatos.LISTAR_PRESUPUESTOS);
                ps.setInt(1, idUsuario);
                System.out.println(ps.toString());
                ps.executeQuery();

                ResultSet rs = ps.getResultSet();

                while (rs.next()) {
                    Presupuesto p = new Presupuesto(rs.getInt(1), rs.getString(2),
                            rs.getDouble(4), rs.getString(6),
                            rs.getInt(5), rs.getString(3),
                            String.valueOf(rs.getBoolean(7)));
                    listaPresupuestos.add(p);
                }

                listaEntidad = FabricaEntidad.obtenerListaEntidad(listaPresupuestos);
                FabricaIdentityMap.obtenerIdentityMap().setListaEntidad(RegistroIdentityMap.LISTA_PRESUPUESTO, listaEntidad);
               
            } catch (SQLException e) {
                log.error("Error listando presupuestos: "+e.getMessage());
                throw FabricaExcepcion.instanciarListarPresupuestoException(998, e.getMessage());
            } catch (NullPointerException e){
                log.error("Error listando presupuestos, hay un valor nulo "+e.getMessage());
                throw FabricaExcepcion.instanciarListarPresupuestoException(5, e.getMessage());
            }
        }
        return listaEntidad;
    }

    /**
     * Metodo encargado de verificar si un nombre de presupuesto ya existe en base de datos
     * @param nombre
     * @return 1 si encuentra el nombre, 0 en caso contrario
     * @throws VerificarNombreException 
     */
    @Override
    public Entidad verificarNombre(String nombre) throws VerificarNombreException{
        int respuesta = 0;
        
        try{
            Connection conn = Conectar();
            PreparedStatement ps = conn.prepareStatement(RegistroBaseDatos.VERIFICAR_NOMBRE);
            ps.setString(1, nombre);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();
            respuesta = rs.getInt("verificarnombre");
            ps.close();
            Desconectar(conn);
        } catch (SQLException e) {
            log.error("Error verificando nombre: " + e.getMessage());
            respuesta = 2;
            throw FabricaExcepcion.instanciarVerificarNombreException(998, e.getMessage());
        } catch (NullPointerException e) {
            log.error("Error verificando nombre, hay un valor nulo " + e.getMessage());
            throw FabricaExcepcion.instanciarVerificarNombreException(5, e.getMessage());
        }
        return FabricaEntidad.obtenerSimpleResponse(respuesta);
    }

    /**
     * Metodo encargado de eliminar un presupuesto 
     * @param id
     * @return 1 en caso de haberlo eliminado
     * @throws EliminarPresupuestoException 
     */
    @Override
    public Entidad eliminarPresupuesto(int id) throws EliminarPresupuestoException {
        
        int respuesta = 0;
        
        try {
            Connection conn = Conectar();
            PreparedStatement ps = conn.prepareStatement(RegistroBaseDatos.ELIMINAR_PRESUPUESTO);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet(); 
            if(rs.next()){
                if(rs.getInt("eliminarpresupuesto")==1){
                respuesta = rs.getInt("eliminarpresupuesto");
                } else
                    respuesta = 0;
                throw FabricaExcepcion.instanciarEliminarPresupuestoExeption(301);
            }
            
            ps.close();
            Desconectar(conn);
            FabricaIdentityMap.obtenerIdentityMap().rmEntidadEnLista(RegistroIdentityMap.LISTA_PRESUPUESTO, id);
        } catch (SQLException e) {
            log.error("Error eliminando presupuestos: " + e.getMessage());
            respuesta = 2;
            throw FabricaExcepcion.instanciarEliminarPresupuestoExeption(998, e.getMessage());
        } catch (NullPointerException e) {
            log.error("Error eliminando presupuesto, hay un valor nulo " + e.getMessage());
            throw FabricaExcepcion.instanciarEliminarPresupuestoExeption(5, e.getMessage());
        }
       
        return FabricaEntidad.obtenerSimpleResponse(respuesta);
    }

    /**
     * Metodo encargado de exportar una lista de presupuestos de un usuario
     * @param idUsuario
     * @return la lista del presupuesto a exportar
     * @throws FinUCABException 
     */
    @Override
    public ListaEntidad exportar(int idUsuario) throws FinUCABException {
            ListaEntidad listaEntidad = null;
        
        try {
                ArrayList<Entidad> listaPresupuestos = new ArrayList<>();
                Connection conn = Conectar();
                PreparedStatement ps = conn.prepareStatement(RegistroBaseDatos.LISTAR_PRESUPUESTOS_EXPORTAR);
                ps.setInt(1, idUsuario);
                System.out.println(ps.toString());
                ps.executeQuery();

                ResultSet rs = ps.getResultSet();

                while (rs.next()) {
                    Presupuesto p = new Presupuesto(rs.getInt(1), rs.getString(2),
                            rs.getDouble(4), rs.getString(6),
                            rs.getInt(5), rs.getString(3),
                            String.valueOf(rs.getBoolean(7)));
                    listaPresupuestos.add(p);
                }

                listaEntidad = FabricaEntidad.obtenerListaEntidad(listaPresupuestos);
                
            } catch (SQLException e) {
                log.error("Error listando presupuestos: "+e.getMessage());
                throw FabricaExcepcion.instanciarExportarPresupuestoException(998, e.getMessage());
            } catch (NullPointerException e){
                log.error("Error exportando presupuestos, hay un valor nulo "+e.getMessage());
                throw FabricaExcepcion.instanciarExportarPresupuestoException(5, e.getMessage());
            }
        return listaEntidad;
    }
        
    /**
     * Metodo encargado de obtener los ultimos presupuestos por usuario
     * @param id
     * @return un array de json con los presupuestos
     */
    public JsonArray getUltimosPresupuestos(int id) {
        CallableStatement cstm;
        JsonArray array = null;
        try {
            Statement st = Conexion.conectarADb().createStatement();
            cstm =Conexion.conectarADb().prepareCall(RegistroBaseDatos.ESTADISTICA_PRESUPUESTO);
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            int n = 0;
            while (rs.next()) {
                n++;
                cuentaBuilder.add("est_id", "5." + Integer.toString(n));
                cuentaBuilder.add("est_fecha", rs.getString("pr_fecha"));
                cuentaBuilder.add("est_transaccion", rs.getString("pr_nombre"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            array = arrayBuilder.build();

        } catch (SQLException ex) {
            log.error("Error listando ultimos presupuestos presupuestos: "+ex.getMessage());
            Logger.getLogger(DaoTarjeta_Credito.class.getName()).log(Level.SEVERE, null, ex);

        }
        return array;
    }

}