package Dominio;

/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase se encarga de la declaracion de los datos entrantes y salientes de la aplicacion que va
 *relacionado con los presupuestos
 **/
public class Presupuesto extends Entidad{
    private String _nombre;
    private Double _monto;
    private String _clasificacion;
    private Integer _duracion;
    private Integer _usuario;
    private String _categoria;
    private String _tipo;

    public Presupuesto(int id, String _nombre, Double _monto, String _clasificacion, Integer _duracion, Integer _usuario, String _categoria) {
        super(id);
        this._nombre = _nombre;
        this._monto = _monto;
        this._clasificacion = _clasificacion;
        this._duracion = _duracion;
        this._usuario = _usuario;
        this._categoria = _categoria;
    }

    public Presupuesto(String _nombre, Double _monto, String _clasificacion, Integer _duracion, Integer _usuario, String _categoria) {
        this._nombre = _nombre;
        this._monto = _monto;
        this._clasificacion = _clasificacion;
        this._duracion = _duracion;
        this._usuario = _usuario;
        this._categoria = _categoria;
    }

    public Presupuesto(String _nombre, Double _monto, String _clasificacion, Integer _duracion, Integer _usuario, String _categoria, String _tipo) {
        this._nombre = _nombre;
        this._monto = _monto;
        this._clasificacion = _clasificacion;
        this._duracion = _duracion;
        this._usuario = _usuario;
        this._categoria = _categoria;
        this._tipo = _tipo;
    }

    public Presupuesto(String _nombre, Double _monto, String _clasificacion, Integer _duracion, Integer _usuario, String _categoria, String _tipo, int id) {
        super(id);
        this._nombre = _nombre;
        this._monto = _monto;
        this._clasificacion = _clasificacion;
        this._duracion = _duracion;
        this._usuario = _usuario;
        this._categoria = _categoria;
        this._tipo = _tipo;
    }

    public Presupuesto(int id, String _nombre, Double _monto, String _clasificacion, Integer _duracion, String _categoria, String _tipo) {
        super(id);
        this._nombre = _nombre;
        this._monto = _monto;
        this._clasificacion = _clasificacion;
        this._duracion = _duracion;
        this._categoria = _categoria;
        this._tipo = _tipo;
    }
 
    public Presupuesto() {
    }

    public String getNombre() {
        return _nombre;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public Double getMonto() {
        return _monto;
    }

    public void setMonto(Double _monto) {
        this._monto = _monto;
    }

    public String getClasificacion() {
        return _clasificacion;
    }

    public void setClasificacion(String _clasificacion) {
        this._clasificacion = _clasificacion;
    }

    public Integer getDuracion() {
        return _duracion;
    }

    public void setDuracion(Integer _duracion) {
        this._duracion = _duracion;
    }

    public Integer getUsuario() {
        return _usuario;
    }

    public void setUsuario(Integer _usuario) {
        this._usuario = _usuario;
    }

    public String getCategoria() {
        return _categoria;
    }

    public void setCategoria(String _categoria) {
        this._categoria = _categoria;
    }

    public String getTipo() {
        return _tipo;
    }

    public void setTipo(String _tipo) {
        this._tipo = _tipo;
    }

}
