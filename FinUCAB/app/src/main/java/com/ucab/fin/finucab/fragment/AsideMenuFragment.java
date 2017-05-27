package com.ucab.fin.finucab.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.InicioActivity;
import com.ucab.fin.finucab.activity.MainActivity;
import com.ucab.fin.finucab.webservice.ControlDatos;

public class AsideMenuFragment extends Fragment implements View.OnClickListener{

    private View parentView;
    private MainActivity parentActivity;

    LinearLayout myProfileBtn, budgetBtn, categorybtn,paysBtn,logoutbtn, planificationBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.aside_menu_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        //Nombre de usuario:
        TextView nombredeusuario = (TextView)parentView.findViewById(R.id.usuario);
        nombredeusuario.setText("Usuario: " + ControlDatos.getUsuario().getUsuario());
//          BIND VIEWS
        myProfileBtn = (LinearLayout) parentView.findViewById(R.id.myProfileBtn);
        budgetBtn = (LinearLayout) parentView.findViewById(R.id.budgetBtn);
        categorybtn = (LinearLayout) parentView.findViewById(R.id.categoryBtn);
        paysBtn = (LinearLayout) parentView.findViewById(R.id.paysBtn);
        logoutbtn = (LinearLayout) parentView.findViewById(R.id.singoutBtn);
        planificationBtn = (LinearLayout) parentView.findViewById(R.id.planificationBtn);

//        SET LISTENERS
        myProfileBtn.setOnClickListener(this);
        budgetBtn.setOnClickListener(this);
        categorybtn.setOnClickListener(this);
        paysBtn.setOnClickListener(this);
        logoutbtn.setOnClickListener(this);
        planificationBtn.setOnClickListener(this);
        return parentView;
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.myProfileBtn:
                parentActivity.changeFragment(new MyProfileFragment(), false);
                parentActivity.closeDrawerLayout();
                break;
            case R.id.budgetBtn:
                parentActivity.changeFragment(new BudgetFragment(), false);
                parentActivity.closeDrawerLayout();
                break;
            case R.id.categoryBtn:
                parentActivity.changeFragment(new ListaCategorias_Fragment(), false);
                parentActivity.closeDrawerLayout();
                break;
            case R.id.paysBtn:
                parentActivity.changeFragment(new PaymentFragment(), false);
                parentActivity.closeDrawerLayout();
                break;
            case R.id.planificationBtn:
                parentActivity.changeFragment(new PlanificacionFragment(), false);
                parentActivity.closeDrawerLayout();
                break;
            case R.id.singoutBtn:
                SharedPreferences pref = parentActivity.getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("cookie");
                editor.commit();
                ControlDatos.setUsuario(null);
                Intent inicio = new Intent (parentActivity, InicioActivity.class);
                startActivity(inicio);
                parentActivity.finish();
                parentActivity.closeDrawerLayout();


                break;

        }


    }


}