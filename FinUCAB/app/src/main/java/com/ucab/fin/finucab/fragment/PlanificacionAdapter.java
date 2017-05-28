package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Planificacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by William on 25/5/2017.
 */

public class PlanificacionAdapter extends RecyclerView.Adapter<PlanificacionAdapter.ViewHolder>{

    private List<Planificacion> items;
    Manejador_Categoria manejador;



    public PlanificacionAdapter(List<Planificacion> planificacionList){
        super();
        this.items = planificacionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_planificacion, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Planificacion lista = items.get(position);


        holder.textViewCategoria.setText(String.valueOf(lista.getIdCategoria()));
        holder.textViewDescripcion.setText(lista.getDescripcion());
        holder.textViewFecha.setText(date.format(lista.getFechaInicio()) + " hasta "+ date.format(lista.getFechaFin()));
        holder.textViewMonto.setText(String.format("%.2f",lista.getMonto()));
        holder.textViewRecurrente.setText(lista.getRecurrencia());


    }

    @Override
    public int getItemCount() {
        if (items == null){
            return 0;
        }else {
            return items.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener{
        private final String TAG = ViewHolder.class.getSimpleName();
        private TextView textViewCategoria;
        private TextView textViewDescripcion;
        private TextView textViewFecha;
        private TextView textViewMonto;
        private TextView textViewRecurrente;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewCategoria = (TextView) itemView.findViewById(R.id.planificacionPaTextView);
            textViewDescripcion = (TextView) itemView.findViewById(R.id.descripcionPaTextView);
            textViewFecha = (TextView) itemView.findViewById(R.id.fechaPaTextView);
            textViewMonto = (TextView) itemView.findViewById(R.id.montoPaTextView);
            textViewRecurrente = (TextView) itemView.findViewById(R.id.recurrenteTextView);

            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            int i = getPosition();
            items.remove(i);
            notifyItemRemoved(i);
            Log.d(TAG, "Item long-clicked at position " + i );
            return true;
        }
    }
}
