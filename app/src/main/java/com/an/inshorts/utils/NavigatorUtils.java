package com.an.inshorts.utils;

import android.content.Context;
import android.content.Intent;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.activity.CustomWebViewActivity;
import com.an.inshorts.model.Feed;

public class NavigatorUtils implements BaseConstants {

    public static void openWebView(Context context, Feed feed) {
        Intent intent = new Intent(context, CustomWebViewActivity.class);
        intent.putExtra(INTENT_URL, feed.getUrl());
        intent.putExtra(INTENT_CATEGORY_NAME, CATEGORY.get(feed.getCategory()));
        context.startActivity(intent);
    }
}
