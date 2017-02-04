package com.app.hehego;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.app.common.sdk.bean.User;
import com.app.common.sdk.http.TokenManager;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2017/1/15.
 */
public class HehegoApplication extends Application {

    private static HehegoApplication mInstance;

    public static String PREFERENCE_NAME = "Hehego_Pref";

    private User user;

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
        initUser();
        Fresco.initialize(this);
    }

    private void initUser(){
        this.user = TokenManager.getUser(this,PREFERENCE_NAME);
    }

    public User getUser(){
        return user;
    }

    public void putUser(User user,String token){
        this.user = user;
        TokenManager.putUser(this, PREFERENCE_NAME, user);
        TokenManager.putToken(this, PREFERENCE_NAME, token);
    }

    public void clearUser(){
        this.user = null;
        TokenManager.clearUser(this, PREFERENCE_NAME);
        TokenManager.clearToken(this,PREFERENCE_NAME);
    }

    public String getToken(){
        return  TokenManager.getToken(this,PREFERENCE_NAME);
    }

    private Intent targetIntent;
    public void putTargetIntent(Intent intent){
        this.targetIntent = intent;
    }

    public Intent getTargetIntent() {
        return this.targetIntent;
    }

    public void jumpToTargetActivity(Context context){

        context.startActivity(targetIntent);
        this.targetIntent =null;
    }
}
