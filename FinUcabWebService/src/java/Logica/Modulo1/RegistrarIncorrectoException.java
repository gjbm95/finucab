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
public class RegistrarIncorrectoException extends FinUCABException{
    
    public RegistrarIncorrectoException(int code, String message) {
        super(code, message,  RegistrarIncorrectoException.class);
    }
    
     public RegistrarIncorrectoException(int code){
        super(code,RegistrarIncorrectoException.class);
    }
    
}
