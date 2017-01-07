package com.landon.meizhi.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.landon.meizhi.R;
import com.landon.meizhi.model.MeizhiBean;
import com.landon.meizhi.presenter.MainPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView, RecyclerArrayAdapter.OnMoreListener, RecyclerArrayAdapter.OnErrorListener, SwipeRefreshLayout.OnRefreshListener {

    private MainPresenter mPresenter;

    @BindView(R.id.easyrecyclerview)
    EasyRecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.floating_action_button)
    FloatingActionButton mFloatingActionButton;


    private MeizhiAdapter mMeizhiAdapter;

    private long exitTime = 0;

    private long toolbarClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this, this);
        initViews();
        mPresenter.request(true);
    }

    private void initViews() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mMeizhiAdapter = new MeizhiAdapter(this);
        mRecyclerView.setAdapterWithProgress(mMeizhiAdapter);
        mRecyclerView.setEmptyView(R.layout.recyclerview_empty);
        mMeizhiAdapter.setMore(R.layout.load_more_layout, this);
        mMeizhiAdapter.setNoMore(R.layout.no_more_layout);
        mMeizhiAdapter.setError(R.layout.error_layout, this);
        mMeizhiAdapter.setOnMyItemClickListener(new MeizhiAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
                Intent intent = new Intent(MainActivity.this, MeizhiActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(MeizhiFragment.CURRENT_INDEX, position);
                bundle.putParcelableArrayList(MeizhiFragment.MEIZHI_LIST, (ArrayList<? extends Parcelable>) mMeizhiAdapter.getAllData());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mRecyclerView.setRefreshListener(this);
        mToolbar.setTitle("妹纸画廊");
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refresh(List<MeizhiBean> datas) {
        if (datas != null && datas.size() > 0) {
            for (MeizhiBean bean : datas) {
                Logger.d(bean.getUrl());
            }
            mMeizhiAdapter.clear();
            mMeizhiAdapter.addAll(datas);
            startMore();
        }
    }

    private void startMore(){
        mMeizhiAdapter.setMore(R.layout.load_more_layout,this);
    }

    @Override
    public void load(List<MeizhiBean> datas) {
        if (datas != null && datas.size() > 0) {
            mMeizhiAdapter.addAll(datas);
            startMore();
        }
    }


    @Override
    public void noMore() {
        mMeizhiAdapter.stopMore();
    }

    @Override
    public void netError(String msg) {
        mMeizhiAdapter.pauseMore();
        mRecyclerView.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        mPresenter.request(true);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(mRecyclerView, "再按一次退出程序哦~", Snackbar.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onMoreShow() {
        mPresenter.request(false);
    }

    @Override
    public void onMoreClick() {

    }


    @OnClick({R.id.floating_action_button, R.id.toolbar})
    public void onClick(View view){
        if(view.getId() == R.id.floating_action_button){
            mRecyclerView.scrollToPosition(0);
        }else if(view.getId() == R.id.toolbar){
            if(System.currentTimeMillis() - toolbarClickTime < 300){
                mRecyclerView.setRefreshing(true,true);
                toolbarClickTime = 0;
            }else{
                toolbarClickTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void onErrorShow() {
        mPresenter.request(false);
    }

    @Override
    public void onErrorClick() {

    }
}
