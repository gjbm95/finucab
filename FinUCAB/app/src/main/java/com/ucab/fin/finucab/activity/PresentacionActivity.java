package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.webservice.Parametros;
/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase se encarga de gestionar la actividad de Presentacion de la aplicacion. Y de inicializar
 * parametros de red para la aplicacion.
 *
 **/

public class PresentacionActivity extends AppCompatActivity {
    ImageView logo; //Contiene el logo de la aplicacion
    ImageView ucab; //Contiene el texto del logo de la aplicacion
    ImageView touch;  //Contiene el boton en forma de imagen para iniciar la aplicacion.
    /**
     * Metodo de inicializacion de la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        String datos = pref.getString("cookie","vacio");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Configuraciones de Red: (Coloque aqui la IP y puerto de su servidor)
        //-------------------------------------------------------------------
            Parametros.setServer("http://192.168.1.104"); // Asigno direccion IP a parametros de red.
            Parametros.setPuerto("8080"); // Asigno puerto por el cual el servidor escucha.
        if(datos.equals("vacio")) {
            //-------------------------------------------------------
            logo = (ImageView) findViewById(R.id.logoPresentacion); //Asigno las imagenes
            ucab = (ImageView) findViewById(R.id.ucabPresentacion); // Asino las imagenes
            Animation animation = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, 150);  //Desplazo la imagen hacia abajo
            animation.setDuration(2000); //La duracion la ajusto a 2 segundos.
            animation.setFillAfter(true);
            logo.startAnimation(animation); //Inicio la animacion
            //---------------------------------------------------------
            touch = (ImageView) findViewById(R.id.touch);
            touch.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent inicio = new Intent(PresentacionActivity.this, InicioActivity.class);
                                             startActivity(inicio); // Inicio la ventana de inicio de sesion.
                                         }
                                     }
            );
        }else{
            GestionUsuarios_Controller.descomponerUsuario(datos);
            Intent inicio = new Intent(PresentacionActivity.this, MainActivity.class);
            startActivity(inicio);
        }

    }
}