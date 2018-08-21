package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.appromobile.partnerapp.R;
import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.api.HelperApi;
import com.appromobile.partnerapp.model.api.CancelSettingDto;
import com.appromobile.partnerapp.model.api.HotelSettingDto;
import com.appromobile.partnerapp.model.api.HotelSettingForm;
import com.appromobile.partnerapp.model.api.ResSettingDto;
import com.appromobile.partnerapp.model.api.ResSettingForm;
import com.appromobile.partnerapp.model.api.RestResult;
import com.appromobile.partnerapp.presenter.Interface.InterfaceSetting;
import com.appromobile.partnerapp.presenter.view.ViewSetting;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public class PresenterSetting implements InterfaceSetting {
    private ViewSetting viewSetting;

    public PresenterSetting(ViewSetting viewSetting) {
        this.viewSetting = viewSetting;
    }

    @Override
    public void updateHotelSettingForm(Context context, HotelSettingDto hotelSettingDto, final TextView textView, final String content) {
        Api.getInstance().updateHotelSettingForm(context, hotelSettingDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (HelperApi.restResult(restResult.getResult())) {
                    viewSetting.updateHotelSettingFormOk();
                    textView.setText(content);
                }
            }
        });
    }

    @Override
    public void updateReservationSetting(Context context, ResSettingDto resSettingDto, final TextView textView, final String value, final ImageView on, final ImageView off, final int status) {
        Api.getInstance().updateReservationSetting(context, resSettingDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (HelperApi.restResult(restResult.getResult())) {
                    viewSetting.updateReservationSettingOk();
                    if (textView != null && value != null) {
                        textView.setText(value);
                    }
                    if (HelperApi.restResult(restResult.getResult())) {
                        viewSetting.updateReservationSettingOk();
                        if (status== 1){
                            on.setImageResource(R.drawable.box_press);
                            off.setImageResource(R.drawable.box);
                        }else {
                            on.setImageResource(R.drawable.box);
                            off.setImageResource(R.drawable.box_press);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void findReservationSetting(Context context, long hotelSn) {
        Api.getInstance().findReservationSetting(context, hotelSn, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                viewSetting.findReservationSetting((ResSettingForm) object);
            }
        });
    }

    @Override
    public void findHotelSettingForm(Context context) {
        Api.getInstance().findHotelSettingForm(context, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                HotelSettingForm hotelSettingForm = (HotelSettingForm) object;
                viewSetting.findHotelSettingForm(hotelSettingForm.getNumberOfRecord());
            }
        });
    }

    @Override
    public void updateCancelledSetting(Context context, CancelSettingDto cancelSettingDto, final TextView cancel, final String value, final ImageView auto, final ImageView bypass, final int status) {
        Api.getInstance().updateCancelledSetting(context, cancelSettingDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (HelperApi.restResult(restResult.getResult())) {
                    viewSetting.updateReservationSettingOk();
                    cancel.setText(value);
                    if (status== 1){
                        auto.setImageResource(R.drawable.box_press);
                        bypass.setImageResource(R.drawable.box);
                    }else {
                        auto.setImageResource(R.drawable.box);
                        bypass.setImageResource(R.drawable.box_press);
                    }
                }
            }
        });
    }

    @Override
    public void updateAutoReserved(Context context, final boolean status, final ImageView auto, final ImageView bypass) {
        Api.getInstance().updateAutoReserved(context, status, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult restResult = (RestResult) object;
                if (HelperApi.restResult(restResult.getResult())) {
                    viewSetting.updateReservationSettingOk();
                    if (status){
                        auto.setImageResource(R.drawable.box_press);
                        bypass.setImageResource(R.drawable.box);
                    }else {
                        auto.setImageResource(R.drawable.box);
                        bypass.setImageResource(R.drawable.box_press);
                    }
                }
            }
        });
    }
}
