package com.ucab.fin.finucab;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.ucab.fin.finucab.activity.RegistroActivity;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.fragment.DatosCuentaFragment;
import com.ucab.fin.finucab.fragment.DatosPersonalesFragment;
import com.ucab.fin.finucab.fragment.DatosSeguridadFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.FragmentController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejadandro Negrin
 *Descripción de la clase:
 * Esta clase se encarga de realizar pruebas unitarias a la Controladora de Gestion de Usuarios
 *
 **/
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class GestionUsuariosTest {


  /*
    private DatosSeguridadFragment fragmentregistro;


    @Before
    public void setUp() throws Exception
    {

        fragmentregistro = Robolectric.( DatosSeguridadFragment.class )
                .create()
                .resume()
                .get();
    }
*/


    /**
     * Realizo pruebas a la validacion de preguntas y respuestas de usuarios
     *
     * @throws Exception
     */

    @Test
    public void validacionSeguridad_isCorrect() throws Exception {
        DatosSeguridadFragment fragment = new DatosSeguridadFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.resetarVariables();
        GestionUsuarios_Controller.respuesta = (EditText)fragment.getView().findViewById(R.id.answerREditText);
        GestionUsuarios_Controller.respuesta.setText("");
        GestionUsuarios_Controller.pregunta = (EditText)fragment.getView().findViewById(R.id.questionREditText);
        GestionUsuarios_Controller.pregunta.setText("Hola");
        assertEquals(GestionUsuarios_Controller.validacionEtapaSeguridad(),1);
        //Situacion 2:
        GestionUsuarios_Controller.respuesta.setText("Hola");
        GestionUsuarios_Controller.pregunta.setText("");
        assertEquals(GestionUsuarios_Controller.validacionEtapaSeguridad(),1);

        System.out.println("El metodo validacionRespuesta() funciona correctamente");

    }

    /**
     * Realizo pruebas a la validacion de contraseñas y nombres de usuarios
     *
     * @throws Exception
     */

    @Test
    public void validacionCuenta_isCorrect() throws Exception {
        DatosCuentaFragment fragment = new DatosCuentaFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.resetarVariables();
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Prueba");
        assertEquals(GestionUsuarios_Controller.validacionContrasenas(),0);
        //Situacion 2:
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Fallo");
        assertEquals(GestionUsuarios_Controller.validacionContrasenas(),1);
        //Situacion 3:
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Fallo");
        assertEquals(GestionUsuarios_Controller.validacionContrasenas(),1);
        //Situacion 4:
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("");
        assertEquals(GestionUsuarios_Controller.validacionContrasenas(),1);
        //Situacion 5:
        GestionUsuarios_Controller.usuario = (EditText)fragment.getView().findViewById(R.id.usernameREditText);
        GestionUsuarios_Controller.usuario.setText("");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Prueba");
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba");
        assertEquals(GestionUsuarios_Controller.validacionEtapaCuenta(),1);
        //Situacion 6:
        GestionUsuarios_Controller.usuario = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.usuario.setText("Prueba");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Prueba");
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba");
        assertEquals(GestionUsuarios_Controller.validacionEtapaCuenta(),0);
        System.out.println("El metodo validacionContrasena() funciona correctamente");

    }


    /**
     * Realizo pruebas a la validacion de datos personales de usuarios
     *
     * @throws Exception
     */

    @Test
    public void validacionDatos_isCorrect() throws Exception {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.resetarVariables();
        GestionUsuarios_Controller.nombre = (EditText)fragment.getView().findViewById(R.id.nameREditText);
        GestionUsuarios_Controller.nombre.setText("Prueba");
        GestionUsuarios_Controller.apellido = (EditText)fragment.getView().findViewById(R.id.lastnameREditText);
        GestionUsuarios_Controller.apellido.setText("Prueba");
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("Prueba@hotmail.com");
        assertEquals(GestionUsuarios_Controller.validacionEtapaDatos(),0);
        //Situacion 2:
        GestionUsuarios_Controller.nombre = (EditText)fragment.getView().findViewById(R.id.nameREditText);
        GestionUsuarios_Controller.nombre.setText("");
        GestionUsuarios_Controller.apellido = (EditText)fragment.getView().findViewById(R.id.lastnameREditText);
        GestionUsuarios_Controller.apellido.setText("Prueba");
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("PruebaPrueba@hotmail.com");
        assertEquals(GestionUsuarios_Controller.validacionEtapaDatos(),1);
        //Situacion 3:
        GestionUsuarios_Controller.nombre = (EditText)fragment.getView().findViewById(R.id.nameREditText);
        GestionUsuarios_Controller.nombre.setText("Prueba");
        GestionUsuarios_Controller.apellido = (EditText)fragment.getView().findViewById(R.id.lastnameREditText);
        GestionUsuarios_Controller.apellido.setText("");
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("PruebaPrueba@hotmail.com");
        assertEquals(GestionUsuarios_Controller.validacionEtapaDatos(),1);
        //Situacion 4:
        GestionUsuarios_Controller.nombre = (EditText)fragment.getView().findViewById(R.id.nameREditText);
        GestionUsuarios_Controller.nombre.setText("Prueba");
        GestionUsuarios_Controller.apellido = (EditText)fragment.getView().findViewById(R.id.lastnameREditText);
        GestionUsuarios_Controller.apellido.setText("Prueba");
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("Prueba");
        assertEquals(GestionUsuarios_Controller.validacionEtapaDatos(),1);
        //Situacion 5:
        GestionUsuarios_Controller.nombre = (EditText)fragment.getView().findViewById(R.id.nameREditText);
        GestionUsuarios_Controller.nombre.setText("Pruebaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        GestionUsuarios_Controller.apellido = (EditText)fragment.getView().findViewById(R.id.lastnameREditText);
        GestionUsuarios_Controller.apellido.setText("Prueba");
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("Prueba@hotmail.com");
        assertEquals(GestionUsuarios_Controller.validacionEtapaDatos(),1);

        System.out.println("El metodo validacionDatos() funciona correctamente");

    }



}
