package com.wjs.myapplication.Utils.Tools;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by admin on 2016/1/18.
 */
public class FontsUtils {
    public static Typeface typefaceLatoRegular;
    public static Typeface typefaceLatoHairline;
    public static Typeface typefaceLatoLight;

    public static void init(Context context){
        typefaceLatoRegular = Typeface.createFromAsset(
                context.getAssets(), "fonts/Lato-Regular.ttf");
        typefaceLatoHairline = Typeface.createFromAsset(
                context.getAssets(), "fonts/Lato-Hairline.ttf");
        typefaceLatoLight = Typeface.createFromAsset(
                context.getAssets(), "fonts/LatoLatin-Light.ttf");
    }
}

/**
 *
 *
 *
 *fontTv= (TextView) findViewById(R.id.fonts);
 *FontsUtils.init(this);
 *fontTv.setTypeface(FontsUtils.typefaceLatoHairline);
 */
