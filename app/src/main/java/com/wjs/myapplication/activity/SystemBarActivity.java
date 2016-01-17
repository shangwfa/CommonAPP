package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wjs.myapplication.R;
import com.wjs.myapplication.Utils.Tools.SystemBarTintManager;

public class SystemBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_bar);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint 激活导航栏
        tintManager.setNavigationBarTintEnabled(true);
        //设置系统栏设置颜色
        //tintManager.setTintColor(R.color.red);
        //给状态栏设置颜色
        tintManager.setStatusBarTintResource(R.color.common_orange);
        // 设置导航栏设置资源
        tintManager.setNavigationBarTintResource(R.color.common_orange_deep);
    }
}
