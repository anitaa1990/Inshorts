package com.an.inshorts.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.an.inshorts.R;
import com.an.inshorts.listener.OnViewItemClickListener;
import com.an.inshorts.model.Feed;
import com.an.inshorts.service.FeedService;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.CustomViewHolder> implements Filterable {

    private Context context;
    private List<Feed> feedList;
    private List<Feed> filteredList;
    private FeedService feedService;
    private OnViewItemClickListener clickListener;
    public NewsListAdapter(Context context, List<Feed> feedList, FeedService feedService, OnViewItemClickListener clickListener) {
        this.context = context;
        this.feedList = feedList;
        this.filteredList = feedList;
        this.feedService = feedService;
        this.clickListener = clickListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list_item, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Feed feed = feedList.get(position);
        holder.headlineTxt.setText(feed.getTitle());
        holder.sourceTxt.setText(feed.getPublisher());

        if(position == 0) {
            holder.headlineTxt.setLineSpacing(new Float(context.getResources().getDimension(R.dimen.margin)), 1f);
            holder.headlineTxt.setTextSize(new Float(context.getResources().getDimension(R.dimen.font_xxxsmall)));
        } else {
            holder.headlineTxt.setLineSpacing(3f, 1.5f);
            holder.headlineTxt.setTextSize(new Float(context.getResources().getDimension(R.dimen.font_xxxxsmall)));
        }

        holder.favBtn.setChecked(feedService.isFavourite(feed.getId()));
        holder.offlineBtn.setChecked(feedService.isOfflineFeed(feed.getId()));
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public Feed getItem(int position) {
        return feedList.get(position);
    }

    public void updateList(List<Feed> data) {
        feedList = data;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if(charString.isEmpty()) {
                    feedList = filteredList;

                } else {
                    List<Feed> filteredList = new ArrayList<>();
                    for (Feed feed : feedList) {
                        if(feed.getTitle().toLowerCase().contains(charString) ||
                                feed.getPublisher().toLowerCase().contains(charString)) {
                            filteredList.add(feed);
                        }
                    }
                    feedList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = feedList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                feedList = (List<Feed>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private View rootView;

        private TextView headlineTxt;
        private TextView sourceTxt;

        private ShineButton favBtn;
        private ShineButton offlineBtn;

        public CustomViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.root_view);
            rootView.setOnClickListener(this);
            headlineTxt = itemView.findViewById(R.id.feed_headlines);
            sourceTxt = itemView.findViewById(R.id.feed_source);
            favBtn = itemView.findViewById(R.id.img_fav);
            favBtn.setOnClickListener(this);
            offlineBtn = itemView.findViewById(R.id.img_offline);
            offlineBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            if(view == favBtn) {
                clickListener.onFavClick(position, favBtn.isChecked());

            } else if(view == offlineBtn) {
                clickListener.onOfflineClick(position, offlineBtn.isChecked());

            } else {
                clickListener.onViewClick(position);
            }
        }
    }
}
