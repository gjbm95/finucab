package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView ingresos, gastos, total;
    FloatingActionButton fab;
    MainActivity parentActivity;


    public TotalFragment() {
        // Required empty public constructor
    }

    public static TotalFragment newInstance(int sectionNumber) {
        TotalFragment fragment = new TotalFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.total_fragment, container, false);
        ingresos = (TextView) rootView.findViewById(R.id.ingreso);
        gastos = (TextView) rootView.findViewById(R.id.gasto);
        total = (TextView) rootView.findViewById(R.id.total);
        ingresos.setText("0");
        gastos.setText("0");
        total.setText("0");
        parentActivity = (MainActivity) getActivity();
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new AgregarPresupuesto_fragment(), false);

            }
        });
        return rootView;
    }

}
