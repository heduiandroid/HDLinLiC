package com.linli.consumer.ui.main;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.adapter.SwipeCardAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.CookBook;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.IntrestBuyNet;
import com.linli.consumer.widget.CarNumLayout;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;

public class PaiYiPaiFoodsActivity extends BaseActivity {
    private Long shopId;
    private String sign;//舍签分类
    private String name;//菜名
    private String maining;//主料
    private String accessories;//辅料
    private String imgpath;//图片地址
    private Double price;//价格
    private String phone;
    private Double beginGive;
    private Double packing;
    private Double thorn;
    private AppContext appContext;
    private User user;
    private GoodsBean food;//美食bean
    private ArrayList<GoodsBean> goods = new ArrayList<>();
    private DBUtil dbUtil;
    private SwipeCardAdapter adapter ;
    private ImageView iv_close;
    private ImageView iv_delete,iv_addcart,iv_show_cart;
    private CarNumLayout carNumLayout;
    private SimpleDraweeView iv_pro_pic;
    private SwipeFlingAdapterView sfav_foods;
    private TextView tv_commit;
    private AnimationDrawable deleteAnimation;
    private AnimationDrawable addCartAnimation;
    private List<GoodsBean> goodsList;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pai_yi_pai_foods;
    }

    @Override
    protected void initView() {
        dismiss();
        init();
    }

    @Override
    protected void initData() {
        dbUtil =  DBUtil.getInstance(this);
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
        if (goods.size() > 0){
            //设置adapter
            shopId = goods.get(0).getShopId();
            adapter= new SwipeCardAdapter(goods,this);
            sfav_foods.setAdapter(adapter);
            sfav_foods.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
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
                        iv_show_cart.setAlpha(1f);
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
                        iv_show_cart.setAlpha(scrollProgressPercent > 0 ? 1- scrollProgressPercent : 1);//向右从1~0  向左始终为1
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            sfav_foods.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
                @Override
                public void onItemClicked(int i, Object o) {

                }
            });
        }
        if (goods.size() < 1 ){
            Toast.makeText(PaiYiPaiFoodsActivity.this,"没拍到菜品哦，请重试~",Toast.LENGTH_SHORT).show();
            finish();
        }else {
            getDataFromDB();//获取用户在该店铺的购物车商品数量 并显示
        }

//        if (id == 0 || shopId == 0){
//            Toast.makeText(PaiYiPaiFoodsActivity.this, "没拍到商品哦，请重试~", Toast.LENGTH_SHORT).show();
//            finish();
//        }else {
//            request_shop_info();
//            showPaiFood();
//        }
    }
    private void addToLocalCartAndUpdateView(final GoodsBean good, final int removedNum) {
        dbUtil.addGoodNum(good,removedNum);
        getDataFromDB();
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
            tv_commit.setOnClickListener(PaiYiPaiFoodsActivity.this);
            tv_commit.setBackgroundResource(R.mipmap.order_iv_food_details_oval);
            tv_commit.setTextSize(14);
        }else {//中间下方结算按钮灰色不可点击，文字为 右划加入购物袋 / 左划切换下一条
            s_left = "左划\n切换下一条";
            s_right = "右划\n加入购物袋";
            tv_commit.setOnClickListener(null);
            tv_commit.setBackgroundResource(R.mipmap.order_iv_food_details_oval);
            tv_commit.setTextSize(10);
        }
        if (taskcc != null){
            taskcc.cancel();
        }
        taskcc = new MyTimerTast();
        timer.schedule(taskcc, 1, 2000);// 参数分别是delay（多长时间后执行），duration（执行间隔）
    }
    private class MyTimerTast extends TimerTask {
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
    private void request_shop_info() {
        IntrestBuyNet.findFoodShopByshopId(shopId, new HandleSuccess<StoreInfo>() {
            @Override
            public void success(StoreInfo s) {
                if (s.getStatus() == 1){
                    food.setShopName(s.getData().getName());
                }else {
                    Toast.makeText(PaiYiPaiFoodsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void showPaiFood() {
//        tv_prod_name.setText(name);
//        tv_cailiao.setText("主料:"+maining+"\n"+"辅料:"+accessories);
//        tv_price.setText(price.toString());
//        iv_pro_pic.setImageURI(imgpath);
//    }

    private void init() {
        iv_close = findViewClick(R.id.iv_close);
        carNumLayout = findView(R.id.shop_detail_main_cart_num_widget);
        iv_delete = findViewClick(R.id.iv_delete);
        iv_addcart = findViewClick(R.id.iv_addcart);
        iv_show_cart = findViewClick(R.id.iv_show_cart);
        iv_delete.setBackgroundResource(R.drawable.image_click_delanim);
        iv_addcart.setBackgroundResource(R.drawable.image_click_addanim);
        deleteAnimation = (AnimationDrawable)iv_delete.getBackground();
        addCartAnimation = (AnimationDrawable)iv_addcart.getBackground();
        tv_commit = findViewClick(R.id.tv_commit);
        sfav_foods = (SwipeFlingAdapterView) findViewById(R.id.sfav_products);
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
                        UIHelper.togoShopFoodOrderActivity(PaiYiPaiFoodsActivity.this, 0l, shopId, FOOD_MAIN, 0l,null);
                    }
                }else {
                    UIHelper.togoLoginActivity(this);
                }
                break;
            case R.id.iv_delete:
                if (goods.size() > 0){
                    sfav_foods.getTopCardListener().selectLeft();
                }
                break;
            case R.id.iv_addcart:
                if (goods.size() > 0){
                    sfav_foods.getTopCardListener().selectRight();
                }
                break;
            case R.id.iv_show_cart:
                UIHelper.togoPaiYiPaiCart(this,goodsList);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        getDataFromDB();
        super.onResume();
    }
    private void startDeleteAnim(){
        iv_delete.post(new Runnable() {
            @Override
            public void run() {
                if (deleteAnimation.isRunning()){
                    deleteAnimation.stop();
                }
                deleteAnimation.start();
            }
        });
    }
    private void startAddCartAnim(){
        iv_addcart.post(new Runnable() {
            @Override
            public void run() {
                if (addCartAnimation.isRunning()){
                    addCartAnimation.stop();
                }
                addCartAnimation.start();
            }
        });
    }
}
