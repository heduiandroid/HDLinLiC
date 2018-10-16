package com.linli.consumer.ui.shop_v2.shopdetail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailSingleBean;
import com.linli.consumer.bean.GoodsShopInfoBean;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.widget.CarNumLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

/**
 * Created by tomoyo on 2016/12/10.
 * 商品详情页面
 * 里面包含两个fragment，一个原生，一个为webView
 * 包含部分操作，从数据库读取购物车信息
 */

public class GoodsDetailActivity extends BaseActivity {

    private ImageView backIm;       //返回
    private LinearLayout backLl;    //返回键
    private RelativeLayout cartLayout;      //标题的购物车按钮
    private CarNumLayout carNumLayout;      //标题购物车按钮上的小红点
    private LinearLayout enterShopLl;       //进入店铺
    private TextView addCartLl;         //添加进购物车
    private TextView buyRl;           //立即购买


    private ShopDetailScrollFragment scrollFragment;        //原生fragment
    private ShopDetailWebFragment webFragment;              //web fragment
    private DragLayout dragLayout;                          //拖拽组件，用于在拖拽后初始化第二个fragment监听

    private long goodsId;       //商品id
    private GoodsDetailBean goodsDetailBean ;    //商品详情
    private int isIns;      //这里是从购物车 还是 立即购买   0:加入购物车    1:立即购买
    private int type = 0;       //类别

    private Long shopId = null;        //TODO 这里通过数据能拿到

    private DBUtil dbUtil;
    private List<GoodsBean> goodsList = new ArrayList<>();

    private String shopName;        //店铺名称

    private long specId;            //商品规格id
    private BigDecimal specPrice;       //商品价格
    private String specName;        //商品描述
    private int specInventory;      //商品库存

    private User user = AppContext.getInstance().getUser();



    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        goodsId  = intent.getLongExtra("goodsId",1);
        shopName = intent.getStringExtra("shopName");
        type = intent.getIntExtra("Sort",1);

        backIm = findViewClick(R.id.shop_detail_main_back_im);
        findViewClick(R.id.shop_detail_title_name_tv);
        backLl = findViewClick(R.id.shop_detail_main_back_ll);
        cartLayout = findViewClick(R.id.shop_detail_main_cart_rl);
        carNumLayout = findView(R.id.shop_detail_main_cart_num_widget);
        enterShopLl = findViewClick(R.id.shop_detail_main_entershop_ll);

        addCartLl = findViewClick(R.id.shop_detail_main_addcart_ll);
        buyRl = findViewClick(R.id.shop_detail_main_buy);
        enterShopLl.requestFocus();     //请求焦点
        addCartLl.requestFocus();
        //initFragment();
        dbUtil =  DBUtil.getInstance(this);

    }

    /**
     * 这里不进行数据请求，将数据请求放入fragment中处理
     * 这里进行数据库购物车信息的获取
     * */
    @Override
    protected void initData() {

        ShopNet.findGoodsById(goodsId, new HandleSuccess<GoodsDetailSingleBean>() {
            @Override
            public void success(GoodsDetailSingleBean goodsDetailSingleBean) {
                if(goodsDetailSingleBean.getData() != null ){
                    goodsDetailBean = goodsDetailSingleBean.getData();
                    if (goodsDetailBean.getIsRecommend()!= null&& goodsDetailBean.getIsRecommend() == 1){
                        goodsDetailBean.setName("[返券]"+goodsDetailBean.getName());
                    }
                    shopId = goodsDetailBean.getStoreId();
                    shopName = goodsDetailBean.getStoreName();
                    specId = 111L;
                    //specId = goodsDetailBean.getGoodsSpecs().get(0).getId();
                    specName = goodsDetailBean.getGoodsSpecs().get(0).getSpecaValue()+
                            goodsDetailBean.getGoodsSpecs().get(0).getSpecbValue()+
                            goodsDetailBean.getGoodsSpecs().get(0).getSpeccValue();
                    specPrice = goodsDetailBean.getGoodsSpecs().get(0).getPlatformPrice();
                    initFragment();
                    checkStoreStatus(shopId);//再查一下店铺状态 看有没有闭店
                    getDataFromDB();

                }
            }
        });
        //scrollFragment.refreshView(goodsId,specId);

        //TODO 根据id请求网络 mallGoodsVo

    }

    /**
     * 检查店铺信息
     * @param shopId
     */
    private void checkStoreStatus(long shopId) {
        ShopNet.shopInfo(shopId, new HandleSuccess<GoodsShopInfoBean>() {
            @Override
            public void success(GoodsShopInfoBean goodsShopInfoBean) {
                if(goodsShopInfoBean.getData() != null){
                    if (goodsShopInfoBean.getData().getOpenStatus()!=0){//!=0就是等于1   0开店  1闭店
                        buyRl.setOnClickListener(null);//按钮不可点击
                        buyRl.setBackgroundResource(R.drawable.btn_gray_deep);  //按钮背景变色
                        buyRl.setText("休息中");  //按钮更改文字
                    }
                }
                dismiss();
            }
        });
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.shop_detail_title_name_tv:
            case R.id.shop_detail_main_back_im:     //返回
                finish();
                break;
            case R.id.shop_detail_main_cart_rl:     //跳转到购物车
                UIHelper.togoShopDetailActivityFromCart(GoodsDetailActivity.this,shopId,shopName,SHOP_MAIN);//跳转店铺并弹出店铺购物车
//                UIHelper.togoCartActivity(this);//这里不跳总购物车了
                break;
            case R.id.shop_detail_main_entershop_ll:        //跳转到店铺主页
                if(goodsDetailBean != null ){
                    UIHelper.togoShopDetailActivity(this,goodsDetailBean.getStoreId()
                            ,goodsDetailBean.getStoreName(),SHOP_MAIN);
                } else {
                    Toast.makeText(this,"网络繁忙，请重试...",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.shop_detail_main_addcart_ll:          //添加到购物车,数据库数据增加，视图改变
                addToCartNow();
                break;
            case R.id.shop_detail_main_buy:
                buyNow();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = AppContext.getInstance().getUser();
        if (shopId != null) {
            getDataFromDB();
        }
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               scrollFragment.refreshView(goodsId,specId);
            }
        },500);*/
        //scrollFragment.refreshView(goodsId,specId);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        if(dbUtil.queryByGoodId(goodsId,specId) != null){
