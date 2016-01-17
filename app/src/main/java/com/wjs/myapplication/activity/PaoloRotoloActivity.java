package com.wjs.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.wjs.myapplication.fragment.FragmentPage1;
import com.wjs.myapplication.fragment.FragmentPage2;
import com.wjs.myapplication.fragment.FragmentPage3;
import com.wjs.myapplication.fragment.FragmentPage4;

public class PaoloRotoloActivity extends AppIntro {


    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(new FragmentPage1());
        addSlide(new FragmentPage2());
        addSlide(new FragmentPage3());
        addSlide(new FragmentPage4());

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        //addSlide(AppIntroFragment.newInstance("引导页", "APP引导介绍", BitmapFactory.d, Color.BLUE));

        // OPTIONAL METHODS
        // Override bar/separator color
        setBarColor(Color.CYAN);
        setSeparatorColor(Color.BLUE);

        // Hide Skip/Done button
        showSkipButton(true);
        showDoneButton(true);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permesssion in Manifest
        setVibrate(false);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
    }
}
