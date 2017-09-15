package com.an.inshorts.service;

import android.content.Context;

import com.an.inshorts.callback.RESTListener;
import com.an.inshorts.listener.OnFeedChangeListener;
import com.an.inshorts.model.Feed;
import com.an.inshorts.utils.NavigatorUtils;
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

    @Override
    public void handleAction(String type, Feed feed, boolean checked) {
        if(ACTION_TYPE_FAV.equalsIgnoreCase(type)) {
            if(checked) addToFavourites(feed);
            else removeFromFavourites(feed);

        } else if(ACTION_TYPE_OFFLINE.equalsIgnoreCase(type)) {
            if(checked) addToOfflineFeed(feed);
            else removeFromOfflineFeed(feed);

        } else if(ACTION_TYPE_URL.equalsIgnoreCase(type)) {
            NavigatorUtils.openWebView(context, feed);
        }
    }

    @Override
    public boolean isFavourite(Long id) {
        return isAddedToFavourite(id);
    }

    @Override
    public boolean isOfflineFeed(Long id) {
        return isAvailableOffline(id);
    }
}
