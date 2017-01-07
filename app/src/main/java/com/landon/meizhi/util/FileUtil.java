package com.landon.meizhi.util;

import android.os.Environment;

/**
 * Created by landon.xu on 2017/1/7.
 */

public class FileUtil {

    private static final String DIR = "meizhis";

    public static String getSavePath(){
        return Environment.getExternalStoragePublicDirectory(DIR).getAbsolutePath();
    }
}
