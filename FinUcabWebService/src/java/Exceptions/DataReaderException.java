/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Ramon
 */
public class DataReaderException extends FinUCABException{
    
    public DataReaderException(int code){
        super(code, DataReaderException.class);        
    }
    
    
}
