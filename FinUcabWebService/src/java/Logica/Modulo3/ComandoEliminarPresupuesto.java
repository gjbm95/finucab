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
public class ComandoEliminarPresupuesto extends Comando {
    
    private int idPresupuesto;

    /**
     * Constructor de comando eliminar presupuesto
     * @param idPresupuesto 
     */
    public ComandoEliminarPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }
    
    /**
     * Metodo que instancia el dao presupuesto para eliminar un presupuesto
     * @throws FinUCABException 
     */
    @Override
    public void ejecutar() throws FinUCABException {
        IDAOPresupuesto dao = FabricaDAO.instanciasDAOPresupuesto();
        this.response = dao.eliminarPresupuesto(idPresupuesto);
    }
    
}
