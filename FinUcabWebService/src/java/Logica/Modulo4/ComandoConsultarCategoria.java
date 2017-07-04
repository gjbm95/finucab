/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOCategoria;
import Dominio.Categoria;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author Jeffrey
 */

public class ComandoConsultarCategoria extends Comando{
    private int idcategoria;
    
    public ComandoConsultarCategoria(int categoria){
        this.idcategoria = categoria;
    }

    @Override
    public void ejecutar() throws FinUCABException {
     
        IDAOCategoria dao = FabricaDAO.instanciasDaoCategoria();
        this.response = dao.consultar(idcategoria);
        System.out.println("responsecosnsultar"+this.response);
        
    }
    
    
}
