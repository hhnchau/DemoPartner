package com.appromobile.partnerapp.font;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class Font {
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();
    public static final String SanFrancisco_Regular = "SanFrancisco_Regular";
    public static final String SanFrancisco_Heavy = "SanFrancisco_Heavy";
    public static final String SanFrancisco_Medium = "SanFrancisco_Medium";
    public static final String SanFrancisco_Bold = "SanFrancisco_Bold";

    public static Typeface getFont(Context context, String assetPath, String fontName) {
        synchronized (cache) {

            if (!cache.containsKey(assetPath)) {

                try {
                    Typeface t = (Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName));
                    cache.put(assetPath, t);

                } catch (Exception e) {
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }
}
