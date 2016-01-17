package com.wjs.myapplication.view.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;



import java.math.BigDecimal;
import java.text.DecimalFormat;



@SuppressLint("HandlerLeak")
public class MagicTextView extends TextView implements PullToRefreshScrollView.ScrollListener {
    // view ����߶�
    private int mHeight;
    // view ����scrollView��˸߶�
    private int locHeight;
    // �ݼ�/���� �ı���ֵ
    private double mRate;
    // view ���õ�ֵ
    private double mValue;
    // ��ǰ��ʾ��ֵ
    private double mCurValue;
    // ��ǰ�仯������״̬��Ŀ��ֵ
    private double mGalValue;
    // ���ƼӼ���
    private int rate = 1;
    // ��ǰ�仯״̬(��/��/����)
    private int mState = 0;
    private boolean refreshing;
    private static final int REFRESH = 1;
    private static final int SCROLL = 2;
    // ƫ���� ��Ҫ��������У�����롣
    private static final int OFFSET = 20;

    DecimalFormat fnum = new DecimalFormat("0.00");
    

    private Handler mHandler = new Handler() {
        public void handleMessage( Message msg) {
            switch (msg.what) {

                case REFRESH:
                    if (rate * mCurValue < mGalValue) {
                        refreshing = true;
                        setText(fnum.format(mCurValue));
                        mCurValue += mRate * rate;
                        mHandler.sendEmptyMessageDelayed(REFRESH, 50);
                    } else {
                        refreshing = false;
                        setText(fnum.format(mGalValue));
                    }
                    break;

                case SCROLL:
                    doScroll(msg.arg1, msg.arg2);
                    break;

                default:
                    break;
            }
        };
    };

    public MagicTextView( Context context) {
        super(context);
    }

    public MagicTextView( Context context, AttributeSet set) {
        super(context, set);
    }

    public MagicTextView( Context context, AttributeSet set, int defStyle) {
        super(context, set, defStyle);
    }

    public void setLocHeight(int height) {
        locHeight = height;
    }

    public void setValue(double value) {
        mCurValue = 0.00;
        mGalValue = isShown() ? value : 0;
        mValue = value;
        mRate = (double) (mValue / 20.00);
        BigDecimal b = new BigDecimal(mRate);
        mRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    @Override
    public void onScrollChanged(int state, int scroll) {
        Message msg = mHandler.obtainMessage();
        msg.what = SCROLL;
        msg.arg1 = state;
        msg.arg2 = scroll;
        mHandler.sendMessage(msg);
    }

    private void doScroll(int state, int scroll) {
        if (mState == state && refreshing)
            return;

        mState = state;
        if (doMinus(scroll)) {
            rate = -1;
            mGalValue = 0;
        } else if (doPlus(scroll)) {
            rate = 1;
            mGalValue = mValue;
        }
        mHandler.sendEmptyMessage(REFRESH);
    }

    private boolean doPlus(int scroll) {
        if (isShown() && (scroll + 1280 > locHeight + OFFSET)
                && (mState == PullToRefreshScrollView.UP))
            return true;
        if (isShown() && (scroll < locHeight) && mState == PullToRefreshScrollView.DOWN)
            return true;

        return false;
    }

    private boolean doMinus(int scroll) {
        if (isShown() && (scroll > locHeight) && (mState == PullToRefreshScrollView.UP))
            return true;

        if (isShown() && (scroll + 1280 - mHeight < locHeight - OFFSET)
                && (mState == PullToRefreshScrollView.DOWN))
            return true;

        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // ִ����onMeasure�󼴿ɻ��view�Ŀ��
        mHeight = getMeasuredHeight();
    }
}
