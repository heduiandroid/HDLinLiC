package com.linli.consumer.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.bean.AddVoice;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;

public class SpringboardActivity extends Activity {
    private AppContext appContext;
    private User user;
    private City city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_springboard);
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        city = appContext.getCity();
        String voiceName = getIntent().getStringExtra("voicename");
        request_uploadvoice_to_myserver(voiceName);
    }
    /**
     * sorry，以下的方法跟此Activity没有太大联系，只是用此Activity作为了跳板
     */
    private void request_uploadvoice_to_myserver(String name) {
        IntrestBuyNet.addUserVoice(0, user.getId(), name, new HandleSuccess<AddVoice>() {//0代表是语音
            @Override
            public void success(AddVoice s) {
                if (s.getStatus() == 1){
                    Toast.makeText(SpringboardActivity.this,"正在发送需求",Toast.LENGTH_SHORT).show();
                    request_submitsever_topushdata(s.getData().getId());
                }else {
                    finish();
                    Toast.makeText(SpringboardActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void request_submitsever_topushdata(long id) {
        IntrestBuyNet.jpush(10, 1, 3d, id, user.getId(), city.getLongitude(), city.getLatitude(),0, new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    finish();
                    Toast.makeText(SpringboardActivity.this,"发送成功，请等待反馈信息",Toast.LENGTH_SHORT).show();
                }else{
                    finish();
                    Toast.makeText(SpringboardActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
