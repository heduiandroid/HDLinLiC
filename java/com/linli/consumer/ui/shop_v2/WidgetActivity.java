package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.app.AppContext;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.base.UIHelper;
import com.linli.consumer.bean.GoodsBean;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.dao.DBUtil;
import com.linli.consumer.domain.User;
import com.linli.consumer.widget.wrilayout.FlowLayout;
import com.linli.consumer.widget.wrilayout.TagAdapter;
import com.linli.consumer.widget.wrilayout.TagFlowLayout;
import com.linli.consumer.widget.wrilayout.TagView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tomoyo on 2017/2/17.
 */

public class WidgetActivity extends BaseActivity {

    private SimpleDraweeView avatarIm;      //头像
    private TextView nameTv;                //名称
    private TextView priceTv;               //价格   跟随规格变化
    private TextView numTv;                 //数量
    private TextView specNameTv;            //规格名称
    private TextView tv_inventory;          //规格库存
    private RelativeLayout reduceIb;
    private RelativeLayout moreIb;

    private String temPrice="";    //暂时的价格


    private TextView specTv1;         //关于规格的组件
    private TextView specTv2;
    private TextView specTv3;
    private TagFlowLayout flowLayout1;
    private TagFlowLayout flowLayout2;
    private TagFlowLayout flowLayout3;

    private LayoutInflater inflater ;
    private GoodsDetailBean goodsDetailBean ;

    private LinearLayout spaceLl;       //空白处
    //private LinearLayout enterShopLl;   //加入店铺
    //private LinearLayout enterCartLl;   //加入购物车
    //private ImageView enterCartIm;      //加入购物车图片
    //private RelativeLayout buyNowRl;    //立即购买
    private Button confirmBt;       //确认

    private long specId = 111L;        //规格id
    private String specName = "";       //规格名称
    private BigDecimal specPrice = new BigDecimal(0);           //价格
    private int specInventory;          //规格库存
    private int num =1;        //数量

    private DBUtil dbUtil;
    //private GoodsBean goodsBean;
    private User user = AppContext.getInstance().getUser();
    private int isIns = 0;


    @Override
    protected int getLayoutId() {
        return R.layout.good_detail_dialog;
    }

    @Override
    protected void initView() {
        avatarIm = findView(R.id.good_spec_dialog_avatar_im);
        nameTv = findView(R.id.good_spec_dialog_name_tv);
        priceTv = findView(R.id.good_spec_dialog_price_tv);
        reduceIb = findViewClick(R.id.good_spec_dialog_reduce_ib);
        moreIb = findViewClick(R.id.good_spec_dialog_more_ib);
        numTv = findView(R.id.good_spec_dialog_num_tv);
        specNameTv = findView(R.id.good_spec_dialog_spec_tv);
        tv_inventory = findView(R.id.tv_inventory);

        inflater = LayoutInflater.from(this);

        specTv1 = findView(R.id.widget_spec_tag_name_tv1);
        specTv2 = findView(R.id.widget_spec_tag_name_tv2);
        specTv3 = findView(R.id.widget_spec_tag_name_tv3);
        flowLayout1 = findView(R.id.id_flowlayout1);
        flowLayout2 = findView(R.id.id_flowlayout2);
        flowLayout3 = findView(R.id.id_flowlayout3);

        spaceLl = findViewClick(R.id.good_spec_dialog_space_ll);
        confirmBt = findViewClick(R.id.widget_confirm_bt);
        /*enterShopLl = findViewClick(R.id.shop_detail_main_entershop_ll);
        enterCartLl = findViewClick(R.id.shop_detail_main_addcart_ll);
        enterCartIm = findView(R.id.shop_detail_main_addcart_im);
        buyNowRl = findViewClick(R.id.shop_detail_main_buy);*/

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        goodsDetailBean = (GoodsDetailBean)intent.getSerializableExtra("GoodsDetail");
        isIns = intent.getIntExtra("isIns",0);
        dbUtil =  DBUtil.getInstance(this);
        switch (goodsDetailBean.getSpecNameValues().size()-1){
            case -1:
                specNone();
                break;
            case 0:
                specOne();
                fillSpecData();
                fillData();
                break;
            case 1:
                specTwo();
                fillSpecData();
                fillData();
                break;
            case 2:
                specThree();
                fillSpecData();
                fillData();
                break;

        }

    }
    private Set<String> specSet1 = new HashSet<>();     //零时存放按钮内容 -- 为了去除重复
    private Set<String> specSet2 = new HashSet<>();
    private Set<String> specSet3 = new HashSet<>();

