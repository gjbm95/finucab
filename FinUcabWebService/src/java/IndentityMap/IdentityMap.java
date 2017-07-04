/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IndentityMap;

import Dominio.Entidad;
import Dominio.FabricaEntidad;
import Dominio.ListaEntidad;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ramon
 */
public class IdentityMap {
    
    private static IdentityMap _instancia; 
    private HashMap<String, Entidad> _cache;
    
    public static IdentityMap obtenerInstancia(){
      if (_instancia==null)
          _instancia = new IdentityMap();
      
        return _instancia; 
    }
    
    public static IdentityMap getInstance() {
        return SingletonIdentityMapHolder.INSTANCE;
    }
    
    private static class SingletonIdentityMapHolder {
        private static final IdentityMap INSTANCE = new IdentityMap();
    }
    
    private IdentityMap(){
        
        this._cache = new HashMap<>();
    }
    
    public Entidad getEntidad(String id){
        
        Entidad salida  = this._cache.get(id);
     
        return salida;
    }
    
    public void setEntidad(String id, Entidad entidad){
        
        this._cache.put(id, entidad);
     
    }
    
     public ListaEntidad getListaEntidad(String id){
        
        Entidad salida  = this._cache.get(id);
        if (salida != null ){
            return (ListaEntidad) salida;
        }else{
            
            ListaEntidad listaEntidad = FabricaEntidad.obtenerListaEntidad(new ArrayList<Entidad>());
            
            setEntidad(id, listaEntidad);
            
            return listaEntidad;
        }
    }
     
      public void setListaEntidad(String id, ListaEntidad listaEntidad){
        
        this._cache.put(id, listaEntidad);
        
    }
    
     public void addEntidadEnLista(String id, Entidad entidad){
         
        ListaEntidad listaEntidad  = getListaEntidad(id);
        listaEntidad.getLista().add(entidad);
     
    }
     
    public void rmEntidadEnLista(String id, int idEntidad){
         
        ListaEntidad listaEntidad  = getListaEntidad(id);       
        ArrayList<Entidad> lista = listaEntidad.getLista();
        
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getId() == idEntidad){
                listaEntidad.getLista().remove(i);
            }
        }
     
    }
    
    public void updateEntidadEnLista(String id, Entidad entidad ){
        
        int idEntidad = entidad.getId();
        
        rmEntidadEnLista(id, idEntidad);
        addEntidadEnLista(id, entidad);
        
     
    }
    
    public Entidad getEntidadEnLista(String id, int idEntidad){
         
        ListaEntidad listaEntidad  = getListaEntidad(id);       
        ArrayList<Entidad> lista = listaEntidad.getLista();
        
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getId() == idEntidad){
                return listaEntidad.getLista().get(i);
            }
        }
        
        return null;
    }
    
    
    
    
}
