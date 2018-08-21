package com.appromobile.partnerapp.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Chau Huynh on 4/3/2017.
 */

public class HelperSpinner {
    public static void setColor(TextView textView, int position) {
        if (position == 0) {
            textView.setTextColor(Color.rgb(255, 255, 0));
        } else if (position == 1) {
            textView.setTextColor(Color.rgb(150, 255, 150));
        } else if (position == 2) {
            textView.setTextColor(Color.rgb(100, 250, 250));
        } else if (position == 3) {
            textView.setTextColor(Color.rgb(170, 170, 170));
        } else {
            textView.setTextColor(Color.rgb(255, 100, 0));
        }
    }
}
