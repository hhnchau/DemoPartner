package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;

import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObjectList;
import com.appromobile.partnerapp.model.api.FqaInformationForm;
import com.appromobile.partnerapp.model.api.UserReviewForm;
import com.appromobile.partnerapp.presenter.Interface.InterfaceFaq;
import com.appromobile.partnerapp.presenter.view.ViewFaq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chau Huynh on 4/6/2017.
 */

public class PresenterFaq implements InterfaceFaq {
    private ViewFaq viewFaq;

    public PresenterFaq(ViewFaq viewFaq) {
        this.viewFaq = viewFaq;
    }

    @Override
    public void findLimitFaqInfomationList(Context context, int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", limit);
        Api.getInstance().findLimitFaqInfomationList(context, params, new CallbackApiObjectList() {
            @Override
            public void resultApiList(List<Object> list) {
                viewFaq.findLimitFaqInfomationList((List<FqaInformationForm>) (Object) list);
            }
        });
    }
}
