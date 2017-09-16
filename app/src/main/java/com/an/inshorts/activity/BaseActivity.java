package com.an.inshorts.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.an.inshorts.BaseConstants;
import com.an.inshorts.R;
import com.an.inshorts.dialogs.BottomSheetHelper;
import com.an.inshorts.dialogs.CustomBottomSheetDialog;
import com.an.inshorts.listener.MenuItemListener;
import com.an.inshorts.utils.BaseUtils;
import com.an.inshorts.views.MenuCreator;

import java.util.Arrays;

public abstract class BaseActivity extends MenuCreator implements View.OnClickListener, MenuItemListener, BaseConstants {

    protected Toolbar toolbar;
    protected TextView toolbarTitle;
    private ImageView menuBtn;
    private ImageView filterBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        menuBtn = toolbar.findViewById(R.id.btn_back);
        menuBtn.setOnClickListener(this);
        filterBtn = toolbar.findViewById(R.id.btn_filter);
        filterBtn.setOnClickListener(this);
    }

    protected void disableFilterBtn() {
        filterBtn.setEnabled(false);
    }

    protected void enableFilterBtn() {
        filterBtn.setEnabled(true);
    }

    protected void updateBackButton() {
        menuBtn.setTag(R.drawable.ic_back);
        menuBtn.setImageResource(R.drawable.ic_back);
    }

    protected void updateMenuButton() {
        menuBtn.setTag(R.drawable.ic_side_menu);
        menuBtn.setImageResource(R.drawable.ic_side_menu);
    }

    protected void updateToolbarTitle(String text) {
        toolbarTitle.setText(text);
        toolbarTitle.setTextSize(new Float(getResources().getDimension(R.dimen.font_xxxsmall)));
    }

    protected void hideFilters() {
        filterBtn.setVisibility(View.GONE);
    }

    protected void showFilters() {
        filterBtn.setVisibility(View.VISIBLE);
    }

    protected void displaySortBtn() {
        filterBtn.setTag(R.drawable.ic_sort);
        filterBtn.setImageResource(R.drawable.ic_sort);
    }

    protected void displayFilterBtn() {
        filterBtn.setTag(R.drawable.ic_filters);
        filterBtn.setImageResource(R.drawable.ic_filters);
    }

    @Override
    public void onClick(View view) {
        if(view == filterBtn) {
            int tag = (Integer) filterBtn.getTag();
            switch (tag) {
                case R.drawable.ic_sort :
                    BottomSheetHelper.getInstance().showBottomSheet(BaseActivity.this,
                            BaseUtils.addMenuItems(Arrays.asList(getResources().getStringArray(R.array.sort_items))), this);
                    break;

                case R.drawable.ic_filters :
                    BottomSheetHelper.getInstance().showBottomSheet(BaseActivity.this,
                            BaseUtils.addMenuItems(Arrays.asList(getResources().getStringArray(R.array.filter_items))), this);
                    break;
            }

        } else if(view == menuBtn) {
            int tag = (Integer) menuBtn.getTag();
            switch (tag) {
                case R.drawable.ic_side_menu :
                    initNavigationDrawer(BaseActivity.this);
                    toggleDrawer();
                    break;

                case R.drawable.ic_back :
                    onBackPressed();
                    break;
            }

        }
    }
}
