package com.appromobile.partnerapp.presenter.Interface;

import android.content.Context;

import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.api.CallbackApiObjectList;

import java.util.Map;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public interface InterfaceComment {
    void viewDetailHotel();
    void countAllReviewList(Context context, long hotelSn);
    void findUserReviewList(Context context, long hotelSn, int offset, int limit);
}
