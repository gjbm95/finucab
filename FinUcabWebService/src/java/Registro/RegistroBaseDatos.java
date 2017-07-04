/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import BaseDatosDAO.Seguridad;

/**
 *
 * @author Junior
 */
public class RegistroBaseDatos {
    private static final Seguridad seguridad = Seguridad.obtenerInstancia();
    public static String nombreDB =seguridad.obtenerUsuarioDB();
    public static String contrasenaDB = seguridad.obtenerContrasenaDB();
    public static String url = seguridad.obtenerServerDB();
    
    //Registros Modulo 3:
    public static final String AGREGAR_PRESUPUESTO = " select agregarpresupuesto (?,?::real,?,?,?,?)";
    public static final String MODIFICAR_PRESUPUESTO = " select modificarpresupuesto (?,?::real,?,?,?,?,?)";
    public static final String LISTAR_PRESUPUESTOS = " select * from obtenerlistapresupuesto(?)";
    public static final String LISTAR_PRESUPUESTOS_EXPORTAR = " select * from obtenerlistapresupuestodesc(?)";
    public static final String OBTENER_PRESUPUESTO = " select * from obtenerpresupuesto(?)";
    public static final String ELIMINAR_PRESUPUESTO = " select eliminarpresupuesto(?)";
    public static final String VERIFICAR_NOMBRE = " select verificarnombre(?)";
    //Registros Modulo 5:
    public static final String AGREGAR_PAGO = "{ call AgregarPago(?,?,?,?) }";
    public static final String MODIFICAR_PAGO = "{ call ModificarPago(?,?,?,?,?) }";
    public static final String LISTAR_PAGOS = "{ call ListaPagos(?) }";
    public static final String OBTENER_PAGO = "{ call ConsultarPago(?) }";
    //Registros Modulo 1:
    public static final String AGREGAR_USUARIO = "{ call Registrar(?,?,?,?,?,?,?) }";
    public static final String ACTUALIZAR_CLAVE = "{ call ActualizarClave(?,?) }";
    public static final String VERIFICAR_USUARIO = "{ call verificarUsuario(?) }";
    public static final String INICIO_SESION = "{ call iniciarSesion(?,?) }";
    public static final String RECUPERAR_CLAVE = "{ call recuperarclave(?) }";
    //Registros Modulo 2:
    public static final String ACTUALIZAR_USUARIO = "{ call update_usuario(?,?,?,?,?,?,?,?)}"; 
    public static final String ESTADISTICA_PRESUPUESTO = "{ call obtenerUltimosPresupuestos(?)}";
    public static final String ESTADISTICA_PAGO = "{ call obtenerBalance(?)}";
    public static final String ESTADISTICA_PLANIFICACION = "{ call obtenerUltimosPlanificaciones(?)}"; 
    public static final String AGREGAR_CUENTABANCARIA = "{ call agregarCuentaBancaria(?,?,?,?,?)}";
    public static final String MODIFICAR_CUENTABANCARIA = "{ call modificarCuentaBancaria(?,?,?,?,?)}";
    public static final String ELIMINAR_CUENTABANCARIA = "{ call eliminarCuentaBancaria(?)}";
    public static final String OBTENER_CUENTASBANCARIAS = "{ call obtenerCuentasBancarias(?,?)}";
    public static final String OBTENER_SALDOCUENTAS = "{ call getSaldoCuentas(?)}";
    public static final String AGREGAR_TDC = "{ call agregarTarjetaCredito(?,?,?,?,?)}";
    public static final String MODIFICAR_TDC = "{ call modificarTarjetaCredito(?,?,?,?,?)}";
    public static final String ELIMINAR_TDC = "{ call eliminarTarjetasCredito(?)}";
    public static final String OBTENER_TDCS = "{ call obtenerTarjetasCredito(?,?)}";
    public static final String OBTENER_SALDOTDC = "{ call getSaldoTarjetas(?)}";
    
}
