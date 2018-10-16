package com.linli.consumer.ui.shop_v2.shopdetail;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.widget.CarNumLayout;

/**
 * Created by tomoyo on 2016/12/10.
 * 商品详情页面
 * 里面包含两个fragment，一个原生，一个为webView
 * 包含部分操作，从数据库读取购物车信息
 */
@Deprecated
public class ShopDetailActivity extends BaseActivity {

    private ImageView backIm;       //返回
    private RelativeLayout cartLayout;      //标题的购物车按钮
    private CarNumLayout carNumLayout;      //标题购物车按钮上的小红点
    private LinearLayout enterShopLl;       //进入店铺
    private LinearLayout addCartLl;         //添加进购物车
    private RelativeLayout buyRl;           //立即购买


    private ShopDetailScrollFragment scrollFragment;        //原生fragment
    private ShopDetailWebFragment webFragment;              //web fragment
    private DragLayout dragLayout;                          //拖拽组件，用于在拖拽后初始化第二个fragment监听


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        dismiss();
        backIm = findViewClick(R.id.shop_detail_main_back_im);
        cartLayout = findViewClick(R.id.shop_detail_main_cart_rl);
        carNumLayout = findViewClick(R.id.shop_detail_main_cart_num_widget);
        enterShopLl = findViewClick(R.id.shop_detail_main_entershop_ll);
        addCartLl = findViewClick(R.id.shop_detail_main_addcart_ll);
        buyRl = findViewClick(R.id.shop_detail_main_buy);

        initFragment();
    }

    /**
     * 这里不进行数据请求，将数据请求放入fragment中处理
     * 这里进行数据库购物车信息的获取
     * */
    @Override
    protected void initData() {

    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.shop_detail_main_back_im:
                finish();
                break;
            case R.id.shop_detail_main_cart_rl:
                break;
            case R.id.shop_detail_main_cart_num_widget:
                break;
            case R.id.shop_detail_main_entershop_ll:
                //TODO 进入店铺
                break;
            case R.id.shop_detail_main_addcart_ll:
                break;
            case R.id.shop_detail_main_buy:
                break;
        }
    }

    /**
     * 初始化fragment
     * 设置拖拽监听
     * */
    private void initFragment(){
        scrollFragment = new ShopDetailScrollFragment();
        webFragment = new ShopDetailWebFragment();
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
    }

    private void addCart(){

        //TODO

    }
}
