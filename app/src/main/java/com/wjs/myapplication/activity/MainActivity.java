package com.wjs.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.wjs.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends ActionBarActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_main)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nv)
    NavigationView nv;
    ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initHeader();
        initSlideMenu();
    }

    private void initSlideMenu() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.men_hui_yuan:
                        break;
                    case R.id.menu_qian_bao:
                        break;
                    case R.id.menu_zhuang_ban:
                        break;
                    case R.id.menu_shou_cang:
                        break;
                    case R.id.menu_xiang_ce:
                        break;
                }
                return false;
            }
        });
    }

    private void initHeader() {
        toolbar.setTitle("Toolbar");//设置Toolbar标题
//      toolbar.setSubtitle("subToobar");//设置子标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
//      toolbar.setLogo(R.drawable.time_logo);
//      toolbar.setNavigationIcon(R.drawable.time_logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(mDrawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }

            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(mDrawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

}
