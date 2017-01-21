package com.app.common.sdk.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/26.
 */
public class LogUtils {

    private static final String TAG = "luoqi_CommonSdk";
    /**log总开关*/
    public static final boolean DEBUG = true;

    private static String getClassMsg() {
        StackTraceElement caller = new Throwable().fillInStackTrace()
                .getStackTrace()[2];
        return new StringBuilder()
                .append(caller.getClassName().substring(
                        caller.getClassName().lastIndexOf(".") + 1,
                        caller.getClassName().length())).append(".")
                .append(caller.getMethodName()).append("().line")
                .append(caller.getLineNumber()).append(": ").toString();
    }

    public static void v(String msg) { // verbose
        if(DEBUG){
            Log.v(TAG, getClassMsg() + msg);
        }
    }

    public static void i(String msg) { // info
        Log.i(TAG, getClassMsg() + msg);
    }

    public static void d(String msg) { // debug
        if(DEBUG){
            Log.d(TAG, getClassMsg() + msg);
        }
    }

    public static void w(String msg) { // warn
        Log.w(TAG, getClassMsg() + msg);
    }

    public static void e(String msg) { // error
        Log.e(TAG, getClassMsg() + msg);
    }

    public static void t(String msg) { // Throwable
        if(DEBUG){
            Log.d(TAG, getClassMsg() + msg, new Throwable());
        }
    }

    public static void toastMsg(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
