package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.wjs.myapplication.R;
import com.wjs.myapplication.view.UCPopupMenu;


import java.util.ArrayList;
import java.util.List;


public class UCPopupWindowActivity extends ActionBarActivity {
    private List<String> mTitles;
    private List<List<String>> mItem_names;
    private List<List<Integer>> mItem_images;
    private UCPopupMenu mUCPopupMenu;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ucpopup_window);

        mTitles=new ArrayList<String>();
        mTitles=addItems(new String[]{"常用","设置","工具"});
        mButton= (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置菜单栏显示位置
                mUCPopupMenu.showAtLocation(findViewById(R.id.layout),
                        Gravity.BOTTOM, 0, 0);
                mUCPopupMenu.isShowing();
            }
        });

        /**
         * 选项图标
         */
        mItem_images = new ArrayList<List<Integer>>();
        mItem_images.add(addItems(new Integer[]{R.drawable.ic_action_call,
                R.drawable.ic_action_camera, R.drawable.ic_action_copy,
                R.drawable.ic_action_crop, R.drawable.ic_action_cut,
                R.drawable.ic_action_discard, R.drawable.ic_action_download,
                R.drawable.ic_action_edit}));
        mItem_images.add(addItems(new Integer[] { R.drawable.ic_action_email,
                R.drawable.ic_action_full_screen, R.drawable.ic_action_help,
                R.drawable.ic_action_important, R.drawable.ic_action_map,
                R.drawable.ic_action_mic, R.drawable.ic_action_picture,
                R.drawable.ic_action_place }));
        mItem_images.add(addItems(new Integer[] { R.drawable.ic_action_refresh,
                R.drawable.ic_action_save, R.drawable.ic_action_search,
                R.drawable.ic_action_share, R.drawable.ic_action_switch_camera,
                R.drawable.ic_action_video, R.drawable.ic_action_web_site,
                R.drawable.ic_action_screen_rotation }));

        /**
         * 选项名称
         */
        mItem_names = new ArrayList<List<String>>();
        mItem_names.add(addItems(new String[] { "电话", "相机", "复制", "裁剪", "剪切",
                "删除", "下载", "编辑" }));
        mItem_names.add(addItems(new String[] { "邮件", "全屏", "帮助", "收藏", "地图",
                "语音", "图片", "定位" }));
        mItem_names.add(addItems(new String[]{"刷新", "保存", "搜索", "分享", "切换",
                "录像", "浏览器", "旋转屏幕"}));
        mUCPopupMenu = new UCPopupMenu(this, mTitles, mItem_names, mItem_images);
        mUCPopupMenu.setAnimationStyle(R.style.PopupAnimation);

    }
    private List<String> addItems( String[] values){
        List<String> list=new ArrayList<String>();
        for(String var:values){
            list.add(var);
        }
        return  list;
    }

    /**
     * 转换为List<Integer>
     * 用于菜单栏中的标题赋值
     * @param values
     * @return
     */
    private List<Integer> addItems( Integer[] values) {

        List<Integer> list = new ArrayList<Integer>();
        for (Integer var : values) {
            list.add(var);
        }

        return list;
    }

}
