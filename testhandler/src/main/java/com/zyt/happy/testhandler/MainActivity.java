package com.zyt.happy.testhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView tv;
    private int a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.time_tv);
        mHandler.postDelayed(mRunnable,1000);
    }

    Handler mHandler =new Handler();
    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            a++;
            tv.setText(""+a);
            mHandler.postDelayed(this,1000);
        }
    };


}
