package com.linli.consumer.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.BalancePayBean;
import com.linli.consumer.bean.ChargeBean;
import com.linli.consumer.bean.FindAccounts;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import com.swwx.paymax.PaymaxCallback;
import com.swwx.paymax.PaymaxSDK;

public class OnlinePayMethodActivity extends BaseActivity implements View.OnClickListener, PaymaxCallback {
    private Double totalPrice;

    private static final int SDK_PAY_FLAG = 1;
    public Long orderId;//订单id
    private String orderNo;
    private LinearLayout ll_weixinpay,ll_zhifubaopay;
    private LinearLayout ll_walet,ll_walet_pay;
    private TextView tv_accountcount;
    private String dataWX = null;//微信charge对象
    private String dataAli = null;//支付宝charge对象
    private Double balance = 0d;
    private Long balanceId = null;
    private Long balancePayId;//钱包支付用这个ID去支付 其实这是订单主键ID

    @Override
    protected int getLayoutId() {
        return R.layout.activity_online_pay_method;
    }

    @Override
    protected void initView() {
        dismiss();
        init();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        orderId = intent.getLongExtra("orderid",0);
        Log.i("test",orderId+"");
        user.setOrderNo(orderId);
        Log.i("test","初始订单编号为"+user.getOrderNo());
        totalPrice = intent.getDoubleExtra("totalprice",0);
        orderNo = intent.getStringExtra("orderno");
        balancePayId = intent.getLongExtra("balancePayId",0);
        if (orderNo.equals("recharge")){
            ll_walet.setVisibility(View.GONE);
        }else {
            checkBalance();
        }
        System.out.println(orderNo+"  "+totalPrice);
    }
    /*
    查询余额
     */
    private void checkBalance() {
        IntrestBuyNet.findAccounts(4, user.getId(), new HandleSuccess<FindAccounts>() {
            @Override
            public void success(final FindAccounts findAccounts) {
                if (findAccounts.getStatus() == 1) {
                    balanceId = findAccounts.getData().get(0).getId();
                    switch (findAccounts.getData().get(0).getStatus()) {  // // 账户状态：0正常，1冻结，2危险
                        case 0:  //
                            balance = findAccounts.getData().get(0).getBalance();
                            if (findAccounts.getData().get(0).getBalance() != null) {
                                tv_accountcount.setText("钱包剩余¥ "+balance);
                                if (balance == 0d){
                                    tv_accountcount.setText("钱包剩余¥ 0.00");
                                }
                            }else {
                                balance = 0d;
                                tv_accountcount.setText("钱包剩余¥ 0.00");
                            }
                            break;
                        default:
                            tv_accountcount.setText("钱包剩余¥ 0.00");
                            break;
                    }
                    if (totalPrice <= 0d) {
                        if (balanceId != null && balancePayId != 0) {
                            startWalletPay();
                        } else {
                            showToast("余额支付升级中，请稍后再试");
                        }
                    }
                } else {
                    balance = 0d;
                    tv_accountcount.setText("钱包剩余¥ 0.00");
                }
            }
        });
    }

    private void init() {
        ll_weixinpay = findViewClick(R.id.ll_weixinpay);
        ll_zhifubaopay = findViewClick(R.id.ll_zhifubaopay);
        ll_walet = findView(R.id.ll_walet);
        ll_walet_pay = findViewClick(R.id.ll_walet_pay);
        tv_accountcount = findView(R.id.tv_accountcount);
    }
    /**
     * 支付宝支付渠道
     */
    private String CHANNEL_ALIPAY = "alipay_app";

