package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.ExportarPresupuesto_Controller;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView ingresos, gastos, total;
    FloatingActionButton exportFAB;
    MainActivity parentActivity;


    public TotalFragment() {
        // Required empty public constructor
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
        exportFAB = (FloatingActionButton) rootView.findViewById(R.id.addFloatingBtnTotal);
        exportFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parentActivity, "Exportando...", Toast.LENGTH_SHORT).show();
                ExportarPresupuesto_Controller task=new ExportarPresupuesto_Controller();
                task.execute();
                Toast.makeText(parentActivity, "Exportado correctamente", Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }

}
