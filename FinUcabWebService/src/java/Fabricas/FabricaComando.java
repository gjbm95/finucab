/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fabricas;
import Comandos.*;

/**
 *
 * @author Junior
 */
public class FabricaComando {
        private static FabricaComando instancia = null;
    private FabricaComando(){
    }
    
    //Aplicando Singleton:
    public static FabricaComando obtenerInstancia(){
       if (instancia==null)
       {
         instancia = new FabricaComando();
       }
      return instancia; 
    }
    
    public static ComandoModulo1 crearComandoModulo1(){
     return new ComandoModulo1();
    }
    public static ComandoModulo2 crearComandoModulo2(){
     return new ComandoModulo2();
    }
    public static ComandoModulo3 crearComandoModulo3(){
     return new ComandoModulo3();
    }
    public static ComandoModulo4 crearComandoModulo4(){
     return new ComandoModulo4();
    }
    public static ComandoModulo5 crearComandoModulo5(){
     return new ComandoModulo5();
    }
    public static ComandoModulo6 crearComandoModulo6(){
     return new ComandoModulo6();
    }
}
