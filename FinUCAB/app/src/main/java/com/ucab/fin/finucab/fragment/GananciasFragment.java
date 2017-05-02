package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class GananciasFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    FloatingActionButton fab;
    View rootView;
    MainActivity parentActivity;


    public GananciasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.ganancias_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new AgregarPresupuesto_fragment(), false/*,"fragmentAgregarPresupuesto"*/);

            }
        });

        return rootView;
    }

    public static GananciasFragment newInstance(int sectionNumber) {
        GananciasFragment fragment = new GananciasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

}
