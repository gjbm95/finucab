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
public class AgregarPresupuestoException extends FinUCABException{
    
    /**
     * Constructor de la excepcion de agregar presupuesto
     * @param code 
     */
    public AgregarPresupuestoException(int code) {
        super(code, AgregarPresupuestoException.class);
    }
    
    /**
     * Constructor de la excepcion de agregar presupuesto
     * @param code
     * @param message 
     */
    public AgregarPresupuestoException(int code, String message){
        super (code, message,AgregarPresupuestoException.class);
    }
    
}
