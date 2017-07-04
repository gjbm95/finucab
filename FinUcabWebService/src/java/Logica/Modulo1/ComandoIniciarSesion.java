package Logica.Modulo1;


import Logica.Comando;
import BaseDatosDAO.*;
import BaseDatosDAO.Interfaces.IDAOUsuario;
import Dominio.Entidad;

/**
 * Modulo 1 - Modulo de Inicio de Sesion 
 * Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido 
 * Descripción de la clase: Clase encargada de realizar la llamada al DAOUsuario
 * para verificar si los datos de inicio de sesion son correctos o incorrectos
 */
public class ComandoIniciarSesion extends Comando{
    Entidad usuario;
    
    //Constructor
    public ComandoIniciarSesion(Entidad usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Procedimiento que se encarga de realizar la llamada al DAOUsuario
     * para verificar los datos ingresados por el usuario.
     * @throws Logica.Modulo1.IniciarSesionException
     */
    @Override
    public void ejecutar() throws IniciarSesionException{
        IDAOUsuario dao = FabricaDAO.instanciasDaoUsuario();
        this.response = dao.obtenerInicioSesion(usuario);
    }
}