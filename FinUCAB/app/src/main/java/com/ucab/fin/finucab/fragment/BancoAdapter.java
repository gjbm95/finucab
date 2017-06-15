package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ucab.fin.finucab.R;

import com.ucab.fin.finucab.domain.Cuenta_Bancaria;

import java.util.List;

/**
 * Created by Junior on 14/06/2017.
 */

public class BancoAdapter  extends RecyclerView.Adapter<BancoAdapter.BancoViewHolder> {

    private List<Cuenta_Bancaria> BancoList; //creacion de una lista de Bancos

    public BancoAdapter(List<Cuenta_Bancaria> BancoList)
    {
        this.BancoList = BancoList;
    }

    public class BancoViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView; //TextView de nombre de Banco
        private TextView tipocuentaTextView; //TextView del tipo de cuenta
        private TextView numeroCuenta; //TextView numero de cuenta;
        private TextView saldoCuenta; // TextView con saldo de la cuenta;
        private Button spaceToClick;
        private Cuenta_Bancaria Banco;



        /**
         * Creacion del Metodo para acceder a los valores y asignarlos en variables
         *
         * @param v
         */
        public BancoViewHolder(View v) {
            super(v);

            nameTextView = (TextView) v.findViewById(R.id.nombreBancosTextView);
            tipocuentaTextView = (TextView) v.findViewById(R.id.tipoCuentaTextView);
            numeroCuenta = (TextView) v.findViewById(R.id.numeroCuentaTextView);
            saldoCuenta = (TextView) v.findViewById(R.id.saldoTextView);

        }

    }

    /**
     * Saber el tamaño de la lista
     * @return
     */
    @Override
    public int getItemCount()
    {
        if (BancoList == null ){
            return 0;
        }else {
            return BancoList.size();
        }
    }


    /**
     * traer el contenido de los direfentes atributos
     * @param BancoViewHolder
     * @param i
     */

    @Override
    public void onBindViewHolder(final BancoAdapter.BancoViewHolder BancoViewHolder, int i)
    {
        Cuenta_Bancaria pi = BancoList.get(i);
        BancoViewHolder.Banco = pi;
        BancoViewHolder.nameTextView.setText("Banco: " + pi.getNombreBanco());
        BancoViewHolder.tipocuentaTextView.setText("Tipo de Cuenta: "+pi.getTipoCuenta());
        BancoViewHolder.numeroCuenta.setText("N° de Cuenta: " +pi.getNumcuenta());
        BancoViewHolder.saldoCuenta.setText("Saldo: " + Float.toString(pi.getSaldoActual()) + " Bs.");
        BancoViewHolder.itemView.setLongClickable(true);

        if ( BancoList.size() == (i+1) ){

        }
    }


    /**
     *
     /**
     * llamar al layout recycle_view donde aparece el esquema como se deberia
     * de ver la lista de Bancos
     * @param viewGroup
     * @param i
     * @return
     */

    @Override
    public BancoAdapter.BancoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout. recycle_view_bancos, viewGroup, false);

        return new BancoAdapter.BancoViewHolder(itemView);

    }



}
