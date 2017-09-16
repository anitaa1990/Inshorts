package com.an.inshorts.service;


import android.content.Context;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.db.DbExecutorService;
import com.an.inshorts.db.FeedDbTask;
import com.an.inshorts.model.Feed;
import com.an.inshorts.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/*
 * Class to handle/access data
 *
 * */
public class DataServiceImpl extends AbstractServiceImpl implements DataService {


    public DataServiceImpl(Context context) {
        super(context);
    }

    @Override
    public List<Feed> sortFeedAsc(List<Feed> data) {
        return CollectionUtils.sortFeedAsc(data);
    }

    @Override
    public List<Feed> sortFeedDesc(List<Feed> data) {
        return CollectionUtils.sortFeedDesc(data);
    }

    @Override
    public Map<String, List<Feed>> filterByCategory(List<Feed> data) {
        Map<String, List<Feed>> finalMap = new HashMap<>();
        if(data == null) data = getFeeds();
        for(Feed feed : data) {
            if (!finalMap.containsKey(feed.getCategory())) {
                List<Feed> list = new ArrayList<Feed>();
                list.add(feed);

                finalMap.put(feed.getCategory(), list);
            } else {
                finalMap.get(feed.getCategory()).add(feed);
            }
        }
        return finalMap;
    }

    @Override
    public Map<String, List<Feed>> filterByPublisher(List<Feed> data) {
        Map<String, List<Feed>> finalMap = new HashMap<>();
        if(data == null) data = getFeeds();
        for(Feed feed : data) {
            if (!finalMap.containsKey(feed.getPublisher())) {
                List<Feed> list = new ArrayList<Feed>();
                list.add(feed);

                finalMap.put(feed.getPublisher(), list);
            } else {
                finalMap.get(feed.getPublisher()).add(feed);
            }
        }
        return finalMap;
    }

    @Override
    public List<Feed> getFeedsByCategory(String name, int limit) {
        List<Feed> finalList = new ArrayList<>();
        List<Feed> feeds = getFeeds().subList(limit, (limit+PAGE_SIZE));
        for(Feed feed : feeds) {
            if(name.equalsIgnoreCase(feed.getCategory()))
                finalList.add(feed);
        }
        return finalList;
    }

    @Override
    public List<Feed> getFeedsByPublisher(String name, int limit) {
        List<Feed> finalList = new ArrayList<>();
        List<Feed> feeds = getFeeds().subList(limit, (limit+PAGE_SIZE));
        for(Feed feed : feeds) {
            if(name.equalsIgnoreCase(feed.getPublisher()))
                finalList.add(feed);
        }
        return finalList;
    }


    @Override
    public List<Feed> getFavouriteFeeds() {
        List<Feed> feeds = new ArrayList<>();
        Map<Long, Feed> faves = feedModule.getFavourites();

        Iterator<Map.Entry<Long, Feed>> iterator = faves.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Feed> entry = iterator.next();
            feeds.add(entry.getValue());
        }
        return feeds;
    }



    @Override
    public List<Feed> getOfflineFeeds() {
        List<Feed> feeds = new ArrayList<>();
        Map<Long, Feed> faves = feedModule.getOfflineFeeds();

        Iterator<Map.Entry<Long, Feed>> iterator = faves.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Feed> entry = iterator.next();
            feeds.add(entry.getValue());
        }
        return feeds;
    }

    @Override
    public List<Feed> getFeeds() {
        return feedModule.getFeedsFromDb();
    }

    @Override
    public void addFeedToDb(List<Feed> feeds) {
        DbExecutorService.submit(new FeedDbTask(context, BaseConstants.TYPE_ADD_FEED, feeds));
    }

    @Override
    public void addToFavourites(Feed feed) {
        DbExecutorService.submit(new FeedDbTask(context, BaseConstants.TYPE_ADD_FAVOURITES, feed));
    }

    @Override
    public void removeFromFavourites(Feed feed) {
        DbExecutorService.submit(new FeedDbTask(context, BaseConstants.TYPE_REMOVE_FAVOURITES, feed));
    }

    @Override
    public void addToOfflineFeed(Feed feed) {
        DbExecutorService.submit(new FeedDbTask(context, BaseConstants.TYPE_ADD_OFFLINE, feed));
    }

    @Override
    public void removeFromOfflineFeed(Feed feed) {
        DbExecutorService.submit(new FeedDbTask(context, BaseConstants.TYPE_REMOVE_OFFLINE, feed));
    }

    @Override
    public boolean isAddedToFavourite(Long id) {
        return feedModule.isFavourite(id);
    }

    @Override
    public boolean isAvailableOffline(Long id) {
        return feedModule.isOffline(id);
    }
}
