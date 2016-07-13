package com.nomine.prometheus.ui.mashup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nomine.prometheus.adapter.MashupAdapter;
import com.nomine.prometheus.model.BaseModel;
import com.nomine.prometheus.R;
import com.nomine.prometheus.ui.widget.LoadFinishCallBack;
import com.nomine.prometheus.ui.widget.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class MashupFragment extends Fragment implements IMashupView{

    public static final int F_MASHUP = 0;
    public static final int F_ARTICLE = 1;
    public static final int F_IMAGE = 2;
    public static final int F_MUSIC = 3;
    public static final int F_VIDEO = 4;
    public int fragment_type ;

    @Bind(R.id.progressbar)
    ProgressBar progressBar;
    @Bind(R.id.mashup_list)
    PullLoadMoreRecyclerView mRecyclerView;
    @Bind(R.id.mashup_refreshlayout)
    SwipeRefreshLayout mashup_refreshlayout;
    LoadFinishCallBack mLoadFinisCallBack;

    List<BaseModel> mDatas;
    MashupAdapter mAdapter;
    MashupPresenter presenter;

    public MashupFragment() {}

    public static MashupFragment newInstance(int args) {
        MashupFragment fragment = new MashupFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("F_TYPE", args);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mashup, container, false);
        ButterKnife.bind(this, rootView);

        this.fragment_type = getArguments().getInt("F_TYPE");
        initPresenter();
        presenter.loadMashupDatas();
        return rootView;
    }

    public void initPresenter(){
        presenter = new MashupPresenter(this.getActivity(), this, fragment_type);
        presenter.init();
    }
    @Override
    public void initView() {
        mLoadFinisCallBack = mRecyclerView;
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MashupAdapter(getActivity(), mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPauseListenerParams(getActivity(), true, true);
        mRecyclerView.getRecycledViewPool().setMaxRecycledViews(MashupAdapter.ITEM_TYPE_ARTICLE, 50);
        mRecyclerView.setItemViewCacheSize(50);
        mRecyclerView.setLoadMoreListener(new PullLoadMoreRecyclerView.onLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.loadMoreDatas();
            }
        });
        mashup_refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMashupDatas();
            }
        });
    }

    @Override
    public void showDatas(List<BaseModel> mData) {
        //mDatas.clear();
        mDatas.addAll(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreDatas(List<BaseModel> mData) {
        mDatas.addAll(mData);
        mAdapter.notifyDataSetChanged();
        mLoadFinisCallBack.loadFinish(null);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatas = null;
        presenter.release();
        presenter = null;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        if (mashup_refreshlayout.isRefreshing()) {
            mashup_refreshlayout.setRefreshing(false);
        }
    }
}
