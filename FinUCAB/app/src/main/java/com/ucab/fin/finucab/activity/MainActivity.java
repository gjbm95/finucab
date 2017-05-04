package com.ucab.fin.finucab.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.fragment.AgregarPresupuesto_fragment;
import com.ucab.fin.finucab.fragment.HomeFragment;
import com.ucab.fin.finucab.fragment.MyProfileFragment;

public class MainActivity extends AppCompatActivity {

    private View parentView;

    DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Colocando el icono en la parte superior izquierda:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.indicador);
        actionBar.setTitle(" Nombre_Seccion");
        actionBar.setElevation(0);
        //------------------------------------------------------------------------------------------
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        changeFragment(new HomeFragment(),false);
    }

    public void closeDrawerLayout(){

        drawer_layout.closeDrawer(Gravity.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cubo, menu);
        return true;
    }


    public void changeFragment(Fragment fragment, boolean addToBackStack) {
        if (fragment != null) {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack("history");
            transaction.replace(R.id.main_layout, fragment);
            transaction.commit();
        }
    }
}

