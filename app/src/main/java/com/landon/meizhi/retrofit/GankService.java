package com.landon.meizhi.retrofit;

import com.landon.meizhi.model.GankMeizhiBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by landon.xu on 2017/1/6.
 */

public interface GankService {

    String BASE_URL = "http://gank.io/api/data/";

    @GET("%E7%A6%8F%E5%88%A9/{size}/{page}")
    Observable<GankMeizhiBean> getMeizhis(@Path("size") int size, @Path("page") int page);

}
