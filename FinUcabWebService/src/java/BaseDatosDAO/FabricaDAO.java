/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO;

import BaseDatosDAO.Singleton.SingletonDAOPago;

/**
 *
 * @author Oswaldo
 */
public class FabricaDAO {
    
  
    
    public static DaoUsuario instanciasDaoUsuario() {
        return new DaoUsuario();
    }
    
    public static DAOCategoria instanciasDaoCategoria(){
        return new DAOCategoria();
    }
    
    public static DaoCuenta_Bancaria instanciasDaoCuenta_Bancaria() {
        return new DaoCuenta_Bancaria();
    }
    
    public static DaoTarjeta_Credito instanciasDaoTarjeta_Credito() {
        return new DaoTarjeta_Credito();
    }
    
    public static DAOPago instanciasDAOPago() {
        return DAOPago.getInstance();
    }
    
    public static DAOPresupuesto instanciasDAOPresupuesto() {
        return new DAOPresupuesto();
    }
    
    public static DaoPlanificacion instanciasDAOPlanificacion() {
        return new DaoPlanificacion();
    }
    
   
}
