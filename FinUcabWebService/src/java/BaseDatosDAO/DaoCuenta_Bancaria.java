package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAOCuentaBancaria;
import Dominio.Cuenta_Bancaria;
import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import Dominio.Usuario;
import Exceptions.FabricaExcepcion;
import IndentityMap.IdentityMap;
import Logica.Modulo2.AgregarFallidoException;
import Logica.Modulo2.EliminarFallidoException;
import Logica.Modulo2.MapaModulo2;
import Logica.Modulo2.ModificarFallidoException;
import Registro.RegistroBaseDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class DaoCuenta_Bancaria extends DAO implements IDAOCuentaBancaria{

    private Connection conn = Conexion.conectarADb();

    /**
     * Metodo encargado de registrar Cuentas Bancarias en la base de datos 
     * 
     * @param e Entidad Cuenta_Bancaria a ser almacenada
     * @return Objeto de tipo Cuenta_Bancaria (Entidad sin castear)
     */
    @Override
    public Entidad agregar(Entidad e) throws AgregarFallidoException {
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        Cuenta_Bancaria obj =(Cuenta_Bancaria) cache.getEntidad("CuentaNueva");      
        CallableStatement cstmt;
        int idCuenta = 0;
        try {
            cstmt = conn.prepareCall(RegistroBaseDatos.AGREGAR_CUENTABANCARIA);
            cstmt.setString(1, obj.getNombreBanco());
            cstmt.setString(2, obj.getTipoCuenta());
            cstmt.setString(3, obj.getNumcuenta());
            cstmt.setFloat(4, obj.getSaldoActual());
            cstmt.setInt(5, obj.getIdusuario());
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idCuenta = rs.getInt(1);
            obj.setId(idCuenta);
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Agregado Cuenta Bancaria de id: "+idCuenta);
            cstmt.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Agregado Cuenta Bancaria de id: "+idCuenta);
        }catch (SQLException ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).
                    log(Level.SEVERE, null, ex);
            throw FabricaExcepcion.instanciarAgregarFallidoException
        (ex.getErrorCode(),ex.getMessage() );
        }catch (Exception ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }

     /**
     * Metodo encargado de modificar Cuentas Bancarias en la base de datos 
     * 
     * @param e Entidad Cuenta_Bancaria a ser modificada
     * @return Objeto de tipo Cuenta_Bancaria (Entidad sin castear)
     */
    public Entidad modificar(Entidad e) throws ModificarFallidoException{
        MapaModulo2 cache = MapaModulo2.obtenerInstancia();
        Cuenta_Bancaria obj = (Cuenta_Bancaria)cache.getEntidad("CuentaModificada");
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall(RegistroBaseDatos.MODIFICAR_CUENTABANCARIA);
            cstmt.setString(3, obj.getTipoCuenta());
            cstmt.setString(2, obj.getNumcuenta());
            cstmt.setString(1, obj.getNombreBanco());
            cstmt.setFloat(4, obj.getSaldoActual());
            cstmt.setInt(5, obj.getId());
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Modificado Cuenta Bancaria de id: "+obj.getId());
            cstmt.execute();
            cstmt.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Modificado Cuenta Bancaria de id:"+obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).
                    log(Level.SEVERE, null, ex);
            throw FabricaExcepcion.instanciarModificarFallidoException
        (ex.getErrorCode(),ex.getMessage() );
        }catch (Exception ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Entidad consultar(int id) {
        return null;
    }

    /**
     * Metodo encargado de eliminar Cuentas Bancarias en la base de datos 
     * 
     * @param id
     * @param e Id del usuario titular de la cuenta a eliminar
     * @return Id de la cuenta eliminada
     */
    public int eliminar(int id) throws EliminarFallidoException{
        CallableStatement cstmt;
        int idCuenta = 0;
        try {
            cstmt = conn.prepareCall(RegistroBaseDatos.ELIMINAR_CUENTABANCARIA);
            cstmt.setInt(1, id);
            cstmt.executeQuery();
            ResultSet rs = cstmt.getResultSet();
            rs.next();
            idCuenta = id;
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Eliminado Cuenta Bancaria de id: "+id);
            cstmt.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Eliminado Cuenta Bancaria de id: "+id);
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).
                    log(Level.SEVERE, null, ex);
            throw FabricaExcepcion.instanciarEliminarFallidoException
        (ex.getErrorCode(),ex.getMessage() );
        }catch (Exception ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCuenta;
    }

     /**
     * Metodo encargado de mostrar Cuentas Bancarias en la base de datos 
     * 
     * @param e Id del usuario titular de las cuentas bancarias 
     * @return Arreglo de Jsons con los datos de las cuentas bancarias
     */
    public String getCuentasXUsuario(int id) {
        CallableStatement cstm;
        String respuesta;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall(RegistroBaseDatos.OBTENER_CUENTASBANCARIAS);
            cstm.setInt(2, id);
            cstm.setString(1, "OBTENERCUENTASUSUARIO");
            System.out.println("Entre con id" + id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (rs.next()) {   
                cuentaBuilder.add("ct_id", rs.getString("ct_id"));
                cuentaBuilder.add("ct_tipo", rs.getString("ct_tipocuenta"));
                cuentaBuilder.add("ct_numerocuenta", rs.getString("ct_numcuenta"));
                cuentaBuilder.add("ct_nombrebanco", rs.getString("ct_nombrebanco"));
                cuentaBuilder.add("ct_saldoactual", rs.getString("ct_saldo"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            JsonArray array = arrayBuilder.build();
            respuesta = array.toString();
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Lista de Cuentas Bancarias obtenidas del usuario de id: "+id);
            cstm.close();
            st.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Lista de Cuentas Bancarias obtenidas del usuario de id: "+id);
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "0";
        }catch (Exception ex) {
            respuesta = "0";
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }


     /**
     * Metodo encargado de registrar Cuentas Bancarias en la base de datos 
     * 
     * @param e Entidad Cuenta_Bancaria a ser almacenada
     * @return Objeto de tipo Cuenta_Bancaria (Entidad sin castear)
     */
    public String getSaldoTotal(int id) {
        CallableStatement cstm;
        String respuesta;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall(RegistroBaseDatos.OBTENER_SALDOCUENTAS);
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            if (rs.next()) {
                respuesta = rs.getString(1);
            }
            else {
                respuesta = "";
            }
            Logger.getLogger(getClass().getName()).log(
            Level.FINER, "Saldo obtenido del usuario de id: "+id);
            cstm.close();
            st.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Saldo obtenido del usuario de id: "+id);
        } catch (SQLException ex) {
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).log(Level.SEVERE, null, ex);
            respuesta = "e";
        }catch (Exception ex) {
            respuesta = "e";
            Logger.getLogger(DaoCuenta_Bancaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public ListaEntidad consultarTodos(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


}
