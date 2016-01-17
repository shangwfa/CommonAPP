package com.wjs.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.wjs.myapplication.been.MainTab;
import com.wjs.myapplication.R;

public class FragmentTabHostActivity extends FragmentActivity {
    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;
    private MainTab[] tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);
        initView();
    }

    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        tabs = MainTab.values();
        final int size = tabs.length;
        for(int i = 0; i < size; i++){
            MainTab mainTab=tabs[i];
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(mainTab.getResName())).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mainTab.getClz(), null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.MAGENTA);
        }
        //mTabHost.getTabWidget().setVisibility(View.GONE);//隐藏自带的Tab按键
    }
    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index){
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(tabs[index].getResIcon());

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(getString(tabs[index].getResName()));

        return view;
    }

}
