package com.zimplyshop.app.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZPreferences {

    private static final String KEY = "zimplyshop.prefs";
    private static final String IS_USER_LOGIN = "is_user_login";

    public static void setIsUserLogin(Context context, boolean isUserLogin) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        editor.putBoolean(IS_USER_LOGIN, isUserLogin);
        editor.commit();
    }

    public static boolean isUserLogin(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return savedSession.getBoolean(IS_USER_LOGIN, false);
    }
}
