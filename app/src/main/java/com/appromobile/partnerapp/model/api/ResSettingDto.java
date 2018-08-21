package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/29/2017.
 */

public class ResSettingDto {
    private int checkOutOneday;
    private boolean disableWeekend;
    private int endOvernight;
    private int startOvernight;

    public ResSettingDto() {
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

    public int getStartOvernight() {
        return startOvernight;
    }

    public void setStartOvernight(int startOvernight) {
        this.startOvernight = startOvernight;
    }
}
