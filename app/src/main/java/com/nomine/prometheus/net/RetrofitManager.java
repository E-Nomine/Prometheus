package com.nomine.prometheus.net;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by E Nomine on 2016/3/18.
 */
public class RetrofitManager {
    public static final String BASE_URL = "http://192.168.155.1:80";

    private Retrofit retrofit = null;
    private static RetrofitManager retrofitManager;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
}
