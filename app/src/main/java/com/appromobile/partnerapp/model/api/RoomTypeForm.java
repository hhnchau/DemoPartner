package com.appromobile.partnerapp.model.api;

import java.util.List;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class RoomTypeForm {
    private int additionalHours;
    private int firstHours;
    private int homeImageSn;
    private int hotelSn;
    private String lastUpdate;
    private String memo;
    private String name;
    private int priceAdditionalHours;
    private String priceAdditionalHoursStr;
    private int priceFirstHours;
    private String priceFirstHoursStr;
    private int priceOneDay;
    private String priceOneDayStr;
    private int priceOvernight;
    private String priceOvernightStr;
    private List<RoomForm> roomFormList;
    private List<HotelImageForm> roomTypeImageList;
    private int sn;

    public RoomTypeForm() {
    }

    public int getAdditionalHours() {
        return additionalHours;
    }

    public void setAdditionalHours(int additionalHours) {
        this.additionalHours = additionalHours;
    }

    public int getFirstHours() {
        return firstHours;
    }

    public void setFirstHours(int firstHours) {
        this.firstHours = firstHours;
    }

    public int getHomeImageSn() {
        return homeImageSn;
    }

    public void setHomeImageSn(int homeImageSn) {
        this.homeImageSn = homeImageSn;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceAdditionalHours() {
        return priceAdditionalHours;
    }

    public void setPriceAdditionalHours(int priceAdditionalHours) {
        this.priceAdditionalHours = priceAdditionalHours;
    }

    public String getPriceAdditionalHoursStr() {
        return priceAdditionalHoursStr;
    }

    public void setPriceAdditionalHoursStr(String priceAdditionalHoursStr) {
        this.priceAdditionalHoursStr = priceAdditionalHoursStr;
    }

    public int getPriceFirstHours() {
        return priceFirstHours;
    }

    public void setPriceFirstHours(int priceFirstHours) {
        this.priceFirstHours = priceFirstHours;
    }

    public String getPriceFirstHoursStr() {
        return priceFirstHoursStr;
    }

    public void setPriceFirstHoursStr(String priceFirstHoursStr) {
        this.priceFirstHoursStr = priceFirstHoursStr;
    }

    public int getPriceOneDay() {
        return priceOneDay;
    }

    public void setPriceOneDay(int priceOneDay) {
        this.priceOneDay = priceOneDay;
    }

    public String getPriceOneDayStr() {
        return priceOneDayStr;
    }

    public void setPriceOneDayStr(String priceOneDayStr) {
        this.priceOneDayStr = priceOneDayStr;
    }

    public int getPriceOvernight() {
        return priceOvernight;
    }

    public void setPriceOvernight(int priceOvernight) {
        this.priceOvernight = priceOvernight;
    }

    public String getPriceOvernightStr() {
        return priceOvernightStr;
    }

    public void setPriceOvernightStr(String priceOvernightStr) {
        this.priceOvernightStr = priceOvernightStr;
    }

    public List<RoomForm> getRoomFormList() {
        return roomFormList;
    }

    public void setRoomFormList(List<RoomForm> roomFormList) {
        this.roomFormList = roomFormList;
    }

    public List<HotelImageForm> getRoomTypeImageList() {
        return roomTypeImageList;
    }

    public void setRoomTypeImageList(List<HotelImageForm> roomTypeImageList) {
        this.roomTypeImageList = roomTypeImageList;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }
}
