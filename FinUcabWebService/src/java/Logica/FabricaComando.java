/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Dominio.Entidad;
import Logica.Modulo1.*;
import Logica.Modulo2.ComandoActualizarDatosUsuario;
import Logica.Modulo4.ComandoAgregarCategoria;
import Logica.Modulo4.ComandoVisualizarCategoria;
import Dominio.Cuenta_Bancaria;
import Dominio.Tarjeta_Credito;
import Dominio.Usuario;
import Logica.Modulo2.*;
import Logica.Modulo4.ComandoConsultarCategoria;
import Logica.Modulo4.ComandoEliminarCategoria;
import Logica.Modulo4.ComandoModificarCategoria;
import Logica.Modulo5.ComandoAgregarPago;
import Logica.Modulo5.ComandoConsultarPago;
import Logica.Modulo5.ComandoListarPagos;
import Logica.Modulo5.ComandoModificarPago;
import Logica.Modulo3.*;



/**
 *
 * @author Oswaldo
 */
public class FabricaComando {
    
    public static ComandoIniciarSesion 
        instanciarComandoIniciarSesion(Entidad usuario){
        return new ComandoIniciarSesion(usuario);
    }
    
     public static ComandoVerificarUsuario 
        instanciarComandoVerificarUsuario(String usuario){
        return new ComandoVerificarUsuario(usuario);
    }
     
     public static ComandoRecuperarClave 
        instanciarComandoRecuperarClave(String usuario){
        return new ComandoRecuperarClave(usuario);
    }
     
     public static ComandoActualizarClave 
        instanciarComandoActualizarClave(Entidad usuario){
        return new ComandoActualizarClave(usuario);
    }
     
     public static ComandoRegistrarUsuario 
        instanciarComandoRegistrarUsuario(Entidad usuario){
        return new ComandoRegistrarUsuario(usuario);
    }
    
     public static ComandoActualizarDatosUsuario 
        instanciarComandoActualizarDatosUsuario(Usuario usuario){
        return new ComandoActualizarDatosUsuario(usuario);
    }
     
     public static ComandoAgregarCategoria 
        instanciarComandoAgregarCategoria(Entidad categoria){
        return new ComandoAgregarCategoria(categoria);
    }
     public static ComandoEliminarCategoria 
        instanciarComandoEliminarCategoria(int categoria){
        return new ComandoEliminarCategoria(categoria);
    }
     
     public static ComandoModificarCategoria 
        instanciarComandoModificarCategoria(Entidad categoria){
        return new ComandoModificarCategoria(categoria);
    }
     
     public static ComandoVisualizarCategoria 
        instanciarComandoVisualizarCategoria(int usuario){
         return new ComandoVisualizarCategoria(usuario);
     }
     
      public static ComandoConsultarCategoria 
        instanciarComandoConsultarCategoria(int idCategoria){
        return new ComandoConsultarCategoria(idCategoria);
    }
    
     public static ComandoAgregarCuenta 
        instanciarComandoAgregarCuenta(Cuenta_Bancaria cuenta){
        return new ComandoAgregarCuenta(cuenta);
    }
    
     public static ComandoActualizarCuenta 
        instanciarComandoActualizarCuenta(Cuenta_Bancaria cuenta){
        return new ComandoActualizarCuenta(cuenta);
    }
    
     public static ComandoEliminarCuenta 
        instanciarComandoEliminarCuenta(int id){
        return new ComandoEliminarCuenta(id);
    }
    
     /*---------------------------     PAGOS      ------------------------------------*/
     /**
      * Fabrica inicializadora del ComandoListarPagos
      * @param idUsuario Identificador de usuario en base de datos
      * @return 
      */
     public static ComandoListarPagos 
        instanciarComandoListarPagos(int idUsuario){
        return new ComandoListarPagos(idUsuario);
    }
    
     /**
      * Fabrica inicializadora del ComandoAgregarPago
      * @param pago 
      * @return 
      */
     public static ComandoAgregarPago 
        instanciarComandoAgregarPago(Entidad pago){
        return new ComandoAgregarPago(pago);
     }
     
