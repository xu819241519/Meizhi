package com.landon.meizhi.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by landon.xu on 2017/1/7.
 */

public class ToastUtil {

    public static void showToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
