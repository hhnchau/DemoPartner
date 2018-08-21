package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;
import android.util.Log;

import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.api.HelperApi;
import com.appromobile.partnerapp.model.api.HotelDetailForm;
import com.appromobile.partnerapp.model.api.RestResult;
import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.model.api.StaffLoginDto;
import com.appromobile.partnerapp.model.storage.InfoHotel;
import com.appromobile.partnerapp.presenter.Interface.InterfaceLogin;
import com.appromobile.partnerapp.presenter.view.ViewLogin;
import com.appromobile.partnerapp.storage.Storage;

/**
 * Created by Chau Huynh on 3/21/2017.
 */

public class PresenterLogin implements InterfaceLogin {
    private ViewLogin viewLogin;

    public PresenterLogin(ViewLogin viewLogin) {
        this.viewLogin = viewLogin;
    }

    @Override
    public void loginViaMobile(Context context, StaffLoginDto staffLoginDto) {

        Api.getInstance().loginViaMobile(context, staffLoginDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (HelperApi.restResult(restResult.getResult())) {
                    Log.d("LOGIN", "--> Ok <--");
                    viewLogin.loginOk(restResult.getOtherInfo(), restResult.getSn());
                } else {
                    Log.d("LOGIN", "--> Fail <--");
                    viewLogin.loginFail();
                }
            }
        });

    }

    @Override
    public void updateStaffToken(Context context, StaffDeviceInfoDto staffDeviceInfoDto) {

        Api.getInstance().updateStaffToken(context, staffDeviceInfoDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (HelperApi.restResult(restResult.getResult())) {
                    Log.d("UPDATE_STAFF_TOKEN", "--> Ok <--");
                    viewLogin.updateStaffTokenOk();
                } else {
                    Log.d("UPDATE_STAFF_TOKEN", "--> Fail <--");
                    viewLogin.updateStaffTokenFail();
                }
            }
        });

    }

    @Override
    public void viewHotelDetail(final Context context, long hotelSn) {
        Api.getInstance().viewHotelDetail(context, hotelSn, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                HotelDetailForm hotelDetailForm = (HotelDetailForm) object;
                if (hotelDetailForm != null) {
                    viewLogin.viewHotelDetailOk();

                    //Store info Hotel
                    InfoHotel infoHotel = new InfoHotel();
                    infoHotel.setName(hotelDetailForm.getName());
                    infoHotel.setSn(hotelDetailForm.getSn());
                    infoHotel.setAverageMark(hotelDetailForm.getAverageMark());
                    Storage.getInstance(context).setInfoHotel(infoHotel);
                } else {
                    viewLogin.viewHotelDetailFail();
                }
            }
        });
    }
}
