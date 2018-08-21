package com.appromobile.partnerapp.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class Edittex_Font_SanFrancisco extends EditText {
    public Edittex_Font_SanFrancisco(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public Edittex_Font_SanFrancisco(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public Edittex_Font_SanFrancisco(Context context) {
        super(context);
    }


    @Override
    public void setTypeface(Typeface tf) {
        String fontName = "SF-UI-Display-Regular.otf";
        super.setTypeface(Font.getFont(getContext(), Font.SanFrancisco_Regular, fontName));
    }
}
