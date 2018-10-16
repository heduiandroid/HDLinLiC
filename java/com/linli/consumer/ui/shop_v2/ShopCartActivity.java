package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.linli.consumer.R;
import com.linli.consumer.adapter.shop_v2.CartAdapter;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.CartShopBean;
import com.linli.consumer.bean.CartSumBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.dao.DBUtil;

import com.linli.consumer.widget.BackLayout;
import com.linli.consumer.widget.CartDialog;
import com.linli.consumer.widget.CartEmptyLayout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by tomoyo on 2016/12/12.
 */

public class ShopCartActivity extends BaseActivity {

    private BackLayout backLayout;

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private CartEmptyLayout emptyView;

    private DBUtil dbUtil = DBUtil.getInstance(this);
    private List<Object> list = new ArrayList<>();          //用于展示的list
    private List<Map<Long,Integer>> mapList = new ArrayList<>();    //key:  goodsId  value:num

    private List<GoodsBean > goodsList = new ArrayList<>();     //实际从数据库取的数据
    private int type ;

    //private AlertDialog.Builder normalDialog;   //对话框，提示是否删除
    private CartDialog cartDialog;
    private int position = 0  ;         //从dialog中传入的数据，要删除的位置


    /**
     *
     * 1，从数据库通过shopId获取goodsId <分组>
     * 2，从服务器通过goodsId s 获取商品信息 list
     * 3，在<分组>拼装
     *
     * */


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cart_v2;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        type = intent.getIntExtra("Sort",1);
        backLayout = findView(R.id.cart_main_back);
        backLayout.setTitle("购物车");
        emptyView = findView(R.id.cart_main_empty);
        //goodsList = dbUtil.queryAll();
        //initDataAndView();
        recyclerView = findView(R.id.cart_main_rc);
        adapter = new CartAdapter(this,list,type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        createDialog();
        initDataAndView();
    }

    private void initDataAndView(){
        goodsList.clear();
        goodsList.addAll(dbUtil.queryAll());
        if(goodsList.size() == 0){
            dismiss();
            emptyView.setVisibility(View.VISIBLE);
        }else {
            emptyView.setVisibility(View.GONE);
            groupByShopId();
        }
    }
    private void createDialog(){
        cartDialog = new CartDialog(this);
        cartDialog.setDeleteGoods(new CartDialog.DeleteGoods() {
            @Override
            public void delete() {
                deleteGoods();
            }
        });
        //normalDialog.show();
    }

    public void showDelDialog(int position){
        this.position = position;
        cartDialog.show();
    }
    private void deleteGoods(){

        long shopId = 0 ;
        double price = 0 ;
        if(list.get(position) instanceof GoodsBean){
            shopId = ((GoodsBean) list.get(position)).getShopId();
            price = ((GoodsBean) list.get(position)).getPrice();
            dbUtil.deleteByGoodId(((GoodsBean) list.get(position)).getGoodId(),((GoodsBean) list.get(position)).getGoodsSpec());
            list.remove(position);
        }
        List<GoodsBean> temList =  dbUtil.queryByShopId(shopId);        //这里在删除了商品后，如果没有这个店的商品了，就把店铺名称删除了
        if(temList != null && temList.size() == 0){
            for(int i = 0 ; i < list.size() ; i ++){
                if(list.get(i) instanceof CartShopBean){
                    if(((CartShopBean) list.get(i)).getShopId() == shopId){
                        list.remove(i);
                    }
                }
                if(list.get(i) instanceof CartSumBean){
                    if(((CartSumBean) list.get(i)).getShopId() == shopId){
                        list.remove(i);
                    }
                }
            }
        } else {
            for(int i = 0 ; i < list.size() ; i ++){
                if(list.get(i) instanceof CartSumBean){
                    if(((CartSumBean) list.get(i)).getShopId() == shopId){
                        BigDecimal tract = BigDecimal.valueOf(Double.parseDouble(((CartSumBean) list.get(i)).getPrice())).setScale(2,BigDecimal.ROUND_HALF_UP).subtract(BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP));
                        ((CartSumBean) list.get(i)).setPrice(tract.toString());
                    }
                }
            }
        }
        if(dbUtil.queryAll().size() == 0){
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void initData() {
    }

    Set<Long > shopIdSet = new HashSet<>();                       //用于存放shopId;
    Map<Long,List<GoodsBean>> listMap = new HashMap<>();    //用于存放不同店铺的商品list的map
    private void groupByShopId(){
        shopIdSet.clear();      //清空重新操作
        listMap.clear();
        list.clear();
        adapter.notifyDataSetChanged();
        //goodsList.addAll(dbUtil.queryAll());

        for(int i = 0 ; i < goodsList.size() ; i ++){       //填充set
            shopIdSet.add(goodsList.get(i).getShopId());
        }
        for(Long shopId : shopIdSet){                       //填充map
            listMap.put(shopId,new ArrayList<GoodsBean>());
        }
        for(Long id : shopIdSet){                           //填充map # list
            for(int i = 0 ; i < goodsList.size() ; i ++ ) {
                if(goodsList.get(i).getShopId() == id){
                    listMap.get(id).add(goodsList.get(i));
                }
            }
        }
        for(Long id : shopIdSet){           //遍历set
            BigDecimal price = new BigDecimal(0);
            CartShopBean cartShopBean = new CartShopBean();
            list.add(cartShopBean);
            CartSumBean cartSumBean = new CartSumBean();
            for(int j = 0 ; j < listMap.get(id).size();j++){        //遍历list
                GoodsBean goodsBean = listMap.get(id).get(j);
                cartShopBean.setName(goodsBean.getShopName());
                cartShopBean.setShopId(goodsBean.getShopId());

                price = price.add(BigDecimal.valueOf(goodsBean.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(goodsBean.getNumber())));
                cartSumBean.setPrice(price.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                cartSumBean.setShopId(goodsBean.getShopId());
                list.add(goodsBean);
            }
            list.add(cartSumBean);
        }
        adapter.notifyDataSetChanged();
        dismiss();
        /*for(GoodsBean bean : goodsList){      //这个写法好，等等看
            if(listMap.containsKey(bean.getShopId())){
                listMap.get(bean.getShopId()).add(bean);
            } else {
                List<GoodsBean> list = new ArrayList<>();
                list.add(bean);
                listMap.put(bean.getShopId(),list);

            }
        }*/
    }

    @Override
    public void onHDClick(View v) {
    }



    @Override
    protected void onRestart() {
        super.onRestart();
        initDataAndView();
    }
}
