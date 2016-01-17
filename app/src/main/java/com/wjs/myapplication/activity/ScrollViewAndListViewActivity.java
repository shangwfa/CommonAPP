package com.wjs.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wjs.myapplication.R;
import com.wjs.myapplication.adapter.ScrollViewAndListViewAdapter;



import java.util.ArrayList;
import java.util.List;


public class ScrollViewAndListViewActivity extends ActionBarActivity {
    private ListView mListView;
    private ListView mListViewOne;
    private ScrollViewAndListViewAdapter mAdapter;
    private ScrollViewAndListViewAdapter mAdapterOne;

    private List<String> mList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_and_list_view);
        mListView= (ListView) findViewById(R.id.list);
        mListViewOne= (ListView) findViewById(R.id.list_one);
        for(int i=0;i<40;i++){
            mList.add("文本.......+"+i);
        }
        mAdapter=new ScrollViewAndListViewAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        setListViewHeightBasedOnChildren(mListView);

        mAdapterOne=new ScrollViewAndListViewAdapter(this,mList);
        mListViewOne.setAdapter(mAdapterOne);
        setListViewHeightBasedOnChildren(mListViewOne);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "mList+" + position, Toast.LENGTH_LONG).show();
            }
        });

        mListViewOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "mListOne+" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void setListViewHeightBasedOnChildren( ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
