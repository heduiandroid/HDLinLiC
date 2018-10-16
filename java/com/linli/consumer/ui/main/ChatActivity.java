package com.linli.consumer.ui.main;

import android.os.Bundle;
import android.app.Activity;

import com.linli.consumer.R;

public class ChatActivity extends Activity {
    public static ChatActivity activityInstance;
    String toChatUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        activityInstance = this;
        toChatUsername = getIntent().getExtras().getString("userId");
//        chatFragment = new ChatFragment();
    }

}
