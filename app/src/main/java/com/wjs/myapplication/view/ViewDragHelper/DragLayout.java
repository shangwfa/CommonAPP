package com.wjs.myapplication.view.ViewDragHelper;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2015/7/14.
 */
public class DragLayout extends LinearLayout {
    private final static String TAG="DragLayout";
    private  ViewDragHelper mDragHelper;
    private View mDragView;


    public DragLayout(Context context) {
        super(context);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, 1.0f, new DragHelperCallback());
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action= MotionEventCompat.getActionMasked(ev);
        if(action==MotionEvent.ACTION_CANCEL||action==MotionEvent.ACTION_UP){
            mDragHelper.cancel();
            return false;
        }
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    private class DragHelperCallback extends ViewDragHelper.Callback {


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            Logger.d(TAG,"clampViewPositionHorizontal"+left+","+dx);
            int leftBound=getPaddingLeft();
            int righBound=getWidth()-mDragView.getWidth();
            int newLeft=Math.min(Math.max(left,leftBound),righBound);
            return newLeft;
        }
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            final int topBound = getPaddingTop();
            final int bottomBound = getHeight() - mDragView.getHeight();
            final int newTop = Math.min(Math.max(top, topBound), bottomBound);
            return newTop;
        }

        /**
         * 功能：tryCaptureView方法的返回值可以决定一个parentview中哪个子view可以拖动，现在假设有两
         * 个子views (mDragView1和mDragView2)  ，如下实现tryCaptureView之后，则只有mDragView1是可以
         * 拖动的。returnchild == mDragView1;
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        /**
         * 功能：如果你想在边缘滑动的时候根据滑动距离移动一个子view，可以通过实现onEdgeDragStarted方
         * 法，并在onEdgeDragStarted方法中手动指定要移动的子View
         * @param edgeFlags
         * @param pointerId
         */
        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            mDragHelper.captureChildView(mDragView,pointerId);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView=getChildAt(0);
    }
}
