package com.linli.consumer.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.linli.consumer.R;

public class PaiYiPaiIntroduceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_yi_pai_introduce);
        init();
    }

    private void init() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tv = (TextView) findViewById(R.id.tv_head_name);
        tv.setText("功能介绍");
    }

}
