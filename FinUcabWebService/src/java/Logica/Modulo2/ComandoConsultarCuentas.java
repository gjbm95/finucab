package Logica.Modulo2;

import BaseDatosDAO.DaoCuenta_Bancaria;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Dominio.Cuenta_Bancaria;
import Dominio.FabricaEntidad;
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
public class ComandoConsultarCuentas extends Comando {

    private int idusuario ;
 
    
    
    public ComandoConsultarCuentas(int idusuario) {
        this.idusuario  = idusuario;
    }

    
    /**
     * Metodo encargado de la ejecucion de consulta de cuentas bancarias
     */
    @Override
    public void ejecutar() {

        DaoCuenta_Bancaria dao = FabricaDAO.instanciasDaoCuenta_Bancaria();
        super.response = FabricaEntidad.obtenerSimpleResponse(0,0,
                dao.getCuentasXUsuario(idusuario));
    }
    
    
}
