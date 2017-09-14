package com.an.inshorts;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;
    private RequestQueue mRequestQueue;

    private static Context mCtx;

    private BaseApplication(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized BaseApplication getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BaseApplication(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
