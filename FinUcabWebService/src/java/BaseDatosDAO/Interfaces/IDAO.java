/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Interfaces;

import Dominio.Entidad;
import Dominio.ListaEntidad;
import Exceptions.FinUCABException;

/**
 *
 * @author William
 */
public interface IDAO {
    
    Entidad agregar(Entidad e) throws FinUCABException;
    
    Entidad modificar(Entidad e)throws FinUCABException;
    
    Entidad consultar(int id) throws FinUCABException;
    
    ListaEntidad consultarTodos(int idUsuario) throws FinUCABException;
}
