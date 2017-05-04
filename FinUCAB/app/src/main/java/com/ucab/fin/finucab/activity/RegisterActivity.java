package com.ucab.fin.finucab.activity;

import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.fragment.BudgetFragment;
import com.ucab.fin.finucab.fragment.DatosPersonalesFragment;
import com.ucab.fin.finucab.fragment.GananciasFragment;
import com.ucab.fin.finucab.fragment.GastosFragment;
import com.ucab.fin.finucab.fragment.RegisterSwapFragment;
import com.ucab.fin.finucab.fragment.TotalFragment;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {


    private Button siguiente;
    private Button anterior;
    private int conteo=0;


    //Creacion de la actividad:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //Inicio el contador de etapas:
        //---------------------------------------------------------------------------------
        conteo=0;
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
                if (conteo<=3)
                    conteo++;
                else
                    conteo =1;

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
                 Intent home = new Intent (RegisterActivity.this,LoginActivity.class);
                 startActivity(home);
                 finish();
               }
                }
        );

    }







}
