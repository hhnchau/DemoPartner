package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class ReservationStatisticForm {
    private int canceled;
    private int checkIn;
    private int confirmed;
    private int curMonthRevenue;
    private int pastWaitConfirm;
    private int todayRevenue;
    private int waitConfirm;

    public ReservationStatisticForm() {
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getCurMonthRevenue() {
        return curMonthRevenue;
    }

    public void setCurMonthRevenue(int curMonthRevenue) {
        this.curMonthRevenue = curMonthRevenue;
    }

    public int getPastWaitConfirm() {
        return pastWaitConfirm;
    }

    public void setPastWaitConfirm(int pastWaitConfirm) {
        this.pastWaitConfirm = pastWaitConfirm;
    }

    public int getTodayRevenue() {
        return todayRevenue;
    }

    public void setTodayRevenue(int todayRevenue) {
        this.todayRevenue = todayRevenue;
    }

    public int getWaitConfirm() {
        return waitConfirm;
    }

    public void setWaitConfirm(int waitConfirm) {
        this.waitConfirm = waitConfirm;
    }
}
