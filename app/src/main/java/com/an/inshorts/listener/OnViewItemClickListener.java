package com.an.inshorts.listener;


public interface OnViewItemClickListener {
    void onFavClick(int position, boolean checked);
    void onOfflineClick(int position, boolean checked);
    void onViewClick(int position);
}
