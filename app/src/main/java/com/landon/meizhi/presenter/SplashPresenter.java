package com.landon.meizhi.presenter;

import android.content.Context;

import com.landon.meizhi.MeizhiApplication;
import com.landon.meizhi.model.GankMeizhiBean;
import com.landon.meizhi.model.MeizhiBean;
import com.landon.meizhi.retrofit.GankRetrofitApi;
import com.landon.meizhi.view.ISplashView;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class SplashPresenter {

    private ISplashView mView;

    public SplashPresenter(ISplashView view){
        mView = view;
        GankRetrofitApi api = GankRetrofitApi.getsInstance();
        api.getGankService().getMeizhis(1,1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GankMeizhiBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showImage(null);
            }

            @Override
            public void onNext(GankMeizhiBean gankMeizhiBean) {
                boolean showed = false;
                if(gankMeizhiBean != null){
                    List<GankMeizhiBean.ResultsBean> meizhiBeens =  gankMeizhiBean.getResults();
                    if(meizhiBeens != null && meizhiBeens.size() > 0){
                        if(mView != null){
                            mView.showImage(meizhiBeens.get(0).getUrl());
                            MeizhiApplication.FirstPic = meizhiBeens.get(0).getUrl();
                            showed = true;
                        }
                    }
                }
                if(!showed && mView != null){
                    mView.showImage(null);
                }
            }
        });
    }
}
