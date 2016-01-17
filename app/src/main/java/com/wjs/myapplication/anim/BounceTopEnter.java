package com.wjs.myapplication.anim;

import android.util.DisplayMetrics;
import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Administrator on 2015/11/16.
 */
public class BounceTopEnter extends BaseAnimatorSet {
    public BounceTopEnter() {
        duration = 1000;
    }

    @Override
    public void setAnimation(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1, 1),//
                ObjectAnimator.ofFloat(view, "translationY", -250 * dm.density, 30, -10, 0));
    }
}

