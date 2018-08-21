package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class StaffLoginDto {
    private String mobileUserId;
    private String userId;
    private String password;

    public StaffLoginDto() {
    }

    public String getMobileUserId() {
        return mobileUserId;
    }

    public void setMobileUserId(String mobileUserId) {
        this.mobileUserId = mobileUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
