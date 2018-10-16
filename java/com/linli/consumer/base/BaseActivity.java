package com.linli.consumer.base;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.linli.consumer.app.AppContext;
import com.linli.consumer.domain.User;
import com.linli.consumer.widget.HDDialog;
import com.linli.consumer.widget.ProDialog;


/**
 * Created by tomoyo on 2016/11/5.
 * Activity的基类，包括:
 * 1,沉浸式状态栏
 * 2,堆栈式管理Activity
 * 3,自定义Dialog
 * 4,注册组件 findViewById
 * 5,注册点击事件 setOnClickListener
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {



    /* 两次点击的间隔时间*/
    private static final int MIN_CLICK_TIME = 500;
    private static long lastClickTime = 0;
    /* 两次刷新的间隔时间*/
    private static final int MIN_REFRESH_TIME = 1000;
    private static long lastRequestTime = 0;
    private static boolean readyToRequest;


    /**
     * 上下文环境
     * */
    public Context mContext;
    private HDDialog dialog;
    public ProDialog proDialog;
//    private User user = AppContext.getInstance().getUser();

    public AppContext appContext;
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setTheme(R.style.ColorTranslucentTheme);
        }*/
        setContentView(getLayoutId());
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        appContext = (AppContext) this.getApplicationContext();
        user = appContext.getUser();//实体
        ActivityCollector.addActivity(this);
        //AppBus.getInstance().register(this);
        readyToRequest = true;
        dialog = new HDDialog(this);
        proDialog = new ProDialog(this);
        dialog.show();
        initView();
        initData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },5000);

    }
    /**
     * 获取布局id
     * */
    protected abstract int getLayoutId();



    /**
     * 初始化视图
     * */
    protected abstract void initView();

    /**
     * 初始化数据
     * */
    protected abstract void initData();

    /**
     * 填充数据
     * */
    protected void fillData() {

    }


    /**
     * 自定义点击事件，实现父类点击事件
     * 防止快速点击{@linkplain #isDoubleClick}
     * */
    public abstract void onHDClick(View v);


    /**
     * 点击事件
     * */
    @Override
    public void onClick(View v) {
        if(!isDoubleClick()){
            onHDClick(v);
        }
    }




    /**
     * 重写findView方法
     * 含有点击事件的注册
     * */
    protected <T extends View> T findViewClick(int viewId){
        findViewById(viewId).setOnClickListener(this);
        return (T)findViewById(viewId);
    }
    /**
     * 重写findView方法
     * */
    protected <T extends View> T findView(int viewId){
        return (T)findViewById(viewId);
    }



    public void dismiss(){
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }
    public void showDialog(){
        if (!dialog.isShowing()){
            dialog.show();
        }
    }
    public void showSimpleDialog(){
        if (!proDialog.isShowing()){
            proDialog.show();
        }
    }
    public void dismissSimDialog(){
        if(proDialog.isShowing()){
            proDialog.dismiss();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        if(dialog.isShowing()){
            dialog.dismiss();
        }
    }


    /**
     * 防止快速点击造成的点击重复
     * */
    public boolean isDoubleClick(){
        long time = System.currentTimeMillis();
        long timeDur = time - lastClickTime;
        if(0 < timeDur && timeDur < MIN_CLICK_TIME){
            return true;
        }
        lastClickTime = time;
        return false;
    }
    /**
     * 防止快速刷新/快速下拉加载更多造成的重复数据 公用方法
     * */
    public boolean isDoubleRefresh(){
        long time = System.currentTimeMillis();
        long timeDur = time - lastRequestTime;
        if(0 < timeDur && timeDur < MIN_REFRESH_TIME){
            lastRequestTime = time;
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = AppContext.getInstance().getUser();
    }
    public boolean checkRecordPermissionSelf(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permission  = checkSelfPermission(Manifest.permission.RECORD_AUDIO);
            if (permission == PackageManager.PERMISSION_GRANTED) {//有这个权限
                return true;
            }else if (permission == PackageManager.PERMISSION_DENIED){//没有这个权限
                //去请求这个权限
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO},1);
            }
        }else {
            return true;
        }
        return false;
    }
    public void showToast(String strAler){
        Toast.makeText(BaseActivity.this,strAler,Toast.LENGTH_SHORT).show();
    }
    public boolean isReadyToRequestUrl(){
        return readyToRequest;
    }
    public void setRequestUrl(boolean b_ready){
        readyToRequest = b_ready;
    }
}
