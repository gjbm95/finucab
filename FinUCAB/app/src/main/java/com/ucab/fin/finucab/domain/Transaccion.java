package com.ucab.fin.finucab.domain;

import java.util.Date;

/**
 * Created by Jeffrey on 10/05/2017.
 */

public class Transaccion {
    private int idTransaccion;
    private String categoria;
    private String descripcion;
    private Date fecha;
    private float subtotal;
    private float impuesto;
    private float total;
    private String tipo;

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public Transaccion(int idTransaccion, String categoria, String descripcion, Date fecha, float subtotal, float impuesto, float total, String tipo) {
        this.idTransaccion = idTransaccion;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
        this.tipo = tipo;
    }

    public Transaccion(){

    }
}
