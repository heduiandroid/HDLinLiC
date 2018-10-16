package com.linli.consumer.ui.cookbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.domain.OrderHealthWay;

/**
 * 养生道详情展示
 */
public class OrderHealthWayDetailsActivity extends Activity implements View.OnClickListener {
    private ImageView iv_order_health_back;
    private ImageView iv_order_health_background;
    private TextView tv_order_healthway_title,tv_order_healthway_content,tv_tag;
    private OrderHealthWay orderHealthWay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_health_way_details);

        orderHealthWay = (OrderHealthWay) getIntent().getSerializableExtra("healthyway");
        init();
        iv_order_health_back.setOnClickListener(this);
        tv_tag.setText("标签："+orderHealthWay.getContent());
        tv_order_healthway_content.setText(orderHealthWay.getLead());
        tv_order_healthway_title.setText(orderHealthWay.getName());
        setBackground();
    }

    private void setBackground() {
        iv_order_health_background.setImageResource(orderHealthWay.getImgpath());
//        if (orderHealthWay.getImgpath()!=null){
//            holder.iv_info_pic.setImageUrl(list.get(position).getImagePath(), imageLoader);
//            holder.iv_info_pic.setDefaultImageResId(R.mipmap.background_businessinfo);
//            holder.iv_info_pic.setErrorImageResId(R.mipmap.bg_businessinfo);
//        }
    }

    public void init() {
        iv_order_health_back = (ImageView) findViewById(R.id.iv_order_health_back);
        iv_order_health_background = (ImageView) findViewById(R.id.iv_order_health_background);
        tv_order_healthway_content = (TextView) findViewById(R.id.tv_order_healthway_content);
        tv_order_healthway_title = (TextView) findViewById(R.id.tv_order_healthway_title);
        tv_tag = (TextView) findViewById(R.id.tv_tag);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_order_health_back) {
            finish();
        }
    }
}
