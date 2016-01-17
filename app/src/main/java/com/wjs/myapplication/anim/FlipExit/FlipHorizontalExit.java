package com.wjs.myapplication.anim.FlipExit;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
import com.wjs.myapplication.anim.BaseAnimatorSet;

public class FlipHorizontalExit extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(ObjectAnimator.ofFloat(view, "rotationY", 0, 90),//
				ObjectAnimator.ofFloat(view, "alpha", 1, 0));
	}
}
