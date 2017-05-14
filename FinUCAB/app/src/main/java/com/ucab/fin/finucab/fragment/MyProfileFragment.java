package com.ucab.fin.finucab.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    Button boton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        boton = (Button) view.findViewById(R.id.bBack);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager man = getFragmentManager();
                FragmentTransaction trans = man.beginTransaction();
                HomeFragment profi = new HomeFragment();
                trans.add(R.id.drawer_layout,profi);
                trans.commit();

            }
        });
        //myEditText.setText( myEditText.getText() + " " + myButton.getText() );
        etNombre.setText( etNombre.getText() + " " + boton.getText());

        return view;


    }

}
