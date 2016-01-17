package com.wjs.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.wjs.myapplication.R;

public class AnimationActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getDelegate().setContentView(R.layout.activity_animation);

        ((Button) findViewById(R.id.mBt)).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent mIntent = new Intent(AnimationActivity.this, AnimationActivitySecond.class);
                startActivity(mIntent);
            }
        });
    }

}
