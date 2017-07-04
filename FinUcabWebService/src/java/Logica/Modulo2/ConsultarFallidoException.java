package Logica.Modulo2;

import Exceptions.FinUCABException;

/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class ConsultarFallidoException extends FinUCABException {
    
    public ConsultarFallidoException(int code){
      super(code,ConsultarFallidoException.class);
    }
    
    public ConsultarFallidoException(int code,String mensaje){
      super(code,mensaje,ConsultarFallidoException.class);
    }
}
