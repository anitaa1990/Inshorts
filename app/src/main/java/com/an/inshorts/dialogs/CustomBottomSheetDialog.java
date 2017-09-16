package com.an.inshorts.dialogs;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.an.inshorts.R;
import com.an.inshorts.adapter.MenuItemAdapter;
import com.an.inshorts.listener.MenuItemListener;
import com.an.inshorts.model.MenuItem;
import com.an.inshorts.views.RecyclerItemClickListener;

import java.util.List;

public class CustomBottomSheetDialog extends BottomSheetDialog implements RecyclerItemClickListener.OnItemClickListener {

    private Context context;
    private RecyclerView recyclerView;
    private List<MenuItem> menuItemList;

    private MenuItemAdapter adapter;
    private MenuItemListener listener;

    public CustomBottomSheetDialog(Context context, List<MenuItem> menuItemList, MenuItemListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.menuItemList = menuItemList;
        setDialogView();
    }

    public CustomBottomSheetDialog(Context context) {
        super(context);
        this.context = context;
        setDialogView();
    }

    private void setDialogView() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.view_bottom_sheet, null);
        setContentView(bottomSheetView);

        recyclerView = bottomSheetView.findViewById(R.id.bottom_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MenuItemAdapter(menuItemList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, this));
    }

    @Override
    public void onItemClick(View childView, int position) {
        dismiss();
        listener.onMenuItemClick(adapter.getItem(position));
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
