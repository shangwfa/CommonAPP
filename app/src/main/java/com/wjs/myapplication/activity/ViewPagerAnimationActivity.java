package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.wjs.myapplication.R;
import com.wjs.myapplication.Utils.Tools.viewpagertransformer.ZoomOutTranformer;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAnimationActivity extends Activity {
    private ViewPager mViewPager;
    private int[] mImgIds = new int[] { R.drawable.abc,
            R.drawable.a, R.drawable.abc };
    private List<ImageView> mImageViews = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_pager_animation);

        initData();

        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        mViewPager.setPageTransformer(false, new ZoomOutTranformer());
        mViewPager.setAdapter(new PagerAdapter() {
                                  private View mCurrentView;
                                  @Override
                                  public Object instantiateItem(ViewGroup container, int position) {

                                      container.addView(mImageViews.get(position));
                                      return mImageViews.get(position);
                                  }

                                  @Override
                                  public void destroyItem(ViewGroup container, int position,
                                                          Object object) {

                                      container.removeView(mImageViews.get(position));
                                  }

                                  @Override
                                  public boolean isViewFromObject(View view, Object object) {
                                      return view == object;
                                  }

                                  @Override
                                  public int getCount() {
                                      return mImgIds.length;
                                  }

                                  @Override
                                  public void setPrimaryItem(ViewGroup container, int position, Object object) {
                                      super.setPrimaryItem(container, position, object);
                                      mCurrentView= (View) object;
                                  }
                              }

        );
    }

    private void initData()
    {
        for (int imgId : mImgIds)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(imgId);
            mImageViews.add(imageView);
        }
    }

}
