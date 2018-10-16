package com.linli.consumer.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.webkit.JavascriptInterface;

import com.linli.consumer.base.UIHelper;

/**
 * Created by hasee on 2018/5/30.
 */

public class H5UIHelper {
    private Context context;

    public H5UIHelper(Context context) {
        this.context = context;
    }
    @JavascriptInterface  //必须添加，这样才可以标志这个类的名称是 callNative
    public String toString(){
        return "callNative";
    }
    //关闭此页--这是 button.finishH5() 的触发事件
    //H5调用方法：javascript:button.finishH5()
    @JavascriptInterface
    public void finishH5(){
        ((Activity)context).finish();
    }
    //去登录页面--这是 button.toLogin() 的触发事件
    //H5调用方法：javascript:button.toLogin()
    @JavascriptInterface
    public void toLogin(){
        UIHelper.togoLoginActivity(context);
    }

    //拉起弹窗--这是 button.showAlert() 的触发事件，可以传递待参数
    //H5调用方法：javascript:button.showAlert('标题','内容')
    @JavascriptInterface
    public void showAlert(String title,String content){
        show(title,content);
    }

    private void show(String title,String data){
        new AlertDialog.Builder(((Activity)context).getWindow().getContext())
                .setTitle(title)
                .setMessage(data)
                .setPositiveButton("确定",null)
                .create().show();
    }
}
