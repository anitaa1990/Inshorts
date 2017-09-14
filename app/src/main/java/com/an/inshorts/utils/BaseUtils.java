package com.an.inshorts.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class BaseUtils {

    public static void showSnackBar(String message,
                                    View view) {
        Snackbar.make(view, message,Snackbar.LENGTH_LONG).show();
    }
}
