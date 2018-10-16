//package com.linli.consumer.ui.load_h5;
//
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.util.Log;
//import android.view.View;
//import android.webkit.ValueCallback;
//import android.webkit.WebChromeClient;
//import android.widget.Button;
//
//import com.google.gson.Gson;
//import com.linli.consumer.R;
//import com.linli.consumer.app.AppContext;
//import com.linli.consumer.base.BaseActivity;
//import com.linli.consumer.base.UIHelper;
//import com.linli.consumer.bean.UserForH5;
//import com.linli.consumer.common.Common;
//import com.linli.consumer.domain.City;
//import com.linli.consumer.utils.CommonUtil;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class ParentH5NewActivity extends BaseActivity {
//    private final String TAG = "ParentH5NewActivity";
//    private AppContext appContext;
//    private com.linli.consumer.domain.User user;
//    private City city;
//    private UserForH5 userForH5 = new UserForH5();
//    BridgeWebView webView;
//
//    Button button;
//
//    int RESULT_CODE = 0;
//
//    ValueCallback<Uri> mUploadMessage;
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_parent_h5_new;
//    }
//
//    @Override
//    protected void initView() {
//        webView = (BridgeWebView) findViewById(R.id.webView);
//        button = findViewClick(R.id.button);
//    }
//
//    @Override
//    protected void initData() {
//        webView.setDefaultHandler(new DefaultHandler());
//        webView.setWebChromeClient(new WebChromeClient() {
//            @SuppressWarnings("unused")
//            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType, String capture) {
//                this.openFileChooser(uploadMsg);
//            }
//            @SuppressWarnings("unused")
//            public void openFileChooser(ValueCallback<Uri> uploadMsg, String AcceptType) {
//                this.openFileChooser(uploadMsg);
//            }
//            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//                mUploadMessage = uploadMsg;
//                pickFile();
//            }
//        });
//        webView.loadUrl(Common.mIndexH5);
//        //被动接受H5的请求的监听 （要原生去做什么）
//        doSthForH5Listener();
//        //主动给H5传递数据 （将本地的用户信息传给H5（包含定位信息））
//        sendNativeUserInfoToH5();
//        webView.send("hello");
//        dismiss();
//    }
//    /*
//     获取原生的用户信息（包含定位信息）
//      */
//    private void sendNativeUserInfoToH5() {
//        appContext = (AppContext) getApplicationContext();
//        user = appContext.getUser();
//        city = appContext.getCity();
//        if (user != null){
//            userForH5.setId(user.getId());
//            if(user.getNickName() == null || user.getNickName().equals("") || user.getNickName().equals("null") ){
//                userForH5.setNickname(CommonUtil.secretPhone(user.getPhone()));
//            }else {
//                userForH5.setNickname(user.getNickName());
//            }
//        }
//        if (city != null){
//            UserForH5.Location location = new UserForH5.Location();
//            location.setLongitude(city.getLongitude());
//            location.setLatitude(city.getLatitude());
//            userForH5.setLocation(location);
//        }
//        if (userForH5 != null){
//            webView.callHandler("functionInJs", new Gson().toJson(userForH5), new CallBackFunction() {
//                @Override
//                public void onCallBack(String data) {
//
//                }
//            });
//        }
//    }
//    /*
//    注册监听 H5需要原生做什么操作通过此处通知我们 我需要的参数一同携带给我
//     */
//    private void doSthForH5Listener() {
//        webView.registerHandler("submitFromWeb", new BridgeHandler() {
//            @Override
//            public void handler(String data, CallBackFunction function) {//data是需要解析的参数
//                Log.i(TAG, "data from web = " + data);
//                try {
//                    JSONObject msg = new JSONObject(data);
//                    switch (msg.getInt("tag")){
//                        case 1://关闭页面
//                            ParentH5NewActivity.this.finish();
//                            break;
//                        case 2://跳转到原生登录界面
//                            UIHelper.togoLoginActivity(ParentH5NewActivity.this);
//                            break;
//                        case 3://吐司提示 闪烁的提示文字
//                            showToast(msg.getString("msg"));
//                            break;
//                        case 4://调用安卓提示弹出框 提示文字 alert something
//                            alertSomething(msg.getString("title"),msg.getString("msg"));
//                            break;
//                        default:
//                            showToast("网络繁忙，请稍后再试");
//                            break;
//                    }
//                    function.onCallBack("success");
//                } catch (JSONException e) {
//                    function.onCallBack("failed--"+e.toString());
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private void alertSomething(String title,String msg) {
//        new AlertDialog.Builder(getWindow().getContext())
//                .setTitle(title)
//                .setMessage(msg)
//                .setPositiveButton("确定",null)
//                .create().show();
//    }
//
//    @Override
//    public void onHDClick(View v) {
//        if (button.equals(v)) {
//            webView.callHandler("functionInJs", "data from Java", new CallBackFunction() {
//                @Override
//                public void onCallBack(String data) {
//                    // TODO Auto-generated method stub
//                    Log.i(TAG, "reponse data from js " + data);
//                }
//            });
//        }
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        sendNativeUserInfoToH5();
//    }
//
//    public void pickFile() {
//        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        chooserIntent.setType("image/*");
//        startActivityForResult(chooserIntent, RESULT_CODE);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        if (requestCode == RESULT_CODE) {
//            if (null == mUploadMessage){
//                return;
//            }
//            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
//            mUploadMessage.onReceiveValue(result);
//            mUploadMessage = null;
//        }
//    }
//}
