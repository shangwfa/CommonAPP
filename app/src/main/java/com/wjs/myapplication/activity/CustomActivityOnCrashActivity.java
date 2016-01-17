package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.wjs.myapplication.R;

public class CustomActivityOnCrashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_activity_on_crash);

        throw new RuntimeException("Boom!");
    }


}
