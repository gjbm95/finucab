package BaseDatosDAO;

import Dominio.Entidad;
import Dominio.ListaEntidad;
import Registro.RegistroBaseDatos;
import java.sql.CallableStatement;
import java.sql.Connection;
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
/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class DaoPlanificacion extends DAO {

    private Connection conn = Conexion.conectarADb();
    
    public JsonArray getUltimasPlanificaciones(int id) {
        CallableStatement cstm;
        JsonArray array = null;
        try {
            Statement st = conn.createStatement();
            cstm = conn.prepareCall(RegistroBaseDatos.ESTADISTICA_PLANIFICACION);
            cstm.setInt(1, id);
            ResultSet rs = cstm.executeQuery();
            JsonObjectBuilder cuentaBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            int n = 0;
            while (rs.next()) {
                n++;
                cuentaBuilder.add("est_id", "4." + Integer.toString(n));
                cuentaBuilder.add("est_fecha", rs.getString("pa_fecha"));
                cuentaBuilder.add("est_transaccion", rs.getString("pa_nombre"));
                JsonObject cuentaJsonObject = cuentaBuilder.build();
                arrayBuilder.add(cuentaJsonObject);
            }
            array = arrayBuilder.build();
            cstm.close();
            st.close();
            rs.close();
            Logger.getLogger(getClass().getName()).log(
            Level.INFO, "Lista de proximos pagos obtenidos con exito");
        } catch (SQLException ex) {
            Logger.getLogger(DaoPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(DaoPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    @Override
    public Entidad agregar(Entidad e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Entidad modificar(Entidad e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Entidad consultar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public ListaEntidad consultarTodos(int idUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
