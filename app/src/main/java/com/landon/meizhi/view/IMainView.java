package com.landon.meizhi.view;

import com.landon.meizhi.model.MeizhiBean;

import java.util.List;

/**
 * Created by landon.xu on 2017/1/6.
 */

public interface IMainView {

    void refresh(List<MeizhiBean> datas);

    void load(List<MeizhiBean> datas);

    void noMore();

    void netError(String msg);

}


