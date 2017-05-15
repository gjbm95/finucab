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

public class BeginActivity extends AppCompatActivity {
     ImageView logo;
     ImageView ucab;
     ImageView touch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        logo = (ImageView)findViewById(R.id.logoPresentacion);
        ucab = (ImageView)findViewById(R.id.ucabPresentacion);
        Animation animation = new TranslateAnimation(0,0,Animation.RELATIVE_TO_SELF,150);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        logo.startAnimation(animation);
        //--------------------------------------------------------
        Animation animation2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,60,0,0);
        animation2.setDuration(2000);
        animation2.setFillAfter(true);
        ucab.startAnimation(animation2);
        //---------------------------------------------------------
        touch = (ImageView)findViewById(R.id.touch);
        touch.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Animation animation = new TranslateAnimation(0,0,150,0);
                                            animation.setDuration(2000);
                                            animation.setFillAfter(true);
                                            logo.setAnimation(animation);
                                            Animation animation2 = new TranslateAnimation(60,0,0,0);
                                            animation2.setDuration(2000);
                                            animation2.setFillAfter(true);
                                            ucab.startAnimation(animation2);
                                            Intent inicio = new Intent(BeginActivity.this,LoginActivity.class);
                                            startActivity(inicio);
                                        }
                                    }
        );



    }
}
