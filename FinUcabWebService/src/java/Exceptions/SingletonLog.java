/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import BaseDatosDAO.Singleton.*;
import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOPago;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Ramon
 */
public class SingletonLog {
    
    private SingletonLog() {
    }
    
    private static Logger INSTANCE; 
    
    public static Logger getInstance() {
        
        if (INSTANCE == null){
            INSTANCE = LogManager.getLogger();
        }
        
        return SingletonLog.INSTANCE;
    }
    

}
