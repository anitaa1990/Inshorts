package com.an.inshorts.views;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.an.inshorts.R;

public class CustomWebViewClient extends WebViewClient {

    private WebView webView;
    private Activity activity;
    public CustomWebViewClient(WebView webView, Activity activity) {
        this.webView = webView;
        this.activity = activity;
    }

    @Override
    public void onLoadResource(WebView view, String url) {

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        webView.setVisibility(View.GONE);
        ViewStub stub = activity.findViewById(R.id.stub_import);
        if(stub != null) stub.inflate();
    }
}
