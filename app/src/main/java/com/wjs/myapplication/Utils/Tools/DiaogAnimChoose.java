package com.wjs.myapplication.Utils.Tools;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.wjs.myapplication.activity.DialogHomeActivity;
import com.wjs.myapplication.anim.Attention.Flash;
import com.wjs.myapplication.anim.Attention.RubberBand;
import com.wjs.myapplication.anim.Attention.ShakeHorizontal;
import com.wjs.myapplication.anim.Attention.ShakeVertical;
import com.wjs.myapplication.anim.Attention.Swing;
import com.wjs.myapplication.anim.Attention.Tada;
import com.wjs.myapplication.anim.BaseAnimatorSet;
import com.wjs.myapplication.anim.BounceEnter.BounceBottomEnter;
import com.wjs.myapplication.anim.BounceEnter.BounceEnter;
import com.wjs.myapplication.anim.BounceEnter.BounceLeftEnter;
import com.wjs.myapplication.anim.BounceEnter.BounceRightEnter;
import com.wjs.myapplication.anim.BounceTopEnter;
import com.wjs.myapplication.anim.FadeEnter.FadeEnter;
import com.wjs.myapplication.anim.FadeExit.FadeExit;
import com.wjs.myapplication.anim.FallEnter.FallEnter;
import com.wjs.myapplication.anim.FallEnter.FallRotateEnter;
import com.wjs.myapplication.anim.FlipEnter.FlipBottomEnter;
import com.wjs.myapplication.anim.FlipEnter.FlipHorizontalEnter;
import com.wjs.myapplication.anim.FlipEnter.FlipHorizontalSwingEnter;
import com.wjs.myapplication.anim.FlipEnter.FlipLeftEnter;
import com.wjs.myapplication.anim.FlipEnter.FlipRightEnter;
import com.wjs.myapplication.anim.FlipEnter.FlipTopEnter;
import com.wjs.myapplication.anim.FlipEnter.FlipVerticalEnter;
import com.wjs.myapplication.anim.FlipExit.FlipHorizontalExit;
import com.wjs.myapplication.anim.FlipExit.FlipVerticalExit;
import com.wjs.myapplication.anim.FlipVerticalSwingEnter;
import com.wjs.myapplication.anim.Jelly;
import com.wjs.myapplication.anim.NewsPaperEnter;
import com.wjs.myapplication.anim.SlideBottomExit;
import com.wjs.myapplication.anim.SlideEnter.SlideBottomEnter;
import com.wjs.myapplication.anim.SlideEnter.SlideLeftEnter;
import com.wjs.myapplication.anim.SlideEnter.SlideRightEnter;
import com.wjs.myapplication.anim.SlideEnter.SlideTopEnter;
import com.wjs.myapplication.anim.SlideExit.SlideLeftExit;
import com.wjs.myapplication.anim.SlideExit.SlideRightExit;
import com.wjs.myapplication.anim.SlideExit.SlideTopExit;
import com.wjs.myapplication.anim.ZoomEnter.ZoomInBottomEnter;
import com.wjs.myapplication.anim.ZoomEnter.ZoomInEnter;
import com.wjs.myapplication.anim.ZoomEnter.ZoomInLeftEnter;
import com.wjs.myapplication.anim.ZoomEnter.ZoomInRightEnter;
import com.wjs.myapplication.anim.ZoomEnter.ZoomInTopEnter;
import com.wjs.myapplication.anim.ZoomExit.ZoomInExit;
import com.wjs.myapplication.anim.ZoomExit.ZoomOutBottomExit;
import com.wjs.myapplication.anim.ZoomExit.ZoomOutExit;
import com.wjs.myapplication.anim.ZoomExit.ZoomOutLeftExit;
import com.wjs.myapplication.anim.ZoomExit.ZoomOutRightExit;
import com.wjs.myapplication.anim.ZoomExit.ZoomOutTopExit;
import com.wjs.myapplication.view.dialog.ActionSheetDialog;
import com.wjs.myapplication.view.dialog.OnOperItemClickL;

import java.util.ArrayList;

public class DiaogAnimChoose {
    public static void showAnim(final Context context) {
        final Class<?> cs[] = {BounceEnter.class,//
                BounceTopEnter.class,//
                BounceBottomEnter.class,//
                BounceLeftEnter.class,//
                BounceRightEnter.class,//
                FlipHorizontalEnter.class,//
                FlipHorizontalSwingEnter.class,//
                FlipVerticalEnter.class,//
                FlipVerticalSwingEnter.class,//
                FlipTopEnter.class,//
                FlipBottomEnter.class,//
                FlipLeftEnter.class,//
                FlipRightEnter.class,//
                FadeEnter.class, //
                FallEnter.class,//
                FallRotateEnter.class,//
                SlideTopEnter.class,//
                SlideBottomEnter.class,//
                SlideLeftEnter.class, //
                SlideRightEnter.class,//
                ZoomInEnter.class,//
                ZoomInTopEnter.class,//
                ZoomInBottomEnter.class,//
                ZoomInLeftEnter.class,//
                ZoomInRightEnter.class,//

                NewsPaperEnter.class,//
                Flash.class,//
                ShakeHorizontal.class,//
                ShakeVertical.class,//
                Jelly.class,//
                RubberBand.class,//
                Swing.class,//
                Tada.class,//
        };

        ArrayList<String> itemList = new ArrayList<>();
        for (Class<?> c : cs) {
            itemList.add(c.getSimpleName());
        }

        final String[] contents = new String[itemList.size()];
        final ActionSheetDialog dialog = new ActionSheetDialog(context, //
                itemList.toArray(contents), null);
        dialog.title("使用内置show动画设置对话框显示动画\r\n指定对话框将显示效果")//
                .titleTextSize_SP(14.5f)//
                .layoutAnimation(null)//
                .show();
        dialog.setCanceledOnTouchOutside(false);

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String animType = contents[position];
                    ((DialogHomeActivity) context).setBasIn((BaseAnimatorSet) cs[position].newInstance());
                    T.showShort(context, animType + "设置成功");
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void dissmissAnim(final Context context) {
        final Class<?> cs[] = {FlipHorizontalExit.class,//
                FlipVerticalExit.class,//
                FadeExit.class,//
                SlideTopExit.class,//
                SlideBottomExit.class,//
                SlideLeftExit.class, //
                SlideRightExit.class,//
                ZoomOutExit.class,//
                ZoomOutTopExit.class,//
                ZoomOutBottomExit.class,//
                ZoomOutLeftExit.class,//
                ZoomOutRightExit.class,//
                ZoomInExit.class,//
        };

        ArrayList<String> itemList = new ArrayList<String>();
        for (Class<?> c : cs) {
            itemList.add(c.getSimpleName());
        }

        final String[] contents = new String[itemList.size()];
        final ActionSheetDialog dialog = new ActionSheetDialog(context, //
                itemList.toArray(contents), null);
        dialog.title("使用内置dismiss动画设置对话框消失动画\r\n指定对话框将消失效果")//
                .titleTextSize_SP(14.5f)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String animType = contents[position];
                    ((DialogHomeActivity) context).setBasOut((BaseAnimatorSet) cs[position].newInstance());
                    T.showShort(context, animType + "设置成功");
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
