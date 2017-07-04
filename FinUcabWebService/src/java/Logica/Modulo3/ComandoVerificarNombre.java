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
public class ComandoVerificarNombre extends Comando {

    private String nombrePresupuesto;

    /**
     * Constructor de comando verificar presupuesto
     * @param idUsuario 
     */
    public ComandoVerificarNombre(String nombrePresupuesto) {
        this.nombrePresupuesto = nombrePresupuesto;
    }
       
    /**
     * Metodo encargado de instanciar el dao presupuesto para verificar un nombre de presupuesto
     * @throws FinUCABException 
     */
    @Override
    public void ejecutar() throws FinUCABException {
        IDAOPresupuesto dao = FabricaDAO.instanciasDAOPresupuesto();
        this.response = dao.verificarNombre(nombrePresupuesto);
    }
    
}
