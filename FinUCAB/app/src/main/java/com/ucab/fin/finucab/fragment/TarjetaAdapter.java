package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.domain.Tarjeta_Credito;

import java.util.List;

/**
 * Created by Junior on 20/06/2017.
 */

public class TarjetaAdapter extends RecyclerView.Adapter<TarjetaAdapter.TarjetaViewHolder> {


    private List<Tarjeta_Credito> TarjetaList; //creacion de una lista de Tarjetas

    public TarjetaAdapter(List<Tarjeta_Credito> TarjetaList)
    {
        this.TarjetaList = TarjetaList;
    }

    public class TarjetaViewHolder extends RecyclerView.ViewHolder {

        private TextView tipotarjeta; //TextView de tipo de tarjeta
        private TextView numeroTarjeta; //TextView numero de cuenta;
        private TextView deudaTarjeta; // TextView con saldo de la cuenta;
        private TextView fechavenTarjeta; // TextView con fecha de vencimiento de la tarjeta.
        private Button spaceToClick;
        private Tarjeta_Credito Tarjeta;



        /**
         * Creacion del Metodo para acceder a los valores y asignarlos en variables
         *
         * @param v
         */
        public TarjetaViewHolder(View v) {
            super(v);

            tipotarjeta = (TextView) v.findViewById(R.id.tipotarjetaTextView);
            numeroTarjeta = (TextView) v.findViewById(R.id.numerotarjetaTextView);
            deudaTarjeta = (TextView) v.findViewById(R.id.deudaTarjetaTextView);
            fechavenTarjeta = (TextView) v.findViewById(R.id.fechavenTextView);

        }

    }

    /**
     * Saber el tamaño de la lista
     * @return
     */
    @Override
    public int getItemCount()
    {
        if (TarjetaList == null ){
            return 0;
        }else {
            return TarjetaList.size();
        }
    }


    /**
     * traer el contenido de los direfentes atributos
     * @param TarjetaViewHolder
     * @param i
     */

    @Override
    public void onBindViewHolder(final TarjetaAdapter.TarjetaViewHolder TarjetaViewHolder, int i)
    {
        Tarjeta_Credito pi = TarjetaList.get(i);
        TarjetaViewHolder.Tarjeta = pi;
        TarjetaViewHolder.tipotarjeta.setText("Tipo de Tarjeta: " + pi.getTipotdc());
        TarjetaViewHolder.numeroTarjeta.setText("Nº: ************"+pi.getNumero().
                substring(pi.getNumero().length()-3,pi.getNumero().length()));
        TarjetaViewHolder.fechavenTarjeta.setText("Fecha Ven: " +pi.getFechaven());
        TarjetaViewHolder.deudaTarjeta.setText("Deuda: " + Float.toString(pi.getSaldo()) + " Bs.");
        TarjetaViewHolder.itemView.setLongClickable(true);

        if ( TarjetaList.size() == (i+1) ){

        }
    }


    /**
     *
     /**
     * llamar al layout recycle_view donde aparece el esquema como se deberia
     * de ver la lista de Tarjetas
     * @param viewGroup
     * @param i
     * @return
     */

    @Override
    public TarjetaAdapter.TarjetaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout. recycle_view_tarjetas, viewGroup, false);

        return new TarjetaAdapter.TarjetaViewHolder(itemView);

    }




}
