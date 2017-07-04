package Logica.Modulo1;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Entidad;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 * Modulo 1 - Modulo de Inicio de Sesion 
 * Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido 
 * Descripción de la clase: Clase encargada de realizar la llamada al DAOUsuario
 * para registrar el usuario
 */
public class ComandoRegistrarUsuario extends Comando{
    Entidad usuario;
    
    //Constructor
    public ComandoRegistrarUsuario(Entidad usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Procedimiento que se encarga de realizar la llamada al DAOUsuario
     * para registrar el usuario
     * @throws Exceptions.FinUCABException
     */
    @Override
    public void ejecutar() throws FinUCABException{
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.agregar(usuario);
    }
}