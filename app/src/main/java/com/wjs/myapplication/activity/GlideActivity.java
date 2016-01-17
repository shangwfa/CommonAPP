package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wjs.myapplication.R;

public class GlideActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

       ImageView imageView = (ImageView) findViewById(R.id.glide_imageView);
//
//        Glide.with(this).load("htp://image.photophoto.cn/nm-6/018/030/0180300244.jpgt").into(imageView);

        Glide.with(this)
                .load("http://image.photophoto.cn/nm-6/018/030/0180300244.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


    }


}
