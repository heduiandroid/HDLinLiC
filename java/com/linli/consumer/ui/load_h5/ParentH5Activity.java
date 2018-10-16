package com.linli.consumer.ui.load_h5;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.utils.H5UIHelper;

public class ParentH5Activity extends BaseActivity {
    private WebView wv_container_loadurl;
    private Button redButton,colorButton;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_parent_h5;
    }

    @Override
    protected void initView() {
        redButton = findViewClick(R.id.red);
        colorButton = findViewClick(R.id.color);
        wv_container_loadurl = findView(R.id.wv_container_loadurl);
        initWebView();

    }

    private void initWebView() {
        wv_container_loadurl.getSettings().setJavaScriptEnabled(true);
        wv_container_loadurl.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //这段代码的作用是让webview不要使用系统自带浏览器
//        wv_container_loadurl.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url){
//                view.loadUrl(url);
//                return true;
//            }
//        });
        H5UIHelper h5UIHelper = new H5UIHelper(this);
        //这里添加JS的交互事件，这样H5就可以调用原生的代码
        wv_container_loadurl.addJavascriptInterface(h5UIHelper,h5UIHelper.toString());
    }

    @Override
    protected void initData() {
        wv_container_loadurl.loadUrl("file:///android_asset/test.html");
        wv_container_loadurl.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wv_container_loadurl.loadUrl("javascript:setUserId('我是android原生传过来的用户ID')");
                wv_container_loadurl.loadUrl("javascript:setLocation('我是android原生传过来的经度','我是android原生传过来的纬度')");
            }
        });
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.red:  //调用JS中的无参数方法
                wv_container_loadurl.loadUrl("javascript:setRed()");
                break;
            case R.id.color://调用JS中的有参数方法
                wv_container_loadurl.loadUrl("javascript:setUserId('这是android 原生调用JS代码的触发事件')");
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        wv_container_loadurl.loadUrl("javascript:setUserId('我又给你传了一次用户ID')");
        wv_container_loadurl.loadUrl("javascript:setLocation('我又给你传了一次经度','我又给你传了一次纬度')");
    }
}
