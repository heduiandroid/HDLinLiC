package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.adapter.ShopCarByFeedBackAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.FeedBackGoods;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.Product;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class FeedbackDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private TextView tv_head_name;//赋值店铺名称
    private RatingBar room_ratingbar;//店铺等级星级
    private ImageView iv_head_right;
    private SimpleDraweeView niv_shop_logo;
    private TextView tv_shop_name;//赋值店铺名称
    private TextView tv_shop_address;
    private ImageView iv_message;//go to message talking page
    private ImageView iv_call;//call the shop seller
    private LinearLayout ll_blacklist; // add to blacklist
    private ListView lv_recommends;
    private Button btn_buy_immediate;
    private ArrayList<Product> recommends = new ArrayList<Product>();
    private static ShopCarByFeedBackAdapter shopCarAdapter;//adapter
    public static BigDecimal checkedPrice = new BigDecimal("0.0");//选中条目总价 初始为0
    private Long voiceId;
    public static Long shopId;//商家id
    private Long shopUserId;//商家的userid
    private String areaCode;//商家所属地区码
    public static Integer category;//商家类型
    private LinearLayout btn_add_shopcart,btn_goto_shop;
    private AppContext appContext;
    private User user;
    public static String shopName;//商家名称
    private String shopLogo;//商家logo地址
    private Boolean istakeout = false;//如果是订餐类型 到下个页面需判断是否是外卖
    private Double beginGive = 0d;//如果是订餐类型 到下个页面需起送价
    private Double packing = 0d;//如果是订餐类型 到下个页面需打包费
    private Double thorn = 0d;//如果是订餐类型 到下个页面需送餐费
    private String phone;//商家电话 到下个界面需要商家电话
    private Integer shopLevel;//店铺等级
    private long regionId;//商户区域ID
    private String shopAddress;


    /**
     * 有可能是商品、订餐、服务 需要不同的显示界面，不同的购买及预约操作 根据后台参数去判断如何显示界面 如何操作
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback_detail;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        Intent intent = getIntent();
        voiceId = intent.getLongExtra("voiceid", 0);
        shopId = intent.getLongExtra("shopid", 0);
        shopUserId = intent.getLongExtra("shopuserid", 0);
        areaCode = intent.getStringExtra("areacode");
        shopName = intent.getStringExtra("shopname");
        shopLogo = intent.getStringExtra("shoplogo");
        phone = intent.getStringExtra("shopphone"); //电话
        category = intent.getIntExtra("category",0);//店铺类别   1商城   2美食    3服务
        shopLevel = intent.getIntExtra("level",0);
        regionId = intent.getLongExtra("regionid",0l);
        shopAddress = intent.getStringExtra("address");
        if (voiceId == 0 || shopId == 0){
            Toast.makeText(this,"参数错误",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            niv_shop_logo.setImageURI(shopLogo);
            tv_head_name.setText(shopName);
            tv_shop_name.setText(shopName);
            if (shopAddress != null){
                tv_shop_address.setText("地址："+shopAddress);
            }
            if (shopLevel != 0 || shopLevel != null){
                room_ratingbar.setNumStars(shopLevel);
            }else {
                room_ratingbar.setNumStars(1);
            }
            request_prods_datas();

        }
    }

    @Override
    protected void initData() {

    }

    private void request_prods_datas() {
        IntrestBuyNet.findListByVoiceId(voiceId, shopId, new HandleSuccess<FeedBackGoods>() {
            @Override
            public void success(FeedBackGoods s) {
                if (s.getStatus() == 1){
                    List<FeedBackGoods.DataBean> backProds = s.getData();
                    if (backProds != null || backProds.size() > 0){
                        for (int i = 0;i < backProds.size();i++){
                            FeedBackGoods.DataBean.HdMallStoreVoiceCartBean item = backProds.get(i).getHdMallStoreVoiceCart();
                            Product product = new Product();
                            product.setId(item.getGoodsId());
                            product.setShopCategory(item.getShopcartygory());
                            product.setShopName(item.getStoreName());
                            product.setShopCartID(item.getId());
                            product.setShopLogo(item.getStoreLogo());
                            product.setName(item.getName());
                            product.setPrice(item.getPrice());
                            product.setShopCartNum(item.getNum());
                            product.setPicPath(item.getProimg());
                            product.setSpecId(item.getGoodsSpecId());
                            product.setIsPackageCost(backProds.get(i).getIsPackageCost());      //这里加打包费: 美食0:没有  1:有   商城:默认0(int类型默认0)
                            recommends.add(product);
                        }
                    }
                    btn_add_shopcart.setVisibility(View.VISIBLE);
                    if (recommends.size()>0) {
                        shopCarAdapter = new ShopCarByFeedBackAdapter(recommends,FeedbackDetailActivity.this);
                        lv_recommends.setAdapter(shopCarAdapter);
                    }else {
                        Toast.makeText(FeedbackDetailActivity.this, "商家正在处理，稍后再看吧！", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(FeedbackDetailActivity.this,"商家正在处理，稍后再看吧！",Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void init() {
        findViewClick(R.id.iv_back);
        tv_head_name = findViewClick(R.id.tv_head_name);
        room_ratingbar = findView(R.id.room_ratingbar);
        iv_head_right = findViewClick(R.id.iv_head_right);
        iv_head_right.setImageResource(R.mipmap.icon_showmore);
        niv_shop_logo = findViewClick(R.id.niv_shop_logo);
        tv_shop_name =  findView(R.id.tv_shop_name);
        iv_message =  findViewClick(R.id.iv_message);
        iv_call = findViewClick(R.id.iv_call);
        ll_blacklist = findViewClick(R.id.ll_blacklist);
        lv_recommends = findView(R.id.lv_recommends);
        tv_shop_address = findView(R.id.tv_shop_address);
        lv_recommends.setOnItemClickListener(this);
        btn_buy_immediate = findViewClick(R.id.btn_buy_immediate);
        btn_buy_immediate.setOnClickListener(this);
        btn_add_shopcart = findViewClick(R.id.btn_add_shopcart);
        btn_goto_shop = findViewClick(R.id.btn_goto_shop);
        btn_add_shopcart.setOnClickListener(this);
    }

    @Override
    public void onHDClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.iv_message:
                //去聊天界面
                if (RongIM.getInstance() != null){
                    RongIM.getInstance().startPrivateChat(FeedbackDetailActivity.this,shopUserId.toString().trim(),shopName);
                }
                break;
            case R.id.iv_call:
                switch (category){//店铺类别   1商城   2美食    3服务
                    case 1:
                        IntrestBuyNet.findShopByshopId(shopId, new HandleSuccess<MallShopInfo>() {
                            @Override
                            public void success(MallShopInfo s) {
                                phone = s.getData().getMobilePhone();
                                UIHelper.callThePhoneNumber(FeedbackDetailActivity.this,phone);
                            }
                        });
                        break;
                    case 2:
                        IntrestBuyNet.findFoodShopByshopId(shopId, new HandleSuccess<StoreInfo>() {
                            @Override
                            public void success(StoreInfo s) {
                                phone = s.getData().getMobilePhone();
                                UIHelper.callThePhoneNumber(FeedbackDetailActivity.this,phone);
                            }
                        });
                        break;
                    case 3:
                        FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
                            @Override
                            public void success(ServiceStoreBean s) {
                                phone = s.getData().getMobilePhone();
                                UIHelper.callThePhoneNumber(FeedbackDetailActivity.this,phone);
                            }
                        });
                        break;
                    default:
                        break;
                }
                break;
            case R.id.ll_blacklist:
                request_add_blacklist();
                break;
            case R.id.iv_head_right:
                intent.putExtra("shopid",shopId);
                intent.putExtra("shopname",shopName);
                intent.putExtra("areacode",areaCode);
                intent.putExtra("category",category);
                intent.putExtra("regionid",regionId);
                intent.setClass(FeedbackDetailActivity.this,FeedBackDetailRightDialog.class);
                startActivity(intent);
                break;
            case R.id.btn_buy_immediate:
                ArrayList<Product> toPayList = new ArrayList<Product>();
                for (int i = 0;i<recommends.size();i++){
                    if (ShopCarByFeedBackAdapter.getIsSelected().get(i)){
                        toPayList.add(recommends.get(i));
                    }
                }
                if (toPayList.size() > 0){
                    DBUtil dbUtil =  DBUtil.getInstance(this);;
                    int cate = 0;
                    switch (category){
                        case 1:
                            cate = SHOP_MAIN;
                            break;
                        case 2:
                            cate = FOOD_MAIN;
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                    if (cate != 0){
                        //先删除该用户在商家所有购物车商品
                        dbUtil.deleteByShopId(shopId);
                        for (int i = 0;i< toPayList.size();i++){
                            GoodsBean good = new GoodsBean();
                            good.setGoodId(toPayList.get(i).getId());
                            good.setGoodsName(toPayList.get(i).getName());
                            good.setNumber(toPayList.get(i).getShopCartNum());
                            good.setPrice(toPayList.get(i).getPrice());
                            good.setImagePath(toPayList.get(i).getPicPath());
                            good.setShopId(shopId);
                            good.setShopName(shopName);
                            good.setIsChoice(true);
                            good.setType(cate);
                            if (toPayList.get(i).getSpecId() != null){
                                good.setGoodsSpec(toPayList.get(i).getSpecId());
                            }
                            if(cate == FOOD_MAIN){
                                good.setGoodsSpec(0);
                                good.setGoodsSpecName("");
                                good.setIspackagecost(toPayList.get(i).getIsPackageCost());
                            }
                            dbUtil.addGoodNum(good);
                        }
                        UIHelper.togoShopFoodOrderActivity(FeedbackDetailActivity.this,0,shopId,cate,0,null);
                        FeedbackDetailActivity.this.finish();
                    }else {
                        Toast.makeText(this,"店铺维护中，稍后再试吧~",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(this,"还没选择商品哦~",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_add_shopcart:
                //add to the shopping mall shopcart(may other operation)
                ArrayList<Product> addCartList = new ArrayList<Product>();
                for (int i = 0;i<recommends.size();i++){
                    if (ShopCarByFeedBackAdapter.getIsSelected().get(i)){
                        addCartList.add(recommends.get(i));
                    }
                }
                if (addCartList.size() > 0){
                    DBUtil dbUtil =  DBUtil.getInstance(this);
                    int cate = 0;
                    switch (category){
                        case 1:
                            cate = SHOP_MAIN;
                            break;
                        case 2:
                            cate = FOOD_MAIN;
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                    if (cate != 0){
                        for (int i = 0;i< addCartList.size();i++){
                            //要添加购物车商品 先删除购物车中此商品
                            if (cate == FOOD_MAIN){
                                dbUtil.deleteByGoodId(addCartList.get(i).getId(),0);
                            }else if (cate == SHOP_MAIN){
                                dbUtil.deleteByGoodId(addCartList.get(i).getId(),addCartList.get(i).getSpecId());
                            }
                            GoodsBean good = new GoodsBean();
                            good.setGoodId(addCartList.get(i).getId());
                            good.setGoodsName(addCartList.get(i).getName());
                            good.setNumber(addCartList.get(i).getShopCartNum());
                            good.setPrice(addCartList.get(i).getPrice());
                            good.setImagePath(addCartList.get(i).getPicPath());
                            good.setShopId(shopId);
                            good.setShopName(shopName);
                            good.setIsChoice(true);
                            good.setType(cate);
                            if (addCartList.get(i).getSpecId() != null){
                                good.setGoodsSpec(addCartList.get(i).getSpecId());
                            }
                            if(cate == FOOD_MAIN){
                                good.setGoodsSpec(0);
                                good.setGoodsSpecName("");
                                good.setIspackagecost(addCartList.get(i).getIsPackageCost());
                            }
                            dbUtil.addGoodNum(good);
                        }
                        Toast.makeText(this,"添加购物车成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"店铺维护中，稍后再试吧~",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(this,"还没选择商品哦~",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.niv_shop_logo:
            case R.id.btn_goto_shop:
                switch (category){
                    case 1:
                        UIHelper.togoShopDetailActivity(this,shopId,shopName,SHOP_MAIN);
                        break;
                    case 2:
                        UIHelper.togoShopDetailActivity(this,shopId,shopName,FOOD_MAIN);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    private void request_add_blacklist() {

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final ShopCarByFeedBackAdapter.ViewHolder holder = (ShopCarByFeedBackAdapter.ViewHolder)view.getTag();
        holder.cb.toggle();
        ShopCarByFeedBackAdapter.getIsSelected().put(position,holder.cb.isChecked());
        if (holder.cb.isChecked() == true){
            for (int i = 0;i < recommends.get(position).getShopCartNum();i++){
                checkedPrice = checkedPrice.add(BigDecimal.valueOf(recommends.get(position).getPrice()));
            }
        }else {
            for (int i = 0;i < recommends.get(position).getShopCartNum();i++){
                checkedPrice = checkedPrice.subtract(BigDecimal.valueOf(recommends.get(position).getPrice()));
            }
        }
        dataChanged();
    }

    /**
     * refesh the listview datas and reset the totalPrice
     */
    public static void dataChanged() {
        shopCarAdapter.notifyDataSetChanged();
    }
}
