package com.app.hehego.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.common.sdk.http.OkHttpHelper;
import com.app.common.sdk.http.ProgressCallBack;
import com.app.common.sdk.utils.LogUtils;
import com.app.hehego.Config.Contants;
import com.app.hehego.R;
import com.app.hehego.adapter.HomeCatgoryAdapter;
import com.app.hehego.adapter.decoration.CardViewtemDecortion;
import com.app.hehego.bean.Campaign;
import com.app.hehego.bean.HomeCampaign;
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
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    /**图片轮播*/
    private SliderLayout mSliderLayout;
    /**图片轮播指示*/
    private PagerIndicator mPagerIndicator;
    /**轮播数据*/
    private List<SliderBean> mSliderBean = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private HomeCatgoryAdapter mAdatper;

    private Context mContext;

    private OkHttpHelper mHttpHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        mSliderLayout = (SliderLayout)view.findViewById(R.id.slider);
        mPagerIndicator = (PagerIndicator)view.findViewById(R.id.custom_indicator);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);

        mHttpHelper = OkHttpHelper.getInstance();
        requestSliderData();
        requestRecyclerViewData();
        return view;
    }

    /**
     * 请求轮播数据
     */
    private void requestSliderData(){
        String url ="http://112.124.22.238:8081/course_api/banner/query?type=1";
        mHttpHelper.get(url, new MyHttpCallback<List<SliderBean>>(getActivity()){

            @Override
            public void onSuccess(Response response, List<SliderBean> sliderBeans) {
                LogUtils.i("sliderBeans.size = " + sliderBeans.size());
                mSliderBean = sliderBeans;
                initSliderData();
                mPagerIndicator.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }


    /**
     * 初始化轮播数据
     */
    private void initSliderData(){
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

    private void requestRecyclerViewData() {
        mHttpHelper.get(Contants.API.CAMPAIGN_HOME, new ProgressCallBack<List<HomeCampaign>>(this.getActivity()) {
            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {
                LogUtils.d("homeCampaigns.size = " + homeCampaigns);
                initRecyclerViewData(homeCampaigns);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onTokenError(Response response, int code) {

            }
        });

    }

    /**
     * 初始化列表数据
     * @param homeCampaigns
     */
    private  void initRecyclerViewData(List<HomeCampaign> homeCampaigns){
        mAdatper = new HomeCatgoryAdapter(homeCampaigns,getActivity());
        mAdatper.setOnCampaignClickListener(new HomeCatgoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, Campaign campaign) {
//                Intent intent = new Intent(getActivity(), WareListActivity.class);
//                intent.putExtra(Contants.COMPAINGAIN_ID,campaign.getId());
//                startActivity(intent);
            }
        });

        mRecyclerView.setAdapter(mAdatper);
        mRecyclerView.addItemDecoration(new CardViewtemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSliderLayout.stopAutoCycle();
    }
}
