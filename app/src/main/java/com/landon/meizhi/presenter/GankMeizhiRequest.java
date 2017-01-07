package com.landon.meizhi.presenter;

import com.landon.meizhi.model.GankMeizhiBean;
import com.landon.meizhi.retrofit.GankRetrofitApi;
import com.orhanobut.logger.Logger;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class GankMeizhiRequest extends MeizhiRequest {

    public GankMeizhiRequest(RequestListener listener) {
        super(listener);
    }

    @Override
    public void request(int size, int page) {
        GankRetrofitApi gankRetrofitApi = GankRetrofitApi.getsInstance();
        Observable<GankMeizhiBean> bean = gankRetrofitApi.getGankService().getMeizhis(size, page);
        bean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GankMeizhiBean>() {
            @Override
            public void onCompleted() {
                Logger.d("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Logger.d("onError " + e.getMessage());
                error(e.getMessage());
            }

            @Override
            public void onNext(GankMeizhiBean gankMeizhiBean) {
                if (gankMeizhiBean != null) {
                    List<GankMeizhiBean.ResultsBean> beans = gankMeizhiBean.getResults();
                    if (beans != null && beans.size() > 0) {
                        update(gankMeizhiBean.convert());
                    } else {
                        Logger.d("beans is null");
                    }
                } else {
                    Logger.d("meizhibean is null");
                }
            }
        });
    }

}
