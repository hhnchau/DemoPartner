package com.appromobile.partnerapp.presenter.Interface;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.appromobile.partnerapp.model.api.CancelSettingDto;
import com.appromobile.partnerapp.model.api.HotelSettingDto;
import com.appromobile.partnerapp.model.api.ResSettingDto;

/**
 * Created by Chau Huynh on 3/31/2017.
 */

public interface InterfaceSetting {
    void updateHotelSettingForm(Context context, HotelSettingDto hotelSettingDto, TextView textView, String content);

    void updateReservationSetting(Context context, ResSettingDto resSettingDto, TextView textView, String value, ImageView on, ImageView off, int status);

    void findReservationSetting(Context context, long hotelSn);

    void findHotelSettingForm(Context context);

    void updateCancelledSetting(Context context, CancelSettingDto cancelSettingDto,TextView cancel, String value, final ImageView auto, ImageView bypass, final int status);

    void updateAutoReserved(Context context, boolean status, ImageView auto, ImageView bypass);
}
