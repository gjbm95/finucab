package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ucab.fin.finucab.R;

public class AddPaymentActivity extends AppCompatActivity implements View.OnClickListener  {

    Button signInButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agregar_pago);

        //Colocando el icono en la parte superior izquierda:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");
        //------------------------------------------------------------------------------------------
//        BIND VIEES (Se extraen los objetos asociados a los botones en pantalla)
        signInButton = (Button) findViewById(R.id.backButton);
        signUpButton = (Button) findViewById(R.id.acceptButton);

//        SET LISTENERS (Se le asigna la actividad en el cual funcionaran)
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    //Dandole funcionalidades a cada uno de los botones que salen en pantalla:
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){

            case R.id.backButton:
                i = new Intent(AddPaymentActivity.this, MainActivity.class);
                startActivity(i);
                break;
            //Al accionar, se inicia la actividad que presenta el formulario de registro.
            case R.id.acceptButton:
                i = new Intent(AddPaymentActivity.this, RegistroActivity.class);
                startActivity(i);
                break;
        }

    }
}
