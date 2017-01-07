package com.landon.meizhi.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.landon.meizhi.R;

import butterknife.ButterKnife;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class MeizhiActivity extends BaseActivity {

    private MeizhiFragment mMeizhiFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            mMeizhiFragment = new MeizhiFragment();
            mMeizhiFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.girl_fragment, mMeizhiFragment).commit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            return mMeizhiFragment.interruptBackKey() || super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
