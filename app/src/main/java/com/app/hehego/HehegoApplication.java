package com.app.hehego;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2017/1/15.
 */
public class HehegoApplication extends Application {

    private static HehegoApplication mInstance;

    public static String PREFERENCE_NAME = "Hehego_Pref";

    public static HehegoApplication getInstance(){
        return  mInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
    }
}
