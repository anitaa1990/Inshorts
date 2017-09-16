package com.an.inshorts.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.an.inshorts.R;
import com.an.inshorts.model.MenuItem;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.CustomViewHolder> {

    private List<MenuItem> mItems;

    public MenuItemAdapter(List<MenuItem> items) {
        mItems = items;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        MenuItem menuItem = mItems.get(position);
        holder.setData(menuItem);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public MenuItem getItem(int position) {
        return mItems.get(position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        private MenuItem menuItem;
        public CustomViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.menu_img);
            textView = itemView.findViewById(R.id.menu_item);
        }

        public void setData(MenuItem menuItem) {
            this.menuItem = menuItem;
            if(menuItem.getDrawableResource() != 0 )
                    imageView.setImageResource(menuItem.getDrawableResource());
            else imageView.setVisibility(View.INVISIBLE);

            textView.setText(menuItem.getTitle());
        }
    }
}
