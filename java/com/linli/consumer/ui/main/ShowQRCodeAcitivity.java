package com.linli.consumer.ui.main;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.utils.ZXingUtils;

public class ShowQRCodeAcitivity extends BaseActivity {
    private ImageView iv_coupon_qrcode;
    private Long couponId  = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_qrcode_acitivity;
    }

    @Override
    protected void initView() {
        iv_coupon_qrcode = findViewClick(R.id.iv_coupon_qrcode);
    }

    @Override
    protected void initData() {
        dismiss();
        couponId = getIntent().getLongExtra("couponid",0l);
        if (couponId != null){
            JSONObject couponInfo = new JSONObject();
            couponInfo.put("tag",5);
            couponInfo.put("couponId",couponId);
            Log.i("test",couponInfo.toString());
            load_image_qrcode(couponInfo.toString());
        }else {
            Toast.makeText(ShowQRCodeAcitivity.this,"当前优惠券暂不可使用",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void load_image_qrcode(String info) {
        Bitmap bitmap = ZXingUtils.createQRImage(info, 250, 250,null);
        iv_coupon_qrcode.setImageBitmap(bitmap);
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_coupon_qrcode:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
