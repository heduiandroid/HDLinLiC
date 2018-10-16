package com.linli.consumer.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.Login;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.CafeNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.ui.main.PaySuccessActivity;
import com.linli.consumer.utils.LoginHelper;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by hasee on 2018/4/10.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;
    private User user = AppContext.getInstance().getUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        api = WXAPIFactory.createWXAPI(this, Common.APP_ID, true);
        api.handleIntent(getIntent(), this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        JSONObject result = JSON.parseObject(JSON.toJSONString(baseResp));
//        Integer type = result.getIntValue("type");
        Integer type = baseResp.getType();
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (type != null){
                    if (type == 1){//微信登录获取code成功
                        String code = result.getString("code");
                        doWXLogin(code);
                        return;
                    }else if (type == 2){//分享成功
                        Toast.makeText(this,"分享成功",Toast.LENGTH_SHORT).show();
                    }
                    //微信支付成功，跳转支付成功页面
                    else if(type == ConstantsAPI.COMMAND_PAY_BY_WX){
                        //已经付款成功 返回的刚刚成功的数据 可以去某个界面显示一下付款成功
                        Toast.makeText(this,"支付成功",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(WXEntryActivity.this, PaySuccessActivity.class));
                    }
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (type != null){
                    switch (type){
                        case 1://取消微信登录
                            Toast.makeText(this,"登录已取消",Toast.LENGTH_SHORT).show();
                            break;
                        case 2://分享取消
                            Toast.makeText(this,"分享取消",Toast.LENGTH_SHORT).show();
                            break;
                        case ConstantsAPI.COMMAND_PAY_BY_WX://微信支付被取消，提示“支付已取消”即可
                            Toast.makeText(this,"支付已取消",Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                switch (type){
                    case 1://取消微信登录
                        Toast.makeText(this,"登录被拒绝",Toast.LENGTH_SHORT).show();
                        break;
                    case 2://分享拒绝
                        Toast.makeText(this,"分享被拒绝",Toast.LENGTH_SHORT).show();
                        break;
                    case ConstantsAPI.COMMAND_PAY_BY_WX://微信支付被取消，提示“支付被取消”即可
                        Toast.makeText(this,"支付被取消",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        finish();
    }
    private void doWXLogin(String code) {
        IntrestBuyNet.appWXLogin(code, new HandleSuccess<Login>() {
            @Override
            public void success(Login s) {
                if (s != null){
                    if ("400030".equals(s.getResponseCode())){
                        if (user != null){
                            Toast.makeText(WXEntryActivity.this,"该微信账号已经绑定其它账号",Toast.LENGTH_SHORT).show();
                        }else {
                            //存储openid到sharepreference中 以后自动登录备用30
                            LoginHelper.saveOpenId(WXEntryActivity.this, 1, s.getResponseData().getPersonalCode());
                            LoginHelper.doLoginSilentlyWeChat(WXEntryActivity.this, s);
                        }
                    }else if ("400029".equals(s.getResponseCode())){
                        if (s.getResponseData().getOpenId() != null) {//是新用户 直接注册即可
                            UIHelper.togoBindPhoneActivity(WXEntryActivity.this, s.getResponseData().getOpenId());
                        }
                    }else if ("400031".equals(s.getResponseCode())){//是老用户 直接绑定即可
                        UIHelper.togoBindPhoneActivity(WXEntryActivity.this, s.getResponseData().getOpenId());
                    }else {
                        Toast.makeText(WXEntryActivity.this,s.getResponseMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
//                switch (s.getStatus()){
//                    case 1:
//                        if (user != null){
//                            Toast.makeText(WXEntryActivity.this,"该微信账号已经绑定其它账号",Toast.LENGTH_SHORT).show();
//                        }else {
//                            //存储openid到sharepreference中 以后自动登录备用30
//                            LoginHelper.saveOpenId(WXEntryActivity.this, 1, s.getData().getId());
//                            LoginHelper.doLoginSilentlyWeChat(WXEntryActivity.this, s);
//                        }
//                        break;
//                    case 2:
//                        if (s.getData().getOpenid() != null) {29
//                            UIHelper.togoBindPhoneActivity(WXEntryActivity.this, s.getData().getOpenid());
//                        }
//                        break;
                        //31
//                    default:
//                        break;
//                }
                WXEntryActivity.this.finish();
            }
        });
    }
}
