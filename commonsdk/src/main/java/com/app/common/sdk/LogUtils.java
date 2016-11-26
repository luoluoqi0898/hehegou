package com.app.common.sdk;

import android.util.Log;

/**
 * Created by Administrator on 2016/11/26.
 */
public class LogUtils {

    private static final String TAG = "CommonSdk";
    /**log总开关*/
    public static final boolean DEBUG = true;

    private static String getClassMsg() {
        StackTraceElement caller = new Throwable().fillInStackTrace()
                .getStackTrace()[2];
        return new StringBuilder()
                .append("[")
                .append(caller.getClassName().substring(
                        caller.getClassName().lastIndexOf(".") + 1,
                        caller.getClassName().length())).append("].")
                .append(caller.getMethodName()).append("(): ").toString();
    }

    public static void v(String msg) { // verbose
        if(DEBUG){
            Log.v(TAG, getClassMsg() + msg);
        }
    }

    public static void i(String msg) { // info
        if(DEBUG){
            Log.i(TAG, getClassMsg() + msg);
        }
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
}
