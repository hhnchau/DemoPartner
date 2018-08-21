package com.appromobile.partnerapp.model.storage;

/**
 * Created by Chau Huynh on 3/21/2017.
 */

public class InfoLogin {
    private boolean remember;
    private String username;
    private String password;

    public InfoLogin() {
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
