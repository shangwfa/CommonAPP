package com.wjs.myapplication.Utils.Tools;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Lenovo on 2015/12/2.
 */
public class CustomCrash implements Thread.UncaughtExceptionHandler {
    private static final  String TAG="CustomCrash";
    private static final  int TYPE_SAVE_SDCARD=1;//崩溃日志保存本地--建议开发模式使用
    private static final  int TYPE_SAVE_REMOTE=2;//崩溃日志保存远程服务器--建议生产模式使用

    private int type_save=2;//崩溃日志模式 默认保存服务器
    private static final  String CRASH_SAVE_SDPATH="sdcard/fda_cache";
    private static final  String CRASH_LOG_DELIVER="xxxxxxxxxxxxxxxxxxxxxxxx";
    private static  CustomCrash instance=new CustomCrash();
    private Context mContext;
    private  CustomCrash(){}

    /**
     *
     * @return
     */
    public static  CustomCrash getInstance(){
        return instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(type_save==TYPE_SAVE_SDCARD){
            //保存信息到sdcard中
            saveToSdcard(mContext,ex);
        }else if(type_save==TYPE_SAVE_REMOTE){
            //异常崩溃信息保存到服务器
            saveToServer(mContext,ex);
        }
        //应用退出
        T.showShort(mContext,"和抱歉，程序发生异常，即将退出.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    //设置自定义异常类
    public void setCustomCrashInfo(Context mContext){
        this.mContext=mContext;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    private void saveToServer(Context mContext, Throwable ex) {
        String fileName=null;
        StringBuilder mBuilder=new StringBuilder();
        mBuilder.append(getExceptinoInfo(ex));
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File file=new File(CRASH_SAVE_SDPATH);
            if(!file.exists()){
                file.mkdir();
            }
            fileName=file.toString()+File.separator+".log";
            File neFile=new File(fileName);
            FileOutputStream fos;
            try {
                fos=new FileOutputStream(neFile);
                fos.write(mBuilder.toString().getBytes());
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void saveToSdcard(Context mContext, Throwable ex) {
        final String carsh_log=getExceptinoInfo(ex);
        //提交服务器
    }
    private String getExceptinoInfo(Throwable ex) {
        StringWriter sw=new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        StringBuffer mBuffer=new StringBuffer();
        mBuffer.append("--------------------Crash Log Begin-----------------\n");
        mBuffer.append("SystemVersion:"+android.os.Build.VERSION.RELEASE+"\n");
        mBuffer.append(sw.toString()+"\n");
        mBuffer.append("--------------------Crash Log End--------------------\n");
        return  mBuffer.toString();
    }
}
