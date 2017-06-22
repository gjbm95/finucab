package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.fragment.ContrasenaFragment;
import com.ucab.fin.finucab.fragment.RespuestaFragment;
import com.ucab.fin.finucab.webservice.Parametros;
/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase se encarga de gestionar la actividad de recuperacion de cuenta de usuario.
 *
 **/
public class RecuperacionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button siguiente; //Boton de siguiente
    private Button cancelar;  //Boron de cancelar
    private int conteo;  // Contador que indica el numero de formulario a mostrar


    /**
     * Metodo encargado de crear la actividad.
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        activarPaso(1);
        conteo=1;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Colocando el icono en la parte superior izquierda:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");
        cancelar = (Button) findViewById(R.id.cancelButton);
        siguiente = (Button) findViewById(R.id.siguienteButton);
        cancelar.setOnClickListener(this);
        siguiente.setOnClickListener(this);

    }


    /**
     * Agrego un menu Overflow al Action Bar:
    **/
     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;

    }

    /**
    *Se le coloca acciones a las funcionalidades que ofrece el Menu overflow del action bar.
    **/
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
     * Metodo cambiar el formulario para la recuperacion de contraseña.
     * @Param indicador numero de formulario a mostrar. (Del 1 al 2)
     */
    public void activarPaso(int indicador) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if(indicador ==1) {
            RespuestaFragment fragment1 = new RespuestaFragment();
            fragmentTransaction.replace(R.id.RecoverFragment, fragment1).commit();
        }
        else
        if(indicador ==2) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            ContrasenaFragment fragment1 = new ContrasenaFragment();
            fragmentTransaction.replace(R.id.RecoverFragment, fragment1).commit();
            GestionUsuarios_Controller.pasoRecuperacion=2;

        }
    }
    /**
     * Metodo encargado de darle funcionalidades a los botones de la pantalla de recuperacion de contraseña.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.siguienteButton:
                if(conteo==1 && GestionUsuarios_Controller.validacionRespuesta()==0){
                    activarPaso(2);
                    conteo++;
                }else{
                    GestionUsuarios_Controller.pasoRecuperacion=2;
                    if(conteo ==2 && GestionUsuarios_Controller.validacionContrasenas()==0) {

                        GestionUsuarios_Controller.actualizarContrasena(GestionUsuarios_Controller.contrasena2
                                .getText().toString(),RecuperacionActivity.this);

                    }
                }
                break;
            case R.id.cancelButton:
                GestionUsuarios_Controller.resetarVariables();
                Intent iniciar = new Intent(RecuperacionActivity.this, InicioActivity.class);
                startActivity(iniciar);
                GestionUsuarios_Controller.resetarVariables();
                Parametros.reset();
                finish();
                break;
        }
    }


    /**
     * Metodo encargado de recargar la actividad y recibir los datos enviados desde el Web Service
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(GestionUsuarios_Controller.pasoRecuperacion==2) {
            if (Parametros.getRespuesta().equals("5")) {
                GestionUsuarios_Controller.resetarVariables();
                Intent iniciar = new Intent(RecuperacionActivity.this, InicioActivity.class);
                startActivity(iniciar);
                GestionUsuarios_Controller.resetarVariables();
                Parametros.reset();
                finish();
            } else {
                mensajeError(getString(R.string.inesperado));

            }
        }

    }

    /**
     * Metodo que se encarga de mostrar un mensaje de error.
     * @param mensaje
     */
    private void mensajeError(String mensaje){
        Toast.makeText(RecuperacionActivity.this,mensaje,Toast.LENGTH_LONG);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(mensaje);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}