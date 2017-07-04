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
public interface IDAOPresupuesto extends IDAO{
    
    Entidad verificarNombre(String nombre) throws FinUCABException;
    Entidad eliminarPresupuesto(int idPresupuesto) throws FinUCABException;
    ListaEntidad exportar(int idUsuario) throws FinUCABException;
    
}
