package com.linli.consumer.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.common.Common;

public class SongXiaobaoDownloadActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_song_xiaobao_download;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.ll);
        findViewClick(R.id.tv_download_songxiaobao);
    }

    @Override
    protected void initData() {
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.tv_download_songxiaobao:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(Common.downloadSongXiaobaoUrl);
                intent.setData(content_url);
                startActivity(intent);
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
