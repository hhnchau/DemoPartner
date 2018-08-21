package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 4/8/2017.
 */

public class ApiSettingForm {
    private String fileTypeSupport;
    private String lastAndroidAppVersion;
    private String lastIosAppVersion;
    private int maxDisplayHotel;
    private int maxSizeUpload;
    private String maxSizeUploadName;
    private int minMoney;
    private double nearbyDistance;
    private  int timeIntervalGetCode;
    private double updateLocationDistance;

    public ApiSettingForm() {
    }

    public String getFileTypeSupport() {
        return fileTypeSupport;
    }

    public void setFileTypeSupport(String fileTypeSupport) {
        this.fileTypeSupport = fileTypeSupport;
    }

    public String getLastAndroidAppVersion() {
        return lastAndroidAppVersion;
    }

    public void setLastAndroidAppVersion(String lastAndroidAppVersion) {
        this.lastAndroidAppVersion = lastAndroidAppVersion;
    }

    public String getLastIosAppVersion() {
        return lastIosAppVersion;
    }

    public void setLastIosAppVersion(String lastIosAppVersion) {
        this.lastIosAppVersion = lastIosAppVersion;
    }

    public int getMaxDisplayHotel() {
        return maxDisplayHotel;
    }

    public void setMaxDisplayHotel(int maxDisplayHotel) {
        this.maxDisplayHotel = maxDisplayHotel;
    }

    public int getMaxSizeUpload() {
        return maxSizeUpload;
    }

    public void setMaxSizeUpload(int maxSizeUpload) {
        this.maxSizeUpload = maxSizeUpload;
    }

    public String getMaxSizeUploadName() {
        return maxSizeUploadName;
    }

    public void setMaxSizeUploadName(String maxSizeUploadName) {
        this.maxSizeUploadName = maxSizeUploadName;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public double getNearbyDistance() {
        return nearbyDistance;
    }

    public void setNearbyDistance(double nearbyDistance) {
        this.nearbyDistance = nearbyDistance;
    }

    public int getTimeIntervalGetCode() {
        return timeIntervalGetCode;
    }

    public void setTimeIntervalGetCode(int timeIntervalGetCode) {
        this.timeIntervalGetCode = timeIntervalGetCode;
    }

    public double getUpdateLocationDistance() {
        return updateLocationDistance;
    }

    public void setUpdateLocationDistance(double updateLocationDistance) {
        this.updateLocationDistance = updateLocationDistance;
    }
}
