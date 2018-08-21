package com.appromobile.partnerapp.model.other;

import java.util.ArrayList;

/**
 * Created by Chau Huynh on 3/26/2017.
 */

public class GroupInfoPromotion {
    private String title;
    private String content;
    private int status;
    private boolean apply;

    public ArrayList<ChildInfoPromotion> childs = new ArrayList<>();

    public GroupInfoPromotion() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isApply() {
        return apply;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }
}
