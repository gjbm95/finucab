package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.GestionUsuarios_Controller;

/**
 *Modulo 1 - Modulo de  Inicio de Sesion y registro de usuario
 *Desarrolladores:
 *@author Garry Jr. Bruno / Erbin Rodriguez / Alejandro Negrin
 *Descripción de la clase:
 * Esta clase presenta un formulario para el registro de datos personales  durante el registro de +
 * usuarios.
 **/
public class DatosPersonalesFragment extends Fragment {

    /// / TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public DatosPersonalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosPersonalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosPersonalesFragment newInstance(String param1, String param2) {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
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

    /**
     * Metodo encargado de crear el fragmento:
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.datos_personales_fragment, container, false);
        if (GestionUsuarios_Controller.nombre==null)
        GestionUsuarios_Controller.nombre = (EditText)rootView.findViewById(R.id.nameREditText);
        else {
            EditText nombre = (EditText) rootView.findViewById(R.id.nameREditText);
            nombre.setText(GestionUsuarios_Controller.nombre.getText());
            GestionUsuarios_Controller.nombre= nombre;
        }
        if (GestionUsuarios_Controller.apellido==null)
        GestionUsuarios_Controller.apellido = (EditText)rootView.findViewById(R.id.lastnameREditText);
        else {
            EditText apellido = (EditText) rootView.findViewById(R.id.lastnameREditText);
            apellido.setText(GestionUsuarios_Controller.apellido.getText());
            GestionUsuarios_Controller.apellido= apellido;
        }
        if (GestionUsuarios_Controller.correo==null)
        GestionUsuarios_Controller.correo = (EditText)rootView.findViewById(R.id.emailR2EditText);
        else {
            EditText correo = (EditText) rootView.findViewById(R.id.emailR2EditText);
            correo.setText(GestionUsuarios_Controller.correo.getText());
            GestionUsuarios_Controller.correo= correo;
        }

        return rootView ;


    }






}
