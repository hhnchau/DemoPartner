package com.appromobile.partnerapp.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.appromobile.partnerapp.R;

/**
 * Created by Chau Huynh on 17/03/02017.
 */

public class Loading {
    private static Loading Instance = null;

    private static ProgressDialog progressDialog;

    public static Loading getInstance() {
        if (Instance == null) {
            Instance = new Loading();
        }
        return Instance;
    }

    public void show(Context context) {
        progressDialog = new ProgressLoading(context, R.style.TransparentLoading);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(50);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);


        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void hide() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
