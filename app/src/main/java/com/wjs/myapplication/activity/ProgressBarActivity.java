package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.wjs.myapplication.R;
import com.wjs.myapplication.view.HorizontalProgressBarWithNumber;

public class ProgressBarActivity extends Activity {
    private HorizontalProgressBarWithNumber mProgressBar;
    private static final int MSG_PROGRESS_UPDATE=0x110;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int progress=mProgressBar.getProgress();
            mProgressBar.setProgress(++progress);
            if(progress>=100){
                mHandler.removeMessages(MSG_PROGRESS_UPDATE);
            }
            mHandler.sendEmptyMessageAtTime(MSG_PROGRESS_UPDATE,100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        mProgressBar = (HorizontalProgressBarWithNumber) findViewById(R.id.id_progressbar01);
        mHandler.sendEmptyMessage(MSG_PROGRESS_UPDATE);
    }


}
