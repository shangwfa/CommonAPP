package com.wjs.myapplication.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.Timer;
import java.util.TimerTask;

public class AutoTextView extends TextSwitcher implements ViewFactory, Runnable {
//	private final float mHeight;
	private final Context mContext;
	
	// mInUp,mOutUp分离构成向下翻页的进出动画
	private Rotate3dAnimation mInUp;
	private Rotate3dAnimation mOutUp;
	
	// mInDown,mOutDown分离构成向下翻页的进出动画
	private Rotate3dAnimation mInDown;
	private Rotate3dAnimation mOutDown;

	public AutoTextView(Context context) {
		this(context, null);
	}

	public AutoTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
//		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.auto3d);
//		mHeight = a.getDimension(R.styleable.auto3d_textSize, 36);
//		mHeight = DisplayUtils.dip2px(context, 36);
		mContext = context;
		
		setFactory(this);
		
		Animation in = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);   
        Animation out = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_out);   
        this.setInAnimation(in);   
        this.setOutAnimation(out);   
		
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		mInUp = createAnim(-90, 0, true, true);
		mOutUp = createAnim(0, 90, false, true);
		mInDown = createAnim(90, 0, true, false);
		mOutDown = createAnim(0, -90, false, false);
		// TextSwitcher重要用于文件切换，比如 从文字A 切换到 文字 B，
		// setInAnimation()后，A将执行inAnimation，
		// setOutAnimation()后，B将执行OutAnimation
		setInAnimation(mInUp);
		setOutAnimation(mOutUp);
	}
	
	private String [] textArray;
	private int curIndex = 0;
	
	public void setTextArray(String [] textArray) {
		this.textArray = textArray;
		if(textArray!=null){
			if(textArray.length>1){
				this.startTimer();
			}else{
				this.setText(textArray[curIndex]);
			}	
		}
		
		
	}
	
	private Timer timer;
	private void startTimer() {
		if(timer == null) {
			timer = new Timer(true);
			timer.schedule(new TimerTask() {
				public void run() {
					post(AutoTextView.this);
				}
			}, 1000, 5000);
		}
	}
	
	private void stopTimer() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		
			
		
		
	}
	
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		this.stopTimer();
	}
	
	public interface OnSwitcherClick {
		public void onClick(View v, int index);
	}
	
	/**
	 * 设置监听, 带上Index
	 * @param listener
	 */
	public void setOnClickListener(final OnSwitcherClick listener) {
		if(listener != null) {
			this.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(v, curIndex);
				}
			});
		}
	}

	private Rotate3dAnimation createAnim(float start, float end, boolean turnIn, boolean turnUp) {
		final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, turnIn, turnUp);
		rotation.setDuration(800);
		rotation.setFillAfter(false);
		rotation.setInterpolator(new AccelerateInterpolator());
		return rotation;
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		final TextView t = new TextView(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		t.setLayoutParams(params);
		t.setTextColor(0xffff5400);
		t.setGravity(Gravity.CENTER);
		t.setTextSize(11);
		return t;
	}

	// 定义动作，向下滚动翻页
	public void previous() {
		if (getInAnimation() != mInDown) {
			setInAnimation(mInDown);
		}
		if (getOutAnimation() != mOutDown) {
			setOutAnimation(mOutDown);
		}
	}

	// 定义动作，向上滚动翻页
	public void next() {
		if (getInAnimation() != mInUp) {
			setInAnimation(mInUp);
		}
		if (getOutAnimation() != mOutUp) {
			setOutAnimation(mOutUp);
		}
	}

	class Rotate3dAnimation extends Animation {
		private final float mFromDegrees;
		private final float mToDegrees;
		private float mCenterX;
		private float mCenterY;
		private final boolean mTurnIn;
		private final boolean mTurnUp;
		private Camera mCamera;

		public Rotate3dAnimation(float fromDegrees, float toDegrees, boolean turnIn, boolean turnUp) {
			mFromDegrees = fromDegrees;
			mToDegrees = toDegrees;
			mTurnIn = turnIn;
			mTurnUp = turnUp;
		}

		@Override
		public void initialize(int width, int height, int parentWidth, int parentHeight) {
			super.initialize(width, height, parentWidth, parentHeight);
			mCamera = new Camera();
			mCenterY = getHeight() / 2;
			mCenterX = getWidth() / 2;
		}

		@Override
		protected void applyTransformation(float interpolatedTime, Transformation t) {
			final float fromDegrees = mFromDegrees;
			float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
			final float centerX = mCenterX;
			final float centerY = mCenterY;
			final Camera camera = mCamera;
			final int derection = mTurnUp ? 1 : -1;
			final Matrix matrix = t.getMatrix();
			camera.save();
			if (mTurnIn) {
				camera.translate(0.0f, derection * mCenterY * (interpolatedTime - 1.0f), 0.0f);
			} else {
				camera.translate(0.0f, derection * mCenterY * (interpolatedTime), 0.0f);
			}
			camera.rotateX(degrees);
			camera.getMatrix(matrix);
			camera.restore();
			matrix.preTranslate(-centerX, -centerY);
			matrix.postTranslate(centerX, centerY);
		}
	}

	@Override
	public void run() {
		if(textArray != null && textArray.length > 1) {
			curIndex++;
			curIndex = curIndex % textArray.length;
			next();
			this.setText(textArray[curIndex]);
		}
	}
}
