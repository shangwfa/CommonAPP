package com.wjs.myapplication.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wjs.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/6.
 */
public class ScrollViewAndListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList=new ArrayList<String>();
    private LayoutInflater mLayoutInflater;
    public ScrollViewAndListViewAdapter( Context mContext,List<String> mList){
        this.mContext=mContext;
        this.mList=mList;
        mLayoutInflater=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        ViewHolder mHolder;
            if(convertView==null){
                convertView=mLayoutInflater.inflate(R.layout.scrollview_and_listview_item,null);
                mHolder=new ViewHolder();
                mHolder.info= (TextView) convertView.findViewById(R.id.list_item);
                convertView.setTag(mHolder);
            }else {
                mHolder= (ViewHolder) convertView.getTag();
            }
            mHolder.info.setText(mList.get(position));

        return convertView;
    }

    public final class ViewHolder{
        public TextView info;
    }
}
