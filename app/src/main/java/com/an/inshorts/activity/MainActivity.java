package com.an.inshorts.activity;

import android.animation.ArgbEvaluator;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity implements OnFeedChangeListener, ViewPager.OnPageChangeListener {

    private ImageView imgCategory;

    private CustomViewPager viewPager;
    private MainPagerAdapter pagerAdapter;
    private List<Feed> feedList;

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
        updateToolbarTitle(getString(R.string.app_name));

        feedService = new FeedServiceImpl(this, this);

        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        imgCategory = (ImageView) findViewById(R.id.img_category);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        feedList = BaseUtils.loadDummyData(this);
        Map<String, List<Feed>> feeds = feedService.filterFeed(getString(R.string.filter_item_1), feedList);
        refreshData(feeds);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new CustomPageTransformer());

        colors = getResources().getIntArray(R.array.colors);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onItemClick(MenuItem item) {
        Map<String, List<Feed>> filterFeed = feedService.filterFeed(item.getTitle(), feedList);
        refreshData(filterFeed);
    }

    private void refreshData(Map<String, List<Feed>> feeds) {
        int i = 0;
        if(pagerAdapter != null) pagerAdapter.removeFragments();
        for(Map.Entry<String, List<Feed>> entry : feeds.entrySet()) {
            pagerAdapter.addFragment(MainFragment.newInstance(i, entry.getKey(), entry.getValue()));
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
}
