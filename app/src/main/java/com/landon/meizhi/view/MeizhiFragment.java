package com.landon.meizhi.view;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.landon.meizhi.R;
import com.landon.meizhi.model.MeizhiBean;
import com.landon.meizhi.presenter.MeizhiPresenter;
import com.landon.meizhi.util.SnackbarUtil;
import com.landon.meizhi.util.ToastUtil;

import java.util.List;
import java.util.jar.Manifest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class MeizhiFragment extends BaseFragment implements IMeizhiView, MeizhiPagerAdapter.OnItemActionListener {

    private View mView;

    public static final String CURRENT_INDEX = "current_index";

    public static final String MEIZHI_LIST = "meizhi_list";

    private Unbinder mUnbinder;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private MeizhiPagerAdapter mMeizhiAdapter;

    private PopupWindow mPopupWindow;

    private MeizhiPresenter mPresenter;

    private static final int PERMISSION_REQUEST = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_meizhi, null);
        mUnbinder = ButterKnife.bind(this, mView);
        mPresenter = new MeizhiPresenter(getActivity(), this);
        initViews();
        return mView;
    }

    private void initViews() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            int index = bundle.getInt(CURRENT_INDEX);
            List<MeizhiBean> beans = bundle.getParcelableArrayList(MEIZHI_LIST);
            if (beans != null && beans.size() > 0) {
                mMeizhiAdapter = new MeizhiPagerAdapter(getActivity(), this, beans);
                mViewPager.setAdapter(mMeizhiAdapter);
                if (index < 0 || index >= beans.size()) {
                    index = 0;
                }
                mViewPager.setCurrentItem(index);
                mPresenter.setMeizhis(beans);
            } else {

            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onClick() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        } else {
            getActivity().finish();
        }
    }

    @Override
    public void onLongClick() {
        if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST);
        }else{
            showPopwindow();
        }
    }


    private void showPopwindow() {
        if (mPopupWindow == null) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_meizhi_details, null);
            mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setAnimationStyle(R.style.popwindow_anim);
            Button btShare = (Button) view.findViewById(R.id.bt_share);
            Button btSave = (Button) view.findViewById(R.id.bt_save);
            btShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imageView = getCurrentImageView();
                    if (imageView != null) {
                        mPresenter.share(imageView.getDrawable(), mViewPager.getCurrentItem());
                        if(mPopupWindow != null){
                            mPopupWindow.dismiss();
                            mPopupWindow = null;
                        }
                    }
                }
            });
            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imageView = getCurrentImageView();
                    if (imageView != null) {
                        mPresenter.save(imageView.getDrawable(), mViewPager.getCurrentItem(), true);
                        if(mPopupWindow != null){
                            mPopupWindow.dismiss();
                            mPopupWindow = null;
                        }
                    }
                }
            });
            WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
            params.alpha = 0.5f;
            getActivity().getWindow().setAttributes(params);
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                    params.alpha = 1f;
                    getActivity().getWindow().setAttributes(params);
                }
            });
            mPopupWindow.showAtLocation(mView, Gravity.BOTTOM, 0, 0);
        }

    }

    private ImageView getCurrentImageView() {
        View view = mMeizhiAdapter.getPrimaryItem();
        if (view != null) {
            return (ImageView) view.findViewById(R.id.photoview_meizhi);
        }
        return null;
    }

    public boolean interruptBackKey() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
            return true;
        }
        return false;
    }

    @Override
    public void completeDownload(boolean success) {
        if (success) {
            SnackbarUtil.showSnackbar(mView,"妹纸图片已经下载完");
        } else {
            SnackbarUtil.showSnackbar(mView,"妹纸图片下载出错了。。。");
        }
    }

    @Override
    public void completeShare(boolean success) {
        if(!success){
            SnackbarUtil.showSnackbar(mView,"分享出错了");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showPopwindow();
            }
        }
    }
}
