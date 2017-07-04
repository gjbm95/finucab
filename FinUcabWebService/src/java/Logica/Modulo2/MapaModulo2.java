
package Logica.Modulo2;

import Dominio.Entidad;
import IndentityMap.IdentityMap;
import java.util.HashMap;

/**
*Modulo 2 - Modulo de Home
*Desarrolladores:
*Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
*Descripción de la clase:
*Contiene en memoria los objetos que estan siendo utilizados durante la 
* ejecucion.
**/
public class MapaModulo2 {
    private static MapaModulo2 _instancia; 
    private HashMap<String, Entidad> _cache= new HashMap<>();
    
    //Aplicando Singleton
    public static MapaModulo2 obtenerInstancia(){
      if (_instancia==null)
          _instancia = new MapaModulo2(); 
        return _instancia; 
    }
       
    public void setEntidad(String id, Entidad entidad){  
        this._cache.put(id, entidad);
    }
    
    public Entidad getEntidad(String id){      
        Entidad salida  = this._cache.get(id);
        return salida;
    }
    
    public void eliminarEntidad(String id){
       this._cache.remove(id);
       
    } 
    
    public void actualizarEntidad(String id, Entidad entidad){
       this._cache.remove(id);
       this._cache.put(id, entidad);
    }
}
