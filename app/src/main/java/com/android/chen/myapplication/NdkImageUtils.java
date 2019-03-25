package com.android.chen.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

/**
 * Created by ${chensi} on 2019/3/21.
 */
public class NdkImageUtils {
    private static Bitmap mbitmap;
    public static Bitmap getNdkImage(Bitmap bitmap, Context context){

        long startTime = System.currentTimeMillis();
        mbitmap=  BitmapFactory.decodeResource(context.getResources(),R.mipmap.cc);
        int []buffer = new int[bitmap.getWidth()*bitmap.getHeight()];
        mbitmap.getPixels(buffer,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
        int [] ndkImage = getNdkImage(buffer,bitmap.getWidth(),bitmap.getHeight());

        bitmap =Bitmap.createBitmap(ndkImage,bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.RGB_565);
        long endTime = System.currentTimeMillis();
        Toast.makeText(context ,"耗时"+(endTime-startTime)+"毫秒",Toast.LENGTH_SHORT).show();
        return bitmap;

    }


    public static native  int [] getNdkImage(int []buffer,int width,int height);



}
