package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wjs.myapplication.been.Cheeses;
import com.wjs.myapplication.R;


import java.util.List;

public class SwipeRefreshLayoutActivity extends Activity {
    private static final String LOG_TAG = SwipeRefreshLayoutActivity.class.getSimpleName();

    private static final int LIST_ITEM_COUNT = 20;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.swipe_color_1, R.color.swipe_color_2, R.color.swipe_color_3, R.color.swipe_color_4);
//        mSwipeRefreshLayout.setColorScheme(
//                R.color.swipe_color_1, R.color.swipe_color_2,
//                R.color.swipe_color_3, R.color.swipe_color_4);

        mListView = (ListView) findViewById(R.id.list);

        mListAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Cheeses.randomList(LIST_ITEM_COUNT));

        mListView.setAdapter(mListAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");

                initiateRefresh();
            }
        });

    }

    private void initiateRefresh() {
        Log.i(LOG_TAG, "initiateRefresh");

        /**
         * Execute the background task, which uses {@link android.os.AsyncTask} to load the data.
         */
        new DummyBackgroundTask().execute();
    }


    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<String>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds


        @Override
        protected List<String> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random list of cheeses
            return Cheeses.randomList(LIST_ITEM_COUNT);
        }

        @Override
        protected void onPostExecute( List<String> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }

    }

    private void onRefreshComplete( List<String> result) {
        Log.i(LOG_TAG, "onRefreshComplete");

        // Remove all items from the ListAdapter, and then replace them with the new items
        mListAdapter.clear();
        for (String cheese : result) {
            mListAdapter.add(cheese);
        }

        // Stop the refreshing indicator
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
