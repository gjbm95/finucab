package com.ucab.fin.finucab.domain;

/**
 * Created by Junior on 03/05/2017.
 */

public class Tarjeta_Credito {
    private int idTDC;
    private String tipotdc;
    private String fechaven;
    private String numero;
    private float saldo;

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFechaven() {
        return fechaven;
    }

    public void setFechaven(String fechaven) {
        this.fechaven = fechaven;
    }

    public String getTipotdc() {
        return tipotdc;
    }

    public void setTipotdc(String tipotdc) {
        this.tipotdc = tipotdc;
    }

    public int getIdTDC() {
        return idTDC;
    }

    public void setIdTDC(int idTDC) {
        this.idTDC = idTDC;
    }
}
