package com.linli.consumer.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.FoodNet;

import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;

public class PaiYiPaiServiceActivity extends Activity implements View.OnClickListener {
    private Button btn_toshopindex,btn_topay;
    private TextView tv_price;
    private TextView tv_prod_name;
    private ImageView iv_pro_pic;
    private ImageView iv_close;

    private long id;//商品id
    private long shopId;//店铺id
    private String proName;//商品名称
    private Double price;//商品价格
    private String imgSrc;//图片路径
    private AppContext appContext;
    private User user;
//    private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pai_yi_pai_service);
        init();
        initImageOptions();
        Intent intent = getIntent();
        id = intent.getLongExtra("id", 0);
        shopId = intent.getLongExtra("shopid", 0);
        proName = intent.getStringExtra("name");
        price = intent.getDoubleExtra("price", 0d);
        imgSrc = intent.getStringExtra("imgsrc");
        if (shopId == 0 || id == 0){
            Toast.makeText(PaiYiPaiServiceActivity.this, "没拍到商品哦，请重试~", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            showPaiProduct();
        }
    }
    private void init() {
        iv_close = (ImageView) findViewById(R.id.iv_close);
        btn_toshopindex = (Button) findViewById(R.id.btn_toshopindex);
//        btn_topay = (Button) findViewById(R.id.btn_topay);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_prod_name = (TextView) findViewById(R.id.tv_prod_name);
        iv_pro_pic = (ImageView) findViewById(R.id.iv_pro_pic);
        iv_close.setOnClickListener(this);
        iv_pro_pic.setOnClickListener(this);
        btn_toshopindex.setOnClickListener(this);
//        btn_topay.setOnClickListener(this);
    }
    private void initImageOptions() {
//        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.default_background)//设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.mipmap.default_background)//设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.mipmap.default_background)//设置图片加载/解码过程中错误时候显示的图片
//                .cacheInMemory(false)//设置下载的图片是否缓存在内存中
//                .cacheOnDisk(false)// 设置下载的资源是否缓存在SD卡中
//                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以何种编码方式显示
//                .bitmapConfig(Bitmap.Config.RGB_565) // 设置图片的解码类型
//                .build();
    }
    private void showPaiProduct() {
        tv_prod_name.setText(proName);
        tv_price.setText(price.toString());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.iv_close:
                finish();
                break;
            case R.id.btn_toshopindex:
                FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
                    @Override
                    public void success(ServiceStoreBean s) {
                        if (s.getData() != null){
                            UIHelper.togoShopDetailActivity(PaiYiPaiServiceActivity.this,shopId,s.getData().getName(),SERVICE_MAIN);
                        }
                    }
                });
                break;
            case R.id.iv_pro_pic:
//            case R.id.btn_topay:
//                if (user != null){
//                    intent.putExtra("cate",3);
//                    intent.putExtra("shopid", shopId);
//                    ArrayList<Product> toPayList = new ArrayList<Product>();
//                    Product product = new Product();
//                    product.setId(id);
//                    product.setPicPath(imgSrc);
//                    product.setName(proName);
//                    product.setPrice(price);
//                    product.setShopCartNum(1);
//                    product.setShopName("服务类");
//                    toPayList.add(product);
//                    intent.putExtra("paylist", (Serializable) toPayList);
//                    intent.putExtra("checkedprice",price);
//                    intent.setClass(this, BeforeOrderConfirmActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    startActivity(new Intent(PaiYiPaiServiceActivity.this,LoginYZXActivity.class));
//                }
//                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        super.onResume();
    }
}
