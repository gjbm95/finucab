package Logica.Modulo1;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Logica.Comando;

/**
 * Modulo 1 - Modulo de Inicio de Sesion 
 * Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido 
 * Descripción de la clase: Clase encargada de realizar la llamada al DAOUsuario
 * para verificar la existencia del usuario.
 */
public class ComandoVerificarUsuario extends Comando{
    String usuario;
    
    //Constructor
    public ComandoVerificarUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Metodo encargado de realizar la llamada al DAOUsuario para verificar la 
     * existencia del usuario.
     */
    @Override
    public void ejecutar() throws VerificarUsuarioException{
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.verificarUsuario(usuario);
    }
}