package com.an.inshorts.service;

import com.an.inshorts.model.Feed;

import java.util.List;
import java.util.Map;

public interface DataService {

    Map<String, List<Feed>> filterByCategory(List<Feed> data);
    Map<String, List<Feed>> filterByPublisher(List<Feed> data);
    List<Feed> sortFeedAsc(List<Feed> data);
    List<Feed> sortFeedDesc(List<Feed> data);
    List<Feed> getFavouriteFeeds();
    List<Feed> getOfflineFeeds();
}
