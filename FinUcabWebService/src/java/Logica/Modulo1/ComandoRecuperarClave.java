package Logica.Modulo1;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Logica.Comando;

/**
 * Modulo 1 - Modulo de Inicio de Sesion 
 * Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido 
 * Descripción de la clase: Clase encargada de realizar la llamada al DAOUsuario
 * para obtener datos del usuario que desea recuperar su clave
 */
public class ComandoRecuperarClave extends Comando{
    String usuario;
    
    //Constructor
    public ComandoRecuperarClave(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Procedimiento que se encarga de realizar la llamada al DAOUsuario
     * para obtener datos del usuario que desea recuperar su clave
     * @throws Logica.Modulo1.RecuperarClaveException
     */
    @Override
    public void ejecutar() throws RecuperarClaveException{
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.obtenerXRecuperarClave(usuario);
    }
}
