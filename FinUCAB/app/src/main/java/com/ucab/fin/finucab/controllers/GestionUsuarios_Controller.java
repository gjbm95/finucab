package com.ucab.fin.finucab.controllers;

import android.widget.EditText;

import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenasDiferentes_Exception;
import com.ucab.fin.finucab.exceptions.CorreoInvalido_Exception;
import com.ucab.fin.finucab.exceptions.Longitud_Exception;

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

        try{
            verificoVacio(nombre);
            verificoLongitud(nombre,50,"string");
            verificoVacio(apellido);
            verificoLongitud(apellido,50,"string");
            verificoVacio(correo);
            verificoLongitud(correo,100,"string");
            verificoCorreo(correo);
        }catch (CampoVacio_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        } catch (CorreoInvalido_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }catch (Longitud_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }

               //Estadarizamos mayusculas y minusculas:
               nombre.setText(GestionUsuarios_Controller.formatearCadena(nombre.getText().toString()));
               apellido.setText(GestionUsuarios_Controller.formatearCadena(apellido.getText().toString()));
               return 0;
    }


    //Se encarga de validar que no se encuentre vacio los campos usuario, contrasena, repeticion de contrasena
    public static int validacionEtapaCuenta()
    {
          try{
              verificoVacio(usuario);
              verificoLongitud(usuario,50,"string");
              verificoVacio(contrasena1);
              verificoVacio(contrasena2);
              verificoIgualdad(contrasena1,contrasena2);
          } catch (CampoVacio_Exception e){
              e.getCampo().setError(e.getMessage());
              return 1;
          } catch (ContrasenasDiferentes_Exception e){
              e.getCampo().setError(e.getMessage());
              return 1;
          } catch (Longitud_Exception e){
              e.getCampo().setError(e.getMessage());
              return 1;
          }
        /*
            Falta validar si  ya existe el nombre de usuario en el sistema.
         */

        return 0;
    }


    //Se encarga de validar que no se encuentre vacio los campos pregunta, respuesta
    public static int validacionEtapaSeguridad(){
        try{
            verificoVacio(pregunta);
            verificoLongitud(pregunta,1000,"string");
            verificoVacio(respuesta);
            verificoLongitud(respuesta,1000,"string");
        }catch (CampoVacio_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }catch (Longitud_Exception e){
            e.getCampo().setError(e.getMessage());
            return 1;
        }

        return 0;
    }

    //Validacion para verficar que los campos cumplan con el rango correcto
    public static void verificoLongitud(EditText campo,int longitud, String tipo)throws Longitud_Exception {
           if (campo.getText().toString().length() >= longitud) {
               if (tipo.equals("string")) {
                   Longitud_Exception campolargo = new Longitud_Exception("El texto es demasiado largo");
                   campolargo.setCampo(campo);
                   throw campolargo;
               }
               if (tipo.equals("int")) {
                   Longitud_Exception campolargo = new Longitud_Exception("El numero es muy alto");
                   campolargo.setCampo(campo);
                   throw campolargo;
               }
           }
    }

    //Realizo la validacion para verificar que el campo este vacio:
    public static void verificoVacio(EditText campo) throws CampoVacio_Exception {
        if (campo.getText().toString().isEmpty())
        {
            CampoVacio_Exception campovacio = new CampoVacio_Exception("Este campo esta vacio");
            campovacio.setCampo(campo);
            throw campovacio;
        }

    }

    //Realizo la validacion para verificar que las contraseñas son diferentes:
    public static void verificoIgualdad(EditText contesena1, EditText contrasena2) throws ContrasenasDiferentes_Exception {
        if (!(contrasena1.getText().toString().equals(contrasena2.getText().toString())))
        {
            ContrasenasDiferentes_Exception diferentes = new ContrasenasDiferentes_Exception("Las contraseñas son diferentes");
            diferentes.setCampo(contrasena2);
            throw diferentes;
        }

    }

    //Realizo la validacion para verificar si el correo electronico suministrado es valido.
    public static void verificoCorreo(EditText campo)throws CorreoInvalido_Exception{
        if (!campo.getText().toString().contains("@")) {
            CorreoInvalido_Exception correoinvalido  =  new CorreoInvalido_Exception("El correo es invalido");
            correoinvalido.setCampo(campo);
            throw correoinvalido;
        }else
        {
            String [] fragmento = campo.getText().toString().split("@");
            if (fragmento.length==2) {
                if (!fragmento[1].contains(".")) {
                    CorreoInvalido_Exception correoinvalido = new CorreoInvalido_Exception("El correo es invalido");
                    correoinvalido.setCampo(campo);
                    throw correoinvalido;
                }
            }else
            {
                CorreoInvalido_Exception correoinvalido = new CorreoInvalido_Exception("El correo es invalido");
                correoinvalido.setCampo(campo);
                throw correoinvalido;
            }
        }
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
