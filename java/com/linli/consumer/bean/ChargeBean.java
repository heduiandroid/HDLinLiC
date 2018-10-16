package com.linli.consumer.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hasee on 2017/8/5.
 */

public class ChargeBean {


    /**
     * amount : 52.0
     * amount_refunded : 0
     * amount_settle : 0
     * app : app_7hqF2S6GYXET457i
     * body : 商品在线支付
     * channel : wechat_app
     * client_ip : 127.0.0.1
     * credential : {"alipay_app":{"orderInfo":"_input_charset=\"utf-8\"&it_b_pay=\"2017-08-05 18:18:25\"&notify_url=\"https://www.paymax.cc/webservice/alipay_app\"&out_trade_no=\"ch_9c7796b140557bcc97c12a66\"&partner=\"2088121688433844\"&payment_type=\"1\"&seller_id=\"beijingheduikeji@163.com\"&service=\"mobile.securitypay.pay\"&subject=\"商品在线支付\"&total_fee=\"52.0\"&sign=\"PCsqBRq5266u5LxmUwH%2BHyY4ao%2BbMQl%2Baa2matGDJFwNNHNQj2K49z9Xs2SfwUJ1Z2egRYAl3uOH1P6qLP%2Be8MV3Z9d%2FmTgrhoX5jFCDgtIfj%2BtGLNXqRAfJnOiC9zf1Ql%2BLl1uM2GOgU3yoJN1CLJH8CBU%2F11kJO7mH%2BJ1EUeI%3D\"&sign_type=\"RSA\""},"wechat_app":{"sign":"D52DE07E8BC1D0FBEEC96AEB4AA37DB1","timestamp":1501841267,"noncestr":"imspwnxgfxw5rrfyjy9g246kb2xoq8r5","partnerid":"1324016301","prepayid":"wx20170804180747706130843e0302245553","package":"Sign=WXPay","appid":"wx5269eef08886e3d5"}}
     * currency : cny
     * description :
     * extra : {"user_id":"app_9dk8yOrq1MUE7668"}
     * id : ch_e9b26b1e8f3d6eaad2384ac8
     * liveMode : false
     * order_no : eab31c33d56b457bb9ed41df8a4337cc
     * refunds : []
     * reqSuccessFlag : true
     * status : PROCESSING
     * subject : 商互通(指令)订单
     * time_created : 1501841267000
     * time_expire : 1501844861575
     */

    private double amount;
    private int amount_refunded;
    private int amount_settle;
    private String app;
    private String body;
    private String channel;
    private String client_ip;
    private CredentialBean credential;
    private String currency;
    private String description;
    private ExtraBean extra;
    private String id;
    private boolean liveMode;
    private String order_no;
    private boolean reqSuccessFlag;
    private String status;
    private String subject;

    private long time_created;
    private long time_expire;
    private List<?> refunds;

    public int getAmount_refunded() {
        return amount_refunded;
    }

    public void setAmount_refunded(int amount_refunded) {
        this.amount_refunded = amount_refunded;
    }

    public int getAmoun_settle() {
        return amount_settle;
    }

