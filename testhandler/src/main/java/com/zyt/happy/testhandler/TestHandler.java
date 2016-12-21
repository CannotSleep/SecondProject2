package com.zyt.happy.testhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2016/12/20.
 */

public class TestHandler extends Activity {
    private ImageView im;
    private int  a;
    private int[] images={R.drawable.wancana,R.drawable.wucana,R.drawable.zaoa};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        im=(ImageView) findViewById(R.id.im);
        final Handler mhandl=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.arg1==123){
                    a++;
                    im.setImageResource(images[a%3]);
                }
            }
        };

        //定义一个计时器设置为延迟0ms后执行，每隔1s执行一次（这里如果设置为 timer.schedule(task,1000)表示执行一次）
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1=123;
                mhandl.sendMessage(message);
            }
        },0,1000);

    }





}
