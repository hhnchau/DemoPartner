package com.appromobile.partnerapp.presenter.Interface;

import android.content.Context;

import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;

/**
 * Created by Chau Huynh on 3/21/2017.
 */

public interface InterfaceSplash {
    void checkAutoLogin(Context context);
    void loginViaMobile(Context context); //get info from Storage
    void updateStaffToken(Context context, StaffDeviceInfoDto staffDeviceInfoDto);
    void viewHotelDetail(Context context, long hotelSn);
    void findApiSetting(Context context);
}
