package com.appromobile.partnerapp.model.other;

/**
 * Created by Chau Huynh on 3/26/2017.
 */

public class ChildInfoPromotion {
    private int discount;
    private String applyStart;
    private String applyEnd;
    private String couponStart;
    private String couponEnd;

    public ChildInfoPromotion() {
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getApplyStart() {
        return applyStart;
    }

    public void setApplyStart(String applyStart) {
        this.applyStart = applyStart;
    }

    public String getApplyEnd() {
        return applyEnd;
    }

    public void setApplyEnd(String applyEnd) {
        this.applyEnd = applyEnd;
    }

    public String getCouponStart() {
        return couponStart;
    }

    public void setCouponStart(String couponStart) {
        this.couponStart = couponStart;
    }

    public String getCouponEnd() {
        return couponEnd;
    }

    public void setCouponEnd(String couponEnd) {
        this.couponEnd = couponEnd;
    }
}
