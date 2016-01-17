package com.wjs.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Administrator on 2015/7/3.
 */
public class TitleAdapter extends BaseAdapter {
    private List<String> titles;
    private Context context;
    private TextView[] tv_titles;
    int position=0;

    public TitleAdapter( List<String> titles, Context context, int position) {
        this.titles = titles;
        this.context = context;
        this.tv_titles = new TextView[titles.size()];
        this.position=position;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * 动态添加标题textView 控件，并设置布局属性
         */
        tv_titles[position]=new TextView(context);
        tv_titles[position].setGravity(Gravity.CENTER);
        tv_titles[position].setText(titles.get(position));
        tv_titles[position].setTextSize(20);

        tv_titles[position].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv_titles[position].setPadding(0,20,0,10);

        /**
         * 在选中某一标题后，重新声明adapter对象，通过构造函数给的position 确定把哪个标题的字体颜色直接初始化
         */
        if (position == this.position) {
            tv_titles[position].setTextColor(Color.WHITE);
        } else {
            tv_titles[position].setTextColor(Color.GRAY);
        }
        return tv_titles[position];
    }
}
