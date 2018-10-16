package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopDialogAdapter1;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.FoodListBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.MallGoodsVo;
import com.linli.consumer.bean.ShopBeanV2;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.ShopNet;

import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;


/**
 * Created by tomoyo on 2016/11/8.
 * 购物车底下的按钮弹出，这个是已选择的列表
 */
@Deprecated
public class ShopCartDialog1 extends Dialog {


    private CheckBox checkAll;
    private TextView numTv;
    private TextView clearAllTv;
    private boolean isLock = false;     //checkAll的锁，在item的checkbox状态发生后改变总checkbox状态，会调用总CheckBox的方法，锁住就不会调用



    private Context context;
    private long shopId;
    private RecyclerView recyclerView;
    private ShopDialogAdapter1 adapter;

    //private List<NewsBean.ResultBean.DataBean > list = new ArrayList();

    private ShopDetailBottomLayout bottomLayout;

    private List<Object> goodsSerList = new ArrayList<>();  //商品的网络数据
    private List<GoodsBean> goodsDbList = new ArrayList<>();      //本地数据
    private DBUtil dbUtil ;

    private int type ;




    public ShopCartDialog1(Context context, long shopId,int type){
        this(context, R.style.widget_shop_cart_dialog);
        setCancelable(true);
        this.context = context;
        setCanceledOnTouchOutside(true);
        this.shopId = shopId;
        dbUtil =  DBUtil.getInstance(context);
        this.type = type;
    }
    private ShopCartDialog1(Context context, int theme) {
        super(context, theme);
    }



