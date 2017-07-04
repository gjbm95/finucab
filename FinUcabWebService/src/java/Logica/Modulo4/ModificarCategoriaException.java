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
public class ModificarCategoriaException extends FinUCABException {
    
    public ModificarCategoriaException(int code, String message){
        super(code, message,ModificarCategoriaException.class);
    }
    
}
