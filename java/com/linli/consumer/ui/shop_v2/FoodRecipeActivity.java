package com.linli.consumer.ui.shop_v2;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.linli.consumer.R;
import com.linli.consumer.base.BaseActivity;
import com.linli.consumer.bean.FoodRecipeBean;
import com.linli.consumer.utils.CommonUtil;
import com.linli.consumer.widget.BackLayout;

/**
 * Created by tomoyo on 2017/3/9.
 * 食靠谱详情
 */

public class FoodRecipeActivity extends BaseActivity {


    private SimpleDraweeView simpleDraweeView;      //图片
    private TextView nameTv;            //菜名
    private TextView mainTv;            //主料
    private TextView accessoriesTv;     //辅料
    private TextView methodTv;          //烹饪方法
    private TextView tipsTv;            //小贴士

    private BackLayout backLayout;      //返回按钮
    private FoodRecipeBean.DataBean dataBean;       //用于显示的实体，从前一个界面传入

    @Override
    protected int getLayoutId() {
        return R.layout.activity_food_recipe;
    }

    @Override
    protected void initView() {
        simpleDraweeView = findView(R.id.food_recipe_avatar_im);
        nameTv = findView(R.id.food_recipe_name_tv);
        mainTv = findView(R.id.food_recipe_maining_tv);
        accessoriesTv = findView(R.id.food_recipe_accessories_tv);
        methodTv = findView(R.id.food_recipe_method_tv);
        tipsTv = findView(R.id.food_recipe_tips_tv);
        backLayout = findView(R.id.food_recipe_back);
        backLayout.setTitle("食靠谱");
        backLayout.setToSearch();


    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        dataBean = (FoodRecipeBean.DataBean)intent.getSerializableExtra("data");
        if(dataBean != null){
            fillData();
            dismiss();
        }

    }

    @Override
    protected void fillData() {

        simpleDraweeView.setImageURI(CommonUtil.assembleRecipe(dataBean.getImgpath()));
        nameTv.setText(dataBean.getName());
        mainTv.setText(dataBean.getMaining());
        accessoriesTv.setText(dataBean.getAccessories());
        methodTv.setText(dataBean.getMakemethod());
        tipsTv.setText(dataBean.getTips());
    }

    @Override
    public void onHDClick(View v) {

    }
}
