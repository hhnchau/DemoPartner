package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public class HotelSettingDto {
    private int acceptCancel;
    private int cancelByPassHours;
    private int changeNewAfter;
    private String lastAndroidVersion;
    private String lastIosVersion;
    private int numberOfRecord;

    public HotelSettingDto() {
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

    public int getChangeNewAfter() {
        return changeNewAfter;
    }

    public void setChangeNewAfter(int changeNewAfter) {
        this.changeNewAfter = changeNewAfter;
    }

    public String getLastAndroidVersion() {
        return lastAndroidVersion;
    }

    public void setLastAndroidVersion(String lastAndroidVersion) {
        this.lastAndroidVersion = lastAndroidVersion;
    }

    public String getLastIosVersion() {
        return lastIosVersion;
    }

    public void setLastIosVersion(String lastIosVersion) {
        this.lastIosVersion = lastIosVersion;
    }

    public int getNumberOfRecord() {
        return numberOfRecord;
    }

    public void setNumberOfRecord(int numberOfRecord) {
        this.numberOfRecord = numberOfRecord;
    }
}
