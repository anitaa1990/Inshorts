package com.an.inshorts.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.an.inshorts.R;
import com.an.inshorts.adapter.NewsListAdapter;
import com.an.inshorts.model.Feed;

import java.util.List;

public class FeedListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private NewsListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        initToolbar();
        updateToolbarTitle(getIntent().getStringExtra("categoryName"));

        recyclerView = (RecyclerView) findViewById(R.id.feed_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsListAdapter(this, (List<Feed>) getIntent().getSerializableExtra("feed"));
        recyclerView.setAdapter(adapter);
    }
}
