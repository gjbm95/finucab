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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatosCuentaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatosCuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosCuentaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DatosCuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosCuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosCuentaFragment newInstance(String param1, String param2) {
        DatosCuentaFragment fragment = new DatosCuentaFragment();
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

        View rootView = inflater.inflate(R.layout.datos_cuenta_fragment, container, false);
        if (GestionUsuarios_Controller.usuario==null)
        GestionUsuarios_Controller.usuario = (EditText)rootView.findViewById(R.id.usernameREditText);
        else {
            EditText usuario = (EditText) rootView.findViewById(R.id.usernameREditText);
            usuario.setText(GestionUsuarios_Controller.usuario.getText());
            GestionUsuarios_Controller.usuario= usuario;
        }
        if (GestionUsuarios_Controller.contrasena1==null)
        GestionUsuarios_Controller.contrasena1 = (EditText)rootView.findViewById(R.id.passwordREditText);
        else {
            EditText contrasena1 = (EditText) rootView.findViewById(R.id.passwordREditText);
            contrasena1.setText(GestionUsuarios_Controller.contrasena1.getText());
            GestionUsuarios_Controller.contrasena1= contrasena1;
        }
        if (GestionUsuarios_Controller.contrasena2==null)
        GestionUsuarios_Controller.contrasena2 = (EditText)rootView.findViewById(R.id.passwordtwoREditText);
        else {
            EditText contrasena2 = (EditText) rootView.findViewById(R.id.passwordtwoREditText);
            contrasena2.setText(GestionUsuarios_Controller.contrasena2.getText());
            GestionUsuarios_Controller.contrasena2= contrasena2;
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
