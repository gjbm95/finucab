/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica.Modulo5;

import BaseDatosDAO.Interfaces.IDAOPago;
import BaseDatosDAO.Singleton.SingletonDAOPago;
import Exceptions.FinUCABException;
import Logica.Comando;

/**
 *
 * @author Ramon
 */

/**
 * Clase para Listar los Pagos
 * @author Juan
 */
public class ComandoListarPagos extends Comando{

    private int idUSuario;
    
    public ComandoListarPagos(int idUsuario){
        this.idUSuario = idUsuario;
    }
    
    @Override
    public void ejecutar() throws FinUCABException{        
        IDAOPago dao = SingletonDAOPago.getInstance();
        this.response = dao.consultarTodos(idUSuario);
       
    }
    
}
