package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 03/03/02017.
 */

public class StaffDeviceInfoDto {
    private String address;
    String districtCode;
    private int language;
    private double latitude;
    private double longitude;
    private String mobileUserId;
    private int os;
    private String osVersion;
    private String partnerVersion;
    private String provinceCode;
    private String tokenId;

    public StaffDeviceInfoDto() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMobileUserId() {
        return mobileUserId;
    }

    public void setMobileUserId(String mobileUserId) {
        this.mobileUserId = mobileUserId;
    }

    public int getOs() {
        return os;
    }

    public void setOs(int os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getPartnerVersion() {
        return partnerVersion;
    }

    public void setPartnerVersion(String partnerVersion) {
        this.partnerVersion = partnerVersion;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
