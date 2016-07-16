package com.nomine.prometheus.ui.mashup;

import android.content.Context;

import com.nomine.prometheus.adapter.MashupAdapter;
import com.nomine.prometheus.model.ArticleData;
import com.nomine.prometheus.model.BaseModel;
import com.nomine.prometheus.model.ImageData;
import com.nomine.prometheus.model.MusicData;
import com.nomine.prometheus.net.PrometheusAPIs;
import com.nomine.prometheus.net.RetrofitManager;
import com.nomine.prometheus.ui.base.BasePresenter;
import com.nomine.prometheus.utils.ShowToast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by E Nomine on 2016/3/28.
 */
public class MashupPresenter extends BasePresenter<IMashupView> {

    private int page = 0;
    private int type = 0;

    public MashupPresenter(Context context, IMashupView iView, int fragment_type) {
        super(context, iView);
        this.type = fragment_type;

    }

    public void loadMashupDatas() {
        iView.showProgressBar();

        if(type == MashupFragment.F_MASHUP || type == MashupFragment.F_MUSIC ){
            RetrofitManager.getInstance()
                    .create(PrometheusAPIs.class)
                    .getMusicData(page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<MusicData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ShowToast.Short("Music获取失败！");
                            iView.hideProgressBar();
                        }

                        @Override
                        public void onNext(MusicData musicData) {
                            List<BaseModel> list = new ArrayList<BaseModel>();
                            list.addAll(musicData.getData());
                            iView.showDatas(list);
                            iView.hideProgressBar();
                        }
                    });
        }
        if(type == MashupFragment.F_MASHUP || type == MashupFragment.F_ARTICLE ){
            RetrofitManager.getInstance()
                    .create(PrometheusAPIs.class)
                    .getArticleData(page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<ArticleData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ShowToast.Short("Article获取失败！");
                            iView.hideProgressBar();
                        }

                        @Override
                        public void onNext(ArticleData articleData) {
                            List<BaseModel> list = new ArrayList<BaseModel>();
                            list.addAll(articleData.getData());
                            iView.showDatas(list);
                            iView.hideProgressBar();
                        }
                    });
        }
        if(type == MashupFragment.F_MASHUP || type == MashupFragment.F_IMAGE ){
            RetrofitManager.getInstance()
                    .create(PrometheusAPIs.class)
                    .getImageData()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<ImageData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ShowToast.Short("Image获取失败！");
                            iView.hideProgressBar();
                        }

                        @Override
                        public void onNext(ImageData imageData) {
                            List<BaseModel> list = new ArrayList<BaseModel>();
                            list.addAll(imageData.getData());
                            iView.showDatas(list);
                            iView.hideProgressBar();
                        }
                    });
        }
        if(type == MashupFragment.F_MASHUP || type == MashupFragment.F_VIDEO ){
            List<BaseModel> list = new ArrayList<BaseModel>();
            BaseModel baseModel = new BaseModel();
            baseModel.setType(MashupAdapter.ITEM_TYPE_VIDEO);
            list.add(baseModel);
            list.add(baseModel);
            list.add(baseModel);
            list.add(baseModel);
            list.add(baseModel);
            iView.showDatas(list);
            iView.hideProgressBar();
        }
    }
    public void loadMoreDatas(){
        iView.showProgressBar();
        page++;
        if(type == MashupFragment.F_MASHUP || type == MashupFragment.F_ARTICLE ){
            RetrofitManager.getInstance()
                    .create(PrometheusAPIs.class)
                    .getArticleData(page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<ArticleData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ShowToast.Short("Article获取失败！");
                            iView.hideProgressBar();
                        }

                        @Override
                        public void onNext(ArticleData articleData) {
                            List<BaseModel> list = new ArrayList<BaseModel>();
                            list.addAll(articleData.getData());
                            iView.showMoreDatas(list);
                            iView.hideProgressBar();
                        }
                    });
        }
        if(type == MashupFragment.F_MASHUP || type == MashupFragment.F_MUSIC ){
            RetrofitManager.getInstance()
                    .create(PrometheusAPIs.class)
                    .getMusicData(page)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(new Subscriber<MusicData>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            ShowToast.Short("Music获取失败！");
                            iView.hideProgressBar();
                        }

                        @Override
                        public void onNext(MusicData musicData) {
                            List<BaseModel> list = new ArrayList<BaseModel>();
                            list.addAll(musicData.getData());
                            iView.showMoreDatas(list);
                            iView.hideProgressBar();
                        }
                    });
        }
        /*RetrofitManager.getInstance()
                .create(PrometheusAPIs.class)
                .getMusicData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<MusicData>() {
                    @Override
                    public void onCompleted() {
                        ShowToast.Short("获取成功！");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ShowToast.Short("获取失败！");
                    }

                    @Override
                    public void onNext(MusicData musicData) {
                        List<BaseModel> list = new ArrayList<BaseModel>();
                        list.addAll(musicData.getData());
                        iView.showMoreDatas(list);
                        iView.hideProgressBar();
                    }
                });*/
    }
    @Override
    public void release() {
        //subscription.unsubscribe();
        subscription = null;
    }
}
