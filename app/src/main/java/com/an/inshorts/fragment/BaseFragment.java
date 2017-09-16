package com.an.inshorts.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.an.inshorts.BaseConstants;

public class BaseFragment extends Fragment implements BaseConstants {

    protected Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }
}