//            scrollFragment.refreshView(goodsId,specId);
//        } else {
//            scrollFragment.refreshView(goodsId,111L);
//        }
        scrollFragment.refresh();
    }

    /**
     * 初始化fragment
     * 设置拖拽监听
     * */
    private void initFragment(){
        if(goodsDetailBean != null){
            scrollFragment = ShopDetailScrollFragment.newInstance(goodsDetailBean);
            webFragment = ShopDetailWebFragment.newInstance(goodsDetailBean.getInfo());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.shop_detail_main_scroll_fl,scrollFragment)
                    .add(R.id.shop_detail_main_web_fl,webFragment)
                    .commit();
            dragLayout = findView(R.id.shop_detail_main_draglayout);
            DragLayout.ShowNextPageNotifier notifier = new DragLayout.ShowNextPageNotifier() {
                @Override
                public void onDragNext() {
                    webFragment.fillData();
                }
            };
            dragLayout.setNextPageListener(notifier);
            //scrollFragment.refreshView(goodsId,specId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == UIHelper.GOODS_SPEC_REQUEST_CODE){
            specId = data.getLongExtra("specId",111);
            specName = data.getStringExtra("specName");
            specPrice = BigDecimal.valueOf(Double.valueOf(data.getStringExtra("specPrice")));
            specInventory = data.getIntExtra("specInventory",0);
            if(specId != 111L){
                if(dbUtil.queryByGoodId(goodsId,specId) != null){

                    scrollFragment.refresh();
                }
            }
        }
    }

    /**
     * 加入购物车
     * 1, 点击 立即购买
     * 2, 在 选择规格后*/
    private void addToCartNow(){
        if(user == null){                   //判断是否登录
            UIHelper.togoLoginActivity(this);
        }else {
            if (goodsDetailBean != null) {        //商品不为0
                List<GoodsBean> listGoodsbean = dbUtil.queryByShopId(shopId);
                if (listGoodsbean != null && listGoodsbean.size() > 0) {
                    UIHelper.togoShopFoodOrderActivity(GoodsDetailActivity.this, 0l, shopId, SHOP_MAIN, 0l, null);
                } else {
                    UIHelper.togoGoodsSpecActivity(this, goodsDetailBean, 0);
                    showToast("先添加到购物车吧");
                }
            } else {
                Toast.makeText(this, "网络繁忙，请重试...", Toast.LENGTH_SHORT).show();
            }
        }
    }
    /**
     * 立即购买*/
    private void buyNow(){
        if(user == null){                   //判断是否登录
            UIHelper.togoLoginActivity(this);
        } else {
            List<GoodsBean> listGood = dbUtil.queryByGoodId(goodsId);
            if (listGood != null && listGood.size() > 0){
                UIHelper.togoShopFoodOrderActivity(this,goodsId,shopId,SHOP_MAIN,specId,null);
            }else {
                UIHelper.togoGoodsSpecActivity(this,goodsDetailBean,0);
            }


//            if(goodsDetailBean != null){    //判断是否有数据
//                if(specId == 111L){         //判断是否有规格
//                    UIHelper.togoGoodsSpecActivity(this,goodsDetailBean,1);
//                } else {
//                    if(dbUtil.queryByGoodId(goodsId,specId) != null){
//                        goodsDetailBean.setNumber(goodsDetailBean.getNumber()+1);
//                    } else {
//                        dbUtil.addGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,specId,specName,specPrice,specInventory));
//                    }
//                    getDataFromDB();        //改变视图，小圆点加数据
//                    int num = dbUtil.queryByGoodId(goodsId,specId).getNumber();
//                    if(num <= specInventory && num >0){
//                        UIHelper.togoShopFoodOrderActivity(this,goodsId,shopId,SHOP_MAIN,specId,null);
//                    } else {
//                        Toast.makeText(this,"库存不足",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }else {
//                Toast.makeText(this,"网络繁忙，请重试...",Toast.LENGTH_SHORT).show();
//            }
        }
    }

    /**
     * 从数据库获取购物车信息
     *
     * 两处调用
     * 初始化数据    {@linkplain #initView()}
     * 跳转后同步数据  {@linkplain #onPause()}
     * */
    private void getDataFromDB(){
        goodsList = dbUtil.queryByShopId(shopId);
        int sum = 0;
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                sum = sum + goodsList.get(i).getNumber();
            }
            carNumLayout.setVisibility(View.VISIBLE);
            carNumLayout.setMyNum(sum+"");
        }else {
            carNumLayout.setVisibility(View.INVISIBLE);
        }
    }
}
