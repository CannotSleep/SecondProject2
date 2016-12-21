package com.zyt.happy.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button start;
    private Button stop;
    private Button binder;
    private Button unbinder;
    private Button b_start;
    private Button b_stop;
    private MyService.MyBinder mBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        binder = (Button) findViewById(R.id.binder);
        unbinder = (Button) findViewById(R.id.unbinder);
        b_start= (Button) findViewById(R.id.b_start);
        b_stop= (Button) findViewById(R.id.b_stop);
        binder.setOnClickListener(this);
        unbinder.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        b_start.setOnClickListener(this);
        b_stop.setOnClickListener(this);
    }


    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mBinder= (MyService.MyBinder) binder;
            mBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                Intent startintent = new Intent(this, MyService.class);
                startService(startintent);
                break;
            case R.id.stop:
                Intent stopintent = new Intent(this, MyService.class);
                stopService(stopintent);
                break;
            case R.id.binder:
                Intent bindintent=new Intent(this,MyService.class);
                bindService(bindintent,mConnection,BIND_AUTO_CREATE);
                Log.i("aaa","binder");
                break;
            case R.id.unbinder:
                unbindService(mConnection);
                Log.i("aaa","unbinder");
                break;
            case R.id.b_start:
                Intent startintent1 = new Intent(this, FrontService.class);
                startService(startintent1);
                break;
            case R.id.b_stop:
                Intent stopintent1 = new Intent(this, FrontService.class);
                stopService(stopintent1);
                break;
        }

    }
}
