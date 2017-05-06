package com.ucab.fin.finucab.controllers;

import android.widget.EditText;

/**
 * Created by Junior on 06/05/2017.
 */

public class GestionUsuarios_Controller {
    //Recursos:
    public static EditText nombre;
    public static EditText apellido;
    public static EditText correo;
    public static EditText usuario;
    public static EditText contrasena1;
    public static EditText contrasena2;
    public static EditText pregunta;
    public static EditText respuesta;


    public GestionUsuarios_Controller (){


    }

    //Se encarga de validar que no se encuentre vacio los campos nombre, apellido, correo
    //Tambien se valida si el formato del correo electronico es correcto.
    public static int validacionEtapaDatos(){

            if (nombre.getText().toString().isEmpty()){
                nombre.setError("Debe colocar un Nombre");

                return 1;
            } else if (apellido.getText().toString().isEmpty()){
                apellido.setError("Debe colocar un Apellido");
                return 1;
            } else if (correo.getText().toString().isEmpty()){
               correo.setError("Debe colocar una direccion de Correo");
               return 1;
            }else if (!correo.getText().toString().contains("@")) {
               correo.setError("El correo no es valido!");
               return 1;
            }else
              {
                  String [] fragmento = correo.getText().toString().split("@");
                  if (!fragmento[1].contains(".")) {
                    correo.setError("El correo no es valido!");
                    return 1;
                  }
              }
               //Estadarizamos mayusculas y minusculas:
               nombre.setText(GestionUsuarios_Controller.formatearCadena(nombre.getText().toString()));
               apellido.setText(GestionUsuarios_Controller.formatearCadena(apellido.getText().toString()));
               return 0;
    }


    //Se encarga de validar que no se encuentre vacio los campos usuario, contrasena, repeticion de contrasena
    public static int validacionEtapaCuenta()
    {

        if (usuario.getText().toString().isEmpty()){
            usuario.setError("Debe colocar una nombre de Usuario");
            return 1;
        }else if (contrasena1.getText().toString().isEmpty()){
            contrasena1.setError("Debe colocar una Contrasena");
            return 1;
        }else if (contrasena2.getText().toString().isEmpty()){
            contrasena2.setError("Debe repetir la Contrasena");
            return 1;
        }else if (!(contrasena1.getText().toString().equals(contrasena2.getText().toString())))
        {
            contrasena2.setError("Las contraseñas son diferentes");
            return 1;
        }

        /*
            Falta validar si  ya existe el nombre de usuario en el sistema.
         */

        return 0;
    }


    //Se encarga de validar que no se encuentre vacio los campos pregunta, respuesta
    public static int validacionEtapaSeguridad(){

        if (pregunta.getText().toString().isEmpty()){
            pregunta.setError("Debe colocar una Pregunta");
            return 1;
        } else if (respuesta.getText().toString().isEmpty()){
            respuesta.setError("Debe colocar una Respuesta");
            return 1;
        }

        return 0;
    }

    //Este metodo le da un formato unico a los Nombre y Apellidos. Colocando su primera letra en Mayusculas y el
    //resto en minusculas.
    public static CharSequence formatearCadena (String texto){
        String convertido = texto.toLowerCase();
        return Character.toUpperCase(convertido.charAt(0)) + convertido.substring(1);
    }

    //Inicializo nuevamente las variables
    public static void resetarVariables (){
         nombre=null;
         apellido=null;
         correo=null;
         usuario=null;
         contrasena1=null;
         contrasena2=null;
         pregunta=null;
         respuesta=null;
    }


}
