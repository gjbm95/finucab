package com.ucab.fin.finucab.webservice;

import com.ucab.fin.finucab.domain.Usuario;

/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase se encarga de almacenar los datos del usuario, una vez haya iniciado sesion en la
 * aplicacion.
 **/
public class ControlDatos {

    private static Usuario usuario;

    public ControlDatos(){

    }

    public ControlDatos(Usuario usuario){
        this.usuario = usuario;
    }


    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        ControlDatos.usuario = usuario;
    }
}
