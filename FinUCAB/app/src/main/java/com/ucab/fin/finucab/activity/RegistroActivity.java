package com.ucab.fin.finucab.activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.domain.Usuario;
import com.ucab.fin.finucab.exceptions.UsuarioInvalido_Exception;
import com.ucab.fin.finucab.fragment.CuentaFragment;
import com.ucab.fin.finucab.fragment.DatosCuentaFragment;
import com.ucab.fin.finucab.fragment.DatosPersonalesFragment;
import com.ucab.fin.finucab.fragment.DatosSeguridadFragment;
import com.ucab.fin.finucab.webservice.Parametros;

import static com.ucab.fin.finucab.controllers.GestionUsuarios_Controller.verificoUsuario;

public class RegistroActivity extends AppCompatActivity {


    private Button siguiente;
    private Button anterior;
    private Button comenzar;
    private ImageView posicionEtapa;
    private int conteo;


    /**
     * Creacion de la actividad:
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inicioOnboarding();
        activarPaso(1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Inicio el contador de etapas:
        //---------------------------------------------------------------------------------
        conteo = 1; //Esta variable se encarga de indicar que paso del registro de usuario se debe ejecutar
        //Colocando el icono en la parte superior izquierda de la pantalla:
        //---------------------------------------------------------------------------------
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logoh);
        actionBar.setTitle("");

        //Botones de Funcionamiento:
        //---------------------------------------------------------------------------------
        //Colocando acciones al boton para iniciar la cuenta:
        comenzar = (Button) findViewById(R.id.comenzarRE);
        comenzar.setVisibility(View.INVISIBLE);
        comenzar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            GestionUsuarios_Controller.inicioSesion(GestionUsuarios_Controller.usuario,
                                                    GestionUsuarios_Controller.contrasena1,RegistroActivity.this);
                                        }
                                    }
        );
        //Colocando acciones al boton de "Siguiente":
        siguiente = (Button) findViewById(R.id.nextButton);
        siguiente.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (conteo < 4)
                                                 conteo++;
                                             else
                                                 conteo = 1;
                                             //Realizando el cambio de formulario:
                                             if (conteo == 1) {
                                                 if (controlValidacion(conteo)) {
                                                     inicioOnboarding();
                                                     activarPaso(conteo);
                                                 }
                                             }
                                             if (conteo == 2) {
                                                 if (controlValidacion(conteo)) {
                                                     inicioOnboarding();
                                                     activarPaso(conteo);
                                                 }
                                             }
                                             if (conteo == 3) {
                                                 if (controlValidacion(conteo)) {
                                                     inicioOnboarding();
                                                     activarPaso(conteo);
                                                 }
                                             }
                                             if (conteo == 4) {
                                                 if (controlValidacion(conteo)) {
                                                     Usuario usuario = new Usuario();
                                                     usuario.setNombre(GestionUsuarios_Controller.nombre.getText().toString());
                                                     usuario.setApellido(GestionUsuarios_Controller.apellido.getText().toString());
                                                     usuario.setCorreo(GestionUsuarios_Controller.correo.getText().toString());
                                                     usuario.setUsuario(GestionUsuarios_Controller.usuario.getText().toString());
                                                     usuario.setContrasena(Integer.toString(GestionUsuarios_Controller.encriptarDatos
                                                             (GestionUsuarios_Controller.contrasena1.getText().toString())));
                                                     usuario.setPregunta(GestionUsuarios_Controller.pregunta.getText().toString());
                                                     usuario.setRespuesta(Integer.toString(GestionUsuarios_Controller.encriptarDatos
                                                             (GestionUsuarios_Controller.respuesta.getText().toString())));
                                                     GestionUsuarios_Controller.registrarUsuario(usuario, RegistroActivity.this);
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
                                            if (anterior.getText().equals("CANCELAR") || anterior.getText().equals("Cancelar")) {
                                                Intent home = new Intent(RegistroActivity.this, InicioActivity.class);
                                                startActivity(home);
                                                GestionUsuarios_Controller.resetarVariables();
                                                Parametros.reset();
                                                finish();
                                            }
                                            if (anterior.getText().equals("ANTERIOR")) {
                                                conteo--;
                                                if (conteo != 0) {
                                                    activarPaso(conteo);
                                                } else {
                                                    conteo++;
                                                    activarPaso(conteo);
                                                }

                                            }
                                        }
                                    }
        );


    }

    /**
     * Quito la visibilidad a los indicadores de Etapa:
     */
    //---------------------------------------------------------------------------------
    public void inicioOnboarding() {

        posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
        posicionEtapa.setImageResource(R.mipmap.onboarding);

    }

