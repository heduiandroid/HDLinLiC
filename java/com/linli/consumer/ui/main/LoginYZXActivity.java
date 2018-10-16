package com.linli.consumer.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.RongToken;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.utils.DemoContext;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginYZXActivity extends BaseActivity {
    private String mUsername,mPassword;
    private EditText et_username,et_password;
    private ImageView iv_seeingpwd;
    private AppContext appContext;
    private User user;
    private String rongyunToken;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_yzh;
    }

    @Override
    protected void initView() {
        initViews();
    }

    @Override
    protected void initData() {
        dismiss();
    }

    private void initViews() {
        et_username= findView(R.id.et_username);
        et_password= findView(R.id.et_password);
        findViewClick(R.id.btn_login);
        findViewClick(R.id.tv_register);
        findViewClick(R.id.ll_wechat_login);
        findViewClick(R.id.tv_forget_password);
        findViewClick(R.id.iv_back);
        iv_seeingpwd = findView(R.id.iv_seeingpwd);
        iv_seeingpwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else if (event.getAction() == MotionEvent.ACTION_UP){
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user != null){
            finish();
            showToast("登录成功");
        }
        dismissSimDialog();
        super.onResume();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_login:
                mUsername = et_username.getText().toString().trim();
                mPassword = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)){
                    Toast.makeText(this,"请输入账号和密码",Toast.LENGTH_SHORT).show();
                }else if (mPassword.length()<6){
                    Toast.makeText(this,"密码不能少于6位",Toast.LENGTH_SHORT).show();
                }else {
                    doLogin(mUsername, mPassword);
                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginYZXActivity.this, RegisterYZXActivity.class));
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(LoginYZXActivity.this,ResetPasswordActivity.class));
                break;
            case R.id.ll_wechat_login:
                showSimpleDialog();
                IWXAPI iwxapi = WXAPIFactory.createWXAPI(this, Common.APP_ID,true);
                iwxapi.registerApp(Common.APP_ID);
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";//
//                req.scope = "snsapi_login";//提示 scope参数错误，或者没有scope权限
                req.state = "wechat_sdk_微信登录";
                iwxapi.sendReq(req);
                break;
            default:
                break;
        }
    }
    private void doLogin(String username, final String password) {
        rongyunToken = DemoContext.getInstance().getSharedPreferences().getString("RY_TOKEN"+username, null);
        System.out.println("融云Token："+rongyunToken);
        showDialog();
        IntrestBuyNet.userLogin(username,password,new HandleSuccess<Login>() {
            @Override
            public void success(Login s) {
                if (s.getResponseData() != null && s.getResponseCode().equals(CafeNet.SUCCESS)){
                    //填充User对象；将账号密码存到本地用于以后自动登录
                    appContext = (AppContext) getApplicationContext();
                    user = new User();
                    user.setId(s.getResponseData().getPersonalCode());
                    user.setPassword(password);
                    if (s.getResponseData().getPersonalRealName() == null){
                        user.setNickName(null);
                        user.setRealName(null);
                    }else {
                        user.setNickName(s.getResponseData().getPersonalRealName());
                        user.setRealName(s.getResponseData().getPersonalRealName());
                    }

                    if (s.getResponseData().getPersonalIdcard() == null){
                        user.setBirthday(null);
                        user.setIdCardNum(null);
                    }else {
                        user.setBirthday(s.getResponseData().getPersonalIdcard());
                        user.setIdCardNum(s.getResponseData().getPersonalIdcard());
                    }
                    user.setPhone(s.getResponseData().getPersonalPhone());
                    if (s.getResponseData().getPersonalAvatar() == null){
                        user.setHeadPath(null);
                    }else {
                        user.setHeadPath(s.getResponseData().getPersonalAvatar());
                    }

                    if (s.getResponseData().getOpenId() != null){
                        user.setThirdWXid(s.getResponseData().getOpenId());
                    }
                    if (s.getResponseData().getInvitationCode() != null){
                        user.setInviteCode(s.getResponseData().getInvitationCode());
                    }
                    appContext.setUser(user);
                    appContext = (AppContext) getApplicationContext();
                    user = appContext.getUser();
                    if (rongyunToken != null){
                        connect(rongyunToken);
                        LoginYZXActivity.this.finish();//finish掉登录界面
                    }else {
                        request_rongyun_token();
                    }
                }else {
                    dismiss();
                    showToast(s.getResponseMessage());
                }
            }
        });
    }
    private void request_rongyun_token(){
        String avatar = "";
        String nickname = "";
        if (user.getNickName() == null) {
            nickname = user.getPhone();
        }else {
            nickname = user.getNickName();
        }
        if (user.getHeadPath() == null) {
            avatar = "";
        } else {
            avatar = user.getHeadPath();
        }
        IntrestBuyNet.getRongToken(avatar, nickname, user.getId(), new HandleSuccess<RongToken>() {
            @Override
            public void success(RongToken s) {
                if (s.getCode() == 200){
                    String token = s.getToken();
                    connect(token);//连接融云服务器
                    SharedPreferences.Editor edit = DemoContext.getInstance().getSharedPreferences().edit();
                    edit.putString("RY_TOKEN"+user.getPhone(), token);//+userid需测试
                    edit.apply();
                }else {
                    dismiss();
                    Toast.makeText(LoginYZXActivity.this,"提示：聊天系统维护中",Toast.LENGTH_SHORT).show();
                }
                LoginYZXActivity.this.finish();//finish掉登录界面
            }
        });
    }


    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(final String token) {
        if (getApplicationInfo().packageName.equals(AppContext.getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {//重新获取token
                    Log.d("LoginActivity", "--onTokenIncorrect");
                    Log.i("test","重新获取了一次Token，并重连");
                    request_rongyun_token();
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);

//                    String nickname = null;
//                    String headpath = null;
//                    if (user.getNickName().equals("") || user.getNickName().equals("null") || user.getNickName() == null) {
//                        nickname = user.getPhone();
//                    } else {
//                        nickname = user.getUsername();
//                    }
//                    if (user.getHeadPath().equals("") || user.getHeadPath().equals("null") || user.getHeadPath() == null) {
//                        headpath = null;
//                    } else {
//                        headpath = user.getHeadPath();
//                    }
//                    CUserInfo userInfo = new CUserInfo(userid, nickname, Uri.parse(headpath));
//                    if (RongIM.getInstance() != null) {
//                        RongIM.getInstance().setCurrentUserInfo(userInfo);
//                    }
                    dismiss();
                    Toast.makeText(LoginYZXActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    JPushInterface.resumePush(getApplicationContext());

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("LoginActivity", "--onError" + errorCode);
                    Log.i("test","融云链接错误，并重连了一次");
                    //                    RongIM.getInstance().disconnect();
                    RongIM.getInstance().logout();
                    new Handler().postDelayed(new Runnable() {//5秒之后重连
                        @Override
                        public void run() {
                            if (rongyunToken != null){
                                connect(rongyunToken);
                            }else {
                                request_rongyun_token();
                            }
                        }
                    }, 5000);
                }
            });
        }
    }


    //    private void RongyunDemoLoginAndConnect() {
