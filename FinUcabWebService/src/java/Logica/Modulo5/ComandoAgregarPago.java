/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import BaseDatosDAO.Interfaces.IDAOPago;
import BaseDatosDAO.Singleton.SingletonDAOPago;
import Dominio.Entidad;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author Juan
 */


/**
 * Clase Comando Agregar Pago 
 * @author Juan
 */
public class ComandoAgregarPago extends Comando {

    private Entidad pago;
     
     public ComandoAgregarPago(Entidad pago){
         this.pago=pago;
     }

     /**
      * metodo para ejecutar el Dao
      * @throws FinUCABException 
      */
    @Override
    public void ejecutar() throws FinUCABException {
        IDAOPago dao = SingletonDAOPago.getInstance();
        this.response = dao.agregar(pago);
    }
    
}
