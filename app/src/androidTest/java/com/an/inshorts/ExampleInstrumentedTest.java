package com.an.inshorts;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.an.inshorts.callback.RESTListener;
import com.an.inshorts.model.Feed;
import com.an.inshorts.rest.RESTAPITask;
import com.an.inshorts.rest.RESTExecutorService;
import com.an.inshorts.service.DataService;
import com.an.inshorts.service.DataServiceImpl;
import com.an.inshorts.service.FeedService;
import com.an.inshorts.utils.BaseUtils;
import com.an.inshorts.utils.CollectionUtils;
import com.android.volley.VolleyError;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.an.inshorts", appContext.getPackageName());
    }


    @Test
    public void getNewsFeedTest() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        RESTListener restListener = new RESTListener() {
            @Override
            public void onSuccess(Object response) {
                Log.d(BaseConstants.METHOD_NEWS_FEED, String.format("Success: %s", response.toString()));
            }

            @Override
            public void onError(VolleyError error) {
                Log.d(BaseConstants.METHOD_NEWS_FEED, String.format("Error: %s", error.getMessage()));
            }
        };

        RESTExecutorService.submit(new RESTAPITask(appContext, BaseConstants.METHOD_NEWS_FEED, restListener));
        Thread.sleep(2500);
    }


    @Test
    public void sortFeedByDateDescTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<Feed> feedList = BaseUtils.loadDummyData(appContext);
        List<Feed> sortedList = CollectionUtils.sortFeedDesc(feedList);
        for(Feed feed : sortedList) {
            System.out.println("-------------------");
            System.out.println(feed.getId());
            System.out.println(feed.getTimestamp());
        }
    }

    @Test
    public void sortFeedByDateAscTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<Feed> feedList = BaseUtils.loadDummyData(appContext);
        List<Feed> sortedList = CollectionUtils.sortFeedAsc(feedList);
        for(Feed feed : sortedList) {
            System.out.println("-------------------");
            System.out.println(feed.getId());
            System.out.println(feed.getTimestamp());
        }
    }

    @Test
    public void filterByCategoryTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<Feed> feedList = BaseUtils.loadDummyData(appContext);

        DataService dataService = new DataServiceImpl(appContext);
        Map<String, List<Feed>> filteredData = dataService.filterByCategory(feedList);
        for(Map.Entry<String, List<Feed>> s : filteredData.entrySet()) {
            System.out.println("-------------------");
            System.out.println(s.getKey());
            System.out.println(s.getValue().size());
        }
    }

    @Test
    public void filterByPublisherTest() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<Feed> feedList = BaseUtils.loadDummyData(appContext);

        DataService dataService = new DataServiceImpl(appContext);
        Map<String, List<Feed>> filteredData = dataService.filterByPublisher(feedList);
        for(Map.Entry<String, List<Feed>> s : filteredData.entrySet()) {
            System.out.println("-------------------");
            System.out.println(s.getKey());
            System.out.println(s.getValue().size());
        }
    }
}
