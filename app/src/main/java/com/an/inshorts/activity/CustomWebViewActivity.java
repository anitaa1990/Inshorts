package com.an.inshorts.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.an.inshorts.R;
import com.an.inshorts.model.MenuItem;
import com.an.inshorts.utils.ConnectivityStatus;
import com.an.inshorts.views.CustomWebViewClient;

import static com.an.inshorts.BaseConstants.INTENT_CATEGORY_NAME;
import static com.an.inshorts.BaseConstants.INTENT_URL;

public class CustomWebViewActivity extends BaseActivity {

    private WebView webView;
    private View progressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        initToolbar();
        updateToolbarTitle(getIntent().getStringExtra(INTENT_CATEGORY_NAME));
        hideFilters();
        updateBackButton();

        progressView = findViewById(R.id.progress_view);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new CustomWebViewClient());
        webView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);

        int cacheMode = ConnectivityStatus.isConnected(this) ? WebSettings.LOAD_DEFAULT : WebSettings.LOAD_CACHE_ELSE_NETWORK;
        webView.getSettings().setCacheMode(cacheMode);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress >= 90) progressView.setVisibility(View.GONE);
            }
        });

        webView.loadUrl(getIntent().getStringExtra(INTENT_URL));
    }

    @Override
    public void onMenuItemClick(MenuItem item) {

    }
}
