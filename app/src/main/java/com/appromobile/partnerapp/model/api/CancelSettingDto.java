package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 4/21/2017.
 */

public class CancelSettingDto {
    private int acceptCancel;
    private int cancelByPassHours;

    public CancelSettingDto() {
    }

    public int getAcceptCancel() {
        return acceptCancel;
    }

    public void setAcceptCancel(int acceptCancel) {
        this.acceptCancel = acceptCancel;
    }

    public int getCancelByPassHours() {
        return cancelByPassHours;
    }

    public void setCancelByPassHours(int cancelByPassHours) {
        this.cancelByPassHours = cancelByPassHours;
    }
}
