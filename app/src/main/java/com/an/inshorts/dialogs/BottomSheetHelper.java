package com.an.inshorts.dialogs;

import android.content.Context;
import com.an.inshorts.model.MenuItem;
import java.util.List;


public class BottomSheetHelper {

    private static BottomSheetHelper instance;
    public static BottomSheetHelper getInstance() {
        if(instance == null) instance = new BottomSheetHelper();
        return instance;
    }

    private CustomBottomSheetDialog bottomSheetDialog;

    public void showBottomSheet(Context context, List<MenuItem> list, CustomBottomSheetDialog.MenuItemListener listener) {
        bottomSheetDialog = new CustomBottomSheetDialog(context, list, listener);
        bottomSheetDialog.show();
    }
}
