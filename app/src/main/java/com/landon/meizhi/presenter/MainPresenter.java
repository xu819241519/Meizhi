package com.landon.meizhi.presenter;

import android.content.Context;

import com.landon.meizhi.view.IMainView;
import com.landon.meizhi.model.MeizhiBean;

import java.util.List;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class MainPresenter implements MeizhiRequest.RequestListener {

    private Context mContext;

    private IMainView mView;

    private static final int SIZE  = 20;

    private int mPageIndex = 0;

    private MeizhiRequest mMeizhiRequest = new GankMeizhiRequest(this);

    private boolean mHasMore = true;

    public MainPresenter(Context context, IMainView view){
        mContext = context;
        mView = view;
    }

    public void request(boolean isRefresh){
        if(isRefresh){
            mPageIndex = 0;
            mHasMore = true;
            mMeizhiRequest.request(SIZE,++mPageIndex);
        }else {
            if(mHasMore) {
                mMeizhiRequest.request(SIZE, ++mPageIndex);
            }else{
                mView.noMore();
            }
        }
    }

    @Override
    public void update(List<MeizhiBean> datas) {
        if(mView != null){
            if(datas != null && datas.size() > 0) {
                if (mPageIndex == 1) {
                    mView.refresh(datas);
                } else {
                    mView.load(datas);
                }
                if(datas.size() < SIZE){
                    mHasMore = false;
                    mView.noMore();
                }
            }
        }
    }

    @Override
    public void error(String msg) {
        mView.netError(msg);
    }

}
