package com.linli.consumer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.linli.consumer.R;

public class NoInternetIfPaiPaiActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_if_pai_pai);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        Button btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoInternetIfPaiPaiActivity.this.finish();
            }
        });
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoInternetIfPaiPaiActivity.this,PaiYiPaiActivity.class));
                NoInternetIfPaiPaiActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}
