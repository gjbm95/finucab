package com.ucab.fin.finucab.activity;

import android.view.View;

import com.ucab.fin.finucab.fragment.ListaCategorias_Fragment;

/**
 * Created by Juan on 16-05-2017.
 */

public class ListaCategoryActiviy {

    private View parentView;
    private MainActivity parentActivity;

    public void onCreate() {


                parentActivity.changeFragment(new ListaCategorias_Fragment(), false);
                parentActivity.closeDrawerLayout();


        }


    }

