package com.an.inshorts.activity;


import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

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

public class FeedListActivity extends BaseActivity implements OnViewItemClickListener, OnFeedChangeListener, SearchView.OnQueryTextListener, BaseConstants {

    private SearchView searchView;

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
        searchView = (SearchView) findViewById(R.id.search);

        initFeed();
        initSearch();
    }

    private void initFeed() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feeds = (List<Feed>) getIntent().getSerializableExtra("feed");
        adapter = new NewsListAdapter(this, feeds, feedService, this);
        recyclerView.setAdapter(adapter);
    }


    private void initSearch() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setIconifiedByDefault(false);

        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextSize(new Float(getResources().getDimension(R.dimen.font_xxxxxsmall)));
        searchEditText.setTextColor(getResources().getColor(R.color.toolbar_text_color));
        searchEditText.setHintTextColor(getResources().getColor(R.color.feed_source_text_color));
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/tiempos_h_medium.otf");
        searchEditText.setTypeface(myCustomFont);
        searchView.setOnQueryTextListener(this);
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText.isEmpty()) feeds = (List<Feed>) getIntent().getSerializableExtra("feed");
        adapter.getFilter().filter(newText);
        return true;
    }
}
