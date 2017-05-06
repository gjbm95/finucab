package com.ucab.fin.finucab.activity;

import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.fragment.BudgetFragment;
import com.ucab.fin.finucab.fragment.DatosCuentaFragment;
import com.ucab.fin.finucab.fragment.DatosPersonalesFragment;
import com.ucab.fin.finucab.fragment.DatosSeguridadFragment;
import com.ucab.fin.finucab.fragment.GananciasFragment;
import com.ucab.fin.finucab.fragment.GastosFragment;
import com.ucab.fin.finucab.fragment.RegisterSwapFragment;
import com.ucab.fin.finucab.fragment.TotalFragment;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {


    private Button siguiente;
    private Button anterior;
    private ImageView posicionEtapa;
    private int conteo;


    //Creacion de la actividad:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
          inicioOnboarding();
          activarPaso(1);

        //Inicio el contador de etapas:
        //---------------------------------------------------------------------------------
        conteo=1;
        //Colocando el icono en la parte superior izquierda de la pantalla:
        //---------------------------------------------------------------------------------
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");

        //Botones de Funcionamiento:
        //---------------------------------------------------------------------------------
        //Colocando acciones al boton de "Siguiente":
        siguiente = (Button) findViewById(R.id.nextButton);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conteo<3)
                    conteo++;
                else
                    conteo =1;
                //Realizando el cambio de formulario:
                if (conteo==1) {
                    inicioOnboarding();
                    activarPaso(1);
                }
                if (conteo==2) {
                    inicioOnboarding();
                    activarPaso(2);
                }
                if (conteo==3) {
                    inicioOnboarding();
                    activarPaso(3);
                }
            }
              }
        );


        //Colocando acciones al boton de "Cancelar":
        //----------------------------------------------------------------------------------
        anterior = (Button) findViewById(R.id.cancelButton);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 //Cambio la actividad de vuelta al Home Inicio de Sesion.
                if (anterior.getText().equals("CANCELAR")||anterior.getText().equals("Cancelar")) {
                    Intent home = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(home);
                    finish();
                }
                if (anterior.getText().equals("ANTERIOR")) {
                    conteo--;
                    if(conteo!=0)
                    activarPaso(conteo);
                    else{
                        conteo++;
                        activarPaso(conteo);
                    }

                }
               }
                }
        );

    }

    //Quito la visibilidad a los indicadores de Etapa:
    //---------------------------------------------------------------------------------
    public void inicioOnboarding(){

        posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
        posicionEtapa.setImageResource(R.mipmap.onboarding);

    }

    //Coloco la visibilidad a un indicador y formualario en la Etapa:
    //---------------------------------------------------------------------------------
    public void activarPaso(int indicador){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(indicador ==1) {
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText("CANCELAR");
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding1);
            DatosPersonalesFragment fragment1 = new DatosPersonalesFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
        }
        if (indicador==2){
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText("ANTERIOR");
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding2);
            DatosCuentaFragment fragment1 = new DatosCuentaFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
        }
        if(indicador==3){
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText("ANTERIOR");
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding3);
            DatosSeguridadFragment fragment1 = new DatosSeguridadFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
        }

    }





}
