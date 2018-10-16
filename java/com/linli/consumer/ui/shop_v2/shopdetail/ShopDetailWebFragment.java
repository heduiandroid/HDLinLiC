package com.linli.consumer.ui.shop_v2.shopdetail;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseFragment;

/**
 * Created by tomoyo on 2016/12/10.
 */

public class ShopDetailWebFragment extends BaseFragment {

    private CustomWebView webView;
    private WebSettings webSettings;
    private boolean hasInit = false;
    private String info = "";

    public static ShopDetailWebFragment newInstance(String webDetail){
        ShopDetailWebFragment fragment = new ShopDetailWebFragment();
        Bundle bundle = new Bundle();
        bundle.putString("webDetail",webDetail);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_detail_web;
    }

    @Override
    protected void initView() {

        dismiss();
        webView = findView(R.id.fragment_shop_detail_webview);
        info = getArguments().getString("webDetail");
        initWebView();
        fillData();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void fillData() {
        if(webView != null && !hasInit){
            hasInit = true;
            webView.loadDataWithBaseURL("x-data://base",comUrl(), "text/html", "utf-8",null);
            //webView.loadUrl("");
            //TODO url
        }

    }

    private void initWebView(){
        webSettings = webView.getSettings();
        //能够和js交互
        webSettings.setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        webSettings.setBuiltInZoomControls(false);
        //开启DOM storage API功能
        webSettings.setDomStorageEnabled(true);
        //开启application Cache功能
        webSettings.setAppCacheEnabled(false);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new MyWebViewClient());
        // 设置是否加载图片，true不加载，false加载图片
        webSettings.setBlockNetworkImage(false);
        webSettings.setTextSize(WebSettings.TextSize.LARGEST);

    }

    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    private String comUrl(){

        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/Moen.css\" type=\"text/css\">";

        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "\t<meta charset=\"utf-8\" />"
                + css
                + "\n</head>\n"
                + info
                + "</body></html>";
        return html;
    }



}
