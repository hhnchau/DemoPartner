package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;

import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.model.api.ReservationStatisticForm;
import com.appromobile.partnerapp.presenter.Interface.InterfaceDashboard;
import com.appromobile.partnerapp.presenter.view.ViewDashboard;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public class PresenterDashboard implements InterfaceDashboard {
    private ViewDashboard viewDashboard;

    public PresenterDashboard(ViewDashboard viewDashboard) {
        this.viewDashboard = viewDashboard;
    }

    @Override
    public void findBookingStatisticForOwner(Context context) {
        Api.getInstance().findBookingStatisticForOwner(context, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                viewDashboard.getData((ReservationStatisticForm) object);
            }
        });
    }
}
