package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 4/8/2017.
 */

public class ConfirmBookingDto {
    private boolean accept;
    private int sn;

    public ConfirmBookingDto() {
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }
}
