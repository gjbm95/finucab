package Logica.Modulo2;

import BaseDatosDAO.DaoCuenta_Bancaria;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Dominio.Cuenta_Bancaria;
import Dominio.Usuario;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import javax.json.JsonObject;

/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
* Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Metodos del servicio web destinados para las funcionalidades de Home y 
* Tarjetas de Credito y Cuentas Bancarias. 
*
**/
public class ComandoAgregarCuenta extends Comando {

    private Cuenta_Bancaria cuenta ;
    
    public ComandoAgregarCuenta(Cuenta_Bancaria cuenta) {
        this.cuenta  = cuenta;
    }

    
    /**
     * Metodo encargado de la ejecucion de registro de cuentas bancarias.
     */
    @Override
    public void ejecutar() throws AgregarFallidoException {
        
        DaoCuenta_Bancaria daoCuenta = FabricaDAO.instanciasDaoCuenta_Bancaria();
        cuenta = (Cuenta_Bancaria) daoCuenta.agregar(cuenta);
        super.response = cuenta;
    }
    
    
}
