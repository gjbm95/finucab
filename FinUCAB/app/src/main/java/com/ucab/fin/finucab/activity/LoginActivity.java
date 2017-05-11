package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ucab.fin.finucab.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    Button signInButton;
    Button signUpButton;

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

//        SET LISTENERS (Se le asigna la actividad en el cual funcionaran)
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

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

    //Dandole funcionalidades a cada uno de los botones que salen en pantalla:
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
             //Al accionar, se inician los procesos de validacion de datos para acceder el perfil de usuario.
            case R.id.signInButton:
                i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                break;
            //Al accionar, se inicia la actividad que presenta el formulario de registro.
            case R.id.signUpButton:
                i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
        }

    }
}
