package com.ucab.fin.finucab.domain;



public class Presupuesto {
    String _nombre;
    String _categoria;
    Float _monto;
    String _clasificacion;
    int _duracion;
    String _tipo;

    public Presupuesto(String _nombre, String _categoria, Float _monto, String _clasificacion, int _duracion, String _tipo) {
        this._nombre = _nombre;
        this._categoria = _categoria;
        this._monto = _monto;
        this._clasificacion = _clasificacion;
        this._duracion = _duracion;
        this._tipo = _tipo;
    }

    public Presupuesto() {
    }

    public String get_nombre(){
        return _nombre;
    }
    public String get_categoria() {
        return _categoria;
    }

    public Float get_monto() {
        return _monto;
    }

    public String get_clasificacion() {
        return _clasificacion;
    }

    public int get_duracion() {
        return _duracion;
    }

    public String get_tipo() {
        return _tipo;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void set_categoria(String _categoria) {
        this._categoria = _categoria;
    }

    public void set_monto(Float _monto) {
        this._monto = _monto;
    }

    public void set_clasificacion(String _clasificacion) {
        this._clasificacion = _clasificacion;
    }

    public void set_duracion(int _duracion) {
        this._duracion = _duracion;
    }

    public void set_tipo(String _tipo) {
        this._tipo = _tipo;
    }
}
