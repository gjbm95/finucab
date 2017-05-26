package com.ucab.fin.finucab.exceptions;

import android.widget.EditText;
/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase es una excepcion que se dispara al colocar una direccion de correo no valida.
 *
 **/
public class CorreoInvalido_Exception extends Exception {

    EditText campo;


    public CorreoInvalido_Exception(String msg) {
        super(msg);
    }

    public void setCampo(EditText campo){
        this.campo = campo;
    }

    public EditText getCampo(){
        return campo;
    }


}