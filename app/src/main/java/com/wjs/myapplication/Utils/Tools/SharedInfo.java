package com.wjs.myapplication.Utils.Tools;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.wjs.myapplication.BaseApplication;

/**
 * Created by Administrator on 2015/11/13.
 */
public class SharedInfo {
    private final static String SHARED_PREFERENCES_TAG = "LOCAL_DB";
    private SharedPreferences sharedPreferences;
    //单例
    private static SharedInfo sSharedInfo;
    public static  SharedInfo getInstance(){
        if(sSharedInfo==null){
            sSharedInfo=new SharedInfo();
        }
        return  sSharedInfo;
    }
    private SharedInfo(){
        sharedPreferences= BaseApplication.getPreferences();
    }
//------------------------------------------------------------------------------------------------//
    /**
     * 保存对象到本地
     * @param obj
     */
    public void put(final Object obj) {
        if (obj != null) {
            final String innerKey = getKey(obj.getClass());
            if (innerKey != null) {
                basicPutObject(innerKey, obj);
            }
        }
    }
    private synchronized void basicPutObject(final String key, final Object obj) {
        final String value = obj2string(obj);
        basicPutString(key, value);
    }
    private synchronized void basicPutString(final String key, final String value) {
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }
    /***
     * Object 到 String 的序列和反序列化
     */
    private String obj2string(final Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 类的名字
     */
    private String getKey(final Class<?> clazz) {
        if (clazz != null) {
            return clazz.getSimpleName();
        }
        return null;
    }
//------------------------------------------------------------------------------------------------//
    /**
     *功能：删除保存在本地的对象数据
     * @param clazz
     */
    public void remove(final Class<?> clazz) {
        final String innerKey = getKey(clazz);
        if (innerKey != null) {
            basicPutObject(innerKey, null);
        }
    }
//------------------------------------------------------------------------------------------------//
    /***
     * 功能：
     */
    public <T> T get(final Class<T> clazz, final T tDefault) {
        final String innerKey = getKey(clazz);
        if (innerKey != null) {
            final T ret = basicGetObject(innerKey, clazz);
            if (ret != null) {
                return ret;
            }
        }
        return tDefault;
    }
    private synchronized <T> T basicGetObject(final String key, Class<T> clazz) {
        final String value = basicGetString(key);
        return string2obj(value, clazz);
    }
    private synchronized String basicGetString(final String key) {
        return sharedPreferences.getString(key, null);

    }private <T> T string2obj(final String string, final Class<T> clazz) {
        try {
            return new Gson().fromJson(string, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
