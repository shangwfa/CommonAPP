package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;
import com.wjs.myapplication.R;

public class UmengUpdateActivity extends ActionBarActivity {
    private Button mBtUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umeng_update);



        mBtUpdate= (Button) findViewById(R.id.mBtUpdate);
        mBtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manualUpdate();
            }
        });
        // autoAppUpdate();

    }

    /**
     * 功能：自动安装
     */
    private void autoAppUpdate() {
        //自动更新
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(this);
    }

    /**
     * 功能：App手动更新
     */
    private void manualUpdate(){
        UmengUpdateAgent.forceUpdate(this);
    }
    /**
     * 功能：静默安装
     */
    public void silentUpdate(){
        UmengUpdateAgent.silentUpdate(this);
    }

    /**
     * 功能：恢复默认设置
     */
    public void setDefault(){
        UmengUpdateAgent.setDefault();//恢复默认设置
    }


    /**
     *
     */
    public void updateListener(){
        UmengUpdateAgent.setUpdateAutoPopup(false);
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus,UpdateResponse updateInfo) {
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent.showUpdateDialog(UmengUpdateActivity.this, updateInfo);
                        break;
                    case UpdateStatus.No: // has no update
                        Toast.makeText(UmengUpdateActivity.this, "没有更新", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        Toast.makeText(UmengUpdateActivity.this, "没有wifi连接， 只在wifi下更新", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.Timeout: // time out
                        Toast.makeText(UmengUpdateActivity.this, "超时", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        UmengUpdateAgent.update(this);
    }
}
