package com.appromobile.partnerapp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.appromobile.partnerapp.model.storage.InfoHotel;
import com.appromobile.partnerapp.model.storage.InfoLogin;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class Storage {
    private static Storage Instance = null;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static Gson gson;

    public static Storage getInstance(Context context) {
        if (Instance == null) {
            Instance = new Storage();

            preferences = context.getSharedPreferences("Storage", MODE_PRIVATE);
            editor = preferences.edit();
            gson = new Gson();
        }
        return Instance;
    }

    //Auto Login
    public void setAutoLogin(boolean autoLogin) {
        editor.putBoolean("AUTOLOGIN", autoLogin);
        editor.commit();
    }

    public boolean getAutoLogin() {
        return preferences.getBoolean("AUTOLOGIN", false);
    }

    //InfoLogin
    public void setInfoLogin(InfoLogin infoLogin) {
        editor.putString("INFOLOGIN", gson.toJson(infoLogin));
        editor.commit();
    }

    public InfoLogin getInfoLogin() {
        String info = preferences.getString("INFOLOGIN", null);
        InfoLogin infoLogin = gson.fromJson(info, InfoLogin.class);
        return infoLogin;
    }

    //InfoLogin
    public void setInfoHotel(InfoHotel infoHotel) {
        editor.putString("INFOHOTEL", gson.toJson(infoHotel));
        editor.commit();
    }

    public InfoHotel getInfoHotel() {
        String info = preferences.getString("INFOHOTEL", null);
        InfoHotel infoHotel = gson.fromJson(info, InfoHotel.class);
        return infoHotel;
    }

    //Auth
    public void setAuth(String auth) {
        editor.putString("AUTH", auth);
        editor.commit();
    }

    public String getAuth() {
        return preferences.getString("AUTH", null);
    }

}
