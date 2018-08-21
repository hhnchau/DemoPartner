package com.appromobile.partnerapp.api;

import com.appromobile.partnerapp.model.api.ApiSettingForm;
import com.appromobile.partnerapp.model.api.CancelSettingDto;
import com.appromobile.partnerapp.model.api.ConfirmBookingDto;
import com.appromobile.partnerapp.model.api.FqaInformationForm;
import com.appromobile.partnerapp.model.api.HotelDetailForm;
import com.appromobile.partnerapp.model.api.HotelSettingDto;
import com.appromobile.partnerapp.model.api.HotelSettingForm;
import com.appromobile.partnerapp.model.api.PromotionForm;
import com.appromobile.partnerapp.model.api.ResSettingDto;
import com.appromobile.partnerapp.model.api.ResSettingForm;
import com.appromobile.partnerapp.model.api.ReservationStatisticForm;
import com.appromobile.partnerapp.model.api.RestResult;
import com.appromobile.partnerapp.model.api.StaffLogoutDto;
import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.model.api.StaffLoginDto;
import com.appromobile.partnerapp.model.api.UpdateBookingStatusDto;
import com.appromobile.partnerapp.model.api.UserBookingForm;
import com.appromobile.partnerapp.model.api.UserReviewForm;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public interface ServiceApi {

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/mobile/update/updateStaffToken")
    Call<RestResult> updateStaffToken(@Body StaffDeviceInfoDto staffDeviceInfoDto, @Header("authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/staff/view/loginViaMobile")
    Call<RestResult> loginViaMobile(@Body StaffLoginDto staffLoginDto);

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/user/update/login")
    Call<RestResult> logout(@Body StaffLogoutDto staffLogoutDto, @Header("authorization") String auth);

    @GET("/hotelapi/hotel/view/viewHotelDetail")
        //findBasicHotelInformation
    Call<HotelDetailForm> viewHotelDetail(@QueryMap Map<String, Object> params);

    @GET("/hotelapi/reservation/view/findBookingStatisticForOwner")
    Call<ReservationStatisticForm> findBookingStatisticForOwner(@Header("authorization") String auth);

    @GET("/hotelapi/reservation/view/findLimitReservationListViaHotel")
    Call<List<UserBookingForm>> findLimitReservationListViaHotel(@QueryMap Map<String, Object> params, @Header("authorization") String auth);

    @GET("/hotelapi/promotion/view/findLimitPromotionListForPartner")
    Call<List<PromotionForm>> findLimitPromotionListForParner(@QueryMap Map<String, Object> params, @Header("authorization") String auth);

    @GET("/hotelapi/user/view/countAllOfReviewList")
    Call<RestResult> countAllOfReviewList(@QueryMap Map<String, Object> params);

    @GET("/hotelapi/user/view/findUserReviewList")
    Call<List<UserReviewForm>> findUserReviewList(@QueryMap Map<String, Object> params);

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/setting/update/updateHotelSettingForm")
    Call<RestResult> updateHotelSettingForm(@Body HotelSettingDto hotelSettingDto, @Header("authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/setting/update/updateReservationSetting")
    Call<RestResult> updateReservationSetting(@Body ResSettingDto resSettingDto, @Header("authorization") String auth);

    @GET("/hotelapi/setting/view/findReservationSetting")
    Call<ResSettingForm> findReservationSetting(@QueryMap Map<String, Object> params);

    @GET("/hotelapi/setting/view/findHotelSettingForm")
    Call<HotelSettingForm> findHotelSettingForm(@Header("authorization") String auth);

    @GET("/hotelapi/reservation/view/countAllReservationListViaHotel")
    Call<RestResult> countAllReservationListViaHotel(@QueryMap Map<String, Object> params, @Header("authorization") String auth);

    @GET("/hotelapi/conversation/view/findLimitFqaInformationList")
    Call<List<FqaInformationForm>> findLimitFqaInformationList(@QueryMap Map<String, Object> params, @Header("authorization") String auth);

    @GET("/hotelapi/setting/view/findApiSetting")
    Call<ApiSettingForm> findApiSetting();

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/setting/update/updateHotelSettingForm")
    Call<RestResult> confirmReservationStatus(@Body ConfirmBookingDto confirmBookingDto, @Header("authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @POST("/hotelapi/setting/update/updateAutoReserved")
    Call<RestResult> updateAutoReserved(@QueryMap Map<String, Object> params, @Header("authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/setting/update/updateCancelledSetting")
    Call<RestResult> updateCancelledSetting(@Body CancelSettingDto cancelSettingDto, @Header("authorization") String auth);

    @Headers({"Content-Type: application/json;charset=UTF-8", "Accept: application/json"})
    @PUT("/hotelapi/reservation/update/updateReservationStatus")
    Call<RestResult> updateReservationStatus(@Body UpdateBookingStatusDto updateBookingStatusDto, @Header("authorization") String auth);
}