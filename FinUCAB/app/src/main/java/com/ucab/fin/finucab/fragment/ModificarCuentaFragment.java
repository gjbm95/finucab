package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;
import com.ucab.fin.finucab.webservice.ControlDatos;

public class ModificarCuentaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button aceptar;
    private EditText nombre,apellido,correo,usuario;
    private MainActivity parentActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ModificarCuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModificarCuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarCuentaFragment newInstance(String param1, String param2) {
        ModificarCuentaFragment fragment = new ModificarCuentaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragview =  inflater.inflate(R.layout.fragment_modificar_cuenta, container, false);
        //Inicializaciones:
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Modificar Perfil");
        aceptar = (Button)fragview.findViewById(R.id.modificarPerfilButton);
        nombre = (EditText)fragview.findViewById(R.id.nombreEditText);
        apellido = (EditText)fragview.findViewById(R.id.apellidoEditText);
        correo = (EditText)fragview.findViewById(R.id.correoEditText);
        usuario = (EditText)fragview.findViewById(R.id.usuarioEditText);
        //Coloco los datos:
        nombre.setText(ControlDatos.getUsuario().getNombre());
        apellido.setText(ControlDatos.getUsuario().getApellido());
        correo.setText(ControlDatos.getUsuario().getCorreo());
        usuario.setText(ControlDatos.getUsuario().getUsuario());
        //Seteo para validar:
        GestionUsuarios_Controller.nombre = nombre;
        GestionUsuarios_Controller.apellido = apellido;
        GestionUsuarios_Controller.correo = correo;
        GestionUsuarios_Controller.usuario = usuario;
        //Funciones para el boton:
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GestionUsuarios_Controller.validacionModificacionDatos()==0)
                {
                    parentActivity.changeFragment(new PerfilFragment(), false);
                    parentActivity.closeDrawerLayout();
                }
            }
        });

        return fragview;

    }



}
