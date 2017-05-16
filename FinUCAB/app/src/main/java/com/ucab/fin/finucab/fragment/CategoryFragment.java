package com.ucab.fin.finucab.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {


    private View parentView;
    private MainActivity parentActivity;
    private TabLayout pestanas;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.category_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        parentActivity.getSupportActionBar().setTitle("Categorias");
        if (savedInstanceState == null) {
            viewPager = (ViewPager) view.findViewById(R.id.category);
            CategoryFragment.AdaptadorSecciones adapter = new CategoryFragment.AdaptadorSecciones(getChildFragmentManager());
            adapter.addFragment(new ListaCategorias_Fragment(), "Puedo");
            viewPager.setAdapter(adapter);
        }

        return view;
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



}
