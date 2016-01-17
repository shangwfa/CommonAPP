package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.wjs.myapplication.R;

/**
 * Created by Administrator on 2015/7/13.
 */
public class AnimationActivitySecond extends Activity{
    private View mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_second);
        mRootView=findViewById(R.id.root);
        if(savedInstanceState==null){
            mRootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mRootView.getViewTreeObserver().removeOnPreDrawListener(this);
                    startRootAnimation();
                    return true;

                }

                private void startRootAnimation() {
                    mRootView.setScaleY(0.1f);
                    mRootView.setPivotY(mRootView.getY()+mRootView.getHeight()/2);
                    mRootView.animate().scaleY(1.0f).setDuration(1000).setInterpolator(new AccelerateDecelerateInterpolator()).start();
                }
            });
        }
    }
}
