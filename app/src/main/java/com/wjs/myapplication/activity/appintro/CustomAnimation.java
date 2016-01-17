package com.wjs.myapplication.activity.appintro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.nineoldandroids.view.ViewHelper;
import com.wjs.myapplication.R;
import com.wjs.myapplication.activity.MainActivity;
import com.wjs.myapplication.fragment.SampleSlide;

public class CustomAnimation extends AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));
        addSlide(SampleSlide.newInstance(R.layout.intro4));

        setCustomTransformer(new ZoomOutPageTransformer());
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                ViewHelper.setAlpha(view, 0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    ViewHelper.setTranslationX(view, horzMargin - vertMargin / 2);
                } else {
                    ViewHelper.setTranslationX(view, -horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                ViewHelper.setScaleX(view, scaleFactor);
                ViewHelper.setScaleY(view, scaleFactor);

                // Fade the page relative to its size.
                ViewHelper.setAlpha(view, MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                ViewHelper.setAlpha(view, 0);
            }
        }
    }
}