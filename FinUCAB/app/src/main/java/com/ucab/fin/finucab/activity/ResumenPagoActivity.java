package com.ucab.fin.finucab.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.fragment.ListaCategorias_Fragment;

/**
 * Created by Jeffrey on 16/05/2017.
 */

public class ResumenPagoActivity {
    private View parentView;
    private MainActivity parentActivity;

    protected void onCreate() {

        parentActivity.changeFragment(new ListaCategorias_Fragment(), false);
        parentActivity.closeDrawerLayout();

    }
}
