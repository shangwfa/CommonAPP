package com.wjs.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/10/4.
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth=MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight=MeasureSpec.getMode(widthMeasureSpec);

        //wrap_content
        int width=0;
        int height=0;
        //记录每一行的高度和宽度
        int lineWidth=0;
        int lineHeight=0;
        //得到内部元素的个数
        int cCount=getChildCount();
        for (int i=0;i<cCount;i++){
            View child=getChildAt(i);
            //测量子View的宽高
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp= (MarginLayoutParams) child.getLayoutParams();
            //子View占据的宽度和高度
            int childWidth=child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            int childHeight=child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            //换行
            if(lineHeight+childWidth>width){
                width=Math.max(width,lineWidth);
                lineWidth=childWidth;
                height+=childHeight;
                lineHeight=childHeight;
            }else {//不换行
                //叠加行宽
                lineWidth+=childWidth;
                //当前行的最大高度
                lineHeight=Math.max(lineHeight,childHeight);
            }
            if(i==cCount){
                width=Math.max(width,lineWidth);
                height+=childHeight;
            }
        }
       setMeasuredDimension(modeWidth==MeasureSpec.EXACTLY?sizeWidth:width,modeHeight==MeasureSpec.EXACTLY?sizeHeight:height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
