package com.linli.consumer.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;

public class PaiYiPaiTypeActivity extends Activity implements View.OnClickListener {
    private ImageView iv_product_ifchecked;
    private ImageView iv_adver_ifchecked;
    private ImageView iv_meeting_ifchecked;
    private ImageView iv_announcement_ifchecked;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_yi_pai_type);
        init();
        initViewStyleByPreferences();

    }

    private void initViewStyleByPreferences() {
        preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
        int type = preferences.getInt("paipro", 0);
        if (type == 1){
            iv_product_ifchecked.setImageResource(R.mipmap.imageview_checked);
        }
        type = preferences.getInt("paiadv",0);
        if (type == 1){
            iv_adver_ifchecked.setImageResource(R.mipmap.imageview_checked);
        }
        type = preferences.getInt("paimee",0);
        if (type == 1){
            iv_meeting_ifchecked.setImageResource(R.mipmap.imageview_checked);
        }
        type = preferences.getInt("paiann",0);
        if (type == 1){
            iv_announcement_ifchecked.setImageResource(R.mipmap.imageview_checked);
        }
    }

    private void init() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        TextView tv_head_name = (TextView) findViewById(R.id.tv_head_name);
        tv_head_name.setText("类别设置");
        tv_head_name.setOnClickListener(this);
        findViewById(R.id.ll_product).setOnClickListener(this);
        findViewById(R.id.ll_advertisement).setOnClickListener(this);
        findViewById(R.id.ll_meeting).setOnClickListener(this);
        findViewById(R.id.ll_announcement).setOnClickListener(this);
        iv_product_ifchecked = (ImageView) findViewById(R.id.iv_product_ifchecked);
        iv_product_ifchecked.setOnClickListener(this);
        iv_adver_ifchecked = (ImageView) findViewById(R.id.iv_adver_ifchecked);
        iv_adver_ifchecked.setOnClickListener(this);
        iv_meeting_ifchecked = (ImageView) findViewById(R.id.iv_meeting_ifchecked);
        iv_meeting_ifchecked.setOnClickListener(this);
        iv_announcement_ifchecked = (ImageView) findViewById(R.id.iv_announcement_ifchecked);
        iv_announcement_ifchecked.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int type;
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.iv_product_ifchecked:
            case R.id.ll_product:
                preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                type = preferences.getInt("paipro", 0);
                switch (type){
                    case 0:
                        iv_product_ifchecked.setImageResource(R.mipmap.imageview_checked);
                        SharedPreferences.Editor editor1=preferences.edit();
                        editor1.putInt("paipro", 1);
                        editor1.commit();
                        break;
                    case 1:
                        iv_product_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
                        SharedPreferences.Editor editor2=preferences.edit();
                        editor2.putInt("paipro", 0);
                        editor2.commit();
                        break;
                    default:
                        break;
                }
                break;
            case R.id.iv_adver_ifchecked:
            case R.id.ll_advertisement:
                preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                type = preferences.getInt("paiadv", 0);
                switch (type){
                    case 0:
                        iv_adver_ifchecked.setImageResource(R.mipmap.imageview_checked);
                        SharedPreferences.Editor editor1=preferences.edit();
                        editor1.putInt("paiadv", 1);
                        editor1.commit();
                        break;
                    case 1:
                        iv_adver_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
                        SharedPreferences.Editor editor2=preferences.edit();
                        editor2.putInt("paiadv", 0);
                        editor2.commit();
                        break;
                    default:
                        break;
                }
                break;
            case R.id.iv_meeting_ifchecked:
            case R.id.ll_meeting:
                preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                type = preferences.getInt("paimee", 0);
                switch (type){
                    case 0:
                        iv_meeting_ifchecked.setImageResource(R.mipmap.imageview_checked);
                        SharedPreferences.Editor editor1=preferences.edit();
                        editor1.putInt("paimee", 1);
                        editor1.commit();
                        break;
                    case 1:
                        iv_meeting_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
                        SharedPreferences.Editor editor2=preferences.edit();
                        editor2.putInt("paimee", 0);
                        editor2.commit();
                        break;
                    default:
                        break;
                }
                break;
            case R.id.iv_announcement_ifchecked:
            case R.id.ll_announcement:
                preferences = getSharedPreferences("userSetting", Context.MODE_PRIVATE);
                type = preferences.getInt("paiann", 0);
                switch (type){
                    case 0:
                        iv_announcement_ifchecked.setImageResource(R.mipmap.imageview_checked);
                        SharedPreferences.Editor editor1=preferences.edit();
                        editor1.putInt("paiann", 1);
                        editor1.commit();
                        break;
                    case 1:
                        iv_announcement_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
                        SharedPreferences.Editor editor2=preferences.edit();
                        editor2.putInt("paiann", 0);
                        editor2.commit();
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void clearChoose() {
        iv_product_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
        iv_adver_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
        iv_meeting_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
        iv_announcement_ifchecked.setImageResource(R.mipmap.imageview_unchecked);
    }
}
