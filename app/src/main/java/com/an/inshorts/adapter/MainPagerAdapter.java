package com.an.inshorts.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.an.inshorts.fragment.MainFragment;

import java.util.List;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    List<MainFragment> fragments;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainPagerAdapter(FragmentManager fm,
                            List<MainFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        MainFragment fragment = fragments.get(position);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        int position = fragments.indexOf(object);
        if (position >= 0) {
            return position;
        } else return POSITION_NONE;
    }

    public void addFragment(MainFragment fragment) {
        fragments.add(fragment);
    }

    public void removeFragments() {
        fragments.clear();
        refreshAdapter();
    }

    public void refreshAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
