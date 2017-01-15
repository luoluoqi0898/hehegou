package com.app.common.sdk.http;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/1/15.
 */

public class TokenManager {

    private static TokenManager sInstance;

    public static final String TOKEN="token";

    public static TokenManager getInstance(){
        if(sInstance == null){
            synchronized (TokenManager.class){
                if(sInstance == null){
                    sInstance = new TokenManager();
                }
            }
        }
        return sInstance;
    }

    private TokenManager(){

    }

    public String getToken(Context context,String preferenceName){
        SharedPreferences settings = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        return settings.getString(TOKEN, null);
    }

    public boolean clearToken(Context context, String preferenceName) {
        SharedPreferences settings = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TOKEN, "");
        return editor.commit();
    }

}
