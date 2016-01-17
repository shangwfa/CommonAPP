package com.wjs.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Stack;

/**
 * activity堆栈式管理
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年10月30日 下午6:22:05
 * 
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;
    /**
     * productMap : {"priorityCode":"","investDateTime":null,"specialPriorityCode":"","maxFloatExpectIncome":0,"payTimeOut":20,"investInterest":"0.00","minFloatExpectIncome":0,"investAmount":1,"productName":"跳秒测试cesena测试似的发射点垃圾圣诞节立即肌肤啦","dueDate":1445961600000}
     * alipayMap : {"scene_code":"FINANCING","sign_type":"MD5","notify_url":"http://m-test.wjs.com//alipay/alipayAppFreezeNotify/1","out_order_no":"201510231609092364685386001677","payee_user_id":"2088911356833962","payer_user_id":"2088702986146485","return_url":"http://m-test.wjs.com//alipay/alipayAppFreezeReturn/1","order_title":"产品购买","sign":"4119d6bc76243e9befea63c4233f7eae","amount":"1.0","_input_charset":"utf-8","payee_logon_id":"santansettletest@wjs.com","product_code":"FUND_PRE_AUTH","pay_mode":"WIRELESS","service":"alipay.fund.auth.create.freeze.apply","out_request_no":"201510231609092364685386001675","partner":"2088911356833962","pay_timeout":"20"}
     */
    private ProductMapEntity productMap;
    private AlipayMapEntity alipayMap;

    private AppManager() {}

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(@Nullable Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
                break;
            }
        }
        activityStack.clear();
    }

    /**
     * 获取指定的Activity
     * 
     * @author kymjs
     */
    public static Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public void setProductMap(ProductMapEntity productMap) {
        this.productMap = productMap;
    }

    public void setAlipayMap(AlipayMapEntity alipayMap) {
        this.alipayMap = alipayMap;
    }

    public ProductMapEntity getProductMap() {
        return productMap;
    }

    public AlipayMapEntity getAlipayMap() {
        return alipayMap;
    }

    /***
     * 双击退出
     * @author FireAnt（http://my.oschina.net/LittleDY）
     * @created 2015年1月5日 下午7:07:44
     *
     */
    private class DoubleClickExitHelper {

        private final Activity mActivity;

        private boolean isOnKeyBacking;
        private Handler mHandler;
        private Toast mBackToast;

        public DoubleClickExitHelper(Activity activity) {
            mActivity = activity;
            mHandler = new Handler(Looper.getMainLooper());
        }

        /**
         * Activity onKeyDown事件
         * */
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if(keyCode != KeyEvent.KEYCODE_BACK) {
                return false;
            }
            if(isOnKeyBacking) {
                mHandler.removeCallbacks(onBackTimeRunnable);
                if(mBackToast != null){
                    mBackToast.cancel();
                }
                // 退出
                AppManager.getAppManager().AppExit(mActivity);
                return true;
            } else {
                isOnKeyBacking = true;
                if(mBackToast == null) {
                    mBackToast = Toast.makeText(mActivity, R.string.tip_double_click_exit, Toast.LENGTH_LONG);
                }
                mBackToast.show();
                mHandler.postDelayed(onBackTimeRunnable, 2000);
                return true;
            }
        }

        private Runnable onBackTimeRunnable = new Runnable() {

            @Override
            public void run() {
                isOnKeyBacking = false;
                if(mBackToast != null){
                    mBackToast.cancel();
                }
            }
        };
    }

    public class ProductMapEntity {
        /**
         * priorityCode :
         * investDateTime : null
         * specialPriorityCode :
         * maxFloatExpectIncome : 0
         * payTimeOut : 20
         * investInterest : 0.00
         * minFloatExpectIncome : 0
         * investAmount : 1
         * productName : 跳秒测试cesena测试似的发射点垃圾圣诞节立即肌肤啦
         * dueDate : 1445961600000
         */
        private String priorityCode;
        private String investDateTime;
        private String specialPriorityCode;
        private int maxFloatExpectIncome;
        private int payTimeOut;
        private String investInterest;
        private int minFloatExpectIncome;
        private int investAmount;
        private String productName;
        private String dueDate;

        public void setPriorityCode(String priorityCode) {
            this.priorityCode = priorityCode;
        }

        public void setInvestDateTime(String investDateTime) {
            this.investDateTime = investDateTime;
        }

        public void setSpecialPriorityCode(String specialPriorityCode) {
            this.specialPriorityCode = specialPriorityCode;
        }

        public void setMaxFloatExpectIncome(int maxFloatExpectIncome) {
            this.maxFloatExpectIncome = maxFloatExpectIncome;
        }

        public void setPayTimeOut(int payTimeOut) {
            this.payTimeOut = payTimeOut;
        }

        public void setInvestInterest(String investInterest) {
            this.investInterest = investInterest;
        }

        public void setMinFloatExpectIncome(int minFloatExpectIncome) {
            this.minFloatExpectIncome = minFloatExpectIncome;
        }

        public void setInvestAmount(int investAmount) {
            this.investAmount = investAmount;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public String getPriorityCode() {
            return priorityCode;
        }

        public String getInvestDateTime() {
            return investDateTime;
        }

        public String getSpecialPriorityCode() {
            return specialPriorityCode;
        }

        public int getMaxFloatExpectIncome() {
            return maxFloatExpectIncome;
        }

        public int getPayTimeOut() {
            return payTimeOut;
        }

        public String getInvestInterest() {
            return investInterest;
        }

        public int getMinFloatExpectIncome() {
            return minFloatExpectIncome;
        }

        public int getInvestAmount() {
            return investAmount;
        }

        public String getProductName() {
            return productName;
        }

        public String getDueDate() {
            return dueDate;
        }
    }

    public class AlipayMapEntity {
        /**
         * scene_code : FINANCING
         * sign_type : MD5
         * notify_url : http://m-test.wjs.com//alipay/alipayAppFreezeNotify/1
         * out_order_no : 201510231609092364685386001677
         * payee_user_id : 2088911356833962
         * payer_user_id : 2088702986146485
         * return_url : http://m-test.wjs.com//alipay/alipayAppFreezeReturn/1
         * order_title : 产品购买
         * sign : 4119d6bc76243e9befea63c4233f7eae
         * amount : 1.0
         * _input_charset : utf-8
         * payee_logon_id : santansettletest@wjs.com
         * product_code : FUND_PRE_AUTH
         * pay_mode : WIRELESS
         * service : alipay.fund.auth.create.freeze.apply
         * out_request_no : 201510231609092364685386001675
         * partner : 2088911356833962
         * pay_timeout : 20
         */
        private String scene_code;
        private String sign_type;
        private String notify_url;
        private String out_order_no;
        private String payee_user_id;
        private String payer_user_id;
        private String return_url;
        private String order_title;
        private String sign;
        private String amount;
        private String _input_charset;
        private String payee_logon_id;
        private String product_code;
        private String pay_mode;
        private String service;
        private String out_request_no;
        private String partner;
        private String pay_timeout;

        public void setScene_code(String scene_code) {
            this.scene_code = scene_code;
        }

        public void setSign_type(String sign_type) {
            this.sign_type = sign_type;
        }

        public void setNotify_url(String notify_url) {
            this.notify_url = notify_url;
        }

        public void setOut_order_no(String out_order_no) {
            this.out_order_no = out_order_no;
        }

        public void setPayee_user_id(String payee_user_id) {
            this.payee_user_id = payee_user_id;
        }

        public void setPayer_user_id(String payer_user_id) {
            this.payer_user_id = payer_user_id;
        }

        public void setReturn_url(String return_url) {
            this.return_url = return_url;
        }

        public void setOrder_title(String order_title) {
            this.order_title = order_title;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void set_input_charset(String _input_charset) {
            this._input_charset = _input_charset;
        }

        public void setPayee_logon_id(String payee_logon_id) {
            this.payee_logon_id = payee_logon_id;
        }

        public void setProduct_code(String product_code) {
            this.product_code = product_code;
        }

        public void setPay_mode(String pay_mode) {
            this.pay_mode = pay_mode;
        }

        public void setService(String service) {
            this.service = service;
        }

        public void setOut_request_no(String out_request_no) {
            this.out_request_no = out_request_no;
        }

        public void setPartner(String partner) {
            this.partner = partner;
        }

        public void setPay_timeout(String pay_timeout) {
            this.pay_timeout = pay_timeout;
        }

        public String getScene_code() {
            return scene_code;
        }

        public String getSign_type() {
            return sign_type;
        }

        public String getNotify_url() {
            return notify_url;
        }

        public String getOut_order_no() {
            return out_order_no;
        }

        public String getPayee_user_id() {
            return payee_user_id;
        }

        public String getPayer_user_id() {
            return payer_user_id;
        }

        public String getReturn_url() {
            return return_url;
        }

        public String getOrder_title() {
            return order_title;
        }

        public String getSign() {
            return sign;
        }

        public String getAmount() {
            return amount;
        }

        public String get_input_charset() {
            return _input_charset;
        }

        public String getPayee_logon_id() {
            return payee_logon_id;
        }

        public String getProduct_code() {
            return product_code;
        }

        public String getPay_mode() {
            return pay_mode;
        }

        public String getService() {
            return service;
        }

        public String getOut_request_no() {
            return out_request_no;
        }

        public String getPartner() {
            return partner;
        }

        public String getPay_timeout() {
            return pay_timeout;
        }
    }
}