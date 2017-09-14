package com.an.inshorts.utils;

import com.an.inshorts.model.Feed;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtils {

    public static List<Feed> sortFeedAsc(List<Feed> data) {
        Collections.sort(data, new Comparator<Feed>() {
            public int compare(Feed o1, Feed o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });
        return data;
    }

    public static List<Feed> sortFeedDesc(List<Feed> data) {
        List<Feed> sortedData = sortFeedAsc(data);
        Collections.reverse(sortedData);
        return sortedData;
    }
}
