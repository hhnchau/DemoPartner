package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class RestResult {
    private int count;
    //mapInfo
    private String message;
    private String otherInfo;
    private int result;
    private int sn;
    private int[] snList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int[] getSnList() {
        return snList;
    }

    public void setSnList(int[] snList) {
        this.snList = snList;
    }
}
