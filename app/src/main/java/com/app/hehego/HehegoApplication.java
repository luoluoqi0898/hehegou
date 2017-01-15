package com.app.hehego;

import android.app.Application;

/**
 * Created by Administrator on 2017/1/15.
 */
public class HehegoApplication extends Application {

    private static HehegoApplication mInstance;

    public static String PREFERENCE_NAME = "Hehego_Pref";

    public static HehegoApplication getInstance(){

        return  mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }
}
