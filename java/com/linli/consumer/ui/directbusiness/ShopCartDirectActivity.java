package com.linli.consumer.ui.directbusiness;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.linli.consumer.R;
import com.linli.consumer.adapter.ShopCarDirectAdapter;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.City;
import com.linli.consumer.domain.Product;
import com.linli.consumer.domain.User;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCartDirectActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private TextView tv_head_name;//赋值店铺名称
    private ListView lv_recommends;
    private Button btn_buy_immediate;
    private ArrayList<GoodsBean> recommends = new ArrayList<GoodsBean>();
    private static ShopCarDirectAdapter shopCarDirectAdapter;//adapter
    public static BigDecimal checkedPrice = new BigDecimal("0.0");//选中条目总价 初始为0
    public static Long shopId;//商家id
    public static Integer category;//商家类型
    private AppContext appContext;
    private User user;
    private City city;
    public static String shopName;//商家名称
    private DBUtil dbUtil = DBUtil.getInstance(this);
    private BigDecimal goodsSum =  new BigDecimal(0);    //商品总价
    private List<GoodsBean> dataList = new ArrayList<>();
    private List<GoodsBean> list = new ArrayList<>();
    Map<String,Object> map = new HashMap<>();   //要上传的map
    private SimpleDateFormat dateFormatUp = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 有可能是商品、订餐、服务 需要不同的显示界面，不同的购买及预约操作 根据后台参数去判断如何显示界面 如何操作
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopcart_direct;
    }
    @Override
    protected void initView() {
        init();
        appContext = (AppContext) getApplicationContext();
        user = appContext.getUser();
        city = appContext.getCity();

    }
    @Override
    protected void initData() {
        request_prods_datas();
    }
    private void request_prods_datas() {
        recommends.addAll(dbUtil.queryByShopId(999L));
        if (recommends.size()>0) {
            shopCarDirectAdapter = new ShopCarDirectAdapter(recommends,ShopCartDirectActivity.this);
            lv_recommends.setAdapter(shopCarDirectAdapter);
        }else {
            Toast.makeText(ShopCartDirectActivity.this, "购物车还是空的~", Toast.LENGTH_SHORT).show();
        }
       dismiss();
    }

    private void init() {
        findViewClick(R.id.iv_back);
        tv_head_name = findViewClick(R.id.tv_head_name);
        tv_head_name.setText("购物车");
        lv_recommends = findView(R.id.lv_recommends);
        lv_recommends.setOnItemClickListener(this);
        btn_buy_immediate = findViewClick(R.id.btn_buy_immediate);
        btn_buy_immediate.setOnClickListener(this);
    }
    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
            case R.id.tv_head_name:
                finish();
                break;
            case R.id.btn_buy_immediate:

                break;
            default:
                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final ShopCarDirectAdapter.ViewHolder holder = (ShopCarDirectAdapter.ViewHolder)view.getTag();
        holder.cb.toggle();
        ShopCarDirectAdapter.getIsSelected().put(position,holder.cb.isChecked());
        if (holder.cb.isChecked() == true){
            for (int i = 0;i < recommends.get(position).getNumber();i++){
                checkedPrice = checkedPrice.add(BigDecimal.valueOf(recommends.get(position).getPrice()));
            }
        }else {
            for (int i = 0;i < recommends.get(position).getNumber();i++){
                checkedPrice = checkedPrice.subtract(BigDecimal.valueOf(recommends.get(position).getPrice()));
            }
        }
        dataChanged();
    }

    /**
     * refesh the listview datas and reset the totalPrice
     */
    public static void dataChanged() {
        shopCarDirectAdapter.notifyDataSetChanged();
    }

    private ArrayList<Product> goodsBeansToProducts(ArrayList<GoodsBean> goodsBeeans){
        if (goodsBeeans != null && goodsBeeans.size() > 0){
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 0;i<goodsBeeans.size();i++){
                Product product = new Product();
                product.setCollectId(goodsBeeans.get(i).getId());//数据库id
                product.setId(goodsBeeans.get(i).getGoodId());
                product.setPrice(goodsBeeans.get(i).getPrice());
                product.setType(goodsBeeans.get(i).getType()); //商家类型

            }
        }

        return null;
    }
}
