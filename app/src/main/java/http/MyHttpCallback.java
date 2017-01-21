package http;

import android.content.Context;
import android.content.Intent;

import com.app.common.sdk.http.ProgressCallBack;
import com.app.common.sdk.http.TokenManager;
import com.app.common.sdk.utils.LogUtils;
import com.app.hehego.HehegoApplication;
import com.app.hehego.R;
import com.app.hehego.activity.LoginActivity;

import okhttp3.Request;
import okhttp3.Response;

public abstract class MyHttpCallback<T> extends ProgressCallBack<T> {

    public MyHttpCallback(Context context){
        super(context);
    }

    @Override
    public void onBeforeRequest(Request request) {
        super.onBeforeRequest(request);
    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    @Override
    public void onResponse(Response response) {
        super.onResponse(response);
    }

    @Override
    public void onTokenError(Response response, int code) {
        //这里可以实现跳转到登陆界面
        LogUtils.toastMsg(mContext, mContext.getString(R.string.token_error));

        Intent intent = new Intent();
        intent.setClass(mContext, LoginActivity.class);
        mContext.startActivity(intent);

        TokenManager.getInstance().clearToken(HehegoApplication.getInstance(),HehegoApplication.PREFERENCE_NAME);

    }


}
