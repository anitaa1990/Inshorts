package com.an.inshorts.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.an.inshorts.R;
import com.an.inshorts.adapter.NewsListAdapter;
import com.an.inshorts.model.Feed;
import com.an.inshorts.views.RecyclerItemClickListener;

import java.util.List;

import static com.an.inshorts.BaseConstants.CATEGORY;
import static com.an.inshorts.BaseConstants.INTENT_CATEGORY_NAME;
import static com.an.inshorts.BaseConstants.INTENT_URL;

public class FeedListActivity extends BaseActivity implements RecyclerItemClickListener.OnItemClickListener {

    private RecyclerView recyclerView;
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        initToolbar();
        updateToolbarTitle(getIntent().getStringExtra("categoryName"));
        updateBackButton();
        displaySortBtn();

        recyclerView = (RecyclerView) findViewById(R.id.feed_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsListAdapter(this, (List<Feed>) getIntent().getSerializableExtra("feed"));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
    }

    @Override
    public void onItemClick(View childView, int position) {
        Feed feed = adapter.getItem(position);
        Intent intent = new Intent(getApplicationContext(), CustomWebViewActivity.class);
        intent.putExtra(INTENT_URL, feed.getUrl());
        intent.putExtra(INTENT_CATEGORY_NAME, CATEGORY.get(feed.getCategory()));
        startActivity(intent);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
