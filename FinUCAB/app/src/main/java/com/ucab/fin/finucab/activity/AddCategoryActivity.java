package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.ucab.fin.finucab.R;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener  {

    Button signInButton;
    Button signUpButton;
    Switch switchestado;
    Switch switchtipo;
    private TextView switchStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agregar_categoria);

        //Colocando el icono en la parte superior izquierda:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");
        //------------------------------------------------------------------------------------------
//        BIND VIEES (Se extraen los objetos asociados a los botones en pantalla)

        signUpButton = (Button) findViewById(R.id.acceptButton);
        switchestado = (Switch) findViewById(R.id.habilitarSwitch);

        switchtipo = (Switch) findViewById(R.id.tipoSwitch);
        switchStatus = (TextView) findViewById(R.id.estadoTextView);



//        SET LISTENERS (Se le asigna la actividad en el cual funcionaran)
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);


        //set the switch to ON

        switchtipo.setChecked(true);
        switchestado.setChecked(true);
        //attach a listener to check for changes in state


    }




    //Dandole funcionalidades a cada uno de los botones que salen en pantalla:
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){


            //Al accionar, se inicia la actividad que presenta el formulario de registro.
            case R.id.acceptButton:
                i = new Intent(AddCategoryActivity.this,MainActivity.class);
                startActivity(i);
                break;
        }

    }
}
