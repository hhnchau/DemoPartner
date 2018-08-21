package com.appromobile.partnerapp.presenter.Interface;

import android.content.Context;

import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.model.api.StaffLoginDto;

/**
 * Created by Chau Huynh on 3/21/2017.
 */

public interface InterfaceLogin {
    void loginViaMobile(Context context, StaffLoginDto staffLoginDto);
    void updateStaffToken(Context context, StaffDeviceInfoDto staffDeviceInfoDto);
    void viewHotelDetail(Context context, long hotelSn);
}
