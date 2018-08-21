package com.appromobile.partnerapp.utils;

import android.widget.ImageView;

import com.appromobile.partnerapp.R;

/**
 * Created by Chau Huynh on 4/4/2017.
 */

public class HelperRating {
    public static void setRating(float rating,
                                 ImageView star1,
                                 ImageView star2,
                                 ImageView star3,
                                 ImageView star4,
                                 ImageView star5) {
        if (rating < 1) {
            star1.setImageResource(R.drawable.star_half);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.star);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.star);
        } else if (rating == 1) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star);
            star3.setImageResource(R.drawable.star);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.star);
        } else if (rating > 1 && rating < 2) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_half);
            star3.setImageResource(R.drawable.star);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.star);
        } else if (rating == 2) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_fill);
            star3.setImageResource(R.drawable.star);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.star);
        } else if (rating > 2 && rating < 3) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_fill);
            star3.setImageResource(R.drawable.star_half);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.star);
        } else if (rating == 3) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_fill);
            star3.setImageResource(R.drawable.star_fill);
            star4.setImageResource(R.drawable.star);
            star5.setImageResource(R.drawable.star);
        } else if (rating > 3 && rating < 4) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_fill);
            star3.setImageResource(R.drawable.star_fill);
            star4.setImageResource(R.drawable.star_half);
            star5.setImageResource(R.drawable.star);
        } else if (rating == 4) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_fill);
            star3.setImageResource(R.drawable.star_fill);
            star4.setImageResource(R.drawable.star_fill);
            star5.setImageResource(R.drawable.star);
        } else if (rating > 4 && rating < 5) {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_fill);
            star3.setImageResource(R.drawable.star_fill);
            star4.setImageResource(R.drawable.star_fill);
            star5.setImageResource(R.drawable.star_half);
        } else {
            star1.setImageResource(R.drawable.star_fill);
            star2.setImageResource(R.drawable.star_fill);
            star3.setImageResource(R.drawable.star_fill);
            star4.setImageResource(R.drawable.star_fill);
            star5.setImageResource(R.drawable.star_fill);
        }
    }
}
