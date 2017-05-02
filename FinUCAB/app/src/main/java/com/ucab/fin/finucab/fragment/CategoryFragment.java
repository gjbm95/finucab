package com.ucab.fin.finucab.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucab.fin.finucab.R;
import com.ucab.fin.finucab.activity.MainActivity;

public class CategoryFragment extends Fragment {

    private View parentView;
    private MainActivity parentActivity;
    private AppBarLayout appBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.category_fragment, container, false);
        parentActivity = (MainActivity) getActivity();
        //appBar = (AppBarLayout) parentView.findViewById(R.id.appbar);
        //appBar.enable


        return parentView;
    }

}