//        /**
//         * 用户登录，用户登录成功，获得 cookie，将cookie 保存
//         */
//        new AsyncTask<Void, Void, String>() {
//
//            @Override
//            protected String doInBackground(Void... params) {
//
//                Map<String, String> requestParameter = new HashMap<String, String>();
//
//                requestParameter.put("email", "yang115@qq.com");
//                requestParameter.put("password", "123456");
//
//                String result = NetUtils.sendPostRequest("email_login", requestParameter);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//
//                getToken();
//            }
//        }.execute();
//    }
    /**
     * token 的主要作用是身份授权和安全，因此不能通过客户端直接访问融云服务器获取 token，
     * 您必须通过 Server API 从融云服务器 获取 token 返回给您的 App，并在之后连接时使用
     */
//    private String token;
    /**
     * 获得token
     */
//    private void getToken() {
//        new AsyncTask<Void, Void, String>() {
//            @Override
//            protected String doInBackground(Void... params) {
//                String result = NetUtils.sendGetRequest("token");
//                return result;
//            }
//            @Override
//            protected void onPostExecute(String result) {
//                try {
//                    if (result != null) {
//                        JSONObject object = new JSONObject(result);
//                        JSONObject jobj = object.getJSONObject("result");
//                        if (object.getInt("code") == 200) {
//                            token = jobj.getString("token");
//                            connect(token);
//                            SharedPreferences.Editor edit = DemoContext.getInstance().getSharedPreferences().edit();
//                            edit.putString("RY_TOKEN", token);
//                            edit.apply();
//                        }
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.execute();
//    }
//    @Override
//    public CUserInfo getUserInfo(String s) {
//        if (user != null){
//            return new CUserInfo(user.getId().toString(),user.getNickName(),Uri.parse(user.getHeadPath()));
//        }
//        return null;
//    }

