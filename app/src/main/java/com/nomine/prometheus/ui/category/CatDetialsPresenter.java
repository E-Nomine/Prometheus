package com.nomine.prometheus.ui.category;

import android.content.Context;

import com.nomine.prometheus.model.BaseModel;
import com.nomine.prometheus.model.CategoryData;
import com.nomine.prometheus.model.SourceModel;
import com.nomine.prometheus.net.PrometheusAPIs;
import com.nomine.prometheus.net.RetrofitManager;
import com.nomine.prometheus.ui.base.BasePresenter;
import com.nomine.prometheus.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by E Nomine on 2016/4/4.
 */
public class CatDetialsPresenter extends BasePresenter<ICatDetailsView> {
    private int page = 0;

    public CatDetialsPresenter(Context context, ICatDetailsView iView) {
        super(context, iView);
    }

    public void loadCatDetialsDatas() {
        iView.showProgressBar();
        RetrofitManager.getInstance()
                .create(PrometheusAPIs.class)
                .getCategoryData(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<CategoryData>() {
                    @Override
                    public void onCompleted() {
                        ShowToast.Short("获取成功！");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ShowToast.Short("获取失败！");
                    }

                    @Override
                    public void onNext(CategoryData categoryData) {
                        List<SourceModel> list = new ArrayList<SourceModel>();
                        list.addAll(categoryData.getData());
                        iView.showDatas(list);
                        iView.hideProgressBar();
                    }
                });
    }
    public void loadMoreDatas() {
        iView.showProgressBar();
        page++;
        RetrofitManager.getInstance()
                .create(PrometheusAPIs.class)
                .getCategoryData(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<CategoryData>() {
                    @Override
                    public void onCompleted() {
                        ShowToast.Short("获取成功！");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ShowToast.Short("获取失败！");
                    }

                    @Override
                    public void onNext(CategoryData categoryData) {
                        List<SourceModel> list = new ArrayList<SourceModel>();
                        list.addAll(categoryData.getData());
                        iView.showMoreDatas(list);
                        iView.hideProgressBar();
                    }
                });
    }
    @Override
    public void release() {
        subscription = null;
    }
}
