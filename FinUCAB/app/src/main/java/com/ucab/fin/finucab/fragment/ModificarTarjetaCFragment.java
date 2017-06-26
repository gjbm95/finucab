package com.ucab.fin.finucab.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Banco_Controller;
import com.ucab.fin.finucab.controllers.Tarjeta_Controller;

import java.text.DateFormat;
import java.util.Calendar;


public class ModificarTarjetaCFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DateFormat format;
    private ArrayAdapter spinner_adapter;
    private Calendar calendar;
    private EditText fechaven;
    private MainActivity parentActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public ModificarTarjetaCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModificarTarjetaCFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarTarjetaCFragment newInstance(String param1, String param2) {
        ModificarTarjetaCFragment fragment = new ModificarTarjetaCFragment();
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
        View fragview = inflater.inflate(R.layout.agregar_tarjeta_fragment, container, false);

        fechaven = (EditText)fragview.findViewById(R.id.fechavenEditText);
        calendar = Calendar.getInstance();
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Modificar TDC");
        fechaven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(fechaven.getId());
            }
        });
        EditText tipotarjeta = (EditText)fragview.findViewById(R.id.AddtipoTarjetaEditText);
        EditText numerotarjeta = (EditText)fragview.findViewById(R.id.numerotarjetaEditText);
        Spinner cuentaafilida = (Spinner) fragview.findViewById(R.id.cuentaafiliadaSpinner);
        Tarjeta_Controller.tipotarjeta = tipotarjeta;
        Tarjeta_Controller.numerotarjeta = numerotarjeta;
        Tarjeta_Controller.fechaven = fechaven;

        Button botonaceptar = (Button)fragview.findViewById(R.id.agregarTarjetaButton);
        botonaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Tarjeta_Controller.validacionTarjetas(ModificarTarjetaCFragment.this)==0){
                    parentActivity.changeFragment(new TarjetasCreditoFragment(), false);
                    parentActivity.closeDrawerLayout();
                }

            }
        });



        return fragview;
    }

    private void showDatePicker(int id) {

        switch (id) {

            case R.id.fechavenEditText:
                DatePickerDialog datePicker = new DatePickerDialog(parentActivity, datePickerListener, calendar.get(Calendar
                        .YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(false);
                datePicker.setTitle("Selecciona una fecha");
                datePicker.show();
                break;

        }


    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            fechaven.setText(day1 + "-" + month1 + "-" + year1);

        }
    };

}
