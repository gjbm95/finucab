/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Interfaces;

import Dominio.Entidad;
import Logica.Modulo1.ActualizarClaveException;
import Logica.Modulo1.IniciarSesionException;
import Logica.Modulo1.RecuperarClaveException;
import Logica.Modulo1.VerificarUsuarioException;

/**
 *
 * @author Junior
 */
public interface IDAOUsuario extends IDAO {
    
    public Entidad ActualizarClave(Entidad entidad) 
            throws ActualizarClaveException;
    
    public Entidad verificarUsuario(String usuario)
            throws VerificarUsuarioException;
    
    public Entidad obtenerInicioSesion(Entidad usuario)
            throws IniciarSesionException;
    
    public Entidad obtenerXRecuperarClave(String usuario)
            throws RecuperarClaveException;
 
}
