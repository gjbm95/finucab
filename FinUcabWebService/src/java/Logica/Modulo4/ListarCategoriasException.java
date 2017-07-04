/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import Exceptions.FinUCABException;

/**
 *
 * @author Jeffrey
 */
public class ListarCategoriasException extends FinUCABException {
    
    public ListarCategoriasException(int code, String message){
        super(code, message,ListarCategoriasException.class);
    }
    
}
