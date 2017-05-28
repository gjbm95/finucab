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

/**
 * A simple {@link Fragment} subclass.
 */
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
        Presupuesto_Controller.visualizarPresupuestos(parentActivity);
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
            JSONArray mJsonArray = null;
            JSONObject jObject = null;

            ArrayList listaCategoria = new ArrayList<Categoria>();
            try {
                mJsonArray = new JSONArray(Parametros.getRespuesta());
                int count = mJsonArray.length();

                for (int i = 0; i < count; i++) {   // iterate through jsonArray
                    jObject = mJsonArray.getJSONObject(i);  // get jsonObject @ i position
                    Presupuesto pre = new Presupuesto();
                    pre.set_duracion(Integer.parseInt((String) jObject.get("Duracion")));
                    pre.set_clasificacion((String) jObject.get("Clasificacion"));
                    pre.set_monto(Float.parseFloat((String) jObject.get("Monto")));
                    pre.set_categoria((String) jObject.get("Categoria"));
                    pre.set_nombre((String) jObject.get("Nombre"));
                    if ((jObject.get("Tipo")).equals("t")) {
                        Presupuesto_Controller.listaGanancias.add(pre);
                        Presupuesto_Controller.ganancias = Presupuesto_Controller.ganancias + pre.get_monto();
                    } else {
                        Presupuesto_Controller.listaGastos.add(pre);
                        Presupuesto_Controller.gastos = Presupuesto_Controller.gastos + pre.get_monto();
                    }
                }
                Presupuesto_Controller.total = Presupuesto_Controller.ganancias - Presupuesto_Controller.gastos;
                poblarViewPager(viewPager);
                pestanas.setupWithViewPager(viewPager);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //} else if (casoRequest == 1) {

            //   Toast.makeText(parentActivity, Parametros.getRespuesta(), Toast.LENGTH_SHORT).show();

            // }
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
