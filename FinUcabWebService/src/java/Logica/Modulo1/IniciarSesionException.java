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
public class IniciarSesionException extends FinUCABException{
    
    public IniciarSesionException(int code, String message) {
        super(code, message, IniciarSesionException.class);
    }
    
    public IniciarSesionException(int code) {
        super(code, IniciarSesionException.class);
    }
}
