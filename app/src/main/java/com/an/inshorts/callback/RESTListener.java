package com.an.inshorts.callback;


import com.android.volley.VolleyError;

public interface RESTListener {

    void onSuccess(Object response);
    void onError(VolleyError error);
}
