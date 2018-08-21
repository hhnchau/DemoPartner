package com.appromobile.partnerapp.api;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class UrlParams {
    private static UrlParams Instance = null;

    public static UrlParams getInstance() {
        if (Instance == null) {
            Instance = new UrlParams();
        }
        return Instance;
    }

    //public String url = "http://192.168.1.36:8080/";
    public String url = "http://27.74.249.60:8080/";
}
