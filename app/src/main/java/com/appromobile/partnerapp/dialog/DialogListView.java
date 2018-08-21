package com.appromobile.partnerapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.ui.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chau Huynh on 4/4/2017.
 */

public class DialogListView {
    private static DialogListView Instance = null;

    public static DialogListView getInstance() {
        if (Instance == null) {
            Instance = new DialogListView();
        }
        return Instance;
    }

    public void show(Context context, final int range, final CallbackDialogListView callbackDialogListView) {
        final Dialog dialog = new Dialog(context, R.style.myDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog_list_view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        ListView listView = (ListView) dialog.findViewById(R.id.listView_dialog);

        //final List<String> list = Arrays.asList(context.getResources().getStringArray(R.array.nav_drawer_items));

        final ArrayList<String> list = new ArrayList<>();

        if (range != 0) {
            for (int i = 0; i < range; i++) {
                list.add(String.valueOf(i + 1));
            }
        }else {
            for (int i= 1;i < 7; i++ ){
                list.add(String.valueOf(i*5));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.item_dialog_list_view_custom_text, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callbackDialogListView.listViewSelect(list.get(position), range);
                dialog.dismiss();
            }
        });

        RelativeLayout relativeLayout = (RelativeLayout) dialog.findViewById(R.id.relative_dialog_listview);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
