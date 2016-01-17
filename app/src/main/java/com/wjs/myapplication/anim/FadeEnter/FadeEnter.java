package com.wjs.myapplication.anim.FadeEnter;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
import com.wjs.myapplication.anim.BaseAnimatorSet;

public class FadeEnter extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(duration));
	}
}
