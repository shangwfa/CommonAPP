package com.wjs.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Administrator on 2015/7/2.
 * 自定义自动换行布局
 */
public class HorizantalFallWaterLayout extends ViewGroup{
    private  int maxWidth;//可使用的最大宽度

    public HorizantalFallWaterLayout(Context context) {
        super(context);
    }

    public HorizantalFallWaterLayout( Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizantalFallWaterLayout( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        maxWidth=MeasureSpec.getSize(widthMeasureSpec);
        int containorHeight=0;
        int count=getChildCount();
        for(int i=0;i<count;i++){
            View view=getChildAt(i);
            /*
            * >MeasureSpec.AT_MOST 该组件可以设置自己的大小，但是最大不能超过其父组件的限定
            * >MeasureSpec.EXACTLY 无论该组件设置大小是多少，都只能按照父组件限制的大小来显示
            * >MeasureSpec.UNSPECIFIED 该组件不受父组件的限制，可以设置任意大小
            * */
            view.measure(MeasureSpec.UNSPECIFIED,MeasureSpec.UNSPECIFIED);
            containorHeight+=view.getMeasuredHeight();
        }
        //onMeasure方法的关键代码，该语句设置父容器的大小
        setMeasuredDimension(maxWidth,containorHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();
        int row=1;
        int left=0;
        int right=0;
        int top=0;
        int bottom=0;
        int p=getPaddingLeft();
        for(int i=0;i<childCount;i++){
            View view=getChildAt(i);
            int width=view.getMeasuredWidth();
            int height=view.getMeasuredHeight();
            left=p+right;
            right=left+width;
            top=p*row+height*(row-1);
            bottom=top+height;
            if(right>maxWidth){
                left = 0;//每次换行后要将子组件左边“坐标”与右边“坐标”重新初始化
                right = 0;
                left = p + right;
                right = left + width;
                top = p * row + height * (row - 1);
                bottom = top + height;
            }
            view.layout(left,top,right,bottom);
        }
    }
}
