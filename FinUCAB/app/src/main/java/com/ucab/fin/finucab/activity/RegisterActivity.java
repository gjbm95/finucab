package com.ucab.fin.finucab.activity;

import android.content.Intent;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.fragment.DatosCuentaFragment;
import com.ucab.fin.finucab.fragment.DatosPersonalesFragment;
import com.ucab.fin.finucab.fragment.DatosSeguridadFragment;

public class RegisterActivity extends AppCompatActivity {


    private Button siguiente;
    private Button anterior;
    private ImageView posicionEtapa;
    private int conteo;


    /**Creacion de la actividad:
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
          inicioOnboarding();
          activarPaso(1);

        //Inicio el contador de etapas:
        //---------------------------------------------------------------------------------
        conteo=1; //Esta variable se encarga de indicar que paso del registro de usuario se debe ejecutar
        //Colocando el icono en la parte superior izquierda de la pantalla:
        //---------------------------------------------------------------------------------
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");

        //Botones de Funcionamiento:
        //---------------------------------------------------------------------------------
        //Colocando acciones al boton de "Siguiente":
        siguiente = (Button) findViewById(R.id.nextButton);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conteo<3)
                    conteo++;
                else
                    conteo =1;
                //Realizando el cambio de formulario:
                if (conteo==1) {
                    if (controlValidacion(conteo))
                    {
                        inicioOnboarding();
                        activarPaso(conteo);
                    }
                }
                if (conteo==2) {
                    if (controlValidacion(conteo))
                    {
                        inicioOnboarding();
                        activarPaso(conteo);
                    }
                }
                if (conteo==3) {
                    if (controlValidacion(conteo))
                    {
                        inicioOnboarding();
                        activarPaso(conteo);
                    }
                }
            }
              }
        );


        /**Colocando acciones al boton de "Cancelar":
         *
         */
        //----------------------------------------------------------------------------------
        anterior = (Button) findViewById(R.id.cancelButton);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                 //Cambio la actividad de vuelta al Home Inicio de Sesion.
                if (anterior.getText().equals("CANCELAR")||anterior.getText().equals("Cancelar")) {
                    Intent home = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(home);
                    GestionUsuarios_Controller.resetarVariables();
                    finish();
                }
                if (anterior.getText().equals("ANTERIOR")) {
                    conteo--;
                    if(conteo!=0) {
                        activarPaso(conteo);
                    }else{
                        conteo++;
                        activarPaso(conteo);
                    }

                }
               }
                }
        );

    }

    /**Quito la visibilidad a los indicadores de Etapa:
     *
     */
    //---------------------------------------------------------------------------------
    public void inicioOnboarding(){

        posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
        posicionEtapa.setImageResource(R.mipmap.onboarding);

    }

    /**Coloco la visibilidad a un indicador y formualario en la Etapa:
     *
     * @param indicador
     */
    //---------------------------------------------------------------------------------
    public void activarPaso(int indicador){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left);
        //Muestro el formulario de registro para datos personales.
        if(indicador ==1) {
            //Ajusto el titulo de los botones.
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText("CANCELAR");
            siguiente = (Button) findViewById(R.id.nextButton);
            siguiente.setText("SIGUIENTE");
            //Modifico la posicion del grafico de onBoarding.
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding1);
            DatosPersonalesFragment fragment1 = new DatosPersonalesFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
        }
        //Muestro el formulario de registro para datos de la cuenta.
        if (indicador==2){
            //Ajusto el titulo de los botones.
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText("ANTERIOR");
            siguiente = (Button) findViewById(R.id.nextButton);
            siguiente.setText("SIGUIENTE");
            //Modifico la posicion del grafico de onBoarding.
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding2);
            DatosCuentaFragment fragment1 = new DatosCuentaFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
        }
        //Muestro el formulario de registro para datos de la seguridad de la cuenta.
        if(indicador==3){
            //Ajusto el titulo de los botones.
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText("ANTERIOR");
            siguiente = (Button) findViewById(R.id.nextButton);
            siguiente.setText("LISTO");
            //Modifico la posicion del grafico de onBoarding.
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding3);
            DatosSeguridadFragment fragment1 = new DatosSeguridadFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
        }

    }

    /**Se actualiza la variable conteo, con el fin de que se permita el desplazamiento entre formularios. 
     *
     * @param conteo
     * @return
     */
    public boolean controlValidacion(int conteo){
        if((conteo)==2){
            if(GestionUsuarios_Controller.validacionEtapaDatos()==1)
            {
                this.conteo--;
                return false;
            }
        }else if ((conteo)==3){
            if(GestionUsuarios_Controller.validacionEtapaCuenta()==1)
            {
                this.conteo--;
                return false;
            }

        } else if ((conteo)==1){
            if(GestionUsuarios_Controller.validacionEtapaSeguridad()==1)
            {
                this.conteo--;
                return false;
            }
        }
        return true;
    }

    /**Agrego un menu Overflow al Action Bar:
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }


    /**Se le coloca acciones a las funcionalidades que ofrece el Menu overflow del action bar.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Se finaliza la aplicacion.
            case R.id.exit:
                System.exit(0);
                return true;
            //Se accede a la funcionalidades de configuracion.
            case R.id.setting:
                //Aqui se llama a las opciones de Configuracion
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
