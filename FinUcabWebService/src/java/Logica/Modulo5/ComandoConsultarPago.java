/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import BaseDatosDAO.Interfaces.IDAOPago;
import BaseDatosDAO.Singleton.SingletonDAOPago;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author Juan
 */

/**
 * Comando Para consultar Pago
 * @author Juan
 */
public class ComandoConsultarPago extends Comando {
    
    private int idPago;
    
    public ComandoConsultarPago(int idPago){
        this.idPago = idPago;
    }
    
    @Override
    public void ejecutar() throws FinUCABException{
        IDAOPago dao = SingletonDAOPago.getInstance();
        this.response = dao.consultar(idPago);            

    }
    
}
