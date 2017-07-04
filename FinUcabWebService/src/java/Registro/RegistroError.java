/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Ramon
 */
public class RegistroError {
    
    public static final String error_default = "Ha ocurrido un error";
    
    private static final String error_parametros = "Ha ocurrido un error con los datos proporcionados";
    private static final String error_respuesta_vacia = "No se ha encontrado la entidad con el id proposionado";
    private static final String error_string_vacia = "Se ha proporcionado una cadena de caracteres vacio";
    private static final String error_numero_vacia = "Se ha proporcionado un numero nulo";
    private static final String error_entidad_nula = "La Entidad proporcionada es nula";
    private static final String error_string_nula = "La cadena de caracteres proporcionda es nula";
    
    private static final String error_consulta_vacia = "La consulta realizada no trajo ningun resultado";
    private static final String error_agregar_pago = "Ocurrio un error al agregar su nuevo pago";
    private static final String error_tipo_nopermitido = "El tipo proporcionado no esta permitida (debe ser o ingreso o egreso) ";

    private static final String error_actualizar_clave = "Ha ocurrido un error actualizando la clave";
    private static final String error_recuperar_clave = "Ha ocurrido un error recuperando la clave";
    
    private static final String ERROR_AGREGAR = "Ha ocurrido un error al agregar un presupuesto";
    private static final String ERROR_ELIMINAR = "Presupuesto no encontrado";

    
    private static final String error_base_datos = "Ocurrio un error interno en la base de datos";
    private static final String error_decode = "Ocurrio un error al descodificar las datos proporcionados";
     
    
    public static final HashMap<Integer,String> errores;
    static{
        HashMap<Integer,String> errorsLocal = new HashMap<Integer,String>();
        errorsLocal.put(1, error_parametros);
        errorsLocal.put(2, error_respuesta_vacia);
        errorsLocal.put(3, error_string_vacia);
        errorsLocal.put(4, error_string_nula);
        errorsLocal.put(5, error_entidad_nula);
        errorsLocal.put(6, error_numero_vacia);
        
        errorsLocal.put(100, error_agregar_pago);
        errorsLocal.put(101, error_consulta_vacia);
        errorsLocal.put(102, error_tipo_nopermitido);
        
        errorsLocal.put(201, error_actualizar_clave);
        errorsLocal.put(202, error_recuperar_clave);
        
        errorsLocal.put(300, ERROR_AGREGAR);
        errorsLocal.put(301, ERROR_ELIMINAR);
        
        errorsLocal.put(998, error_base_datos);        
        errorsLocal.put(999, error_decode);
        
        errores = errorsLocal;
        
    }
    
     
}
