/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions.Presupuesto;

import Exceptions.FinUCABException;

/**
 *
 * @author William
 */
public class ExportarPresupuestoException extends FinUCABException{
    
    /**
     * Constructor de la excepcion exportar presupuesto
     * @param code 
     */
    public ExportarPresupuestoException(int code){
        super(code,ExportarPresupuestoException.class);
    }
    
    /**
     * Constructor de la excepcion exportar presupuesto
     * @param code
     * @param message 
     */
    public ExportarPresupuestoException(int code, String message){
        super(code, message,ExportarPresupuestoException.class);
    }
    
}
