package com.zyt.happy.servicetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by Administrator on 2016/12/19.
 */
public class BActivity extends Activity implements View.OnClickListener {
    private Button start;
    private Button stop;



    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_b);
        Log.i("bbb", Process.myPid() + "");

        start = (Button) findViewById(R.id.b_start);
        stop = (Button) findViewById(R.id.b_stop);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_start:
                Intent startintent = new Intent(this, FrontService.class);
                startService(startintent);
                break;
            case R.id.b_stop:
                Intent stopintent = new Intent(this, FrontService.class);
                stopService(stopintent);
                break;
        }
    }
}
