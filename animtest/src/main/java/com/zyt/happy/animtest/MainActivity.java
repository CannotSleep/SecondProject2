package com.zyt.happy.animtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView im;
    ImageView ball;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im= (ImageView) findViewById(R.id.ball);
        ball= (ImageView) findViewById(R.id.ball_two);
    }

    public void testAnim(final View view){

        final ObjectAnimator anim= ObjectAnimator.ofFloat(view,"zhy",1.0f,0.0f)
                .setDuration(3000);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(2);
        anim.start();

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.postInvalidate();
                view.invalidate();
                //不用AnimationSet来形成多个动画播放
                float cal= (float) animator.getAnimatedValue();
                view.setAlpha(cal);
                view.setScaleX(cal);
                view.setScaleY(cal);
            }
        });
    }

    /**
     * 一个动画实现多个效果
     */
    public void viewholder(View view){
        PropertyValuesHolder phx=PropertyValuesHolder.ofFloat("alpha",1f,0f,1f);
        PropertyValuesHolder phy=PropertyValuesHolder.ofFloat("scaleX",1f,0f,1f);
        PropertyValuesHolder phz=PropertyValuesHolder.ofFloat("scaleY",1f,0f,1f);
        ObjectAnimator.ofPropertyValuesHolder(view,phx,phy,phz).setDuration(1000).start();
    }


    //垂直
    public void viewDown(View view){
       //获取设备屏幕高度
        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        float mscreen=dm.heightPixels;

        ValueAnimator animator=ValueAnimator.ofFloat(0,mscreen-im.getHeight());
        animator.setTarget(im);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                im.setTranslationY((Float) animator.getAnimatedValue());
            }
        });
    }

    //抛物线
    public void viewFly(View view){
        ValueAnimator valueAnimator=new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0,0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
        {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue)
            {
//                Log.e(TAG, fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                PointF point = (PointF) animation.getAnimatedValue();
                im.setX(point.x);
                im.setY(point.y);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }
        });

    }



    //AnimationSet测试
    public void together(View view){
        ObjectAnimator anim1=ObjectAnimator.ofFloat(ball,"scaleX",1.0f,2f);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(ball,"scaleY",1.0f,2f);
        AnimatorSet animatorSet =new AnimatorSet();
        animatorSet.setDuration(3000);
        animatorSet.setInterpolator(new LinearInterpolator());

        animatorSet.playTogether(anim1,anim2);
        animatorSet.start();

    }

    //顺序执行
    public void order(View view){
        ObjectAnimator anim1=ObjectAnimator.ofFloat(ball,"scaleX",1.0f,2f);
        ObjectAnimator anim2=ObjectAnimator.ofFloat(ball,"scaleY",1.0f,2f);
        ObjectAnimator anim3=ObjectAnimator.ofFloat(ball,"x",ball.getX(),0f);
        ObjectAnimator anim4=ObjectAnimator.ofFloat(ball,"y",ball.getY(),0f);

        AnimatorSet set=new AnimatorSet();
        set.play(anim1).with(anim2);
        set.play(anim2).after(anim3);
        set.play(anim4).after(anim3);
        set.setDuration(1000);
        set.start();

    }


}
