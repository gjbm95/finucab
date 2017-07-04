/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Interfaces.IDAO;
import Dominio.Entidad;
import Registro.RegistroBaseDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William
 */
public abstract class DAO implements IDAO {

    Connection conn;
    
    /**
     * Metodo que crea el conector de la base de datos
     *
     * @return un conector para hacer llamadas a la BD
     */
    public Connection Conectar() {

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(RegistroBaseDatos.url, 
                    RegistroBaseDatos.nombreDB, RegistroBaseDatos.contrasenaDB);
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    /**
     * Metodo que establece la conexion a la base de datos
     *
     * @return un conector para hacer llamadas a la BD
     */
    public void Desconectar(){
        
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Metodo que desconecta la conexion a la base de datos
     *
     * @return un conector para hacer llamadas a la BD
     */
    public void Desconectar(Connection conexion){
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
