package com.ucab.fin.finucab.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by William on 25/5/2017.
 */

public class Planificacion implements Serializable{

    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private String nombre;
    private String descripcion;
    private Double monto;
    private int idCategoria;
    private Boolean recurrente;
    private String recurrencia;
    private Boolean activo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Boolean getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(Boolean recurrente) {
        this.recurrente = recurrente;
    }

    public String getRecurrencia() {
        return recurrencia;
    }

    public void setRecurrencia(String recurrencia) {
        this.recurrencia = recurrencia;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Planificacion(int id, Date fechaInicio, Date fechaFin, String nombre, String descripcion, Double monto, int idCategoria, Boolean recurrente, String recurrencia, Boolean activo) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.monto = monto;
        this.idCategoria = idCategoria;
        this.recurrente = recurrente;
        this.recurrencia = recurrencia;
        this.activo = activo;
    }

    public Planificacion(Date fechaInicio, Date fechaFin, String descripcion, Double monto, Boolean recurrente, String recurrencia, Boolean activo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.monto = monto;
        this.recurrente = recurrente;
        this.recurrencia = recurrencia;
        this.activo = activo;
    }

    public Planificacion(Date fechaInicio, Date fechaFin, String descripcion, Double monto, int idCategoria, Boolean recurrente, String recurrencia, Boolean activo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
        this.monto = monto;
        this.idCategoria = idCategoria;
        this.recurrente = recurrente;
        this.recurrencia = recurrencia;
        this.activo = activo;
    }

    public Planificacion(Date fechaInicio, Date fechaFin, String nombre, String descripcion, Double monto, int idCategoria, Boolean recurrente, String recurrencia, Boolean activo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.monto = monto;
        this.idCategoria = idCategoria;
        this.recurrente = recurrente;
        this.recurrencia = recurrencia;
        this.activo = activo;
    }

    public Planificacion() {
        // Constructor vacio
        this.fechaInicio = new Date();
        this.fechaFin = new Date();
        this.nombre = "";
        this.descripcion = "";
        this.monto = 0.0;
        this.idCategoria = -1;
        this.recurrente = false;
        this.recurrencia = "";
        this.activo = true;
    }
}
