package com.appromobile.partnerapp.model.storage;

/**
 * Created by Chau Huynh on 4/5/2017.
 */

public class InfoHotel {
    private String name;
    private int sn;
    private double averageMark;

    public InfoHotel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }
}
