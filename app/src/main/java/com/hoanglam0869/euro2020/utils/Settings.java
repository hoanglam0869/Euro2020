package com.hoanglam0869.euro2020.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private static SharedPreferences sp;
    private static final String SHARED_PREFERENCES_NAME = "settings";
    private static final String KEY_TIME_ZONE = "timezone";

    public static void getTimeZone(Context context) {
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        TimeZone.positionTimeZone = sp.getInt(KEY_TIME_ZONE, 14);
    }

    public static void setTimeZone(Context context) {
        sp = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(KEY_TIME_ZONE, TimeZone.positionTimeZone);
        editor.apply();
    }
}
