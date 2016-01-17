package com.wjs.myapplication.anim.Attention;

import android.view.View;
import android.view.animation.CycleInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;
import com.wjs.myapplication.anim.BaseAnimatorSet;

public class ShakeVertical extends BaseAnimatorSet {
	public ShakeVertical() {
		duration = 1000;
	}
	@Override
	public void setAnimation(View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", -10, 10);
		animator.setInterpolator(new CycleInterpolator(5));
		animatorSet.playTogether(animator);
	}
}
