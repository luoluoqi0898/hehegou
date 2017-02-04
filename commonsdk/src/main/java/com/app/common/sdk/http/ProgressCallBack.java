package com.app.common.sdk.http;

import android.content.Context;

import dmax.dialog.SpotsDialog;
import okhttp3.Request;
import okhttp3.Response;


public abstract class ProgressCallBack<T> extends BaseCallback<T> {

    protected Context mContext;

    private SpotsDialog mDialog;

    public ProgressCallBack(Context context){
        mContext  = context;
        initProgressDialog();
    }


    private  void initProgressDialog(){
        mDialog = new SpotsDialog(mContext,"拼命加载中...");
    }

    public  void showDialog(){
        mDialog.show();
    }

    public  void dismissDialog(){
        mDialog.dismiss();
    }


    public void setLoadMessage(int resId){
        mDialog.setMessage(mContext.getString(resId));
    }


    @Override
    public void onBeforeRequest(Request request) {
        showDialog();
    }

    @Override
    public void onResponse(Response response) {
        dismissDialog();
    }

    @Override
    public void onTokenError(Response response, int code) {

    }
}
