package com.an.inshorts.rest;

import android.content.Context;

import com.an.inshorts.BaseApplication;
import com.an.inshorts.callback.RESTListener;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import static com.an.inshorts.BaseConstants.BASE_URL;
import static com.an.inshorts.BaseConstants.PATH_NEWS_FEED;

public class RESTService {

    private static volatile RESTService instance;

    public static RESTService getInstance() {
        if (instance == null) {
            synchronized (RESTService.class) {
                if (instance == null) instance = new RESTService();
            }
        }
        return instance;
    }

    public void getNewsFeed(Context context, final RESTListener restListener) {
        String url = String.format("%s%s", BASE_URL, PATH_NEWS_FEED);

        RESTCacheRequest stringRequest = new RESTCacheRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        restListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        restListener.onError(error);
                    }
                });

        BaseApplication.getInstance(context).addToRequestQueue(stringRequest);
    }

}
