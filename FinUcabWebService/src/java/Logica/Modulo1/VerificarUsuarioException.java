/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo1;

import Exceptions.FinUCABException;

/**
 *
 * @author Somebody
 */
public class VerificarUsuarioException extends FinUCABException{
    
    public VerificarUsuarioException(int code, String message) {
        super(code, message, VerificarUsuarioException.class);
    }
    
    public VerificarUsuarioException(int code){
        super(code,VerificarUsuarioException.class);
    }
    
}
