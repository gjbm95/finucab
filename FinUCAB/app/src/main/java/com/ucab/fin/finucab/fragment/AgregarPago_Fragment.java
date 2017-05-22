package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Pago_Controller;
import com.ucab.fin.finucab.domain.Pago;


public class AgregarPago_Fragment extends Fragment {

    MainActivity parentActivity;
    Button agregarButton;
    EditText descripcionEditText, montoEditText;
    Spinner categoriaSpinner,tipoSpinner;
    int Resp;
    Pago pago;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_agregar_pago, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Agregar Pago");

        descripcionEditText = (EditText) rootView.findViewById(R.id.descripccionEditText);
        montoEditText = (EditText) rootView.findViewById(R.id.montoEditText);
        categoriaSpinner= (Spinner) rootView.findViewById(R.id.categoriaSpinner) ;
        tipoSpinner= (Spinner) rootView.findViewById(R.id.tipoTransaccionSpinner) ;

        agregarButton = (Button) rootView.findViewById(R.id.acceptButton);

        Pago_Controller.categoriaPago = categoriaSpinner;
        Pago_Controller.montoPago = montoEditText;
        Pago_Controller.tipoTransaccion=tipoSpinner;
        Pago_Controller.descripcionPago=descripcionEditText;




        agregarButton = (Button) rootView.findViewById(R.id.acceptButtonAPago);
        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resp = Pago_Controller.validacionPagoVacio();
                if (Resp == 1) {
                    pago = new Pago();
                    pago.setCategoria(categoriaSpinner.getSelectedItem().toString());
                    pago.setDescripcion(descripcionEditText.getText().toString());
                    pago.setTotal(Float.valueOf(montoEditText.getText().toString()));
                    pago.setTipo(tipoSpinner.getSelectedItem().toString());
                    Pago_Controller.registrarPago(pago,parentActivity);
                    parentActivity.changeFragment(new PaymentFragment(), false);
                }
            }
        });


        return rootView;
    }
}
