/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import Exceptions.FinUCABException;

/**
 *
 * @author Ramon
 */

/**
 * Clase para la excepcion de consultar Pago
 * @author Juan
 */
public class ConsultarPagoException extends FinUCABException {
    
    public ConsultarPagoException(int code){
        super(code,ConsultarPagoException.class);
    }
    
    public ConsultarPagoException(int code, String message){
        super(code, message,ConsultarPagoException.class);
    }
    
}
