package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class RoomForm {
    private int hotelSn;
    private String name;
    private int roomTypeSn;
    private int sn;

    public RoomForm() {
    }

    public int getHotelSn() {
        return hotelSn;
    }

    public void setHotelSn(int hotelSn) {
        this.hotelSn = hotelSn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomTypeSn() {
        return roomTypeSn;
    }

    public void setRoomTypeSn(int roomTypeSn) {
        this.roomTypeSn = roomTypeSn;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }
}
