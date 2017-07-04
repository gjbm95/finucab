/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import Registro.RegistroError;

/**
 *
 * @author Ramon
 */
public abstract class FinUCABException extends Exception{
    
    private int _code;
    private String _message;
    private Class _className;
    
    public FinUCABException(int code, Class className){
        super();        
        this._code = code;
        String message = RegistroError.errores.get(code);
        if(message == null){
            this._message = RegistroError.error_default;
        }else{
            this._message = message;
        }
        this._className = className;
        
        
        SingletonLog.getInstance().error("Error ("+_className+") : "+_code+" - "+ _message);
    }
    
    public FinUCABException(int code, String message, Class className){
        super();        
        this._code = code;
        this._message = message;
        this._className = className;
        
        SingletonLog.getInstance().error("Error ("+_className+") : "+_code+" - "+ _message);
    }
    
    public String getOwnMessage(){
        return _message;
    }
    
    
}
