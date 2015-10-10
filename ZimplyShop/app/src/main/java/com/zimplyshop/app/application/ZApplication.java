package com.zimplyshop.app.application;

import android.app.Application;

/**
 * Created by praveen goel on 10/6/2015.
 */
public class ZApplication extends Application {

    static ZApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static ZApplication getsInstance() {
        if (sInstance == null) {
            sInstance = new ZApplication();
        }
        return sInstance;
    }
}
