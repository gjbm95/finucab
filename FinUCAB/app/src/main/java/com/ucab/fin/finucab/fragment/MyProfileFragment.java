package com.ucab.fin.finucab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;

public class MyProfileFragment extends Fragment {


    private View parentView;
    private MainActivity parentActivity;

    EditText etNombre;
    EditText etApellido;
    EditText etCorreo;
    EditText etUsuario;
    EditText etClave;
    EditText etPregunta;
    EditText etRespuesta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        parentView = inflater.inflate(R.layout.profile_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        return parentView;


    }

}
