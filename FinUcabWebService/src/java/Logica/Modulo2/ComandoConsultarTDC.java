package Logica.Modulo2;

import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
import Dominio.FabricaEntidad;
import Dominio.Tarjeta_Credito;
import Logica.Comando;
import Services.Modulo1sResource;
import Services.Modulo2sResource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ComandoConsultarTDC extends Comando {

    private int user ;
 
    
    
    public ComandoConsultarTDC(int user) {
        this.user  = user;
    }

    
    /**
     * Metodo encargado de la ejecucion de consulta de tarjetas de credito
     */
    @Override
    public void ejecutar() {

        DaoTarjeta_Credito dao = FabricaDAO.instanciasDaoTarjeta_Credito();
        super.response = FabricaEntidad.obtenerSimpleResponse(0,0,dao.getTarjetasXUsuario(user));
      
    }
    
    
}
