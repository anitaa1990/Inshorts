package com.an.inshorts.service;

import android.content.Context;

public abstract class AbstractServiceImpl {

    protected Context context;
    public AbstractServiceImpl(Context context) {
        this.context = context;
    }
}
