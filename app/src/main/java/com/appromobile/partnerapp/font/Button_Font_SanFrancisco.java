package com.appromobile.partnerapp.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class Button_Font_SanFrancisco extends Button {
    public Button_Font_SanFrancisco(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Button_Font_SanFrancisco(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Button_Font_SanFrancisco(Context context) {
        super(context);
    }


    @Override
    public void setTypeface(Typeface tf) {
        String fontName = "SF-UI-Display-Regular.otf";
        super.setTypeface(Font.getFont(getContext(), Font.SanFrancisco_Regular, fontName));
    }
}
