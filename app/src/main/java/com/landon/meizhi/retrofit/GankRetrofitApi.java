package com.landon.meizhi.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class GankRetrofitApi {

    private static final int DEFAULT_READ_TIMEOUT = 3;

    private static final int DEFAULT_WRITE_TIMEOUT = 3;

    private static final int DEFAULT_CONNECTION_TIMEOUT = 3;

    private static volatile GankRetrofitApi sInstance;

    private Retrofit mRetrofit;

    private GankRetrofitApi() {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS).connectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS).build();
        mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(client).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(GankService.BASE_URL).build();
    }

    public static GankRetrofitApi getsInstance() {
        if (sInstance == null) {
            synchronized (GankRetrofitApi.class) {
                if (sInstance == null) {
                    sInstance = new GankRetrofitApi();
                }
            }
        }
        return sInstance;
    }

    public GankService getGankService() {
        return mRetrofit.create(GankService.class);
    }

}