    private List<String> specNames1 = new ArrayList<>();     //转换写入按钮的数据，在转换为数组 --存放按钮内容
    private List<String> specNames2 = new ArrayList<>();
    private List<String> specNames3 = new ArrayList<>();

    private Map<Integer,List<String>> map = new HashMap<>();        //各自的规格
    private Map<Integer,String> mapSimp = new HashMap<>();    //各自的规格，里面存单个String;

    /**
     * 无规格
     * */
    private void specNone(){
        specId = goodsDetailBean.getGoodsSpecs().get(0).getId();        //规格id
        specName = "";       //规格名称
        specPrice = goodsDetailBean.getGoodsSpecs().get(0).getPlatformPrice();           //价格
        specInventory = goodsDetailBean.getGoodsSpecs().get(0).getInventory();          //规格库存

        avatarIm.setImageURI(goodsDetailBean.getPrimaryImage());
        nameTv.setText(goodsDetailBean.getName());
        priceTv.setText("￥"+specPrice);
        tv_inventory.setText("    库存："+specInventory);
        temPrice = specPrice.toString();
        GoodsBean goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
        if (goodsBean != null){
            num = goodsBean.getNumber();
            numTv.setText(num+"");
        }
        dismiss();
    }

    /**
     * 一种规格*/
    private void specOne(){
        for(int i = 0 ; i< goodsDetailBean.getGoodsSpecs().size() ; i ++){
            specSet1.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue());
        }
        for(String name : specSet1){
            specNames1.add(name);
        }

    }
    /**
     * 两种规格*/
    private void specTwo(){
        for(int i = 0 ; i< goodsDetailBean.getGoodsSpecs().size() ; i ++){
            specSet1.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue());
        }
        for(String name : specSet1){
            specNames1.add(name);
        }
        for(int i = 0 ; i< goodsDetailBean.getGoodsSpecs().size() ; i ++){
            specSet2.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecbValue());
        }
        for(String name : specSet2){
            specNames2.add(name);
        }

    }
    /**
     * 三种规格*/
    private void specThree(){
        for(int i = 0 ; i< goodsDetailBean.getGoodsSpecs().size() ; i ++){
            specSet1.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue());
        }
        for(String name : specSet1){
            specNames1.add(name);
        }
        for(int i = 0 ; i< goodsDetailBean.getGoodsSpecs().size() ; i ++){
            specSet2.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecbValue());
        }
        for(String name : specSet2){
            specNames2.add(name);
        }
        for(int i = 0 ; i< goodsDetailBean.getGoodsSpecs().size() ; i ++){
            specSet3.add(goodsDetailBean.getGoodsSpecs().get(i).getSpeccValue());
        }
        for(String name : specSet3){
            specNames3.add(name);
        }
    }

    private void fillSpecData(){
        int size = goodsDetailBean.getSpecNameValues().size();
        for(int i = 0 ; i < goodsDetailBean.getGoodsSpecs().size() ;i ++){      //填充各自的规格
            List<String> list = new ArrayList<>();
            switch (size){
                case 1:
                    list.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue());   //规格a       0
                    list.add("");   //规格b       1
                    list.add("");   //规格c       2
                    break;
                case 2:
                    list.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue());   //规格a       0
                    list.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecbValue());   //规格b       1
                    list.add("");   //规格c       2
                    break;
                case 3:
                    list.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue());   //规格a       0
                    list.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecbValue());   //规格b       1
                    list.add(goodsDetailBean.getGoodsSpecs().get(i).getSpeccValue());   //规格c       2
                    break;
            }
            list.add(goodsDetailBean.getGoodsSpecs().get(i).getId()+"");        //规格id      3
            list.add(goodsDetailBean.getGoodsSpecs().get(i).getPlatformPrice().toString()); //规格价格      4
            list.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue()     //规格描述      5
                    +goodsDetailBean.getGoodsSpecs().get(i).getSpecbValue()
                    +goodsDetailBean.getGoodsSpecs().get(i).getSpeccValue());
            list.add(goodsDetailBean.getGoodsSpecs().get(i).getInventory()+""); //规格库存      6
            map.put(i,list);
        }
        for(int i = 0 ; i < map.size(); i ++){
            String a = "";
            for(int j = 0 ; j < goodsDetailBean.getSpecNameValues().size() ; j ++){
                a = a +map.get(i).get(j);
            }
            mapSimp.put(i,a);       //存放3个规格，String类型
        }

        /** 这里开始 ,计算价格区间**/
        List<Double> tem = new ArrayList<>();
        for(int i = 0 ; i < map.size() ; i ++){
            String num = map.get(i).get(4);
            tem.add(Double.valueOf(num));
        }
        double[] temA = new double[tem.size()];
        for(int i = 0 ; i < tem.size() ; i ++){
            temA[i] = tem.get(i);
        }
        for( int i = 0 ; i < temA.length-1 ;i ++){
            for(int j = 0 ; j <temA.length-1-i; j ++){
                if(temA[j]> temA[j+1]){
                    double a = temA[j];
                    temA[j] = temA[j+1];
                    temA[j+1] = a;
                }
            }
        }
        switch (size){
            case 1:
                specNameTv.setText(goodsDetailBean.getSpecNameValues().get(0)+":"+"  ");
                break;
            case 2:
                specNameTv.setText(goodsDetailBean.getSpecNameValues().get(0)+":"+"  "
                        +goodsDetailBean.getSpecNameValues().get(1)+":"+"  ");
                break;
            case 3:
                specNameTv.setText(goodsDetailBean.getSpecNameValues().get(0)+":"+"  "
                        +goodsDetailBean.getSpecNameValues().get(1)+":"+"  "
                        +goodsDetailBean.getSpecNameValues().get(2)+":");
                break;
        }
        /*specNameTv.setText(goodsDetailBean.getSpecNameValues().get(0)+":"+"  "
                +goodsDetailBean.getSpecNameValues().get(1)+":"+"  "
                +goodsDetailBean.getSpecNameValues().get(2)+":");*/
        temPrice = "￥"+temA[0]+"-"+"￥"+temA[temA.length-1];


    }

    private boolean bool1 = false;
    private boolean bool2 = false;
    private boolean bool3 = false;
    private String str1 = "";
    private String str2 = "";
    private String str3 = "";
    private String strSel = "";
    @Override
    protected void fillData() {

        avatarIm.setImageURI(goodsDetailBean.getPrimaryImage());
        nameTv.setText(goodsDetailBean.getName());

        for(int i = 0 ; i < goodsDetailBean.getSpecNameValues().size() ; i ++){
            switch (i){
                case 0:
                    specTv1.setVisibility(View.VISIBLE);
                    flowLayout1.setVisibility(View.VISIBLE);
                    specTv1.setText(goodsDetailBean.getSpecNameValues().get(0));
                    break;
                case 1:
                    specTv2.setVisibility(View.VISIBLE);
                    flowLayout2.setVisibility(View.VISIBLE);
                    specTv2.setText(goodsDetailBean.getSpecNameValues().get(1));
                    break;
                case 2:
                    specTv3.setVisibility(View.VISIBLE);
                    flowLayout3.setVisibility(View.VISIBLE);
                    specTv3.setText(goodsDetailBean.getSpecNameValues().get(2));
                    break;
                default:
                    break;
            }
        }
        switch (goodsDetailBean.getSpecNameValues().size()-1) {
            case -1:        //没有规格
                break;
            case 0:
                flowLayout1.setAdapter(new TagAdapter<String>(specNames1.toArray(new String[specNames1.size()])) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) inflater.inflate(R.layout.wri_text_tv_layout,
                                flowLayout1, false);
                        tv.setText(s);
                        return tv;
                    }

                });
                flowLayout1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        bool1 = flowLayout1.isFlowSelected();
                        if (selectPosSet.iterator().hasNext()) {
                            TagView tagView = (TagView) flowLayout1.getChildAt(selectPosSet.iterator().next());
                            TextView textView = (TextView) tagView.getChildAt(0);
                            str1 = textView.getText().toString();
                        }
                        chooseOne();    //这里选中一个的方法
                    }
                });
                break;
            case 1:
                flowLayout1.setAdapter(new TagAdapter<String>(specNames1.toArray(new String[specNames1.size()])) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) inflater.inflate(R.layout.wri_text_tv_layout,
                                flowLayout1, false);
                        tv.setText(s);
                        return tv;
                    }

                });
                flowLayout2.setAdapter(new TagAdapter<String>(specNames2.toArray(new String[specNames2.size()])) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) inflater.inflate(R.layout.wri_text_tv_layout,
                                flowLayout2, false);
                        tv.setText(s);
                        return tv;
                    }
                });
                flowLayout1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        bool1 = flowLayout1.isFlowSelected();
                        if (selectPosSet.iterator().hasNext()) {
                            TagView tagView = (TagView) flowLayout1.getChildAt(selectPosSet.iterator().next());
                            TextView textView = (TextView) tagView.getChildAt(0);
                            str1 = textView.getText().toString();
                        }
                        chooseTwo();
                    }
                });

                flowLayout2.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        bool2 = flowLayout2.isFlowSelected();
                        if (selectPosSet.iterator().hasNext()) {
                            TagView tagView = (TagView) flowLayout2.getChildAt(selectPosSet.iterator().next());
                            TextView textView = (TextView) tagView.getChildAt(0);
                            str2 = textView.getText().toString();
                        }
                        chooseTwo();
                    }
                });
                break;
            case 2:
                flowLayout1.setAdapter(new TagAdapter<String>(specNames1.toArray(new String[specNames1.size()])) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) inflater.inflate(R.layout.wri_text_tv_layout,
                                flowLayout1, false);
                        tv.setText(s);
                        return tv;
                    }

                });
                flowLayout2.setAdapter(new TagAdapter<String>(specNames2.toArray(new String[specNames2.size()])) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) inflater.inflate(R.layout.wri_text_tv_layout,
                                flowLayout2, false);
                        tv.setText(s);
                        return tv;
                    }

                });
                flowLayout3.setAdapter(new TagAdapter<String>(specNames3.toArray(new String[specNames3.size()])) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) inflater.inflate(R.layout.wri_text_tv_layout,
                                flowLayout3, false);
                        tv.setText(s);
                        return tv;
                    }
                });
                flowLayout1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        bool1 = flowLayout1.isFlowSelected();
                        if (selectPosSet.iterator().hasNext()) {
                            TagView tagView = (TagView) flowLayout1.getChildAt(selectPosSet.iterator().next());
                            TextView textView = (TextView) tagView.getChildAt(0);
                            str1 = textView.getText().toString();
                        }
                        chooseThree();
                    }
                });

                flowLayout2.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        bool2 = flowLayout2.isFlowSelected();
                        if (selectPosSet.iterator().hasNext()) {
                            TagView tagView = (TagView) flowLayout2.getChildAt(selectPosSet.iterator().next());
                            TextView textView = (TextView) tagView.getChildAt(0);
                            str2 = textView.getText().toString();
                        }
                        chooseThree();
                    }
                });
                flowLayout3.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        bool3 = flowLayout3.isFlowSelected();
                        if (selectPosSet.iterator().hasNext()) {
                            TagView tagView = (TagView) flowLayout3.getChildAt(selectPosSet.iterator().next());
                            TextView textView = (TextView) tagView.getChildAt(0);
                            str3 = textView.getText().toString();
                        }
                        chooseThree();
                    }
                });

                break;
        }
        dismiss();

    }

    private List<Integer> cof = new ArrayList<>();
    private List<String> tem1 = new ArrayList<>();
    private List<String> tem2 = new ArrayList<>();
    private List<String> tem3 = new ArrayList<>();
    /**
     * 一个的点击事件*/
    private void chooseOne(){
        tem1.clear();
        strSel = "";
        strSel = (bool1?str1:"");
        specNameTv.setText(goodsDetailBean.getSpecNameValues().get(0)+":"+(bool1?str1:""));
        for(int i = 0 ; i < map.size(); i ++){      //map中放了3个规格list
            String selectString = "";
            List<String> list = map.get(i);
            selectString = (bool1?list.get(0):"");
            if(strSel.equals(selectString)){
                cof.add(i);                                     //筛选出符合条件的list
            }
        }
        for(int i = 0 ; i < cof.size() ; i ++){
            List<String> list = map.get(cof.get(i));
            tem1.add(list.get(0));
        }
        String finalStr = "";
        if(bool1){
            if(mapSimp.get(cof.get(0)).equals(strSel)){
                //map.get(cof.get(0)).get(1);
                //map.get(cof.get(0)).get(3);
                specId =  Long.valueOf(map.get(cof.get(0)).get(3));
                //specName = strSel;
                specName = goodsDetailBean.getSpecNameValues().get(0)+":"+(bool1?str1:"");
                specPrice = BigDecimal.valueOf(Double.valueOf(map.get(cof.get(0)).get(4)));
                specInventory = Integer.decode(map.get(cof.get(0)).get(6));
                priceTv.setText("￥"+specPrice);
                tv_inventory.setText("    库存："+specInventory);
                showNum(specId);
            }
        } else {
            recoverNum();
            tem1.clear();
        }
        cof.clear();

    }
    /**
     * 两个的点击事件*/
    private void chooseTwo(){
        tem1.clear();
        tem2.clear();
        strSel = "";
        strSel = (bool1?str1:"")+(bool2?str2:"");
        specNameTv.setText(goodsDetailBean.getSpecNameValues().get(0)+":"+(bool1?str1:"")+"  "
                +goodsDetailBean.getSpecNameValues().get(1)+":"+(bool2?str2:""));
        for(int i = 0 ; i < map.size(); i ++){      //map中放了3个规格list
            String selectString = "";
            List<String> list = map.get(i);
            selectString = (bool1?list.get(0):"")+(bool2?list.get(1):"");
            if(strSel.equals(selectString)){
                cof.add(i);                                     //筛选出符合条件的list
            }
        }
        for(int i = 0 ; i < cof.size() ; i ++){
            List<String> list = map.get(cof.get(i));
            tem1.add(list.get(0));
            tem2.add(list.get(1));
        }
        flowLayout1.setUnLock(tem1);
        flowLayout2.setUnLock(tem2);
        if(bool1 && !bool2){
            recoverNum();
            tem1.clear();
        }
        if(bool2 && !bool1){
            recoverNum();
            tem2.clear();
        }

        if(!bool1 && !bool2){
            recoverNum();
            tem1.clear();
            tem2.clear();
            flowLayout1.setUnLock(tem1);
            flowLayout2.setUnLock(tem2);
        }
        String finalStr = "";
        if(bool1 && bool2 ){
            if(mapSimp.get(cof.get(0)).equals(strSel)){
                //map.get(cof.get(0)).get(1);
                //map.get(cof.get(0)).get(3);
                specId =  Long.valueOf(map.get(cof.get(0)).get(3));
                //specName = strSel;
                specName = goodsDetailBean.getSpecNameValues().get(0)+":"+(bool1?str1:"")+"  "
                        +goodsDetailBean.getSpecNameValues().get(1)+":"+(bool2?str2:"");
                specPrice = BigDecimal.valueOf(Double.valueOf(map.get(cof.get(0)).get(4)));
                specInventory = Integer.decode(map.get(cof.get(0)).get(6));
                showNum(specId);
            }
        }
        if(bool1 && !bool2){            //当未完全选择，价格显示暂时的
            recoverNum();
        }
        if(bool2 && !bool1){
            recoverNum();
        }
        cof.clear();
    }
    /**
     * 三个的点击事件*/
    private void chooseThree(){
        tem1.clear();
        tem2.clear();
        tem3.clear();
        strSel = "";
        strSel = (bool1?str1:"")+(bool2?str2:"")+(bool3?str3:"");
        specNameTv.setText(goodsDetailBean.getSpecNameValues().get(0)+":"+(bool1?str1:"")+"  "
                +goodsDetailBean.getSpecNameValues().get(1)+":"+(bool2?str2:"")+"  "
                +goodsDetailBean.getSpecNameValues().get(2)+":"+(bool3?str3:""));
        for(int i = 0 ; i < map.size(); i ++){      //map中放了3个规格list
            String selectString = "";
            List<String> list = map.get(i);
            selectString = (bool1?list.get(0):"")+(bool2?list.get(1):"")+(bool3?list.get(2):"");
            if(strSel.equals(selectString)){
                cof.add(i);                                     //筛选出符合条件的list
            }
        }
        for(int i = 0 ; i < cof.size() ; i ++){
            List<String> list = map.get(cof.get(i));
            tem1.add(list.get(0));
            tem2.add(list.get(1));
            tem3.add(list.get(2));
        }
        flowLayout1.setUnLock(tem1);
        flowLayout2.setUnLock(tem2);
        flowLayout3.setUnLock(tem3);
        if(bool1 && (!bool2&&!bool3)){
            recoverNum();
            tem1.clear();
        }
        if(bool2 && (!bool1&&!bool3)){
            recoverNum();
            tem2.clear();
        }
        if(bool3 && (!bool1 && !bool2)){
            recoverNum();
            tem3.clear();
        }
        if(!bool1 && !bool2 && !bool3){
            recoverNum();
            tem1.clear();
            tem2.clear();
            tem3.clear();
            flowLayout1.setUnLock(tem1);
            flowLayout2.setUnLock(tem2);
            flowLayout3.setUnLock(tem3);
        }
        String finalStr = "";
        if(bool1 && bool2 && bool3){
            if(mapSimp.get(cof.get(0)).equals(strSel)){
                //map.get(cof.get(0)).get(1);
                //map.get(cof.get(0)).get(3);
                specId =  Long.valueOf(map.get(cof.get(0)).get(3));
                //specName = strSel;
                specName = goodsDetailBean.getSpecNameValues().get(0)+":"+(bool1?str1:"")+"  "
                        +goodsDetailBean.getSpecNameValues().get(1)+":"+(bool2?str2:"")+"  "
                        +goodsDetailBean.getSpecNameValues().get(2)+":"+(bool3?str3:"");
                specPrice = BigDecimal.valueOf(Double.valueOf(map.get(cof.get(0)).get(4)));
                specInventory = Integer.decode(map.get(cof.get(0)).get(6));
                showNum(specId);
            }
        }
        if((bool1 && bool2) && (!bool3)){
            recoverNum();
        }
        if((bool1 && bool3) && (!bool2)){
            recoverNum();
        }
        if((bool3 && bool2) && (!bool1)){
            recoverNum();
        }
        cof.clear();
    }

    /**
     * 在选中规格后，对数量进行更改
     * */
    private void showNum(long specId){
        GoodsBean goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
        if(goodsBean != null){
            numTv.setText(goodsBean.getNumber()+"");
            num = goodsBean.getNumber();
            priceTv.setText("￥"+specPrice);
        } else {
            priceTv.setText("￥"+specPrice);
        }
        tv_inventory.setText("    库存："+specInventory);

    }
    /**
     * 在未选中时，数据恢复为0
     * */
    private void recoverNum(){
        specId = 111L;
        num = 1;
        specName = "";
        specPrice = BigDecimal.valueOf(0);
        specInventory = 0;
        priceTv.setText(temPrice);
        tv_inventory.setText("");
    }



    @Override
    public void onBackPressed() {
        setBackData();
        super.onBackPressed();
    }
    private void setBackData(){
        Intent intent = new Intent();
        intent.putExtra("specId",specId);
        intent.putExtra("specName",specName);
        intent.putExtra("specPrice",specPrice.toString());
        intent.putExtra("specInventory",specInventory);
        setResult(UIHelper.GOODS_SPEC_REQUEST_CODE,intent);
        finish();
    }

    @Override
    public void onHDClick(View v) {
        switch (v.getId()){
            case R.id.good_spec_dialog_space_ll:            //空白处点击消失
                setBackData();
                break;
            /*case R.id.shop_detail_main_entershop_ll:        //进入店铺
                if(goodsDetailBean != null ){
                    UIHelper.togoShopDetailActivity(this,goodsDetailBean.getStoreId()
                            ,goodsDetailBean.getStoreName(),SHOP_MAIN);
                } else {
                    Toast.makeText(this,"网络繁忙，请重试...",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.shop_detail_main_addcart_ll:          //加入 购物车
                addToCartNow();
                break;
            case R.id.shop_detail_main_buy:                 //立即购买
                buyNow();
                break;*/
            case R.id.good_spec_dialog_reduce_ib:           //减少数量
                if(specId != 111L){
                    if(num > 1 ){
                        //dbUtil.reduceGoodNum(goodsDetailBean.getId(),specId);
                        num = num - 1;
                        numTv.setText(num+"");
                        //goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
                    }
                } else {
                    specToast();
                }
                break;
            case R.id.good_spec_dialog_more_ib:             //增加数量
                //这里做优化，将查询放在外面
                if(specId != 111L){
                    if(num < specInventory){
                        num = num + 1;
                        numTv.setText(num+"");
                    } else {                    //小于库存
                        num = specInventory;
                        numTv.setText(num+"");
                        Toast.makeText(this,"库存不足",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.widget_confirm_bt:
                if (specInventory == 0 || num>specInventory){
                    showToast("库存不足");
                    num = specInventory;
                    numTv.setText(num+"");
                }else {
                    addToCartNow();
                }
                break;
        }
    }

    /**
     * 这个方法是添加数量按钮的，但是逻辑改了，还是想把它放这里，感觉稳
     * */
    @Deprecated
    private void buttonAdd(){
        if(specId != 111){
            GoodsBean goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
            if(goodsBean != null){
                if(goodsBean.getNumber() < specInventory){
                    dbUtil.addGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,specId,specName,specPrice,specInventory));
                    num = num+1;
                    numTv.setText(num+"");
                } else {
                    dbUtil.addGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,specId,specName,specPrice,specInventory),specInventory);
                    num = specInventory;
                    numTv.setText(num+"");
                    Toast.makeText(this,"库存不足",Toast.LENGTH_SHORT).show();
                }
                //goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);      //操作完数据库后，更新本地全局变量goodsBean
                //dbUtil.addGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,specId,specName,specPrice,specInventory));
            } else {
                //num = num;
                numTv.setText(num+"");
                dbUtil.addGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,specId,specName,specPrice,specInventory));
                //goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
            }
        } else {
            specToast();
        }
    }

    /**
     * 加入购物车
     * 1, 点击 立即购买
     * 2, 在 选择规格后*/
    private void addToCartNow(){
        if(goodsDetailBean != null){        //商品不为0
            if(specId == 111L){             //规格不为空
                Toast.makeText(this,"请选择规格",Toast.LENGTH_SHORT).show();
            } else {
                Log.i("test",specId+" "+specName + " "+specPrice);
                dbUtil.changeGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,specId,specName,specPrice,specInventory),num);
                goodsDetailBean.setNumber(goodsDetailBean.getNumber()+1);
                numTv.setText(num+"");
                if(isIns == 0){
                    Toast.makeText(WidgetActivity.this,"已加入购物车",Toast.LENGTH_SHORT).show();
                } else if( isIns == 1){
                    Toast.makeText(WidgetActivity.this,"已选择规格",Toast.LENGTH_SHORT).show();
                }

                setBackData();
            }
        } else {
            Toast.makeText(this,"网络繁忙，请重试...",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 立即购买*/
    /*private void buyNow(){
        if(user == null){                   //判断是否登录
            UIHelper.togoLoginActivity(this);
        } else {
            if(goodsDetailBean != null){    //判断是否有数据
                if(specId == 111L){         //判断是否有规格
                    Toast.makeText(this,"请选择规格",Toast.LENGTH_SHORT).show();
                } else {
                    GoodsBean goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
                    if(goodsBean != null){
                        goodsDetailBean.setNumber(goodsBean.getNumber()+1);
                    } else {
                        dbUtil.addGoodNum(dbUtil.convertGoodForDetail(goodsDetailBean,specId,specName,specPrice,specInventory));

                    }
                    UIHelper.togoShopFoodOrderActivity(this,goodsDetailBean.getId(),goodsDetailBean.getStoreId()
                            ,SHOP_MAIN,specId);
                }
            }
            else {
                Toast.makeText(this,"网络繁忙，请重试...",Toast.LENGTH_SHORT).show();
            }
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        user = AppContext.getInstance().getUser();
        if(specId != 111){
            GoodsBean goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
            if(goodsBean != null){
                numTv.setText(goodsBean.getNumber()+"");
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        GoodsBean goodsBean = dbUtil.queryByGoodId(goodsDetailBean.getId(),specId);
        if(goodsBean != null){
            numTv.setText(goodsBean.getNumber()+"");
        } else {
            numTv.setText("1");
        }
    }

    private void specToast(){
        Toast.makeText(WidgetActivity.this,"请选择规格",Toast.LENGTH_SHORT).show();
    }

}
