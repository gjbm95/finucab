package Logica.Modulo2;

import BaseDatosDAO.DaoUsuario;
import BaseDatosDAO.FabricaDAO;
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
public class ComandoActualizarDatosUsuario extends Comando {

    private Usuario user ;
    private String result;
    
    
    public ComandoActualizarDatosUsuario(Usuario user) {
        this.user  = user;
    }

    
    /**
     * Metodo encargado de la ejecucion de la moficiacion de cuenta de usuario.
     */
    @Override
    public void ejecutar() {

        DaoUsuario daoUsuario = FabricaDAO.instanciasDaoUsuario();
        daoUsuario.modificar(user);
    }
    
    
}
