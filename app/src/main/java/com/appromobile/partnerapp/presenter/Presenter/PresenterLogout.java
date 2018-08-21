package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;
import android.util.Log;

import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.api.HelperApi;
import com.appromobile.partnerapp.model.api.RestResult;
import com.appromobile.partnerapp.model.api.StaffLogoutDto;
import com.appromobile.partnerapp.presenter.Interface.InterfaceLogout;
import com.appromobile.partnerapp.presenter.view.ViewLogout;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class PresenterLogout implements InterfaceLogout {
    private ViewLogout viewLogout;

    public PresenterLogout(ViewLogout viewLogout) {
        this.viewLogout = viewLogout;
    }

    @Override
    public void logout(Context context, String mobileUserId) {
        StaffLogoutDto staffLogoutDto = new StaffLogoutDto();
        staffLogoutDto.setMobileUserId(mobileUserId);

        Api.getInstance().logout(context, staffLogoutDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;

                if (HelperApi.restResult(restResult.getResult())) {
                    Log.d("LOGOUT", "--> Ok <--");
                    viewLogout.logoutOk();
                } else {
                    Log.d("LOGOUT", "--> Fail <--");
                    viewLogout.logoutOk();
                }
            }
        });
    }
}
