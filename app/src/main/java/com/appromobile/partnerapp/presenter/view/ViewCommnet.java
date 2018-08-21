package com.appromobile.partnerapp.presenter.view;

import com.appromobile.partnerapp.model.api.UserBookingForm;
import com.appromobile.partnerapp.model.api.UserReviewForm;

import java.util.List;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public interface ViewCommnet {
    void viewHotelDetail();
    void countAllRevireList(int count);
    void findUserReviewList(List<UserReviewForm> listFindUserReviewList);
}
