package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucab.fin.finucab.R;

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
        PresupuestoViewHolder.tvName.setText(Pi.getName());
        PresupuestoViewHolder.tvFname.setText(( Pi.getFname().toString()));
        PresupuestoViewHolder.tvEmail.setText(Pi.getEmail());
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
        private TextView tvName;
        private TextView tvFname;
        private TextView tvEmail;
        public PresupuestoViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.Name);
            tvFname = (TextView) v.findViewById(R.id.Fname);
            tvEmail = (TextView) v.findViewById(R.id.Email);

        }
    }

}