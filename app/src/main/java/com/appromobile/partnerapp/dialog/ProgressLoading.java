package com.appromobile.partnerapp.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.appromobile.partnerapp.R;
import com.bumptech.glide.Glide;

/**
 * Created by Chau Huynh on 17/03/02017.
 */

public class ProgressLoading extends ProgressDialog {
    public ProgressLoading(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_loading);
        ImageView img = (ImageView) findViewById(R.id.imageView_loading);

        Glide.with(getContext())
                .load(R.raw.anim_loading)
                .asGif()
                .crossFade()
                .into(img);
    }
}
