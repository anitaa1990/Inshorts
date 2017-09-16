package com.an.inshorts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseConstants {

    String BASE_URL = "http://starlord.hackerearth.com";
    String PATH_NEWS_FEED = "/newsjson";

    String METHOD_NEWS_FEED = "news_feed";
    String INTENT_URL = "webview_url";
    String INTENT_CATEGORY_NAME = "categoryName";

    String FILTER_BY_CATEGORY = "category";
    String FILTER_BY_PUBLISHER = "publisher";

    String ACTION_TYPE_FAV = "favourite";
    String ACTION_TYPE_OFFLINE = "offline";
    String ACTION_TYPE_URL = "open_url";
    String ACTION_TYPE_GET_FEED = "get_feed";

    String TYPE_ADD_OFFLINE = "add_offline";
    String TYPE_REMOVE_OFFLINE = "remove_offline";
    String TYPE_ADD_FAVOURITES = "add_favourites";
    String TYPE_REMOVE_FAVOURITES = "remove_favourites";
    String TYPE_ADD_FEED = "add_feed";

    int PAGE_SIZE = 20;

    String LOCALE_CACHE_PATH = "/data/data/com.an.inshorts/inshorts.dat";

    Map<String, String> CATEGORY = new HashMap()
    {{
        put("b", "Business");
        put("t", "Tech");
        put("e", "Movies");
        put("m", "Health");
    }};
}
