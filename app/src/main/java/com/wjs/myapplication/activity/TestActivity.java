package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.wjs.myapplication.R;

//@EActivity(R.layout.activity_test)
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }
}
