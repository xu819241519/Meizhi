package com.landon.meizhi.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

import com.landon.meizhi.model.MeizhiBean;
import com.landon.meizhi.util.BitmapUtil;
import com.landon.meizhi.view.IMeizhiView;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by landon.xu on 2017/1/7.
 */

public class MeizhiPresenter {

    private IMeizhiView mView;

    private Context mContext;

    private List<MeizhiBean> mMeizhis;

    public MeizhiPresenter(Context context, IMeizhiView view) {
        mContext = context;
        mView = view;
    }

    public void setMeizhis(List<MeizhiBean> beans) {
        mMeizhis = beans;
    }

    public String save(final Drawable drawable, int index, final boolean notify) {
        String result = null;
        if (drawable != null) {
            String name = getPicName(index);
            if (TextUtils.isEmpty(name)) {
                Calendar calendar = Calendar.getInstance();
                name = String.valueOf(calendar.getTimeInMillis());
            }
            final String finalName = name;
            result = BitmapUtil.getBitmapFullName(name);
            Observable.create(new Observable.OnSubscribe<Boolean>() {
                @Override
                public void call(Subscriber<? super Boolean> subscriber) {
                    subscriber.onNext(BitmapUtil.saveDrawable(mContext, drawable, finalName, false));
                    subscriber.onCompleted();
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
                @Override
                public void call(Boolean success) {
                    if (mView != null && notify) {
                        mView.completeDownload(success);
                    }
                }
            });
        } else {

        }
        if (result != null) {
            File file = new File(result);
            if (file.exists()) {
                return result;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void share(Drawable drawable, int index) {
        if (drawable != null) {
            String pic = save(drawable, index, false);
            if (!TextUtils.isEmpty(pic)) {
                File file = new File(pic);
                if (file.exists()) {
                    //由文件得到uri
                    Uri imageUri = Uri.fromFile(file);
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setType("image/*");
                    mContext.startActivity(Intent.createChooser(shareIntent, "分享MeiZhi到"));
                } else {
                    mView.completeShare(false);
                }
            } else{
                mView.completeShare(false);
            }
        } else {
            mView.completeShare(false);
        }
    }


    private String getPicName(int index) {
        if (mMeizhis != null && index >= 0 && index < mMeizhis.size()) {
            return mMeizhis.get(index).getUrl().substring(mMeizhis.get(index).getUrl().lastIndexOf('/') + 1);
        }
        return null;
    }

}
