package com.ucab.fin.finucab.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Banco_Controller;
import com.ucab.fin.finucab.controllers.Tarjeta_Controller;
import com.ucab.fin.finucab.domain.Cuenta_Bancaria;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

public class ModificarBancoFragment extends Fragment  implements ResponseWebServiceInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainActivity parentActivity;

    public ModificarBancoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModificarBancoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarBancoFragment newInstance(String param1, String param2) {
        ModificarBancoFragment fragment = new ModificarBancoFragment();
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
        View view = inflater.inflate(R.layout.modificar_banco_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Modificar Banco");
        final EditText nombrebanco = (EditText)view.findViewById(R.id.nombreEditText);
        final EditText numerocuenta = (EditText)view.findViewById(R.id.numerocuentaEditText);
        final Spinner tipocuenta = (Spinner) view.findViewById(R.id.tipocuentaSpinner);
        final EditText saldoinicial = (EditText)view.findViewById(R.id.fechavenEditText);
        nombrebanco.setText(Banco_Controller.banco.getNombreBanco());
        numerocuenta.setText(Banco_Controller.banco.getNumcuenta());
        saldoinicial.setText(Float.toString(Banco_Controller.banco.getSaldoActual()));
        if (Banco_Controller.banco.getTipoCuenta().equals("Cuenta Corriente"))
        tipocuenta.setSelection(1);
        if (Banco_Controller.banco.getTipoCuenta().equals("Cuenta de Ahorro"))
        tipocuenta.setSelection(2);
        Banco_Controller.nombrebanco = nombrebanco;
        Banco_Controller.numerocuenta = numerocuenta;
        Banco_Controller.saldoinicial = saldoinicial;
        Banco_Controller.tipocuenta = tipocuenta;
        Banco_Controller.initManejador(parentActivity,this);

        Button botonaceptar = (Button)view.findViewById(R.id.agregarBancoButton);
        botonaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Banco_Controller.validacionBancos(ModificarBancoFragment.this)==0){
                    Banco_Controller.modificarBanco(new Cuenta_Bancaria(Banco_Controller.banco.getIdCuenta(),
                            nombrebanco.getText().toString(),numerocuenta.getText().toString(),
                            Float.parseFloat(saldoinicial.getText().toString()),
                            tipocuenta.getSelectedItem().toString()));
                }

            }
        });


        return view;
    }

    /**
     * Response WebService
     * se llena la lista con las consultas provenientes del WebService con la BD
     * @param response Respuesta del WebService
     */
    @Override
    public void obtuvoCorrectamente(Object response){
        String recepcion  = (String)response;

        if (!recepcion.equals("0"))
        {

            Toast.makeText(parentActivity,"Se ha modificado correctamente", Toast.LENGTH_SHORT).show();
            if(Banco_Controller.getCasoRequest() == 2 ){
                Banco_Controller.resetCasoRequest();
                parentActivity.onBackPressed();
            }else{

                Banco_Controller.resetCasoRequest();
            }

        }

    }
    /**
     * Response WebService
     * se llena la lista con las consultas provenientes del WebService con la BD
     * @param response Error del WebService
     */
    @Override
    public void noObtuvoCorrectamente(Object response){

    }


}