    public void setAmoun_settle(int amoun_settle) {
        this.amount_settle = amoun_settle;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public long getTime_created() {
        return time_created;
    }

    public void setTime_created(long time_created) {
        this.time_created = time_created;
    }

    public long getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(long time_expire) {
        this.time_expire = time_expire;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public CredentialBean getCredential() {
        return credential;
    }

    public void setCredential(CredentialBean credential) {
        this.credential = credential;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isLiveMode() {
        return liveMode;
    }

    public void setLiveMode(boolean liveMode) {
        this.liveMode = liveMode;
    }

    public boolean isReqSuccessFlag() {
        return reqSuccessFlag;
    }

    public void setReqSuccessFlag(boolean reqSuccessFlag) {
        this.reqSuccessFlag = reqSuccessFlag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<?> getRefunds() {
        return refunds;
    }

    public void setRefunds(List<?> refunds) {
        this.refunds = refunds;
    }

    public static class CredentialBean {
        /**
         * alipay_app : {"orderInfo":"_input_charset=\"utf-8\"&it_b_pay=\"2017-08-05 18:18:25\"&notify_url=\"https://www.paymax.cc/webservice/alipay_app\"&out_trade_no=\"ch_9c7796b140557bcc97c12a66\"&partner=\"2088121688433844\"&payment_type=\"1\"&seller_id=\"beijingheduikeji@163.com\"&service=\"mobile.securitypay.pay\"&subject=\"商品在线支付\"&total_fee=\"52.0\"&sign=\"PCsqBRq5266u5LxmUwH%2BHyY4ao%2BbMQl%2Baa2matGDJFwNNHNQj2K49z9Xs2SfwUJ1Z2egRYAl3uOH1P6qLP%2Be8MV3Z9d%2FmTgrhoX5jFCDgtIfj%2BtGLNXqRAfJnOiC9zf1Ql%2BLl1uM2GOgU3yoJN1CLJH8CBU%2F11kJO7mH%2BJ1EUeI%3D\"&sign_type=\"RSA\""}
         * wechat_app : {"sign":"D52DE07E8BC1D0FBEEC96AEB4AA37DB1","timestamp":1501841267,"noncestr":"imspwnxgfxw5rrfyjy9g246kb2xoq8r5","partnerid":"1324016301","prepayid":"wx20170804180747706130843e0302245553","package":"Sign=WXPay","appid":"wx5269eef08886e3d5"}
         */

        private AlipayAppBean alipay_app;
        private WechatAppBean wechat_app;

        public AlipayAppBean getAlipay_app() {
            return alipay_app;
        }

        public void setAlipay_app(AlipayAppBean alipay_app) {
            this.alipay_app = alipay_app;
        }

        public WechatAppBean getWechat_app() {
            return wechat_app;
        }

        public void setWechat_app(WechatAppBean wechat_app) {
            this.wechat_app = wechat_app;
        }

        public static class AlipayAppBean {
            /**
             * orderInfo : _input_charset="utf-8"&it_b_pay="2017-08-05 18:18:25"&notify_url="https://www.paymax.cc/webservice/alipay_app"&out_trade_no="ch_9c7796b140557bcc97c12a66"&partner="2088121688433844"&payment_type="1"&seller_id="beijingheduikeji@163.com"&service="mobile.securitypay.pay"&subject="商品在线支付"&total_fee="52.0"&sign="PCsqBRq5266u5LxmUwH%2BHyY4ao%2BbMQl%2Baa2matGDJFwNNHNQj2K49z9Xs2SfwUJ1Z2egRYAl3uOH1P6qLP%2Be8MV3Z9d%2FmTgrhoX5jFCDgtIfj%2BtGLNXqRAfJnOiC9zf1Ql%2BLl1uM2GOgU3yoJN1CLJH8CBU%2F11kJO7mH%2BJ1EUeI%3D"&sign_type="RSA"
             */

            private String orderInfo;

            public String getOrderInfo() {
                return orderInfo;
            }

            public void setOrderInfo(String orderInfo) {
                this.orderInfo = orderInfo;
            }
        }

        public static class WechatAppBean {
            /**
             * sign : D52DE07E8BC1D0FBEEC96AEB4AA37DB1
             * timestamp : 1501841267
             * noncestr : imspwnxgfxw5rrfyjy9g246kb2xoq8r5
             * partnerid : 1324016301
             * prepayid : wx20170804180747706130843e0302245553
             * package : Sign=WXPay
             * appid : wx5269eef08886e3d5
             */

            private String sign;
            private int timestamp;
            private String noncestr;
            private String partnerid;
            private String prepayid;
            @SerializedName("package")
            private String packageX;
            private String appid;

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }
        }
    }

    public static class ExtraBean {
        /**
         * user_id : app_9dk8yOrq1MUE7668
         */

        private String user_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