    /**
     * 微信支付渠道
     */
    private String CHANNEL_WECHAT = "wechat_app";
    private String userNumberPaymax = "app_9dk8yOrq1MUE7668";
    private String timeExpirePaymax = "3600";
    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.ll_weixinpay:
                if (totalPrice <= 0d){
                    showToast("金额数量过低，不可微信支付");
                }else {
                    startPay(CHANNEL_WECHAT);
                }
                break;
            case R.id.ll_zhifubaopay:
                //正确做法应该是先添加一个get_rsa_private()方法，成功才调用alipay()方法
//                get_alipay_private();
                if (totalPrice <= 0d){
                    showToast("金额数量过低，不可支付宝支付");
                }else {
                    startPay(CHANNEL_ALIPAY);
                }
                break;
            case R.id.ll_walet_pay://钱包支付
                if (balance < totalPrice){
                    //余额不足以支付
                    showToast("您当前余额不足");
                }else {//余额支付逻辑
                    if (balanceId != null && balancePayId != 0){
                        startWalletPay();
                    }else {
                        showToast("余额支付升级中，请稍后再试");
                    }
                }
                break;
            default:
                finish();
                break;
        }
    }
    /*
    钱包支付逻辑
     */
    private void startWalletPay() {
        IntrestBuyNet.balancePay(balanceId, totalPrice, balancePayId, new HandleSuccess<BalancePayBean>() {
            @Override
            public void success(BalancePayBean s) {
                if (s.getStatus() == 1){
                    showToast("支付成功");
                    Intent intent = new Intent();
                    intent.putExtra("success",true);
                    setResult(1009,intent);
                    OnlinePayMethodActivity.this.finish();
                }else {
                    showToast("余额支付升级中，请稍后再试");
                }
            }
        });
    }

    private void startPay(final String method) {
        showSimpleDialog();
        long time_expire=Long.parseLong(timeExpirePaymax)* 1000+System.currentTimeMillis();
        Log.d("FaceRecoSDK", "time_expire=" + time_expire);
        Log.i("test","订单编号为"+user.getOrderNo());
        IntrestBuyNet.getChartLKL("商互通(指令)订单", "商品在线支付", method, time_expire, totalPrice, userNumberPaymax, user.getOrderNo().toString(),new HandleSuccess<ChargeBean>() {
            @Override
            public void success(ChargeBean s) {
                String data = null;
                user.setOrderNo(Long.valueOf(s.getOrder_no()));
                Log.i("test","新的订单编号为"+user.getOrderNo());
                if (method.equals(CHANNEL_ALIPAY)){
                    dataAli = JSON.toJSONString(s);
                    data = dataAli;
                    Log.d("PaymaxSDK", "dataAli=" + dataAli);
                }else if (method.equals(CHANNEL_WECHAT)){
                    dataWX = JSON.toJSONString(s);
                    dataWX = dataWX.replace("packageX","package");
                    data = dataWX;
                    Log.d("PaymaxSDK", "dataWX=" + dataWX);
                }
               if (data != null){
                   PaymaxSDK.pay(OnlinePayMethodActivity.this, data, OnlinePayMethodActivity.this);
               }else {
                   showToast("支付系统忙，请稍后再试");
               }
            }
        });
    }

    @Override
    public void onPayFinished(com.swwx.paymax.PayResult payResult) {
        dismissSimDialog();
        String msg = "未能完成支付";
        Intent intent = new Intent();
        switch (payResult.getCode()) {
            case PaymaxSDK.CODE_SUCCESS:
                user.setOrderNo(null);//支付成功，清空user中的orderno
                msg = "支付成功!";
                intent.putExtra("success",true);
                setResult(1009,intent);
                OnlinePayMethodActivity.this.finish();
                break;

            case PaymaxSDK.CODE_ERROR_CHARGE_JSON:
                msg = "未能完成支付，解析出现问题。";
                break;

            case PaymaxSDK.CODE_FAIL_CANCEL:
                msg = "支付已取消";
                break;

            case PaymaxSDK.CODE_ERROR_CHARGE_PARAMETER:
                msg = "未能完成支付，支付标识错误。";//appid error.
                break;

            case PaymaxSDK.CODE_ERROR_WX_NOT_INSTALL:
                msg = "没有安装微信";
                break;

            case PaymaxSDK.CODE_ERROR_WX_NOT_SUPPORT_PAY:
                msg = "微信版本不支持支付";
                break;

            case PaymaxSDK.CODE_ERROR_WX_UNKNOW:
                msg = "微信遇到未知问题，无法支付。";
                break;

            case PaymaxSDK.CODE_ERROR_ALI_DEAL:
                msg = "支付结果确认中";
                break;

            case PaymaxSDK.CODE_ERROR_ALI_CONNECT:
                msg = "支付宝网络连接失败";
                break;

            case PaymaxSDK.CODE_ERROR_CHANNEL:
                msg = "支付方式选择失败";
                break;

            case PaymaxSDK.CODE_ERROR_LAK_USER_NO_NULL:
                msg = "未能完成支付，支付标识为空。";
                break;

        }
        final String finalMsg = msg;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(OnlinePayMethodActivity.this, finalMsg,Toast.LENGTH_SHORT).show();
            }
        });
//        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
//                .setAction("Close", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
    }



    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
//    private String sign(String content) {
//        return SignUtils.sign(content, RSA_PRIVATE);
//    }
    /**
     * get the sign type we use. 获取签名方式
     *
     */
//    private String getSignType() {
//        return "sign_type=\"RSA\"";
//    }

    //    private void request_weixin_pay() {
