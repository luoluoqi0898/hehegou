package com.app.hehego.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.common.sdk.bean.User;
import com.app.hehego.HehegoApplication;
import com.app.hehego.activity.LoginActivity;

public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = createView(inflater,container,savedInstanceState);
        initToolBar();
        init();
        return view;

    }

    public void  initToolBar(){

    }


    public abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public abstract void init();


    public void startActivity(Intent intent,boolean isNeedLogin){

        if(isNeedLogin){
            User user = HehegoApplication.getInstance().getUser();
            if(user != null){
                super.startActivity(intent);
            }
            else{
                HehegoApplication.getInstance().putTargetIntent(intent);
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                super.startActivity(loginIntent);
            }
        }
        else{
            super.startActivity(intent);
        }

    }


}
