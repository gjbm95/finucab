/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fabricas;

import BaseDatosDAO.*;

/**
 *
 * @author Junior
 */
public class FabricaDAO {
    
    private static FabricaDAO instancia = null;
    private FabricaDAO(){
    }
    
    //Aplicando Singleton:
    public static FabricaDAO obtenerInstancia(){
       if (instancia==null)
       {
         instancia = new FabricaDAO();
       }
      return instancia; 
    }
    
    
    public static Modulo1DAO crearDaoModulo1(){
     return new Modulo1DAO();
    }
    
    public static Modulo2DAO crearDaoModulo2(){
     return new Modulo2DAO();
    
    }
    public static Modulo3DAO crearDaoModulo3(){
     return new Modulo3DAO();
    
    }
    public static Modulo4DAO crearDaoModulo4(){
     return new Modulo4DAO();
    
    }
    public static Modulo5DAO crearDaoModulo5(){
     return new Modulo5DAO();
    
    }
    public static Modulo6DAO crearDaoModulo6(){
     return new Modulo6DAO();
    
    }
    
}