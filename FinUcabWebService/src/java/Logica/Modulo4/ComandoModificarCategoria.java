/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo4;

import BaseDatosDAO.DAO;
import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOCategoria;
import Dominio.Entidad;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author MariPerez
 */
public class ComandoModificarCategoria extends Comando{
    public Entidad categoria;
    
    public ComandoModificarCategoria(Entidad categoria){
        this.categoria = categoria;
    }

    @Override
    public void ejecutar() throws FinUCABException {
        IDAOCategoria dao = FabricaDAO.instanciasDaoCategoria();
        this.response = dao.modificar(categoria);

    }
    
    
}