package com.appromobile.partnerapp.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Chau Huynh on 02/03/02017.
 */

public class Helper {

    public static String getDeviceId(Context context) {
        String id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return setMD5(id);
    }

    public static String setMD5(String txt) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] array = md5.digest(txt.getBytes());
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                buffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            Log.d("DEVICE_ID: ", buffer.toString());
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getLanguages() {
        if (Locale.getDefault().getLanguage().equals("vi")) {
            return 2;
        } else {
            return 1;
        }
    }

    public static int getOz() {
        //return Android;
        return 2;
    }

    public static String getOsVersion() {
        return "4.4.2";
    }

    public static String getPartnerVerzion() {
        return "0.1";
    }

    public static CalendarDay getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String date = simpleDateFormat.format(new Date());
        String[] day = date.split("-");
        CalendarDay calendarDay = CalendarDay.from(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]));

        return calendarDay;
    }
}
