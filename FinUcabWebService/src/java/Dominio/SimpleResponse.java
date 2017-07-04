/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Ramon
 */
public class SimpleResponse extends Entidad{
    
    private int _status;
    private String _descripcion;
    
    public SimpleResponse (){
    }
    
    protected void setStatus(int status){
        this._status = status;
    }
    
    public SimpleResponse (int id){
        super(id);
    }
    
    public SimpleResponse (int id, int status){
        super(id);
        this._status = status;
    }
    
    public SimpleResponse (String descripcion){
        this._descripcion = descripcion;
    }
    
    public SimpleResponse (int id, int status, String descripcion){
        super(id);
        this._status = status;
        this._descripcion = descripcion;
    }

    public int getStatus() {
        return _status;
    }

    public String getDescripcion() {
        return _descripcion;
    }
    
    
}
