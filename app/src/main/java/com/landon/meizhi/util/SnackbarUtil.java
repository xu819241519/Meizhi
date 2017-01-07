package com.landon.meizhi.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by landon.xu on 2017/1/7.
 */

public class SnackbarUtil {

    public static void showSnackbar(View view,String msg){
        Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
    }
}
