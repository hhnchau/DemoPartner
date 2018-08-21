package com.appromobile.partnerapp.api;

import android.content.Context;

import com.appromobile.partnerapp.model.api.CancelSettingDto;
import com.appromobile.partnerapp.model.api.ConfirmBookingDto;
import com.appromobile.partnerapp.model.api.HotelSettingDto;
import com.appromobile.partnerapp.model.api.ResSettingDto;
import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.model.api.StaffLoginDto;
import com.appromobile.partnerapp.model.api.StaffLogoutDto;
import com.appromobile.partnerapp.model.api.UpdateBookingStatusDto;

import java.util.Map;

/**
 * Created by Chau Huynh on 17/03/02017.
 */

public interface ListApi {

    //Login - Staff Management
    void loginViaMobile(Context context, StaffLoginDto staffLoginDto, CallbackApiObject callbackApiObject);

    //Logout - Staff Management
    void logout(Context context, StaffLogoutDto staffLogoutDto, CallbackApiObject callbackApiObject);

    //Update - Mobile Device Management
    void updateStaffToken(Context context, StaffDeviceInfoDto staffDeviceInfoDto, CallbackApiObject callbackApiObject);

    //Hotel Management
    void viewHotelDetail(Context context, long hotelSn, CallbackApiObject callbackApiObject);

    //User Booking Management
    void findBookingStatisticForOwner(Context context, CallbackApiObject callbackApiObject);

    //User Booking Management
    void findLimitReservationListViaHotel(Context context, Map<String, Object> params, CallbackApiObjectList callbackApiObjectList);

    //Promotion Management
    void findLimitPromotionListForPartner(Context context, Map<String, Object> params, CallbackApiObjectList callbackApiObjectList);

    //User Management
    void countAllOfReviewList(Context context, long hotelSn, CallbackApiObject callbackApiObject);

    //User Management
    void findUserReviewList(Context context, Map<String, Object> params, CallbackApiObjectList callbackApiObjectList);

    //Setting Management
    void updateHotelSettingForm(Context context, HotelSettingDto hotelSettingDto, CallbackApiObject callbackApiObject);

    //Setting Management
    void updateReservationSetting(Context context, ResSettingDto resSettingDto, CallbackApiObject callbackApiObject);

    //Setting Management
    void findReservationSetting(Context context, long hotelSn, CallbackApiObject callbackApiObject);

    //Setting Management
    void findHotelSettingForm(Context context, CallbackApiObject callbackApiObject);

    //User Booking Management
    void countAllReservationListViaHotel(Context context, Map<String, Object> params, CallbackApiObject callbackApiObject);

    //Conversation Management
    void findLimitFaqInfomationList(Context context, Map<String, Object> params, final CallbackApiObjectList callbackApiObjectList);

    //Setting Management
    void findApiSetting(Context context, CallbackApiObject callbackApiObject);

    //User Booking Management
    void confirmReservationStatus(Context context, ConfirmBookingDto confirmBookingDto, CallbackApiObject callbackApiObject);

    //Setting Management
    void updateAutoReserved(Context context, boolean auto, CallbackApiObject callbackApiObject);

    //Setting Management
    void updateCancelledSetting(Context context, CancelSettingDto cancelSettingDto, CallbackApiObject callbackApiObject);

    //User Booking Management
    void updateReservationStatus(Context context, UpdateBookingStatusDto updateBookingStatusDto, CallbackApiObject callbackApiObject);
}
