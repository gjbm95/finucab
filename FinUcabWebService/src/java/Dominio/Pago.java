package Dominio;

import java.io.Serializable;

/**
 * Created by Jeffrey on 10/05/2017.
 */


/**
 * Creacion de la clase Pago
 * @author Juan
 */
public class Pago  extends Entidad implements Serializable{

    private int categoria;
    private String descripcion;
    private float total;
    private String tipo;
    private String nombreCategoria;

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    
    /**
     * Constructor de pago 
     * @param categoria
     * @param descripcion
     * @param total
     * @param tipo 
     */
    public Pago( int categoria, String descripcion, float total, String tipo) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
        this.tipo = tipo;
        
    }
    
    
    /**
     * Constructor de Pago 
     * @param id
     * @param categoria
     * @param descripcion
     * @param total
     * @param tipo 
     */
    public Pago( int id, int categoria, String descripcion, float total, String tipo) {
        super(id);
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
        this.tipo = tipo;
        
    }
    
    
    /**
     * Constructor de Pago
     * @param categoria
     * @param descripcion
     * @param total
     * @param tipo
     * @param nombreCategoria 
     */
    public Pago( int categoria, String descripcion, float total, String tipo, String nombreCategoria) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
        this.tipo = tipo;
        this.nombreCategoria = nombreCategoria;
        
    }
    
    
    /**
     * Constructor de Pago
     * @param id
     * @param categoria
     * @param descripcion
     * @param total
     * @param tipo
     * @param nombreCategoria 
     */
    public Pago( int id, int categoria, String descripcion, float total, String tipo, String nombreCategoria) {
        super(id);
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.total = total;
        this.tipo = tipo;
        this.nombreCategoria = nombreCategoria;
        
    }

    public Pago(){

    }
}
