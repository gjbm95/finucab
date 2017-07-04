/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatosDAO.Interfaces;

import Dominio.Entidad;

/**
 *
 * @author MariPerez
 */
public interface IDAOCategoria extends IDAO{
    
    Entidad eliminarCategoria(int id);
    
}