//    @Override
//    protected void onResume() {
//        TestinAgent.onResume(this);
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        TestinAgent.onPause(this);
//        super.onPause();
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        TestinAgent.onDispatchTouchEvent(this,ev);
//        return super.dispatchTouchEvent(ev);
//    }
    //    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
//        @Override
//        public void gotResult(int code, String alias, Set<String> tags) {
//            String logs ;
//            switch (code) {
//                case 0:
//                    logs = "Set tag and alias success";
//                    Log.i("JPush", logs);
//                    break;
//
//                case 6002:
//                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
//                    Log.i("JPush", logs);
//                    if (ExampleUtil.isConnected(getApplicationContext())) {
//                        jPushHandler.sendMessageDelayed(jPushHandler.obtainMessage(YZXIndexActivity.MSG_SET_ALIAS, alias), 1000 * 60);
//                    } else {
//                        Log.i("JPush", "No network");
//                    }
//                    break;
//
//                default:
//                    logs = "Failed with errorCode = " + code;
//                    Log.e("JPush", logs);
//            }
//
////            ExampleUtil.showToast(logs, getApplicationContext());
//        }
//
//    };
//    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {
//
//        @Override
//        public void gotResult(int code, String alias, Set<String> tags) {
//            String logs ;
//            switch (code) {
//                case 0:
//                    logs = "Set tag and alias success";
//                    Log.i("JPush", logs);
//                    break;
//
//                case 6002:
//                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
//                    Log.i("JPush", logs);
//                    if (ExampleUtil.isConnected(getApplicationContext())) {
//                        jPushHandler.sendMessageDelayed(jPushHandler.obtainMessage(YZXIndexActivity.MSG_SET_TAGS, tags), 1000 * 60);
//                    } else {
//                        Log.i("JPush", "No network");
//                    }
//                    break;
//
//                default:
//                    logs = "Failed with errorCode = " + code;
//                    Log.e("JPush", logs);
//            }
//
////            ExampleUtil.showToast(logs, getApplicationContext());
//        }
//
//    };
//    private final Handler jPushHandler = new Handler() {
//        @Override
//        public void handleMessage(android.os.Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case YZXIndexActivity.MSG_SET_ALIAS:
//                    Log.d("JPush", "Set alias in handler.");
//                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
//                    break;
//
//                case YZXIndexActivity.MSG_SET_TAGS:
//                    Log.d("JPush", "Set tags in handler.");
//                    JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
//                    break;
//
//                default:
//                    Log.i("JPush", "Unhandled msg - " + msg.what);
//            }
//        }
//    };
//    /**
//     * 为用户设置别名用于推送消息
//     */
//    private void setAlias() {
//        if (user != null){
//            String alias = user.getId().toString();
//            if (TextUtils.isEmpty(alias)){
//                Log.i("alias为空:",alias);
//                return;
//            }
//            if (!ExampleUtil.isValidTagAndAlias(alias)){
//                Log.i("alias格式错误:",alias);
//                return;
//            }
//            jPushHandler.sendMessage(jPushHandler.obtainMessage(YZXIndexActivity.MSG_SET_ALIAS, alias));
//        }
//    }
}








