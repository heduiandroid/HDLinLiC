package com.linli.consumer.ui.main;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.common.Common;

public class WeChatShareActivity extends BaseActivity {
    private LinearLayout ll_wechat_friend,ll_wechat_friends;//朋友、朋友圈
    @Override
    protected int getLayoutId() {
        return R.layout.activity_we_chat_share;
    }

    @Override
    protected void initView() {
        findViewClick(R.id.ll);
        ll_wechat_friend = findViewClick(R.id.ll_wechat_friend);//朋友
        ll_wechat_friends = findViewClick(R.id.ll_wechat_friends);//朋友圈
    }

    @Override
    protected void initData() {
        dismiss();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.ll_wechat_friend:
                UIHelper.callWXShareWebPage(this, Common.downloadAppUrlWeb,1);//朋友
                finish();
                break;
            case R.id.ll_wechat_friends:
                UIHelper.callWXShareWebPage(this, Common.downloadAppUrlWeb,2);//朋友圈
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