     /**
      * Fabrica inicializadora del ComandoAgregarPago
      * @param pago 
      * @return 
      */
     public static ComandoModificarPago 
        instanciarComandoModificarPago(Entidad pago){
        return new ComandoModificarPago(pago);
     }
     
     /**
      * Fabrica inicializadora del ComandoConsultarPago
      * @param idPago Identificador de pago en base de datos
      * @return 
      */
      public static ComandoConsultarPago
         instanciarComandoConsultarPago(int idPago){
        return new ComandoConsultarPago(idPago);
    }
     
     /*---------------------------   Gestion de Cuentas y Tarjetas      ------------------------------------*/

     public static ComandoAgregarTDC 
        instanciarComandoAgregarTDC(Tarjeta_Credito tdc){
        return new ComandoAgregarTDC(tdc);
    }
    
     public static ComandoActualizarTDC 
        instanciarComandoActualizarTDC(Tarjeta_Credito tdc){
        return new ComandoActualizarTDC(tdc);
    }
    
     public static ComandoEliminarTDC 
        instanciarComandoEliminarTDC(int id){
        return new ComandoEliminarTDC(id);
    }
    
     public static ComandoConsultarTDC 
        instanciarComandoConsultarTDC(int id){
        return new ComandoConsultarTDC(id);
    }
    
     public static ComandoConsultarCuentas 
        instanciarComandoConsultarCuentas(int id){
        return new ComandoConsultarCuentas(id);
    }
    
     public static ComandoConsultarEstadisticasHome 
        instanciarComandoConsultarEstadisticas(int id){
        return new ComandoConsultarEstadisticasHome(id);
    }

     /**
      * Metodo encargado de instanciar el comando para agregar un presupuesto
      * @param e
      * @return la instancia del comando 
      */
    public static ComandoAgregarPresupuesto 
        instanciarComandoAgregarPresupuesto(Entidad e) {
        return new ComandoAgregarPresupuesto(e);
    }

        /**
         * Metodo encargado de instanciar el comando para modificar un presupuesto
         * @param e
         * @return la instancia del comando 
         */
    public static ComandoModificarPresupuesto 
        instanciarComandoModificarPresupuesto(Entidad e) {
        return new ComandoModificarPresupuesto(e);
    }
     
      /**
      * Metodo encargado de instanciar el comando para listar los presupuestos de
      * un usuario
      * @param idUsuario
      * @return la instancia del comando
      */
    public static ComandoListarPresupuestos
            instanciarComandoListarPresupuestos(int idUsuario) {
        return new ComandoListarPresupuestos(idUsuario);
    }

        /**
         * Metodo encargado de instanciar el comando para obtener un presupuesto
         * @param idPresupuesto
         * @return la instancia del comando 
         */
    public static ComandoObtenerPresupuesto 
        instanciarComandoObtenerPresupuesto(int idPresupuesto) {
        return new ComandoObtenerPresupuesto(idPresupuesto);
    }
    
        /**
         * Metodo encargado de instanciar el comando para eliminar un presupuesto
         * @param idPresupuesto
         * @return la instancia del comando 
         */
    public static ComandoEliminarPresupuesto
        instanciarComandoEliminarPresupuesto(int idPresupuesto){
        return new ComandoEliminarPresupuesto(idPresupuesto);
    }
    
        /**
         * Metodo encargado de instanciar el comando para verificar un nombre
         * @param nombrePresupuesto
         * @return la instancia del comando 
         */
    public static ComandoVerificarNombre 
        instanciarComandoVerificarNombre(String nombrePresupuesto){
        return new ComandoVerificarNombre(nombrePresupuesto);
    }
     
        /**
         * Metodo encargado de instanciar el comando para exportar presupuestos
         * @param idUsuario
         * @return la instancia del comando
         */
    public static ComandoExportarPresupuestos instanciarComandoExportarPresupuesto(int idUsuario){
        return new ComandoExportarPresupuestos(idUsuario);
    }
}
