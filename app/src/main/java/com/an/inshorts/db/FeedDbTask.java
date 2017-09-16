package com.an.inshorts.db;

import android.content.Context;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.model.Feed;

import java.util.List;

public class FeedDbTask implements Runnable, BaseConstants {

    private Context context;
    private String method;
    private Feed feed;
    private List<Feed> feeds;
    public FeedDbTask(Context context, String method, Feed feed) {
        this.context = context;
        this.method = method;
        this.feed = feed;
    }

    public FeedDbTask(Context context, String method, List<Feed> feeds) {
        this.context = context;
        this.method = method;
        this.feeds = feeds;
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

        } else if(TYPE_ADD_FEED.equalsIgnoreCase(method)) {
            FeedModule.getInstance().addFeeds(feeds);
        }
    }
}
