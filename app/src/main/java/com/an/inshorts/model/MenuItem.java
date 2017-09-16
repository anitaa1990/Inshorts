package com.an.inshorts.model;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private int mDrawableRes;
    private String mTitle;

    public MenuItem(int drawable, String title) {
        mDrawableRes = drawable;
        mTitle = title;
    }

    public int getDrawableResource() {
        return mDrawableRes;
    }

    public String getTitle() {
        return mTitle;
    }
}
