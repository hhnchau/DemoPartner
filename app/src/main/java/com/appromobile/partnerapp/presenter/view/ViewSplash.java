package com.appromobile.partnerapp.presenter.view;

/**
 * Created by Chau Huynh on 3/21/2017.
 */

public interface ViewSplash {
    void autoLoginTrue();
    void autoLoginFalse();

    void loginFail();
    void loginOk(String session, int sn);

    void updateStaffTokenOk();
    void updateStaffTokenFail();

    void viewHotelDetailOk();
    void viewHotelDetailFail();

    void checkVersionContinue();
    void checkVersionUpdate();
    void checkVersionForceUpdate();
}
