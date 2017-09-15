package com.an.inshorts.views;

/*
 * Page Transformer class to add animation in between page transitions
 *
 * */

import android.support.v4.view.ViewPager;
import android.view.View;

import com.an.inshorts.R;

public class CustomPageTransformer implements ViewPager.PageTransformer {



    @Override
    public void transformPage(View page, float position) {


        int pageWidth = (int) page.getWidth();
        final float pageWidthTimesPosition = pageWidth * position;
        final float absPosition = Math.abs(position);

        View text = page.findViewById(R.id.category_name);
        View image = page.findViewById(R.id.category_img);

        if (position <= -1.0f || position >= 1.0f) {

            // Page is not visible -- stop any running animations

        } else if (position == 0.0f) {

            // Page is selected -- reset any views if necessary

        } else {

            if(text != null) {
                text.setTranslationX(pageWidthTimesPosition * 0.5f);
            }
            if(image != null) {
                image.setTranslationX(pageWidthTimesPosition * 0.5f);
            }
        }

    }
}
