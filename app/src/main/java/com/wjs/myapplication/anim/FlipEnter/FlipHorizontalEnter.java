package com.wjs.myapplication.anim.FlipEnter;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
import com.wjs.myapplication.anim.BaseAnimatorSet;

public class FlipHorizontalEnter extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				// ObjectAnimator.ofFloat(view, "rotationY", -90, 0));
				ObjectAnimator.ofFloat(view, "rotationY", 90, 0));
	}
}
