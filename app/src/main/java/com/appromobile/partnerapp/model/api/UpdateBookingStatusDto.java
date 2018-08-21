package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 4/24/2017.
 */

public class UpdateBookingStatusDto {
    private int sn;
    private int toStatus;

    public UpdateBookingStatusDto() {
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getToStatus() {
        return toStatus;
    }

    public void setToStatus(int toStatus) {
        this.toStatus = toStatus;
    }
}
