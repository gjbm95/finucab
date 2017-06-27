package com.ucab.fin.finucab.domain;

/**
 * Created by Jeffrey on 10/05/2017.
 */

public class Pago {
    private int idPago;
    private int idCategoria;
    private String categoria;
    private String descripcion;
    private float total;
    private String tipo;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setCategoria(String categoria) {
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

    public Pago(int idPago, int idCategoria, String descripcion, float total, String tipo) {
        this.idPago = idPago;
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.total = total;
        this.tipo = tipo;
    }

    public Pago(){

    }
}
