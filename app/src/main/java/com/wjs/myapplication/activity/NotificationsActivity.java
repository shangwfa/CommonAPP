package com.wjs.myapplication.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

import com.wjs.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationsActivity extends AppCompatActivity {
    /** Notification管理 */
    public NotificationManager mNotificationManager;
    /** Notification构造器 */
    NotificationCompat.Builder mBuilder;
    /** Notification的ID */
    int notifyId = 100;
    @OnClick(R.id.btn_show)
    public void showNotifications(){
        showNotify();
    }
    @OnClick(R.id.btn_bigstyle_show)
    public void showbigsytle(){
        showBigStyleNotify();
    }
    @OnClick(R.id.btn_clear)
    public void clear(){
        clearNotify(notifyId);
    }
    @OnClick(R.id.btn_clear_all)
    public void clearall(){
        clearall();
    }
    @OnClick(R.id.btn_show_intent_act)
    public void showIntent(){
        showIntentActivityNotify();
    }
    @OnClick(R.id.btn_show_progress)
    public void showProgress(){
        startActivity(new Intent(getApplicationContext(), ProgressAcitivty.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        initNotify();
    }
    /** 初始化通知栏 */
    private void initNotify(){
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("测试标题")
                .setContentText("测试内容")
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
//				.setNumber(number)//显示数量
                .setTicker("测试通知来啦")//通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示
                .setPriority(Notification.PRIORITY_DEFAULT)//设置该通知优先级
//				.setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                        //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.drawable.app_logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.app_logo))
        ;
    }
    /** 显示通知栏 */
    public void showNotify(){
        mBuilder.setContentTitle("测试标题")
                .setContentText("测试内容")
//				.setNumber(number)//显示数量
                .setTicker("测试通知来啦");//通知首次出现在通知栏，带上升动画效果的
        mNotificationManager.notify(notifyId, mBuilder.build());
//		mNotification.notify(getResources().getString(R.string.app_name), notiId, mBuilder.build());
    }
    /** 显示大视图风格通知栏 */
    public void showBigStyleNotify() {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[5];
        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("大视图内容:");
        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        mBuilder.setContentTitle("测试标题xx")
                .setContentText("测试内容xx")
//				.setNumber(number)//显示数量
                .setStyle(inboxStyle)//设置风格
                .setTicker("测试通知来啦xx");// 通知首次出现在通知栏，带上升动画效果的
        mNotificationManager.notify(notifyId, mBuilder.build());
        // mNotification.notify(getResources().getString(R.string.app_name),
        // notiId, mBuilder.build());
    }
    /** 显示通知栏点击跳转到指定Activity */
    public void showIntentActivityNotify(){
        // Notification.FLAG_ONGOING_EVENT --设置常驻 Flag;Notification.FLAG_AUTO_CANCEL 通知栏上点击此通知后自动清除此通知
//		notification.flags = Notification.FLAG_AUTO_CANCEL; //在通知栏上点击此通知后自动清除此通知
        mBuilder.setAutoCancel(true)//点击后让通知将消失
                .setContentTitle("测试标题")
                .setContentText("点击跳转")
                .setTicker("点我");
        //点击的意图ACTION是跳转到Intent
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        mNotificationManager.notify(notifyId, mBuilder.build());
    }
    /**
     * @获取默认的pendingIntent,为了防止2.3及以下版本报错
     * @flags属性:
     * 在顶部常驻:Notification.FLAG_ONGOING_EVENT
     * 点击去除： Notification.FLAG_AUTO_CANCEL
     */
    public PendingIntent getDefalutIntent(int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), flags);
        return pendingIntent;
    }
    /**
     * 清除当前创建的通知栏
     */
    public void clearNotify(int notifyId){
        mNotificationManager.cancel(notifyId);//删除一个特定的通知ID对应的通知
//		mNotification.cancel(getResources().getString(R.string.app_name));
    }

    /**
     * 清除所有通知栏
     * */
    public void clearAllNotify() {
        mNotificationManager.cancelAll();// 删除你发的所有通知
    }
}
