package com.ucab.fin.finucab.domain;

/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase se encarga de la declaracion de los datos entrantes y salientes de la aplicacion que va
 *relacionado con los presupuestos
 **/

public class Presupuesto {
    private String _nombre;
    private Double _monto;
    private String _clasificacion;
    private Integer _duracion;
    private Integer _usuario;
    private Integer _categoria;
    private String _tipo;

    public Presupuesto(String _nombre, Double _monto, String _clasificacion, Integer _duracion, Integer _usuario, Integer _categoria, String _tipo) {
        this._nombre = _nombre;
        this._monto = _monto;
        this._clasificacion = _clasificacion;
        this._duracion = _duracion;
        this._usuario = _usuario;
        this._categoria = _categoria;
        this._tipo = _tipo;
    }

    public Presupuesto() {
    }

    public String get_nombre(){
        return _nombre;
    }
    public Integer get_categoria() {
        return _categoria;
    }

    public Double get_monto() {
        return _monto;
    }

    public String get_clasificacion() {
        return _clasificacion;
    }

    public Integer get_duracion() {
        return _duracion;
    }

    public String get_tipo() {
        return _tipo;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void set_categoria(Integer _categoria) {
        this._categoria = _categoria;
    }

    public void set_monto(Double _monto) {
        this._monto = _monto;
    }

    public void set_clasificacion(String _clasificacion) {
        this._clasificacion = _clasificacion;
    }

    public void set_duracion(Integer _duracion) {
        this._duracion = _duracion;
    }

    public void set_tipo(String _tipo) {
        this._tipo = _tipo;
    }

    public Integer get_usuario() {
        return _usuario;
    }

    public void set_usuario(Integer _usuario) {
        this._usuario = _usuario;
    }
}
