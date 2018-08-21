package com.appromobile.partnerapp.model.other;

/**
 * Created by Chau Huynh on 15/03/02017.
 */

public class DrawerObject {
    private String title;
    private int icon;

    public DrawerObject() {
    }

    public DrawerObject(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public DrawerObject(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
