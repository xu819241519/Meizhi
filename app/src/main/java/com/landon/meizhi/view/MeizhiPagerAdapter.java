package com.landon.meizhi.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.landon.meizhi.R;
import com.landon.meizhi.model.MeizhiBean;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class MeizhiPagerAdapter extends PagerAdapter {

    private List<MeizhiBean> mList;

    private Context mContext;

    private OnItemActionListener mItemActionListener;

    private View mCurrentView;

    public MeizhiPagerAdapter(Context context, OnItemActionListener listener, List<MeizhiBean> datas){
        mContext = context;
        mList = datas;
        mItemActionListener = listener;
    }

    public void updateList(List<MeizhiBean> datas){
        mList = datas;
        notifyDataSetChanged();
    }


    public MeizhiBean getItem(int position){
        return mList == null ? null : mList.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View) object;
    }

    public View getPrimaryItem(){
        return mCurrentView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final String url = mList.get(position).getUrl();
        View view = LinearLayout.inflate(mContext, R.layout.meizhi_detail,null);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.photoview_meizhi );
        Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(photoView);
        container.addView(view);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if(mItemActionListener != null){
                    mItemActionListener.onClick();
                }
            }

            @Override
            public void onOutsidePhotoTap() {
                ((MeizhiActivity)mContext).finish();
            }
        });
        photoView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mItemActionListener != null){
                    mItemActionListener.onLongClick();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }

    public interface OnItemActionListener{

        void onClick();

        void onLongClick();
    }
}
