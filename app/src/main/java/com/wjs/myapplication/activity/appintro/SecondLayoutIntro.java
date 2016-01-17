package com.wjs.myapplication.activity.appintro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro2;
import com.wjs.myapplication.R;
import com.wjs.myapplication.activity.MainActivity;
import com.wjs.myapplication.fragment.SampleSlide;

public class SecondLayoutIntro extends AppIntro2 {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro_2));
        addSlide(SampleSlide.newInstance(R.layout.intro2_2));
        addSlide(SampleSlide.newInstance(R.layout.intro3_2));
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}
