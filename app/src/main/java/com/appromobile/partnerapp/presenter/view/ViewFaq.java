package com.appromobile.partnerapp.presenter.view;

import com.appromobile.partnerapp.model.api.FqaInformationForm;
import com.appromobile.partnerapp.model.api.UserReviewForm;

import java.util.List;

/**
 * Created by Chau Huynh on 4/6/2017.
 */

public interface ViewFaq {
    void findLimitFaqInfomationList(List<FqaInformationForm> listFaq);
}
