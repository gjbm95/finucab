/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo3;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author William
 */
public class ComandoListarPresupuestos extends Comando {

    private int idUsuario; 

    /**
     * Constructor de comando listar presupuesto
     * @param idUsuario 
     */
    public ComandoListarPresupuestos(int idUsuario) {
        this.idUsuario = idUsuario;
    }
        
    /**
     * Metodo encargado de instanciar el dao presupuesto para crear la lista de presupuestos
     * @throws FinUCABException 
     */
    @Override
    public void ejecutar() throws FinUCABException{
        IDAOPresupuesto dao = FabricaDAO.instanciasDAOPresupuesto();
        this.response = dao.consultarTodos(idUsuario);
    }
    
}
