package com.ucab.fin.finucab.domain;

/**
 * Created by ErbinRodriguez on 27/6/17.
 */

public class Top {
    private String fecha;
    private String descripcion;

    public Top(String fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
