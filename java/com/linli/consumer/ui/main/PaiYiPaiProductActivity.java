package com.linli.consumer.ui.main;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.SwipeCardAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.bean.GoodsDetailSingleBean;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.CookBook;
import com.linli.consumer.domain.Product;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.net.ShopNet;
import com.linli.consumer.widget.CarNumLayout;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class PaiYiPaiProductActivity extends BaseActivity {
    private TextView tv_commit;
    private ImageView iv_close;
    private ImageView iv_delete,iv_addcart,iv_show_cart;
    private CarNumLayout carNumLayout;

    private Long shopId = null;//店铺id
    private AppContext appContext;
    private User user;
    private ArrayList<GoodsBean> goods = new ArrayList<>();
    private SwipeFlingAdapterView sfav_products;
    private SwipeCardAdapter adapter ;
    private DBUtil dbUtil;
//    private AnimationDrawable deleteAnimation;
//    private AnimationDrawable addCartAnimation;
    private List<GoodsBean> goodsList;
    private Integer CATEGORY;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pai_yi_pai_product;
    }

    @Override
    protected void initView() {
        init();
        dismiss();
    }

    @Override
    protected void initData() {
        dbUtil =  DBUtil.getInstance(this);
        Intent intent = getIntent();
        CATEGORY = intent.getIntExtra("CATE",0);
        switch (CATEGORY){
            case FOOD_MAIN://1
                ArrayList<CookBook> foods = (ArrayList<CookBook>) getIntent().getSerializableExtra("foods");
                for (int i=0;i<foods.size();i++){
                    GoodsBean good = new GoodsBean();
                    good.setGoodId(foods.get(i).getId());
                    good.setGoodsSpecName("用料：主料【"+foods.get(i).getMaining()+"】    辅料【"+foods.get(i).getAccessories()+"】");
                    good.setGoodsName(foods.get(i).getName());
                    good.setNumber(1);
                    good.setPrice(foods.get(i).getPrice());
                    good.setImagePath(foods.get(i).getImgpath());
                    good.setShopId(foods.get(i).getShopId());
                    good.setShopName("");
                    good.setIsChoice(true);
                    good.setType(FOOD_MAIN);
                    goods.add(good);
                }
                break;
            case SHOP_MAIN://2
                ArrayList<Product> products = (ArrayList<Product>) intent.getSerializableExtra("products");
                for (int i=0;i<products.size();i++){
                    GoodsBean good = new GoodsBean();
                    good.setGoodId(products.get(i).getId());
                    good.setGoodsSpec(products.get(i).getSpecId());
                    good.setGoodsSpecName("");
                    good.setInventory(products.get(i).getResidueNum());
                    good.setGoodsName(products.get(i).getName());
                    good.setNumber(products.get(i).getShopCartNum());
                    good.setPrice(products.get(i).getPrice());
                    good.setImagePath(products.get(i).getPicPath());
                    good.setShopId(products.get(i).getShopId());
                    good.setShopName("");
                    good.setIsChoice(true);
                    good.setType(SHOP_MAIN);
                    goods.add(good);
                }
                break;
            default:
                break;
        }
        if (goods.size() > 0){
            //设置adapter
            shopId = goods.get(0).getShopId();
            adapter= new SwipeCardAdapter(goods,this);
            sfav_products.setAdapter(adapter);
            sfav_products.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
                private int removedNum;
                private GoodsBean good;
                @Override
                public void removeFirstObjectInAdapter() {
                    Log.d("LIST", "removed object!");
                    good = null;
                    good = goods.get(0);
                    removedNum = goods.get(0).getNumber();
                    goods.remove(0);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onLeftCardExit(Object o) {
                    goods.add(good);
                    adapter.notifyDataSetChanged();
                    startDeleteAnim();
                }

                @Override
                public void onRightCardExit(Object o) {
                    if (good != null){
                        //添加到购物车 成功之后改变布局
                        addToLocalCartAndUpdateView(good,removedNum);
                        startAddCartAnim();
                    }
                }

                @Override
                public void onAdapterAboutToEmpty(int i) {
                    // Ask for more data here
                    Log.d("PROLIST", "ask for more data here");
                }

                @Override
                public void onScroll(float scrollProgressPercent) {
                    try {
                        Log.i("test",scrollProgressPercent+"");
                        iv_show_cart.setAlpha(scrollProgressPercent > 0 ? 1-scrollProgressPercent : 1);//向右从1~0 向左始终为1
                        iv_addcart.setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);//向右从0~1  向左始终为0
                        iv_delete.setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);//向左从0~1  向右始终为0
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            sfav_products.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
                @Override
                public void onItemClicked(int i, Object o) {

                }
            });
        }
        if (goods.size() < 1 ){
            Toast.makeText(PaiYiPaiProductActivity.this,"没拍到商品哦，请重试~",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            getDataFromDB();//获取用户在该店铺的购物车商品数量 并显示
        }
    }
    private void addToLocalCartAndUpdateView(final GoodsBean good, final int removedNum) {
        switch (CATEGORY){
            case FOOD_MAIN:
                dbUtil.addGoodNum(good,removedNum);
                getDataFromDB();
                break;
            case SHOP_MAIN:
                ShopNet.findGoodsById(good.getGoodId(), new HandleSuccess<GoodsDetailSingleBean>() {
                    @Override
                    public void success(GoodsDetailSingleBean s) {
                        if (s.getData() != null){
                            GoodsDetailBean goodsDetailBean = s.getData();
                            String specName = goodsDetailBean.getGoodsSpecs().get(0).getSpecaValue();
                            dbUtil.changeGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,good.getGoodsSpec(),specName, BigDecimal.valueOf(good.getPrice()),good.getInventory()),removedNum);
                            getDataFromDB();
                        }else {
                            showToast();
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
    private void showToast(){
        Toast.makeText(PaiYiPaiProductActivity.this,"下手晚了，已经售罄",Toast.LENGTH_SHORT).show();
    }
    private void request_shop_info() {
        IntrestBuyNet.findShopByshopId(shopId, new HandleSuccess<MallShopInfo>() {
            @Override
            public void success(MallShopInfo s) {
                if (s.getStatus() == 1){
//                    good.setShopName(s.getData().getName());
                }else {
                    Toast.makeText(PaiYiPaiProductActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        getDataFromDB();
        Log.i("test","onResume runned");
        super.onResume();
    }

    private void init() {
        iv_close = findViewClick(R.id.iv_close);
        carNumLayout = findView(R.id.shop_detail_main_cart_num_widget);
        iv_delete = findView(R.id.iv_delete);
        iv_addcart = findView(R.id.iv_addcart);
        iv_show_cart = findViewClick(R.id.iv_show_cart);
//        iv_delete.setBackgroundResource(R.drawable.image_click_delanim);
//        iv_addcart.setBackgroundResource(R.drawable.image_click_addanim);
//        deleteAnimation = (AnimationDrawable)iv_delete.getBackground();
//        addCartAnimation = (AnimationDrawable)iv_addcart.getBackground();
        tv_commit = findViewClick(R.id.tv_commit);

        sfav_products = (SwipeFlingAdapterView) findViewById(R.id.sfav_products);
        startDeleteAnim();//初始设为全部透明
        startAddCartAnim();//初始设为全部透明
    }


    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_commit:
                if (user != null){
                    if (shopId != null){
                        switch (CATEGORY){
                            case FOOD_MAIN:
                                UIHelper.togoShopFoodOrderActivity(PaiYiPaiProductActivity.this, 0l, shopId, FOOD_MAIN, 0l,null);
                                break;
                            case SHOP_MAIN:
                                UIHelper.togoShopFoodOrderActivity(PaiYiPaiProductActivity.this, 0l, shopId, SHOP_MAIN, 0l,null);
                                break;
                            default:
                                break;
                        }
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
//            case R.id.iv_delete:
//                if (goods.size() > 0){
//                    sfav_products.getTopCardListener().selectLeft();
//                }
//                break;
//            case R.id.iv_addcart:
//                if (goods.size() > 0){
//                    sfav_products.getTopCardListener().selectRight();
//                }
//                break;
            case R.id.iv_show_cart:
                UIHelper.togoPaiYiPaiCart(this,goodsList);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("test","onRestart runned");
    }

    /**
     * 从数据库获取店铺购物车信息
     * */
    private void getDataFromDB(){
        goodsList = dbUtil.queryByShopId(shopId);
        int cartNum = 0;
        if(goodsList.size() != 0){
            for(int i = 0;i<goodsList.size();i++){
                cartNum = cartNum + goodsList.get(i).getNumber();
            }
            carNumLayout.setVisibility(View.VISIBLE);
            carNumLayout.setMyNum(cartNum+"");
            //中间下方结算按钮可点击 变成彩色 文字为 结算
            showAlert(true);
        }else {
            //中间下方结算按钮灰色不可点击，文字为 右划加入购物袋 / 左划切换下一条
            carNumLayout.setVisibility(View.INVISIBLE);
            showAlert(false);
        }
    }
    private int text = 0;//控制文字
    private Timer timer = new Timer();//控制文字闪烁
    private String s_left,s_right;
    private MyTimerTast taskcc = null;
    private void showAlert(boolean haveDatas) {
        if (haveDatas){//中间下方结算按钮可点击 变成彩色 文字为 结算
            s_left = "结 算";
            s_right = "结 算";
            tv_commit.setOnClickListener(PaiYiPaiProductActivity.this);
            tv_commit.setBackgroundResource(R.mipmap.btn_pyp_confirm);
            tv_commit.setTextSize(14);
        }else {//中间下方结算按钮灰色不可点击，文字为 右划加入购物袋 / 左划切换下一条
            s_left = "左划\n切换下一条";
            s_right = "右划\n加入购物袋";
            tv_commit.setOnClickListener(null);
            tv_commit.setBackgroundResource(R.mipmap.btn_pyp_confirm_alert);
            tv_commit.setTextSize(10);
        }
        if (taskcc != null){
            taskcc.cancel();
        }
        taskcc = new MyTimerTast();
        timer.schedule(taskcc, 1, 2000);// 参数分别是delay（多长时间后执行），duration（执行间隔）
    }
    private class MyTimerTast extends TimerTask{
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (text == 0) {
                        text = 1;
                        tv_commit.setText(s_right);
                    } else {
                        text = 0;
                        tv_commit.setText(s_left);
                    }
                }
            });
        }
    }
    private void startDeleteAnim(){
        iv_delete.setAlpha(0f);
//        iv_delete.setVisibility(View.GONE);
//        iv_delete.post(new Runnable() {
//            @Override
//            public void run() {
//                if (deleteAnimation.isRunning()){
//                    deleteAnimation.stop();
//                }
//                deleteAnimation.start();
//            }
//        });
    }
    private void startAddCartAnim(){
        iv_addcart.setAlpha(0f);
        iv_show_cart.setAlpha(1f);
//        iv_addcart.setVisibility(View.GONE);
//        iv_addcart.post(new Runnable() {
//            @Override
//            public void run() {
//                if (addCartAnimation.isRunning()){
//                    addCartAnimation.stop();
//                }
//                addCartAnimation.start();
//            }
//        });
    }
}
