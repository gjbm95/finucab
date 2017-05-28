package com.ucab.fin.finucab.domain;

/**
 * Created by William on 28/5/2017.
 */

public class CategoriaSpinner {

    private int id;
    private String nombre;

    public CategoriaSpinner(int id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}
