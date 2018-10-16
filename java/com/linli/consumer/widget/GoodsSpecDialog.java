package com.linli.consumer.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.bean.GoodsDetailBean;
import com.linli.consumer.widget.wrilayout.FlowLayout;
import com.linli.consumer.widget.wrilayout.TagAdapter;
import com.linli.consumer.widget.wrilayout.TagFlowLayout;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by tomoyo on 2016/11/8.
 */

public class GoodsSpecDialog extends Dialog {

    private SimpleDraweeView avatarIm;
    private TextView nameTv;
    private TextView priceTv;
    private TextView numTv;
    private RelativeLayout reduceIb;
    private RelativeLayout moreIb;
    private LinearLayout containerLl;

    private Context context;
    private GoodsDetailBean goodsDetailBean;
    private LayoutInflater inflater;

    /**data of container*/
    private List<String> specName = new ArrayList<>();      //规格名称s



    public GoodsSpecDialog(Context context, GoodsDetailBean goodsDetailBean){
        this(context, R.style.widget_mc_dialog);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.goodsDetailBean = goodsDetailBean;
    }
    public GoodsSpecDialog(Context context, int theme) {
        super(context, theme);
        inflater = LayoutInflater.from(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();

    }

    private void initView(){
        setContentView(R.layout.good_detail_dialog);

        avatarIm = (SimpleDraweeView)findViewById(R.id.good_spec_dialog_avatar_im);
        nameTv = (TextView)findViewById(R.id.good_spec_dialog_name_tv);
        priceTv = (TextView)findViewById(R.id.good_spec_dialog_price_tv);
        reduceIb = (RelativeLayout)findViewById(R.id.good_spec_dialog_reduce_ib);
        moreIb = (RelativeLayout)findViewById(R.id.good_spec_dialog_more_ib);
        containerLl = (LinearLayout)findViewById(R.id.good_spec_dialog_spec_container_ll);

    }
    private void initData(){
        if(goodsDetailBean != null){
            avatarIm.setImageURI(goodsDetailBean.getPrimaryImage());
            nameTv.setText(goodsDetailBean.getName());
            initContainer();
        }
    }

    private List<String> specList1 = new ArrayList<>();         //规格1的合集
    private List<String> specList2 = new ArrayList<>();         //规格2的合集
    private List<String> specList3 = new ArrayList<>();         //规格3的合集

    private String specString1 = "";
    private String specString2 = "";
    private String specString3 = "";

    private Map<String,String[]> specMap = new HashMap<>();     //规格详情map
    private void initContainer(){
        //存起来总规格名称
        for(int i = 0 ; i < goodsDetailBean.getSpecNameValues().size();i ++){
            specName.add(goodsDetailBean.getSpecNameValues().get(i));
        }

        for(int i = 0 ; i < goodsDetailBean.getGoodsSpecs().size() ; i ++ ) {   //规格1填充
            specList1.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecaValue());
        }
        String [] spec1 = specList1.toArray(new String[specList1.size()]);
        specMap.put(specName.get(0),spec1);

        for(int i = 0 ; i < goodsDetailBean.getGoodsSpecs().size() ; i ++ ) {   //规格2填充
            specList2.add(goodsDetailBean.getGoodsSpecs().get(i).getSpecbValue());
        }
        String [] spec2 = specList2.toArray(new String[specList2.size()]);
        specMap.put(specName.get(1),spec2);

        for(int i = 0 ; i < goodsDetailBean.getGoodsSpecs().size() ; i ++ ) {   //规格3填充
            specList3.add(goodsDetailBean.getGoodsSpecs().get(i).getSpeccValue());
        }
        String [] spec3 = specList3.toArray(new String[specList3.size()]);
        specMap.put(specName.get(2),spec3);

        //名称布局实例化
        for(int i = 0 ; i < specName.size() ; i ++){
            View nameLl = inflater.inflate(R.layout.layout_goods_spec_name,containerLl,false);
            TextView nameTv = (TextView)nameLl.findViewById(R.id.widget_spec_tag_name_tv);
            nameTv.setText(specName.get(i));
            final View fl = inflater.inflate(R.layout.layout_goods_spec_flowlayout,containerLl,false);
            final TagFlowLayout tagFlowLayout = (TagFlowLayout)fl.findViewById(R.id.widget_spec_tag_flowlayout);

            tagFlowLayout.setAdapter(new TagAdapter<String>(specMap.get(specName.get(i))) {
                @Override
                public View getView(FlowLayout parent, int position, String string) {
                    TextView tv = (TextView)inflater.inflate(R.layout.layout_goods_spec_tag,tagFlowLayout,false);
                    tv.setText(string);
                    return tv;
                }
            });

        }

    }


}
