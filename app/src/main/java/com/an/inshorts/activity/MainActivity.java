package com.an.inshorts.activity;

import android.animation.ArgbEvaluator;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.an.inshorts.R;
import com.an.inshorts.adapter.MainPagerAdapter;
import com.an.inshorts.fragment.MainFragment;
import com.an.inshorts.listener.OnFeedChangeListener;
import com.an.inshorts.model.Feed;
import com.an.inshorts.model.MenuItem;
import com.an.inshorts.service.FeedService;
import com.an.inshorts.service.FeedServiceImpl;
import com.an.inshorts.utils.BaseUtils;
import com.an.inshorts.views.CustomPageTransformer;
import com.an.inshorts.views.CustomViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity implements OnFeedChangeListener, ViewPager.OnPageChangeListener {

    private ImageView imgCategory;
    private View progressView;

    private CustomViewPager viewPager;
    private MainPagerAdapter pagerAdapter;

    private List<MainFragment> fragments = new ArrayList<>();

    private int [] colors;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private FeedService feedService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        updateMenuButton();
        displayFilterBtn();
        disableFilterBtn();
        updateToolbarTitle(getString(R.string.app_name));

        feedService = new FeedServiceImpl(this, this);
        feedService.handleAction(ACTION_TYPE_GET_FEED, null, false);

        progressView = findViewById(R.id.progress_view);
        progressView.setVisibility(View.VISIBLE);
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        imgCategory = (ImageView) findViewById(R.id.img_category);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new CustomPageTransformer());

        colors = getResources().getIntArray(R.array.colors);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onMenuItemClick(MenuItem item) {
        MainFragment fragment = (MainFragment) pagerAdapter.getItem(viewPager.getCurrentItem());
        Map<String, List<Feed>> filterFeed;
        if(getString(R.string.menu_item_1).equalsIgnoreCase(item.getTitle())) {
            filterFeed = feedService.fetchFavouriteFeeds();

        } else if(getString(R.string.menu_item_1).equalsIgnoreCase(item.getTitle())) {
            filterFeed = feedService.fetchOfflineFeeds();

        } else {
            filterFeed = feedService.filterFeed(item.getTitle(), fragment.getFeeds());
        }
        if(filterFeed != null) refreshData(filterFeed);
    }

    private void refreshData(Map<String, List<Feed>> feeds) {
        progressView.setVisibility(View.GONE);
        enableFilterBtn();
        int i = 0;
        if(pagerAdapter != null) pagerAdapter.removeFragments();
        for(Map.Entry<String, List<Feed>> entry : feeds.entrySet()) {
            List<Feed> subList = new ArrayList<>();
            if(entry.getValue().size() <= PAGE_SIZE) subList.addAll(entry.getValue());
            else subList.addAll(entry.getValue().subList(0, PAGE_SIZE));
            pagerAdapter.addFragment(MainFragment.newInstance(i, entry.getKey(), subList));
            i++;
        }
        pagerAdapter.refreshAdapter();
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position +1 < colors.length) {
            Drawable mDrawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_category_3);
            mDrawable.setColorFilter(new PorterDuffColorFilter((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]), PorterDuff.Mode.SRC_IN));
            imgCategory.setImageDrawable(mDrawable);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void showError(String message) {
        BaseUtils.showSnackBar(message, findViewById(R.id.root_view));
    }

    @Override
    public void refreshFeed(Map<String, List<Feed>> data) {
        refreshData(data);
    }
}
