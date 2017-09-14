package com.an.inshorts.db;

import com.an.inshorts.model.Feed;
import com.an.inshorts.utils.BaseUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class FeedDb implements Serializable {

    private static FeedDb instance;
    public static FeedDb getInstance() {
        if(instance == null) readFromDisk();
        return instance;
    }

    private static void readFromDisk() {
        instance = (FeedDb) BaseUtils.readObjectFromDisk();
        if (instance == null) {
            instance = new FeedDb();
            saveToDisk();
        }
    }

    private static void saveToDisk() {
        BaseUtils.writeObjectToDisk(instance);
    }

    private Map<Long, Feed> favFeeds;
    private Map<Long, Feed> offlineFeeds;

    public Map<Long, Feed> getFavFeeds() {
        if(favFeeds == null) favFeeds = new HashMap<>();
        return favFeeds;
    }

    public void setFavFeeds(Map<Long, Feed> favFeeds) {
        this.favFeeds = favFeeds;
        saveToDisk();
    }

    public void addToFavFeeds(Feed feed) {
        Map<Long, Feed> faves = getFavFeeds();
        faves.put(feed.getId(), feed);
        setFavFeeds(faves);
    }

    public void removeFromFavFeeds(Feed feed) {
        Map<Long, Feed> faves = getFavFeeds();
        if(faves.containsKey(feed.getId()))
            faves.remove(feed.getId());
        setFavFeeds(faves);
    }

    public boolean isFavourite(long feedId) {
        return getFavFeeds().containsKey(feedId);
    }


    public Map<Long, Feed> getOfflineFeeds() {
        if(offlineFeeds == null) offlineFeeds = new HashMap<>();
        return offlineFeeds;
    }

    public void setOfflineFeeds(Map<Long, Feed> offlineFeeds) {
        this.offlineFeeds = offlineFeeds;
        saveToDisk();
    }

    public void addToOfflineFeeds(Feed feed) {
        Map<Long, Feed> offlineList = getOfflineFeeds();
        offlineList.put(feed.getId(), feed);
        setOfflineFeeds(offlineList);
    }

    public void removeFromOfflineFeeds(Feed feed) {
        Map<Long, Feed> offlineMap = getOfflineFeeds();
        if(offlineMap.containsKey(feed.getId()))
            offlineMap.remove(feed.getId());
        setOfflineFeeds(offlineMap);
    }

    public boolean isOffline(long feedId) {
        return getOfflineFeeds().containsKey(feedId);
    }
}
