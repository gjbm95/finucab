package com.ucab.fin.finucab.domain;

import java.util.ArrayList;

/**
 * Created by Junior on 03/05/2017.
 */

public class Cuenta_Bancaria {
    private int idCuenta;
    private String tipoCuenta;
    private String numcuenta;
    private String nombreBanco;
    private float saldoActual;
    private ArrayList<Tarjeta_Credito> tarjetas = new ArrayList<Tarjeta_Credito>();
    private ArrayList<Pago> transacciones = new ArrayList<Pago>();

    public Cuenta_Bancaria(int idCuenta, String nombreBanco, String numcuenta,
                           float saldoActual,String tipoCuenta) {
        this.saldoActual = saldoActual;
        this.nombreBanco = nombreBanco;
        this.numcuenta = numcuenta;
        this.idCuenta = idCuenta;
        this.tipoCuenta = tipoCuenta;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public float getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(float saldoActual) {
        this.saldoActual = saldoActual;
    }

    public ArrayList<Tarjeta_Credito> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<Tarjeta_Credito> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public ArrayList<Pago> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Pago> transacciones) {
        this.transacciones = transacciones;
    }
}
