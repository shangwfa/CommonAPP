package com.wjs.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.wjs.myapplication.R;
import com.wjs.myapplication.Utils.Tools.ViewFindUtils;
import com.wjs.myapplication.activity.DialogHomeActivity;

/**
 * Created by Administrator on 2015/11/16.
 */
public class HomeAdapter extends BaseExpandableListAdapter {
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    // --->group
    @Override
    public int getGroupCount() {
        return DialogHomeActivity.groups.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return DialogHomeActivity.groups[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.ad_dialog_home, null);
        }

        TextView tv = ViewFindUtils.find(convertView, R.id.tv);
        tv.setText(DialogHomeActivity.groups[groupPosition]);
        return convertView;
    }

    // --->child
    @Override
    public int getChildrenCount(int groupPosition) {
        return DialogHomeActivity.childs[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return DialogHomeActivity.childs[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.ad_dialog_home, null);
        }

        TextView tv = ViewFindUtils.find(convertView, R.id.tv);
        View v_line = ViewFindUtils.find(convertView, R.id.v_line);

        v_line.setVisibility(View.INVISIBLE);
        tv.setTextColor(Color.parseColor("#383838"));
        tv.setText(DialogHomeActivity.childs[groupPosition][childPosition]);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
