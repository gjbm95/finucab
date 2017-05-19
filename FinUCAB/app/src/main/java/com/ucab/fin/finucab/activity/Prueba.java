package com.ucab.fin.finucab.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;
import com.ucab.fin.finucab.webservice.Parametros;

import org.json.JSONException;
import org.json.JSONObject;

public class Prueba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        Button botones = (Button)findViewById(R.id.botoncartel);
        TextView cartel3 = (TextView)findViewById(R.id.cartel);
        try {
            JSONObject json = new JSONObject(Parametros.respuesta);
            cartel3.setText((String)json.get("nombre"));

        } catch (JSONException e) {
            e.printStackTrace();
        }



        final TextView cartel = (TextView)findViewById(R.id.cartel);
        botones.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Presupuesto_Controller.cualquiercosa(Prueba.this,cartel);
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(Parametros.respuesta);
        TextView cartel = (TextView)findViewById(R.id.cartel);
        JSONObject json = null;
        try {
            json = new JSONObject(Parametros.respuesta);
            cartel.setText((String)json.get("Nombre"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    }

