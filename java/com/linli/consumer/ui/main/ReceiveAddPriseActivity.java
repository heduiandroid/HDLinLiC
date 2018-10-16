package com.linli.consumer.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.adapter.EvalGoodsAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.Order;
import com.linli.consumer.domain.User;
import com.linli.consumer.myview.MyListView;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;

import io.rong.imkit.RongIM;

public class ReceiveAddPriseActivity extends BaseActivity {
    private SimpleDraweeView sdv_shoplogo;
    private TextView tv_shopname;
    private RatingBar room_ratingbar;
    private ImageView iv_talk,iv_call;
    private TextView tv_orderno;
    private MyListView mlv_prods;
    private Order order;
    private EvalGoodsAdapter adapter;
    private Long shopUserId;//店铺userId
    private String shopName;//店铺名称
    private String shopPhone;//店铺电话
    private AppContext appContext;
    private User user;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_receive_add_prise;
    }

    @Override
    protected void initView() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        findViewClick(R.id.iv_back);
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("点赞评价");
        TextView tv_head_right = findViewClick(R.id.tv_head_right);
        tv_head_right.setText("提交");
        ImageView iv = findView(R.id.iv_head_right);
        iv.setVisibility(View.GONE);
        sdv_shoplogo = findView(R.id.sdv_shoplogo);
        tv_shopname = findView(R.id.tv_shopname);
        room_ratingbar = findView(R.id.room_ratingbar);
        iv_talk = findViewClick(R.id.iv_talk);
        iv_call = findViewClick(R.id.iv_call);
        tv_orderno = findView(R.id.tv_orderno);
        mlv_prods = findView(R.id.mlv_prods);
    }

    @Override
    protected void initData() {
        order = (Order) getIntent().getSerializableExtra("order");
        tv_orderno.setText("订单号："+order.getId());
        adapter = new EvalGoodsAdapter(order.getProList(),this,order.getType(),user.getId(),order.getShopId(),order.getOrderNum());
        mlv_prods.setAdapter(adapter);
        switch (order.getType()){
            case 1:
            case 2:
                requset_foodshop_info();
                break;
            case 3:
                request_mallshop_info();
                break;
            case 4:
                request_serviceshop_info();
                break;
            default:
                request_serviceshop_info();
                break;
        }

    }

    private void request_serviceshop_info() {
        FoodNet.findServiceStoresInfos(order.getShopId(), new HandleSuccess<ServiceStoreBean>() {
            @Override
            public void success(ServiceStoreBean s) {
                if (s.getData() != null){
                    sdv_shoplogo.setImageURI(s.getData().getLogoImg());
                    shopName = s.getData().getName();
                    shopUserId = s.getData().getCompanyMemberId();
                    tv_shopname.setText(shopName);
                    Integer level = 0;
                    if (level != null){
                        room_ratingbar.setNumStars(level);
                    }else {
                        room_ratingbar.setNumStars(1);
                    }
                    shopPhone = s.getData().getMobilePhone();
                }
            }
        });
    }

    private void request_mallshop_info() {
        IntrestBuyNet.findShopByshopId(order.getShopId(), new HandleSuccess<MallShopInfo>() {
            @Override
            public void success(MallShopInfo s) {
                if (s.getStatus() == 1){
                    sdv_shoplogo.setImageURI(s.getData().getLogoImg());
                    shopName = s.getData().getName();
                    shopUserId = s.getData().getCompanyMemberId();
                    tv_shopname.setText(shopName);
                    Integer level = s.getData().getCreditLevel();
                    if (level != null){
                        room_ratingbar.setNumStars(level);
                    }else {
                        room_ratingbar.setNumStars(1);
                    }
                    shopPhone = s.getData().getMobilePhone();
                }else {
                    Toast.makeText(ReceiveAddPriseActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void requset_foodshop_info() {
        IntrestBuyNet.findFoodShopByshopId(order.getShopId(), new HandleSuccess<StoreInfo>() {
            @Override
            public void success(StoreInfo s) {
                if (s.getStatus() == 1){
                    sdv_shoplogo.setImageURI(s.getData().getLogoImg());
                    shopName = s.getData().getName();
                    shopUserId = s.getData().getCompanyMemberId();
                    tv_shopname.setText(shopName);
                    Integer level = s.getData().getCreditLevel();
                    if (level != null){
                        room_ratingbar.setNumStars(level);
                    }else {
                        room_ratingbar.setNumStars(1);
                    }
                    shopPhone = s.getData().getMobilePhone();
                }else {
                    Toast.makeText(ReceiveAddPriseActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                setResult(1801);
                finish();
                break;
            case R.id.tv_head_right://确认收货
               Toast.makeText(this,"感谢您的评价",Toast.LENGTH_SHORT).show();
                setResult(1801);
               finish();
                break;
            case R.id.iv_talk:
                if (RongIM.getInstance() != null){
                    RongIM.getInstance().startPrivateChat(ReceiveAddPriseActivity.this,shopUserId.toString().trim(),shopName);
                }
                break;
            case R.id.iv_call:
                Intent itcall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+shopPhone));
                startActivity(itcall);
                break;
            default:
                break;
        }
    }
}
