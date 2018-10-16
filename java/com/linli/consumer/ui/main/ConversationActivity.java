package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.utils.DemoContext;
import com.linli.consumer.utils.LocationCallbackTool;

import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.LocationMessage;

public class ConversationActivity extends FragmentActivity implements View.OnClickListener, RongIM.LocationProvider, RongIM.ConversationBehaviorListener {
    private TextView tv_head_name;
    private String userName;
    private String mTargetId;

    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        init();
        Intent intent = getIntent();
        getIntentDate(intent);
        isReconnect(intent);
    }

    private void init() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setOnClickListener(this);
        RongIM.setLocationProvider(this);
        RongIM.setConversationBehaviorListener(this);
    }
    /**
     * 展示如何从 Intent 中得到 融云会话页面传递的 Uri
     */
    private void getIntentDate(Intent intent) {
        userName = intent.getData().getQueryParameter("title");
        mTargetId = intent.getData().getQueryParameter("targetId");
        mTargetIds = intent.getData().getQueryParameter("targetIds");
        //intent.getData().getLastPathSegment();//获得当前会话类型
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));

        enterFragment(mConversationType, mTargetId);
        setActionBarTitle(userName);
    }
    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType
     * @param mTargetId
     */
    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        ConversationFragment fragment = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
    }
    /**
     * 设置 actionbar title
     */
    private void setActionBarTitle(String username) {

        tv_head_name.setText(username);
    }
    /**
     * 判断消息是否是 push 消息
     */
    private void isReconnect(Intent intent) {


        String token = null;

        if (DemoContext.getInstance() != null) {

            token = DemoContext.getInstance().getSharedPreferences().getString("DEMO_TOKEN", "default");
        }

        //push或通知过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("push") != null
                    && intent.getData().getQueryParameter("push").equals("true")) {

                reconnect(token);
            } else {
                //程序切到后台，收到消息后点击进入,会执行这里
                if (RongIM.getInstance() == null || RongIM.getInstance().getRongIMClient() == null) {

                    reconnect(token);
                } else {
                    enterFragment(mConversationType, mTargetId);
                }
            }
        }
    }
    /**
     * 重连
     *
     * @param token
     */
    private void reconnect(String token) {
        if (getApplicationInfo().packageName.equals(AppContext.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                }
                @Override
                public void onSuccess(String s) {
                    enterFragment(mConversationType, mTargetId);
                }
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                }
            });
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onStartLocation(Context context, LocationCallback callback) {
        LocationCallbackTool.mLastLocationCallback = callback;
        Intent intent = new Intent(context, MapLocationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
        // 消息点击事件，判断如果是位置消息就取出Content()跳转到地图activity
        if (message.getContent() instanceof LocationMessage) {
            Intent intent = new Intent(ConversationActivity.this, MapLocationActivity.class);
            intent.putExtra("location", message.getContent());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public boolean onMessageLinkClick(Context context, String s) {
        return false;
    }

    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }
}
