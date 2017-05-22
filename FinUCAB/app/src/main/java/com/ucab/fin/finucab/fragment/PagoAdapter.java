package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.domain.Pago;

import java.util.List;


public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.PagoViewHolder> {



    private List<Pago> PagoList;

    public PagoAdapter(List<Pago> PagoList)
    {
        this.PagoList = PagoList;
    }



    @Override
    public int getItemCount()
    {
        return PagoList.size();
    }


    @Override
    public void onBindViewHolder( final PagoViewHolder PagoViewHolder, int i)
    {
        Pago Pi = PagoList.get(i);
        PagoViewHolder.categoriaTextView.setText(Pi.getCategoria());
        PagoViewHolder.descripcionTextView.setText(Pi.getDescripcion());
        PagoViewHolder.montoTextView.setText(Float.toString(Pi.getTotal()));
        PagoViewHolder.itemView.setLongClickable(true);
    }



    @Override
    public PagoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout. recycle_view_pagos, viewGroup, false);

        return new PagoViewHolder(itemView);

    }

    public class PagoViewHolder extends RecyclerView.ViewHolder {

        private TextView montoTextView;
        private TextView descripcionTextView;
        private TextView categoriaTextView;

        public PagoViewHolder(View v) {
            super(v);

            montoTextView = (TextView) v.findViewById(R.id.montopagoTextView);
            categoriaTextView = (TextView) v.findViewById(R.id.categoriapagosTextView);
            descripcionTextView = (TextView) v.findViewById(R.id.descripcionpagoTextView);

        }

    }



}