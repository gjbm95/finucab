/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions.Presupuesto;

import Exceptions.FinUCABException;
import Logica.Modulo5.ModificarPagoException;

/**
 *
 * @author CHRISTIAN
 */
public class ModificarPresupuestoException extends FinUCABException{
    
    /**
     * Constructor de la excepcion modificar presupuesto
     * @param code 
     */
    public ModificarPresupuestoException(int code){
        super(code,ModificarPresupuestoException.class);
    }
    
    /**
     * Constructor de la excepcion modificar presupuesto
     * @param code
     * @param message 
     */
    public ModificarPresupuestoException(int code, String message){
        super(code, message,ModificarPresupuestoException.class);
    }
    
}
