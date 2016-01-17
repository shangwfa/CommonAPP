package com.wjs.myapplication.web;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Lenovo on 2015/12/7.
 */
public class MyWebViewClient extends WebViewClient {
    /**
     * 加载过程中 拦截加载的地址url ,可以重新加载
     *
     * @param view
     * @param url  被拦截的url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    /**
     * 页面加载过程中，加载资源回调的方法
     *
     * @param view
     * @param url
     */
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    /**
     * 页面加载完成回调的方法
     *
     * @param view
     * @param url
     */
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

// 加载完成隐藏进度界面,显示WebView内容

// 关闭图片加载阻塞
        view.getSettings().setBlockNetworkImage(false);
    }
    /**
     * 页面开始加载调用的方法
     * @param view
     * @param url
     * @param favicon
     */
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view,url, favicon);
    }


}