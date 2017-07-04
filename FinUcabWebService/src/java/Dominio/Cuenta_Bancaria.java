package Dominio;

import java.util.ArrayList;

/**
 * Created by Junior on 03/05/2017.
 */

public class Cuenta_Bancaria extends Entidad {
    private String _tipoCuenta;
    private String _numcuenta;
    private String _nombreBanco;
    private float _saldoActual;
    private ArrayList<Tarjeta_Credito> _tarjetas = new ArrayList<Tarjeta_Credito>();
    private ArrayList<Pago> _transacciones = new ArrayList<Pago>();
    private int _idusuario;

    public Cuenta_Bancaria(String _tipoCuenta, String _numcuenta, 
            String _nombreBanco, float _saldoActual, int id, int idusuario) {
        super(id);
        this._tipoCuenta = _tipoCuenta;
        this._numcuenta = _numcuenta;
        this._nombreBanco = _nombreBanco;
        this._saldoActual = _saldoActual;
        this._idusuario = idusuario;
    }

    Cuenta_Bancaria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdusuario() {
        return _idusuario;
    }

    public void setIdusuario(int _idusuario) {
        this._idusuario = _idusuario;
    }
    
    public String getTipoCuenta() {
        return _tipoCuenta;
    }

    public void setTipoCuenta(String _tipoCuenta) {
        this._tipoCuenta = _tipoCuenta;
    }

    public String getNumcuenta() {
        return _numcuenta;
    }

    public void setNumcuenta(String _numcuenta) {
        this._numcuenta = _numcuenta;
    }

    public String getNombreBanco() {
        return _nombreBanco;
    }

    public void setNombreBanco(String _nombreBanco) {
        this._nombreBanco = _nombreBanco;
    }

    public float getSaldoActual() {
        return _saldoActual;
    }

    public void setSaldoActual(float _saldoActual) {
        this._saldoActual = _saldoActual;
    }

    public ArrayList<Tarjeta_Credito> getTarjetas() {
        return _tarjetas;
    }

    public void setTarjetas(ArrayList<Tarjeta_Credito> _tarjetas) {
        this._tarjetas = _tarjetas;
    }

    public ArrayList<Pago> getTransacciones() {
        return _transacciones;
    }

    public void setTransacciones(ArrayList<Pago> _transacciones) {
        this._transacciones = _transacciones;
    }
}
