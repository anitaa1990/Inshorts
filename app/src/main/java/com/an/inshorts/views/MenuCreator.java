package com.an.inshorts.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.an.inshorts.R;
import com.an.inshorts.adapter.MenuItemAdapter;
import com.an.inshorts.listener.MenuItemListener;
import com.an.inshorts.utils.BaseUtils;

import java.util.List;

public class MenuCreator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
            RecyclerItemClickListener.OnItemClickListener {

    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawerList;
    private NavigationView navigationView;

    private MenuItemAdapter menuItemAdapter;
    private MenuItemListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initNavigationDrawer(MenuItemListener listener) {
        this.listener = listener;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) findViewById(R.id.bottom_list);
        navigationView = (NavigationView) findViewById(R.id.nav_container);
        navigationView.setNavigationItemSelectedListener(this);

        setUpMenuList();
    }

    private void setUpMenuList() {
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        List<com.an.inshorts.model.MenuItem> menuItems = BaseUtils.getMenuItems(this);
        menuItemAdapter = new MenuItemAdapter(menuItems);
        mDrawerList.setAdapter(menuItemAdapter);
        mDrawerList.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    protected void dismissDrawer() {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    protected void openDrawer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    protected boolean isDrawerOpened() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void toggleDrawer() {
        if(isDrawerOpened()) dismissDrawer();
        else openDrawer();
    }

    @Override
    public void onItemClick(View childView, int position) {
        dismissDrawer();
        listener.onMenuItemClick(menuItemAdapter.getItem(position));
    }
}
