package com.ucab.fin.finucab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.webservice.ControlDatos;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

public class HomeFragment extends Fragment  implements ResponseWebServiceInterface {

    private View parentView;
    private TextView nombrecompleto;
    private MainActivity parentActivity;
    private ImageButton bancos;
    private ImageButton tarjetas;



    @Override  //METODO QUE LLAMA A OTROS FRAGMENTS(PUEDE SER USADO TANTO EN FRAGMENTS COMO EN ACTIVITY)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Home");
        nombrecompleto = (TextView)view.findViewById(R.id.nombrecompleto);
        nombrecompleto.setText(ControlDatos.getUsuario().getNombre() + " "
        +ControlDatos.getUsuario().getApellido());
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

    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }
}
