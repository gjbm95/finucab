package Logica.Modulo2;
import BaseDatosDAO.DaoCuenta_Bancaria;
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
public class ComandoEliminarCuenta extends Comando {

    private int id;

    public ComandoEliminarCuenta(int id) {
        this.id = id;
    }

    /**
     * Metodo encargado de la ejecucion de eliminar cuentas bancarias.
     */
    @Override
    public void ejecutar() throws EliminarFallidoException {
        DaoCuenta_Bancaria daoCuenta = FabricaDAO.instanciasDaoCuenta_Bancaria();
        
        super.response = FabricaEntidad.obtenerSimpleResponse(daoCuenta.eliminar(id));
    }

}