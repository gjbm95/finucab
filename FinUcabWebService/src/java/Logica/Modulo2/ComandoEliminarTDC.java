package Logica.Modulo2;
import BaseDatosDAO.DaoTarjeta_Credito;
import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
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
public class ComandoEliminarTDC extends Comando {

    private int tdc ;
 
    
    
    public ComandoEliminarTDC(int tdc) {
        this.tdc  = tdc;
    }

    
    /**
     * Metodo encargado de la ejecucion de eliminar tarjetas de credito.
     */
    @Override
    public void ejecutar() throws EliminarFallidoException {
        DaoTarjeta_Credito daoTarjeta_credito = 
                FabricaDAO.instanciasDaoTarjeta_Credito();
        super.response = 
        FabricaEntidad.obtenerSimpleResponse(daoTarjeta_credito.eliminar(tdc));
    }
    
    
}
