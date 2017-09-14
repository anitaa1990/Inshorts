package com.an.inshorts.service;

import android.content.Context;

import com.an.inshorts.callback.RESTListener;
import com.an.inshorts.listener.OnFeedChangeListener;
import com.android.volley.VolleyError;

public class FeedServiceImpl extends ResponseServiceImpl implements FeedService, RESTListener {

    private OnFeedChangeListener feedChangeListener;
    public FeedServiceImpl(Context context, OnFeedChangeListener feedChangeListener) {
        super(context);
        this.feedChangeListener = feedChangeListener;
    }

    @Override
    public void onSuccess(Object response) {

    }

    @Override
    public void onError(VolleyError error) {

    }
}
