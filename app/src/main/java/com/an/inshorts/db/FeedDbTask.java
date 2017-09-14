package com.an.inshorts.db;

import android.content.Context;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.model.Feed;

public class FeedDbTask implements Runnable, BaseConstants {

    private Context context;
    private String method;
    private Feed feed;
    public FeedDbTask(Context context, String method, Feed feed) {
        this.context = context;
        this.method = method;
        this.feed = feed;
    }

    @Override
    public void run() {
        if(TYPE_ADD_OFFLINE.equalsIgnoreCase(method)) {
            FeedModule.getInstance().addToOfflineFeeds(feed);

        } else if(TYPE_REMOVE_OFFLINE.equalsIgnoreCase(method)) {
            FeedModule.getInstance().removeFromOfflineFeeds(feed);

        } else if(TYPE_ADD_FAVOURITES.equalsIgnoreCase(method)) {
            FeedModule.getInstance().addToFavourites(feed);

        } else if(TYPE_REMOVE_FAVOURITES.equalsIgnoreCase(method)) {
            FeedModule.getInstance().removeFromFavourites(feed);
        }
    }
}
