
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
public class AgregarFallidoException extends FinUCABException {
    
    
    public AgregarFallidoException(int code,String mensaje){
       super(code,mensaje,AgregarFallidoException.class);
    }
   
     public AgregarFallidoException(int code){
       super(code,AgregarFallidoException.class);
    }
    
}
