package com.ucab.fin.finucab.controllers;

import com.ucab.fin.finucab.domain.Cuenta_Bancaria;
import com.ucab.fin.finucab.domain.Manejador_Banco;
import com.ucab.fin.finucab.domain.Manejador_Categoria;

import java.util.ArrayList;

/**
 * Created by Junior on 14/06/2017.
 */

public class Banco_Controller {

    private static Manejador_Banco manejador;
    public static int  casoRequest = -1;

    /**
     * Colocar actual lista de categoria en el manejador
     * @param bancos
     */
    public static void setListaBancos(ArrayList<Cuenta_Bancaria> bancos){

        manejador.setUltimosBancosObtenidos(bancos);
    }

    /**
     * Colocar actual lista de categoria en el manejador
     * @return Lista de categoria cargada
     */
    public static ArrayList<Cuenta_Bancaria> getListaBancos(){

        return manejador.getUltimosBancosObtenidos();
    }

    /**
     * Resetea el caso del request al WebService
     */
    public static void resetCasoRequest(){
        casoRequest = -1;
    }

    /**
     * Obtener caso del request que se esta realizando
     * @return
     */
    public static int getCasoRequest(){
        return casoRequest;
    }

    /**
     * Metodo encargado de llamar a obtener las bancos
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     */
    public static void obtenerTodasCategorias(boolean showStatus){

        casoRequest = 0;
        manejador.obtenerTodosBancos(showStatus);

    }


}
