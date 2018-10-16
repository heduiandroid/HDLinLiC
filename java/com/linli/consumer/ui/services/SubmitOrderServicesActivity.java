package com.linli.consumer.ui.services;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.AddressInfoBean;
import com.linli.consumer.bean.CouponsBean;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.bean.ServiceOrderBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.ui.main.PaySuccessActivity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

public class SubmitOrderServicesActivity extends BaseActivity implements View.OnClickListener {
    private AppContext appContext;
    private User user;
    private TextView tv_default_address;
    private TextView tv_prod_price,tv_price;
    private TextView tv_order_time1,tv_order_time2;
    private TextView tv_order_period;//服务时长 周期
    private EditText et_remarks;
    private TextView tv_coupon_price;//显示当前所用的优惠券
    private Button btn_submit_service;
    private Long id;//服务项id
    private Long addrId = 0l;
    private String addressee;
    private String phone;
    private String address;
    private TextView tv_order_content;
    private LinearLayout ll_online_pay;
    private LinearLayout ll_offline_pay;
    private ImageView iv_offline;
    private ImageView iv_online;
    private boolean onLinePay = true;//是否线上支付  默认就是线上支付了//////////////////////////
    private ServiceGoodBean.DataBean service;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
    private java.util.Date nowDate = new java.util.Date();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_order_services;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        init();
    }

    @Override
    protected void initData() {
        //获取intent数据
        service = (ServiceGoodBean.DataBean) getIntent().getSerializableExtra("service");
        if (service != null) {
            request_orderneed_datas();
            request_datas();
            dismiss();
        }else {
            dismiss();
            finish();
            Toast.makeText(SubmitOrderServicesActivity.this,"参数错误",Toast.LENGTH_SHORT).show();
        }
    }

    private void request_datas() {
        request_defaultAddress();
    }

    private void request_orderneed_datas() {
        tv_order_content.setText(service.getStoreName()+"-"+service.getContent());
        tv_order_period.setText("未知");
        tv_prod_price.setText(service.getPrice()+"");
        String time = dateFormat.format(nowDate);
        String[] timeS = time.split("-");
        tv_order_time1.setText(timeS[0]);
        tv_order_time2.setText(timeS[1]);
    }
    private void init() {
        iv_online = findView(R.id.iv_online);
        iv_offline = findView(R.id.iv_offline);
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("预约详情");
        tv_order_time1 = findView(R.id.tv_order_time1);
        tv_order_time2 = findView(R.id.tv_order_time2);
        tv_order_period = findView(R.id.tv_order_period);
        tv_default_address = findView(R.id.tv_default_address);
        tv_price = findView(R.id.tv_price);
        tv_prod_price = findView(R.id.tv_prod_price);
        et_remarks = findView(R.id.et_remarks);
        tv_coupon_price = findView(R.id.tv_coupon_price);
        findViewClick(R.id.ll_default_addr);
        tv_order_content = findView(R.id.tv_order_content);
        ll_online_pay = findViewClick(R.id.ll_online_pay);
        ll_offline_pay = findViewClick(R.id.ll_offline_pay);
        findViewClick(R.id.ll_order_date);
        findViewClick(R.id.ll_service_time);
        btn_submit_service = findViewClick(R.id.btn_submit_service);
        findViewClick(R.id.ll_choose_coupon);
    }
    /**
     * 时间选择器的监听器
     * 如果选择了时间，就将时间赋给bean
     * 如果没有选择，就将初始事件(当前时间)赋给bean
     * */
    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(java.util.Date date)
        {
            String now = dateFormat.format(date);
            String[] nowS = now.split("-");
            tv_order_time1.setText(nowS[0]);
            tv_order_time2.setText(nowS[1]);
            nowDate = date;

        }
        @Override
        public void onDateTimeCancel() {
        }
    };
    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.ll_default_addr:
                UIHelper.togoAddressManageActivity(this);
                break;
            case R.id.ll_order_date:
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setIndicatorColor(Color.parseColor("#f98910"))
                        .setMinDate(nowDate)
                        .setIs24HourTime(true)
                        .setInitialDate(new java.util.Date())
                        .build()
                        .show();
                break;
            case R.id.ll_service_time:
                break;
            case R.id.btn_submit_service:
                if (addrId != 0){
                    showDialog();
                    Long couponId = null;
                    if (useingCoupon != null && useingCoupon.getId() != 0l){
                        couponId = useingCoupon.getId();
                    }
                    request_service_order_sub(couponId);
                }else {
                    showToast("请选择服务地址！");
                }
                break;
            case R.id.ll_online_pay:
                onLinePay = true;
                iv_online.setImageResource(R.mipmap.imageview_checked);
                iv_offline.setImageResource(R.mipmap.imageview_unchecked);
                break;
            case R.id.ll_offline_pay:
                onLinePay = false;
                iv_online.setImageResource(R.mipmap.imageview_unchecked);
                iv_offline.setImageResource(R.mipmap.imageview_checked);
                break;
            case R.id.ll_choose_coupon:
                if (allCoupons!= null && allCoupons.size()>0){
                    UIHelper.togoCashCouponActivity(this,allCoupons);
                }else {
                    showToast("暂无可用优惠券");
                }
                break;
            default:
                break;
        }
    }
    /*
    提交订单信息
     */
    private void request_service_order_sub(Long couponId) {
        String date = tv_order_time1.getText()+" "+tv_order_time2.getText();
        BigDecimal cps = null;
        if (couponSum.doubleValue() != 0d){
            cps = couponSum;
        }
        FoodNet.orderService(service.getId(), service.getStoreid(), addressee, phone, address, date,user.getId(), BigDecimal.valueOf(service.getPrice()),
                paySum,cps,couponId,new HandleSuccess<ServiceOrderBean>() {
            @Override
            public void success(ServiceOrderBean s) {
                if (s.getStatus() == 1){
                    if (s.getData() != null){
                        Long orderId = Long.valueOf(s.getData().getOrderSn());
                        UIHelper.togoOnLinePayActivity(SubmitOrderServicesActivity.this,
                                orderId
                                , s.getData().getOrderamount()
                                , s.getData().getOrderSn() + "", s.getData().getId());
                    }
                    finishCommit();


//                    showToast("服务预约成功！");
//                    UIHelper.togoOrderSuccessServiceActivity(SubmitOrderServicesActivity.this);
//                    SubmitOrderServicesActivity.this.finish();
                }else {
                    showToast("服务预约已满，请稍后再试");
                }
                dismiss();
            }
        });
    }

    private void finishCommit() {
        btn_submit_service.setText(getResources().getString(R.string.order_submited));
        btn_submit_service.setTextColor(getResources().getColor(R.color.gray));
        btn_submit_service.setOnClickListener(null);
        btn_submit_service.setClickable(false);
    }

    private void request_defaultAddress() {
        ShopNet.changeAddress(user.getId(), new HandleSuccess<AddressInfoBean>() {
            @Override
            public void success(AddressInfoBean s) {
                if (s.getData() != null){
                    addrId = s.getData().getId();
                    addressee = s.getData().getName();
                    phone = s.getData().getPhone();
                    address = s.getData().getAddress();
                    tv_default_address.setText(address+"\n"+addressee+"\n"+phone);
                }
                request_coupons();//看看有没有可用优惠券
            }
        });
    }
    private List<CouponsBean.DataBean> allCoupons = null;//所有可用优惠券
    private CouponsBean.DataBean useingCoupon = null;//正使用的优惠券
    private BigDecimal couponSum = new BigDecimal(0d);//优惠券金额
    private BigDecimal paySum;                          //需要支付的金额
    private void request_coupons() {
        IntrestBuyNet.queryStoreCouponByType(1, user.getId(),service.getStoreid(), 1,new HandleSuccess<CouponsBean>() {
            @Override
            public void success(CouponsBean s) {
                if (s.getData() != null && s.getData().size()>0){
                    allCoupons = s.getData();
                    useingCoupon = s.getData().get(0);
                }
                resetCouponViewDatas(useingCoupon);
                dismiss();
            }
        });
    }

    private void resetCouponViewDatas(CouponsBean.DataBean useingCoupon) {
        if (useingCoupon != null) {
            if (useingCoupon.getId() != 0l) {
                //找到优惠金额并显示
                couponSum = BigDecimal.valueOf(useingCoupon.getCouponSum());
                tv_coupon_price.setText(service.getStoreName() + couponSum.toString() + "元代金券");
            }else {
                couponSum = BigDecimal.valueOf(0d);
                tv_coupon_price.setText("不使用代金券");
            }
        }
        //计算需要支付的价格并显示
        paySum = BigDecimal.valueOf(service.getPrice()).subtract(couponSum);
        if (paySum.doubleValue() < 0d){
            paySum = BigDecimal.valueOf(0d);
        }
        tv_price.setText(paySum.toString());
        btn_submit_service.setText("立即支付 (￥"+paySum.toString()+")");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (resultCode == 1002){
                addrId = data.getLongExtra("id",0);
                addressee = data.getStringExtra("addressee");
                phone = data.getStringExtra("phone");
                address = data.getStringExtra("address");
                tv_default_address.setText(address+"\n"+addressee+"\n"+phone);
            }else if (requestCode == 1718){
                if (data != null) {
                    useingCoupon = (CouponsBean.DataBean) data.getSerializableExtra("ccp");
                    resetCouponViewDatas(useingCoupon);
                }
            }else if (resultCode == 1009){
                boolean success = data.getBooleanExtra("success",false);
                if (success){
                    request_set_payed_order();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void request_set_payed_order() {
        //已经付款成功 返回的刚刚成功的数据 可以去某个界面显示一下付款成功
        startActivity(new Intent(SubmitOrderServicesActivity.this, PaySuccessActivity.class));
    }
}
