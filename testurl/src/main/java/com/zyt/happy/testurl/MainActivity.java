package com.zyt.happy.testurl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //线程优化 线程池

         ExecutorService executorService= Executors.newCachedThreadPool();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建网络连接
                    URL url  = new URL("http://www.baidu.com/");



                    //获取数据(IO流)
                    InputStream in = url.openStream();
                    //转换  //线程安全
                    BufferedInputStream inputStream= new BufferedInputStream(in);

                    StringBuffer str = new StringBuffer("");

                    byte b[]=new byte[1024*1024];

                    int count=0;

                    while((count=inputStream.read(b))!=-1){
                        str.append(new String(b,0,count));
                    }
                    Log.i("aa",str+"");

                    inputStream.close();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }).start();


        try {
            URL url = new URL("");
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            //android网络请求方式 get（默认）    post
            //connection.getInputStream();
            //connection.getOutputStream();
            BufferedInputStream buff = new BufferedInputStream(connection.getInputStream());

            Log.i("aaa",connection.getResponseCode()+"");
            Log.i("aaa",connection.getDoInput()+"");
            Log.i("aaa",connection.getRequestMethod());
            Log.i("aaa",connection.getDoOutput()+"");
            Log.i("aaa",connection.getConnectTimeout()+"");
//            Log.i("aaa",);

            connection.setConnectTimeout(5000);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //载入图片

    private static final int UPDATE_UI =123;

    private void AsyncImage() throws Exception {
        URL url  = new URL("");
        HttpURLConnection coon = (HttpURLConnection) url.openConnection();
        BufferedInputStream inputStream= null;
        Bitmap bit = null;
        if(coon.getResponseCode()==200){
            inputStream=new BufferedInputStream(coon.getInputStream());
            bit= BitmapFactory.decodeStream(inputStream);
        }

        mHandler.sendEmptyMessage(UPDATE_UI);



    }
    private MyHandler mHandler;
    private class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }



}
