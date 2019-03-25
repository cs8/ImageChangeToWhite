package com.android.chen.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Bitmap mbitmap;
    ImageView image;
    public int width;
    public int hight;


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.hello_textview);
        tv.setText( stringFromJNI() );

        Button buttomjava = (Button)findViewById(R.id.java);
        Button buttomndk = (Button)findViewById(R.id.ndk);
        Button normal = (Button)findViewById(R.id.normal);

        image= (ImageView)findViewById(R.id.image);
        mbitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.cc);
        width=mbitmap.getWidth();
        hight= mbitmap.getHeight();
        image.setImageBitmap(mbitmap);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.cc));
            }
        });

        //java 调美白
        buttomjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                image.setImageBitmap(JavaImageUtils.getJavaImage(mbitmap,MainActivity.this));
            }
        });

        buttomndk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                image.setImageBitmap(NdkImageUtils.getNdkImage(mbitmap,MainActivity.this));
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();



}
