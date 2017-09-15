package com.an.inshorts.service;


import com.an.inshorts.model.Feed;

public interface FeedService {

    void handleAction(String type, Feed feed, boolean checked);
    boolean isFavourite(Long id);
    boolean isOfflineFeed(Long id);
}
