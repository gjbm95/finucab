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

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;

public class HomeFragment extends Fragment {

    private View parentView;
    private MainActivity parentActivity;

    Button boton;
    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        parentView = inflater.inflate(R.layout.home_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        return parentView;

    }*/

    @Override  //METODO QUE LLAMA A OTROS FRAGMENTS(PUEDE SER USADO TANTO EN FRAGMENTS COMO EN ACTIVITY)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        boton = (Button) view.findViewById(R.id.bAffiliations);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager man = getFragmentManager();
                FragmentTransaction trans = man.beginTransaction();
                Afiliaciones_Fragment afi = new Afiliaciones_Fragment();
                trans.add(R.id.drawer_layout,afi);
                //trans.replace(R.id.drawer_layout, afi);
                trans.commit();

            }
        });

        return view;

    }

    /*FragmentManager man = getFragmentManager();
    FragmentTransaction trans = man.beginTransaction();
    Afiliaciones_Fragment afi = new Afiliaciones_Fragment();
                trans.replace(R.id.contenedor_principal, afi);*/


}