    //TODO summary:dialog在外部调用show才会调用onCreate()方法，new的话只调用构造方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        //initData();

    }

    /**
     * 初始化视图
     * */
    private void initView(){

        setContentView(R.layout.shop_cart_dialog_widget);
        //recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.shop_cart_rc);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new ShopDialogAdapter1(context,goodsSerList,true,type);
        recyclerView.setAdapter(adapter);

        //dialog的位置重新放置
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setGravity(Gravity.BOTTOM);

        //dialog上面的部分
        checkAll = (CheckBox)findViewById(R.id.shop_cart_dialog_main_all_cb);
        numTv = (TextView)findViewById(R.id.shop_cart_dialog_main_num_tv);
        clearAllTv = (TextView)findViewById(R.id.shop_cart_dialog_main_clear_tv);
        //设置选中监听 选中后更改数据的isShow标记，不对总队列进行修改，
        // 再调用handleData更新dialog视图，
        // 之后关闭dialog后自动会调用Activity的handleData更新视图，所以这里只调用自己的handleData
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isLock){
                    if(goodsSerList.size() != 0){
                        if(type == SHOP_MAIN){
                            if(isChecked){
                                for(int i = 0;i<goodsSerList.size();i++){
                                    ((MallGoodsVo)goodsSerList.get(i)).setShow(true);
                                }
                            }else {
                                for(int i = 0;i<goodsSerList.size();i++){
                                    ((MallGoodsVo)goodsSerList.get(i)).setShow(false);
                                }
                            }
                        } else if(type == FOOD_MAIN){
                            if(isChecked){
                                for(int i = 0;i<goodsSerList.size();i++){
                                    ((FoodListBean.DataBean)goodsSerList.get(i)).setShow(true);
                                }
                            }else {
                                for(int i = 0;i<goodsSerList.size();i++){
                                    ((FoodListBean.DataBean)goodsSerList.get(i)).setShow(false);
                                }
                            }
                        }

                        updateDialogView();
                    }
                }
            }
        });
        //清空购物车
        clearAllTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(goodsSerList.size() != 0){
                    if(type == SHOP_MAIN){
                        for(int i = 0 ; i < goodsSerList.size(); i ++){
                            ((MallGoodsVo)goodsSerList.get(i)).setShow(false);     //还原数据@Deprecated
                            ((MallGoodsVo)goodsSerList.get(i)).setNumber(0);
                        }
                        for(int i = 0 ;i<goodsSerList.size();i++){      //清空本地数据库
                            dbUtil.deleteByGoodId(((MallGoodsVo)goodsSerList.get(i)).getMallGoods().getId(),0);
                        }
                    }else if(type == FOOD_MAIN){
                        for(int i = 0 ; i < goodsSerList.size(); i ++){
                            ((FoodListBean.DataBean)goodsSerList.get(i)).setShow(false);     //还原数据@Deprecated
                            ((FoodListBean.DataBean)goodsSerList.get(i)).setNumber(0);
                        }
                        for(int i = 0 ;i<goodsSerList.size();i++){      //清空本地数据库
                            dbUtil.deleteByGoodId(((FoodListBean.DataBean)goodsSerList.get(i)).getId(),0);
                        }
                    }

                    goodsSerList.clear();   //清空网络数据， 更新视图
                    updateDialogView();
                }

            }
        });


        //cancel监听重写
        //加入了视图刷新的动作
        this.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                listener.onCancel();
                updateDialogView();
                whenCloseDialog();
            }
        });

        //对bottom的设置
        bottomLayout = (ShopDetailBottomLayout)findViewById(R.id.shop_cart_dialog_bottom);
        bottomLayout.setCartClickListener(new ShopDetailBottomLayout.OnCartClickListener() {
            @Override
            public void onCart() {
                ShopCartDialog1.this.cancel();
            }
        });
        bottomLayout.setCommitClickListener(new ShopDetailBottomLayout.OnCommitClickListener() {
            @Override
            public void onCommit() {
                //TODO 这里设置递交请求,not for
                for (int i = 0;i<goodsSerList.size();i++){
                    whenCloseDialog();
                    dbUtil.queryByShopId(shopId);
                    UIHelper.togoShopFoodOrderActivity(context,0l,shopId,type,0l,null);
                }

            }
        });

    }

    /**
     * 初始化数据
     * */
    //TODO 这里往下有很多type类型判断然后类型转换的语句
    //TODO 本来可以令两个实体类继承同一个父类，然后在此转型到父类，就不用判断了，但是不想
    //TODO 情不知所起，一往而深
    private void initData(){
        goodsDbList = dbUtil.queryByShopId(shopId); //查找，取参数
        if(goodsDbList.size() != 0 ){
            String ids = dbUtil.queryFoodsDetailFromSer(goodsDbList);        //参数
            //TODO 从数据库取，联网查
            Log.i("WATER",ids);
            if(type == SHOP_MAIN){
                ShopNet.goodsDetail(ids, new HandleSuccess<ShopBeanV2>() {
                    @Override
                    public void success(ShopBeanV2 shopBeanV2) {
                        //dbUtil.compareDataToDB(shopBeanV2.getData(),SHOP_GOODS_LIST,0,shopId);  //更新本地数据库数据，网络数据填充
                        for(int i = 0 ; i < shopBeanV2.getData().size(); i++){
                            goodsSerList.add(shopBeanV2.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        ((BaseActivity)context).dismiss();
                        //goodsDbList.clear();        //本地数据库数据list清空
                        //goodsDbList = dbUtil.queryByShopId(shopId);     //本地数据库数据list填充
                    }
                });
            } else if(type == FOOD_MAIN){
                //TODO
               /* FoodNet.queryFoodDetailByIds(ids, new HandleSuccess<FoodListBean>() {
                    @Override
                    public void success(FoodListBean foodListBean) {
                        //dbUtil.compareDataToDB(foodListBean.getData());  //更新本地数据库数据，网络数据填充
                        for(int i = 0 ; i < foodListBean.getData().size(); i++){
                            goodsSerList.add(foodListBean.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        ((BaseActivity)context).dismiss();
                        //goodsDbList.clear();        //本地数据库数据list清空
                        //goodsDbList = dbUtil.queryByShopId(shopId);     //本地数据库数据list填充
                        updateDialogView();
                    }
                });*/

                FoodNet.foodListOfSHop(shopId, 0, 1, 1000, new HandleSuccess<FoodListBean>() {
                    @Override
                    public void success(FoodListBean foodListBean) {
                        dbUtil.compareDataToDB(foodListBean.getData(),0,shopId,true);
                        for(int i = 0 ; i < foodListBean.getData().size(); i++){
                            goodsSerList.add(foodListBean.getData().get(i));
                        }
                        adapter.notifyDataSetChanged();
                        ((BaseActivity)context).dismiss();
                        updateDialogView();
                    }
                });
            }

        }
        //updateDialogView();

    }


    /**
     * cancel的回调，在其中加入了视图刷新的动作，所以要重新写监听
     * */
    public interface OnShopCancelListener{
        void onCancel();
    }
    private OnShopCancelListener listener;

    public void setListener(OnShopCancelListener listener) {
        this.listener = listener;
    }



    /**
     * 修改 添加 移除 商品实体类
     * 并更新底部数据(购物车中数量，总价，checkAll选中状态)
     * */
    /**
     * 随时更新 列表
     * (除了在适配器的onBindView里，这样会造成非法状态异常--在执行计算时刷新列表)
     * */
    /**
     * 将这个方法暴露给activity，让activity暴露给adapter
     * 用于更新视图
     * 只有在调用cancel方法 及 commit方法 时才对数据库进行操作
     * */
    public void updateDialogView(){
        int sum = 0;
        double price = 0;
        //goodsDbList.clear();
        //goodsDbList = dbUtil.queryByShopId(shopId);
        if(goodsSerList.size() != 0){
            if(type == SHOP_MAIN){
                for(int i = 0;i<goodsSerList.size();i++){
                    if(((MallGoodsVo)goodsSerList.get(i)).isShow()){       //这里在选中状态下才计算总价格
                        sum = sum + ((MallGoodsVo)goodsSerList.get(i)).getNumber();
                        price = price + ((MallGoodsVo)goodsSerList.get(i)).getNumber()*((MallGoodsVo)goodsSerList.get(i)).getMarketPrice().doubleValue();
                    }
                }
            }else if(type == FOOD_MAIN){
                for(int i = 0;i<goodsSerList.size();i++){
                    if(((FoodListBean.DataBean)goodsSerList.get(i)).isShow()){       //这里在选中状态下才计算总价格
                        sum = sum + ((FoodListBean.DataBean)goodsSerList.get(i)).getNumber();
                        price = price + ((FoodListBean.DataBean)goodsSerList.get(i)).getNumber()*((FoodListBean.DataBean)goodsSerList.get(i)).getPrice();
                    }
                }
            }

            bottomLayout.setCarNum(sum+"");
            bottomLayout.setPrice(price);
            numTv.setText("(已选"+sum+"件)");

        }else {
            bottomLayout.setDis();
            bottomLayout.setPrice(0);
            numTv.setText("(已选"+0+"件)");
        }

        //全选的判定标志
        int noCheck = 0;
        if(type == SHOP_MAIN){
            for (int i =0;i<goodsSerList.size();i++){
                if(!((MallGoodsVo)goodsSerList.get(i)).isShow()){
                    noCheck = noCheck +1;
                }
            }
        }else if(type == FOOD_MAIN){
            for (int i =0;i<goodsSerList.size();i++){
                if(!((FoodListBean.DataBean)goodsSerList.get(i)).isShow()){
                    noCheck = noCheck +1;
                }
            }
        }

        //设置是否全选
        if(noCheck != 0){
            isLock = true;
            checkAll.setChecked(false);
            isLock = false;
        }else {
            isLock = true;
            checkAll.setChecked(true);
            isLock = false;
        }

        //这里加延迟，在onBindView中调用时checkbox状态改变会报非法状态异常
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        },300);

    }

    /**
     * 关闭dialog的时候 最后更新数据库，通过checkBox的状态进行判断
     * */
    private void whenCloseDialog(){
        for(int i = 0;i< goodsSerList.size();i++){
            if(type == SHOP_MAIN){
                if(!((MallGoodsVo)goodsSerList.get(i)).isShow()){
                    dbUtil.deleteByGoodId(((MallGoodsVo)goodsSerList.get(i)).getMallGoods().getId(),0);
                }
            } else if(type == FOOD_MAIN){
                if(!((FoodListBean.DataBean)goodsSerList.get(i)).isShow()){
                    dbUtil.deleteByGoodId(((FoodListBean.DataBean)goodsSerList.get(i)).getId(),0);
                }
            }

        }
    }


    /**
     * 在onCreate方法后调用此方法，和activity一样
     * 在创建后，每次调用activity都会遍历查找是否有这个dialog的id，有的话，直接调用onStart
     * */
    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    /**
     * 在关闭后，清空list，每次进入都重新填充
     * */
    @Override
    protected void onStop() {
        super.onStop();
        goodsSerList.clear();
    }
}
