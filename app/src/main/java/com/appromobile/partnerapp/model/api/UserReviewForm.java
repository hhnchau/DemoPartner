package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class UserReviewForm {
    private int appUserSn;
    private boolean author;
    private String comment;
    private String createTime;
    private String hotelSn;
    private int mark;
    private String roomName;
    private int roomSn;
    private String roomTypeName;
    private int roomTypeSn;
    private int sn;
    private String userNickName;

    public UserReviewForm() {
    }

    public int getAppUserSn() {
        return appUserSn;
    }

    public void setAppUserSn(int appUserSn) {
        this.appUserSn = appUserSn;
    }

    public boolean isAuthor() {
        return author;
    }

    public void setAuthor(boolean author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHotelSn() {
        return hotelSn;
    }

    public void setHotelSn(String hotelSn) {
        this.hotelSn = hotelSn;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomSn() {
        return roomSn;
    }

    public void setRoomSn(int roomSn) {
        this.roomSn = roomSn;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
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

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
