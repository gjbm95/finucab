package com.ucab.fin.finucab.exceptions;

import android.widget.EditText;

/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 * Esta clase es una excepcion que se dispara al no encontrar un nombre de usuario suministrado por
 * el usuario al iniciar sesion.
 *
 **/

public class UsuarioInvalido_Exception extends Exception {


    EditText campo;

    public UsuarioInvalido_Exception(String msg) {
        super(msg);
    }

    public void setCampo(EditText campo){
        this.campo = campo;
    }

    public EditText getCampo(){
        return campo;
    }



}
