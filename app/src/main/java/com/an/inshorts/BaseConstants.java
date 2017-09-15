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


    String TYPE_ADD_OFFLINE = "add_offline";
    String TYPE_REMOVE_OFFLINE = "remove_offline";
    String TYPE_ADD_FAVOURITES = "add_favourites";
    String TYPE_REMOVE_FAVOURITES = "remove_favourites";


    String LOCALE_CACHE_PATH = "/data/data/com.an.inshorts/inshorts.dat";

    Map<String, String> CATEGORY = new HashMap()
    {{
        put("b", "Business");
        put("t", "Tech");
        put("e", "Movies");
        put("m", "Health");
    }};
}
