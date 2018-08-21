package com.appromobile.partnerapp.presenter.Interface;

import android.content.Context;

import com.appromobile.partnerapp.model.api.ConfirmBookingDto;
import com.appromobile.partnerapp.model.api.UpdateBookingStatusDto;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public interface InterfaceManageReservation {
    void findLimitReservationListViaHotel(Context context, int status, String fromDate, int offset, int limit);
    void countAllReservationListViaHotel(Context context, int status, String fromDate);
    void confirmReservationStatus(Context context, ConfirmBookingDto confirmBookingDto, int position);
    void updateReservationStatus(Context context, UpdateBookingStatusDto updateBookingStatusDto, int position);
}
