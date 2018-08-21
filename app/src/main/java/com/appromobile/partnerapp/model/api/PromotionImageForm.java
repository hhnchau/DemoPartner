package com.appromobile.partnerapp.model.api;

/**
 * Created by Chau Huynh on 3/28/2017.
 */

public class PromotionImageForm {
    private String contentType;
    private String customizePath;
    private String lastUpdate;
    private int promotionSn;
    private int sn;
    private int typeDisplay;

    public PromotionImageForm() {
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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

    public int getPromotionSn() {
        return promotionSn;
    }

    public void setPromotionSn(int promotionSn) {
        this.promotionSn = promotionSn;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(int typeDisplay) {
        this.typeDisplay = typeDisplay;
    }
}