    /**
     * Coloco la visibilidad a un indicador y formualario en la Etapa:
     *
     * @param indicador
     */
    //---------------------------------------------------------------------------------
    public void activarPaso(int indicador) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Muestro el formulario de registro para datos personales.
        if (indicador == 1) {
            //Ajusto el titulo de los botones.
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText(getString(R.string.cancelar));
            siguiente = (Button) findViewById(R.id.nextButton);
            siguiente.setText(getString(R.string.siguiente2));
            //Modifico la posicion del grafico de onBoarding.
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding1);
            DatosPersonalesFragment fragment1 = new DatosPersonalesFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
            GestionUsuarios_Controller.pasoRegistro = 1;
        }
        //Muestro el formulario de registro para datos de la cuenta.
        if (indicador == 2) {
            //Ajusto el titulo de los botones.
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText(getString(R.string.anterior));
            siguiente = (Button) findViewById(R.id.nextButton);
            siguiente.setText(getString(R.string.siguiente2));
            //Modifico la posicion del grafico de onBoarding.
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding2);
            DatosCuentaFragment fragment1 = new DatosCuentaFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
            GestionUsuarios_Controller.pasoRegistroCuenta = true;
            GestionUsuarios_Controller.pasoRegistro = 2;
        }
        //Muestro el formulario de registro para datos de la seguridad de la cuenta.
        if (indicador == 3) {
            //Ajusto el titulo de los botones.
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            anterior = (Button) findViewById(R.id.cancelButton);
            anterior.setText(getString(R.string.anterior));
            siguiente = (Button) findViewById(R.id.nextButton);
            siguiente.setText(getString(R.string.listo));
            //Modifico la posicion del grafico de onBoarding.
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding3);
            DatosSeguridadFragment fragment1 = new DatosSeguridadFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1).commit();
            GestionUsuarios_Controller.pasoRegistro = 3;
        }
        //Muestro la cuenta creada
        if (indicador == 4) {
            //Ajusto el titulo de los botones.
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
            anterior.setVisibility(View.INVISIBLE);
            siguiente.setVisibility(View.INVISIBLE);
            comenzar.setVisibility(View.VISIBLE);
            //Modifico la posicion del grafico de onBoarding.
            posicionEtapa = (ImageView) findViewById(R.id.onboardindImageView);
            posicionEtapa.setImageResource(R.mipmap.onboarding4);
            CuentaFragment fragment1 = new CuentaFragment();
            fragmentTransaction.replace(R.id.fragment, fragment1);
            //------------Elimino los fragmentos sin uso-------------------------------
            DatosSeguridadFragment fragmentc = new DatosSeguridadFragment();
            DatosCuentaFragment fragmentb = new DatosCuentaFragment();
            DatosPersonalesFragment fragmenta = new DatosPersonalesFragment();
            fragmentTransaction.remove(fragmenta);
            fragmentTransaction.remove(fragmentb);
            fragmentTransaction.remove(fragmentc).commit();
            GestionUsuarios_Controller.pasoRegistro = 4;
        }

    }

    /**
     * Se actualiza la variable conteo, con el fin de que se permita el desplazamiento entre formularios.
     *
     * @param conteo
     * @return
     */
    public boolean controlValidacion(int conteo) {
        if ((conteo) == 2) {
            if (GestionUsuarios_Controller.validacionEtapaDatos() == 1) {
                this.conteo--;
                return false;
            }
        } else if ((conteo) == 3) {
            if (GestionUsuarios_Controller.validacionEtapaCuenta() == 1) {
                this.conteo--;
                return false;
            }else {
                try {
                    verificoUsuario(RegistroActivity.this,GestionUsuarios_Controller.usuario);
                } catch (UsuarioInvalido_Exception e) {
                    e.printStackTrace();
                }

            }

        } else if ((conteo) == 4) {
            if (GestionUsuarios_Controller.validacionEtapaSeguridad() == 1) {
                this.conteo--;
                return false;
            }
        }
        return true;
    }


    /**
     * Agrego un menu Overflow al Action Bar:
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }


    /**
     * Se le coloca acciones a las funcionalidades que ofrece el Menu overflow del action bar.
     *
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

    @Override
    protected void onResume() {
        super.onResume();
        if (Parametros.getRespuesta()!=null) {
            if (validarUsuario(Parametros.getRespuesta()) && GestionUsuarios_Controller.pasoRegistroCuenta)
                System.out.println("Validacion de Etapa 2 de Registro");
            else if (validarCuenta(Parametros.getRespuesta()))
                System.out.println("Validacion de Etapa 3 de Registro");
            else if (Parametros.getRespuesta().contains("iniciosesion"))
                iniciarSesion();
        }

    }

    /**
     *  Metodo encargado de recibir los datos luego de iniciar sesion, son el fin de que se mantengan en el
     *  telefono .
     */
    private boolean iniciarSesion(){
        String[] datos = Parametros.getRespuesta().split(":-:");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("cookie",datos[0]);
        editor.commit();
        GestionUsuarios_Controller.descomponerUsuario(datos[0]);
        Intent iniciar = new Intent(RegistroActivity.this, MainActivity.class);
        startActivity(iniciar);
        GestionUsuarios_Controller.resetarVariables();
        Parametros.reset();
        finish();
        return true;
    }

    /**
     *  Metodo que se encarga de validar datos del usuario que provienen del servidor.
     *  @param mensaje mensaje qued proviene del web service
     */
    private boolean validarUsuario(String mensaje){
        if(mensaje!=null) {
            if (mensaje.equals("Error")&&(GestionUsuarios_Controller.pasoRegistro+1)==2) {
                conteo=GestionUsuarios_Controller.pasoRegistro+1;
                activarPaso(conteo);
                GestionUsuarios_Controller.pasoRegistro = conteo-1;
                mensajeError(getString(R.string.conexion));
                return true;
            }else if (mensaje.equals("No Disponible")){
                activarPaso(2);
                conteo++;
                mensajeError(getString(R.string.usuarioenuso));
                return true;
            }else if (mensaje.equals("Usuario Disponible")){
                if (conteo!=3)
                    conteo=3;
                GestionUsuarios_Controller.pasoRegistroCuenta = false;
                activarPaso(3);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que se encarga de activa validaciones de los campos relacionados con la cuentas de usuario.
     * @param mensaje mensaje que llega del web service.
     * @return
     */

    private boolean validarCuenta (String mensaje){
        if (mensaje!=null) {
            if (mensaje.equals("Registro exitoso")) {
                activarPaso(4);
                return true;
            } else if (mensaje.equals("Error") && (GestionUsuarios_Controller.pasoRegistro + 1) == 3) {
                conteo = GestionUsuarios_Controller.pasoRegistro + 1;
                activarPaso(conteo);
                mensajeError(getString(R.string.conexion));
                return true;
            }
        }
        return false;
    }


    /**
     * Meotodo que se encarga de mostrar un mensaje de error.
     * @param mensaje
     */
    private void mensajeError(String mensaje){
        Toast.makeText(RegistroActivity.this,mensaje,Toast.LENGTH_LONG);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(mensaje);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}
