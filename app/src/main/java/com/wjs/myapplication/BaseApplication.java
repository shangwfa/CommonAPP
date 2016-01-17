package com.wjs.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.wjs.myapplication.Utils.Tools.StringUtils;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;


@SuppressLint("InflateParams")
public class BaseApplication extends Application {


    private static String LAST_REFRESH_TIME = "last_refresh_time.pref";
    static Context _context;
    static Resources _resource;

    private static String lastToast = "";
    private static long lastToastTime;

    private static boolean sIsAtLeastGB;

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            sIsAtLeastGB = true;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        _resource = _context.getResources();
        CustomActivityOnCrash.install(this);
    }


    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    public static Resources resources() {
        return _resource;
    }

    /**
     * 放入已读文章列表中
     *
     */
    public static void putReadedPostList(String prefFileName, String key,
                                         String value) {
        SharedPreferences preferences = getPreferences(prefFileName);
        int size = preferences.getAll().size();
        Editor editor = preferences.edit();
        if (size >= 100) {
            editor.clear();
        }
        editor.putString(key, value);
        apply(editor);
    }

    /**
     * 读取是否是已读的文章列表
     *
     * @return
     */
    public static boolean isOnReadedPostList(String prefFileName, String key) {
        return getPreferences(prefFileName).contains(key);
    }

    /**
     * 记录列表上次刷新时间
     *
     * @param key
     * @param value
     * @return void
     * @author 火蚁
     * 2015-2-9 下午2:21:37
     */
    public static void putToLastRefreshTime(String key, String value) {
        SharedPreferences preferences = getPreferences(LAST_REFRESH_TIME);
        Editor editor = preferences.edit();
        editor.putString(key, value);
        apply(editor);
    }

    /**
     * 获取列表的上次刷新时间
     *
     * @param key
     * @return
     * @author 火蚁
     * 2015-2-9 下午2:22:04
     */
    @Nullable
    public static String getLastRefreshTime(String key) {
        return getPreferences(LAST_REFRESH_TIME).getString(key, StringUtils.getCurTimeStr());
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void apply( Editor editor) {
        if (sIsAtLeastGB) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void set(String key, int value) {
        Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static void set(String key, boolean value) {
        Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static void set(String key, String value) {
        Editor editor = getPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    @Nullable
    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences() {
        String PREF_NAME = "creativelocker.pref";
        SharedPreferences pre = context().getSharedPreferences(PREF_NAME,
                Context.MODE_MULTI_PROCESS);
        return pre;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences(String prefName) {
        return context().getSharedPreferences(prefName,
                Context.MODE_MULTI_PROCESS);
    }


    public static int[] getDisplaySize() {
        return new int[]{getPreferences().getInt("screen_width", 480),
                getPreferences().getInt("screen_height", 854)};
    }

    public static void saveDisplaySize( Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay()
                .getMetrics(displaymetrics);
        Editor editor = getPreferences().edit();
        editor.putInt("screen_width", displaymetrics.widthPixels);
        editor.putInt("screen_height", displaymetrics.heightPixels);
        editor.putFloat("density", displaymetrics.density);
        editor.commit();
    }

    public static String string(int id) {
        return _resource.getString(id);
    }

    public static String string(int id, Object... args) {
        return _resource.getString(id, args);
    }
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .memoryCacheExtraOptions(480, 800)
                        // default = device screen dimensions
                .threadPoolSize(3)
                        // default
                .threadPriority(Thread.NORM_PRIORITY - 1)
                        // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                        // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024) // 缓冲大小
                .discCacheFileCount(100) // 缓冲文件数目
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(this)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();

        // 2.单例ImageLoader类的初始化
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }
}
