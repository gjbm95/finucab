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


            switchestado = (Switch) findViewById(R.id.switchestado);
            statusTextView = (TextView) findViewById(R.id.estadoTextView);


//
            switchestado.setChecked(true);
        }
    }


