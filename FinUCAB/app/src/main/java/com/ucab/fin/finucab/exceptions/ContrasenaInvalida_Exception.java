package com.ucab.fin.finucab.exceptions;

import android.widget.EditText;

/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase es una excepcion que se dispara al haber una contraseña que no se asocia con el usuario al
 * iniciar sesion.
 *
 **/
public class ContrasenaInvalida_Exception extends Exception {


    EditText campo;

    public ContrasenaInvalida_Exception(String msg) {
        super(msg);
    }

    public void setCampo(EditText campo){
        this.campo = campo;
    }

    public EditText getCampo(){
        return campo;
    }



}
