package com.app.hehego.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.common.sdk.http.OkHttpHelper;
import com.app.common.sdk.utils.LogUtils;
import com.app.hehego.R;
import com.app.hehego.bean.SliderBean;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

import http.MyHttpCallback;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    /**图片轮播*/
    private SliderLayout mSliderLayout;
    /**图片轮播指示*/
    private PagerIndicator mPagerIndicator;
    /**轮播数据*/
    private List<SliderBean> mSliderBean = new ArrayList<>();

    private Context mContext;

    private OkHttpHelper mHttpHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        mSliderLayout = (SliderLayout)view.findViewById(R.id.slider);
        mPagerIndicator = (PagerIndicator)view.findViewById(R.id.custom_indicator);
        mHttpHelper = OkHttpHelper.getInstance();

        requestSliderData();
        return view;
    }

    private void requestSliderData(){
//        SliderBean sliderBean = new SliderBean();
//        sliderBean.setImgUrl("http://7mno4h.com2.z0.glb.qiniucdn.com/5608eb8cN9b9a0a39.jpg");
//        sliderBean.setName("手机国庆礼");
//        SliderBean sliderBean2 = new SliderBean();
//        sliderBean2.setImgUrl("http://7mno4h.com2.z0.glb.qiniucdn.com/5608cae6Nbb1a39f9.jpg");
//        sliderBean2.setName("IT生活");
//        SliderBean sliderBean3 = new SliderBean();
//        sliderBean3.setImgUrl("http://7mno4h.com2.z0.glb.qiniucdn.com/560a409eN35e252de.jpg");
//        sliderBean3.setName("大放假");
//        mSliderBean.add(sliderBean);
//        mSliderBean.add(sliderBean2);
//        mSliderBean.add(sliderBean3);
        String url ="http://112.124.22.238:8081/course_api/banner/query?type=1";
        mHttpHelper.get(url, new MyHttpCallback<List<SliderBean>>(getActivity()){

            @Override
            public void onSuccess(Response response, List<SliderBean> sliderBeans) {
                LogUtils.i("sliderBeans.size = " + sliderBeans.size());
                mSliderBean = sliderBeans;
                initSliderLayout();
                mPagerIndicator.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });



    }
    private void initSliderLayout(){
        for (SliderBean sliderBean : mSliderBean){
            TextSliderView textSliderView = new TextSliderView(this.getActivity());
            textSliderView.image(sliderBean.getImgUrl());
            textSliderView.description(sliderBean.getName());
            textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {
                    LogUtils.toastMsg(mContext, slider.getUrl() + ":" + slider.getDescription());
                }
            });
            mSliderLayout.addSlider(textSliderView);
        }

        //注释掉默认的指示图标
//        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //设置自定义的轮播指示
        mSliderLayout.setCustomIndicator(mPagerIndicator);
        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);

        mSliderLayout.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSliderLayout.stopAutoCycle();
    }
}
