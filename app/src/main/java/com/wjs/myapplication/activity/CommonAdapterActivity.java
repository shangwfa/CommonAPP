package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.wjs.myapplication.R;
import com.wjs.myapplication.adapter.CommonAdapter.BaseAdapterHelper;
import com.wjs.myapplication.adapter.CommonAdapter.QuickAdapter;
import com.wjs.myapplication.been.Been;

import java.util.ArrayList;

public class CommonAdapterActivity extends ActionBarActivity {
    private ListView mListView;
    private ArrayList<Been> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_adapter);
        mDatas=new ArrayList<Been>();
        for(int i=0;i<10;i++){
            Been mBeen=new Been("mTitle:"+i);
            mDatas.add(mBeen);
        }
        mListView= (ListView) findViewById(R.id.common_adapter_listview);
        mListView.setAdapter(new QuickAdapter<Been>(this, R.layout.base_listview_item, mDatas) {
            @Override
            protected void convert(BaseAdapterHelper helper, Been item) {
                helper.setText(R.id.id_tv_title,item.getmTitle());
            }
        });

    }


}
