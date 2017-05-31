package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.Planificacion_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Manejador_Categoria;
import com.ucab.fin.finucab.domain.Planificacion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by William on 25/5/2017.
 */

public class PlanificacionAdapter extends RecyclerView.Adapter<PlanificacionAdapter.ViewHolder> {

    private List<Planificacion> items;
    Manejador_Categoria manejador;


    public PlanificacionAdapter(List<Planificacion> planificacionList) {
        super();
        this.items = planificacionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_planificacion, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Planificacion lista = items.get(position);
        String nombreCategoria = Planificacion_Controller.nombreCategoria(lista.getIdCategoria());
        String fecha;
        if (lista.getRecurrente()) {
            fecha = date.format(lista.getFechaInicio()) + " hasta " + date.format(lista.getFechaFin());
        } else fecha = (date.format(lista.getFechaInicio()));
        holder.textViewCategoria.setText(nombreCategoria);
        holder.textViewDescripcion.setText(lista.getDescripcion());
        holder.textViewFecha.setText(fecha);
        holder.textViewMonto.setText(String.format("%.2f", lista.getMonto()));
        holder.textViewRecurrente.setText(lista.getRecurrencia());
        holder.itemView.setLongClickable(true);


    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
            //itemView.setOnLongClickListener(this);
        }

    }
}
