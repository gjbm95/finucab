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

/**
 *Modulo 4 - Modulo de  Gestion de Categorias
 *Desarrolladores:
 *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
 *Descripción de la clase:
 * Esta clase se encarga de gestionar la actividad de los botones que se encuentran en nuestras
 * diferentes vistas
 * . Y de inicializar
 * parametros de los botones para la aplicacion.
 */

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    EditText AddDescripcionEditText; //caja de texto para almacenar la descripcion de la categoria
    EditText AgregarcategoriaEditText; //caja de texto para almacenar la categoria
    Button acceptButton; //Boton para activar el guardado de categoria
    Switch switchestado; //Switch para cambiar las opciones Habilitado y Deshabilitado
    Switch switchtipo; //Switch para cambiar las opciones Ingreso y Egreso
    private TextView statusTextView; //Texto que cambia al mover el Switchestado
    private TextView tipoTextView; //Texto que cambia al mover el Switchtipo
    private boolean isModificando; //Variable tipo bool para entrar al metodo cargardata


    /**
     * Metodo de inicializacion de la actividad
     * @param savedInstanceState
     */
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_agregar_categoria);
        //Colocando el icono en la parte superior izquierda:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");

        Categoria_Controller.initManejador(this,null);
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
        /**Colocando opciones al switch de nombre switchtipo
         *
         */
        switchtipo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    tipoTextView.setText("Ingreso");
                } else {
                    tipoTextView.setText("Egreso");
                }

            }
        });
        /**Colocando opciones al switch de nombre switchtEstado
         *
         */
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

    /**
     * Metodo para llenar la data y mostrar Categoria, Descripcion
     * Estado y Tipo
     *
     */
    public void cargarData(){

        Serializable s = getIntent().getSerializableExtra("CATEGORIA_DATA");
        isModificando = s != null;

        if ( isModificando ) {

            Categoria categoria = (Categoria) s;

            AgregarcategoriaEditText.setText(categoria.getNombre()); //obtener el nombre de la categoria
            AddDescripcionEditText.setText(categoria.getDescripcion()); //obtener la descripcion
            switchestado.setChecked(categoria.isEstaHabilitado());//obtener el estado de la categoria
            switchtipo.setChecked(categoria.isIngreso()); //obtener el tipo de categoria

        }

    }
    //Dandole funcionalidades a cada uno de los botones que salen en pantalla:

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {

            /**Colocando acciones al boton de "Aceptar":
             *
             */
            case R.id.acceptButton:
                try {

                    Categoria_Controller.verificoVacio(AgregarcategoriaEditText);
                    Categoria_Controller.verificoVacio(AddDescripcionEditText);

                    Categoria categoria = new Categoria(AgregarcategoriaEditText.getText().toString(),
                            AddDescripcionEditText.getText().toString(),
                            switchestado.isChecked(),
                            switchtipo.isChecked());

                    if (isModificando){
                        categoria.setIdcategoria( ((Categoria) getIntent().getSerializableExtra("CATEGORIA_DATA")).getIdcategoria());
                        Categoria_Controller.modificarCategoria(categoria);
                    }else {
                        Categoria_Controller.registrarCategoria(categoria);
                    }
                    //this.onBackPressed();

                }catch(CampoVacio_Exception e){
                    e.getCampo().setError(e.getMessage());
                }catch(Exception e){
                    Log.e("Error","No capturado");
                }


        }
    }
}
