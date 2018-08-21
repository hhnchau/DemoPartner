package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public class ResSettingForm {
    private int acceptCancel;
    private int acceptReserved;
    private int cancelByPassHours;
    private int checkOutOneday;
    private boolean disableWeekend;
    private int endOvernight;
    private int reservedByPasshours;
    private int startOvernight;

    public ResSettingForm() {
    }

    public int getAcceptCancel() {
        return acceptCancel;
    }

    public void setAcceptCancel(int acceptCancel) {
        this.acceptCancel = acceptCancel;
    }

    public int getAcceptReserved() {
        return acceptReserved;
    }

    public void setAcceptReserved(int acceptReserved) {
        this.acceptReserved = acceptReserved;
    }

    public int getCancelByPassHours() {
        return cancelByPassHours;
    }

    public void setCancelByPassHours(int cancelByPassHours) {
        this.cancelByPassHours = cancelByPassHours;
    }

    public int getCheckOutOneday() {
        return checkOutOneday;
    }

    public void setCheckOutOneday(int checkOutOneday) {
        this.checkOutOneday = checkOutOneday;
    }

    public boolean isDisableWeekend() {
        return disableWeekend;
    }

    public void setDisableWeekend(boolean disableWeekend) {
        this.disableWeekend = disableWeekend;
    }

    public int getEndOvernight() {
        return endOvernight;
    }

    public void setEndOvernight(int endOvernight) {
        this.endOvernight = endOvernight;
    }

    public int getReservedByPasshours() {
        return reservedByPasshours;
    }

    public void setReservedByPasshours(int reservedByPasshours) {
        this.reservedByPasshours = reservedByPasshours;
    }

    public int getStartOvernight() {
        return startOvernight;
    }

    public void setStartOvernight(int startOvernight) {
        this.startOvernight = startOvernight;
    }
}
