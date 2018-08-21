package com.appromobile.partnerapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appromobile.partnerapp.R;

import java.util.ArrayList;

/**
 * Created by Chau Huynh on 4/4/2017.
 */

public class DialogInput {
    private static DialogInput Instance = null;

    public static DialogInput getInstance() {
        if (Instance == null) {
            Instance = new DialogInput();
        }
        return Instance;
    }

    public void show(Context context, final String hint, final CallbackDialogInput callbackDialogInput) {
        final Dialog dialog = new Dialog(context, R.style.myDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog_input);
        Window window = dialog.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE); //Show Keyboard
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        final EditText edt = (EditText) dialog.findViewById(R.id.edittex_dialog_input);
        edt.setHint(hint);

        TextView Cancel = (TextView) dialog.findViewById(R.id.input_dialog_no);
        TextView Done = (TextView) dialog.findViewById(R.id.input_dialog_apply);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edt.getText().toString().trim().equals("")) {
                    callbackDialogInput.getInput(edt.getText().toString().trim());
                }
                dialog.dismiss();
            }
        });

    }
}
