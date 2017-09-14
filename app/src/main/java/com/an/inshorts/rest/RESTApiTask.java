package com.an.inshorts.rest;


import android.content.Context;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.callback.RESTListener;

public class RESTAPITask implements Runnable, BaseConstants {

    private Context context;
    private String method;
    private RESTListener restListener;

    public RESTAPITask(Context context, String method, RESTListener restListener) {
        this.context = context;
        this.method = method;
        this.restListener = restListener;
    }

    @Override
    public void run() {
        if (METHOD_NEWS_FEED.equalsIgnoreCase(method)) {
            RESTService.getInstance().getNewsFeed(context, restListener);
        }
    }
}
