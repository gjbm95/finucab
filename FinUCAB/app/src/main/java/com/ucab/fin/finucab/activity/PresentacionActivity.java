package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Space;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.webservice.Parametros;

public class PresentacionActivity extends AppCompatActivity {
     ImageView logo; //Contiene el logo de la aplicacion
     ImageView ucab; //Contiene el texto del logo de la aplicacion
     ImageView touch;  //Contiene el boton en forma de imagen para iniciar la aplicacion.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        //Configuraciones de Red: (Coloque aqui la IP y puerto de su servidor)
        //-------------------------------------------------------------------
        Parametros.setServer("http://192.168.1.108");
        Parametros.setPuerto("8080");
        //-------------------------------------------------------
        logo = (ImageView)findViewById(R.id.logoPresentacion); //Asigno las imagenes
        ucab = (ImageView)findViewById(R.id.ucabPresentacion); // Asino las imagenes
        Animation animation = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,150);  //Desplazo la imagen hacia abajo
        animation.setDuration(2000); //La duracion la ajusto a 2 segundos.
        animation.setFillAfter(true);
        logo.startAnimation(animation); //Inicio la animacion
        //--------------------------------------------------------
        Animation animation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,60,0,0); //Dessplazo la imagen hacia la derecha
        animation2.setDuration(2000);  // La duracion la ajusto a 2 segundos.
        animation2.setFillAfter(true);
        ucab.startAnimation(animation2); // Inicio la animacion.
        //---------------------------------------------------------
        touch = (ImageView)findViewById(R.id.touch);
        touch.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Desplazo las imagenes nuevamente.
                                            Animation animation = new TranslateAnimation(0,0,150,0);
                                            animation.setDuration(2000);
                                            animation.setFillAfter(true);
                                            logo.setAnimation(animation);
                                            Animation animation2 = new TranslateAnimation(60,0,0,0);
                                            animation2.setDuration(2000);
                                            animation2.setFillAfter(true);
                                            ucab.startAnimation(animation2);
                                            Intent inicio = new Intent(PresentacionActivity.this,InicioActivity.class);
                                            startActivity(inicio); // Inicio la ventana de inicio de sesion.
                                        }
                                    }
        );



    }
}
