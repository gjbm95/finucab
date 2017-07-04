/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo3;

import BaseDatosDAO.FabricaDAO;
import BaseDatosDAO.Interfaces.IDAO;
import BaseDatosDAO.Interfaces.IDAOPresupuesto;
import Dominio.Entidad;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author William
 */
public class ComandoAgregarPresupuesto extends Comando{

    private Entidad presupuesto;

    /**
     * Constructor de comando agregar presupuesto
     * @param presupuesto 
     */
    public ComandoAgregarPresupuesto(Entidad presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    /**
     * Metodo que instancia el dao de presupuesto para agregar un presupuesto
     * @throws FinUCABException 
     */
    @Override
    public void ejecutar() throws FinUCABException {
        IDAOPresupuesto dao = FabricaDAO.instanciasDAOPresupuesto();
        this.response = dao.agregar(presupuesto);
    }
    
}