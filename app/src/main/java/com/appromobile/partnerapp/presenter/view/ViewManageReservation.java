package com.appromobile.partnerapp.presenter.view;

import com.appromobile.partnerapp.model.api.UserBookingForm;

import java.util.List;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public interface ViewManageReservation {
    void listManageReservation(List<UserBookingForm> listManageReservation);
    void countAllReservationListViaHotel(int count);
    void confirmReservationOk(int p);
    void confirmReservationFail(int p);
    void updateReservationStatusOk(int p);
    void updateReservationStatusFail(int p);
}
