package com.an.inshorts.service;

import android.content.Context;
import com.an.inshorts.R;
import com.an.inshorts.callback.RESTListener;
import com.an.inshorts.listener.OnFeedChangeListener;
import com.an.inshorts.model.Feed;
import com.an.inshorts.rest.RESTAPITask;
import com.an.inshorts.rest.RESTExecutorService;
import com.an.inshorts.utils.BaseUtils;
import com.an.inshorts.utils.ConnectivityStatus;
import com.an.inshorts.utils.NavigatorUtils;
import com.android.volley.VolleyError;

import java.util.List;
import java.util.Map;

public class FeedServiceImpl extends ResponseServiceImpl implements FeedService, RESTListener {

    private OnFeedChangeListener feedChangeListener;
    public FeedServiceImpl(Context context) {
        super(context);
        this.context = context;
    }

    public FeedServiceImpl(Context context, OnFeedChangeListener feedChangeListener) {
        super(context);
        this.feedChangeListener = feedChangeListener;
    }

    @Override
    public void onSuccess(Object response) {
        /* Store the list in local cache
         * get 20 items from the list & update the UI
         * */
        List<Feed> feeds = BaseUtils.loadFeedData((String) response);
        addFeedToDb(feeds);
        Map<String, List<Feed>> feedsMap = filterByCategory(feeds);
        feedChangeListener.refreshFeed(feedsMap);
    }

    @Override
    public void onError(VolleyError error) {
        if(!ConnectivityStatus.isConnected(context)) {
            feedChangeListener.showError(context.getString(R.string.generic_no_internet_error));
            return;
        }
        feedChangeListener.showError(context.getString(R.string.generic_http_error));
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

        } else if(ACTION_TYPE_GET_FEED.equalsIgnoreCase(type)) {
            RESTExecutorService.submit(new RESTAPITask(context, METHOD_NEWS_FEED, this));
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

    @Override
    public List<Feed> sortFeed(String type, List<Feed> data) {
        if(context.getString(R.string.sort_item_1).equalsIgnoreCase(type)) {
            return sortFeedDesc(data);
        } else return sortFeedAsc(data);
    }

    @Override
    public Map<String, List<Feed>> filterFeed(String type, List<Feed> data) {
        if(context.getString(R.string.filter_item_1).equalsIgnoreCase(type)) {
            return filterByCategory(data);
        } else return filterByPublisher(data);
    }

    @Override
    public List<Feed> loadMoreFeed(String type, String name, int limit) {
        if(FILTER_BY_CATEGORY.equalsIgnoreCase(type))
            return getFeedsByCategory(name, limit);
        else return getFeedsByPublisher(name, limit);
    }
}
