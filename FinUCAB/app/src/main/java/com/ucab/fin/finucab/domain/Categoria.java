package com.ucab.fin.finucab.domain;


import java.io.Serializable;

/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase es en la que creamos la clase Categoria con sus atributos
 * haciendo los metodos de get y set correspondientes
 */

public class Categoria implements Serializable{
    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
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

    public boolean isEstaHabilitado() {
        return estaHabilitado;
    }

    public void setEstaHabilitado(boolean estaHabilitado) {
        this.estaHabilitado = estaHabilitado;
    }

    public boolean isIngreso() {
        return esIngreso;
    }

    public void setEsIngreso(boolean esIngreso) {
        this.esIngreso = esIngreso;
    }

    private int idcategoria;
    private String nombre;
    private String descripcion;
    private boolean estaHabilitado;
    private boolean esIngreso;

    public Categoria() {
        this.idcategoria = 0;
        this.nombre = "";
        this.descripcion = "";
        this.estaHabilitado = false;
        this.esIngreso = false;
    }


    public Categoria(int idcategoria, String nombre, String descripcion, boolean estaHabilitado, boolean esIngreso) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estaHabilitado = estaHabilitado;
        this.esIngreso = esIngreso;
    }

    public Categoria( String nombre, String descripcion, boolean estaHabilitado, boolean esIngreso) {
        this.idcategoria = -1;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estaHabilitado = estaHabilitado;
        this.esIngreso = esIngreso;
    }


}
