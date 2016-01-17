package com.wjs.myapplication.Utils.Tools.viewpagertransformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class StackTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        view.setTranslationX(position < 0 ? 0f : -view.getWidth() * position);
    }

}
