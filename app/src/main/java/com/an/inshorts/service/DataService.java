package com.an.inshorts.service;

import com.an.inshorts.model.Feed;

import java.util.List;
import java.util.Map;

public interface DataService {

    Map<String, List<Feed>> filterByCategory(List<Feed> data);
    Map<String, List<Feed>> filterByPublisher(List<Feed> data);
    List<Feed> getFeedsByCategory(String name, int limit);
    List<Feed> getFeedsByPublisher(String name, int limit);
    List<Feed> sortFeedAsc(List<Feed> data);
    List<Feed> sortFeedDesc(List<Feed> data);
    List<Feed> getFavouriteFeeds();
    List<Feed> getOfflineFeeds();
    List<Feed> getFeeds();
    void addFeedToDb(List<Feed> feeds);
    void addToFavourites(Feed feed);
    void removeFromFavourites(Feed feed);
    void addToOfflineFeed(Feed feed);
    void removeFromOfflineFeed(Feed feed);
    boolean isAddedToFavourite(Long id);
    boolean isAvailableOffline(Long id);
}
