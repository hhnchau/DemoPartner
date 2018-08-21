package com.appromobile.partnerapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class Keyboard {
    public static void show(Context context, View focus) {
        InputMethodManager input = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        input.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        focus.requestFocus();
    }

    public static void hide(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager input = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        input.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
