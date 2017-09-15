package com.an.inshorts.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.an.inshorts.R;
import com.an.inshorts.model.Feed;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.CustomViewHolder> {

    private Context context;
    private List<Feed> feedList;
    public NewsListAdapter(Context context, List<Feed> feedList) {
        this.context = context;
        this.feedList = feedList;
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
//            holder.rootView.setPadding(new Float(context.getResources().getDimension(R.dimen.padding_large)).intValue(),
//                    new Float(context.getResources().getDimension(R.dimen.padding_xsmall)).intValue(),
//                    new Float(context.getResources().getDimension(R.dimen.padding_large)).intValue(),
//                    new Float(context.getResources().getDimension(R.dimen.padding_xsmall)).intValue());
            holder.headlineTxt.setLineSpacing(3f, 1.5f);
            holder.headlineTxt.setTextSize(new Float(context.getResources().getDimension(R.dimen.font_xxxxsmall)));
        }
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public Feed getItem(int position) {
        return feedList.get(position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private View buttonView;
        private TextView headlineTxt;
        private TextView sourceTxt;
        public CustomViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.root_view);
            buttonView = itemView.findViewById(R.id.button_view);
            headlineTxt = itemView.findViewById(R.id.feed_headlines);
            sourceTxt = itemView.findViewById(R.id.feed_source);
        }
    }
}
