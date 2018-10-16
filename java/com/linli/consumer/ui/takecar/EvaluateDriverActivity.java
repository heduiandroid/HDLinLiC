package com.linli.consumer.ui.takecar;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.DriverInfoBean;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.TakeCarNeedBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

import java.text.DecimalFormat;

import io.rong.imkit.RongIM;

public class EvaluateDriverActivity extends BaseActivity {
    private SimpleDraweeView sdv_driver_portraint;
    private TextView tv_family_name,tv_car_infos,tv_driver_status;
    private RatingBar rb_driver_level,rb_stars;
    private EditText et_suggestion;
    private TextView tv_driver_persent;
    private Button btn_confirm;
    private AppContext appContext;
    private User user;
    private String driverPhone = null;
    private Long driverId = null;
    private String driverName = null;
    private String textEvaluate = "未填写";//文字评价内容
    private TakeCarNeedBean.DataBean takeCarNeed = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_evaluate_driver;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.iv_back);
        TextView header = findViewClick(R.id.tv_head_name);
        header.setText("我的行程");

        sdv_driver_portraint = findView(R.id.sdv_driver_portraint);
        tv_family_name = findView(R.id.tv_family_name);
        tv_car_infos = findView(R.id.tv_car_infos);
        tv_driver_status = findView(R.id.tv_driver_status);
        rb_driver_level = findView(R.id.rb_driver_level);
        rb_stars = findView(R.id.rb_stars);
        tv_driver_persent = findView(R.id.tv_driver_persent);
        findViewClick(R.id.iv_message_driver);
        findViewClick(R.id.iv_call_driver);
        et_suggestion = findView(R.id.et_suggestion);
        btn_confirm = findViewClick(R.id.btn_confirm);
    }

    @Override
    protected void initData() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        if (user != null) {
            rb_stars.setRating(5F);
            tv_driver_status.setText("已送达到目的地...");
            request_userneedlist_takecar();
        }else {
            finish();
        }
        dismiss();
    }

    private void request_userneedlist_takecar() {
        IntrestBuyNet.findByDemandId(user.getId(), 0L, new HandleSuccess<TakeCarNeedBean>() {
            @Override
            public void success(TakeCarNeedBean s) {
                if (s.getData() != null){
                     takeCarNeed = s.getData();
                }
                if (s.getData().getDriverId() != null || !s.getData().getDriverId().toString().equals("")){
                    driverId = s.getData().getDriverId();
                    request_driver_infos(driverId);
                }
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.iv_message_driver:
                if (driverId != null && !"".equals(driverId.toString())){
                    //去聊天界面
                    if (RongIM.getInstance() != null){
                        RongIM.getInstance().startPrivateChat(EvaluateDriverActivity.this,driverId.toString().trim(),driverName);
                    }
                }else {
                    showToast("暂无司机信息");
                }
                break;
            case R.id.iv_call_driver:
                if (driverPhone != null){
                    UIHelper.callThePhoneNumber(this,driverPhone);
                }else {
                    showToast("暂无司机信息");
                }
            case R.id.btn_confirm:
                if (user != null){
                    if (rb_stars.getRating() > 0F){
                        Log.i("test",rb_stars.getRating()+"");
                        if (!TextUtils.isEmpty(et_suggestion.getText())){
                            textEvaluate = et_suggestion.getText().toString();
                        }
                        request_evaluate_driver();
                    }else {
                        showToast("请做出星级评价");
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 评价司机接口
     */
    private void request_evaluate_driver() {
        if (takeCarNeed != null){
            IntrestBuyNet.evalutionAdd(takeCarNeed.getId(), 1, rb_stars.getRating(), textEvaluate, user.getId(), driverId, new HandleSuccess<Generic>() {
                @Override
                public void success(Generic s) {
                    EvaluateDriverActivity.this.finish();
                    showToast("评价已完成");
                }
            });
        }else {
            finish();
            showToast("评价已完成");
        }

    }

    /*
   查询司机信息
    */
    private void request_driver_infos(Long driverId) {
        IntrestBuyNet.findDriverById(driverId, new HandleSuccess<DriverInfoBean>() {
            @Override
            public void success(DriverInfoBean s) {
                if (s.getStatus() == 1 && s.getData() != null){
                    sdv_driver_portraint.setImageURI(s.getData().getImg());
                    tv_family_name.setText(s.getData().getSurname()+"师傅");
                    tv_car_infos.setText(s.getData().getLicensePlateNumber()+"  "+s.getData().getVehicleBrand()+"  "+s.getData().getVehicleColor());
                    driverPhone = s.getData().getPhone();
                    driverName = s.getData().getSurname()+"师傅";
                    Float driverPersent = 5F;
                    DecimalFormat df = new DecimalFormat("0.0");
                    if (s.getData().getAverage() != 0F){
                        driverPersent = s.getData().getAverage();
                    }
                    tv_driver_persent.setText(df.format(driverPersent)+"分");
                }
            }
        });
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        super.onResume();
    }
}
