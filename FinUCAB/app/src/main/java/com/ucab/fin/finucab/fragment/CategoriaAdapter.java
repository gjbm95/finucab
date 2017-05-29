package com.ucab.fin.finucab.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Space;
import android.widget.Switch;
import android.widget.TextView;
 
import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.domain.Categoria;

import java.util.List;
/**
*Modulo 4 - Modulo de  Gestion de Categorias
        *Desarrolladores:
        *@author Juan Ariza / Augusto Cordero / Manuel Gonzalez
        *Descripción de la clase:
        * Esta clase se encargara mostrar en pantalla el layout de las lista de caegorias
         * dandole funcionalidad a los botones y atributos
        */

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    private List<Categoria> CategoriaList; //creacion de una lista de categorias

    public CategoriaAdapter(List<Categoria> CategoriaList)
    {
        this.CategoriaList = CategoriaList;
    }

    public class CategoriaViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView; //TextView de nombre de categoria
        private TextView descripcionTextView; //TextView de la decripcion de la categoria
        private Switch switchestado;
        private Button spaceToClick;
        Categoria categoria;



        /**
         * Creacion del Metodo para acceder a los valores y asignarlos en variables
         * para habilitar y deshabilitar las categorias
         * @param v
         */
        public CategoriaViewHolder(View v) {
            super(v);

            nameTextView = (TextView) v.findViewById(R.id.categoriasTextView);
            descripcionTextView = (TextView) v.findViewById(R.id.descripcionTextView);
            switchestado = (Switch) v.findViewById(R.id.switchestado);
            //spaceToClick = (Button) v.findViewById(R.id.spaceToClick);

            switchestado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    Categoria_Controller.HabilitarCategoria( (Categoria) buttonView.getTag() ,isChecked);

                }
            });

            /*
            spaceToClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("Evento - Space",String.valueOf(v));
                    Categoria_Controller.redireccionarAgregarCategoria((Categoria) v.getTag());



                }
            });

            spaceToClick.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
*/
        }

    }

    /**
     * Saber el tamaño de la lista
     * @return
     */
    @Override
    public int getItemCount()
    {
        if (CategoriaList == null ){
            return 0;
        }else {
            return CategoriaList.size();
        }
    }


    /**
     * traer el contenido de los direfentes atributos
     * @param CategoriaViewHolder
     * @param i
     */

    @Override
    public void onBindViewHolder( final CategoriaViewHolder CategoriaViewHolder, int i)
    {
        Categoria pi = CategoriaList.get(i);
        CategoriaViewHolder.categoria = pi;
        CategoriaViewHolder.nameTextView.setText(pi.getNombre());
        CategoriaViewHolder.descripcionTextView.setText(pi.getDescripcion());
        CategoriaViewHolder.switchestado.setTag(pi);
        CategoriaViewHolder.switchestado.setChecked(pi.isEstaHabilitado());
        CategoriaViewHolder.itemView.setLongClickable(true);
        //CategoriaViewHolder.spaceToClick.setTag(pi);

        if ( CategoriaList.size() == (i+1) ){
            Categoria_Controller.setHabilitarEventoSwitch(true);
        }
    }


    /**
     *
     /**
     * llamar al layout recycle_view donde aparece el esquema como se deberia
     * de ver la lista de categorias
     * @param viewGroup
     * @param i
     * @return
     */

    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout. recycle_view_categorias, viewGroup, false);

        return new CategoriaViewHolder(itemView);

    }



}