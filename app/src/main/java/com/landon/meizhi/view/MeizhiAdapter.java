package com.landon.meizhi.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.landon.meizhi.R;
import com.landon.meizhi.model.MeizhiBean;

/**
 * Created by landon.xu on 2017/1/6.
 */

public class MeizhiAdapter extends RecyclerArrayAdapter<MeizhiBean>{

    public OnMyItemClickListener mOnItemClickListener;

    public MeizhiAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeizhiViewHolder(parent);
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(position,holder);
                }
            }
        });
    }

    public interface OnMyItemClickListener {
        void onItemClick(int position, BaseViewHolder holder);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static class MeizhiViewHolder extends BaseViewHolder<MeizhiBean>{

        private ImageView mImageView;

        public MeizhiViewHolder(ViewGroup itemView) {
            super(itemView, R.layout.meizhi_item);
            mImageView = $(R.id.iv_meizhi);
        }

        @Override
        public void setData(MeizhiBean data) {
            if(data != null && !TextUtils.isEmpty(data.getUrl())){
                Glide.with(getContext()).load(data.getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
            }
        }

    }


}
