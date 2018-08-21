package com.appromobile.partnerapp.presenter.view;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public interface ViewLogin {
    void loginOk(String session, int sn);
    void loginFail();
    void updateStaffTokenOk();
    void updateStaffTokenFail();
    void viewHotelDetailOk();
    void viewHotelDetailFail();
}
