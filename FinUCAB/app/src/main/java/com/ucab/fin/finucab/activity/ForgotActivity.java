package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.fragment.ContrasenaFragment;
import com.ucab.fin.finucab.fragment.RespuestaFragment;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {

    private Button siguiente;
    private Button cancelar;
    private int conteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        activarPaso(1);
        conteo=1;
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


    //Agrego un menu Overflow al Action Bar:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;

    }


    //Se le coloca acciones a las funcionalidades que ofrece el Menu overflow del action bar.
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

         }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.siguienteButton:
                if(conteo==1 && GestionUsuarios_Controller.validacionRespuesta()==0){
                    activarPaso(2);
                    conteo++;
            }else{
                //SE DEBE ESCRIBIR LA NUEVA CONTRASEÑA EN LA BASE DE DATOS
                    if(conteo ==2 && GestionUsuarios_Controller.validacionContrasenas()==0) {
                        Intent home = new Intent(ForgotActivity.this, LoginActivity.class);
                        startActivity(home);
                        GestionUsuarios_Controller.resetarVariables();
                        finish();
                    }
            }
                break;
            case R.id.cancelButton:
                Intent home = new Intent(ForgotActivity.this, LoginActivity.class);
                startActivity(home);
                GestionUsuarios_Controller.resetarVariables();
                finish();
                break;
        }
    }
}
