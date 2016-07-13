package com.nomine.prometheus.net;

import com.nomine.prometheus.model.ArticleData;
import com.nomine.prometheus.model.ArticleModel;
import com.nomine.prometheus.model.CategoryData;
import com.nomine.prometheus.model.ImageData;
import com.nomine.prometheus.model.MusicData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by E Nomine on 2016/3/18.
 */
public interface PrometheusAPIs {

    @GET("/category/{page}")
    Observable<CategoryData> getCategoryData(@Path("page") int page);

    @GET("/musics/{page}")
    Observable<MusicData> getMusicData(@Path("page") int page);

    @GET("/articles/{page}")
    Observable<ArticleData> getArticleData(@Path("page") int page);

    @GET("/images/")
    Observable<ImageData> getImageData();
}