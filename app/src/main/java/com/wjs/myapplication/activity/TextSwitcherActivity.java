package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.wjs.myapplication.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextSwitcherActivity extends ActionBarActivity implements ViewSwitcher.ViewFactory {
    @Bind(R.id.ts_switcher)
    TextSwitcher switcher;
    @OnClick(R.id.button2)
    public void click(){
        switcher.setText(String.valueOf(new Random().nextInt()));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        switcher.setFactory(TextSwitcherActivity.this);
        switcher.setText("xxxxxxx");
//        // 设置切入动画
//        switcher.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
//        // 设置切出动画
//        switcher.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_out_right));
        // 上下滚屏
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_bottom);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_top);

        switcher.setInAnimation(in);
        switcher.setOutAnimation(out);
    }


    @Override
    public View makeView() {
        TextView textView = new TextView(this);
        textView.setTextSize(36);
        return textView;
    }
}
