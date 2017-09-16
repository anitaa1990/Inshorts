package com.an.inshorts.listener;


import com.an.inshorts.model.Feed;

import java.util.List;
import java.util.Map;

public interface OnFeedChangeListener {

    void showError(String message);
    void refreshFeed(Map<String, List<Feed>> data);
}
