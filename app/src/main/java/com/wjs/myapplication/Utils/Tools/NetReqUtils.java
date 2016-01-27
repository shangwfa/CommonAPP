package com.wjs.myapplication.Utils.Tools;

import android.os.Handler;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/1/27.
 * 功能：取请求队列中最后一个请求
 */
public class NetReqUtils {
    //请求队列
    private List<Object> reqList;
    //请求间隔,单位毫秒
    private static final int intervaltime = 500;
    private Handler mHandler;
    private ReqThread curThread;
    private List<Thread> threadList;
    private CallBack mCallBack;

    public NetReqUtils() {
        reqList = new ArrayList<>();
        mHandler = new Handler();
        curThread = new ReqThread();
        threadList = new ArrayList<>();
    }

    /**
     * 功能：添加请求
     *
     * @param req
     */
    public void addReq(Object req) {
        reqList.add(req);
        if (threadList.contains(curThread) && !curThread.getIsRunned()) {
            mHandler.removeCallbacks(curThread);
            threadList.remove(curThread);
        }
        mHandler.postDelayed(curThread, intervaltime);
        threadList.add(curThread);

        KLog.d("请求个数" + reqList.size());
    }


    /**
     * 功能：清除所有请求
     */
    public void clearReqs() {
        reqList.clear();
        KLog.d("请求清除后" + reqList.size());
    }

    class ReqThread extends Thread {
        private boolean isRunned = false;

        /**
         * 功能：判断当前线程是否执行过
         *
         * @return
         */
        public boolean getIsRunned() {
            return isRunned;
        }

        @Override
        public void run() {
            if(reqList.size()>0){
                mCallBack.executeLastReq(reqList.get(reqList.size() - 1));
                isRunned = true;
                clearReqs();
            }
        }
    }

    /**
     * 回调接口
     */
    public interface CallBack {
        public void executeLastReq(Object req);
    }

    /**
     * 功能：设置回调
     *
     * @param mCallBack
     */
    public void setCallBack(CallBack mCallBack) {
        this.mCallBack = mCallBack;
    }
}
