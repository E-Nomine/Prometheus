package com.nomine.prometheus.ui.category;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.nomine.prometheus.R;
import com.nomine.prometheus.adapter.CatDetialsAdapter;
import com.nomine.prometheus.model.SourceModel;
import com.nomine.prometheus.ui.widget.LoadFinishCallBack;
import com.nomine.prometheus.ui.widget.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CatDetialsActivity extends AppCompatActivity implements ICatDetailsView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progressbar)
    ProgressBar progressBar;
    @Bind(R.id.catdetials_list)
    PullLoadMoreRecyclerView mRecyclerView;
    @Bind(R.id.catdetials_refreshlayout)
    SwipeRefreshLayout catdetials_refreshlayout;
    LoadFinishCallBack mLoadFinisCallBack;

    List<SourceModel> mDatas;
    CatDetialsAdapter mAdapter;
    CatDetialsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detials);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initPresenter();
        presenter.loadCatDetialsDatas();

    }

    public void initPresenter(){
        presenter = new CatDetialsPresenter(this, this);
        presenter.init();
    }

    @Override
    public void initView() {
        mLoadFinisCallBack = mRecyclerView;
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CatDetialsAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnPauseListenerParams(this, true, true);
        mRecyclerView.setLoadMoreListener(new PullLoadMoreRecyclerView.onLoadMoreListener() {
            @Override
            public void loadMore() {
                presenter.loadMoreDatas();
            }
        });
        catdetials_refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadCatDetialsDatas();
            }
        });
    }

    @Override
    public void showDatas(List<SourceModel> mData) {
        mDatas.clear();
        mDatas.addAll(mData);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMoreDatas(List<SourceModel> mData) {
        mDatas.addAll(mData);
        mAdapter.notifyDataSetChanged();
        mLoadFinisCallBack.loadFinish(null);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        if (catdetials_refreshlayout.isRefreshing()) {
            catdetials_refreshlayout.setRefreshing(false);
        }
    }
}
