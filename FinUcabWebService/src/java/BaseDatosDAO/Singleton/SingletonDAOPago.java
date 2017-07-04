/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Singleton;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOPago;

/**
 *
 * @author Ramon
 */
public class SingletonDAOPago {
    
    private SingletonDAOPago() {
    }
    
    private static IDAOPago INSTANCE; 
    
    public static IDAOPago getInstance() {
        
        if (INSTANCE == null){
            INSTANCE = FabricaDAO.instanciasDAOPago();
        }
        
        return SingletonDAOPago.INSTANCE;
    }
    

}
