package com.linli.consumer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.ActivityCollector;
import com.linli.consumer.bean.Login;
import com.linli.consumer.bean.RongToken;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.ui.main.RegisterYZXActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import retrofit2.Call;

/**
 * Created by hasee on 2017/8/2.
 */

public class LoginHelper {
    private static String rongyunToken;
    private static AppContext appContext;
    private static User user;
    private static Context context;

    // 第一步 判断有没有wxopenid(暂时用userid代替)
    // 如果有，用openid获取用户信息 再调用doLoginSilentlyWeChat
    // 如果没有，走以前的逻辑doLoginSilently
    public static void doLoginSilently(final Context ctx){
        Long sp_userid = CommonUtil.getCacheDatasLong(ctx, "userid", "userData");
        if (sp_userid!=null && sp_userid != 0L){//调用接口用userid查询用户信息
            IntrestBuyNet.findUserInfo(sp_userid.toString(), new HandleSuccess<Login>() {
                @Override
                public void success(Login s) {
                    if (s.getResponseData() != null && s.getResponseCode().equals(CafeNet.SUCCESS)){
                        doLoginSilentlyWeChat(ctx,s);
                    }
                }
            });
        }else {
            doLoginSilentlyPhone(ctx);
        }
    }
    public static void doLoginSilentlyWeChat(Context ctx,Login s) {
        context = ctx;

        rongyunToken = DemoContext.getInstance().getSharedPreferences().getString("RY_TOKEN" + s.getResponseData().getPersonalPhone(), null);
        //填充User对象；将账号密码存到本地用于以后自动登录
        appContext = (AppContext) ctx.getApplicationContext();
        user = new User();
        user.setId(s.getResponseData().getPersonalCode());
        user.setPassword("");
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
        appContext = (AppContext) ctx.getApplicationContext();
        user = appContext.getUser();
        if (user != null && rongyunToken != null) {
            connect(rongyunToken);
        }else {
            request_rongyun_token();
        }
        JPushInterface.resumePush(ctx.getApplicationContext());

    }
    public static void doLoginSilentlyPhone(final Context ctx) {
        context = ctx;
        SharedPreferences preferences = ctx.getSharedPreferences("userData", Context.MODE_PRIVATE);

        String username=preferences.getString("phone", "");
        final String password=preferences.getString("password", "");
        rongyunToken = DemoContext.getInstance().getSharedPreferences().getString("RY_TOKEN" + username, null);
        if (username.equals("") || password.equals("")){
            return;
        }
        IntrestBuyNet.userLogin(username, password, new HandleSuccess<Login>() {
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
            }
            @Override
            public void success(Login s) {
                if (s.getResponseData() != null && s.getResponseCode().equals(CafeNet.SUCCESS)){
                    //填充User对象；将账号密码存到本地用于以后自动登录
                    appContext = (AppContext) ctx.getApplicationContext();
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
                    appContext = (AppContext) ctx.getApplicationContext();
                    user = appContext.getUser();
                    if (user != null && rongyunToken != null) {
                        connect(rongyunToken);
                    }else {
                        request_rongyun_token();
                    }
                    JPushInterface.resumePush(ctx.getApplicationContext());
                }else {
                    Toast.makeText(ctx, "登录信息变更，需重新登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private static void request_rongyun_token(){
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
                }
            }
        });
    }
    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private static void connect(String token) {
        if (context.getApplicationInfo().packageName.equals(AppContext.getCurProcessName(context.getApplicationContext()))) {
            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {//重新获取token
                    Log.d("YZXIndexActivity", "--onTokenIncorrect");
                    Log.i("test","重新获取了一次Token，并重连");
                    request_rongyun_token();
                }
                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("YZXIndexActivity", "--onSuccess" + userid);
                    for(int i = 0; i < ActivityCollector.activities.size() ; i ++){
                        if(ActivityCollector.activities.get(i) instanceof RegisterYZXActivity){
                            ActivityCollector.activities.get(i).finish();
                        }
                    }
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

    /**
     * TODO 保存各种openId至本地 但是暂时type可以随便传不用这个参数
     * @param type type 1代表微信  2代表QQ  3代表其他
     * @param userid  暂时把用户id存到本地用于获取用户信息使用  以后可能会改成openid  但是最稳妥的办法是有一个变动的token控制
     */
    public static void saveOpenId(Context context,int type,String userid){
        CommonUtil.setCacheDatas(context,"userid",userid);
    }
}

