package com.zyt.happy.secondproject.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zyt.happy.secondproject.R;

/**
 * Created by Administrator on 2016/12/19.
 * Activity的基类
 */
public abstract  class BaseActivity extends FragmentActivity{

    //状态栏状态值变换
    Boolean isFull=true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //决定是否隐藏状态栏
        isFull=fullscreen();

        if(isFull){
            //如果isFull为真则隐藏状态栏
            //隐藏标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //隐藏状态栏
            getWindow()
            .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                    ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            //如果为flase则隐藏状态栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        setContentView(setView());

        initView();

        setListener();
    }


    //关联布局
    protected  abstract int setView();
    //关联控件
    protected  abstract void initView();
    //设置监听
    protected abstract void setListener();
    //抽象方法没有方法体
    protected abstract boolean fullscreen();


    /**
     * 日志打印的方法
     * @param string
     */
    public void log(String string){
        Log.i("aa",string);
    }

    /**
     * 弹长吐司
     * @param toast
     */
    public void toast(String toast){
        Toast.makeText(this,toast,Toast.LENGTH_LONG).show();
    }

    /**
     * 加载数据时显示的dialog
     */
    private Dialog dialog;
    /**
     * 加载数据时候显示的dialog
     * @param msg 显示的文字
     * @param cancelable 可以不可以用"返回键"取消
     */
    public void showLoadingDialog(String msg,boolean cancelable){
        View v=getLayoutInflater().inflate(R.layout.dialog_loading,null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);
        ImageView im= (ImageView) v.findViewById(R.id.iv_dialogloading_img);
        TextView tv= (TextView) v.findViewById(R.id.tv_dialogloading_msg);
        Animation animotion= AnimationUtils.loadAnimation(this,R.anim.loading_animation);
        im.setAnimation(animotion);
        if(msg!=null){
            tv.setText(msg);
        }
        dialog=new Dialog(this,R.style.loading_dialog);
        dialog.setCancelable(cancelable);
        dialog.setContentView(layout);
        dialog.show();
    }

    public void cancleDialog(){
        if(dialog!=null){
            dialog.dismiss();
        }
    }



}
