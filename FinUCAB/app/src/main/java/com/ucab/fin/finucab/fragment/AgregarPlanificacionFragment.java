package com.ucab.fin.finucab.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.domain.Planificacion_Pago;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AgregarPlanificacionFragment extends Fragment implements ResponseWebServiceInterface{

    private Planificacion_Pago planificacion_pago;
    private EditText descripcion, monto, fromDate, toDate;
    private Spinner categoria;
    private RadioGroup tipoGrupo;
    private RadioButton tipoBoton;
    private MainActivity parentActivity;
    private Button aceptar, volver;
    private DatePickerDialog fromDatePicker, toDatePicker;
    private SimpleDateFormat dateFormatter;

    public AgregarPlanificacionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_agregar_planificacion, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Agregar planificacion de pago");

        descripcion = (EditText) rootView.findViewById(R.id.descripcionPaEditText);
        monto = (EditText) rootView.findViewById(R.id.montoPaEditText);
        categoria = (Spinner) rootView.findViewById(R.id.categoriaPaSpinner);
        tipoGrupo = (RadioGroup) rootView.findViewById(R.id.radioGrpPlanificacion);
        aceptar = (Button) rootView.findViewById(R.id.aceptarPlanificacionButton);
        volver = (Button) rootView.findViewById(R.id.volverPlanificacionButton);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");



        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSetting();
            }
        });


        return rootView;
    }


    @Override
    public void obtuvoCorrectamente(Object response) {

    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }

    private void showDialogSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
        builder.setMessage("Se agregó la planificación correctamente").setTitle("AVISO").
                setPositiveButton("Aceptar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        parentActivity.changeFragment(new PlanificacionFragment(), true);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }



}
