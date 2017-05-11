package com.ucab.fin.finucab.domain;

import java.util.ArrayList;

/**
 * Created by Junior on 01/05/2017.
 */

public class Usuario {
    private int idusuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private String contrasena;
    private String pregunta;
    private String respuesta;
    private ArrayList<Cuenta_Bancaria> cuentas;
    private ArrayList<Planificacion_Pago> planes;


    public Usuario()
    {

    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public ArrayList<Planificacion_Pago> getPlanes() {
        return planes;
    }

    public void setPlanes(ArrayList<Planificacion_Pago> planes) {
        this.planes = planes;
    }

    public ArrayList<Cuenta_Bancaria> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta_Bancaria> cuentas) {
        this.cuentas = cuentas;
    }
}
