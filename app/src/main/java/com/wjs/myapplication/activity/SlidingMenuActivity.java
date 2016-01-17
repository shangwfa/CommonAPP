package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wjs.myapplication.R;

public class SlidingMenuActivity extends ActionBarActivity {
    private SlidingMenu mSlidingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);

        initSlidingMenu();
    }

    private void initSlidingMenu() {
        mSlidingMenu=new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        mSlidingMenu.setMenu(R.layout.slidingmenu);
    }




}
