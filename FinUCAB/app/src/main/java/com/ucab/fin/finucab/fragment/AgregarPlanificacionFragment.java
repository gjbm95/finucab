package com.ucab.fin.finucab.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.ucab.fin.finucab.controllers.Planificacion_Controller;
import com.ucab.fin.finucab.domain.Planificacion;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AgregarPlanificacionFragment extends Fragment implements ResponseWebServiceInterface{

    private EditText descripcion, monto, fechaDesde, fechaHasta;
    private Spinner categoria, recurrencia;
    private RadioGroup tipoGrupo;
    private RadioButton tipoBoton;
    private MainActivity parentActivity;
    private Button aceptar, volver;
    private Calendar calendar;
    private Planificacion planificacion;
    private boolean recurrente;

    public AgregarPlanificacionFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_agregar_planificacion, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Agregar planificacion de pago");
        Planificacion_Controller.init(parentActivity, this);
        final SimpleDateFormat format = new SimpleDateFormat("d-M-yyyy");

        descripcion = (EditText) rootView.findViewById(R.id.descripcionPaEditText);
        monto = (EditText) rootView.findViewById(R.id.montoPaEditText);
        categoria = (Spinner) rootView.findViewById(R.id.categoriaPaSpinner);
        tipoGrupo = (RadioGroup) rootView.findViewById(R.id.radioGrpPlanificacion);
        aceptar = (Button) rootView.findViewById(R.id.aceptarPlanificacionButton);
        volver = (Button) rootView.findViewById(R.id.volverPlanificacionButton);
        fechaDesde = (EditText) rootView.findViewById(R.id.fechaPaEditText);
        fechaHasta = (EditText) rootView.findViewById(R.id.hastaPaEditText);
        recurrencia = (Spinner) rootView.findViewById(R.id.recurrenciaPaSpinner);
        calendar = Calendar.getInstance();


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = tipoGrupo.getCheckedRadioButtonId();
                tipoBoton = (RadioButton) rootView.findViewById(selectedId);
                String tipo = tipoBoton.getText().toString();
                if (tipo.equals("Recurrente"))
                    recurrente = true;
                else recurrente = false;
                try {
                    if(!campoVacio(descripcion, monto, fechaDesde, fechaHasta)) {
                        planificacion = new Planificacion(format.parse(fechaDesde.getText().toString()), format.parse(fechaHasta
                                .getText().toString()), " ", descripcion.getText().toString(), Double.valueOf
                                (monto.getText().toString()),1, recurrente, recurrencia.getSelectedItem().toString(), true);
                        Planificacion_Controller.agregarPlanificacion(planificacion);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.changeFragment(new PlanificacionFragment(), false);
            }
        });

        fechaDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(fechaDesde.getId());
            }
        });

        fechaHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(fechaHasta.getId());
            }
        });

        return rootView;
    }

    private boolean campoVacio(EditText descripcion1, EditText monto1, EditText fechaIni, EditText fechaFin) throws
            ParseException {


            if (descripcion1.getText().toString().equals("")) {
                descripcion.setError("Este campo es requerido");
            } else if (monto1.getText().toString().equals("")) {
                monto.setError("Este campo es requerido");
            } else if (fechaIni.getText().toString().equals("")) {
                fechaDesde.setError("Debe seleccionar una fecha");
            } else if (fechaFin.getText().toString().equals("")) {
                fechaHasta.setError("Debe seleccionar una fecha");
            } else{
                return false;

            }
            return true;
        }






    @Override
    public void obtuvoCorrectamente(Object response) {
        String respuesta = (String) response;
        showDialogResponse(respuesta);
    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }

    private void showDialogResponse(String respuesta) {

        AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
        builder.setMessage(respuesta).setTitle("AVISO").
                setPositiveButton("Aceptar ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        parentActivity.changeFragment(new PlanificacionFragment(), false);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showDatePicker(int id){

        switch (id){

            case R.id.fechaPaEditText:
                DatePickerDialog datePicker = new DatePickerDialog(parentActivity, datePickerListener, calendar.get(Calendar
                        .YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(false);
                datePicker.setTitle("Selecciona una fecha");
                datePicker.show();
                break;

            case R.id.hastaPaEditText:
                DatePickerDialog datePicker1 = new DatePickerDialog(parentActivity, datePickerHasta, calendar.get(Calendar
                        .YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker1.setCancelable(false);
                datePicker1.setTitle("Selecciona una fecha");
                datePicker1.show();
                break;
        }


    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            fechaDesde.setText(day1 + "-" + month1 + "-" + year1);

        }
    };



    private DatePickerDialog.OnDateSetListener datePickerHasta = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            fechaHasta.setText(day1 + "-" + month1 + "-" + year1);
        }
    };

}
