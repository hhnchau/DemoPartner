package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class HotelImageForm {
    private String contentType;
    private String customizeName;
    private int firstDisplay;
    private int hotelSn;
    private String lastUpdate;
    private String originalName;
    private int roomTypeSn;
    private int sn;
    private String systemOriginalName;

    public HotelImageForm() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCustomizeName() {
        return customizeName;
    }

    public void setCustomizeName(String customizeName) {
        this.customizeName = customizeName;
    }

    public int getFirstDisplay() {
        return firstDisplay;
    }

    public void setFirstDisplay(int firstDisplay) {
        this.firstDisplay = firstDisplay;
    }

    public int getHotelSn() {
        return hotelSn;
    }

    public void setHotelSn(int hotelSn) {
        this.hotelSn = hotelSn;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
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

    public String getSystemOriginalName() {
        return systemOriginalName;
    }

    public void setSystemOriginalName(String systemOriginalName) {
        this.systemOriginalName = systemOriginalName;
    }
}
