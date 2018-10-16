package com.linli.consumer.ui.main;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;

public class BrowserAdverActivity extends BaseActivity {
    private String url;
    private WebView wv_advertisement;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_browser_adver;
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        wv_advertisement = findView(R.id.wv_advertisement);
        wv_advertisement.loadUrl(url);
        wv_advertisement.getSettings().setJavaScriptEnabled(true);
        wv_advertisement.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        dismiss();
    }

    @Override
    public void onHDClick(View v) {

    }
}
