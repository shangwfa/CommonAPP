package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wjs.myapplication.R;

public class DrawerLayoutActivity extends Activity {
    private  ListView mLv;
    private String[] str;
    private DrawerLayout mDrawerLayout;
    private boolean isOpen;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        bt= (Button) findViewById(R.id.id_btn2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    mDrawerLayout.closeDrawers();
                }else{
                    mDrawerLayout.openDrawer(GravityCompat.START);

                }
            }
        });
        mLv = (ListView) findViewById(R.id.id_lv);
        str = new String[] { "item1", "item2", "item3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, str);
        mLv.setAdapter(adapter);


       // mLv.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.i("drawer", slideOffset + "");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.i("drawer", "抽屉被完全打开了！");
                isOpen=true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i("drawer", "抽屉被完全关闭了！");
                isOpen=false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.i("drawer", "drawer的状态：" + newState);
            }
        });
    }

}
