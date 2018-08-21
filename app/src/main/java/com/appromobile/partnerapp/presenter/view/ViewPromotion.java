package com.appromobile.partnerapp.presenter.view;

import com.appromobile.partnerapp.model.api.PromotionForm;
import com.appromobile.partnerapp.model.api.UserBookingForm;

import java.util.List;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public interface ViewPromotion {
    void findLimitPromotionListForPartner(List<PromotionForm> listPromotion);
}
