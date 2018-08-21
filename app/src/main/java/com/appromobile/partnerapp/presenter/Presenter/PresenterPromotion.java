package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;

import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObjectList;
import com.appromobile.partnerapp.model.api.PromotionForm;
import com.appromobile.partnerapp.model.api.UserBookingForm;
import com.appromobile.partnerapp.presenter.Interface.InterfacePromotion;
import com.appromobile.partnerapp.presenter.view.ViewPromotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public class PresenterPromotion implements InterfacePromotion {
    private ViewPromotion viewPromotion;

    public PresenterPromotion(ViewPromotion viewPromotion) {
        this.viewPromotion = viewPromotion;
    }

    @Override
    public void findLimitPromotionListForPartner(Context context, int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);

        Api.getInstance().findLimitPromotionListForPartner(context, params, new CallbackApiObjectList() {
            @Override
            public void resultApiList(List<Object> list) {
                viewPromotion.findLimitPromotionListForPartner((List<PromotionForm>) (Object) list);
            }
        });
    }
}
