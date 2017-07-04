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
public class RecuperarClaveException extends FinUCABException {
    
    public RecuperarClaveException(int code, String message) {
        super(code, message, RecuperarClaveException.class);
    }
    
    public RecuperarClaveException(int code){
        super(code,RecuperarClaveException.class);
    }
}
