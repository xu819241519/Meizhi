package com.landon.meizhi.presenter;

import com.landon.meizhi.model.MeizhiBean;

import java.util.List;

/**
 * Created by landon.xu on 2017/1/6.
 */

public abstract class MeizhiRequest {

    private RequestListener mRequestListener;

    public MeizhiRequest(RequestListener listener){
        mRequestListener = listener;
    }

    public abstract void request(int size, int page);

    public void update(List<MeizhiBean> datas){
        if(mRequestListener != null){
            mRequestListener.update(datas);
        }
    }

    public void error(String msg){
        if(mRequestListener != null){
            mRequestListener.error(msg);
        }
    }

    public interface RequestListener {

        void update(List<MeizhiBean> datas);

        void error(String msg);
    }
}
