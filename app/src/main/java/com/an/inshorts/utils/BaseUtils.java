package com.an.inshorts.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.model.Feed;
import com.an.inshorts.model.MenuItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseUtils implements BaseConstants {

    /*
     * Method to display SnackBar
     * implementation
     * */

    public static void showSnackBar(String message,
                                    View view) {
        Snackbar.make(view, message,Snackbar.LENGTH_LONG).show();
    }


    /*
     * Method to read json file from raw folder
     * & return a string value
     * */

    public static String getJSONStringFromRaw(Context context, int rawId) {

        InputStream content = context.getResources().openRawResource(rawId);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
        String respString = "";
        try {
            String s = "";
            while ((s = buffer.readLine()) != null) {
                respString += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respString;
    }


    /*
     * Method to read the json string from response
     * The string is converted into a list using gson library
     * */

    public static List<Feed> loadFeedData(String requestJson) {
        Type listType = new TypeToken<List<Feed>>() {}.getType();
        List<Feed> feeds = new Gson().fromJson(requestJson, listType);
        return feeds;
    }

    public static List<MenuItem> loadMenuItems(Context context, int raw) {
        String menuJson = getJSONStringFromRaw(context, raw);
        Type listType = new TypeToken<List<Feed>>() {}.getType();
        List<MenuItem> menuItems = new Gson().fromJson(menuJson, listType);
        return menuItems;
    }

    public static List<MenuItem> addMenuItems(List<String> items) {
        List<MenuItem> menuItems = new ArrayList<>();
        for(String s : items) {
            MenuItem menuItem = new MenuItem(0, s);
            menuItems.add(menuItem);
        }
        return menuItems;
    }

    public static String getCurrentGroup(String name) {
       return CATEGORY.get(name) == null ? FILTER_BY_PUBLISHER : FILTER_BY_CATEGORY;
    }


    /*
     * You can use this method to store the
     * request response from your local cache
     * */

    public static void writeObjectToDisk(final Object object) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ObjectUtil objDataStream = new ObjectUtil();
                objDataStream.writeObjects(object, LOCALE_CACHE_PATH);
            }
        }).start();
    }



    /*
     * You can use this method to retrieve the
     * request response from your local cache
     * */

    public static Object readObjectFromDisk() {
        ObjectUtil objDataStream = new ObjectUtil();
        return objDataStream.readObjects(LOCALE_CACHE_PATH);
    }
}
