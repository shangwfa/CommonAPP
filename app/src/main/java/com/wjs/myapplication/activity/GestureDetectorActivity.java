package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.orhanobut.logger.Logger;
import com.wjs.myapplication.R;

public class GestureDetectorActivity extends Activity {
    private GestureDetector mGestureDetector;
    private final static String TAG="GestureDetectorActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);
        mGestureDetector=new GestureDetector(this,new MySimpleOnGestureListener());
    }
    private class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener{
        public MySimpleOnGestureListener() {
            super();
        }
        // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
        ///轻击一下屏幕，立刻抬起来，才会有这个触发
        //从名子也可以看出,一次单独的轻击抬起操作,当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以这个事件 就不再响应
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Logger.d("onSingleTapUp");
            return super.onSingleTapUp(e);
        }
        // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
        @Override
        public void onLongPress(MotionEvent e) {
            Logger.d("onLongPress");
            super.onLongPress(e);
        }
        // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Logger.d("onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
        // 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Logger.d("onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
        }
        /*
         * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
         * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
         *
         * 而onDown也是由一个MotionEventACTION_DOWN触发的，但是他没有任何限制，
         * 也就是说当用户点击的时候，首先MotionEventACTION_DOWN，onDown就会执行，
         * 如果在按下的瞬间没有松开或者是拖动的时候onShowPress就会执行，如果是按下的时间超过瞬间
         * （这块我也不太清楚瞬间的时间差是多少，一般情况下都会执行onShowPress），拖动了，就不执行onShowPress。
         */
        @Override
        public void onShowPress(MotionEvent e) {
            Logger.d("onShowPress");
            super.onShowPress(e);
        }
        // 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
        @Override
        public boolean onDown(MotionEvent e) {
            Logger.d("onDown");
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Logger.d("onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Logger.d("onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Logger.d("onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.d("onTouch");
        //1.把触摸事件交给GestureDetector
        return mGestureDetector.onTouchEvent(event);
    }



}
