package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/21/2017.
 */

public class StaffLogoutDto {
    private String mobileUserId;

    public StaffLogoutDto() {
    }

    //Hardware Id
    public String getMobileUserId() {
        return mobileUserId;
    }

    public void setMobileUserId(String mobileUserId) {
        this.mobileUserId = mobileUserId;
    }
}
