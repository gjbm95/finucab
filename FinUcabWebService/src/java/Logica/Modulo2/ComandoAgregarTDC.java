package Logica.Modulo2;

import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Dominio.Tarjeta_Credito;
import Dominio.Usuario;
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
public class ComandoAgregarTDC extends Comando {

    private Tarjeta_Credito tdc ;
 
    
    
    public ComandoAgregarTDC(Tarjeta_Credito tdc) {
        this.tdc  = tdc;
    }

    
    /**
     * Metodo encargado de la ejecucion de registro de tarjetas de credito.
     */
    @Override
    public void ejecutar() throws AgregarFallidoException {

        DaoTarjeta_Credito daoTDC = FabricaDAO.instanciasDaoTarjeta_Credito();
        super.response = daoTDC.agregar(tdc);
    }
    
    
}   
