package Dominio;

/**
 * Created by Junior on 03/05/2017.
 */

public class Tarjeta_Credito extends Entidad {
    private String _tipotdc;
    private String _fechaven;
    private String _numero;
    private float _saldo;
    private int _idusuario;

    public Tarjeta_Credito(String _tipotdc, String _fechaven, String _numero,
            float _saldo, int id, int idusuario) {
        super(id);
        this._tipotdc = _tipotdc;
        this._fechaven = _fechaven;
        this._numero = _numero;
        this._saldo = _saldo;
        this._idusuario = idusuario;
    }

    public Tarjeta_Credito() {
        
    }

    public int getIdusuario() {
        return _idusuario;
    }

    public void setIdusuario(int _idusuario) {
        this._idusuario = _idusuario;
    }
    
    public String getTipotdc() {
        return _tipotdc;
    }

    public void setTipotdc(String _tipotdc) {
        this._tipotdc = _tipotdc;
    }

    public String getFechaven() {
        return _fechaven;
    }

    public void setFechaven(String _fechaven) {
        this._fechaven = _fechaven;
    }

    public String getNumero() {
        return _numero;
    }

    public void setNumero(String _numero) {
        this._numero = _numero;
    }

    public float getSaldo() {
        return _saldo;
    }

    public void setSaldo(float _saldo) {
        this._saldo = _saldo;
    }


}
