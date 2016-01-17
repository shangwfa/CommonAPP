package com.wjs.myapplication.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2015/7/16.
 */
public class StickyNavLayout extends LinearLayout {
    private View mTop;
    private View mNav;
    private ViewPager mViewPager;

    private int mTopViewHeight;
    private ScrollView mInnerScrollView;
    private boolean isTopHidden = false;

    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaximumVelocity, mMinimumVelocity;

    private float mLastY;
    private boolean mDragging;


    public StickyNavLayout(Context context) {
        super(context);
    }

    public StickyNavLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        mScroller = new OverScroller(context);
        
    }

    public StickyNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
