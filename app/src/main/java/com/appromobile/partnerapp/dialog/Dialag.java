package com.appromobile.partnerapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appromobile.partnerapp.R;

/**
 * Created by Chau Huynh on 17/03/02017.
 */

public class Dialag {
    private static Dialag Instance = null;

    public static Dialag getInstance() {
        if (Instance == null) {
            Instance = new Dialag();
        }
        return Instance;
    }

    public void show(Context context, boolean logo, final boolean outside, String message, String cancel, String ok, final CallbackDialag callbackDialag) {
        final Dialog dialog = new Dialog(context, R.style.myDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_dialog_popup);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();

        TextView tvmessage = (TextView) dialog.findViewById(R.id.popup_text);
        TextView btncancel = (TextView) dialog.findViewById(R.id.popup_no);
        TextView btnok = (TextView) dialog.findViewById(R.id.popup_apply);
        ImageView imgLogo = (ImageView) dialog.findViewById(R.id.imageView_logo);
        RelativeLayout Outside = (RelativeLayout) dialog.findViewById(R.id.dialog_popup);

        Outside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (outside) {
                    dialog.dismiss();
                }
            }
        });

        if (logo) {
            imgLogo.setVisibility(View.VISIBLE);
        }

        if (message != null) {
            tvmessage.setText(message);
        }

        if (cancel == null) {
            btncancel.setVisibility(View.GONE);
        } else {
            btncancel.setText(cancel);
        }

        if (ok == null) {
            btnok.setVisibility(View.GONE);
        } else {
            btnok.setText(ok);
        }

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackDialag.cancel();
                dialog.dismiss();
            }
        });

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackDialag.ok();
                dialog.dismiss();
            }
        });
    }
}
