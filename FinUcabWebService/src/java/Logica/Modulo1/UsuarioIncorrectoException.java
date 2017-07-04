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
public class UsuarioIncorrectoException extends FinUCABException{
    
    public UsuarioIncorrectoException(int code, String message) {
        super(code, message, UsuarioIncorrectoException.class);
    }
    
    public UsuarioIncorrectoException(int code){
        super(code,UsuarioIncorrectoException.class);
    }
    
}
