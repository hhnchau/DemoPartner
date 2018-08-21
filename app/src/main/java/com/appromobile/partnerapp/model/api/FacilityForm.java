package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class FacilityForm {
    private String contentType;
    private String createStaffName;
    private int createStaffSn;
    private String customizePath;
    private String lastUpdate;
    private String name;
    private String originalName;
    private int sn;

    public FacilityForm() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

    public String getCustomizePath() {
        return customizePath;
    }

    public void setCustomizePath(String customizePath) {
        this.customizePath = customizePath;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }
}
