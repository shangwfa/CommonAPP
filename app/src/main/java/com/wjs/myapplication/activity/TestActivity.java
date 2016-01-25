package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.wjs.myapplication.R;
import com.wjs.myapplication.Utils.Tools.FontsUtils;

//@EActivity(R.layout.activity_test)
public class TestActivity extends Activity {
    TextView fontTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        fontTv= (TextView) findViewById(R.id.fonts);
        FontsUtils.init(this);
        fontTv.setTypeface(FontsUtils.typefaceLatoHairline);
    }
}
