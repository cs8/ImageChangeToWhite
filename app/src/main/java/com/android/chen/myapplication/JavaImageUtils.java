package com.android.chen.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

/**
 * Created by ${chensi} on 2019/3/21.
 */
public class JavaImageUtils {

    //亮度
    private static final float brigtenss =0.2f;
    //对比度
    private static  final float contrinst= 0.2f;

    public static Bitmap getJavaImage(Bitmap bitmap, Context context){




        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int a,r,g,b;
        int bab =(int)(255*brigtenss);
        float ca =1.0f+contrinst;

        Bitmap result =Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
        long startTime = System.currentTimeMillis();
        for(int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                int color =bitmap.getPixel(i,j);
                a= Color.alpha(color);
                r= Color.red(color);
                g= Color.green(color);
                b=Color.blue(color);

                //美白 亮度
                int r_b=r+bab;
                int g_b=g+bab;
                int b_b=b+bab;
                //边界检测 范围控制在0至255之间
                r =r_b>255?255:(r_b<0?0:r_b);
                g =g_b>255?255:(g_b<0?0:g_b);
                b =b_b>255?255:(b_b<0?0:b_b);

                //扩大对比度
                r_b=r-128;
                g_b=g-128;
                b_b=b-128;

                r_b=(int)(r_b*ca);
                g_b=(int)(g_b*ca);
                b_b=(int)(b_b*ca);

                r_b=r_b+128;
                g_b=g_b+128;
                b_b=b_b+128;

               //色值边界检测 范围控制在0至255之间
                r =r_b>255?255:(r_b<0?0:r_b);
                g =g_b>255?255:(g_b<0?0:g_b);
                b =b_b>255?255:(b_b<0?0:b_b);
                result.setPixel(i,j,Color.argb(a,r,g,b));
            }
        }

        long endTime = System.currentTimeMillis();
        Toast.makeText(context ,"耗时"+(endTime-startTime)+"毫秒",Toast.LENGTH_SHORT).show();
        return result;
    }

}
