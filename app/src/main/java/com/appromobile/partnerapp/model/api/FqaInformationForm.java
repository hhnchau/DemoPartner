package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 4/5/2017.
 */

public class FqaInformationForm {
    private String content;
    private String createStaffName;
    private int createStaffSn;
    private String createTime;
    private String lastUpdate;
    private int sn;
    private String title;

    public FqaInformationForm() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateStaffName() {
        return createStaffName;
    }

    public void setCreateStaffName(String createStaffName) {
        this.createStaffName = createStaffName;
    }

    public int getCreateStaffSn() {
        return createStaffSn;
    }

    public void setCreateStaffSn(int createStaffSn) {
        this.createStaffSn = createStaffSn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
