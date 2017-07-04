package Logica.Modulo2;

import BaseDatosDAO.DaoCuenta_Bancaria;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOCuentaBancaria;
import Dominio.Cuenta_Bancaria;
import Dominio.Usuario;
import Exceptions.FinUCABException;
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
public class ComandoActualizarCuenta extends Comando {

    private Cuenta_Bancaria cuenta ;
    
    
    public ComandoActualizarCuenta(Cuenta_Bancaria cuenta) {
        this.cuenta  = cuenta;
    }

    
    /**
     * Metodo encargado de la ejecucion de la moficiacion de cuenta de  bancaria.
     */
    @Override
    public void ejecutar() throws FinUCABException {

        IDAOCuentaBancaria daoCuenta = FabricaDAO.instanciasDaoCuenta_Bancaria();
        daoCuenta.modificar(cuenta);
    }
    
    
}
