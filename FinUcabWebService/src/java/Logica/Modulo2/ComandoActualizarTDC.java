package Logica.Modulo2;

import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.FabricaDAO;
import Dominio.Tarjeta_Credito;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.JsonObject;


/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class ComandoActualizarTDC extends Comando {

    private Tarjeta_Credito tdc ;
 
    
    
    public ComandoActualizarTDC(Tarjeta_Credito tdc) {
        this.tdc  = tdc;
    }

    
    /**
     * Metodo encargado de la ejecucion de la moficiacion de tarjeta de credito.
     */
    @Override
    public void ejecutar() throws ModificarFallidoException {

        DaoTarjeta_Credito daoTarjeta_Credito = FabricaDAO.instanciasDaoTarjeta_Credito();
        daoTarjeta_Credito.modificar(tdc);
    }
    
    
}
