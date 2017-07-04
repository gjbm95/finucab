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
public class VerificarNombreException extends FinUCABException{
    
    /**
     * Constructor de la excepcion verificar nombre
     * @param code 
     */
    public VerificarNombreException(int code){
        super(code,VerificarNombreException.class);
    }
    
    /**
     * Constructor de la excepcion verificar nombre
     * @param code
     * @param message 
     */
    public VerificarNombreException(int code, String message){
        super(code, message,VerificarNombreException.class);
    }
    
}
