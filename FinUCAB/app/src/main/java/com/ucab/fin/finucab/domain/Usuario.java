package com.ucab.fin.finucab.domain;

import java.util.ArrayList;

/**
 * Created by Junior on 01/05/2017.
 */

public class Usuario {
    public int idusuario;
    public String nombre;
    public String apellido;
    public String correo;
    public String usuario;
    public String contrasena;
    public String pregunta;
    public String respuesta;
    public ArrayList<Cuenta_Bancaria> cuentas;
    public ArrayList<Planificacion_Pago> planes;


    public Usuario()
    {

    }




}
