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
 *@author Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 * Esta clase presenta un formulario para el registro de datos de seguridad durante el registro de +
 * usuarios.
 **/

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatosSeguridadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatosSeguridadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosSeguridadFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DatosSeguridadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosSeguridadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosSeguridadFragment newInstance(String param1, String param2) {
        DatosSeguridadFragment fragment = new DatosSeguridadFragment();
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
        View rootView = inflater.inflate(R.layout.datos_seguridad_fragment, container, false);
        if (GestionUsuarios_Controller.pregunta==null)
        GestionUsuarios_Controller.pregunta = (EditText)rootView.findViewById(R.id.questionREditText);
        else {
            EditText pregunta = (EditText) rootView.findViewById(R.id.questionREditText);
            pregunta.setText(GestionUsuarios_Controller.pregunta.getText());
            GestionUsuarios_Controller.pregunta= pregunta;
        }
        if (GestionUsuarios_Controller.respuesta==null)
        GestionUsuarios_Controller.respuesta = (EditText)rootView.findViewById(R.id.answerREditText);
        else {
            EditText respuesta = (EditText) rootView.findViewById(R.id.answerREditText);
            respuesta.setText(GestionUsuarios_Controller.respuesta.getText());
            GestionUsuarios_Controller.respuesta= respuesta;
        }
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
