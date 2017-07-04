/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Ramon
 */
public class ListaEntidad extends Entidad{
    
    private ArrayList<Entidad> _lista;
    
    public ListaEntidad( ArrayList<Entidad> lista){
        this._lista = lista;
    }
    
    public ArrayList<Entidad> getLista(){        
        return _lista;
    }
    
}
