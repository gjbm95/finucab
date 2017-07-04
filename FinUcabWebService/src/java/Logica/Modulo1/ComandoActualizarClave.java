package Logica.Modulo1;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Entidad;
import Logica.Comando;

/**
 * Modulo 1 - Modulo de Presupuestos 
 * Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido 
 * Descripción de la clase: Clase encargada de realizar la llamada al DAOUsuario
 * para actualizar la clave del usuario
 */
public class ComandoActualizarClave extends Comando{
    Entidad usuario;
    
    //Constructor
    public ComandoActualizarClave(Entidad usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Procedimiento que se encarga de realizar la llamada al DAOUsuario
     * para actualizar la clave del usuario
     */
    @Override
    public void ejecutar() throws ActualizarClaveException{
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.ActualizarClave(usuario);
    }
}