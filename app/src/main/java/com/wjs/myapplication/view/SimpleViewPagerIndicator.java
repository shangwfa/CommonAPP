package com.wjs.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wjs.myapplication.R;


public class SimpleViewPagerIndicator extends LinearLayout {

	private static final int COLOR_TEXT_NORMAL = 0xFF999999;
	private static final int COLOR_INDICATOR_COLOR = 0xFFFF712B;
	

	private String[] mTitles;
	private int mTabCount = 1;
	private int mIndicatorColor = COLOR_INDICATOR_COLOR;
	private float mTranslationX;
	private Paint mPaint = new Paint();
	private Paint mPaintLine = new Paint();
	private int mTabWidth;
	private int sreenWidth;
	private ViewPager viewPager;
	private int oldPosition = 0;
	private boolean isFirst=true;
	public SimpleViewPagerIndicator(Context context) {
		this(context, null);
	}

	public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint.setColor(mIndicatorColor);
		mPaint.setStrokeWidth(9.0F);
		mPaintLine.setColor(context.getResources().getColor(Color.BLUE));
		mPaintLine.setStrokeWidth(9.0F);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(isFirst){
			mTabWidth = w / mTabCount;
			sreenWidth=w;
			isFirst=false;
		}
		
	}

	public void setTitles(String[] titles) {
		mTitles = titles;
		mTabCount = titles.length;
		generateTitleView();

	}

	public void setIndicatorColor(int indicatorColor) {
		this.mIndicatorColor = indicatorColor;
	}

	public void setViewPager(ViewPager viewPager){
		this.viewPager = viewPager;
		this.viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				((TextView) getChildAt(oldPosition)).setTextColor(COLOR_TEXT_NORMAL);
				((TextView) getChildAt(arg0)).setTextColor(COLOR_INDICATOR_COLOR);
				oldPosition = arg0;
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				scroll(arg0, arg1);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		canvas.save();
		canvas.translate(mTranslationX, getHeight() - 6);
		canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
		canvas.restore();
		canvas.translate(0, getHeight() - 1);
		canvas.drawLine(0, 0, sreenWidth, 0, mPaintLine);
	}

	public void scroll(int position, float offset) {
		/**
		 * <pre>
		 *  0-1:position=0 ;1-0:postion=0;
		 * </pre>
		 */
		mTranslationX = getWidth()/mTabCount * (position + offset);
		invalidate();
		
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	private void generateTitleView() {
		if (getChildCount() > 0)
			this.removeAllViews();
		int count = mTitles.length;

		setWeightSum(count);
		for (int i = 0; i < count; i++) {
			TextView tv = new TextView(getContext());
			LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			tv.setTag(i);
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(COLOR_TEXT_NORMAL);
			//tv.setBackgroundColor(R.color.asset_holding_gray);
			tv.setText(mTitles[i]);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			tv.setLayoutParams(lp);

			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					int position = (Integer) arg0.getTag();
                    if (viewPager != null&&viewPager.getVisibility()!=View.GONE)  
                    {  
                        viewPager.setCurrentItem(position);  
                        
                    }else{
                    	
                    }  
				}
			});
			addView(tv);
		}
		//setIndicator(0);
	}
	
	

	public void setIndicator(int position) {
		for (int i = 0; i < getChildCount(); i++) {
			if (i == position) {
				((TextView) getChildAt(i)).setBackgroundColor(Color.YELLOW);
				((TextView) getChildAt(i)).setTextColor(Color.WHITE);
			}
			((TextView) getChildAt(i)).setBackgroundColor(Color.GRAY);
			((TextView) getChildAt(i)).setTextColor(Color.WHITE);
		}
		invalidate();
	}
	
	 public void setCurrentItem(int position)  
	    {  
	        oldPosition = position;  
	        if (viewPager != null)  
	        {  
	            viewPager.setCurrentItem(position);  
	        }  
	    }  
}
