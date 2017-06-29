package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContentResolverCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Banco_Controller;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.domain.Cuenta_Bancaria;
import com.ucab.fin.finucab.domain.Top;
import com.ucab.fin.finucab.registros.Registro;
import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.Recepcion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;

public class HomeFragment extends Fragment  implements ResponseWebServiceInterface {

    private View parentView;
    private TextView nombrecompleto;
    private MainActivity parentActivity;
    private ImageButton bancos;
    private ImageButton tarjetas;
    private TextView saldoActual;
    private TextView saldoTarjetas;
    private TextView egresoTextView;
    private TextView ingresoTextView;
    private ProgressBar ingresosBar;
    private ProgressBar egresosBar;
    private TextView fechaPagoP1,fechaPagoP2,fechaPagoP3;
    private TextView textoPagoP1,textoPagoP2,textoPagoP3;
    private TextView fechaPagoR1,fechaPagoR2,fechaPagoR3;
    private TextView textoPagoR1,textoPagoR2,textoPagoR3;
    private TextView fechaPres1,fechaPres2,fechaPres3;
    private TextView textoPres1,textoPres2,textoPres3;

    @Override  //METODO QUE LLAMA A OTROS FRAGMENTS(PUEDE SER USADO TANTO EN FRAGMENTS COMO EN ACTIVITY)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Home");
        nombrecompleto = (TextView)view.findViewById(R.id.nombrecompleto);
        nombrecompleto.setText(ControlDatos.getUsuario().getNombre() + " "
        +ControlDatos.getUsuario().getApellido());
        saldoActual = (TextView) view.findViewById(R.id.saldoactual);
        saldoTarjetas = (TextView) view.findViewById(R.id.saldotarjetas);
        ingresoTextView = (TextView) view.findViewById(R.id.ingresoTextView);
        egresoTextView = (TextView) view.findViewById(R.id.egresoTextView);
        ingresosBar = (ProgressBar) view.findViewById(R.id.ingresosBar);
        egresosBar = (ProgressBar) view.findViewById(R.id.egresosBar);
        
        fechaPagoP1 = (TextView) view.findViewById(R.id.fechaPagoP1);
        fechaPagoP2 = (TextView) view.findViewById(R.id.fechaPagoP2);
        fechaPagoP3 = (TextView) view.findViewById(R.id.fechaPagoP3);
        textoPagoP1 = (TextView) view.findViewById(R.id.textoPagoP1);
        textoPagoP2 = (TextView) view.findViewById(R.id.textoPagoP2);
        textoPagoP3 = (TextView) view.findViewById(R.id.textoPagoP3);

        fechaPagoR1 = (TextView) view.findViewById(R.id.fechaPagoR1);
        fechaPagoR2 = (TextView) view.findViewById(R.id.fechaPagoR2);
        fechaPagoR3 = (TextView) view.findViewById(R.id.fechaPagoR3);
        textoPagoR1 = (TextView) view.findViewById(R.id.textoPagoR1);
        textoPagoR2 = (TextView) view.findViewById(R.id.textoPagoR2);
        textoPagoR3 = (TextView) view.findViewById(R.id.textoPagoR3);

