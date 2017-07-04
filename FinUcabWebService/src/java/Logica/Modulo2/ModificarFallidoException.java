
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
public class ModificarFallidoException extends FinUCABException {
    
    public ModificarFallidoException(int code,String mensaje){
      super(code,mensaje,ModificarFallidoException.class);
    }
    public ModificarFallidoException(int code){
      super(code,ModificarFallidoException.class);
    }
    
}
