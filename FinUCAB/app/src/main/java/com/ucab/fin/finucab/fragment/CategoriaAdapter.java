package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;

import java.util.List;


public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private List<Categoria> CategoriaList;

    public CategoriaAdapter(List<Categoria> CategoriaList)
    {
        this.CategoriaList = CategoriaList;
    }


    public class CategoriaViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView descripcionTextView;
        Switch switchestado;
        int id;

        public CategoriaViewHolder(View v) {
            super(v);

            nameTextView = (TextView) v.findViewById(R.id.categoriasTextView);
            descripcionTextView = (TextView) v.findViewById(R.id.descripcionTextView);
            switchestado = (Switch) v.findViewById(R.id.switchestado);

            switchestado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Categoria_Controller.HabilitarCategoria(Integer.parseInt(buttonView.getText()+""),isChecked);

                }
            });

        }

    }


    @Override
    public int getItemCount()
    {
        return CategoriaList.size();
    }


    @Override
    public void onBindViewHolder( final CategoriaViewHolder CategoriaViewHolder, int i)
    {
        Categoria Pi = CategoriaList.get(i);
        CategoriaViewHolder.id = Pi.getIdcategoria();
        CategoriaViewHolder.nameTextView.setText(Pi.getNombre());
        CategoriaViewHolder.descripcionTextView.setText(Pi.getDescripcion());
        CategoriaViewHolder.switchestado.setText(Pi.getIdcategoria()+"");
        CategoriaViewHolder.switchestado.setChecked(Pi.isEstaHabilitado());
        CategoriaViewHolder.itemView.setLongClickable(true);
    }



    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout. recycle_view_categorias, viewGroup, false);

        return new CategoriaViewHolder(itemView);

    }



}