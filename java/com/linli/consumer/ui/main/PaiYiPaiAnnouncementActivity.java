package com.linli.consumer.ui.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.domain.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class PaiYiPaiAnnouncementActivity extends Activity implements View.OnClickListener {
    private Button btn_toshopindex;
    private TextView tv_title,tv_create_time,tv_contents;
    private ProgressDialog mProgressDialog;
    private Product myProduct = new Product();
    private long shopid;//商家id
    private String title;//公告标题
    private String createtime;//公告创建时间
    private String contents;//公告详细
    private Integer type;//已经取到 不知有何用
    private int category;
    private Boolean istakeout;
    private String shopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_yi_pai_announcement);
        init();
        Intent intent = getIntent();
        shopid = intent.getLongExtra("shopid",0);
        title = intent.getStringExtra("title");
        category = intent.getIntExtra("category",0);
        contents = intent.getStringExtra("contents");
        shopName = intent.getStringExtra("shopname");
        if (shopid == 0 || category == 0){
            Toast.makeText(PaiYiPaiAnnouncementActivity.this, "没拍到公告哦，请重试~", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            show_announcement();
        }
    }
    private void init() {
        findViewById(R.id.sv_advert).setOnClickListener(this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_create_time = (TextView) findViewById(R.id.tv_create_time);
        tv_contents = (TextView) findViewById(R.id.tv_contents);
        btn_toshopindex = (Button) findViewById(R.id.btn_toshopindex);
        btn_toshopindex.setOnClickListener(this);
    }
    private void show_announcement() {
        tv_title.setText(title);
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        tv_create_time.setText(formatter.format(curDate));
        tv_contents.setText(contents);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sv_advert:
            case R.id.btn_toshopindex:
                Intent intent = new Intent();
                switch (category){
                    case SHOP_MAIN://订餐商家首页
                        UIHelper.togoShopDetailActivity(PaiYiPaiAnnouncementActivity.this,shopid,shopName,SHOP_MAIN);
                        break;
                    case FOOD_MAIN://商城商家首页
                        UIHelper.togoShopDetailActivity(PaiYiPaiAnnouncementActivity.this,shopid,shopName,FOOD_MAIN);
                        break;
                    case 3://服务商家首页
                        UIHelper.togoShopDetailActivity(PaiYiPaiAnnouncementActivity.this,shopid,shopName,SERVICE_MAIN);
                        break;
                    default:
                        break;
                }
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
