package com.ucab.fin.finucab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.LoginActivity;
import com.ucab.fin.finucab.activity.MainActivity;

public class AsideMenuFragment extends Fragment implements View.OnClickListener{

    private View parentView;
    private MainActivity parentActivity;

    LinearLayout myProfileBtn, budgetBtn, categorybtn,paysBtn,logoutbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.aside_menu_fragment, container, false);
        parentActivity = (MainActivity) getActivity();

//          BIND VIEWS
        myProfileBtn = (LinearLayout) parentView.findViewById(R.id.myProfileBtn);
        budgetBtn = (LinearLayout) parentView.findViewById(R.id.budgetBtn);
        categorybtn = (LinearLayout) parentView.findViewById(R.id.categoryBtn);
        paysBtn = (LinearLayout) parentView.findViewById(R.id.paysBtn);
        logoutbtn = (LinearLayout) parentView.findViewById(R.id.singoutBtn);

//        SET LISTENERS
        myProfileBtn.setOnClickListener(this);
        budgetBtn.setOnClickListener(this);
        categorybtn.setOnClickListener(this);
        paysBtn.setOnClickListener(this);
        logoutbtn.setOnClickListener(this);

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
                parentActivity.changeFragment(new CategoryFragment(), false);
                parentActivity.closeDrawerLayout();
                break;
            case R.id.paysBtn:
                parentActivity.changeFragment(new PaymentFragment(), false);
                parentActivity.closeDrawerLayout();
                break;
            case R.id.singoutBtn:
                Intent inicio = new Intent (parentActivity, LoginActivity.class);
                startActivity(inicio);
                parentActivity.finish();
                parentActivity.closeDrawerLayout();
                break;

        }


    }
}
