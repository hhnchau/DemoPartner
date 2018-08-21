package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;
import android.util.Log;

import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.api.CallbackApiObjectList;
import com.appromobile.partnerapp.api.HelperApi;
import com.appromobile.partnerapp.model.api.ConfirmBookingDto;
import com.appromobile.partnerapp.model.api.RestResult;
import com.appromobile.partnerapp.model.api.UpdateBookingStatusDto;
import com.appromobile.partnerapp.model.api.UserBookingForm;
import com.appromobile.partnerapp.presenter.Interface.InterfaceManageReservation;
import com.appromobile.partnerapp.presenter.view.ViewManageReservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public class PresenterManageReservation implements InterfaceManageReservation {
    private ViewManageReservation viewManageReservation;

    public PresenterManageReservation(ViewManageReservation viewManageReservation) {
        this.viewManageReservation = viewManageReservation;
    }

    @Override
    public void findLimitReservationListViaHotel(Context context, int status, String fromDate, int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("offset", offset);
        params.put("limit", limit);
        params.put("fromDate", fromDate);

        Api.getInstance().findLimitReservationListViaHotel(context, params, new CallbackApiObjectList() {
            @Override
            public void resultApiList(List<Object> list) {
                viewManageReservation.listManageReservation((List<UserBookingForm>) (Object) list);
            }
        });
    }

    @Override
    public void countAllReservationListViaHotel(Context context, int status, String fromDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("fromDate", fromDate);

        Api.getInstance().countAllReservationListViaHotel(context, params, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;

                if (HelperApi.restResult(restResult.getResult())) {
                    viewManageReservation.countAllReservationListViaHotel(restResult.getCount());
                }
            }
        });
    }

    @Override
    public void confirmReservationStatus(Context context, ConfirmBookingDto confirmBookingDto, final int position) {
        Api.getInstance().confirmReservationStatus(context, confirmBookingDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (restResult.getCount() == 1) {
                    viewManageReservation.confirmReservationOk(position);
                } else {
                    viewManageReservation.confirmReservationFail(position);
                }
            }
        });
    }

    @Override
    public void updateReservationStatus(Context context, UpdateBookingStatusDto updateBookingStatusDto, final int position) {
        Api.getInstance().updateReservationStatus(context, updateBookingStatusDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (restResult.getCount() == 1) {
                    viewManageReservation.updateReservationStatusOk(position);
                } else {
                    viewManageReservation.updateReservationStatusFail(position);
                }
            }
        });
    }
}
