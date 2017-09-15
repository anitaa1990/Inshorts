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
import com.an.inshorts.model.Feed;
import com.an.inshorts.service.DataServiceImpl;
import com.an.inshorts.utils.BaseUtils;
import com.an.inshorts.views.CustomPageTransformer;
import com.an.inshorts.views.CustomViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends BaseActivity {

    private ImageView imgCategory;

    private CustomViewPager viewPager;
    private MainPagerAdapter pagerAdapter;
    private List<MainFragment> fragments = new ArrayList<>();

    private int [] colors;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        List<Feed> dummyData = BaseUtils.loadDummyData(this);
        Map<String, List<Feed>> feeds = new DataServiceImpl(this).filterByCategory(dummyData);
        int i = 0;
        for(Map.Entry<String, List<Feed>> entry : feeds.entrySet()) {
            fragments.add(MainFragment.newInstance(i, entry.getKey(), entry.getValue()));
            i++;
        }

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new CustomPageTransformer());

        imgCategory = (ImageView) findViewById(R.id.img_category);

        colors = getResources().getIntArray(R.array.colors);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position +1 < colors.length) {
                    Drawable mDrawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.ic_category_3);
                    mDrawable.setColorFilter(new PorterDuffColorFilter((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]), PorterDuff.Mode.SRC_IN));
                    imgCategory.setImageDrawable(mDrawable);
                }
            }

            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
}
