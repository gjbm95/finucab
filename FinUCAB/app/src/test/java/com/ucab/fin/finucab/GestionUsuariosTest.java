package com.ucab.fin.finucab;
import android.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.ucab.fin.finucab.activity.RegistroActivity;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenasDiferentes_Exception;
import com.ucab.fin.finucab.exceptions.CorreoInvalido_Exception;
import com.ucab.fin.finucab.exceptions.Longitud_Exception;
import com.ucab.fin.finucab.exceptions.UsuarioInvalido_Exception;
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
    }

    /**
     * Realizo pruebas a la validacion de contraseñas y nombres de usuarios
     *
     * @throws Exception
     */
    @Test
    public void validacionContrasenas_isCorrect() throws Exception {
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
    }
    /**
     * Realizo pruebas a la validacion de contraseñas
     * @throws Exception
     */
    @Test
    public void validacionCuenta_isCorrect() throws Exception {
        DatosCuentaFragment fragment = new DatosCuentaFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.usuario = (EditText)fragment.getView().findViewById(R.id.usernameREditText);
        GestionUsuarios_Controller.usuario.setText("");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Prueba");
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba");
        assertEquals(GestionUsuarios_Controller.validacionEtapaCuenta(),1);
        //Situacion 2:
        GestionUsuarios_Controller.usuario = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.usuario.setText("Prueba");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Prueba");
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba");
        assertEquals(GestionUsuarios_Controller.validacionEtapaCuenta(),0);
        //Situacion 1:
        GestionUsuarios_Controller.usuario = (EditText)fragment.getView().findViewById(R.id.usernameREditText);
        GestionUsuarios_Controller.usuario.setText("Prueba");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Prueba");
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Prueba2");
        assertEquals(GestionUsuarios_Controller.validacionEtapaCuenta(),1);
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
    }


    /**
     * Realizo pruebas a la validacion de longitud de caracteres
     *
     * @throws Exception
     */

    @Test
    public void validacionLongitud_isCorrect() throws Exception {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.nombre = (EditText)fragment.getView().findViewById(R.id.nameREditText);
        GestionUsuarios_Controller.nombre.setText("Pruebaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        boolean paso = false ;
        try{
            GestionUsuarios_Controller.verificoLongitud(GestionUsuarios_Controller.nombre,50,"string");
        }catch(Longitud_Exception e){
             paso = true;
        }
         assertTrue(paso);
        //Situacion 2:
        GestionUsuarios_Controller.nombre.setText("Prueba");
        paso = false;
        try{
            GestionUsuarios_Controller.verificoLongitud(GestionUsuarios_Controller.nombre,50,"string");
        }catch(Longitud_Exception e){
            paso = true;
        }
        assertFalse(paso);
    }

    /**
     * Realizo pruebas a la validacion de texto vacio
     *
     * @throws Exception
     */

    @Test
    public void validacionVacio_isCorrect() throws Exception {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.nombre = (EditText)fragment.getView().findViewById(R.id.nameREditText);
        GestionUsuarios_Controller.nombre.setText("");
        boolean paso = false ;
        try{
            GestionUsuarios_Controller.verificoVacio(GestionUsuarios_Controller.nombre);
        }catch(CampoVacio_Exception e){
            paso = true;
        }
        assertTrue(paso);
        //Situacion 2:
        GestionUsuarios_Controller.nombre.setText("Prueba");
        paso = false;
        try{
            GestionUsuarios_Controller.verificoVacio(GestionUsuarios_Controller.nombre);
        }catch(CampoVacio_Exception e){
            paso = true;
        }
        assertFalse(paso);
    }


    /**
     * Realizo pruebas a la validacion de nombre de usuario vacio
     *
     * @throws Exception
     */
    @Test
    public void validacionUsuario_isCorrect() throws Exception {
        DatosCuentaFragment fragment = new DatosCuentaFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.usuario = (EditText)fragment.getView().findViewById(R.id.usernameREditText);
        GestionUsuarios_Controller.usuario.setText("");
        boolean paso = false ;
        try{
            GestionUsuarios_Controller.verificoUsuario(GestionUsuarios_Controller.usuario);
        }catch(UsuarioInvalido_Exception e){
            paso = true;
        }
        assertTrue(paso);
        //Situacion 2:
        GestionUsuarios_Controller.usuario.setText("Prueba");
        paso = false;
        try{
            GestionUsuarios_Controller.verificoUsuario(GestionUsuarios_Controller.usuario);
        }catch(UsuarioInvalido_Exception e){
            paso = true;
        }
        assertFalse(paso);
    }


    /**
     * Realizo pruebas a la validacion de igualdad de contraseñas
     *
     * @throws Exception
     */
    @Test
    public void validacionIgualdad_isCorrect() throws Exception {
        DatosCuentaFragment fragment = new DatosCuentaFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Hola");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Hola2");
        boolean paso = false ;
        try{
            GestionUsuarios_Controller.verificoIgualdad(GestionUsuarios_Controller.contrasena1,GestionUsuarios_Controller.contrasena2);
        }catch(ContrasenasDiferentes_Exception e){
            paso = true;
        }
        assertTrue(paso);
        //Situacion 2:
        GestionUsuarios_Controller.contrasena1 = (EditText)fragment.getView().findViewById(R.id.passwordREditText);
        GestionUsuarios_Controller.contrasena1.setText("Hola");
        GestionUsuarios_Controller.contrasena2 = (EditText)fragment.getView().findViewById(R.id.passwordtwoREditText);
        GestionUsuarios_Controller.contrasena2.setText("Hola");
        paso = false ;
        try{
            GestionUsuarios_Controller.verificoIgualdad(GestionUsuarios_Controller.contrasena1,GestionUsuarios_Controller.contrasena2);
        }catch(ContrasenasDiferentes_Exception e){
            paso = true;
        }
        assertFalse(paso);
    }

    /**
     * Realizo pruebas a la validacion de correo electronico
     *
     * @throws Exception
     */
    @Test
    public void validacionCorreo_isCorrect() throws Exception {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("Hola");
        boolean paso = false ;
        try{
            GestionUsuarios_Controller.verificoCorreo(GestionUsuarios_Controller.correo);
        }catch(CorreoInvalido_Exception e){
            paso = true;
        }
        assertTrue(paso);
        //Situacion 2:
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("Hola@hotmail");
        paso = false ;
        try{
            GestionUsuarios_Controller.verificoCorreo(GestionUsuarios_Controller.correo);
        }catch(CorreoInvalido_Exception e){
            paso = true;
        }
        assertTrue(paso);
        //Situacion 3:
        GestionUsuarios_Controller.correo = (EditText)fragment.getView().findViewById(R.id.emailR2EditText);
        GestionUsuarios_Controller.correo.setText("Hola@hotmail.com");
        paso = false ;
        try{
            GestionUsuarios_Controller.verificoCorreo(GestionUsuarios_Controller.correo);
        }catch(CorreoInvalido_Exception e) {
            paso = true;
        }
        assertFalse(paso);
    }

    /**
     * Realizo pruebas al formateo de cadenas de caracteres.
     */
    @Test
    public void formatarCadena_isCorrect() throws Exception {
        //Situacion 1:
        assertEquals(GestionUsuarios_Controller.formatearCadena("nombre"),"Nombre");
        //Situacion 2:
        assertEquals(GestionUsuarios_Controller.formatearCadena("NOMBRE"),"Nombre");
        //Situacion 3:
        assertEquals(GestionUsuarios_Controller.formatearCadena("Nombre"),"Nombre");
        //Situacion 4:
        assertEquals(GestionUsuarios_Controller.formatearCadena(""),"");
    }
    /**
     * Realizo pruebas al reseteo de variables en
     */
    @Test
    public void resetarVariables_isCorrect() throws Exception {
        DatosCuentaFragment fragment = new DatosCuentaFragment();
        startFragment( fragment );
        //Situacion 1:
        GestionUsuarios_Controller.usuario = (EditText)fragment.getView().findViewById(R.id.usernameREditText);
        GestionUsuarios_Controller.usuario.setText("Hola");
        assertNotNull(GestionUsuarios_Controller.usuario);
        GestionUsuarios_Controller.resetarVariables();
        assertNull(GestionUsuarios_Controller.usuario);
    }
}
