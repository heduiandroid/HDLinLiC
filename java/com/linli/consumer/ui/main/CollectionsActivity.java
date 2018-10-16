package com.linli.consumer.ui.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.CollectionProAdapter;
import com.linli.consumer.adapter.CollectionShopAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.CollectGood;
import com.linli.consumer.bean.CollectShop;
import com.linli.consumer.bean.Generic;
import com.linli.consumer.bean.MallShopInfo;
import com.linli.consumer.common.Common;
import com.linli.consumer.domain.CookBook;
import com.linli.consumer.domain.Product;
import com.linli.consumer.domain.Shop;
import com.linli.consumer.domain.User;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.net.IntrestBuyNet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.linli.consumer.widget.ShopSortLayout.FOOD_MAIN;
import static com.linli.consumer.widget.ShopSortLayout.SHOP_MAIN;

public class CollectionsActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {
    private LinearLayout ll_product;
    private LinearLayout ll_shop;
    private LinearLayout ll_cookbook;
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Shop> shops = new ArrayList<Shop>();
    private ArrayList<CookBook> cookBooks = new ArrayList<CookBook>();
    private ArrayList<Shop> newItems = new ArrayList<Shop>();//用于控制分页操作的list
    private ArrayList<Product> newItemgoods = new ArrayList<Product>();//用于控制收藏商品分页操作的list
    public static CollectionProAdapter proAdapter;
    private CollectionShopAdapter shopAdapter;
    private ListView lv_pro_collections;
    private ListView lv_shop_collections;
    private ListView lv_cookbook_collections;
    private LinearLayout ll_shop_collection;
    private SwipeRefreshLayout srl_collections;
    private TextView tv_mall_collection,tv_food_collection,tv_service_collection,tv_nodatashop,tv_nodatapro;
    private LinearLayout footerview;
    private AppContext appContext;
    private User user;
    private int pagerPro = 1;//商品分页数
    private int pagerShop = 1;//商家分页数
    private int pagerCook = 1;//菜谱分页数
    private int type;// type:1-商品2-店铺 3-菜谱
    private int tag = 1;// tag:1-收藏的商城店铺 2-收藏的美食店铺 3-收藏的服务店铺

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        type = 2;
    }

    @Override
    protected void initData() {
        request_shop_collected(tag);
    }

    private void init() {
        TextView tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("收藏");
        findViewClick(R.id.iv_back);
        tv_nodatashop = findView(R.id.tv_nodatashop);
        tv_nodatapro = findView(R.id.tv_nodatapro);
        ll_product= findView(R.id.ll_product);
        ll_shop = findView(R.id.ll_shop);
        ll_cookbook = findView(R.id.ll_cookbook);
        lv_pro_collections = findView(R.id.lv_pro_collections);
        lv_shop_collections = findView(R.id.lv_shop_collections);
        lv_cookbook_collections = findView(R.id.lv_cookbook_collections);

        lv_pro_collections.setOnItemClickListener(this);
        lv_shop_collections.setOnItemClickListener(this);
        lv_cookbook_collections.setOnItemClickListener(this);
        lv_pro_collections.setOnScrollListener(this);
        lv_shop_collections.setOnScrollListener(this);
        lv_cookbook_collections.setOnScrollListener(this);
        lv_pro_collections.setOnItemLongClickListener(this);
        lv_shop_collections.setOnItemLongClickListener(this);
        lv_cookbook_collections.setOnItemLongClickListener(this);
        ll_shop_collection = findView(R.id.ll_shop_collection);
        findViewClick(R.id.btn_product);
        findViewClick(R.id.btn_shop);
        findViewClick(R.id.btn_cookbook);
        srl_collections = findView(R.id.srl_collections);
        srl_collections.setColorSchemeResources(R.color.orange, R.color.red, R.color.gray, R.color.green);
        srl_collections.setSize(SwipeRefreshLayout.DEFAULT);
        srl_collections.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
        srl_collections.setProgressViewEndTarget(true, 100);
        srl_collections.setOnRefreshListener(this);
        tv_mall_collection = findViewClick(R.id.tv_mall_collection);
        tv_food_collection = findViewClick(R.id.tv_food_collection);
        tv_service_collection = findViewClick(R.id.tv_service_collection);
        footerview = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.widget_loadmore,null);
    }

    @Override
    public void onHDClick(View v) {
            switch (v.getId()){
                case R.id.iv_back:
                case R.id.tv_head_name:
                    finish();
                    break;
                case R.id.btn_product:
                    type = 1;
                    ll_product.setVisibility(View.VISIBLE);
                    ll_shop.setVisibility(View.GONE);
                    ll_cookbook.setVisibility(View.GONE);
                    lv_pro_collections.setVisibility(View.VISIBLE);
                    ll_shop_collection.setVisibility(View.GONE);
                    lv_cookbook_collections.setVisibility(View.GONE);
                    if (products.size()==0){
                        pagerPro = 1;
                        request_pro_collected();
                    }
                    break;
                case R.id.btn_shop:
                    type = 2;
                    ll_product.setVisibility(View.GONE);
                    ll_shop.setVisibility(View.VISIBLE);
                    ll_cookbook.setVisibility(View.GONE);
                    lv_pro_collections.setVisibility(View.GONE);
                    ll_shop_collection.setVisibility(View.VISIBLE);
                    lv_cookbook_collections.setVisibility(View.GONE);
                    if (shops.size()==0){
                        pagerShop = 1;
                        request_shop_collected(tag);
                    }
                    break;
                case R.id.btn_cookbook:
                    type = 3;
                    ll_product.setVisibility(View.GONE);
                    ll_shop.setVisibility(View.GONE);
                    ll_cookbook.setVisibility(View.VISIBLE);
                    lv_pro_collections.setVisibility(View.GONE);
                    ll_shop_collection.setVisibility(View.GONE);
                    lv_cookbook_collections.setVisibility(View.VISIBLE);
                    if (cookBooks.size()==0){
                        pagerCook = 1;
                    }
                    break;
                case R.id.tv_mall_collection:
                    setDefaultTextcolor();
                    tv_mall_collection.setTextColor(getResources().getColor(R.color.orange));
                    shops.clear();
                    pagerShop = 1;
                    tag = 1;
                    request_shop_collected(tag);
                    break;
                case R.id.tv_food_collection:
                    setDefaultTextcolor();
                    tv_food_collection.setTextColor(getResources().getColor(R.color.orange));
                    shops.clear();
                    pagerShop = 1;
                    tag = 2;
                    request_shop_collected(tag);
                    break;
                case R.id.tv_service_collection:
                    setDefaultTextcolor();
                    tv_service_collection.setTextColor(getResources().getColor(R.color.orange));
                    shops.clear();
                    pagerShop = 1;
                    tag = 3;
                    request_shop_collected(tag);
                    break;
                default:
                    break;
            }

    }

    private void setDefaultTextcolor() {
        tv_mall_collection.setTextColor(getResources().getColor(R.color.gray));
        tv_food_collection.setTextColor(getResources().getColor(R.color.gray));
        tv_service_collection.setTextColor(getResources().getColor(R.color.gray));
    }

    private void request_shop_collected(int tag) {
        showDialog();
        IntrestBuyNet.collectionShop(tag, 10, pagerShop, user.getId(), new HandleSuccess<CollectShop>() {
            @Override
            public void success(CollectShop s) {
                newItems.clear();
                if (s.getStatus() == 1){
                    if (s.getData() != null) {
                        if (s.getData().size() > 0) {
                            List<CollectShop.DataBean> data = s.getData();
                            if (data != null) {
                                for (int i = 0; i < data.size(); i++) {
                                    CollectShop.DataBean item = data.get(i);
                                    if (item != null) {
                                        Shop shop = new Shop();
                                        shop.setId(item.getId());
                                        shop.setLogoPath(item.getLogoImg() + Common.SMALLERPICPARAM);//imgqn.koudaitong.com/upload_files/2015/05/11/FjbNAx0eHvlmkWSQJu1oLHUCoXgR.jpg");
                                        shop.setShopName(item.getName());
                                        shop.setShopAddr(item.getAddress());
                                        shop.setLevel(item.getCreditLevel());
                                        if (item.getCategoryType() != null) {
                                            shop.setType(item.getCategoryType());
                                        }
                                        Date dt = new Date(item.getCreateTime());
                                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                                        shop.setCreateTime(format.format(dt));
                                        newItems.add(shop);
                                        shops.add(shop);
                                    }
                                }
                            }
                        }
                    }
                    if (pagerShop == 1){
                        if (shops.size()>0) {
                            //if  have datas
                            if (shops.size() > 9){
                                lv_shop_collections.addFooterView(footerview,null,false);
                            }
                            shopAdapter = new CollectionShopAdapter(shops,CollectionsActivity.this);
                            lv_shop_collections.setAdapter(shopAdapter);
                            lv_shop_collections.setVisibility(View.VISIBLE);
                            tv_nodatashop.setVisibility(View.GONE);
                            pagerShop++;
                        }else {
                            //if have no datas
                            shopAdapter = new CollectionShopAdapter(shops,CollectionsActivity.this);
                            lv_shop_collections.setAdapter(shopAdapter);
                            shopAdapter.notifyDataSetChanged();
                            lv_shop_collections.setVisibility(View.GONE);
                            tv_nodatashop.setVisibility(View.VISIBLE);
                            tv_nodatashop.setText("没有收藏过该类店铺哦~");
                        }
                        srl_collections.setRefreshing(false);
                    }else {
                        if (newItems.size()>0){
                            shopAdapter.notifyDataSetChanged();
                            pagerShop++;
                        }else {
                            if (shops.size()>10){
                                lv_shop_collections.removeFooterView(footerview);
                            }
                        }

                    }
                    dismiss();
                }
            }
        });
    }

    private void request_pro_collected() {
        if (pagerPro == 1){
            showDialog();
        }
        IntrestBuyNet.collectionGoods(10,pagerPro,user.getId(), new HandleSuccess<CollectGood>() {
            @Override
            public void success(CollectGood s) {
                newItemgoods.clear();
                if (s.getStatus() == 1){
                    if (s.getData() != null && s.getData().size()>0){
                        List<CollectGood.DataBean> collectGoods = s.getData();
                        for (int i = 0;i < collectGoods.size();i++){
                            CollectGood.DataBean.MallCollectGoodsBean collectInfo = collectGoods.get(i).getMallCollectGoods();
                            CollectGood.DataBean.MallGoodsBean goodInfo = collectGoods.get(i).getMallGoods();

                            if (goodInfo != null) {
                                Product product = new Product();
                                product.setCollectId(collectInfo.getId());
                                product.setSpecId(collectInfo.getGoodsSpecId());//规格id
                                Date dt = new Date(collectInfo.getCreateTime());
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                                product.setCreateTime(format.format(dt));
                                product.setId(goodInfo.getId());
                                product.setShopId(goodInfo.getStoreId());
                                product.setName(goodInfo.getName());
                                product.setPicPath(goodInfo.getPrimaryImage());
                                List<CollectGood.DataBean.MallGoodsBean.GoodsSpecsBean> specs = goodInfo.getGoodsSpecs();
                                for (int j = 0; j < specs.size(); j++) {
                                    if (specs.get(j).getId() == product.getSpecId()) {
                                        product.setPrice(specs.get(j).getPlatformPrice());
                                        break;
                                    }
                                }
                                product.setDetailsText("");
                                newItemgoods.add(product);
                                products.add(product);
                            }
                        }
                    }
                    if (pagerPro == 1){
                        if (products.size()>0) {
                            //if  have datas
                            if (products.size()>9){
                                lv_pro_collections.addFooterView(footerview,null,false);
                            }
                            proAdapter = new CollectionProAdapter(products,CollectionsActivity.this);
                            lv_pro_collections.setAdapter(proAdapter);
                            lv_pro_collections.setVisibility(View.VISIBLE);
                            tv_nodatapro.setVisibility(View.GONE);
                            pagerPro++;
                        }else {
                            //if have no datas
                            proAdapter = new CollectionProAdapter(products,CollectionsActivity.this);
                            lv_pro_collections.setAdapter(proAdapter);
                            proAdapter.notifyDataSetChanged();
                            lv_pro_collections.setVisibility(View.GONE);
                            tv_nodatapro.setVisibility(View.VISIBLE);
                            tv_nodatapro.setText("没有收藏过商品哦~");
                        }
                        srl_collections.setRefreshing(false);
                        dismiss();
                    }else {
                        if (newItemgoods.size()>0){
                            proAdapter.notifyDataSetChanged();
                            pagerPro++;
                        }else {
                            if (products.size()>10){
                                lv_pro_collections.removeFooterView(footerview);
                            }
                        }
                    }
                }else {
                    Toast.makeText(CollectionsActivity.this,"网络繁忙，请稍后再试",Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.lv_pro_collections:
                showDialog();
                final Product pro = products.get(position);
                IntrestBuyNet.findShopByshopId(pro.getShopId(), new HandleSuccess<MallShopInfo>() {
                    @Override
                    public void success(MallShopInfo s) {
                        if (s.getStatus() == 1){
                            String shopName = s.getData().getName();
                            dismiss();
                            UIHelper.togoGoodsDetailActivity(CollectionsActivity.this,pro.getId(),shopName);
                        }
                    }
                });
                break;
            case R.id.lv_shop_collections:
                if (shops.get(position).getType() != null){
                    switch (shops.get(position).getType()){
                        case 1:
                            UIHelper.togoShopDetailActivity(this,shops.get(position).getId(),shops.get(position).getShopName(),SHOP_MAIN);
                            break;
                        case 2:
                            UIHelper.togoShopDetailActivity(this,shops.get(position).getId(),shops.get(position).getShopName(),FOOD_MAIN);
                            break;
                        case 3:
                            break;
                        default:
                            break;
                    }
                }else {
                    Toast.makeText(this,"等等，该店铺好像爆满了！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.lv_cookbook_collections:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        switch (type){
            case 1:
                AlertDialog.Builder builder1=new AlertDialog.Builder(CollectionsActivity.this);
                builder1.setTitle("提示");
                builder1.setMessage("确认删除吗？");
                builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request_delete_procollection(position);
                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder1.create();
                builder1.show();
                break;
            case 2:
                AlertDialog.Builder builder2=new AlertDialog.Builder(CollectionsActivity.this);
                builder2.setTitle("提示");
                builder2.setMessage("确认删除吗？");
                builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (tag == 1){
                            request_delete_shopcollection(position);
                        }else if (tag == 2){
                            request_delete_foodshopcollection(position);
                        }else if (tag ==3){

                        }

                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder2.create();
                builder2.show();
                break;
            case 3:
//                AlertDialog.Builder builder3=new AlertDialog.Builder(CollectionsActivity.this);
//                builder3.setTitle("提示");
//                builder3.setMessage("确认删除吗？");
//                builder3.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request_delete_collection(products.get(position).getId());
//                    }
//                });
//                builder3.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                builder3.create();
//                builder3.show();
                break;
            default:
                break;
        }
        return true;
    }

    private void request_delete_foodshopcollection(final int position) {
        showDialog();
        IntrestBuyNet.collFoodShopDel(shops.get(position).getId(),user.getId(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    shops.remove(position);
                    shopAdapter.notifyDataSetChanged();
                    Toast.makeText(CollectionsActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                }else if (s.getStatus() == 2){
                    Toast.makeText(CollectionsActivity.this,"太快了，小令还没反应过来呢~",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CollectionsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    public void request_delete_procollection(final int position) {
        IntrestBuyNet.collGoodDel(products.get(position).getCollectId(), new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    products.remove(position);
                    proAdapter.notifyDataSetChanged();
                    Toast.makeText(CollectionsActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                }else if (s.getStatus() == 2){
                    Toast.makeText(CollectionsActivity.this,"太快了，小令还没反应过来呢~",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CollectionsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void request_delete_shopcollection(final int position) {
        showDialog();
        IntrestBuyNet.collShopDel(shops.get(position).getId(), user.getId(),new HandleSuccess<Generic>() {
            @Override
            public void success(Generic s) {
                if (s.getStatus() == 1){
                    shops.remove(position);
                    shopAdapter.notifyDataSetChanged();
                    Toast.makeText(CollectionsActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                }else if (s.getStatus() == 2){
                    Toast.makeText(CollectionsActivity.this,"太快了，小令还没反应过来呢~",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(CollectionsActivity.this,s.getMsg().toString(),Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (view.getId()){
            case R.id.lv_pro_collections:
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == (view.getCount()-1)){
                            request_pro_collected();
                        }
                }
                break;
            case R.id.lv_shop_collections:
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == (view.getCount()-1)){
                            request_shop_collected(tag);
                        }
                }
                break;
            case R.id.lv_cookbook_collections:
                switch (scrollState){
                    case SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == (view.getCount()-1)){
                        }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onRefresh() {

        switch (type){
            case 1:
                lv_pro_collections.removeFooterView(footerview);
                pagerPro = 1;
                products.clear();
                if (proAdapter != null){
                    proAdapter.notifyDataSetChanged();
                }
                request_pro_collected();
                break;
            case 2:
                lv_shop_collections.removeFooterView(footerview);
                pagerShop = 1;
                shops.clear();
                if (shopAdapter != null){
                    shopAdapter.notifyDataSetChanged();
                }
                request_shop_collected(tag);
                break;
            default:
                break;
        }
    }
}
