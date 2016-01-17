package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.wjs.myapplication.R;
import com.wjs.myapplication.adapter.MyRecyclerAdapter;
import com.wjs.myapplication.view.ItemDecoration.TestDecoration;

/**
 * Created by Lenovo on 2015/12/7.
 */
public class RecyclerViewActivity extends Activity {
    private RecyclerView recyclerView_one;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        init();
    }

    private void init() {
        //开始设置RecyclerView
        recyclerView_one=(RecyclerView)this.findViewById(R.id.recyclerView_one);
        //设置固定大小
        recyclerView_one.setHasFixedSize(true);
        //创建线性布局
        mLayoutManager = new LinearLayoutManager(this);
//        GridLayoutManager girdLayoutManager=new GridLayoutManager(this,2);
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new   StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL);
        //垂直方向
//        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //给RecyclerView设置布局管理器
        recyclerView_one.setLayoutManager(mLayoutManager);
//        recyclerView_one.setLayoutManager(girdLayoutManager);
//        recyclerView_one.setLayoutManager(staggeredGridLayoutManager);
        recyclerView_one.addItemDecoration(new TestDecoration(this));
        //创建适配器，并且设置
        mAdapter = new MyRecyclerAdapter(this);
        recyclerView_one.setAdapter(mAdapter);
    }
}

