package com.zyt.happy.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2016/12/19.
 */
public class MyService extends Service {
    //普通service
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("aaa","create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("aaa","startcommand");
        Notification.Builder builder=new Notification.Builder(this.getApplicationContext());
        Intent intent1 = new Intent(this,MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this,0,intent1,0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.cc))
                .setContentTitle("Title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("要显示的内容")
                .setWhen(System.currentTimeMillis());
        Notification notification=builder.build();
        notification.defaults=Notification.DEFAULT_SOUND;
        startForeground(110,notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
        Log.i("aaa","destroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyBinder extends Binder{
        public void startDownload(){
            Log.i("aaa","startdownload");
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            }).start();

        }
    }


}
