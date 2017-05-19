package com.ucab.fin.finucab.domain;

/**
 * Created by Jeffrey on 10/05/2017.
 */

public class Pago {
    private int idPago;
    private String categoria;
    private String descripcion;
    private String fecha;
    private float subtotal;
    private float impuesto;
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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
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

    public Pago(int idPago, String categoria, String descripcion, String fecha, float subtotal, float impuesto, float total, String tipo) {
        this.idPago = idPago;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
        this.tipo = tipo;
    }

    public Pago(){

    }
}