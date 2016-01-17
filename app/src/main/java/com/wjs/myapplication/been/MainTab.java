package com.wjs.myapplication.been;

import com.wjs.myapplication.R;
import com.wjs.myapplication.fragment.FragmentPage1;
import com.wjs.myapplication.fragment.FragmentPage2;
import com.wjs.myapplication.fragment.FragmentPage3;
import com.wjs.myapplication.fragment.FragmentPage4;
import com.wjs.myapplication.fragment.FragmentPage5;

/**
 * Created by Administrator on 2015/7/8.
 */
public enum MainTab {
    NEWS(0, R.string.tab_home, R.drawable.ic_action_call,
            FragmentPage1.class),

    TWEET(1, R.string.tab_message, R.drawable.ic_action_camera,
            FragmentPage2.class),

    QUICK(2, R.string.tab_friend, R.drawable.ic_action_copy,
            FragmentPage3.class),

    EXPLORE(3, R.string.tab_square, R.drawable.ic_action_crop,
            FragmentPage4.class),

    ME(4, R.string.tab_more, R.drawable.ic_action_cut,
            FragmentPage5.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
