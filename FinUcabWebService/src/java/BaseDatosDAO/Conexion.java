/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import Registro.RegistroBaseDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Junior
 */
public class Conexion {
    
    
    
    
    /**
     * Metodo que crea el conector de la base de datos
     * @return un conector para hacer llamadas a la BD
     */
   //Conexion con la bse de datos.
    public static Connection conectarADb()
    {
        Connection conn = null;
        try
        {
            //llamo al driver de Postgre 
            //(el primer import que muestro en el video)
            Class.forName("org.postgresql.Driver");      
            conn = DriverManager.getConnection(RegistroBaseDatos.url
                    ,RegistroBaseDatos.nombreDB
                    ,RegistroBaseDatos.contrasenaDB);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.getMessage();
            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }

    
    
}
