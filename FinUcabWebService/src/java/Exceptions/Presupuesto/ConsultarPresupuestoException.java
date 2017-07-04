/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions.Presupuesto;

import Exceptions.FinUCABException;

/**
 *
 * @author CHRISTIAN
 */
public class ConsultarPresupuestoException extends FinUCABException{
    
    /**
     * Constructor de la excepcion de consultar presupuesto
     * @param code 
     */
        public ConsultarPresupuestoException(int code){
        super(code,ConsultarPresupuestoException.class);
    }
    
        /**
         * Constructor de la excepcion de consultar presupuesto
         * @param code
         * @param message 
         */
    public ConsultarPresupuestoException(int code, String message){
        super(code, message,ConsultarPresupuestoException.class);
    }
    
}
