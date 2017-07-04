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
public class ListarPresupuestoException extends FinUCABException{
    
    /**
     * Constructor de la excepcion listar presupuesto
     * @param code 
     */
        public ListarPresupuestoException(int code){
        super(code,ListarPresupuestoException.class);
    }
    
        /**
         * Constructor de la excepcion listar presupuesto
         * @param code
         * @param message 
         */
    public ListarPresupuestoException(int code, String message){
        super(code, message,ListarPresupuestoException.class);
    }
    
}
