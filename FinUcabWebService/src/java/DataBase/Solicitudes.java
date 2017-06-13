/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**

*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Clase encargada del manejo de peticiones a la base de datos. 
*@Params
*
**/
public class Solicitudes {
    
    /*
       Procedimiento generico en cargado de gestionar las solicitudes 
       a la base de datos. 
       @Params procedimiento - Se encarga de recibir el nombre del procedimiento 
       @Params parametros - Cadena de caracteres que contiene separado por comas 
                           los valores de los diferentes parametros que recibe 
                           el metodo. 
    */
    public ResultSet ejecutarFuncion(String procedimiento,String parametros){
    ResultSet resultados = null;
     
    try {
            Connection conn = Conexion.conectarADb();
            Statement st = conn.createStatement();
            resultados = st.executeQuery("SELECT * FROM "+procedimiento+"("+parametros+");");
            return resultados; 
         } catch (Exception e) {
            e.getMessage();
        }
    
    
      return resultados; 
    }
    
    
    
}
