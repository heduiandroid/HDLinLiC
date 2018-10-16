package com.linli.consumer.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.utils.DemoContext;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by Administrator on 2016/1/12.
 */
public class AppContext extends Application {
    private SharedPreferences preferences;
    private User user;
    private static City city;       //这里city全局唯一
    private int screenWidth;
    private int screenHeight;

    private static AppContext mInstance;

    /**
     * 获取AppContext的单例
     * */
    public static synchronized AppContext getInstance(){
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5b8cea11");//为公司申请的AppKey
        SDKInitializer.initialize(getApplicationContext());
        JPushInterface.setDebugMode(false);//Should close debug mode when release.
        JPushInterface.init(this);
        Fresco.initialize(this);
        Stetho.initializeWithDefaults(this);
//        FIR.init(this);//bugHD不给用了 换个别的
        DBUtil dbUtil = DBUtil.getInstance(this);   //初始化数据库
        dbUtil.synchronizeGoods();      //这里同步本地购物车 数据库
        dbUtil.synchronizeFood();

        /**
         *
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
            RongIM.setOnReceiveMessageListener(new MyReceiveMessageListener());
            if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

                DemoContext.init(this);
            }
        }
    }
    private class MyReceiveMessageListener implements RongIMClient.OnReceiveMessageListener{

        @Override
        public boolean onReceived(io.rong.imlib.model.Message message, int i) {
            //弹通知
            return false;
        }
    }
    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }





    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null){
            preferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
            Editor editor=preferences.edit();
            editor.putString("phone", user.getPhone());
            editor.putString("password", user.getPassword());
            editor.commit();
        }
    }

    public static synchronized City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
