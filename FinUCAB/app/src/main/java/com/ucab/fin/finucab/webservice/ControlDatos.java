package com.ucab.fin.finucab.webservice;

import com.ucab.fin.finucab.domain.Usuario;

/**
 * Created by Junior on 22/05/2017.
 */

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
