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
public class ActualizarClaveException extends FinUCABException{
    
    public ActualizarClaveException(int code, String message) {
        super(code, message,ActualizarClaveException.class);
    }
    
    public ActualizarClaveException(int code){
        super(code,ActualizarClaveException.class);
    }
}
