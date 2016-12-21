package com.zyt.happy.testhandler;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by Administrator on 2016/12/20.
 */

public class HandlerColum extends Activity implements View.OnClickListener{
    private EditText edt;
    private Button btn;
    private TextView tv;

    private Handler mHandler;
    private List<Integer>  list = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column);
        edt= (EditText) findViewById(R.id.edt);
        btn= (Button) findViewById(R.id.btn);
        tv= (TextView) findViewById(R.id.tv);
        btn.setOnClickListener(this);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==123){
                    int num =msg.getData().getInt("num");
                    outer:  for(int i=2;i<num;i++){
                        for (int j = 2; j < Math.sqrt(i); j++) {
                            //如果可以整除，则表明这个数不是质数
                            if (i != 2 && i % j == 0) {
                                continue outer;
                            }
                        }
                        list.add(i);
                    }

                    tv.setText(list.toString());
                    list.removeAll(list);
                }
            }
        };





    }




    @Override
    public void onClick(View view) {
           int num=Integer.parseInt(edt.getText().toString());
            Message message = new Message();
            message.what=123;
            Bundle bundle=new Bundle();
            bundle.putInt("num",num);
            message.setData(bundle);
            mHandler.sendMessage(message);
    }
}
