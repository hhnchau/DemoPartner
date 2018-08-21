package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;
import android.util.Log;

import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.api.CallbackApiObjectList;
import com.appromobile.partnerapp.api.HelperApi;
import com.appromobile.partnerapp.model.api.RestResult;
import com.appromobile.partnerapp.model.api.UserBookingForm;
import com.appromobile.partnerapp.model.api.UserReviewForm;
import com.appromobile.partnerapp.presenter.Interface.InterfaceComment;
import com.appromobile.partnerapp.presenter.view.ViewCommnet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public class PresenterComment implements InterfaceComment {
    private ViewCommnet viewCommnet;

    public PresenterComment(ViewCommnet viewCommnet) {
        this.viewCommnet = viewCommnet;
    }

    @Override
    public void viewDetailHotel() {

    }

    @Override
    public void countAllReviewList(Context context, long hotelSn) {
        Api.getInstance().countAllOfReviewList(context, hotelSn, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (HelperApi.restResult(restResult.getResult())) {
                    viewCommnet.countAllRevireList(restResult.getCount());
                } else {
                    //Fial
                }
            }
        });
    }

    @Override
    public void findUserReviewList(Context context, long hotelSn, int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("hotelSn", hotelSn);
        params.put("offset", offset);
        params.put("limit", limit);
        Api.getInstance().findUserReviewList(context, params, new CallbackApiObjectList() {
            @Override
            public void resultApiList(List<Object> list) {
                viewCommnet.findUserReviewList((List<UserReviewForm>) (Object) list);
            }
        });
    }
}
