package com.ucab.fin.finucab.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;

import java.io.Serializable;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    EditText AddDescripcionEditText, AgregarcategoriaEditText;
    Button acceptButton;
    Switch switchestado;
    Switch switchtipo;
    private TextView statusTextView;
    private TextView tipoTextView;
    int Resp;


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
        acceptButton = (Button) findViewById(R.id.acceptButton);
        switchestado = (Switch) findViewById(R.id.habilitarSwitch);
        switchtipo = (Switch) findViewById(R.id.tipoSwitch);
        statusTextView = (TextView) findViewById(R.id.estadoTextView);
        tipoTextView = (TextView) findViewById(R.id.TipoTextView);
        AgregarcategoriaEditText =(EditText) findViewById(R.id.AgregarcategoriaEditText);
        AddDescripcionEditText =(EditText) findViewById(R.id.AddDescripcionEditText);
        Categoria_Controller.escribirCategoria = AgregarcategoriaEditText;
        Categoria_Controller.escribirDescripcion = AddDescripcionEditText;

//        SET LISTENERS (Se le asigna la actividad en el cual funcionaran)
        acceptButton.setOnClickListener(this);
        switchtipo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tipoTextView.setText("Ingreso");
                } else {
                    tipoTextView.setText("Egreso");
                }

            }
        });
        switchestado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    statusTextView.setText("Habilitado");
                } else {
                    statusTextView.setText("Deshabilitado");
                }

            }
        });

        //set the switch to ON
        switchtipo.setChecked(true);
        switchestado.setChecked(true);
        //attach a listener to check for changes in state

        cargarData();

    }


    public void cargarData(){

        Serializable s = getIntent().getSerializableExtra("CATEGORIA_DATA");

        if (s != null ) {

            Categoria categoria = (Categoria) s;

            AgregarcategoriaEditText.setText(categoria.getNombre());
            AddDescripcionEditText.setText(categoria.getDescripcion());
            switchestado.setChecked(categoria.isEstaHabilitado());
            switchtipo.setChecked(categoria.isIngreso());

        }

    }
    //Dandole funcionalidades a cada uno de los botones que salen en pantalla:

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {

            //Al accionar, se inicia la actividad que presenta el formulario de registro.
            case R.id.acceptButton:
                try {

                    Categoria_Controller.verificoVacio(AgregarcategoriaEditText);
                    Categoria_Controller.verificoVacio(AddDescripcionEditText);

                    Categoria categoria = new Categoria(AgregarcategoriaEditText.getText().toString(),
                                                        AddDescripcionEditText.getText().toString(),
                                                        switchestado.isChecked(),
                                                        switchtipo.isChecked());

                    Categoria_Controller.registrarCategoria(categoria, this);


                    //this.onBackPressed();

                }catch(CampoVacio_Exception e){
                    e.getCampo().setError(e.getMessage());
                }catch(Exception e){
                    Log.e("Error","No capturado");
                }


        }
    }
}
