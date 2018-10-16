package com.linli.consumer.ui.main;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.widget.ZoomableDraweeView;

public class ZoomableImageActivity extends BaseActivity {
    private ZoomableDraweeView zdv_image;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_zoomable_image;
    }

    @Override
    protected void initView() {
        zdv_image = findViewClick(R.id.zdv_image);
        findViewClick(R.id.iv_close);
    }

    @Override
    protected void initData() {
        Log.i("test",getIntent().getStringExtra("url"));
        zdv_image.setImageURI(getIntent().getStringExtra("url"));
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                finish();
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
