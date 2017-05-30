package com.ucab.fin.finucab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.exceptions.CampoVacio_Exception;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;


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

public class AgregarCategoria_Fragment extends Fragment implements View.OnClickListener, ResponseWebServiceInterface {

    MainActivity parentActivity;
    Categoria categoria;

    public static EditText AddDescripcionEditText; //caja de texto para almacenar la descripcion de la categoria
    public static EditText AgregarcategoriaEditText; //caja de texto para almacenar la categoria
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        isModificando = categoria != null;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_agregar_categoria, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Registro Categoria");

        Categoria_Controller.initManejador(parentActivity,this);

        //------------------------------------------------------------------------------------------
//        BIND VIEES (Se extraen los objetos asociados a los botones en pantalla)
        acceptButton = (Button) rootView.findViewById(R.id.acceptButton);
        switchestado = (Switch) rootView.findViewById(R.id.habilitarSwitch);
        switchtipo = (Switch) rootView.findViewById(R.id.tipoSwitch);
        statusTextView = (TextView) rootView.findViewById(R.id.estadoTextView);
        tipoTextView = (TextView) rootView.findViewById(R.id.TipoTextView);
        AgregarcategoriaEditText =(EditText) rootView.findViewById(R.id.AgregarcategoriaEditText);
        AddDescripcionEditText =(EditText) rootView.findViewById(R.id.AddDescripcionEditText);

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

        return rootView;

    }

    /**
     * Asignar nuevo a la categoria
     * @param categoria
     */
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }



    /**
     * Metodo para llenar la data y mostrar Categoria, Descripcion
     * Estado y Tipo
     *
     */
    public void cargarData(){


        if ( isModificando ) {

            parentActivity.getSupportActionBar().setTitle("Modificar Categoria");
            AgregarcategoriaEditText.setText(categoria.getNombre()); //obtener el nombre de la categoria
            AddDescripcionEditText.setText(categoria.getDescripcion()); //obtener la descripcion
            switchestado.setChecked(categoria.isEstaHabilitado());//obtener el estado de la categoria
            switchtipo.setChecked(categoria.isIngreso()); //obtener el tipo de categoria

        }

    }


    /**
     * Dandole funcionalidades a cada uno de los botones que salen en pantalla:
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {

            //Colocando acciones al boton de "Aceptar":
            case R.id.acceptButton:
                try
                {

                    Categoria_Controller.verificoVacio(AgregarcategoriaEditText);
                    Categoria_Controller.verificoVacio(AddDescripcionEditText);

                    Categoria categoria = new Categoria(AgregarcategoriaEditText.getText().toString(),
                            AddDescripcionEditText.getText().toString(),
                            switchestado.isChecked(),
                            switchtipo.isChecked());

                    if (isModificando){
                        categoria.setIdcategoria( categoria.getIdcategoria());
                        Categoria_Controller.modificarCategoria(categoria);
                    }else {
                        Categoria_Controller.registrarCategoria(categoria);
                    }

                }catch(CampoVacio_Exception e){
                    e.getCampo().setError(e.getMessage());
                }catch(Exception e){
                    Log.e("Error","No capturado");
                }


        }
    }


    /**
     * Response WebService
     * se llena la lista con las consultas provenientes del WebService con la BD
     * @param response Respuesta del WebService
     */
    @Override
    public void obtuvoCorrectamente(Object response){

        Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();

        if(Categoria_Controller.getCasoRequest() == 1 ){
            Categoria_Controller.resetCasoRequest();
            parentActivity.onBackPressed();
        }else{

            Categoria_Controller.resetCasoRequest();
        }


    }
    /**
     * Response WebService
     * se llena la lista con las consultas provenientes del WebService con la BD
     * @param response Error del WebService
     */
    @Override
    public void noObtuvoCorrectamente(Object response){

    }

}
