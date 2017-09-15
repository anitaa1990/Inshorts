package com.an.inshorts.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.an.inshorts.R;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

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

    protected void updateToolbarTitle(String text) {
        toolbarTitle.setText(text);
        toolbarTitle.setTextSize(new Float(getResources().getDimension(R.dimen.font_xxxsmall)));
    }

    @Override
    public void onClick(View view) {

    }
}
