package com.zyt.happy.servicetest;



import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/12/19.
 * 前台service
 */
public class FrontService extends Service{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("bbb","oncreate");
        Log.i("bbb", Process.myPid()+"");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("bbb","startcommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
