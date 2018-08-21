package com.appromobile.partnerapp.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class TextView_Font_SanFrancisco_Bold extends TextView {
    public TextView_Font_SanFrancisco_Bold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TextView_Font_SanFrancisco_Bold(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public TextView_Font_SanFrancisco_Bold(Context context) {
        super(context);
    }


    @Override
    public void setTypeface(Typeface tf) {
        String fontName = "SF-UI-Display-Bold.otf";
        super.setTypeface(Font.getFont(getContext(), Font.SanFrancisco_Bold, fontName));
    }
}
