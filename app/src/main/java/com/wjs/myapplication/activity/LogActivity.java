package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wjs.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class LogActivity extends ActionBarActivity {
    private final static  String TAG="LogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Log.d(TAG, "hello");

        Logger.d("hello");
        Logger.d("hello %s %d", "world", 5);   // String.format


        Logger.d("hello");
        Logger.e("hello");
        Logger.w("hello");
        Logger.v("hello");
        Logger.wtf("hello");
        Map map=new HashMap();
        map.put("name","shangwfa");
        map.put("age","24");
        Logger.json(String.valueOf(map));
        Logger.xml(String.valueOf(map));
    }


}
