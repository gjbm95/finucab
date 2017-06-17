package com.ucab.fin.finucab.domain;

import android.app.Activity;

import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Junior on 14/06/2017.
 */

public class Manejador_Banco {

    private Activity actividad;
    private ArrayList<Cuenta_Bancaria> ultimosBancosObtenidos; //creacion de un array de tipo Banco
    private ResponseWebServiceInterface intefaz; //creacion de una interfaz para funcionalidades de vistas
   
        /*------------------------------------- CONSTRUCTORES ----------------------------------------*/

    public Manejador_Banco(Activity actividad, ResponseWebServiceInterface intefaz){

        this.actividad = actividad;
        this.intefaz = intefaz;
    }

    public Manejador_Banco(Activity actividad){

        this.actividad = actividad;
        this.intefaz = null;
    }




    public ResponseWebServiceInterface getIntefaz() {
        return intefaz;
    }

    public ArrayList<Cuenta_Bancaria> getUltimosBancosObtenidos() {
        return ultimosBancosObtenidos;
    } // Obtener ultima lista recuperada
    public void setUltimosBancosObtenidos(ArrayList<Cuenta_Bancaria> bancos) {
        this.ultimosBancosObtenidos = bancos;
    } // Asignar ultima lista recuperada


    /**Creacion del metodo eliminar Categoria
     * conexion con WebService por medio de Json
     *
     * @param id Id de la categoria a borrar
     */
    public void borrarBanco( int id) {

        Parametros.reset();
        Parametros.setMetodo("Modulo2/eliminarBanco?datosBanco="+ String.valueOf(id));
        new Recepcion(actividad,intefaz).execute("GET");

    }

   /*------------------------------------- REQUEST ----------------------------------------*/

    /**Creacion del metodo agregar Banco
     * conexion con WebService por medio de Json
     *
     * @param banco Banco a registrar
     */
    public void Banco(Cuenta_Bancaria banco) {

        try {

            int idUsuario = ControlDatos.getUsuario().getIdusuario();
            JSONObject nuevo_banco = new JSONObject();
            nuevo_banco.put("NombreBanco",banco.getNombreBanco());
            nuevo_banco.put("NumCuenta",banco.getNumcuenta());
            nuevo_banco.put("saldoActual",banco.getSaldoActual());
            nuevo_banco.put("tipoCuenta",banco.getTipoCuenta());

            Parametros.reset();
            Parametros.setMetodo("Modulo2/registrarBanco?datosBanco="+ URLEncoder.encode(nuevo_banco.toString()));
            new Recepcion(actividad,intefaz).execute("GET");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }





    /**
     * Creacion del metodo modificar Banco
     * conexion con WebService por medio de Json
     *
     * @param banco Cuenta Bancaria a modificar
     */

    public void modificarBanco(Cuenta_Bancaria banco) {
        try {

            int idUsuario = ControlDatos.getUsuario().getIdusuario();
            JSONObject nuevo_banco = new JSONObject();
            nuevo_banco.put("NombreBanco",banco.getNombreBanco());
            nuevo_banco.put("NumCuenta",banco.getNumcuenta());
            nuevo_banco.put("saldoActual",banco.getSaldoActual());
            nuevo_banco.put("tipoCuenta",banco.getTipoCuenta());

            Parametros.reset();
            Parametros.setMetodo("Modulo4/modificarCategoria?datosCategoria="+ URLEncoder.encode(nuevo_banco.toString()));
            new Recepcion(actividad,intefaz).execute("GET");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**Creacion del metodo Mostrar lista de Bancos
     * conexion con WebService por medio de Json
     *
     * @param showStatus Mostrar o no el dialog de Cargando
     *
     */
    public void obtenerTodosBancos(boolean showStatus) {

        int idUsuario = ControlDatos.getUsuario().getIdusuario();;
        Parametros.reset();
        Parametros.setMetodo("Modulo2/visualizarBancos?datosBanco="+ String.valueOf(idUsuario) );
        new Recepcion(actividad,intefaz,showStatus).execute("GET");

    }



}
