package com.landon.meizhi.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by landon.xu on 2017/1/7.
 */

public class BitmapUtil {


    public static Bitmap drawable2Bitmap(Drawable drawable){
        android.graphics.Bitmap bitmap = android.graphics.Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? android.graphics.Bitmap.Config.ARGB_8888

                        : android.graphics.Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;
    }

    public static boolean saveDrawable(Context context,Drawable drawable, String name, boolean showInGallery){
        Bitmap bitmap = drawable2Bitmap(drawable);
        if(bitmap != null) {
            return saveBitmap(context, bitmap, name, showInGallery);
        }else{
            return false;
        }
    }

    public static String getBitmapFullName(String name){
        return FileUtil.getSavePath() + "/" + name;
    }

    public static boolean saveBitmap(Context context, Bitmap bitmap, String name, boolean showInGallery){
        if(bitmap != null){
            String path = FileUtil.getSavePath();
            Logger.d(path);
            if(!TextUtils.isEmpty(path)) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(path + "/" + name);
                if (file.exists()) {
                    file.delete();
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                            fileOutputStream);
                    fileOutputStream.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                } finally {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // 其次把文件插入到系统图库
                if (showInGallery) {
                    try {
                        MediaStore.Images.Media.insertImage(context.getContentResolver(),
                                file.getAbsolutePath(), name, null);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // 最后通知图库更新
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
                }

            }
            return true;

        }else{
            return false;
        }
    }
}
