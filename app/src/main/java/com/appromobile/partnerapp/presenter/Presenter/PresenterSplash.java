package com.appromobile.partnerapp.presenter.Presenter;

import android.content.Context;
import android.util.Log;

import com.appromobile.partnerapp.BuildConfig;
import com.appromobile.partnerapp.api.Api;
import com.appromobile.partnerapp.api.CallbackApiObject;
import com.appromobile.partnerapp.api.HelperApi;
import com.appromobile.partnerapp.model.api.ApiSettingForm;
import com.appromobile.partnerapp.model.api.HotelDetailForm;
import com.appromobile.partnerapp.model.api.RestResult;
import com.appromobile.partnerapp.model.api.StaffDeviceInfoDto;
import com.appromobile.partnerapp.model.api.StaffLoginDto;
import com.appromobile.partnerapp.model.storage.InfoHotel;
import com.appromobile.partnerapp.presenter.Interface.InterfaceSplash;
import com.appromobile.partnerapp.presenter.view.ViewSplash;
import com.appromobile.partnerapp.storage.Storage;
import com.appromobile.partnerapp.model.storage.InfoLogin;
import com.appromobile.partnerapp.utils.Helper;

/**
 * Created by Chau Huynh on 17/03/02017.
 */

public class PresenterSplash implements InterfaceSplash {
    private ViewSplash viewSplash;

    public PresenterSplash(ViewSplash viewSplash) {
        this.viewSplash = viewSplash;
    }

    @Override
    public void checkAutoLogin(Context context) {
        if (Storage.getInstance(context).getAutoLogin()) {
            Log.d("AutoLogin", "--> true <--");
            viewSplash.autoLoginTrue();
        } else {
            Log.d("AutoLogin", "--> false <--");
            //Intent Login
            viewSplash.autoLoginFalse();
        }
    }

    @Override
    public void loginViaMobile(Context context) {
        StaffLoginDto staffLoginDto = new StaffLoginDto();
        staffLoginDto.setMobileUserId(Helper.getDeviceId(context));

        InfoLogin info = Storage.getInstance(context).getInfoLogin();
        if (info != null) {
            staffLoginDto.setUserId(info.getUsername());
            staffLoginDto.setPassword(Helper.setMD5(info.getPassword()));
        }

        Api.getInstance().loginViaMobile(context, staffLoginDto, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                RestResult result = (RestResult) object;
                if (HelperApi.restResult(result.getResult())) {
                    Log.d("LOGIN", "--> Ok <--");
                    viewSplash.loginOk(result.getOtherInfo(), result.getSn());
                } else {
                    Log.d("LOGIN", "--> Fail <--");
                    viewSplash.loginFail();
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
                    viewSplash.updateStaffTokenOk();
                } else {
                    Log.d("UPDATE_STAFF_TOKEN", "--> Fail <--");
                    viewSplash.updateStaffTokenFail();
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
                    viewSplash.viewHotelDetailOk();

                    //Store info Hotel
                    InfoHotel infoHotel = new InfoHotel();
                    infoHotel.setName(hotelDetailForm.getName());
                    infoHotel.setSn(hotelDetailForm.getSn());
                    infoHotel.setAverageMark(hotelDetailForm.getAverageMark());
                    Storage.getInstance(context).setInfoHotel(infoHotel);
                } else {
                    viewSplash.viewHotelDetailFail();
                }
            }
        });
    }

    @Override
    public void findApiSetting(Context context) {
        Api.getInstance().findApiSetting(context, new CallbackApiObject() {
            @Override
            public void resultApi(Object object) {
                ApiSettingForm apiSettingForm = (ApiSettingForm) object;

                int[] local = convertVersionToInt(BuildConfig.VERSION_NAME);
                int[] server = convertVersionToInt(apiSettingForm.getLastAndroidAppVersion());

                //checkVersion
                if (local[0] > server[0]) {
                    viewSplash.checkVersionContinue();
                } else {
                    if (local[0] < server[0]) {
                        viewSplash.checkVersionForceUpdate();
                    } else {
                        if (local[1] < server[1]) {
                            viewSplash.checkVersionUpdate();
                        } else {
                            if (local[2] >= server[2]) {
                                viewSplash.checkVersionContinue();
                            } else {
                                viewSplash.checkVersionUpdate();
                            }
                        }
                    }
                }
                //checkVersion
            }
        });
    }

    private int[] convertVersionToInt(String version) {
        String[] temp = version.split("\\.");
        int[] intVer = new int[3];
        for (int i = 0; i < intVer.length; i++) {
            if (i == 2 & temp.length != 3) {
                intVer[2] = 0;
            } else {
                intVer[i] = Integer.parseInt(temp[i]);
            }
        }
        return intVer;
    }

}
