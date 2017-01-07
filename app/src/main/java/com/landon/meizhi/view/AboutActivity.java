package com.landon.meizhi.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.landon.meizhi.MeizhiApplication;
import com.landon.meizhi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.iv_about)
    ImageView mImageView;

    @BindView(R.id.about_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mToolbar.setTitle("关于");
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        /**
         * 此处的setSupportActionBar和setnatigationOnClickListener的顺序不能颠倒，否则设置listener不会生效
         */
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutActivity.this.finish();
                overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
            }
        });
        Glide.with(this).load(MeizhiApplication.FirstPic).bitmapTransform(new BlurTransformation(this, 15)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
    }

}
