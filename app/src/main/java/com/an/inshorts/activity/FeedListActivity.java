package com.an.inshorts.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.R;
import com.an.inshorts.adapter.NewsListAdapter;
import com.an.inshorts.listener.OnFeedChangeListener;
import com.an.inshorts.listener.OnViewItemClickListener;
import com.an.inshorts.model.Feed;
import com.an.inshorts.model.MenuItem;
import com.an.inshorts.service.FeedService;
import com.an.inshorts.service.FeedServiceImpl;

import java.util.List;

public class FeedListActivity extends BaseActivity implements OnViewItemClickListener, OnFeedChangeListener, BaseConstants {

    private RecyclerView recyclerView;
    private NewsListAdapter adapter;
    private List<Feed> feeds;

    private FeedService feedService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        initToolbar();
        updateToolbarTitle(getIntent().getStringExtra(INTENT_CATEGORY_NAME));
        updateBackButton();
        displaySortBtn();

        feedService = new FeedServiceImpl(this, this);

        recyclerView = (RecyclerView) findViewById(R.id.feed_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feeds = (List<Feed>) getIntent().getSerializableExtra("feed");
        adapter = new NewsListAdapter(this, feeds, feedService, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onFavClick(int position, boolean checked) {
        feedService.handleAction(ACTION_TYPE_FAV, adapter.getItem(position), checked);
    }

    @Override
    public void onOfflineClick(int position, boolean checked) {
        feedService.handleAction(ACTION_TYPE_OFFLINE, adapter.getItem(position), checked);
    }

    @Override
    public void onViewClick(int position) {
        feedService.handleAction(ACTION_TYPE_URL, adapter.getItem(position), false);
    }


    @Override
    public void onItemClick(MenuItem item) {
        List<Feed> sortedFeed = feedService.sortFeed(item.getTitle(), feeds);
        adapter.updateList(sortedFeed);
    }
}
