package com.an.inshorts.fragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.an.inshorts.R;
import com.an.inshorts.adapter.FeedListAdapter;
import com.an.inshorts.model.Feed;

import java.io.Serializable;
import java.util.List;

import static com.an.inshorts.BaseConstants.CATEGORY;

public class MainFragment extends BaseFragment {

    private String categoryName;
    private List<Feed> categories;
    private int position;

    private TextView categoryTxt;
    private ImageView categoryImg;
    private RecyclerView recyclerView;
    private FeedListAdapter adapter;

    public static MainFragment newInstance(int position, String categoryName, List<Feed> categories) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("categoryName", categoryName);
        args.putInt("position", position);
        args.putSerializable("categories", (Serializable) categories);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categories = (List<Feed>) getArguments().getSerializable("categories");
        categoryName = getArguments().getString("categoryName");
        position = getArguments().getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, null);

        recyclerView = rootView.findViewById(R.id.feed_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        adapter = new FeedListAdapter(activity, categories);
        recyclerView.setAdapter(adapter);

        categoryTxt = rootView.findViewById(R.id.category_name);
        categoryTxt.setText(CATEGORY.get(categoryName));

        categoryImg = rootView.findViewById(R.id.category_img);
        TypedArray categoryIcons = activity.getResources().obtainTypedArray(R.array.category_icons);
        categoryImg.setImageResource(categoryIcons.getResourceId(position, -1));

        return rootView;
    }
}
