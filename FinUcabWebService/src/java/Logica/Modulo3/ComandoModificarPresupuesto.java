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
public class ComandoModificarPresupuesto extends Comando{
    
    private Entidad presupuesto;

    /**
     * Constructor de comando modificar presupuesto
     * @param idUsuario 
     */
    public ComandoModificarPresupuesto(Entidad presupuesto) {
        this.presupuesto = presupuesto;
    }

    /**
     * Metodo encargado de instanciar el dao presupuesto para modificar un presupuesto
     * @throws FinUCABException 
     */
    @Override
    public void ejecutar() throws FinUCABException{
        
        IDAOPresupuesto dao = FabricaDAO.instanciasDAOPresupuesto();
        this.response = dao.modificar(presupuesto);
        
    }
    
    
}
