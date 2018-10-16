package com.linli.consumer.ui.shop_v2.shopdetail;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseFragment;

import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.CollectionBean;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.common.Common;
import com.linli.consumer.common.HandleSuccess;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.User;
import com.linli.consumer.net.ShopNet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomoyo on 2016/12/10.
 */

public class ShopDetailScrollFragment extends BaseFragment {

    private ViewPager viewPager;        //vp的图片
    private TextView imageNumTv;
    private TextView nameTv;            //商品名称
    private TextView priceTv;           //商品价格
    private TextView fragment_shop_detail_tag;//特别商品标签
    private LinearLayout collectionLl;  //收藏按钮
    private ImageView collectionIm;     //收藏图标，用于改变
    private RelativeLayout chooseRl;      //选择型号按钮

    private TextView isChooseTv;        //是否购买
    private TextView numTv;             //选择数量，用于改变
    private TextView specNameTv;        //规格名称

    private MyPagerAdapter adapter = new MyPagerAdapter();



    //private List<NewsBean.ResultBean.DataBean> list = new ArrayList<>();
    private List<LinearLayout> simpleViewList = new ArrayList<>();

    private GoodsDetailBean goodsDetailBean;
    private User user = AppContext.getInstance().getUser();
    private boolean isCollection = false;

    private String priceStr;
    private DBUtil dbUtil;
    private int goodsNum;//购物车商品数量
    private String goodsSpecName;//用于购物车商品规格名称+数量的显示
    private BigDecimal priceGoods;//用于显示单件商品多规格总价

    //private GoodsSpecDialog dialog;


    public static ShopDetailScrollFragment newInstance(GoodsDetailBean goodsDetailBean){
        ShopDetailScrollFragment fragment = new ShopDetailScrollFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("goodsInfo",goodsDetailBean);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_detail_scroll;
    }

