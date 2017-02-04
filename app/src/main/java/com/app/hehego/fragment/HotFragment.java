package com.app.hehego.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.common.sdk.adapter.Page;
import com.app.common.sdk.adapter.Pager;
import com.app.hehego.Config.Contants;
import com.app.hehego.R;
import com.app.hehego.adapter.HotWaresAdapter;
import com.app.hehego.bean.Wares;
import com.cjj.MaterialRefreshLayout;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class HotFragment extends BaseFragment implements Pager.OnPageListener<Wares> {

    private HotWaresAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private MaterialRefreshLayout mRefreshLayout;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot,container,false);

        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void init() {
        Pager pager = Pager.newBuilder()
                .setUrl(Contants.API.WARES_HOT)
                .setLoadMore(true)
                .setOnPageListener(this)
                .setPageSize(20)
                .setRefreshLayout(mRefreshLayout)
                .build(getActivity(), new TypeToken<Page<Wares>>() {}.getType());

        pager.request();
    }

    @Override
    public void load(List<Wares> datas, int totalPage, int totalCount) {
        mAdapter = new HotWaresAdapter(getActivity(),datas);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void refresh(List<Wares> datas, int totalPage, int totalCount) {
        mAdapter.refreshData(datas);
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void loadMore(List<Wares> datas, int totalPage, int totalCount) {
        mAdapter.loadMoreData(datas);
        mRecyclerView.scrollToPosition(mAdapter.getDatas().size());

    }
}
