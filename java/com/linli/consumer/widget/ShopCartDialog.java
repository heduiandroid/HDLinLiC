package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.ShopDialogAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tomoyo on 2016/11/8.
 * 购物车底下的按钮弹出，这个是已选择的列表
 */

public class ShopCartDialog extends Dialog {


    private CheckBox checkAll;
    private TextView numTv;
    private TextView clearAllTv;
    private boolean isLock = false;     //checkAll的锁，在item的checkbox状态发生后改变总checkbox状态，会调用总CheckBox的方法，锁住就不会调用



    private Context context;
    private long shopId;
    private RecyclerView recyclerView;
    private ShopDialogAdapter adapter;


    private ShopDetailBottomLayout bottomLayout;

    private List<GoodsBean> goodsDbList = new ArrayList<>();      //本地数据
    private DBUtil dbUtil ;
    private int type;
    private String room;
    private boolean isopened;
    private User user = AppContext.getInstance().getUser();


    public ShopCartDialog(Context context, long shopId,int type,String room,boolean isopened){
        this(context, R.style.widget_shop_cart_dialog);
        setCancelable(true);
        this.context = context;
        setCanceledOnTouchOutside(true);
        this.shopId = shopId;
        dbUtil =  DBUtil.getInstance(context);
        this.type = type;
        this.room = room;
        this.isopened = isopened;
    }
    private ShopCartDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        initView();

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
        adapter = new ShopDialogAdapter(context,goodsDbList,type);
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
                    if(goodsDbList.size() != 0){
                        for(int i = 0;i<goodsDbList.size();i++){
                            goodsDbList.get(i).setIsChoice(isChecked);
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

                if(goodsDbList.size() != 0){
                    for(int i = 0 ;i<goodsDbList.size();i++){      //清空本地数据库
                        dbUtil.deleteByGoodId(goodsDbList.get(i).getGoodId(),goodsDbList.get(i).getGoodsSpec());
                    }
                    goodsDbList.clear();
                    updateDialogView();
                    Toast.makeText(context,"购物车已经清空",Toast.LENGTH_SHORT).show();
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
                goodsDbList.clear();
            }
        });

        //对bottom的设置
        bottomLayout = (ShopDetailBottomLayout)findViewById(R.id.shop_cart_dialog_bottom);
        bottomLayout.setCartClickListener(new ShopDetailBottomLayout.OnCartClickListener() {
            @Override
            public void onCart() {
                ShopCartDialog.this.cancel();
            }
        });
        bottomLayout.setCommitClickListener(new ShopDetailBottomLayout.OnCommitClickListener() {
            @Override
            public void onCommit() {
                user = AppContext.getInstance().getUser();
                if(user != null){
                    if(Double.valueOf(bottomLayout.getPrice()) != 0){
                        UIHelper.togoShopFoodOrderActivity(context,0l,shopId,type,0l,room);
                    } else {
                        Toast.makeText(context,"请选择商品",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    UIHelper.togoLoginActivity(context);
                }


            }
        });
    }

    /**
     * 初始化数据
     * */

    private void initData(){
        //goodsDbList = dbUtil.queryByShopId(shopId); //查找，取参数
        goodsDbList.addAll(dbUtil.queryByShopId(shopId));

        if(goodsDbList.size() != 0 ){
            adapter.notifyDataSetChanged();
        }

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
        if (!isopened){
            bottomLayout.setSubmitButtonNotClick();
        }
        int sum = 0;
        double price = 0;
        if(goodsDbList.size() != 0){
            for(int i = 0 ; i <goodsDbList.size() ; i ++){
                if(goodsDbList.get(i).getIsChoice()){        //这里在选中状态下才计算总价格
                    sum = sum + goodsDbList.get(i).getNumber();
                    price = price + goodsDbList.get(i).getNumber()*goodsDbList.get(i).getPrice();

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
        for (int i =0;i<goodsDbList.size();i++){
            if(!goodsDbList.get(i).getIsChoice()){
                noCheck = noCheck +1;
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
        if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE ||
                !recyclerView.isComputingLayout()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            },200);
        }


    }

    /**
     * 关闭dialog的时候 最后更新数据库，通过checkBox的状态进行判断
     * */


    /**
     * 在onCreate方法后调用此方法，和activity一样
     * 在创建后，每次调用activity都会遍历查找是否有这个dialog的id，有的话，直接调用onStart
     * */
    @Override
    protected void onStart() {
        super.onStart();
        initData();
        updateDialogView();


    }

    /**
     * 在关闭后，清空list，每次进入都重新填充
     * */
    @Override
    protected void onStop() {
        super.onStop();
//        if(type == FOOD_MAIN){
//            List<Long> list = new ArrayList<>();
//            if(goodsDbList.size() > 0){
//                for(int i = 0 ; i < goodsDbList.size(); i ++){
//                    list.add(goodsDbList.get(i).getGoodId());
//                }
//            }
//            ((FoodDetailActivity)context).updateNumTv();
//        }
        goodsDbList.clear();

    }
}
