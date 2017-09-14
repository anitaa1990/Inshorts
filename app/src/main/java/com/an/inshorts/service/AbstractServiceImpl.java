package com.an.inshorts.service;

import android.content.Context;

import com.an.inshorts.db.FeedModule;

public abstract class AbstractServiceImpl {

    protected Context context;
    protected FeedModule feedModule;
    public AbstractServiceImpl(Context context) {
        this.context = context;
        this.feedModule = FeedModule.getInstance();
    }
}
