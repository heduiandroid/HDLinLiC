package com.linli.consumer.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.ShoppingProAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.bean.SearchFood;
import com.linli.consumer.bean.SearchGood;
import com.linli.consumer.bean.ServiceGoodBean;
import com.linli.consumer.bean.ServiceStoreBean;
import com.linli.consumer.bean.StoreInfo;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.Product;
import com.linli.consumer.net.FoodNet;
import com.linli.consumer.net.IntrestBuyNet;
import java.util.ArrayList;
import java.util.List;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SERVICE_MAIN;

public class SearchResultsActivity extends BaseActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private ListView lv_search_results;
    private TextView tv_nodata;
    private ArrayList<Product> foods = new ArrayList<Product>();//美食产品
    private ArrayList<Product> shoppingProducts = new ArrayList<Product>();//商城的产品
    private ArrayList<Product> services = new ArrayList<Product>();//服务的产品
    private ShoppingProAdapter adapter;//商城产品adapter；还需服务产品Adapter、美食产品Adapter、美食产品Adapter、联系人Adapter
    private int searchType;
    private String searchText;
    private AppContext appContext;
    private City city;
    private int pager = 1;
    private Boolean istakeout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_results;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {
        appContext = (AppContext) getApplicationContext();
        city = appContext.getCity();
        searchType = getIntent().getIntExtra("searchType",0);
        searchText = getIntent().getStringExtra("searchText");
        if (searchType == 0){
            Toast.makeText(this,"搜索结果异常，稍后再试",Toast.LENGTH_SHORT).show();
        }else {
            requestByType();
        }
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            default:
                break;
        }
    }

    private void requestByType() {
        switch (searchType){
            case 1://搜美食
                request_food_searched();
                break;
            case 2://搜商品
                request_good_searched();
                break;
            case 3://搜服务
                request_service_searched();
                break;
            default:
                break;
        }
    }

    private void request_service_searched() {
        FoodNet.findServiceProducts(searchText,3,city.getLongitude(),city.getLatitude(),pager,10, new HandleSuccess<ServiceGoodBean>() {
            @Override
            public void success(ServiceGoodBean s) {
                if (s.getStatus() == 1){
                    ArrayList<Product> newItems = new ArrayList<Product>();
                    newItems.clear();
                    List<ServiceGoodBean.DataBean> goodList = s.getData();
                    for (int i = 0;i < goodList.size();i++){
                        ServiceGoodBean.DataBean item = goodList.get(i);
                        Product product = new Product();
                        product.setId(item.getId());
                        product.setName(item.getName());
                        product.setPicPath(item.getImg());
                        product.setPicPath1(item.getImg1());
                        product.setPicPath2(item.getImg2());
                        product.setPicPath3(item.getImg3());
                        product.setShopId(item.getStoreid());
                        product.setDetailsText(item.getContent());
                        product.setPrice(item.getPrice());
                        product.setShopCategory(3);//3是服务项产品的意思
                        newItems.add(product);
                        shoppingProducts.add(product);
                    }
                    if (pager == 1){
                        if (shoppingProducts.size()>0) {
                            //if  have datas
                            lv_search_results.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            adapter = new ShoppingProAdapter(shoppingProducts,SearchResultsActivity.this);
                            lv_search_results.setAdapter(adapter);
                            pager++;
                        }else {
                            //if have no datas
                            lv_search_results.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                            tv_nodata.setText("抱歉，暂无相关服务。");
                        }
                    }else {
                        if (newItems.size()>0){
                            adapter.notifyDataSetChanged();
                            pager++;
                        }
                    }
                }else {
                    Toast.makeText(SearchResultsActivity.this,"抱歉，暂无相关服务。",Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void request_good_searched() {
        IntrestBuyNet.findNearGoodsByName(10, pager, searchText, 3, city.getLatitude(), city.getLongitude(), new HandleSuccess<SearchGood>() {
            @Override
            public void success(SearchGood s) {
                if (s.getStatus() == 1){
                    ArrayList<Product> newItems = new ArrayList<Product>();
                    newItems.clear();
                    List<SearchGood.DataBean> goodList = s.getData();
                    for (int i = 0;i < goodList.size();i++){
                        SearchGood.DataBean item = goodList.get(i);
                        Product product = new Product();
                        product.setId(item.getId());
                        product.setName(item.getName());
                        product.setPicPath(item.getPrimaryImage());
                        product.setPicPath1(item.getProductImage1());
                        product.setPicPath2(item.getProductImage2());
                        product.setPicPath3(item.getProductImage3());
                        List<SearchGood.DataBean.GoodsSpecsBean> specs = item.getGoodsSpecs();
                        if (specs.size() > 0){
                            product.setPrice(specs.get(0).getPlatformPrice());//赋值第一个价格
                            for (int j = 0;j < specs.size();j++){//跟第一个/上一个做对比
                                if (specs.get(j).getPlatformPrice() < product.getPrice()){//如果小于第一个/上一个
                                    product.setPrice(specs.get(j).getPlatformPrice());//赋值小的价格
                                }
                            }
                        }
                        product.setShopId(item.getStoreId());
                        product.setDetailsText("品牌:"+item.getBrand());
                        product.setShopCategory(1);//1是商城商品的意思
                        newItems.add(product);
                        shoppingProducts.add(product);
                    }
                    if (pager == 1){
                        if (shoppingProducts.size()>0) {
                            //if  have datas
                            lv_search_results.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            adapter = new ShoppingProAdapter(shoppingProducts,SearchResultsActivity.this);
                            lv_search_results.setAdapter(adapter);
                            pager++;
                        }else {
                            //if have no datas
                            lv_search_results.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                            tv_nodata.setText("抱歉，暂无相关商品哦~");
                        }
                    }else {
                        if (newItems.size()>0){
                            adapter.notifyDataSetChanged();
                            pager++;
                        }
                    }
                }else {
                    Toast.makeText(SearchResultsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void request_food_searched() {
        IntrestBuyNet.findNearCookByName(10, pager, searchText, 3, city.getLatitude(), city.getLongitude(), new HandleSuccess<SearchFood>() {
            @Override
            public void success(SearchFood s) {
                if (s.getStatus() == 1){
                    ArrayList<Product> newItems = new ArrayList<Product>();
                    newItems.clear();
                    List<SearchFood.DataBean> foodList = s.getData();
                    for (int i = 0;i < foodList.size();i++){
                        SearchFood.DataBean item = foodList.get(i);
                        Product product = new Product();
                        product.setId(item.getId());
                        product.setName(item.getName());
                        product.setPicPath(item.getImgpath());
                        product.setPrice(item.getPrice());
                        product.setShopId(item.getBussId());
                        product.setDetailsText("主料:"+item.getMaining()+" 辅料:"+item.getAccessories());
                        newItems.add(product);
                        foods.add(product);
                    }
                    if (pager == 1){
                        if (foods.size()>0) {
                            //if  have datas
                            lv_search_results.setVisibility(View.VISIBLE);
                            tv_nodata.setVisibility(View.GONE);
                            adapter = new ShoppingProAdapter(foods,SearchResultsActivity.this);
                            lv_search_results.setAdapter(adapter);
                            pager++;
                        }else {
                            //if have no datas
                            lv_search_results.setVisibility(View.GONE);
                            tv_nodata.setVisibility(View.VISIBLE);
                            tv_nodata.setText("抱歉，暂无相关美食哦~");
                        }
                    }else {
                        if (newItems.size()>0){
                            adapter.notifyDataSetChanged();
                            pager++;
                        }
                    }
                }else {
                    Toast.makeText(SearchResultsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    private void init() {
        TextView tv_head_name =  findViewClick(R.id.tv_head_name);
        tv_head_name.setText("我的搜索");
        findViewClick(R.id.iv_back);
        tv_nodata = findView(R.id.tv_nodata);
        lv_search_results = findView(R.id.lv_search_results);
        lv_search_results.setOnItemClickListener(this);
        lv_search_results.setOnScrollListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (searchType){
            case 1://去订餐商品详情页
                request_shopinfo(foods.get(position).getShopId());
                break;
            case 2://去商城生鲜商品详情页
                request_good_shopinfo(shoppingProducts.get(position).getShopId(),shoppingProducts.get(position).getId());
                break;
            case 3://去服务商品详情页
                request_services_shopinfo(shoppingProducts.get(position).getShopId());
                break;
            default:
                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_IDLE:
                if (view.getLastVisiblePosition() == (view.getCount()-1)){
                    requestByType();
                }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
    /*
    请求获取商家信息 取shopName
     */
    private void request_good_shopinfo(long shopid, final Long goodsId) {
        showDialog();
        IntrestBuyNet.findShopByshopId(shopid, new HandleSuccess<MallShopInfo>() {
            @Override
            public void success(MallShopInfo s) {
                if (s.getStatus() == 1){
                    dismiss();
                    String shopName = s.getData().getName();
                    UIHelper.togoGoodsDetailActivity(SearchResultsActivity.this,goodsId,shopName);
                }else {
                    dismiss();
                    Toast.makeText(SearchResultsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_services_shopinfo(final Long shopId) {
        showDialog();
        FoodNet.findServiceStoresInfos(shopId, new HandleSuccess<ServiceStoreBean>() {
            @Override
            public void success(ServiceStoreBean s) {
                if (s.getStatus() == 1 && s.getData() != null){
                    String shopName=s.getData().getName();
                    UIHelper.togoShopDetailActivity(SearchResultsActivity.this,shopId,shopName,SERVICE_MAIN);
                }else {
                    Toast.makeText(SearchResultsActivity.this,"附近还没有此类服务",Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }
    private void request_shopinfo(final long shopid) {
        showDialog();
        IntrestBuyNet.findFoodShopByshopId(shopid, new HandleSuccess<StoreInfo>() {
            @Override
            public void success(StoreInfo s) {
                if (s.getStatus() == 1){
                    String shopName = s.getData().getName();
                    UIHelper.togoShopDetailActivity(SearchResultsActivity.this,shopid,shopName,FOOD_MAIN);
                }else {
                    Toast.makeText(SearchResultsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }
}
