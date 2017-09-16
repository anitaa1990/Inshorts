package com.an.inshorts.service;


import com.an.inshorts.model.Feed;

import java.util.List;
import java.util.Map;

public interface FeedService {

    void handleAction(String type, Feed feed, boolean checked);
    boolean isFavourite(Long id);
    boolean isOfflineFeed(Long id);
    List<Feed> sortFeed(String type, List<Feed> data);
    Map<String, List<Feed>> filterFeed(String type, List<Feed> data);
    Map<String, List<Feed>> fetchFavouriteFeeds();
    Map<String, List<Feed>> fetchOfflineFeeds();
    List<Feed> loadMoreFeed(String type, String name, int limit);
}
