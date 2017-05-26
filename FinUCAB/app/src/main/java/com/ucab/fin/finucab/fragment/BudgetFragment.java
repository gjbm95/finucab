package com.ucab.fin.finucab.fragment;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.controllers.Categoria_Controller;
import com.ucab.fin.finucab.controllers.Presupuesto_Controller;
import com.ucab.fin.finucab.domain.Categoria;
import com.ucab.fin.finucab.domain.Presupuesto;
import com.ucab.fin.finucab.webservice.Parametros;
import com.ucab.fin.finucab.webservice.ResponseWebServiceInterface;

import android.graphics.Color;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**Modulo 3 - Modulo de Presupuestos
 *Desarrolladores:*Mariángel Pérez / Oswaldo López / Aquiles Pulido
 *Descripción de la clase:
 *Esta clase es el contenedor del tab layout en el cual se visualizan las pestañas de los
 * presupuestos (Total, ganancias, gastos)
 **/

public class BudgetFragment extends Fragment implements ResponseWebServiceInterface {

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;
    MainActivity parentActivity;

    public BudgetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.budget_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Presupuesto");
        Presupuesto_Controller.interfaz = (ResponseWebServiceInterface) this;
        Presupuesto_Controller.obtenerListaPresupuestos(parentActivity);
        if (savedInstanceState == null) {
            insertarTabs(container);
            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();

        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));
        pestanas.setSelectedTabIndicatorColor(Color.parseColor("#000000"));
        appBar.addView(pestanas);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getChildFragmentManager());
        adapter.addFragment(new TotalFragment(), getString(R.string.titulo_tab_total));
        adapter.addFragment(new GananciasFragment(), getString(R.string.titulo_tab_ganancias));
        adapter.addFragment(new GastosFragment(), getString(R.string.titulo_tab_gastos));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(pestanas);
    }

    @Override
    public void obtuvoCorrectamente(Object response) {
        if(Parametros.getRespuesta().equals("Error")){
            Presupuesto_Controller.mensajeError(parentActivity,"Error de conexion con servidor!");
        }else
        {
            // if (casoRequest == 0) {
            Presupuesto_Controller.visualizarPresupuesto();
            poblarViewPager(viewPager);
            pestanas.setupWithViewPager(viewPager);

        }


    }

    @Override
    public void noObtuvoCorrectamente(Object error) {

    }

    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Parametros.getRespuesta() != null) {
            Log.v("Response-Fra",Parametros.getRespuesta());
            if (Parametros.getRespuesta().equals("Error")||Parametros.getRespuesta().equals("ERROR") ) {
                Toast.makeText(parentActivity, "Ups, ha ocurrido un error", Toast.LENGTH_SHORT).show();

            }

            Parametros.reset();
        }
    }

}
