package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ucab.fin.finucab.R;

public class AddCategoryActivity extends AppCompatActivity  {
    private Button anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agregar_categoria);

        //Colocando acciones al boton de "Cancelar":
        //----------------------------------------------------------------------------------
        anterior = (Button) findViewById(R.id.backButton);
        anterior.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Cambio la actividad de vuelta al Home Inicio de Sesion.
                                            if (anterior.getText().equals("Volver")) {
                                                Intent home = new Intent(AddCategoryActivity.this, LoginActivity.class);
                                                startActivity(home);
                                               finish();
                                            }

                                        }
                                    }
        );

    }



}
