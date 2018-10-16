package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;

public class SettingPaiYiPaiActivity extends Activity implements View.OnClickListener {
    private ImageView iv_sound_ifchecked;
    private boolean paiSound;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_pai_yi_pai);
        init();
        initViewStyleByPreferences();
    }

    private void initViewStyleByPreferences() {
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        paiSound = preferences.getBoolean("paisound", true);
        if (paiSound){
            iv_sound_ifchecked.setImageResource(R.mipmap.bt_commercial_index_yingye);
        }else {
            iv_sound_ifchecked.setImageResource(R.mipmap.bt_commercial_index_juling);
        }
    }

    private void init() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setText("设置");
        tv_head_name.setOnClickListener(this);
        iv_sound_ifchecked = (ImageView) findViewById(R.id.iv_sound_ifchecked);
        iv_sound_ifchecked.setOnClickListener(this);
        findViewById(R.id.ll_sound).setOnClickListener(this);
        findViewById(R.id.ll_set_type).setOnClickListener(this);
        findViewById(R.id.ll_introduce).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.iv_sound_ifchecked:
            case R.id.ll_sound:
                preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1=preferences.edit();
                if (paiSound){
                    iv_sound_ifchecked.setImageResource(R.mipmap.bt_commercial_index_juling);
                    editor1.putBoolean("paisound", false);
                    paiSound = false;
                }else {
                    iv_sound_ifchecked.setImageResource(R.mipmap.bt_commercial_index_yingye);
                    editor1.putBoolean("paisound", true);
                    paiSound = true;
                }
                editor1.commit();
                break;
            case R.id.ll_set_type:
                startActivity(new Intent(SettingPaiYiPaiActivity.this,PaiYiPaiTypeActivity.class));
                break;
            case R.id.ll_introduce:
                startActivity(new Intent(SettingPaiYiPaiActivity.this,PaiYiPaiIntroduceActivity.class));
                break;
            default:
                break;
        }
    }
}
