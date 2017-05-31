package com.ucab.fin.finucab.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.Planificacion_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.CategoriaSpinner;
import com.ucab.fin.finucab.domain.Planificacion;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;


public class AgregarPlanificacionFragment extends Fragment implements ResponseWebServiceInterface {

    private TextView recurrenciaTV, fechaHastaTV;
    private EditText descripcion, monto, fechaDesde, fechaHasta;
    private Spinner categoria, recurrencia;
    private RadioGroup tipoGrupo;
    private RadioButton tipoBoton;
    private MainActivity parentActivity;
    private Button aceptar, volver;
    private Calendar calendar;
    private Planificacion planificacion, pa;
    private boolean recurrente;
    private DateFormat format;
    private boolean modificar;
    private int idPlanificacion;
    private ArrayAdapter spinner_adapter;

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
        format = new SimpleDateFormat("d-M-yyyy");


        recurrenciaTV = (TextView) rootView.findViewById(R.id.recurrenciaPaTextView);
        fechaHastaTV = (TextView) rootView.findViewById(R.id.fechaHastaTextView);
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


        modificar = getArguments() != null && getArguments().getBoolean("modificar");
        if (getArguments() != null) {
            idPlanificacion = getArguments().getInt("planificacionId");
        }


        Planificacion_Controller.listaCategoriasPa();


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = tipoGrupo.getCheckedRadioButtonId();
                tipoBoton = (RadioButton) rootView.findViewById(selectedId);
                String tipo = tipoBoton.getText().toString();
                CategoriaSpinner sp = (CategoriaSpinner) categoria.getSelectedItem();


                if (tipo.equals("Recurrente")) {
                    try {
                        recurrente = true;
                        if (!campoVacio(descripcion, monto, fechaDesde, fechaHasta)) {
                            planificacion = new Planificacion(format.parse(fechaDesde.getText().toString()), format.parse(fechaHasta
                                    .getText().toString()), " ", descripcion.getText().toString(), Double.valueOf
                                    (monto.getText().toString()), sp.getId(), recurrente, recurrencia.getSelectedItem()
                                    .toString(), true);
                            aceptar();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        recurrente = false;
                        if (!campoVacio(descripcion, monto, fechaDesde, fechaDesde)) {
                            planificacion = new Planificacion(format.parse(fechaDesde.getText().toString()), format.parse(fechaDesde.getText().toString()), " ", descripcion.getText().toString(), Double.valueOf
                                    (monto.getText().toString()), sp.getId(), recurrente, "", true);
                            aceptar();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
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
        tipoGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radioUnico) {
                    ocultarCampos();
                } else if (checkedId == R.id.radioRecurrente) {
                    mostrarCampos();
                }
            }
        });

        //llenarCampos();
        return rootView;
    }

    private void aceptar(){
        if (modificar) {
            planificacion.setId(idPlanificacion);
            Planificacion_Controller.modicarPlanificacion(planificacion);
        } else {
            Planificacion_Controller.agregarPlanificacion(planificacion);
        }
    }


    private void llenarCampos() {
        if (modificar) {
            DateFormat date = new SimpleDateFormat("d-M-yyyy");
            parentActivity.getSupportActionBar().setTitle("Modificar planificacion");

            Planificacion pa = Planificacion_Controller.planificacion_pago.getPlanificacion();
            descripcion.setText(pa.getDescripcion());
            monto.setText(String.format("%.2f", pa.getMonto()));
            categoria.setSelection(Planificacion_Controller.posCategoria(pa.getId()));
            fechaDesde.setText(date.format(pa.getFechaInicio()));

            if (pa.getRecurrente()) {
                mostrarCampos();
                tipoGrupo.check(R.id.radioRecurrente);
                fechaHasta.setText(date.format(pa.getFechaFin()));
                recurrencia.setSelection(spinner_adapter.getPosition(pa.getRecurrencia()));
            } else {
                ocultarCampos();
                tipoGrupo.check(R.id.radioUnico);
            }

        }
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
        } else {
            return false;

        }
        return true;
    }

    private void ocultarCampos() {
        recurrencia.setVisibility(recurrencia.INVISIBLE);
        fechaHasta.setVisibility(fechaHasta.INVISIBLE);
        recurrenciaTV.setVisibility(recurrenciaTV.INVISIBLE);
        fechaHastaTV.setVisibility(fechaHastaTV.INVISIBLE);
    }

    private void mostrarCampos() {
        recurrencia.setVisibility(recurrencia.VISIBLE);
        fechaHasta.setVisibility(fechaHasta.VISIBLE);
        recurrenciaTV.setVisibility(recurrenciaTV.VISIBLE);
        fechaHastaTV.setVisibility(fechaHastaTV.VISIBLE);
    }


    @Override
    public void obtuvoCorrectamente(Object response) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Log.i("Caso ", String.valueOf(Planificacion_Controller.managementRequest));

        switch (Planificacion_Controller.managementRequest) {

            case 1:
                try {
                    JSONArray mJsonArray = new JSONArray(Parametros.getRespuesta());
                    LinkedList category = new LinkedList();


                    for (int i = 0; i < mJsonArray.length(); i++) {
                        String strJson = mJsonArray.getString(i);
                        JSONObject jsonObject = new JSONObject(strJson);

                        category.add(new CategoriaSpinner(jsonObject.getInt("Id"), jsonObject.getString("Nombre")));

                    }
                    spinner_adapter = new ArrayAdapter(parentActivity, android.R.layout
                            .simple_spinner_item, category);
                    spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categoria.setAdapter(spinner_adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Planificacion_Controller.managementRequest = -1;
                if (idPlanificacion != 0)
                    Planificacion_Controller.buscaPlanificacion(idPlanificacion);
                break;

            case 2:
                String respuesta = (String) response;
                Planificacion_Controller.managementRequest = -1;
                showDialogResponse(respuesta);

                break;

            case 3:

                try {
                    JSONObject object = new JSONObject(Parametros.getRespuesta());
                    Planificacion pa = new Planificacion(object.getInt("Id"),
                            format.parse(object.getString("fechaInicio")),
                            format.parse(object.getString("fechaFin")),
                            object.getString("Nombre"),
                            object.getString("Descripcion"),
                            Double.parseDouble(object.getString("Monto")),
                            object.getInt("Categoria"),
                            object.getBoolean("Recurrente"),
                            object.getString("Recurrencia"),
                            object.getBoolean("Activo"));
                    Planificacion_Controller.planificacion_pago.setPlanificacion(pa);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Planificacion_Controller.managementRequest = -1;
                llenarCampos();

                break;

            case 5:

                String respuesta1 = (String) response;
                Planificacion_Controller.managementRequest = -1;
                showDialogResponse(respuesta1);

                break;


        }
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

    private void showDatePicker(int id) {

        switch (id) {

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
