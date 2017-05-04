package com.ucab.fin.finucab.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GastosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GastosFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "GastosFragment";
    // TODO: Rename and change types of parameters
    FloatingActionButton fab;
    MainActivity parentActivity;

    public GastosFragment() {
        // Required empty public constructor
    }


    public static GastosFragment newInstance(int sectionNumber) {
        GastosFragment fragment = new GastosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gastos_fragment, container, false);

        parentActivity = (MainActivity) getActivity();
        fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new AgregarPresupuesto_fragment(), false);

            }
        });

        RecyclerView recycleList = (RecyclerView) rootView.findViewById(R.id.reList);

        LinearLayoutManager myLayoutManager = new LinearLayoutManager(getActivity());
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleList.setLayoutManager(myLayoutManager);

        PresupuestoAdapter pAdapter =new PresupuestoAdapter(populatedList());
        recycleList.setAdapter(pAdapter);



        return rootView;
    }

    private ArrayList<Presupuesto> populatedList() {
        ArrayList<Presupuesto> listOfPersona = new ArrayList<Presupuesto>();
        for(int i=0;i<20;i++)
        {
            Presupuesto pi = new Presupuesto();
            pi.setName("Name "+i);
            pi.setFname(150000);
            pi.setEmail("Nombre Categoria");
            listOfPersona.add(pi);

        }
        return listOfPersona;
    }

}
