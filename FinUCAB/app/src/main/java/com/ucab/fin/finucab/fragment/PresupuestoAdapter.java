package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.domain.Presupuesto;

import java.util.List;

/**
 * Created by Somebody on 4/22/2017.
 */

public class PresupuestoAdapter extends RecyclerView.Adapter<PresupuestoAdapter.PresupuestoViewHolder> {

    private List<Presupuesto> PresupuestoList;

    public PresupuestoAdapter(List<Presupuesto> PresupuestoList)
    {
        this.PresupuestoList = PresupuestoList;
    }

    @Override
    public int getItemCount()
    {
        return PresupuestoList.size();
    }

    @Override
    public void onBindViewHolder( final PresupuestoViewHolder PresupuestoViewHolder, int i)
    {
        Presupuesto Pi = PresupuestoList.get(i);
        PresupuestoViewHolder.nameTextView.setText(Pi.get_nombre());
        PresupuestoViewHolder.amountTextView.setText(( Pi.get_monto().toString()));
        PresupuestoViewHolder.catergoryTextView.setText(Pi.get_categoria());
        PresupuestoViewHolder.monthTextView.setText("Cada "+ Pi.get_duracion() +" meses");
        PresupuestoViewHolder.itemView.setLongClickable(true);
    }

    @Override
    public PresupuestoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout. recycle_view_items, viewGroup, false);

        return new PresupuestoViewHolder(itemView);
    }



    public class PresupuestoViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView amountTextView;
        private TextView catergoryTextView;
        private TextView monthTextView;
        public PresupuestoViewHolder(View v) {
            super(v);
            nameTextView = (TextView) v.findViewById(R.id.nameTextView);
            amountTextView = (TextView) v.findViewById(R.id.amountTextView);
            catergoryTextView = (TextView) v.findViewById(R.id.categoryTextView);
            monthTextView = (TextView) v.findViewById(R.id.monthTextView);
        }
    }

}