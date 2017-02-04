package com.app.common.sdk.http;

import android.content.Context;
import android.text.TextUtils;

import com.app.common.sdk.bean.User;
import com.app.common.sdk.utils.JSONUtil;
import com.app.common.sdk.utils.PreferencesUtils;

/**
 * Created by Administrator on 2017/1/15.
 */

public class TokenManager {

    public static final String TOKEN="token";

    public static final String USER_JSON="user_json";

    public static String getToken(Context context,String preferenceName){
        return PreferencesUtils.getString(context, preferenceName, TOKEN, null);
    }

    public static  boolean clearToken(Context context, String preferenceName) {
        return PreferencesUtils.putString(context, preferenceName, TOKEN, "");
    }

    public static User getUser(Context context, String preferenceName){
        String user_json = PreferencesUtils.getString(context,preferenceName,USER_JSON,null);
        if(!TextUtils.isEmpty(user_json)){
            return  JSONUtil.fromJson(user_json,User.class);
        }
        return  null;
    }

    public static boolean clearUser(Context context,String preferenceName) {
        return PreferencesUtils.putString(context, preferenceName, USER_JSON, "");
    }

    public static void putUser(Context context,String preferenceName, User user) {
        String user_json = JSONUtil.toJSON(user);
        PreferencesUtils.putString(context, preferenceName, USER_JSON, user_json);
    }

    public static void putToken(Context context,String preferenceName, String token){
        PreferencesUtils.putString(context,preferenceName,TOKEN,token);
    }

}
