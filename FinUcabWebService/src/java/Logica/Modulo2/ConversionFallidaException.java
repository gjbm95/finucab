/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo2;

import Exceptions.FinUCABException;

/**
 *
 * @author Junior
 */
public class ConversionFallidaException extends FinUCABException{
      
    public ConversionFallidaException (int code,String mensaje){
      super(code,mensaje,ConversionFallidaException.class);
    }
    public ConversionFallidaException (int code){
      super(code,ConversionFallidaException.class);
    }  
    
}
