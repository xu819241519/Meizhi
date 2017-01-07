package com.landon.meizhi.view;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.landon.meizhi.R;
import com.landon.meizhi.presenter.MainPresenter;
import com.landon.meizhi.presenter.SplashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class SplashActivity extends BaseActivity implements ISplashView {

    @BindView(R.id.iv_splash)
    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        SplashPresenter splashPresenter = new SplashPresenter(this);
        initAnimation();
    }

    private void initAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2500);
        mImageView.startAnimation(scaleAnimation);

        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void showImage(String url) {
        if(TextUtils.isEmpty(url)){
            mImageView.setImageDrawable(getResources().getDrawable(R.mipmap.splash));
        }else{
            Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        }
    }
}
