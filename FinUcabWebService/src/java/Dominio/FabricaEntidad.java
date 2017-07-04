/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class FabricaEntidad {

    public static Usuario obtenerUsuario(){
        return new Usuario();
    }

    public static Usuario obtenerUsuario(int idusuario, String nombre,
            String apellido, String correo,
            String usuario, String contrasena, String pregunta, String respuesta,
       ArrayList<Cuenta_Bancaria> cuentas, ArrayList<Planificacion> planes){

       return new Usuario (idusuario,nombre,apellido,correo,usuario,contrasena, 
       pregunta,respuesta,cuentas,planes);   

    }

    public static Cuenta_Bancaria obtenerCuentaBancaria(String _tipoCuenta,
       String _numcuenta,String _nombreBanco, float _saldoActual, int id, int idusuario){
       return 
      new Cuenta_Bancaria(_tipoCuenta,_numcuenta,_nombreBanco,_saldoActual,id, idusuario); 
    }

    public static Cuenta_Bancaria obtenerCuentaBancaria(){
        return new Cuenta_Bancaria();
    }

    public static Tarjeta_Credito obtenerTarjetaCredito(){
        return new Tarjeta_Credito();
    }

    public static Tarjeta_Credito obtenerTarjetaCredito(String _tipotdc,
            String _fechaven, String _numero,float _saldo, int id, int idusuario){
       return 
      new Tarjeta_Credito(_tipotdc,_fechaven,_numero,_saldo,id, idusuario); 
    }

    public static Categoria obtenerCategoria(int idusuario,
            String nombre, String descripcion,boolean estaHabilitado, boolean esIngreso){
       return 
      new Categoria(nombre,descripcion,estaHabilitado,esIngreso,idusuario);
     
    }
    
        public static Categoria obtenerCategoria(int idcategoria,int idusuario,
            String nombre, String descripcion,boolean estaHabilitado, boolean esIngreso){
       return 
      new Categoria(idcategoria,nombre,descripcion,estaHabilitado,esIngreso,idusuario);
     
    }
     
    public static Pago obtenerPago(  int categoria, String descripcion, float total, String tipo){
       return 
      new Pago( categoria, descripcion, total, tipo);
    } 
    
     public static Pago obtenerPago( int categoria, String descripcion, float total, String tipo, String nombreCategoria){
       return 
      new Pago( categoria, descripcion, total, tipo, nombreCategoria);
    }
    
    public static Pago obtenerPago(  int pago, int categoria, String descripcion, float total, String tipo){
       return 
      new Pago(  pago ,categoria, descripcion, total, tipo);
    }  
    
    public static Pago obtenerPago(  int pago, int categoria, String descripcion, float total, String tipo, String nombreCategoria){
       return 
      new Pago(  pago ,categoria, descripcion, total, tipo, nombreCategoria);
    }  
    
    public static ListaEntidad obtenerListaEntidad(ArrayList<Entidad> lista){
       return new ListaEntidad(lista);
    }     
    
    public static SimpleResponse obtenerSimpleResponseStatus(int status){
       SimpleResponse sr = new SimpleResponse();
       sr.setStatus(status);
       return sr;
    } 
    
    public static SimpleResponse obtenerSimpleResponse(int id){
       return new SimpleResponse(id);
    } 
     
    public static SimpleResponse obtenerSimpleResponse(int id, int status){
       return new SimpleResponse(id, status);
    } 
     
    public static SimpleResponse obtenerSimpleResponse(int id, int status, String descripcion){
       return new SimpleResponse(id,status, descripcion);
    } 
    

    public static SimpleResponse obtenerSimpleResponse(String descripcion){
       return new SimpleResponse(descripcion);
    } 
    
    /**
     * Metodo encargado de instanciar un presupuesto con la fabrica
     * @param nombre
     * @param monto
     * @param clasificacion
     * @param duracion
     * @param usuario
     * @param categoria
     * @param tipo
     * @return 
     */
    public static Presupuesto obtenerPresupuesto(String nombre, Double monto, String clasificacion, Integer duracion, Integer usuario, String categoria, String tipo) {

        return new Presupuesto(nombre, monto, clasificacion, duracion, usuario, categoria);
    }
    
    /**
     * Metodo encargado de instanciar un presupuesto con id con la fabrica
     * @param id
     * @param nombre
     * @param monto
     * @param clasificacion
     * @param duracion
     * @param usuario
     * @param categoria
     * @param tipo
     * @return 
     */
    public static Presupuesto obtenerPresupuesto(int id, String nombre, Double monto, String clasificacion, Integer duracion, Integer usuario, String categoria, String tipo){
        return new Presupuesto(id, nombre, monto, clasificacion, duracion, usuario, categoria);
    }

}
