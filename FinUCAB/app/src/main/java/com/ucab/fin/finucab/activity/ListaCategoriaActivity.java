package com.ucab.fin.finucab.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.ucab.fin.finucab.R;

    public class ListaCategoriaActivity extends AppCompatActivity {

        EditText AddDescripcionEditText, AgregarcategoriaEditText;
        Switch switchestado;
        private TextView statusTextView;

        int Resp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_lista_categorias);

            //Colocando el icono en la parte superior izquierda:
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.logoh);
            actionBar.setTitle("");
            //------------------------------------------------------------------------------------------
//        BIND VIEES (Se extraen los objetos asociados a los botones en pantalla)
            switchestado.setTextOn("Habilitado"); // displayed text of the Switch whenever it is in checked or on state
            switchestado.setTextOff("Deshabilitado");

            switchestado = (Switch) findViewById(R.id.switch2);
            statusTextView = (TextView) findViewById(R.id.estadoTextView);

//
            //Dandole funcionalidades a cada uno de los botones que salen en pantalla:

        }
    }


