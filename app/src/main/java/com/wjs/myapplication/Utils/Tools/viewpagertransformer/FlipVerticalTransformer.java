package com.wjs.myapplication.Utils.Tools.viewpagertransformer;

import android.view.View;

/**
 * Created by Administrator on 2015/11/16.
 */
public class FlipVerticalTransformer extends ABaseTransformer {

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = -180f * position;

        view.setAlpha(rotation > 90f || rotation < -90f ? 0f : 1f);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationX(rotation);
    }

}
