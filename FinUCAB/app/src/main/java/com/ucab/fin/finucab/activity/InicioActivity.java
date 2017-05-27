package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.exceptions.ContrasenaInvalida_Exception;
import com.ucab.fin.finucab.exceptions.UsuarioInvalido_Exception;
import com.ucab.fin.finucab.webservice.Parametros;
/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase se encarga de gestionar la actividad de Inicio de sesion de la aplicacion. Y de inicializar
 * parametros de red para la aplicacion.
 *
 **/
public class InicioActivity extends AppCompatActivity implements View.OnClickListener {


    Button signInButton; // Boton para activar el inicio de sesion.
    Button signUpButton; // Boton para activar el registro de usuario.
    TextView forgotPwdText; // Boton para activar la recuperacion de cuenta.
    EditText userNameEditText; // Caja de texto para almacenar el nombre de usuario.
    EditText usrPwdEditText; // Caja de texto para almacenar la contraseña.


    /**
     * Metodo de inicializacion de la actividad
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Colocando el icono en la parte superior izquierda:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");
        //------------------------------------------------------------------------------------------
//        BIND VIEES (Se extraen los objetos asociados a los botones en pantalla)
        signInButton = (Button) findViewById(R.id.signInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        forgotPwdText = (TextView) findViewById(R.id.forgotPwdTextView);
        userNameEditText = (EditText) findViewById(R.id.userNameEditText);
        usrPwdEditText = (EditText) findViewById(R.id.userPwdEditText);
//        SET LISTENERS (Se le asigna la actividad en el cual funcionaran)
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        forgotPwdText.setOnClickListener(this);

    }


    /**
     * Agrego un menu Overflow al Action Bar:
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }


    /**
     * Se le coloca acciones a las funcionalidades que ofrece el Menu overflow del action bar.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                System.exit(0);
                return true;
            case R.id.setting:
                //Aqui se llama a las opciones de Configuracion
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Dandole funcionalidades a cada uno de los botones que salen en pantalla:
     */
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){

            //Al accionar, se inician los procesos de validacion de datos para acceder el perfil de usuario.
            case R.id.signInButton:
                try {
                    GestionUsuarios_Controller.verificoVacio(userNameEditText);
                    GestionUsuarios_Controller.verificoVacio(usrPwdEditText);
                    GestionUsuarios_Controller.usuario =userNameEditText;
                    GestionUsuarios_Controller.contrasena1=usrPwdEditText;
                    GestionUsuarios_Controller.inicioSesion(GestionUsuarios_Controller.usuario,
                            GestionUsuarios_Controller.contrasena1,InicioActivity.this);


                    userNameEditText.setText("");
                    usrPwdEditText.setText("");
                }catch(CampoVacio_Exception e) {
                    e.getCampo().setError(e.getMessage());
                }
                break;

            //Al accionar, se inicia la actividad que presenta el formulario de registro.
            case R.id.signUpButton:
                i = new Intent(InicioActivity.this, RegistroActivity.class);
                startActivity(i);
                break;

            //Al accionar, se verifican los datos e inicia la actividad de recuperar contraseña.
            case R.id.forgotPwdTextView:
                try {
                    GestionUsuarios_Controller.verificoVacio(userNameEditText);
                    Parametros.reset();
                    GestionUsuarios_Controller.nombre=userNameEditText;
                    GestionUsuarios_Controller.verificoUsuario(InicioActivity.this,userNameEditText);


                }catch(CampoVacio_Exception e) {
                    e.getCampo().setError(e.getMessage());
                } catch (UsuarioInvalido_Exception e) {
                    e.getCampo().setError(e.getMessage());
                }
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Parametros.getRespuesta() != null) {

            if (Parametros.getRespuesta().equals("Error")||Parametros.getRespuesta().equals("ERROR") ) {

                mensajeError("Error de conexion con servidor!");
                GestionUsuarios_Controller.resetarVariables();
                Parametros.reset();
            } else if (Parametros.getRespuesta().equals("No Disponible")) {
                GestionUsuarios_Controller.buscarUsuario(InicioActivity.this,GestionUsuarios_Controller.nombre.getText().toString());

            } else if (Parametros.getRespuesta().equals("Usuario Disponible")) {

                mensajeError("El nombre de usuario suministrado no existe!");
                GestionUsuarios_Controller.resetarVariables();
                Parametros.reset();
            }else if (Parametros.getRespuesta().contains("recuperarclave")){
                String[] datos = Parametros.getRespuesta().split(":-:");
                GestionUsuarios_Controller.descomponerUsuario(datos[0]);
                Intent i = new Intent(InicioActivity.this, RecuperacionActivity.class);
                startActivity(i);

            }else if (Parametros.getRespuesta().contains("iniciosesion")){
                String[] datos = Parametros.getRespuesta().split(":-:");
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("cookie",datos[0]);
                editor.commit();
                GestionUsuarios_Controller.descomponerUsuario(datos[0]);
                Intent i = new Intent(InicioActivity.this, MainActivity.class);
                GestionUsuarios_Controller.resetarVariables();
                Parametros.reset();
                startActivity(i);
                finish();

            }else if (Parametros.getRespuesta().equals("DATOSMAL") ) {

                mensajeError("Combinacion de datos es incorrecta!");
            }
        }
    }

    private void mensajeError(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(mensaje);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}