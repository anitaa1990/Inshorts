package com.an.inshorts.utils;

import android.content.Context;
import android.content.Intent;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.activity.CustomWebViewActivity;
import com.an.inshorts.activity.FeedListActivity;
import com.an.inshorts.model.Feed;

import java.io.Serializable;
import java.util.List;

public class NavigatorUtils implements BaseConstants {

    public static void openWebView(Context context, Feed feed) {
        Intent intent = new Intent(context, CustomWebViewActivity.class);
        intent.putExtra(INTENT_URL, feed.getUrl());
        intent.putExtra(INTENT_CATEGORY_NAME, CATEGORY.get(feed.getCategory()));
        context.startActivity(intent);
    }

    public static void openFeedScreen(Context context, String name, List<Feed> data) {
        Intent intent = new Intent(context, FeedListActivity.class);
        intent.putExtra(INTENT_FEED, (Serializable) data);
        intent.putExtra(INTENT_CATEGORY_NAME, name);
        context.startActivity(intent);
    }
}