        fechaPres1 = (TextView) view.findViewById(R.id.fechaPres1);
        fechaPres2 = (TextView) view.findViewById(R.id.fechaPres2);
        fechaPres3 = (TextView) view.findViewById(R.id.fechaPres3);
        textoPres1 = (TextView) view.findViewById(R.id.textoPres1);
        textoPres2 = (TextView) view.findViewById(R.id.textoPres2);
        textoPres3 = (TextView) view.findViewById(R.id.textoPres3);
        Parametros.reset();
        Parametros.setMetodo("Modulo2/consultarEstadisticas?idUsuario="+Integer.toString
                (ControlDatos.getUsuario().getIdusuario()));
        new Recepcion(parentActivity,this).execute("GET");
        if (!Registro.estado){
            refrescarDatos();
            llenarCampos();

        }
        //Botones de la seccion:
        bancos = (ImageButton)view.findViewById(R.id.btnafiliaciones);
        bancos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new BancosAfiliadosFragment(), false);
            }
        });
        tarjetas = (ImageButton)view.findViewById(R.id.btntarjetas);
        tarjetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new TarjetasCreditoFragment(), false);
            }
        });

        return view;

    }


    @Override
    public void obtuvoCorrectamente(Object response) {
        try {
            if (!response.toString().equals("0")){
                refrescarDatos();
                llenarCampos();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }
    public void refrescarDatos(){
        try {
            SharedPreferences pref = this.getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();

            if (Parametros.getRespuesta().equals("Error")||Parametros.getRespuesta().equals("ERROR") ) {

                Toast.makeText(parentActivity, "Ups, ha ocurrido un error", Toast.LENGTH_SHORT).show();

            }else {

                   if(!Parametros.getRespuesta().equals("0")){
                       String datos =Parametros.getRespuesta();
                       editor.putString("cookieEstadisticas",datos);
                       editor.commit();
                       GestionUsuarios_Controller.descomponerEstadisticas(datos);

                   }
                      /*
                        String estadisticas = pref.getString("cookieEstadisticas","vacio");
                        if(estadisticas.equals("vacio")){
                        }else{
                            GestionUsuarios_Controller.descomponerEstadisticas(estadisticas);
                        }
                     */

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void llenarCampos(){
        saldoActual.setText(String.valueOf(ControlDatos.getUsuario().getSaldoCuenta())+" Bs.");
        saldoTarjetas.setText(String.valueOf(ControlDatos.getUsuario().getSaldoTarjeta())+" Bs.");
        ingresoTextView.setText("Ingresos: "+String.valueOf(ControlDatos.getUsuario().getIngresosPorc())+"%");
        egresoTextView.setText("Egresos: "+String.valueOf(ControlDatos.getUsuario().getEgresosPorc())+"%");
        egresosBar.setProgress(ControlDatos.getUsuario().getEgresosPorc());
        ingresosBar.setProgress(ControlDatos.getUsuario().getIngresosPorc());

        if(ControlDatos.getUsuario().getTopPagosP().size()>=1) {
            fechaPagoR1.setText(ControlDatos.getUsuario().getTopPagosP().get(0).getFecha());
            textoPagoR1.setText(ControlDatos.getUsuario().getTopPagosP().get(0).getDescripcion());
        }
        if(ControlDatos.getUsuario().getTopPagosP().size()>=2) {
            fechaPagoR2.setText(ControlDatos.getUsuario().getTopPagosP().get(1).getFecha());
            textoPagoR2.setText(ControlDatos.getUsuario().getTopPagosP().get(1).getDescripcion());
        }
        if(ControlDatos.getUsuario().getTopPagosP().size()>=3) {
            fechaPagoR3.setText(ControlDatos.getUsuario().getTopPagosP().get(2).getFecha());
            textoPagoR3.setText(ControlDatos.getUsuario().getTopPagosP().get(2).getDescripcion());
        }

        if(ControlDatos.getUsuario().getTopPagosR().size()>=1) {
            fechaPagoP1.setText(ControlDatos.getUsuario().getTopPagosR().get(0).getFecha());
            textoPagoP1.setText(ControlDatos.getUsuario().getTopPagosR().get(0).getDescripcion());
        }
        if(ControlDatos.getUsuario().getTopPagosR().size()>=2) {
            fechaPagoP2.setText(ControlDatos.getUsuario().getTopPagosR().get(1).getFecha());
            textoPagoP2.setText(ControlDatos.getUsuario().getTopPagosR().get(1).getDescripcion());
        }
        if(ControlDatos.getUsuario().getTopPagosR().size()>=3) {
            fechaPagoP3.setText(ControlDatos.getUsuario().getTopPagosR().get(2).getFecha());
            textoPagoP3.setText(ControlDatos.getUsuario().getTopPagosR().get(2).getDescripcion());
        }

        if(ControlDatos.getUsuario().getTopPres().size()>=1) {
            fechaPres1.setText(ControlDatos.getUsuario().getTopPres().get(0).getFecha());
            textoPres1.setText(ControlDatos.getUsuario().getTopPres().get(0).getDescripcion());
        }
        if(ControlDatos.getUsuario().getTopPres().size()>=2) {
            fechaPres2.setText(ControlDatos.getUsuario().getTopPres().get(1).getFecha());
            textoPres2.setText(ControlDatos.getUsuario().getTopPres().get(1).getDescripcion());
        }
        if(ControlDatos.getUsuario().getTopPres().size()>=3) {
            fechaPres3.setText(ControlDatos.getUsuario().getTopPres().get(2).getFecha());
            textoPres3.setText(ControlDatos.getUsuario().getTopPres().get(2).getDescripcion());
        }
    }


}
