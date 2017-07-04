/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author William
 */
public abstract class Entidad {
    
    private int _id;
    
    public Entidad() {
        
    }

    public Entidad(int id) {
        this._id = id;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }
    
    
}
