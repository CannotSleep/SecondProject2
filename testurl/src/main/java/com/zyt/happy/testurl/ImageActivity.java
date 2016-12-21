package com.zyt.happy.testurl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/21.
 */

public class ImageActivity extends Activity{
    private ImageView im;
    private Bitmap bit;
    private MyHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        im= (ImageView) findViewById(R.id.image_a);
        mHandler=new MyHandler();
        loadima();
    }

    private void loadima(){
            new Thread(new Runnable() {
                @Override
                public void  run() {
                    BufferedInputStream in=null;
                    try {
                        URL url = new URL("http://4493bz.1985t.com/uploads/allimg/150127/4-15012G52133.jpg");
                        HttpURLConnection coon = (HttpURLConnection) url.openConnection();
                        if(coon.getResponseCode()==200){
                            in=new BufferedInputStream(coon.getInputStream());
                            bit= BitmapFactory.decodeStream(in);
                        }
                        mHandler.sendEmptyMessage(123);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(in!=null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                }
            }).start();
    }

    class  MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==123 && bit!=null){
                im.setImageBitmap(bit);
            }
        }
    }


}
