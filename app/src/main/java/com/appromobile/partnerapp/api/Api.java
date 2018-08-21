package com.appromobile.partnerapp.api;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.appromobile.partnerapp.R;
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
import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.model.api.StaffLoginDto;
import com.appromobile.partnerapp.model.api.StaffLogoutDto;
import com.appromobile.partnerapp.model.api.UpdateBookingStatusDto;
import com.appromobile.partnerapp.model.api.UserBookingForm;
import com.appromobile.partnerapp.model.api.UserReviewForm;
import com.appromobile.partnerapp.storage.Storage;
import com.appromobile.partnerapp.ui.BaseActivity;
import com.appromobile.partnerapp.ui.Login;
import com.appromobile.partnerapp.ui.Quit;
import com.appromobile.partnerapp.dialog.CallbackDialag;
import com.appromobile.partnerapp.dialog.Dialag;
import com.appromobile.partnerapp.dialog.Loading;
import com.appromobile.partnerapp.ui.Splash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chau Huynh on 17/03/02017.
 */

public class Api implements ListApi {
    private static Api Instance = null;

    public static Api getInstance() {
        if (Instance == null) {
            Instance = new Api();
        }
        return Instance;
    }

    @Override
    public void loginViaMobile(final Context context, final StaffLoginDto staffLoginDto, final CallbackApiObject callbackApiObject) {
        //Loading.getInstance().show(context);
        ControllerApi.serviceApi.loginViaMobile(staffLoginDto).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Loading.getInstance().hide();
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("LOG-IN", "Fail");
                //Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        loginViaMobile(context, staffLoginDto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void logout(final Context context, final StaffLogoutDto staffLogoutDto, final CallbackApiObject callbackApiObject) {
        Loading.getInstance().show(context);
        ControllerApi.serviceApi.logout(staffLogoutDto, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                Loading.getInstance().hide();
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("LOG-OUT", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        logout(context, staffLogoutDto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void updateStaffToken(final Context context, final StaffDeviceInfoDto staffDeviceInfoDto, final CallbackApiObject callbackApiObject) {
        //Loading.getInstance().show(context);
        ControllerApi.serviceApi.updateStaffToken(staffDeviceInfoDto, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Loading.getInstance().hide();
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("UPDATE-STAFF-TOKEN", "Fail");
                //Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        updateStaffToken(context, staffDeviceInfoDto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void viewHotelDetail(final Context context, final long hotelSn, final CallbackApiObject callbackApiObject) {
        //Loading.getInstance().show(context);
        Map<String, Object> param = new HashMap<>();
        param.put("hotelSn", hotelSn);

        ControllerApi.serviceApi.viewHotelDetail(param).enqueue(new Callback<HotelDetailForm>() {
            @Override
            public void onResponse(Call<HotelDetailForm> call, Response<HotelDetailForm> response) {
                //Loading.getInstance().hide();

                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<HotelDetailForm> call, Throwable t) {
                Log.d("VIEW-HOTEL-DETAIL", "Fail");
                //Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        viewHotelDetail(context, hotelSn, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void findBookingStatisticForOwner(final Context context, final CallbackApiObject callbackApiObject) {
        Loading.getInstance().show(context);
        ControllerApi.serviceApi.findBookingStatisticForOwner(Storage.getInstance(context).getAuth()).enqueue(new Callback<ReservationStatisticForm>() {
            @Override
            public void onResponse(Call<ReservationStatisticForm> call, Response<ReservationStatisticForm> response) {
                Loading.getInstance().hide();
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<ReservationStatisticForm> call, Throwable t) {
                Log.d("FIND-BOOK-STA-FOR-OWNER", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findBookingStatisticForOwner(context, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void findLimitReservationListViaHotel(final Context context, final Map<String, Object> params, final CallbackApiObjectList callbackApiObjectList) {
        //Loading.getInstance().show(context);
        ControllerApi.serviceApi.findLimitReservationListViaHotel(params, Storage.getInstance(context).getAuth()).enqueue(new Callback<List<UserBookingForm>>() {
            @Override
            public void onResponse(Call<List<UserBookingForm>> call, Response<List<UserBookingForm>> response) {
                //Loading.getInstance().hide();

                if (response.body() != null) {
                    List<Object> list = new ArrayList<>();
                    list.addAll(response.body());
                    callbackApiObjectList.resultApiList(list);
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<List<UserBookingForm>> call, Throwable t) {
                Log.d("FND-LMT-RSR-LST-VI-HTEL", "Fail");
                //Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findLimitReservationListViaHotel(context, params, callbackApiObjectList);
                    }
                });
            }
        });
    }

    @Override
    public void findLimitPromotionListForPartner(final Context context, final Map<String, Object> params, final CallbackApiObjectList callbackApiObjectList) {
        Loading.getInstance().show(context);
        ControllerApi.serviceApi.findLimitPromotionListForParner(params, Storage.getInstance(context).getAuth()).enqueue(new Callback<List<PromotionForm>>() {
            @Override
            public void onResponse(Call<List<PromotionForm>> call, Response<List<PromotionForm>> response) {
                Loading.getInstance().hide();
                if (response.body() != null) {
                    List<Object> list = new ArrayList<>();
                    list.addAll(response.body());
                    callbackApiObjectList.resultApiList(list);
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<List<PromotionForm>> call, Throwable t) {
                Log.d("FND-LMT-PRO-LST-FOR-PAR", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findLimitReservationListViaHotel(context, params, callbackApiObjectList);
                    }
                });
            }
        });
    }

    @Override
    public void countAllOfReviewList(final Context context, final long hotelSn, final CallbackApiObject callbackApiObject) {
        Map<String, Object> param = new HashMap<>();
        param.put("hotelSn", hotelSn);

        ControllerApi.serviceApi.countAllOfReviewList(param).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("COUNT-ALL-OF-REVIEW-LST", "Fail");
                Loading.getInstance().hide();
                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        countAllOfReviewList(context, hotelSn, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void findUserReviewList(final Context context, final Map<String, Object> params, final CallbackApiObjectList callbackApiObjectList) {
        ControllerApi.serviceApi.findUserReviewList(params).enqueue(new Callback<List<UserReviewForm>>() {
            @Override
            public void onResponse(Call<List<UserReviewForm>> call, Response<List<UserReviewForm>> response) {
                if (response.body() != null) {
                    List<Object> list = new ArrayList<>();
                    list.addAll(response.body());
                    callbackApiObjectList.resultApiList(list);
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<List<UserReviewForm>> call, Throwable t) {
                Log.d("FND-USR-REVIEW-LIST", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findLimitReservationListViaHotel(context, params, callbackApiObjectList);
                    }
                });
            }
        });

    }

    @Override
    public void updateHotelSettingForm(final Context context, final HotelSettingDto hotelSettingDto, final CallbackApiObject callbackApiObject) {
        ControllerApi.serviceApi.updateHotelSettingForm(hotelSettingDto, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("UPD-HOTEL-SETTING-FORM", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        updateHotelSettingForm(context, hotelSettingDto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void updateReservationSetting(final Context context, final ResSettingDto resSettingDto, final CallbackApiObject callbackApiObject) {
        ControllerApi.serviceApi.updateReservationSetting(resSettingDto, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("UPD-RESERVATION-SETTING", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        updateReservationSetting(context, resSettingDto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void findReservationSetting(final Context context, final long hotelSn, final CallbackApiObject callbackApiObject) {
        Loading.getInstance().show(context);
        final Map<String, Object> param = new HashMap<>();
        param.put("hotelSn", hotelSn);

        ControllerApi.serviceApi.findReservationSetting(param).enqueue(new Callback<ResSettingForm>() {
            @Override
            public void onResponse(Call<ResSettingForm> call, Response<ResSettingForm> response) {
                Loading.getInstance().hide();
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<ResSettingForm> call, Throwable t) {
                Log.d("FID-RESERVATION-SETTING", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findReservationSetting(context, hotelSn, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void findHotelSettingForm(final Context context, final CallbackApiObject callbackApiObject) {
        //Loading.getInstance().show(context);
        ControllerApi.serviceApi.findHotelSettingForm(Storage.getInstance(context).getAuth()).enqueue(new Callback<HotelSettingForm>() {
            @Override
            public void onResponse(Call<HotelSettingForm> call, Response<HotelSettingForm> response) {
                //Loading.getInstance().hide();
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<HotelSettingForm> call, Throwable t) {
                Log.d("FIND-HOTEL-SETTING-FORM", "Fail");
                //Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findHotelSettingForm(context, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void countAllReservationListViaHotel(final Context context, final Map<String, Object> params, final CallbackApiObject callbackApiObject) {
        Loading.getInstance().show(context);
        ControllerApi.serviceApi.countAllReservationListViaHotel(params, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                Loading.getInstance().hide();
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("CUNT-ALL-RESER-VIA-HTEL", "Fail");
                Loading.getInstance().hide();

                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        countAllReservationListViaHotel(context, params, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void findLimitFaqInfomationList(final Context context, final Map<String, Object> params, final CallbackApiObjectList callbackApiObjectList) {
        ControllerApi.serviceApi.findLimitFqaInformationList(params, Storage.getInstance(context).getAuth()).enqueue(new Callback<List<FqaInformationForm>>() {
            @Override
            public void onResponse(Call<List<FqaInformationForm>> call, Response<List<FqaInformationForm>> response) {
                if (response.body() != null) {
                    List<Object> list = new ArrayList<>();
                    list.addAll(response.body());
                    callbackApiObjectList.resultApiList(list);
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<List<FqaInformationForm>> call, Throwable t) {
                Log.d("FIND-LMIT-FQA-INFO-LIST", "Fail");
                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findLimitFaqInfomationList(context, params, callbackApiObjectList);
                    }
                });
            }
        });
    }

    @Override
    public void findApiSetting(final Context context, final CallbackApiObject callbackApiObject) {
        ControllerApi.serviceApi.findApiSetting().enqueue(new Callback<ApiSettingForm>() {
            @Override
            public void onResponse(Call<ApiSettingForm> call, Response<ApiSettingForm> response) {
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<ApiSettingForm> call, Throwable t) {
                Log.d("FIND-API-SETTING", "Fail");
                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        findApiSetting(context, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void confirmReservationStatus(final Context context, final ConfirmBookingDto confirmBookingDto, final CallbackApiObject callbackApiObject) {
        ControllerApi.serviceApi.confirmReservationStatus(confirmBookingDto, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("CONFIRM-RSEVATIO-STATUS", "Fail");
                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        confirmReservationStatus(context, confirmBookingDto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void updateAutoReserved(final Context context, final boolean auto, final CallbackApiObject callbackApiObject) {
        Map<String, Object> param = new HashMap<>();
        param.put("auto", auto);
        ControllerApi.serviceApi.updateAutoReserved(param, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("UPDATE-AUTO_RESERVED", "Fail");
                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        updateAutoReserved(context, auto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void updateCancelledSetting(final Context context, final CancelSettingDto cancelSettingDto, final CallbackApiObject callbackApiObject) {
        ControllerApi.serviceApi.updateCancelledSetting(cancelSettingDto, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                //Callback Presenter
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("UPDATE-CANCEL-SETTING", "Fail");
                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        updateCancelledSetting(context, cancelSettingDto, callbackApiObject);
                    }
                });
            }
        });
    }

    @Override
    public void updateReservationStatus(final Context context, final UpdateBookingStatusDto updateBookingStatusDto, final CallbackApiObject callbackApiObject) {
        ControllerApi.serviceApi.updateReservationStatus(updateBookingStatusDto, Storage.getInstance(context).getAuth()).enqueue(new Callback<RestResult>() {
            @Override
            public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                if (response.body() != null) {
                    callbackApiObject.resultApi(response.body());
                } else {

                    expiredLogin(context);

                }
            }

            @Override
            public void onFailure(Call<RestResult> call, Throwable t) {
                Log.d("UPDATE-RESERTION-STATUS", "Fail");
                processFail(context, new CallbackProcessFail() {
                    @Override
                    public void retry() {
                        updateReservationStatus(context, updateBookingStatusDto, callbackApiObject);
                    }
                });
            }
        });
    }

    //Helper Api Fail
    private void processFail(final Context context, final CallbackProcessFail callbackProcessFail) {
        Dialag.getInstance().show(context, false,false, context.getString(R.string.cannot_connect_to_server), context.getString(R.string.quit), context.getString(R.string.retry), new CallbackDialag() {
            @Override
            public void ok() {
                //Retry
                callbackProcessFail.retry();
            }

            @Override
            public void cancel() {
                //Quit
                Intent exit = new Intent(context, Quit.class);
                context.startActivity(exit);
            }
        });
    }

    private void expiredLogin(final Context context) {
        Dialag.getInstance().show(context, false,false, context.getString(R.string.token_invalid), context.getString(R.string.quit), context.getString(R.string.txt_0_login), new CallbackDialag() {
            @Override
            public void ok() {
                //Set Logout
                Storage.getInstance(context).setAutoLogin(false);
                Intent splash = new Intent(context, Splash.class);
                context.startActivity(splash);
            }

            @Override
            public void cancel() {
                //Quit
                Intent exit = new Intent(context, Quit.class);
                context.startActivity(exit);
            }
        });
    }
}
