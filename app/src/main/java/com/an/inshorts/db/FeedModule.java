package com.an.inshorts.db;


import com.an.inshorts.model.Feed;
import java.util.Map;


public class FeedModule {

    private static volatile FeedModule instance;

    public static FeedModule getInstance() {
        if (instance == null) {
            synchronized (FeedModule.class) {
                if (instance == null) instance = new FeedModule();
            }
        }
        return instance;
    }

    private FeedDb feedDb;
    private FeedModule() {
        feedDb = FeedDb.getInstance();
    }

    public void addToFavourites(Feed feed) {
        feedDb.addToFavFeeds(feed);
    }

    public void removeFromFavourites(Feed feed) {
        feedDb.removeFromFavFeeds(feed);
    }

    public boolean isFavourite(Long id) {
        return feedDb.isFavourite(id);
    }

    public Map<Long, Feed> getFavourites() {
        return feedDb.getFavFeeds();
    }

    public void addToOfflineFeeds(Feed feed) {
        feedDb.addToOfflineFeeds(feed);
    }

    public void removeFromOfflineFeeds(Feed feed) {
        feedDb.removeFromOfflineFeeds(feed);
    }

    public boolean isOffline(long id) {
        return feedDb.isOffline(id);
    }

    public Map<Long, Feed> getOfflineFeeds() {
        return feedDb.getOfflineFeeds();
    }
}
