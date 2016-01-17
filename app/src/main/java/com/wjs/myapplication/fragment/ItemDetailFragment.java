package com.wjs.myapplication.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wjs.myapplication.been.Item;
import com.wjs.myapplication.R;

import de.greenrobot.event.EventBus;


public class ItemDetailFragment extends Fragment {
    private TextView tvDetail;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Unregister
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_item_detail,
                container, false);
        tvDetail = (TextView) rootView.findViewById(R.id.item_detail);
        return rootView;
    }
    /** List点击时会发送些事件，接收到事件后更新详情 */
    public void onEventMainThread(Item item)
    {
        if (item != null)
            tvDetail.setText(item.content);
    }
}
