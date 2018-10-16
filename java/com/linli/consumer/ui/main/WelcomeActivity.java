package com.linli.consumer.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
       /* Handler h = new Handler();

        h.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, YZXIndexActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);*/
        togoMain();
    }

    /**
     * 从欢迎页面跳转到主界面 / 引导页面
     * */
    private void togoMain(){
        final SharedPreferences sharedPreferences = getSharedPreferences("guide", Context.MODE_PRIVATE);
        final boolean isFirst = sharedPreferences.getBoolean("isFirst",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirst){        //判断是否第一次进入程序
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isFirst",false);
                    editor.apply();
                    UIHelper.togoGuideActivity(WelcomeActivity.this);
                } else {
                    UIHelper.togoYZXIndexActivity(WelcomeActivity.this);
                }
                finish();
            }
        },2000);

    }

}