    @Override
    protected void initView() {

        viewPager = findView(R.id.fragment_shop_detail_vp);
        imageNumTv = findView(R.id.fragment_shop_detail_imagenums_tv);
        nameTv = findView(R.id.fragment_shop_detail_name_tv);
        priceTv = findView(R.id.fragment_shop_detail_price_tv);
        fragment_shop_detail_tag = findView(R.id.fragment_shop_detail_tag);
        collectionLl = findViewClick(R.id.fragment_shop_detail_collection_ll);
        collectionIm = findView(R.id.fragment_shop_detail_collection_im);
        chooseRl = findViewClick(R.id.fragment_shop_detail_choose_rl);

        numTv = findView(R.id.fragment_shop_detail_num_tv);
        isChooseTv = findView(R.id.fragment_shop_detail_spec_is_tv);
        specNameTv = findView(R.id.fragment_shop_detail_spec_name_tv);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                imageNumTv.setText((position+1)+"/"+simpleViewList.size());
                simpleViewList.get(position).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.togoPicDetail(getActivity(),imageList.get(position));
                    }
                });

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        goodsDetailBean = (GoodsDetailBean)getArguments().getSerializable("goodsInfo");
        if(goodsDetailBean != null ){

            initVpView();
            fillData();
        }
        dbUtil = DBUtil.getInstance(getActivity());
        //dialog = new GoodsSpecDialog(getActivity(),goodsDetailBean);

    }

    @Override
    protected void initData() {
        priceGoods = new BigDecimal(0d);
        goodsNum = 0;
        goodsSpecName = "";
        if(user != null){
            ShopNet.goodsCollectionQuery(user.getId(), 1, 100, new HandleSuccess<CollectionBean>() {
                @Override
                public void success(CollectionBean collectionBean) {
                    if(collectionBean.getData() != null && collectionBean.getData().size()!=0){
                        for(int i = 0 ; i < collectionBean.getData().size(); i ++){
                            if(goodsDetailBean.getGoodsSpecs().get(0).getId()
                                    ==collectionBean.getData().get(i).getMallCollectGoods().getGoodsSpecId()){
                                collectionIm.setImageResource(R.mipmap.ic_shop_detail_collection);
                                isCollection = true;
                                break;
                            } else {
                                collectionIm.setImageResource(R.mipmap.ic_shop_detail_collection_nor);
                                isCollection = false;
                            }
                        }
                    }
                }
            });
        }
        double[] temPrice = new double[goodsDetailBean.getGoodsSpecs().size()];
        for(int i = 0 ; i < goodsDetailBean.getGoodsSpecs().size() ; i ++ ) {
            temPrice[i] = goodsDetailBean.getGoodsSpecs().get(i).getPlatformPrice().doubleValue();
        }
        for (int i = 0;i<temPrice.length;i++){
            Log.i("test","之前"+temPrice[i]);
        }
        for( int i = 0 ; i < temPrice.length-1 ;i ++){
            for(int j = 0 ; j <temPrice.length-1-i; j ++){
                if(temPrice[j]> temPrice[j+1]){
                    double a = temPrice[j];
                    temPrice[j] = temPrice[j+1];
                    temPrice[j+1] = a;
                }
            }
        }
        priceStr = "￥"+temPrice[0];
        for (int i = 0;i<temPrice.length;i++){
            Log.i("test","之后"+temPrice[i]+"");
        }
//        if (goodsDetailBean.getGoodsSpecs().size()==1){
            priceTv.setText(priceStr);
//        }else {
//            priceTv.setText(goodsDetailBean.getMinprice().toString());
//        }
        if(goodsDetailBean.getGoodsSpecs() != null && goodsDetailBean.getGoodsSpecs().size() > 0){
            for (int i = 0;i < goodsDetailBean.getGoodsSpecs().size();i++){
                this.refreshView(goodsDetailBean.getId(),goodsDetailBean.getGoodsSpecs().get(i).getId());
            }
        }

    }

    @Override
    protected void fillData() {
        nameTv.setText(goodsDetailBean.getName());

        if (goodsDetailBean.getStoreId() == 999L){
            fragment_shop_detail_tag.setVisibility(View.VISIBLE);
            Long salesCate = goodsDetailBean.getSalescategoryId();
            if (salesCate != null) {
                if (salesCate == 1) {//直接减红包
                    BigDecimal maxFavor = new BigDecimal(0d);
                    List<GoodsDetailBean.GoodsSpecsBean> specs = goodsDetailBean.getGoodsSpecs();
                    for (int i = 0; i < specs.size(); i++) {
                        BigDecimal favorPrice = specs.get(i).getStockPrice();
                        if (favorPrice != null && favorPrice.doubleValue() > 0d) {
                            if (favorPrice.doubleValue() > maxFavor.doubleValue()){
                                maxFavor = favorPrice;
                            }
                        }
                    }
                    if (maxFavor.doubleValue() > 0d) {
                        fragment_shop_detail_tag.setText("*可使用红包抵现，单件最多可抵" + maxFavor + "元");
                    }else {
                        fragment_shop_detail_tag.setVisibility(View.GONE);
                    }
                } else {//还有满减  0元购 团购 没做处理
                    fragment_shop_detail_tag.setVisibility(View.GONE);
                }
            }else {
                fragment_shop_detail_tag.setVisibility(View.GONE);
            }
        }else {
            if (goodsDetailBean.getIsRecommend()!= null&& goodsDetailBean.getIsRecommend() == 1){
                fragment_shop_detail_tag.setVisibility(View.VISIBLE);
            }
            if (goodsDetailBean.getNospecStockPrice()!=null && goodsDetailBean.getNospecStockPrice().doubleValue()!=0d){
                fragment_shop_detail_tag.setVisibility(View.VISIBLE);
                fragment_shop_detail_tag.setTextColor(getResources().getColor(R.color.lightred));
                fragment_shop_detail_tag.setText("*购买该商品最多可返"+goodsDetailBean.getMaxprice().multiply(goodsDetailBean.getNospecStockPrice()).setScale(2,BigDecimal.ROUND_HALF_UP)+"元红包");
            }
        }
        priceTv.setText("");
        //TODO 根据数据判断是否收藏过

    }

    private List<String> imageList = new ArrayList<>();
    private void initVpView(){
        if(!TextUtils.isEmpty(goodsDetailBean.getPrimaryImage())){
            imageList.add(goodsDetailBean.getPrimaryImage());
        }
        if(!TextUtils.isEmpty(goodsDetailBean.getProductImage1())){
            imageList.add(goodsDetailBean.getProductImage1());
        }
        if(!TextUtils.isEmpty(goodsDetailBean.getProductImage2())){
            imageList.add(goodsDetailBean.getProductImage2());
        }
        if(!TextUtils.isEmpty(goodsDetailBean.getProductImage3())){
            imageList.add(goodsDetailBean.getProductImage3());
        }

        for(int i = 0;i<imageList.size();i++){
            LinearLayout view = (LinearLayout)LayoutInflater.from(getContext())
                    .inflate(R.layout.fragment_shop_detail_image_widget,null);
            SimpleDraweeView draweeView = (SimpleDraweeView)view.findViewById(R.id.fragment_shop_detail_image_widget_im);

            draweeView.setImageURI(imageList.get(i)+ Common.WSMALLERPICPARAM400);
            simpleViewList.add(view);
        }
        //这里设置第一个图片的监听
        if(simpleViewList.size()>0){
            simpleViewList.get(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIHelper.togoPicDetail(getActivity(),imageList.get(0));
                }
            });
        }
        imageNumTv.setText(1+"/"+simpleViewList.size());        //开始默认设置为1/4
        dismiss();
        adapter.notifyDataSetChanged();
    }

    private class MyPagerAdapter extends PagerAdapter {

        private MyPagerAdapter() {
            super();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return simpleViewList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(simpleViewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(simpleViewList.get(position));


            return simpleViewList.get(position);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_shop_detail_collection_ll:   //加入收藏
                saveToCollection();
                break;
            case R.id.fragment_shop_detail_choose_rl:       //选择商品规格
                //dialog.show();
                if(goodsDetailBean != null){
                    UIHelper.togoGoodsSpecActivity(getActivity(),goodsDetailBean,0);
                } else  {
                    Toast.makeText(getActivity(),"无网络连接...",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
    /**
     * 在点击收藏按钮后，进行操作
     * */
    private void saveToCollection(){
        //TODO 这里进行联网操作或者其他，还需要处理
        if(user != null){
            if(!isCollection){      //没有收藏，执行收藏动作
                ShopNet.goodsCollectionAdd(goodsDetailBean.getGoodsSpecs().get(0).getId(), user.getId(), new HandleSuccess<CollectionBean>() {
                    @Override
                    public void success(CollectionBean collectionBean) {
                        if(collectionBean.getStatus() == 1){
                            isCollection = true;
                            collectionIm.setImageResource(R.mipmap.ic_shop_detail_collection);
                        }
                    }
                });
            }else {     //收藏了，执行取消动作（暂时取消不了有点麻烦）
                Toast.makeText(getActivity(),"已经收藏过了",Toast.LENGTH_SHORT).show();
//                ShopNet.goodsCollectionDel(goodsDetailBean.getGoodsSpecs().get(0).getId(), new HandleSuccess<CollectionBean>() {
//                    @Override
//                    public void success(CollectionBean collectionBean) {
//                        if(collectionBean.getStatus() == 1){
//                            isCollection = false;
//                            collectionIm.setImageResource(R.mipmap.ic_shop_detail_collection_nor);
//                        }
//                    }
//                });
            }

        }else {
            UIHelper.togoLoginActivity(getActivity());
        }

    }
    public void refresh(){
        initData();
    }
    public void refreshView(long goodsId,long specId){
        if(specId == 111L){
            isChooseTv.setText("未选");
            numTv.setText("");
            specNameTv.setText("");
        } else {
            GoodsBean goodsBean = dbUtil.queryByGoodId(goodsId, specId);
            if(goodsBean != null){
                Log.i("test",goodsBean.getGoodsSpec()+" "+goodsBean.getGoodsSpecName()+" "+goodsBean.getNumber());
                isChooseTv.setText("已选");
                goodsNum = goodsNum + goodsBean.getNumber();
                if (!"".equals(goodsBean.getGoodsSpecName())) {
                    goodsSpecName = goodsSpecName + goodsBean.getGoodsSpecName() + " ×" + goodsBean.getNumber() + "\n";
                    numTv.setText("共"+goodsNum+"件");
                    specNameTv.setText(goodsSpecName.substring(0,goodsSpecName.length()-1));
                }else {
                    numTv.setText(goodsNum+"件");
                    specNameTv.setText(goodsSpecName);
                }
                BigDecimal priceGood = BigDecimal.valueOf(goodsBean.getPrice()).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal n = BigDecimal.valueOf(goodsBean.getNumber());
                priceGoods = priceGoods.add(priceGood.multiply(n));
                Log.i("test",goodsSpecName);
                priceTv.setText("￥"+priceGoods.toString());
            } else {
                if (goodsNum == 0 && "".equals(goodsSpecName)) {
                    isChooseTv.setText("未选");
                    numTv.setText("");
                    specNameTv.setText("");
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        user = AppContext.getInstance().getUser();
    }
}
