package com.wjs.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Administrator on 2015/7/3.
 */
public class BodyAdapter extends BaseAdapter {
    private List<String> item_names;
    private List<Integer> item_images;
    private Context context;

    public BodyAdapter(List<String> item_names, List<Integer> item_images, Context context) {
        this.item_names = item_names;
        this.item_images = item_images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return item_images.size();
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
         * 设置图标与图标名称的布局
         */
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        /**
         * TextView名称
         */
        TextView tv_item = new TextView(context);
        tv_item.setLayoutParams(new GridView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv_item.setGravity(Gravity.CENTER);
        tv_item.setTextColor(Color.WHITE);
        tv_item.setPadding(10, 10, 10, 10);
        tv_item.setText(item_names.get(position));

        /**
         * ImageView图标
         */
        ImageView img_item = new ImageView(context);
        img_item.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
        img_item.setImageResource(item_images.get(position));

        layout.addView(img_item);
        layout.addView(tv_item);
        return layout;

    }
}