//        IntrestBuyNet.wxPay(totalPrice.toString(),orderNo,"1","商互通(指令)订单","商品在线支付", new HandleSuccess<WXPayInfo>() {
//            @Override
//            public void success(WXPayInfo s) {
//                if (s.getStatus().equals("success")){
//                    Toast.makeText(OnlinePayMethodActivity.this, "请稍后", Toast.LENGTH_SHORT).show();
//                    PayReq req = new PayReq();
//                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//                    req.appId			= s.getObj().getAppid();
//                    req.partnerId		= s.getObj().getPartnerid();
//                    req.prepayId		= s.getObj().getPrepayid();
//                    req.nonceStr		= s.getObj().getNoncestr();
//                    req.timeStamp		= s.getObj().getTimestamp();
//                    req.packageValue	= "Sign=WXPay";
//                    req.sign			= s.getObj().getSign();
//                    req.extData			= "app data"; // optional
////                    Toast.makeText(OnlinePayMethodActivity.this, "开启微信支付", Toast.LENGTH_SHORT).show();
//                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//                    api.sendReq(req);
//                }else {
//                    Toast.makeText(OnlinePayMethodActivity.this,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        ll_weixinpay.setEnabled(true);
//        OnlinePayMethodActivity.this.finish();
//    }
//    private void get_alipay_private() {
//        //if获取成功,带着获取到的rsa_private去调用aliPay方法
//        IntrestBuyNet.zhifubaoPay(1l,new HandleSuccess<AliPayInfo>() {//这里参数第一个没用到
//            @Override
//            public void success(AliPayInfo s) {
//                if (s.getStatus() == 1){
//                    if (s.getStatus() == 1){
//                        PARTNER = s.getData().getPARTNER();
//                        SELLER = s.getData().getSELLER();
//                        RSA_PRIVATE = s.getData().getRSA_PRIVATE();
//                        RSA_PUBLIC = s.getData().getRSA_PUBLIC();
//                        IntrestBuyNet.notifyUrlAliPay(1, new HandleSuccess<NotifyUrlBean>() {
//                            @Override
//                            public void success(NotifyUrlBean s) {
//                                if (s.getStatus() == 1){
//                                    if (s.getData() != null){
//                                        aliPay(s.getData());
//                                    }else {
//                                        Toast.makeText(OnlinePayMethodActivity.this,"服务器繁忙，暂时无法完成付款",Toast.LENGTH_SHORT).show();
//                                        finish();
//                                    }
//                                }else {
//                                    Toast.makeText(OnlinePayMethodActivity.this,"服务器繁忙，暂时无法完成付款",Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }
//                            }
//                        });
//
//                    }else {
//                        System.out.println("id为空");
//                    }
//                }
//            }
//        });
//    }
//    private void aliPay(String notifyUrl) {
//        if (TextUtils.isEmpty(RSA_PRIVATE) || RSA_PRIVATE == null) {
//            new AlertDialog.Builder(this).setTitle("警告").setMessage("支付错误，请稍后重试")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialoginterface, int i) {
//                            //
//                            finish();
//                        }
//                    }).show();
//            return;
//        }
//        //需要服务器给我订单号
//        request_orderno(notifyUrl);
//
//
//    }

//    private void request_orderno(String notifyUrl) {///////////////////////////////////要根据订单号组织orderInfo,不可省略
//        //if 订单号获取成功////////////////////////////////商互通(指令)订单
//        String orderInfo = OrderInfo.getOrderInfo("商互通(指令)订单", "商品在线支付", totalPrice.toString(),orderNo,PARTNER,SELLER,notifyUrl);//商品名，商品介绍，商品价格，*订单号(需要填写刚刚获取到的订单号)
//        /**
//         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
//         */
//        request_sign(orderInfo, RSA_PRIVATE);//服务端应该根据orderInfo和rsa_private商户私钥给我一个签名
//
//
//    }
//private void request_sign(String orderInfo, String rsa_private) {//服务端根据我提供的orderInfo和我压根不知道的私钥生成sign给我，不可省略
//    //if获取sign成功后
//    String sign = sign(orderInfo);/////////////////////////////////这是我本地获取的sign，这样不行，应该是获取服务器给的sign成功后给的
//    try {
//        /**
//         * 仅需对sign 做URL编码
//         */
//        sign = URLEncoder.encode(sign, "UTF-8");
//    } catch (UnsupportedEncodingException e) {
//        e.printStackTrace();
//    }
//    /**
//     * 完整的符合支付宝参数规范的订单信息
//     */
//    final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
//    Runnable payRunnable = new Runnable() {
//        @Override
//        public void run() {
//            // 构造PayTask 对象
//            PayTask alipay = new PayTask(OnlinePayMethodActivity.this);
//            // 调用支付接口，获取支付结果
//            String result = alipay.pay(payInfo, true);
//
//            Message msg = new Message();
//            msg.what = SDK_PAY_FLAG;
//            msg.obj = result;
//            alipayHandler.sendMessage(msg);
//        }
//    };
//    // 必须异步调用
//    Thread payThread = new Thread(payRunnable);
//    payThread.start();
//}
}
