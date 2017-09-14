package com.an.inshorts.service;


import android.content.Context;

import com.an.inshorts.model.Feed;
import com.an.inshorts.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
}
