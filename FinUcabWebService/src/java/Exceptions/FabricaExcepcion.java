/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import Exceptions.Presupuesto.AgregarPresupuestoException;
import Exceptions.Presupuesto.ConsultarPresupuestoException;
import Exceptions.Presupuesto.EliminarPresupuestoException;
import Exceptions.Presupuesto.ExportarPresupuestoException;
import Exceptions.Presupuesto.ListarPresupuestoException;
import Exceptions.Presupuesto.ModificarPresupuestoException;
import Exceptions.Presupuesto.VerificarNombreException;
import Logica.Modulo1.ActualizarClaveException;
import Logica.Modulo1.IniciarSesionException;
import Logica.Modulo1.RecuperarClaveException;
import Logica.Modulo1.RegistrarIncorrectoException;
import Logica.Modulo1.VerificarUsuarioException;
import Logica.Modulo2.AgregarFallidoException;
import Logica.Modulo2.ConsultarFallidoException;
import Logica.Modulo2.ConversionFallidaException;
import Logica.Modulo2.EliminarFallidoException;
import Logica.Modulo2.ModificarFallidoException;
import Logica.Modulo5.AgregarPagoException;
import Logica.Modulo5.ConsultarPagoException;
import Logica.Modulo5.ListarPagosException;
import Logica.Modulo5.ModificarPagoException;

/**
 *
 * @author Ramon
 */
public class FabricaExcepcion {
    
    public static DataReaderException instanciarDataReaderException(int code) {
        return new DataReaderException(code);
    }
    
    public static AgregarPagoException instanciarAgregarPagoException(int code) {
        return new AgregarPagoException(code);
    }
    
    public static ModificarPagoException instanciarModificarPagoException(int code) {
        return new ModificarPagoException(code);
    }
    
    public static ConsultarPagoException instanciarConsultarPagoException(int code) {
        return new ConsultarPagoException(code);
    }
    
    public static ListarPagosException instanciarListarPagosException(int code) {
        return new ListarPagosException(code);
    }
    
    public static DataReaderException instanciarDataReaderException(int code, String message) {
        return new DataReaderException(code);
    }
    
    public static AgregarPagoException instanciarAgregarPagoException(int code, String message) {
        return new AgregarPagoException(code, message);
    }
    
    public static ModificarPagoException instanciarModificarPagoException(int code, String message) {
        return new ModificarPagoException(code, message);
    }
    
    public static ConsultarPagoException instanciarConsultarPagoException(int code, String message) {
        return new ConsultarPagoException(code, message);
    }
    
    public static ListarPagosException instanciarListarPagosException(int code, String message) {
        return new ListarPagosException(code, message);
    }
    
    public static AgregarPresupuestoException instanciarAgregarPresupuestoException (int code){
        return new AgregarPresupuestoException(code);
    }
    
    public static AgregarPresupuestoException instanciarAgregarPresupuestoException (int code, String message){
        return new AgregarPresupuestoException(code, message);
    }
    
    public static ConsultarPresupuestoException instanciarConsultarPresupuestoException (int code){
        return new ConsultarPresupuestoException(code);
    }
    
    public static ConsultarPresupuestoException instanciarConsultarPresupuestoException (int code, String message){
        return new ConsultarPresupuestoException(code, message);
    }
    
    public static ListarPresupuestoException instanciarListarPresupuestoException (int code){
        return new ListarPresupuestoException(code);
    }
    
    public static ListarPresupuestoException instanciarListarPresupuestoException (int code, String message){
        return new ListarPresupuestoException(code, message);
    }
    
    public static ModificarPresupuestoException instanciarModificarPresupuestoException (int code){
        return new ModificarPresupuestoException(code);
    }
    
    public static ModificarPresupuestoException instanciarModificarPresupuestoException (int code, String message){
        return new ModificarPresupuestoException(code, message);
    }
    
    public static VerificarNombreException instanciarVerificarNombreException (int code){
        return new VerificarNombreException(code);
    }
    
    public static VerificarNombreException instanciarVerificarNombreException(int code, String message){
        return new VerificarNombreException(code, message);
    }
    
    public static EliminarPresupuestoException instanciarEliminarPresupuestoExeption (int code){
        return new EliminarPresupuestoException(code);
    }
    
    public static EliminarPresupuestoException instanciarEliminarPresupuestoExeption(int code, String message){
        return new EliminarPresupuestoException(code, message);
    }
    
    public static ActualizarClaveException instanciarActualizarClaveException
    (int code, String message){
        return new ActualizarClaveException(code,message);
    
    }
    
    public static IniciarSesionException instanciarIniciarSesionException
    (int code, String message){
        return new IniciarSesionException(code,message);
    
    }
    
    public static RecuperarClaveException instanciarRecuperarClaveException
    (int code, String message){
        return new RecuperarClaveException(code,message);
    
    }
    
     
    public static RegistrarIncorrectoException 
    instanciarRegistrarIncorrectoException(int code, String message){
        return new RegistrarIncorrectoException(code,message);
    
    }
    
    public static VerificarUsuarioException instanciarVerificarUsuarioException
    (int code, String message){
        return new VerificarUsuarioException(code,message);
    
    }
     
   
    public static RegistrarIncorrectoException 
    instanciarRegistrarIncorrectoException(int code){
        return new RegistrarIncorrectoException(code);
    
    }  
     
     public static ActualizarClaveException instanciarActualizarClaveException
    (int code){
        return new ActualizarClaveException(code);
    
    }
     public static IniciarSesionException instanciarIniciarSesionException
     (int code){
      return new IniciarSesionException(code);
    
    }    
        
     public static RecuperarClaveException instanciarRecuperarClaveException
    (int code){
        return new RecuperarClaveException(code);
    
    }   
        
    public static AgregarFallidoException instanciarAgregarFallidoException
        (int code, String message) {
        return new AgregarFallidoException(code, message);
    }
    
    public static ModificarFallidoException instanciarModificarFallidoException
        (int code, String message) {
        return new ModificarFallidoException(code, message);
    }
    
    public static ConsultarFallidoException instanciarConsultarFallidoException
        (int code, String message) {
        return new ConsultarFallidoException(code, message);
    }
    
    public static ConversionFallidaException instanciarConversionFallidaException
        (int code, String message) {
        return new ConversionFallidaException(code, message);
    }
    
    public static EliminarFallidoException instanciarEliminarFallidoException
        (int code, String message) {
        return new EliminarFallidoException(code, message);
    }
        
    public static ExportarPresupuestoException instanciarExportarPresupuestoException(int code, String message) {
        return new ExportarPresupuestoException(code, message);
    }
    
}
