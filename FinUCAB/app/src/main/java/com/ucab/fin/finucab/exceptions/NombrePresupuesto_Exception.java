package com.ucab.fin.finucab.exceptions;

import android.widget.EditText;

/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase es la excepcion que valida si el nombre de un presupuesto esta repetido
 **/

public class NombrePresupuesto_Exception extends Exception {

    EditText campo;
    public NombrePresupuesto_Exception(String msg) {super(msg);}

    public void setCampo(EditText campo){
        this.campo = campo;
    }

    public EditText getCampo(){
        return campo;
    }


}
